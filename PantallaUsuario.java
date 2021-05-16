import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PantallaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField ConstraseniaTextField;
	private JTextField NUsuarioTextField;

	/**
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
		ConstraseniaTextField.setBounds(210, 116, 86, 20);
		contentPane.add(ConstraseniaTextField);
		ConstraseniaTextField.setColumns(10);
		
		NUsuarioTextField = new JTextField();
		NUsuarioTextField.setBounds(210, 44, 86, 20);
		contentPane.add(NUsuarioTextField);
		NUsuarioTextField.setColumns(10);
		
		JButton IngresarButton = new JButton("Ingresar");
		IngresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManejadorArchivos usersFile = new ManejadorArchivos("Users.txt");
				ArrayList<User> usuariosReg= usersFile.leerUsers();
				User usuario = new User(NUsuarioTextField.getText(),ConstraseniaTextField.getText());
				if(usuariosReg.contains(usuario)) {
				PantallaPrincipal principal = new PantallaPrincipal();
				principal.setVisible(true);
				dispose();
					
				}
			}
		});
		IngresarButton.setBounds(321, 204, 89, 23);
		contentPane.add(IngresarButton);
	}

}
