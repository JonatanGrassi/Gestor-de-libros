package Codigo;

//Guarda este archivo con el nombre Main.java
//package ....libros;

import java.io.*;
import java.util.*;

public class Main {

	public static Scanner teclado = new Scanner(System.in);
	public static PrintStream out = System.out;
	public static String ruta = "libros.tsv";
	public static String rutaUsers = "Users.txt";
	public static ManejadorArchivos Archivo = new ManejadorArchivos(ruta);
	public static ManejadorArchivos ArchivoUsers = new ManejadorArchivos(rutaUsers);

	public static void main(String[] args) {
		Vector<Libro> vector = new Vector<>();
		ArrayList<User> users = new ArrayList<>();
		int[] contador = { 0 };
		int opcion;
		//Libro libro = new Libro(), dato = null;
		vector = Archivo.leerArchivoLibros();
		users = ArchivoUsers.leerUsers();
		validarUsuario(users);
		do {
			Libro libro = new Libro(), dato = null;
			opcion = ingresar_opcion(vector);
			if (opcion < 5) {
				dato = validarISBN(libro, contador, vector);
			}
			evaluarRegistro(libro, opcion, dato, contador, vector);
		} while (opcion != 7);
		Archivo.escribirEnArchivo(vector);
	}

	private static void validarUsuario(ArrayList<User> users) {
		boolean coicidencia = false;
		do {
			User usuario = new User(leer_cadena("Ingrese nombre de usuario"), leer_cadena("Ingrese contraseña"));
			for (User user : users)
				if (user.equals(usuario))
					coicidencia = true;
		} while (!coicidencia);
	}

	private static void evaluarRegistro(Libro libro, int opcion, Libro dato, int[] contador, Vector<Libro> vector) {
		if (opcion == 1 && dato != null)
			out.println("El registro ya existe.");
		else if (opcion >= 2 && opcion <= 4 && dato == null)
			out.println("\nRegistro no encontrado.");
		else
			funcionalidadesSubMenu(opcion, libro, dato, contador, vector);
		if (opcion < 7 && opcion >= 1)
			pausar("");
	}

	private static void funcionalidadesSubMenu(int opcion, Libro libro, Libro dato, int[] contador,
			Vector<Libro> vector) {
		switch (opcion) {
		case 1:
			libro.setTitulo(leer_cadena("Ingrese el titulo"));
			libro.setAutor(leer_cadena("Ingrese el autor"));
			libro.setEditorial(leer_cadena("Ingrese el editorial"));
			libro.setEdicion(leer_entero("Ingrese el edicion"));
			libro.setAnno_de_publicacion(leer_entero("Ingrese el anno de publicacion"));
			vector.add(libro);
			libro = new Libro();
			out.println("\nRegistro agregado correctamente.");
			break;
		case 3:
			modificarLibro(dato);
			break;
		case 4:
			vector.remove(dato);
			out.println("Registro borrado correctamente.");
			break;
		case 5:
			Collections.sort(vector);
			out.println("Registros ordenados correctamente.");
			break;
		case 6:
			listar(contador, vector);
			break;
		}

	}

	private static void listar(int[] contador, Vector<Libro> vector) {
		int i;
		contador[0] = 0;
		for (i = 0; i < vector.size(); i++)
			Libro.imprimirPorPantalla(out, vector.get(i), contador);
		out.println("Total de registros: " + contador[0] + ".");
	}

	private static void modificarLibro(Libro dato) {
		int subopcion;
		out.println("Menu de modificacion de campos");
		out.println("1.- titulo");
		out.println("2.- autor");
		out.println("3.- editorial");
		out.println("4.- edicion");
		out.println("5.- anno de publicacion");
		do {
			subopcion = leer_entero("Seleccione un n\u00FAmero de campo a modificar");
			if (subopcion < 1 || subopcion > 5)
				out.println("Opcion no valida.");
		} while (subopcion < 1 || subopcion > 5);
		switch (subopcion) {
		case 1:
			dato.setTitulo(leer_cadena("Ingrese el nuevo titulo"));
			break;
		case 2:
			dato.setAutor(leer_cadena("Ingrese el nuevo autor"));
			break;
		case 3:
			dato.setEditorial(leer_cadena("Ingrese el nuevo editorial"));
			break;
		case 4:
			dato.setEdicion(leer_entero("Ingrese el nuevo edicion"));
			break;
		case 5:
			dato.setAnno_de_publicacion(leer_entero("Ingrese el nuevo anno de publicacion"));
			break;
		}
		out.println("\nRegistro actualizado correctamente.");

	}

	private static Libro validarISBN(Libro libro, int[] contador, Vector<Libro> vector) {
		int i;
		Libro dato;
		libro.setISBN(leer_cadena("Ingrese el ISBN del libro"));
		i = vector.indexOf(libro);
		dato = i < 0 ? null : vector.get(i);
		if (dato != null) {
			out.println();
			Libro.imprimirPorPantalla(out, dato, contador);
		}
		return dato;

	}

	
	
	public static void pausar(String mensage) {
		out.print(mensage + "\nPresione <ENTER> para continuar . . . ");
		teclado.nextLine();
		out.println();
	}

	public static String leer_cadena(String mensaje) {
		out.print(mensaje + ": ");
		return teclado.nextLine();
	}

	public static int leer_entero(String mensaje) {
		try {
			return Integer.parseInt(leer_cadena(mensaje));
		} catch (NumberFormatException e) {
			out.println("numero incorrecto.");
			return leer_entero(mensaje);
		}
	}

	public static int ingresar_opcion(Vector<Libro> vector) {
		int opcion;
		System.out.println("MENU");
		System.out.println("1.- Altas");
		System.out.println("2.- Consultas");
		System.out.println("3.- Actualizaciones");
		System.out.println("4.- Bajas");
		System.out.println("5.- Ordenar registros");
		System.out.println("6.- Listar registros");
		System.out.println("7.- Salir");
		do {
			opcion = leer_entero("Seleccione una opcion");
			if (opcion < 1 || opcion > 7)
				System.out.println("Opcion no valida.");
		} while (opcion < 1 || opcion > 7);
		System.out.println("");
		if (vector.isEmpty() && opcion != 1 && opcion != 7) {
			pausar("No hay registros.\n");
		}
		return opcion;
	}

	public static void modificar_libro(Libro dato) {
		int subopcion;
		System.out.println("Men\u00FA de modificaci\u00F3n de campos");
		System.out.println("1.- titulo");
		System.out.println("2.- autor");
		System.out.println("3.- editorial");
		System.out.println("4.- edicion");
		System.out.println("5.- anno de publicacion");
		do {
			subopcion = leer_entero("Seleccione un n\u00FAmero de campo a modificar");
			if (subopcion < 1 || subopcion > 5)
				System.out.println("Opci\u00F3n no v\u00E1lida.");
		} while (subopcion < 1 || subopcion > 5);
		switch (subopcion) {
		case 1:
			dato.setTitulo(leer_cadena("Ingrese el nuevo titulo"));
			break;
		case 2:
			dato.setAutor(leer_cadena("Ingrese el nuevo autor"));
			break;
		case 3:
			dato.setEditorial(leer_cadena("Ingrese el nuevo editorial"));
			break;
		case 4:
			dato.setEdicion(leer_entero("Ingrese el nuevo edicion"));
			break;
		case 5:
			dato.setAnno_de_publicacion(leer_entero("Ingrese el nuevo anno de publicacion"));
			break;
		}
		System.out.println("\nRegistro actualizado correctamente.");
	}

}