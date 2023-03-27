package personaje.controlador;

import personaje.controlador.personaje.Personaje;
import personaje.controlador.validador.ValidadorEntradas;
import personaje.vista.menu.MenusVista;

/**
 * U09A01-EjemploPersonaje
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class Menu {

	ValidadorEntradas validaEntrada = new ValidadorEntradas();

	public Menu() {
		
	}
	
	public void menuPrincipal() {
		MenusVista menusV = new MenusVista();
		int opcion;
		do {
			menusV.menuPrincipal();
			do {
				opcion = menusV.pedirOpcion();
			}while(!validaEntrada.validarSelectorMenuPrincipal(opcion));
			seleccionar(opcion);
		}while(opcion!=3);
	}

	public void seleccionar(int opcion) {
		
		switch(opcion) {
			case 1:
				Personaje personaje = new Personaje();
				personaje.gestionPersonaje();
				break;
			case 2:
				System.out.println("WIP");
				break;
			case 3:
				return;
			default:
				return;
		}
	}
}
