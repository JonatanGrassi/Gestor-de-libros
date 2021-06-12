package Interfaz;
import Codigo.Libro;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class PantallaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField isbnTextField;
	private JTextField edicion;
	private JTextField titulo;
	private JTextField autor;
	private JTextField editorial;
	private JTextField anioPublicacion;
	private Libro libroReg = new Libro();
	private Libro libroNew;

	public PantallaModificacion(Vector<Libro> librosCreados, int[] contador) {
		setBounds(100, 100, 615, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		isbnTextField = new JTextField();
		isbnTextField.setBounds(60, 50, 83, 23);
		contentPanel.add(isbnTextField);
		isbnTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("MODIFICAR LIBRO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(221, 12, 155, 20);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ISBN");
		lblNewLabel_1.setBounds(10, 53, 69, 20);
		contentPanel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				libroNew = libroReg.validarISBN(contador, librosCreados, isbnTextField.getText());
				if (libroNew == null) {
					JOptionPane.showMessageDialog(null, "El registro que quiere actualizar no existe",
							"Error al actualizar", JOptionPane.ERROR_MESSAGE);
				} else {
					autor.setEnabled(true);
					edicion.setEnabled(true);
					anioPublicacion.setEnabled(true);
					titulo.setEnabled(true);
					editorial.setEnabled(true);
					
					autor.setText(libroNew.getAutor());
					edicion.setText(String.valueOf(libroNew.getEdicion()));
					editorial.setText(String.valueOf(libroNew.getEditorial()));
					titulo.setText(String.valueOf(libroNew.getTitulo()));
					anioPublicacion.setText(String.valueOf(libroNew.getAnno_de_publicacion()));
					

				}
			}
		});
		btnNewButton.setBounds(164, 48, 115, 29);
		contentPanel.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(153, 180, 209), 3));
		panel.setBounds(10, 89, 568, 223);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel sss = new JLabel("Autor");
		sss.setBounds(15, 86, 69, 20);
		panel.add(sss);

		JLabel lblNewLabel_2_1 = new JLabel("Editorial");
		lblNewLabel_2_1.setBounds(15, 164, 69, 20);
		panel.add(lblNewLabel_2_1);

		JLabel jlabel5 = new JLabel("Edicion");
		jlabel5.setBounds(294, 83, 69, 20);
		panel.add(jlabel5);

		edicion = new JTextField();
		edicion.setBounds(378, 80, 146, 26);
		panel.add(edicion);
		edicion.setColumns(10);

		JLabel lblNewLabel_2_2 = new JLabel("A\u00F1o de publicacion");
		lblNewLabel_2_2.setBounds(259, 119, 145, 26);
		panel.add(lblNewLabel_2_2);

		titulo = new JTextField();
		titulo.setBounds(331, 164, 193, 26);
		panel.add(titulo);
		titulo.setColumns(10);

		autor = new JTextField();
		autor.setBounds(72, 83, 146, 26);
		panel.add(autor);
		autor.setColumns(10);

		editorial = new JTextField();
		editorial.setColumns(10);
		editorial.setBounds(86, 161, 81, 26);
		panel.add(editorial);

		anioPublicacion = new JTextField();
		anioPublicacion.setColumns(10);
		anioPublicacion.setBounds(419, 116, 105, 26);
		panel.add(anioPublicacion);

		JLabel lblA = new JLabel("Atributos");
		lblA.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblA.setBounds(12, 16, 155, 20);
		panel.add(lblA);

		autor.setEnabled(false);
		edicion.setEnabled(false);
		anioPublicacion.setEnabled(false);
		titulo.setEnabled(false);
		editorial.setEnabled(false);

		JLabel lblNewLabel_2_2_1 = new JLabel("Titulo");
		lblNewLabel_2_2_1.setBounds(257, 167, 59, 20);
		panel.add(lblNewLabel_2_2_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						libroNew.setAutor(autor.getText());
						libroNew.setEditorial(editorial.getText());
						libroNew.setTitulo(titulo.getText());
						
						if(leer_entero(anioPublicacion.getText(),0)>0) {
							libroNew.setAnno_de_publicacion(Integer.parseInt(anioPublicacion.getText()));//tirar interrupcion
							
							if(leer_entero(edicion.getText(),1)>0){
								libroNew.setEdicion(Integer.parseInt(edicion.getText()));//tirar interrupcion
								dispose();
							}
							
						}
					}
				});
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
