import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaListar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultListModel dlLibros = new DefaultListModel();
	private JTable table;
	private DefaultTableModel modeloTabla;

	public PantallaListar(Vector<Libro> librosCreados) {
		setBounds(100, 100, 732, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 706, 244);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 209, 706, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(SystemColor.activeCaption, 4, true));
			panel.setBounds(168, 13, 382, 36);
			contentPanel.add(panel);
			{
				JLabel lblLibrosRegistrados = new JLabel("LIBROS REGISTRADOS");
				lblLibrosRegistrados.setFont(new Font("Perpetua Titling MT", Font.BOLD, 20));
				panel.add(lblLibrosRegistrados);
			}
		}
		String[] titulos = { "ISBN", "EDITORIAL", "AUTOR", "EDICION", "AÑO P.", "TITULO" };
		Object informacion[][] = new Object[librosCreados.size()][titulos.length];
		for (int i = 0; i < librosCreados.size(); i++) {
			informacion[i][0] = librosCreados.get(i).getISBN();
			informacion[i][1] = librosCreados.get(i).getEditorial();
			informacion[i][2] = librosCreados.get(i).getAutor();
			informacion[i][3] = librosCreados.get(i).getEdicion();
			informacion[i][4] = librosCreados.get(i).getAnno_de_publicacion();
			informacion[i][5] = librosCreados.get(i).getTitulo();
		}
		modeloTabla = new DefaultTableModel(informacion, titulos);

		table = new JTable(modeloTabla) {
	
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// table.setRowSelectionAllowed(true);
		table.setBorder(null);
		table.setBounds(0, 0, 253, 107);
		table.setRowSelectionAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 55, 676, 130);

		contentPanel.add(scrollPane);

		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(180);

	}

	public String listarLibros(Vector<Libro> librosCreados) {
		String listaL = String.format("%-20s	%-90s	%-30s	%-30s	%-10s	%-30s\n", "ISBN", "TITULO", "AUTOR",
				"EDITORIAL", "EDICION", "AÑO DE PUBLICACION");
		for (Libro libro : librosCreados) {
			listaL += libro.toString();
		}

		return listaL;

	}
}
