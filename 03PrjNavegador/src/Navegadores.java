import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;


import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Navegadores extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscador;
	private ButtonGroup bg;
	private Desktop desktop = null;
	
	private JRadioButton rdbGoogle;
	private JRadioButton rdbYahoo;
	private JRadioButton rdbDuck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Navegadores frame = new Navegadores();
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
	public Navegadores() {
		setTitle("Buscador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecciona el Buscador:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(36, 24, 165, 23);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GREEN, 1, true));
		panel.setBounds(36, 58, 139, 104);
		contentPane.add(panel);
		
		bg = new ButtonGroup();
		
		rdbGoogle = new JRadioButton("Google");
		rdbGoogle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bg.add(rdbGoogle);
		
		rdbYahoo = new JRadioButton("Yahoo");
		rdbYahoo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bg.add(rdbYahoo);
		
		rdbDuck = new JRadioButton("Duck Duck Go");
		rdbDuck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bg.add(rdbDuck);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(rdbGoogle)
							.addContainerGap())
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(rdbYahoo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
							.addComponent(rdbDuck))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(rdbGoogle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbYahoo)
					.addGap(7)
					.addComponent(rdbDuck))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Criterio");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(35, 208, 54, 23);
		contentPane.add(lblNewLabel_1);
		
		txtBuscador = new JTextField();
		txtBuscador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBuscador.setBounds(86, 208, 214, 23); 
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);
		
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "";
				if (rdbGoogle.isSelected()) {
					url = "https://www.google.com/search?q=" + txtBuscador.getText().replaceAll(" ", "+");
				} else if(rdbYahoo.isSelected()){
					url = "https://es.search.yahoo.com/search?p=" + txtBuscador.getText().replaceAll(" ", "+");
				}else if(rdbDuck.isSelected()) {
					url = "https://duckduckgo.com/?q=" + txtBuscador.getText().replaceAll(" ", "+");
				}
	
				try {
					URI uri = new URI(url);
					desktop = Desktop.getDesktop();
					if (desktop != null) {
						desktop.browse(uri);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage().toString());
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscar.setBounds(316, 210, 89, 23);
		contentPane.add(btnBuscar);
	}
}
