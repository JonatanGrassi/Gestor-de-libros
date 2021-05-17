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
import java.util.Vector;
import java.awt.event.ActionEvent;

public class PantallaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField ConstraseniaTextField;
	private JTextField NUsuarioTextField;

	/////////
	public static String ruta = "libros.tsv";
	public static String rutaUsers = "Users.txt";
	public static ManejadorArchivos Archivo = new ManejadorArchivos(ruta);
	public static ManejadorArchivos ArchivoUsers = new ManejadorArchivos(rutaUsers);	
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
		NUsuario.setBounds(55, 47, 91, 14);
		contentPane.add(NUsuario);
		
		JLabel Contrasenia = new JLabel("Constrasenia");
		Contrasenia.setBounds(55, 119, 83, 14);
		contentPane.add(Contrasenia);
		
		ConstraseniaTextField = new JTextField();
		ConstraseniaTextField.setBounds(210, 116, 119, 20);
		contentPane.add(ConstraseniaTextField);
		ConstraseniaTextField.setColumns(10);
		
		
		NUsuarioTextField = new JTextField();
		NUsuarioTextField.setBounds(210, 44, 119, 20);
		contentPane.add(NUsuarioTextField);
		NUsuarioTextField.setColumns(10);
		
		
		
		JButton IngresarButton = new JButton("Ingresar");
		IngresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<User> users = new ArrayList<>();
				users = ArchivoUsers.leerUsers();
				if(validarUsuario(users,NUsuarioTextField.getText(),ConstraseniaTextField.getText())) {
					PantallaPrincipal principal = new PantallaPrincipal();
					principal.setVisible(true);
					dispose();
				}else
				{
					JOptionPane.showMessageDialog(PantallaUsuario.this,
							"Usuario no existente","Error al Entrar",JOptionPane.ERROR_MESSAGE);
				}
				
//				ManejadorArchivos usersFile = new ManejadorArchivos("Users.txt");
//				ArrayList<User> usuariosReg= usersFile.leerUsers();
//				User usuario = new User(NUsuarioTextField.getText(),ConstraseniaTextField.getText());
//				if(usuariosReg.contains(usuario)) {
//				PantallaPrincipal principal = new PantallaPrincipal();
//				principal.setVisible(true);
//				dispose();	
//				}
			}
		});
		IngresarButton.setBounds(323, 151, 89, 23);
		contentPane.add(IngresarButton);
		
		JLabel lblNewLabel = new JLabel("\u00BFSos Nuevo?");
		lblNewLabel.setBounds(58, 184, 88, 14);
		contentPane.add(lblNewLabel);
		
		JButton RegistrarseButton = new JButton("Registrate");
		RegistrarseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaRegistroUsuario registro = new PantallaRegistroUsuario();
				registro.setVisible(true);
				dispose();
			}
		});
		RegistrarseButton.setBounds(179, 227, 89, 23);
		contentPane.add(RegistrarseButton);
	}
	
	private static boolean validarUsuario(ArrayList<User> users,String nUsm, String pssw) {
		boolean coicidencia = false;
		//do {
			User usuario = new User(nUsm,pssw);
			for (User user : users)
				if (user.equals(usuario))
					coicidencia = true;
		// while (!coicidencia);
		return coicidencia;
	}

	
}
