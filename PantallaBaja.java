import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class PantallaBaja extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField libroElimTextFileld;

	
	public PantallaBaja(Vector<Libro> librosCreados,int[] contador) {
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
						Libro libroElim =  new Libro();
						
						Libro libroNew = libroElim.validarISBN(contador,librosCreados,libroElimTextFileld.getText());
						if( libroNew == null) {	
						
							JOptionPane.showMessageDialog(null,
									"El libro que quiere eliminar no existe",
									"ERROR al eliminar",
									JOptionPane.ERROR_MESSAGE);	
					
						}else
						{
							librosCreados.remove(libroNew);
							JOptionPane.showMessageDialog(null,
									"El libro ha sido eliminado con exito",
									"EXITO",
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
