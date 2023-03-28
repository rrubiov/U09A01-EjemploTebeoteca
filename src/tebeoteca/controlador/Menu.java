package tebeoteca.controlador;

import personaje.controlador.personaje.Tebeo;
import tebeoteca.controlador.validador.ValidadorEntradas;
import tebeoteca.vista.menu.MenusVista;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class Menu implements ValidadorEntradas{

	public Menu() {
		
	}
	
	public void menuPrincipal() {
		MenusVista menusV = new MenusVista();
		int opcion;
		do {
			menusV.menuPrincipal();
			do {
				menusV.pedirOpcion();
				opcion = pedirOpcionNumerica();
			}while(!validarSelectorMenuPrincipal(opcion));
			seleccionar(opcion);
		}while(opcion!=3);
	}

	public void seleccionar(int opcion) {
		
		switch(opcion) {
			case 1:
				Tebeo tebeo = new Tebeo();
				tebeo.gestionTebeo();
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
