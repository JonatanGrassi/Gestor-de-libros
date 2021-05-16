import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JLabel;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private DefaultListModel dlAcciones = new DefaultListModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		agregarOpcionesLista();
		JList list = new JList(dlAcciones);
		list.setBounds(42, 92, 125, 143);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("\u00BFQue desea hacer?");
		lblNewLabel.setBounds(54, 67, 102, 14);
		contentPane.add(lblNewLabel);
	
		
		
	}
	
	public void agregarOpcionesLista() {
		
		dlAcciones.addElement("1.- Altas");
		dlAcciones.addElement("2.- Consultas");
		dlAcciones.addElement("3.- Actualizaciones");
		dlAcciones.addElement("4.- Bajas");
		dlAcciones.addElement("5.- Ordenar registros");
		dlAcciones.addElement("6.- Listar registros");
		dlAcciones.addElement("7.- Salir");
	}
}
