import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class PantallaAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField isbnTextField;
	private JTextField tituloTextField;
	private JTextField autorTextField;
	private JTextField editorialTextField;
	private JTextField edicionTextField;
	private JTextField publicTextField;
	
	
	Libro libro = new Libro(), dato = null;

	

	/**
	 * Create the dialog.
	 */
	public PantallaAlta( Vector<Libro> librosCreados,int[] contador) {
		setBounds(100, 100, 656, 312);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel isbnLabel = new JLabel("ISBN");
			isbnLabel.setBounds(92, 73, 46, 14);
			contentPanel.add(isbnLabel);
		}
		{
			JLabel publiLabel = new JLabel("A\u00F1o de Publicacion");
			publiLabel.setBounds(422, 182, 128, 14);
			contentPanel.add(publiLabel);
		}
		{
			JLabel tituloLabel = new JLabel("Titulo");
			tituloLabel.setBounds(92, 121, 46, 14);
			contentPanel.add(tituloLabel);
		}
		{
			JLabel autorLabel = new JLabel("Autor");
			autorLabel.setBounds(390, 121, 46, 14);
			contentPanel.add(autorLabel);
		}
		{
			JLabel editorialLabel = new JLabel("Editorial");
			editorialLabel.setBounds(92, 182, 46, 14);
			contentPanel.add(editorialLabel);
		}
		{
			JLabel edicionLabel = new JLabel("Edicion");
			edicionLabel.setBounds(257, 182, 46, 14);
			contentPanel.add(edicionLabel);
		}
		{
			isbnTextField = new JTextField();
			isbnTextField.setBounds(179, 70, 86, 20);
			contentPanel.add(isbnTextField);
			isbnTextField.setColumns(10);
		}
		{
			tituloTextField = new JTextField();
			tituloTextField.setBounds(179, 118, 176, 20);
			contentPanel.add(tituloTextField);
			tituloTextField.setColumns(10);
		}
		{
			autorTextField = new JTextField();
			autorTextField.setBounds(446, 118, 135, 20);
			contentPanel.add(autorTextField);
			autorTextField.setColumns(10);
		}
		{
			editorialTextField = new JTextField();
			editorialTextField.setBounds(152, 179, 82, 20);
			contentPanel.add(editorialTextField);
			editorialTextField.setColumns(10);
		}
		{
			edicionTextField = new JTextField();
			edicionTextField.setColumns(10);
			edicionTextField.setBounds(313, 179, 86, 20);
			contentPanel.add(edicionTextField);
		}
		{
			publicTextField = new JTextField();
			publicTextField.setColumns(10);
			publicTextField.setBounds(526, 179, 55, 20);
			contentPanel.add(publicTextField);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("REGISTRAR LIBRO");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel_6.setBounds(287, 11, 149, 30);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					Libro libroReg =  new Libro();
					
					
					if(validarISBN(libroReg,contador,librosCreados,isbnTextField.getText()) == null) {
						
					}
					
						
					}
				});
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
	
	private static Libro validarISBN(Libro libro, int[] contador, Vector<Libro> vector, String isbn) {
		int i;
		Libro dato;
		libro.setISBN(isbn);
		i = vector.indexOf(libro);
		dato = i < 0 ? null : vector.get(i);
		return dato;
	}

}
