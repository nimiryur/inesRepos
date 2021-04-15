import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmUserPais extends JFrame {

	private JPanel contentPane;
	private JComboBox comboNombre;
	private JButton btnEjecutar;
	private JComboBox comboPais;
	private JComboBox comboFoto;
	private JLabel lblNombre;
	private JLabel lblPais;
	private JLabel lblFoto;
	private JLabel lblImagen; 
	private ImageIcon imagenBandera;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUserPais frame = new FrmUserPais();
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
	public FrmUserPais() {
		setTitle("PAISES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboNombre = new JComboBox();
		comboNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboNombre.setModel(new DefaultComboBoxModel(new String[] {"", "Paco", "Pepe", "Juan"}));
		comboNombre.setBounds(158, 21, 158, 22);
		contentPane.add(comboNombre);
		
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == btnEjecutar) {
					  
					String nombre = comboNombre.getSelectedItem().toString();
					String pais = comboPais.getSelectedItem().toString();
					String bandera = comboFoto.getSelectedItem().toString();

					btnEjecutar.setEnabled(true);
					imagenBandera = new ImageIcon("assest/" + comboFoto.getSelectedItem());
					lblImagen.setIcon(imagenBandera);
			
					JOptionPane.showMessageDialog(null, "Su nombre es "  + nombre + " y su pais es: " + pais , "Mensaje" , 1);
					
				} 
				/*
				
				if (nombre == "" || pais == "" || bandera == "") {
					btnEjecutar.setEnabled(false);
					
				} else {
					btnEjecutar.setEnabled(true);

				}*/
				
			}
		});
		btnEjecutar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEjecutar.setBounds(26, 161, 89, 23);
		contentPane.add(btnEjecutar);
		
		comboPais = new JComboBox();
		comboPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboPais.setModel(new DefaultComboBoxModel(new String[] {"", "Espana", "Francia", "Alemania"}));
		comboPais.setBounds(158, 63, 158, 22);
		contentPane.add(comboPais);
		
		comboFoto = new JComboBox();
		comboFoto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboFoto.setModel(new DefaultComboBoxModel(new String[] {"", "espana.png", "alemania.png", "francia.png"}));
		comboFoto.setBounds(158, 108, 158, 22);
		contentPane.add(comboFoto);
		
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(26, 23, 89, 18);
		contentPane.add(lblNombre);
		
		lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPais.setBounds(26, 65, 89, 18);
		contentPane.add(lblPais);
		
		lblFoto = new JLabel("Foto:");
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFoto.setBounds(26, 110, 89, 18);
		contentPane.add(lblFoto);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(158, 149, 192, 101);
		contentPane.add(lblImagen);
		
	}
}
