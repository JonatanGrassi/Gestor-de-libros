package Codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.Vector;

import com.sun.xml.internal.fastinfoset.Decoder;

public class ManejadorArchivos {
	
	private java.util.Base64.Encoder encoder = Base64.getEncoder();
	private java.util.Base64.Decoder decoder = Base64.getUrlDecoder();
	private String ruta;
	Vector<Libro> vector = new Vector<Libro>();

	public ManejadorArchivos(String nombreArch) {
		this.ruta = nombreArch;
	}

	public Vector<Libro> leerArchivoLibros() {
		Scanner entrada = null;
		Vector<Libro> vector = null;
		try {
			entrada = new Scanner(new FileReader(ruta));
			vector = new Vector<Libro>();
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
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		entrada.close();
		return vector;
	}

	public ArrayList<User> leerUsers() {
		Scanner entrada = null;
		ArrayList<User> usuarios = null; 
		try {
			entrada = new Scanner(new FileReader(ruta));
			usuarios = new ArrayList<>();
			String[] campos;
			while (entrada.hasNextLine()) {
				campos = entrada.nextLine().split("\t");
				byte[] nombreDecode = decoder.decode(campos[0]);
				byte[] passDecode = decoder.decode(campos[1]);
				User usuario = new User(new String(nombreDecode),new String(passDecode));
				usuarios.add(usuario);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		entrada.close();
		return usuarios;
	}

	public void escribirEnArchivo(Vector<Libro> vecLibros) {
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
	
	public void registrarUsuarios(String nombre, String pssw) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			String nombreCifrado = encoder.encodeToString(nombre.getBytes());
			String psswCifrado = encoder.encodeToString(pssw.getBytes());
			fichero = new FileWriter(ruta,true);
			pw = new PrintWriter(fichero);			
			pw.print(nombreCifrado + "\t" + psswCifrado + "\n");
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
