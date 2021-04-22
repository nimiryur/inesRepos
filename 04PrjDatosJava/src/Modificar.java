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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Modificar extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPass;
	private JTextField textNombre;
	private Connection conexion;
	private JTextField textBuscar;
	private JLabel lblNo;
	private String tmp_user;
	private JComboBox comboNombre;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar frame = new Modificar();
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
	public Modificar() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(187, 22, 60, 28);
		contentPane.add(lblNewLabel);
		
		textUser = new JTextField();
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textUser.setBounds(119, 50, 196, 28);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(178, 89, 78, 28);
		contentPane.add(lblContrasea);
		
		textPass = new JTextField();
		textPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPass.setColumns(10);
		textPass.setBounds(119, 128, 196, 28);
		contentPane.add(textPass);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(186, 167, 60, 28);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNombre.setColumns(10);
		textNombre.setBounds(119, 206, 196, 28);
		contentPane.add(textNombre);
		
		JButton btnModificar = new JButton("Modificar");
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
					
				} catch (SQLException e2) {
					// ERRORES
					System.out.println(e2.getMessage().toString());
					String msg = "No ha sido posible realizar la operación";
					JOptionPane.showMessageDialog(null, msg, "Error de Base de Datos!!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnModificar.setBackground(Color.PINK);
		btnModificar.setForeground(new Color(30, 144, 255));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificar.setBounds(119, 268, 196, 28);
		contentPane.add(btnModificar);
		
		textBuscar = new JTextField();
		textBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textBuscar.setColumns(10);
		textBuscar.setBounds(370, 50, 196, 28);
		contentPane.add(textBuscar);
		
		JButton btnBuscar = new JButton();
		ImageIcon icono = new ImageIcon("assets/lupa.png");
		btnBuscar.setIcon(icono);
		btnBuscar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				String str_buscar = textBuscar.getText();
				buscar(str_buscar);
				
			}
		});
		btnBuscar.setBounds(600, 50, 48, 41);
		contentPane.add(btnBuscar);
		
		lblNo = new JLabel("No se han encontrado coincidencias");
		lblNo.setVisible(false);
		lblNo.setForeground(Color.RED);
		lblNo.setBounds(370, 93, 218, 24);
		contentPane.add(lblNo);
		
		
		comboNombre = new JComboBox();
		comboNombre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str_buscar = (String) comboNombre.getSelectedItem();
				buscar(str_buscar);
			}
			
		});
		comboNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboNombre.setBounds(370, 169, 196, 24);
		contentPane.add(comboNombre);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuarios.setBounds(370, 130, 78, 28);
		contentPane.add(lblUsuarios);
		
		
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
		cargar_Combo();
		
	}// fin del constructor
	
	public void cargar_Combo() {
		String sql = "SELECT `user` FROM `trj_user`";
		

		Statement registro;
		try {
			registro = conexion.createStatement();
			ResultSet consulta = registro.executeQuery(sql);
			comboNombre.addItem("");
			while (consulta.next()) {
				comboNombre.addItem(consulta.getString("user").toString());
				//System.out.println(consulta.getString("user"));
			} 
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}// FIN CARGAR COMBO
	
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
	}
	
	
}//fin clase
	
	
