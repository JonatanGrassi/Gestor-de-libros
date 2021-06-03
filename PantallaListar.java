import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import java.awt.ScrollPane;

public class PantallaListar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultListModel dlLibros = new DefaultListModel();
	

	public PantallaListar(Vector<Libro> librosCreados) {
		setBounds(100, 100, 732, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 706, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		JList list = new JList(dlLibros);
		list.setBounds(461, 11, 167, 48);
		contentPanel.add(list);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(636, 152, 17, 65);
		contentPanel.add(scrollBar);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(80, 77, 400, 107);

		contentPanel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(listarLibros(librosCreados));
		textArea.setBounds(51, 132, 627, 39);
		scrollPane.add(textArea);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

		
	public String listarLibros(Vector<Libro> librosCreados) {
		String listaL = String.format("%-20s	%-90s	%-30s	%-30s	%-10s	%-30s\n", "ISBN","TITULO","AUTOR","EDITORIAL","EDICION","AÑO DE PUBLICACION");
		for (Libro libro : librosCreados) {
			listaL += libro.toString();
		}
		return listaL;
	
	}
}
