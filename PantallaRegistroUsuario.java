import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;

public class PantallaRegistroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField NomUsTextField;
	private JPasswordField PsswTextField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaRegistroUsuario frame = new PantallaRegistroUsuario();
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
	public PantallaRegistroUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		lblNewLabel.setBounds(38, 79, 162, 14);
		contentPane.add(lblNewLabel);

		NomUsTextField = new JTextField();
		NomUsTextField.setBounds(240, 73, 121, 26);
		contentPane.add(NomUsTextField);
		NomUsTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(38, 114, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		PsswTextField2 = new JPasswordField();
		PsswTextField2.setBounds(240, 108, 121, 26);
		contentPane.add(PsswTextField2);
				
						JButton CrearUsButton = new JButton("Crear Usuario");
						CrearUsButton.setBounds(222, 185, 162, 29);
						contentPane.add(CrearUsButton);
						CrearUsButton.setBackground(SystemColor.activeCaption);
						
						JPanel panel = new JPanel();
						panel.setBorder(new LineBorder(SystemColor.activeCaption, 7, true));
						panel.setBounds(207, 28, 192, 200);
						contentPane.add(panel);
				CrearUsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
				
						ManejadorArchivos userFile = new ManejadorArchivos("Users.txt");
						ArrayList<User> UsExistentes = userFile.leerUsers();
						
						if(NomUsTextField.getText().equals("") ||  PsswTextField2.getPassword().toString().equals("")) {
							JOptionPane.showMessageDialog(PantallaRegistroUsuario.this,
									"Debe ingresar un nombre y contraseña","Campo/s vacios",JOptionPane.ERROR_MESSAGE);
						}else if (UsExistentes.contains(new User(PsswTextField2.getPassword().toString(),NomUsTextField.getText()))) {
							JOptionPane.showMessageDialog(PantallaRegistroUsuario.this,
									"El usuario ya existe, ingrese otro nombre","Error al Registar",JOptionPane.ERROR_MESSAGE);
						} else {
							userFile.registrarUsuarios(NomUsTextField.getText(), PsswTextField2.getPassword().toString());
							JOptionPane.showMessageDialog(PantallaRegistroUsuario.this, "Se ha Registrado con exito");
							PantallaUsuario usuario = new PantallaUsuario();
							usuario.setVisible(true);
							dispose();
						}
					}
				});
	}
}
