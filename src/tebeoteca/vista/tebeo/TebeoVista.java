package tebeoteca.vista.tebeo;

import java.util.Scanner;

import tebeoteca.controlador.validador.ValidadorEntradas;
import tebeoteca.modelo.dto.TebeoDTO;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class TebeoVista implements ValidadorEntradas{

	private TebeoDTO tebeoDTO;
	Scanner sc = new Scanner(System.in);
	
	public TebeoVista() {
		//this.personajeDTO = new PersonajeDTO();
	}
	
	public String pedirIsbn() {
		System.out.println("Introduce un ISBN para el tebeo:");
		String isbn = pedirOpcionNumerica().toString();
		return isbn;
	}
	
	public String pedirTitulo() {
		System.out.println("Introduce un titulo para el tebeo:");
		String titulo = sc.nextLine();
		return titulo;
	}
	
	public Integer pedirNumero() {
		System.out.println("Introduce el numero del tebeo:");
		Integer num = pedirOpcionNumerica();
		return num;
	}
	
	public String pedirColeccion() {
		System.out.println("Introduce una coleccion para el tebeo:");
		String coleccion = sc.nextLine();
		return coleccion;
	}
	
	public void mostrarSuceso(String suceso) {
		System.out.println(suceso);
	}
	
	public void sinCoincidencias() {
		System.out.println("No se han encontrado coincidencias");
	}
	
	public void pedirOpcion() {
		System.out.print("Introduce una opcion: ");
	}
	
	public String pedirISBNModif() {
		System.out.println("Introduce el ISBN del tebeo que quieres modificar:");
		String isbn = pedirOpcionNumerica().toString();
		return isbn;
	}
	
	public void mostrarTebeo(TebeoDTO tebeo) {
		System.out.println("ISBN: "+tebeo.getIsbn());
		System.out.println("Titulo: "+tebeo.getTitulo());
		System.out.println("Numero: "+tebeo.getNumero());
		System.out.println("Coleccion: "+tebeo.getColeccion());

	}
	
	public void mostrarConcretaIsbn() {
		System.out.println("Elige un solo tebeo");
	}
	
	public String pedirRepetirModifica() {
		System.out.println("Deseas modificar otro atributo? (S/N)");
		sc.nextLine();
		return sc.nextLine().toLowerCase();
	}
}
