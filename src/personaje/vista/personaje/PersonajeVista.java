package personaje.vista.personaje;

import java.util.Scanner;

import tebeoteca.modelo.dto.PersonajeDTO;

/**
 * U09A01-EjemploPersonaje
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class PersonajeVista {

	private PersonajeDTO personajeDTO;
	Scanner sc = new Scanner(System.in);
	
	public PersonajeVista() {
		//this.personajeDTO = new PersonajeDTO();
	}
	
	public String pedirNombre() {
		System.out.println("Introduce un nombre para el personaje:");
		String nombre = sc.nextLine();
		return nombre;
	}
	
	public Integer pedirVida() {
		System.out.println("Introduce su vida:");
		Integer vida = sc.nextInt();
		return vida;
	}
	
	public Integer pedirNivel() {
		System.out.println("Introduce el nivel:");
		Integer dato = sc.nextInt();
		return dato;
	}
	
	public Integer pedirFuerza() {
		System.out.println("Introduce su fuerza:");
		Integer fuerza = sc.nextInt();
		return fuerza;
	}
	
	public Integer pedirDestreza() {
		System.out.println("Introduce su destreza:");
		Integer destreza = sc.nextInt();
		return destreza;
	}
	
	public Integer pedirConstitucion() {
		System.out.println("Introduce su constitucion:");
		Integer cons = sc.nextInt();
		return cons;
	}
	
	public Integer pedirInteligencia() {
		System.out.println("Introduce su inteligencia:");
		Integer intel = sc.nextInt();
		return intel;
	}
	
	public Double pedirOro() {
		System.out.println("Introduce su oro:");
		Double oro = sc.nextDouble();
		return oro;
	}
	
	public String pedirTipo() {
		System.out.println("Introduce el tipo de personaje (Mago/Picaro/Guerrero):");
		sc.nextLine();
		String tipo = sc.nextLine();
		return tipo;
	}
	
	public Double pedirSombras() {
		System.out.println("Habilidad para esconderse en las sombras:");
		Double sombra = sc.nextDouble();
		return sombra;
	}
	
	public Double pedirFurtivo() {
		System.out.println("Habilidad para ataque furtivo:");
		Double furtivo = sc.nextDouble();
		return furtivo;
	}
	
	public Double pedirCerraduras() {
		System.out.println("Habilidad para abrir cerraduras:");
		Double cerrad = sc.nextDouble();
		return cerrad;
	}
	
	public Double pedirBerserker() {
		System.out.println("Habilidad para convertirse en Berserker:");
		Double bers = sc.nextDouble();
		return bers;
	}
	
	public Double pedirArmasPesadas() {
		System.out.println("Habilidad para usar armas pesadas:");
		Double pesadas = sc.nextDouble();
		return pesadas;
	}
	
	public Double pedirArmasLigeras() {
		System.out.println("Habilidad para usar armas ligeras:");
		Double ligeras = sc.nextDouble();
		return ligeras;
	}
	
	public Double pedirPiromancia() {
		System.out.println("Habilidad para usar piromancia:");
		Double dato = sc.nextDouble();
		return dato;
	}

	public Double pedirNigromancia() {
		System.out.println("Habilidad para usar nigromancia:");
		Double dato = sc.nextDouble();
		return dato;
	}
	
	public Double pedirIlusion() {
		System.out.println("Habilidad para usar ilusion:");
		Double dato = sc.nextDouble();
		return dato;
	}
	
	public Double pedirTransmutacion() {
		System.out.println("Habilidad para usar transmutacion:");
		Double dato = sc.nextDouble();
		return dato;
	}
	
	public void mostrarSuceso(String suceso) {
		System.out.println(suceso);
	}
	
	public String pedirNombreModif() {
		System.out.println("Introduce el nombre del personaje que quieres modificar:");
		String nombre = sc.nextLine();
		return nombre;
	}
	
	public void mostrarPersonaje(PersonajeDTO p) {
		System.out.println("Nombre: "+p.getNombre());
		System.out.println("Nivel: "+p.getNivel());
		System.out.println("Vida: "+p.getVida());
		System.out.println("Fuerza: "+p.getFuerza());
		System.out.println("Destreza: "+p.getDestreza());
		System.out.println("Constitucion: "+p.getConstitucion());
		System.out.println("Inteligencia: "+p.getInteligencia());
		System.out.println("Oro: "+p.getOro());
		System.out.println("Tipo: "+p.getTipo());
	}
	
	public void mostrarPicaro(PersonajeDTO p) {
		System.out.println("Esc. Sombras: "+p.getPicaroDTO().getEscondeSombras());
		System.out.println("Ataque Furtivo: "+p.getPicaroDTO().getAtaqueFurtivo());
		System.out.println("Abrir Cerraduras: "+p.getPicaroDTO().getAbrirCerraduras());
		System.out.println("");
	}
	
	public void mostrarGuerrero(PersonajeDTO p) {
		System.out.println("Berserker: "+p.getGuerreroDTO().getBerserker());
		System.out.println("Armas Pesadas: "+p.getGuerreroDTO().getArmasPesadas());
		System.out.println("Armas Ligeras: "+p.getGuerreroDTO().getArmasLigeras());
		System.out.println("");
	}
	
	public void mostrarMago(PersonajeDTO p) {
		System.out.println("Bonif. Piromancia: "+p.getMagoDTO().getPiromancia());
		System.out.println("Bonif. Nigromancia: "+p.getMagoDTO().getNigromancia());
		System.out.println("Bonif. Transmutación: "+p.getMagoDTO().getTransmutacion());
		System.out.println("Bonif. Ilusión: "+p.getMagoDTO().getIlusion());
		System.out.println("");
	}
	
	public void mostrarConcretaNombre() {
		System.out.println("Elige un solo personaje");
	}
	
	public String pedirRepetirModifica() {
		System.out.println("Deseas modificar otro atributo? (S/N)");
		sc.nextLine();
		return sc.nextLine().toLowerCase();
	}
}
