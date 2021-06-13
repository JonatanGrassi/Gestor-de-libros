package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Codigo.Libro;
import Codigo.ManejadorArchivos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class PantallaBaja extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField libroElimTextFileld;
	private static ManejadorArchivos archivo = new ManejadorArchivos("libros.tsv");

	public PantallaBaja(Vector<Libro> librosCreados, int[] contador) {
		setBounds(100, 100, 450, 137);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("ISBN ");
			contentPanel.add(lblNewLabel);
		}
		{
			libroElimTextFileld = new JTextField();
			contentPanel.add(libroElimTextFileld);
			libroElimTextFileld.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Eliminar Libro");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Libro libroElim = new Libro();
						String isbn = libroElimTextFileld.getText();
						Libro libroNew = libroElim.validarISBN(contador, librosCreados, isbn);
						if (Pattern.matches("[0-9]{3}-[0-9]{1,5}-[0-9]{1,7}-[0-9]{1,6}-[0-9]{1,3}", isbn)
								&& isbn.length() <= 17) {
							if (libroNew == null) {

								JOptionPane.showMessageDialog(null, "El libro que quiere eliminar no existe",
										"ERROR al eliminar", JOptionPane.ERROR_MESSAGE);

							} else {
								int opcion = JOptionPane.showConfirmDialog(null,
										"Esta seguro que desea eliminar el libro " + libroElimTextFileld.getText(),
										"Eliminar", JOptionPane.YES_NO_OPTION);
								if (opcion == JOptionPane.YES_OPTION) {
									librosCreados.remove(libroNew);
									archivo.escribirEnArchivo(librosCreados);
									JOptionPane.showMessageDialog(null, "El libro ha sido eliminado con exito", "EXITO",
											JOptionPane.INFORMATION_MESSAGE);
								}
								dispose();
							}
						} else {
							// Error ISBN De libro
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
	}

}
