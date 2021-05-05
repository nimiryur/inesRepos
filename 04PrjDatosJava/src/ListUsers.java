import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxSession.Reset;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListUsers extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPass;
	private JTextField textNombre;
	private Connection conexion;
	private String tmp_user;
	private JPanel panel;
	private JTable tabla;
	private DefaultTableModel modelo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListUsers frame = new ListUsers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListUsers() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 255, 204), 2));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBounds(454, 25, 262, 282);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(108, 14, 45, 17);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textUser = new JTextField();
		textUser.setBounds(67, 45, 126, 23);
		panel.add(textUser);
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textUser.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(95, 82, 70, 17);
		panel.add(lblContrasea);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textPass = new JTextField();
		textPass.setBounds(67, 113, 126, 23);
		panel.add(textPass);
		textPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPass.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(100, 150, 60, 28);
		panel.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textNombre = new JTextField();
		textNombre.setBounds(32, 192, 196, 28);
		panel.add(textNombre);
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNombre.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.setBounds(32, 234, 196, 28);
		panel.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textUser.getText().toString();
				String pass = textPass.getText().toString();
				String nombre = textNombre.getText().toString();
				
				String sql = "UPDATE `trj_user` SET `user` = '"+ user + "', `pass` = '" + pass + "', `nombre` = '"+ nombre +"' WHERE `trj_user`.`user` = '"+ user + "'; ";
				//Debug
				System.out.println(sql);
				textUser.setText(null);
				textPass.setText(null);
				textNombre.setText(null);
				
				try {
					//Statement necesario para ir a la base de datos
					Statement comando = conexion.createStatement();
					
					comando.execute(sql);
					
					String msg = "El registro ha sido grabado con éxito";
					JOptionPane.showMessageDialog(null, msg, "Registro", 
							JOptionPane.INFORMATION_MESSAGE);
					
					
					
					//********REFRESCAR EL MODELO DESPUES DE MODIFICAR****
					int contador = modelo.getRowCount();
					for (int i = contador-1; i >= 0; i--) {
						modelo.removeRow(i);
						
						//System.out.println("ahora");
					}
					
														
					rellenar_tabla();
					
				} catch (SQLException e2) {
					// ERRORES
					System.out.println(e2.getMessage().toString());
					String msg = "No ha sido posible realizar la operación";
					JOptionPane.showMessageDialog(null, msg, "Error de Base de Datos!!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnModificar.setBackground(new Color(255, 250, 205));
		btnModificar.setForeground(new Color(0, 139, 139));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 25, 396, 282);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tabla.rowAtPoint(e.getPoint()); //devuelve la fila donde se clicka
				//System.out.println(fila);
				int columna = 0;
				if ((fila>-1)&&(columna>-1)) {
					textUser.setText(modelo.getValueAt(fila, columna).toString());
					textPass.setText(modelo.getValueAt(fila, columna+1).toString());
					textNombre.setText(modelo.getValueAt(fila, columna+2).toString());
					
					btnModificar.setEnabled(true);
				} 
			}
		});
		scrollPane.setViewportView(tabla);
		//ImageIcon icono = new ImageIcon("assets/lupa.png");
		
		modelo  = new DefaultTableModel();
	
		modelo.addColumn("Usuario");
		modelo.addColumn("Pass");
		modelo.addColumn("Nombre");
		
		tabla.setModel(modelo);
		
		
		// -----------CONEXION A LA BASE DE DATOS-------------
		String url = "jdbc:mysql://localhost:3306/java";
		String usuario = "Ines";
		String password = "1234";
		
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
					"Ha sido imposible conectar con la base de Datos,\n" + 
					"revise los datos de conexion");
			JOptionPane.showInternalMessageDialog(this, e.toString());
		}
		
		//CARGAR COMBO
		//cargar_Combo();
		rellenar_tabla();
		
	}// fin del constructor
	
	//**************REFRESCAR *******//
	
	public void refrescar(String condition) {
		
	}
	
	
	//*********limpiar campos***
		public void limpiar_campos() {
			textUser.setText(null);
			textPass.setText(null);
			textNombre.setText(null);
		}
	//*************rellenar_tabla() ******
	
	public void rellenar_tabla() {
				
		String sql = "SELECT `user`, `pass`, `nombre` FROM `trj_user`";
				
		try {
			//Statement necesario para ir a la base de datos
			Statement comando = conexion.createStatement();
			comando.execute(sql);
			ResultSet resultado = comando.executeQuery(sql);
			while (resultado.next()) {
				
				Object[] fila = new Object[3];
				//lo que hay entre comillas son lo campos de la base de datos
				fila[0] = resultado.getString("user");
				fila[1] = resultado.getString("pass");
				fila[2] = resultado.getString("nombre");
				
				modelo.addRow(fila);
				//System.out.println(resultado.getString("user"));
				//modelo.removeRow();
				
			}
			
			
		} catch (SQLException e2) {
			// ERRORES
			System.out.println(e2.getMessage().toString());
			
		}
	
		
	}
	
	
	/*
	public void cargar_Combo() {
		String sql = "SELECT `user` FROM `trj_user`";
		

		Statement registro;
		try {
			registro = conexion.createStatement();
			ResultSet consulta = registro.executeQuery(sql);
			while (consulta.next()) {
				comboNombre.addItem(consulta.getString("user").toString());
				//System.out.println(consulta.getString("user"));
			} 
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}// FIN CARGAR COMBO*/
	
	/*
	public void buscar(String str_search) {
		
		String sql = "SELECT * FROM `trj_user` WHERE `user`= '"+ str_search +"'";
		System.out.println(sql);
		
		Statement registro;
		try {
			registro = conexion.createStatement();
			ResultSet consulta = registro.executeQuery(sql);
			if (consulta.next()) {
				lblNo.setVisible(false);
				tmp_user = consulta.getString("user");
				textUser.setText(consulta.getString("user"));
				textPass.setText(consulta.getString("pass"));
				textNombre.setText(consulta.getString("nombre"));
			} else {
				lblNo.setVisible(true);
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}*/
}//fin clase
	
	
