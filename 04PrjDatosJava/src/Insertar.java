import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Insertar extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPass;
	private JTextField textNombre;
	private Connection conexion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insertar frame = new Insertar();
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
	public Insertar() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 366);
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
		
		JButton btnInsertar = new JButton("Registrar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textUser.getText().toString();
				String pass = textPass.getText().toString();
				String nombre = textNombre.getText().toString();
				
				String sql = "INSERT INTO `trj_user` (`user`, `pass`, `nombre`)" + " VALUES ('"+ user + "', '" + pass + "', '"+ nombre +"');";
				
				//Debug
				//System.out.println(sql);
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
		btnInsertar.setBackground(Color.PINK);
		btnInsertar.setForeground(new Color(30, 144, 255));
		btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInsertar.setBounds(119, 268, 196, 28);
		contentPane.add(btnInsertar);
		
		
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
		
	}// fin del constructor
		
}//fin clase
	
	
