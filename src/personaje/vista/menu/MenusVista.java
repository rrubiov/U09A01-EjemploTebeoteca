package personaje.vista.menu;

import java.util.Scanner;

/**
 * U09A01-EjemploPersonaje
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
		System.out.println("1.- Gestionar Personajes");
		System.out.println("2.- Gestionar Inventarios");
		System.out.println("\n3.- SALIR\n");

	}
	
	public void menuPersonajes() {
		
		System.out.println("\nMENU PERSONAJES\n");
		System.out.println("1.- Crear Personaje");
		System.out.println("2.- Buscar Personaje");
		System.out.println("3.- Modificar Personaje");
		System.out.println("4.- Eliminar Personaje");
		System.out.println("\n5.- SALIR\n");
	}
	
	public void menuEditarPersonaje() {
		System.out.println("\nEDITAR PERSONAJE\n");
		System.out.println("¿Que atributo deseas modificar?");
		menuAtributosPersonaje();
	}
	
	public void menuBuscarFiltroPersonaje() {
		System.out.println("\nBUSCAR PERSONAJE\n");
		System.out.println("¿Por qué atributo deseas filtrar?");
		menuAtributosPersonaje();
	}
	
	public void menuAtributosPersonaje() {
		
		System.out.println("1.- Nombre");
		System.out.println("2.- Nivel");
		System.out.println("3.- Vida");
		System.out.println("4.- Fuerza");
		System.out.println("5.- Destreza");
		System.out.println("6.- Constitucion");
		System.out.println("7.- Inteligencia");
		System.out.println("8.- Oro");
	}
	
	public void menuAtributosPicaro() {
		System.out.println("9.- Esconderse en las sombras");
		System.out.println("10.- Ataque furtivo");
		System.out.println("11.- Abrir cerraduras");
		System.out.println("\n12.- SALIR");
	}
	
	public void menuAtributosGuerrero() {
		System.out.println("9.- Entrar en Berserker");
		System.out.println("10.- Manejo Armas Pesadas");
		System.out.println("11.- Manejo Armas Ligeras");
		System.out.println("\n12.- SALIR");
	}
	
	public void menuAtributosMago() {
		System.out.println("9.- Bonif. Piromancia");
		System.out.println("10.- Bonif. Nigromancia");
		System.out.println("11.- Bonif. Ilusion");
		System.out.println("12.- Bonif. Transmutacion");
		System.out.println("\n13.- SALIR");
	}
	
	public int pedirOpcion() {
		
		System.out.print("Introduce una opción: ");
		int opcion = sc.nextInt();
		return opcion;
	}
}
