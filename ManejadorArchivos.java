import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ManejadorArchivos {

	private String ruta;
	Vector<Libro> vector = new Vector<Libro>();

	public ManejadorArchivos(String nombreArch) {
		this.ruta = nombreArch;
	}

	public Vector<Libro> leerArchivoLibros() throws FileNotFoundException {

		Scanner entrada = new Scanner(new FileReader(ruta));
		Vector<Libro> vector = new Vector<Libro>();
		String[] campos;
		while (entrada.hasNextLine()) {
			campos = entrada.nextLine().split("\t");
			Libro libro = new Libro();
			libro.setISBN(campos[0]);
			libro.setTitulo(campos[1]);
			libro.setAutor(campos[2]);
			libro.setEditorial(campos[3]);
			libro.setEdicion(Integer.parseInt(campos[4]));
			libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
			vector.add(libro);
		}
		entrada.close();
		return vector;
	}
	
	public ArrayList<User> leerUsers() throws FileNotFoundException {

		Scanner entrada = new Scanner(new FileReader(ruta));
		ArrayList<User> usuarios = new ArrayList<>();
		String[] campos;
		while (entrada.hasNextLine()) {
			campos = entrada.nextLine().split("\t");
			User usuario = new User(campos[0],campos[1]);
			usuarios.add(usuario);
		}
		entrada.close();
		return usuarios;
	}

	public void escribirEnArchivo(Vector<Libro> vecLibros,String ruta) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(ruta);
			pw = new PrintWriter(fichero);
			for (int i = 0; i < vecLibros.size(); i++) {
				pw.print(vecLibros.get(i).getISBN() + "\t");
				pw.print(vecLibros.get(i).getTitulo() + "\t");
				pw.print(vecLibros.get(i).getAutor() + "\t");
				pw.print(vecLibros.get(i).getEditorial() + "\t");
				pw.print(vecLibros.get(i).getEdicion() + "\t");
				pw.print(vecLibros.get(i).getAnno_de_publicacion() + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
