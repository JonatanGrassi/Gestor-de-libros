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
import java.awt.event.ActionEvent;

public class PantallaConsultar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField consultarTexFiled;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public PantallaConsultar(Vector<Libro> librosCreados,int[] contador) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("ISBN");
			contentPanel.add(lblNewLabel);
		}
		{
			consultarTexFiled = new JTextField();
			contentPanel.add(consultarTexFiled);
			consultarTexFiled.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Libro libroConsulta =  new Libro();
						
						 libroConsulta = libroConsulta.validarISBN(contador,librosCreados,consultarTexFiled.getText());
						 
						if( libroConsulta == null) {	
						
							JOptionPane.showMessageDialog(null,
									"El libro que quiere consultar no existe",
									"ERROR al Consultar",
									JOptionPane.ERROR_MESSAGE);	
					
						}else
						{
						
							JOptionPane.showMessageDialog(null,
									"ISBN: " + libroConsulta.getISBN() +"\n"+
									"Título: " + libroConsulta.getTitulo() +"\n"+
									"Autor: " + libroConsulta.getAutor() +"\n"+
									"Edición: " + libroConsulta.getEdicion() +"\n"+
									"Editorial: " + libroConsulta.getEditorial() +"\n"+
									"Año de publicación: " + libroConsulta.getAnno_de_publicacion()+"\n"
									,"Información Libro",
									JOptionPane.INFORMATION_MESSAGE);	
							dispose();
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

}
