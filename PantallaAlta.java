import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.animation.Animation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class PantallaAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField isbnTextField;
	private JTextField tituloTextField;
	private JTextField autorTextField;
	private JTextField editorialTextField;
	private JTextField edicionTextField;
	private JTextField anioPublicTextField;
	
	
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
			publiLabel.setBounds(422, 182, 94, 14);
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
			autorTextField.setBounds(468, 118, 135, 20);
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
			anioPublicTextField = new JTextField();
			anioPublicTextField.setColumns(10);
			anioPublicTextField.setBounds(547, 179, 55, 20);
			contentPanel.add(anioPublicTextField);
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
					
					Libro libroNew = libroReg.validarISBN(contador,librosCreados,isbnTextField.getText());
					if( libroNew == null) {	
					
				
						libroReg.setAutor(autorTextField.getText());
						libroReg.setEditorial(editorialTextField.getText());
						libroReg.setTitulo(tituloTextField.getText());
						
						if(leer_entero(anioPublicTextField.getText(),0)>0) {
							libroReg.setAnno_de_publicacion(Integer.parseInt(anioPublicTextField.getText()));//tirar interrupcion
							
							if(leer_entero(edicionTextField.getText(),1)>0){
								libroReg.setEdicion(Integer.parseInt(edicionTextField.getText()));//tirar interrupcion
								librosCreados.add(libroReg);
								dispose();
							}
							
						}
				
					}else
					{
						JOptionPane.showMessageDialog(null,
												"ISBN: " + libroReg.getISBN() +"\n"+
												"Título: " + libroReg.getTitulo() +"\n"+
												"Autor: " + libroReg.getAutor() +"\n"+
												"Edición: " + libroReg.getEdicion() +"\n"+
												"Editorial: " + libroReg.getEditorial() +"\n"+
												"Año de publicación: " + libroReg.getAnno_de_publicacion()+"\n"
								,"El libro que quiere registrar ya existe",
								JOptionPane.ERROR_MESSAGE);	
					}
					
				
					}});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	

	public static int leer_entero(String mensaje,int identificacor) {
		try {
			return Integer.parseInt(mensaje);
		} catch (NumberFormatException e) {
			if( identificacor == 0) {
				JOptionPane.showMessageDialog(null,
						"El año de publicación debe ser un número entero positivo","Error al Registar",JOptionPane.ERROR_MESSAGE);
			}else
			{
				JOptionPane.showMessageDialog(null,
						"El numero de edicion debe ser un número entero positivo","Error al Registar",JOptionPane.ERROR_MESSAGE);
			}
		
			return -1;
		}
	}
	
}


