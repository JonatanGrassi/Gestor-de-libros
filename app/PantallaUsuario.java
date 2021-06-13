package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import com.sun.xml.internal.fastinfoset.Encoder;

import Codigo.Libro;
import Codigo.ManejadorArchivos;
import Codigo.User;
import Interfaz.PantallaPrincipal;
import Interfaz.PantallaRegistroUsuario;

import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class PantallaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField NUsuarioTextField;

	/////////
	public static String ruta = "libros.tsv";
	public static String rutaUsers = "Users.txt";
	public static ManejadorArchivos Archivo = new ManejadorArchivos(ruta);
	public static ManejadorArchivos ArchivoUsers = new ManejadorArchivos(rutaUsers);
	private JPasswordField ConstraseniaTextField;
	private boolean seVeContra = false;
	private ImageIcon iconoOjoAbierto = new ImageIcon("Iconos\\visibilidad (2).png");
	private ImageIcon iconoOjoCerrado = new ImageIcon("Iconos\\visibilidad (3).png");
	
	////////

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaUsuario frame = new PantallaUsuario();
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
	public PantallaUsuario() {

		Vector<Libro> vector = new Vector<>();

		int[] contador = { 0 };
		int opcion;
//		Libro libro = new Libro(), dato = null;
		vector = Archivo.leerArchivoLibros();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel NUsuario = new JLabel("Nombre de Usuario");
		NUsuario.setBounds(55, 47, 152, 14);
		contentPane.add(NUsuario);

		JLabel Contrasenia = new JLabel("Constrase\u00F1a");
		Contrasenia.setBounds(55, 104, 102, 14);
		contentPane.add(Contrasenia);

		NUsuarioTextField = new JTextField();
		NUsuarioTextField.setBounds(240, 44, 94, 23);
		contentPane.add(NUsuarioTextField);
		NUsuarioTextField.setColumns(10);

		JButton IngresarButton = new JButton("Ingresar");
		IngresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<User> users = new ArrayList<>();
				users = ArchivoUsers.leerUsers();
				char [] password = ConstraseniaTextField.getPassword();
				if (validarUsuario(users, NUsuarioTextField.getText(),
						String.valueOf(ConstraseniaTextField.getPassword()))) {
			      
					PantallaPrincipal principal = new PantallaPrincipal();
					
					principal.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(PantallaUsuario.this, "Usuario no existente", "Error al Entrar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		IngresarButton.setBounds(240, 151, 94, 23);
		contentPane.add(IngresarButton);

		JLabel lblNewLabel = new JLabel("\u00BFSos Nuevo?");
		lblNewLabel.setBounds(78, 209, 119, 14);
		contentPane.add(lblNewLabel);

		JButton RegistrarseButton = new JButton("Registrate");
		RegistrarseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaRegistroUsuario registro = new PantallaRegistroUsuario();
				registro.setVisible(true);
				dispose();
			}
		});
		RegistrarseButton.setBounds(195, 205, 119, 23);
		contentPane.add(RegistrarseButton);

		ConstraseniaTextField = new JPasswordField();
		ConstraseniaTextField.setEchoChar('*');
		ConstraseniaTextField.setBounds(240, 98, 94, 26);
		contentPane.add(ConstraseniaTextField);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 4, true));
		panel.setBounds(15, 16, 397, 177);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!seVeContra) {
					
					ConstraseniaTextField.setEchoChar((char) 0);
					seVeContra = true;
					btnNewButton.setIcon(iconoOjoCerrado);
				}
				else
				{	
					seVeContra = false;
					ConstraseniaTextField.setEchoChar('*');
					btnNewButton.setIcon(iconoOjoAbierto);
				}
			}
		});
		
		btnNewButton.setIcon(iconoOjoAbierto);
		btnNewButton.setBounds(330, 82, 25, 25);
		panel.add(btnNewButton);
	}

	private static boolean validarUsuario(ArrayList<User> users, String nUsm, String pssw) {
		boolean coicidencia = false;
		// do {
		User usuario = new User(nUsm, pssw);
		for (User user : users)
			if (user.equals(usuario))
				coicidencia = true;
		// while (!coicidencia);
		return coicidencia;
	}
}
