import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private DefaultListModel dlAcciones = new DefaultListModel();
	private Integer indexOpcion;
	private boolean eligioOpcion=false;
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
	
		
		JLabel lblNewLabel = new JLabel("\u00BFQue desea hacer?");
		lblNewLabel.setBounds(54, 67, 102, 14);
		contentPane.add(lblNewLabel);
	
		JList list = new JList(dlAcciones);
		list.setBounds(42, 92, 125, 143);
		contentPane.add(list);
		
		JButton AceptarOptButton = new JButton("Aceptar");
		AceptarOptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(eligioOpcion) {
					switch (indexOpcion) {
					case 0: //alta
//						altaLibro();
						break;
					case 1:
						System.out.println("puto");
					default:
						break;
					}
				}
			}
		});
		AceptarOptButton.setBounds(246, 144, 89, 23);
		contentPane.add(AceptarOptButton);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getFirstIndex() != -1 && !e.getValueIsAdjusting()) {
					indexOpcion = list.getSelectedIndex();
					eligioOpcion = true;
				}
			}
		});
		
		
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