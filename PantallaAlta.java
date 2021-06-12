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
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Color;

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
	public PantallaAlta(Vector<Libro> librosCreados, int[] contador) {
		setBounds(100, 100, 656, 312);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel isbnLabel = new JLabel("ISBN:");
			isbnLabel.setBounds(15, 73, 46, 14);
			contentPanel.add(isbnLabel);
		}
		{
			JLabel publiLabel = new JLabel("A\u00D1O DE PUBLICACION:");
			publiLabel.setBounds(313, 182, 187, 14);
			contentPanel.add(publiLabel);
		}
		{
			JLabel tituloLabel = new JLabel("TITULO:");
			tituloLabel.setBounds(15, 121, 94, 14);
			contentPanel.add(tituloLabel);
		}
		{
			JLabel autorLabel = new JLabel("AUTOR:");
			autorLabel.setBounds(313, 73, 94, 14);
			contentPanel.add(autorLabel);
		}
		{
			JLabel editorialLabel = new JLabel("EDITORIAL:");
			editorialLabel.setBounds(15, 182, 94, 14);
			contentPanel.add(editorialLabel);
		}
		{
			JLabel edicionLabel = new JLabel("EDICION:");
			edicionLabel.setBounds(313, 121, 86, 14);
			contentPanel.add(edicionLabel);
		}
		{
			isbnTextField = new JTextField();
			isbnTextField.setBounds(107, 70, 146, 20);
			contentPanel.add(isbnTextField);
			isbnTextField.setColumns(10);
		}
		{
			tituloTextField = new JTextField();
			tituloTextField.setBounds(106, 118, 146, 20);
			contentPanel.add(tituloTextField);
			tituloTextField.setColumns(10);
		}
		{
			autorTextField = new JTextField();
			autorTextField.setBounds(418, 70, 146, 20);
			contentPanel.add(autorTextField);
			autorTextField.setColumns(10);
		}
		{
			editorialTextField = new JTextField();
			editorialTextField.setBounds(106, 179, 146, 20);
			contentPanel.add(editorialTextField);
			editorialTextField.setColumns(10);
		}
		{
			edicionTextField = new JTextField();
			edicionTextField.setColumns(10);
			edicionTextField.setBounds(418, 118, 146, 20);
			contentPanel.add(edicionTextField);
		}
		{
			anioPublicTextField = new JTextField();
			anioPublicTextField.setColumns(10);
			anioPublicTextField.setBounds(492, 179, 72, 20);
			contentPanel.add(anioPublicTextField);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(SystemColor.activeCaption, 4, true));
			panel.setBounds(118, 16, 382, 36);
			contentPanel.add(panel);
			{
				JLabel lblRegistrarLibro = new JLabel("REGISTRO DE LIBRO");
				lblRegistrarLibro.setFont(new Font("Perpetua Titling MT", Font.BOLD, 20));
				panel.add(lblRegistrarLibro);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
			panel.setBounds(0, 59, 634, 158);
			contentPanel.add(panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						Libro libroReg = new Libro();
						String isbn = isbnTextField.getText();
						
						//validacion de ISBN
						if (Pattern.matches("[0-9]{3}-[0-9]{1,5}-[0-9]{1,7}-[0-9]{1,6}-[0-9]{1,3}", isbn)
								&& isbn.length() <= 17) 
						{
							Libro libroNew = libroReg.validarISBN(contador, librosCreados, isbn);
							if (libroNew == null) 
							{
								//obtengo los valores de los campos a validar
								String autor = autorTextField.getText();
								String Editorial = editorialTextField.getText();
								String titulo = tituloTextField.getText();

								if (!autor.equals("") && !Editorial.equals("") && !titulo.equals("")) 
								{
									//validacion de longitud de autor
									if (autor.length() <= 300) 
									{

										libroReg.setAutor(autor);
										libroReg.setEditorial(Editorial);
										libroReg.setTitulo(titulo);
										validacionesAnioyEdicion(libroReg, librosCreados);

									} 
									else 
									{
										//Error de longitud de nombre autor
										JOptionPane.showMessageDialog(null,
												"El autor debe contener entre 1 y 300 caracateres",
												"Formato incorrecto de autor", JOptionPane.ERROR_MESSAGE);
									}

								} 
								else 
								{	
									//Error campos vacios del registro
									JOptionPane.showMessageDialog(null, "No puede haber campos vacios en el registro",
											"Debe llenar todos los campos", JOptionPane.ERROR_MESSAGE);
								}

							} 
							else 
							{	
								//Error Registro de libro ya existente
								JOptionPane.showMessageDialog(null,
										"ISBN: " + libroNew.getISBN() + "\n" + "Título: " + libroNew.getTitulo() + "\n"
												+ "Autor: " + libroNew.getAutor() + "\n" + "Edición: "
												+ libroNew.getEdicion() + "\n" + "Editorial: " + libroNew.getEditorial()
												+ "\n" + "Año de publicación: " + libroNew.getAnno_de_publicacion()
												+ "\n",
										"El libro que quiere registrar ya existe", JOptionPane.ERROR_MESSAGE);
							}
						} 
						else 
						{	
							//Error ISBN De libro
							JOptionPane.showMessageDialog(null,
									"ISBN: " + isbn + " incorrecto \n El formato correcto es: "
											+ "xxx[3]-xx[1a5]-xxxxx[1a7]-xx[1a6]-x[1a3]",
									"DEBE INGRESAR UN ISBN", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void validacionesAnioyEdicion(Libro libroReg, Vector<Libro> librosCreados) {
		if (leer_entero(anioPublicTextField.getText(), 0) > 0) {
			libroReg.setAnno_de_publicacion(Integer.parseInt(anioPublicTextField.getText()));// tirar
																								// interrupcion

			if (leer_entero(edicionTextField.getText(), 1) > 0) {
				libroReg.setEdicion(Integer.parseInt(edicionTextField.getText()));// tirar
																					// interrupcion
				librosCreados.add(libroReg);
				dispose();
			}

		}
	}

	public static int leer_entero(String mensaje, int identificacor) {
		try {//agregar validacion de 4 cifras
			 int num =Integer.parseInt(mensaje);
			
			if(identificacor == 0 && mensaje.length() != 4 && num >= 1900) {
				JOptionPane.showMessageDialog(null, "El año de publicación debe ser un número entero positivo de 4 cifras mayor o igual a 1900",//mayor a 1900
						"Error al Registar", JOptionPane.ERROR_MESSAGE);
			}
			return num;
			
		} catch (NumberFormatException e) {
			if (identificacor == 0) {
				JOptionPane.showMessageDialog(null, "El año de publicación debe ser un número entero positivo de 4 cifras",
						"Error al Registar", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "El numero de edicion debe ser un número entero positivo",
						"Error al Registar", JOptionPane.ERROR_MESSAGE);
			}

			return -1;
		}
	}

}
