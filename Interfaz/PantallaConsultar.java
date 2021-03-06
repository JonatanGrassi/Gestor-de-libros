package Interfaz;

import Codigo.Libro;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;

public class PantallaConsultar extends JDialog {
	private JTextField consultarTexFiled;
	private JLabel labelAņoPubl;
	private JLabel labelEditorial;
	private JLabel labelEdicion;
	private JLabel labelTitulo;
	private JLabel labelAutor;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public PantallaConsultar(Vector<Libro> librosCreados, int[] contador) {
		setBounds(100, 100, 700, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 205, 428, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Libro libroConsulta = new Libro();
						String isbn = consultarTexFiled.getText();
						libroConsulta = libroConsulta.validarISBN(contador, librosCreados, consultarTexFiled.getText());
						if (Pattern.matches("[0-9]{3}-[0-9]{1,5}-[0-9]{1,7}-[0-9]{1,6}-[0-9]{1,3}", isbn)
								&& isbn.length() <= 17) {
							if (libroConsulta == null) {

								JOptionPane.showMessageDialog(null, "El libro que quiere consultar no existe",
										"ERROR al Consultar", JOptionPane.ERROR_MESSAGE);

							} else {
								labelAutor.setText(libroConsulta.getAutor());
								labelEdicion.setText(String.valueOf(libroConsulta.getEdicion()));
								labelEditorial.setText(libroConsulta.getEditorial());
								labelAņoPubl.setText(String.valueOf(libroConsulta.getAnno_de_publicacion()));
								labelTitulo.setText(libroConsulta.getTitulo());
							}
						}
						else
						JOptionPane.showMessageDialog(null,
								"ISBN: " + isbn + " incorrecto \n El formato correcto es: "
										+ "xxx[3]-xx[1a5]-xxxxx[1a7]-xx[1a6]-x[1a3]",
								"DEBE INGRESAR UN ISBN", JOptionPane.ERROR_MESSAGE);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		consultarTexFiled = new JTextField();
		consultarTexFiled.setToolTipText("Ingrese ISBN del libro a consultar");
		consultarTexFiled.setColumns(10);
		consultarTexFiled.setBounds(159, 16, 146, 26);
		getContentPane().add(consultarTexFiled);

		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(119, 19, 35, 20);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("TITULO:");
		lblNewLabel_1.setBounds(25, 80, 90, 20);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("EDICION:");
		lblNewLabel_1_1.setBounds(25, 111, 90, 20);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("EDITORIAL:");
		lblNewLabel_1_1_1.setBounds(25, 141, 90, 20);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("A\u00D1O DE PUBLICACION:");
		lblNewLabel_1_1_1_1.setBounds(25, 169, 178, 20);
		getContentPane().add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("AUTOR:");
		lblNewLabel_1_2.setBounds(25, 53, 90, 20);
		getContentPane().add(lblNewLabel_1_2);

		labelAutor = new JLabel("x-----x");
		labelAutor.setBackground(new Color(127, 255, 212));
		labelAutor.setBounds(212, 53, 414, 20);
		getContentPane().add(labelAutor);

		labelTitulo = new JLabel("x-----x");
		labelTitulo.setBounds(212, 80, 414, 20);
		getContentPane().add(labelTitulo);

		labelEdicion = new JLabel("x-----x");
		labelEdicion.setBounds(212, 111, 414, 20);
		getContentPane().add(labelEdicion);

		labelEditorial = new JLabel("x-----x");
		labelEditorial.setBounds(212, 141, 414, 20);
		getContentPane().add(labelEditorial);

		labelAņoPubl = new JLabel("x-----x");
		labelAņoPubl.setBounds(212, 169, 414, 20);
		getContentPane().add(labelAņoPubl);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(SystemColor.text);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(204, 48, 422, 146);
		getContentPane().add(panel);
	}
}
