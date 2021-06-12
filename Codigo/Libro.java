package Codigo;

import java.io.PrintStream;
import java.util.Vector;

public class Libro implements Comparable<Libro> {

	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int anno_de_publicacion;

	@Override
	public boolean equals(Object libro) {
		return this == libro || (libro instanceof Libro && ISBN.equals(((Libro) libro).ISBN));
	}

	@Override
	public int compareTo(Libro libro) {
		return ISBN.compareTo(libro.ISBN);
	}

	@Override
//	public String toString() {
//		return "ISBN               : " + ISBN + "\n" + "titulo             : " + titulo + "\n" + "autor              : "
//				+ autor + "\n" + "editorial          : " + editorial + "\n" + "edicion            : " + edicion + "\n"
//				+ "anno de publicacion: " + anno_de_publicacion + "\n";
//	}
	
//	public String toString() {
//		return  ISBN + "	" +  titulo + "	" +autor + "	"  + editorial + "	"  + edicion + "	"
//				+anno_de_publicacion + "\n";
//	}
	
	public String toString() {
		return 	String.format("%-20s	%-90s	%-30s	%-20s	%-20d	%-20d\n", ISBN,titulo,autor,editorial,edicion,anno_de_publicacion);
	}


	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	public int getAnno_de_publicacion() {
		return anno_de_publicacion;
	}

	public void setAnno_de_publicacion(int anno_de_publicacion) {
		this.anno_de_publicacion = anno_de_publicacion;
	}
	
	static void imprimirPorPantalla(PrintStream salida,Libro libro,int [] parametros)
	{
		salida.println(libro);
		int[] contador = parametros;
		contador[0]++;
	}
	
	public Libro validarISBN(int[] contador, Vector<Libro> vector, String isbn) {
			int i;
			Libro dato;
			this.ISBN = isbn;
			i = vector.indexOf(this);
			dato = i < 0 ? null : vector.get(i);
			return dato;
		}
	
}
