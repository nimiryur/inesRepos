import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class FrmMenu extends JFrame implements ActionListener {

	private JMenuItem mntmSalir;
	private JMenuItem mntmColor;
	private JMenuItem mntmOriginal;
	private JMenuItem mntm800;
	private JMenuItem mntm1024;
	private JMenuItem mntmCustom;
	private JTextField textAlto;
	private JTextField textAncho;
	
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
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
	public FrmMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 300);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar_1.add(mnNewMenu);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mnNewMenu.add(mntmNuevo);
		
		mntmSalir = new JMenuItem("Salir", new ImageIcon("assest/salida.png"));
		mntmSalir.addActionListener(this); //hay que añadir los objetos que tiene que escuchar
		mnNewMenu.add(mntmSalir);
		
		JMenu mnNewMenu_1 = new JMenu("Options");
		menuBar_1.add(mnNewMenu_1);
		
		mntmColor = new JMenuItem("Cambiar Color", new ImageIcon("assest/circulo-de-color.png"));
		mntmColor.addActionListener(this); 
		mnNewMenu_1.add(mntmColor);
		
		JMenu mnRedimensionar = new JMenu("Redimensionar");
		mnNewMenu_1.add(mnRedimensionar);
		
		mntmOriginal = new JMenuItem("Original");
		mntmOriginal.addActionListener(this);
		mnRedimensionar.add(mntmOriginal);
		
		mntm800 = new JMenuItem("800 x 600");
		mntm800.addActionListener(this);
		mnRedimensionar.add(mntm800);
		
		mntm1024 = new JMenuItem("1024 x 786");
		mntm1024.addActionListener(this);
		mnRedimensionar.add(mntm1024);
		
		mntmCustom = new JMenuItem("Custom");
		mnRedimensionar.add(mntmCustom);
		mntmCustom.addActionListener(this);
		getContentPane().setLayout(null);
		
		JLabel lblAlto = new JLabel("Alto");
		lblAlto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAlto.setBounds(326, 48, 46, 14);
		getContentPane().add(lblAlto);
		
		textAlto = new JTextField();
		textAlto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAlto.setBounds(382, 33, 86, 29);
		getContentPane().add(textAlto);
		textAlto.setColumns(10);
		
		JLabel lblAncho = new JLabel("Ancho");
		lblAncho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAncho.setBounds(326, 83, 46, 14);
		getContentPane().add(lblAncho);
		
		textAncho = new JTextField();
		textAncho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAncho.setColumns(10);
		textAncho.setBounds(382, 68, 86, 29);
		getContentPane().add(textAncho);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == mntmSalir) {
			System.exit(0);
		}
		else if(e.getSource()==mntmColor) {
			int r = (int) (Math.random()*255);
			int g = (int) (Math.random()*255);
			int b = (int) (Math.random()*255);
			 
			this.getContentPane().setBackground(new Color(r, g, b));
		}
		else if(e.getSource()== mntmOriginal){
			setSize(530, 300);
			
		}
		else if(e.getSource() == mntm800){
			setSize(800, 600);
			
		}
		else if(e.getSource() == mntm1024){
			setSize(1024, 786);
			
		}
		else if(e.getSource() == mntmCustom){
			try {
				int alto = Integer.parseInt(textAlto.getText());
				int ancho = Integer.parseInt(textAncho.getText());
				setSize(alto, ancho);
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Tienes que rellenar las cajas","Error",0);
			}
			
		}
		
	}
}
