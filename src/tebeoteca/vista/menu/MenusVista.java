package tebeoteca.vista.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * U09A01-EjemploTebeo
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class MenusVista {

	Scanner sc = new Scanner(System.in);
	
	public MenusVista() {
		
	}
	
	public void menuPrincipal() {
		
		System.out.println("\nMENU PRINCIPAL\n");
		System.out.println("1.- Gestionar Tebeos");
		System.out.println("2.- Gestionar Autores");
		System.out.println("\n3.- SALIR\n");

	}
	
	/*
	 * MENUS DE TEBEO
	 */
	
	public void menuTebeo() {
		
		System.out.println("\nMENU TEBEOS\n");
		System.out.println("1.- Crear Tebeo");
		System.out.println("2.- Buscar Tebeo");
		System.out.println("3.- Modificar Tebeo");
		System.out.println("4.- Eliminar Tebeo");
		System.out.println("\n5.- SALIR\n");
	}
	
	public void menuEditarTebeo() {
		System.out.println("\nEDITAR TEBEO\n");
		System.out.println("Que atributo deseas modificar?");
		menuAtributosEditarTebeo();
	}
	
	public void menuBuscarFiltroTebeo() {
		System.out.println("\nBUSCAR TEBEO\n");
		System.out.println("Por que atributo deseas filtrar?");
		menuAtributosBuscarTebeo();
	}
	
	public void menuAtributosBuscarTebeo() {
		
		System.out.println("1.- ISBN");
		System.out.println("2.- Titulo");
		System.out.println("3.- Numero");
		System.out.println("4.- Coleccion");
		System.out.println("5.- Autor");

	}
	
	public void menuAtributosEditarTebeo() {
		
		System.out.println("1.- Titulo");
		System.out.println("2.- Numero");
		System.out.println("3.- Coleccion");
		System.out.println("4.- Autor");

	}
	
	/*
	 * MENUS DE AUTOR
	 */
	
	public void menuAutor() {
		
		System.out.println("\nMENU AUTORES\n");
		System.out.println("1.- Crear Autor");
		System.out.println("2.- Buscar Autor");
		System.out.println("3.- Modificar Autor");
		System.out.println("4.- Eliminar Autor");
		System.out.println("\n5.- SALIR\n");
	}
	
	public void menuEditarAutor() {
		System.out.println("\nEDITAR TEBEO\n");
		System.out.println("Que atributo deseas modificar?");
		menuAtributosAutor();
	}
	
	public void menuBuscarFiltroAutor() {
		System.out.println("\nBUSCAR AUTOR\n");
		System.out.println("Por que atributo deseas filtrar?");
		menuAtributosAutor();
	}
	
	public void menuAtributosAutor() {
		System.out.println("1.- Nombre");
		System.out.println("2.- Apellido");
		System.out.println("3.- Nacionalidad");
	}
	
	public void pedirOpcion() {
		System.out.print("Introduce una opcion: ");
	}
	
}
