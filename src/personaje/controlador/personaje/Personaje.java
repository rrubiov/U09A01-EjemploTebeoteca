package personaje.controlador.personaje;

import java.util.ArrayList;

import personaje.controlador.Constantes;
import personaje.controlador.Menu;
import personaje.controlador.validador.ValidadorEntradas;
import personaje.vista.menu.MenusVista;
import personaje.vista.personaje.PersonajeVista;
import tebeoteca.modelo.dao.TebeoDAO;
import tebeoteca.modelo.dto.PersonajeDTO;

/**
 * U09A01-EjemploPersonaje
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class Personaje {

	PersonajeVista v = new PersonajeVista();
	TebeoDAO pDAO = new TebeoDAO();
	private static final Integer BUSCAR = 0;
	private static final Integer MODIFICAR = 1;

	
	ValidadorEntradas validaEntrada = new ValidadorEntradas();
	
	public Personaje() {
		
	}
	
	public void gestionPersonaje() {
		MenusVista menu = new MenusVista();
		int opcion;
		do {
			menu.menuPersonajes();
			
			do {
				opcion = menu.pedirOpcion();
			}while(!validaEntrada.validarSelectorMenuPersonaje(opcion));
			
			switch(opcion) {
				case 1:
					crearPersonaje();
					break;
				case 2:
					buscarPersonajeFiltro();
					break;
				case 3: 
					modificarPersonaje();
					break;
				case 4:
					eliminarPersonaje();
					break;
				case 5:
					return;
				default:
					return;
			}
		}while(opcion!=5);
		
	}
	
	public void crearPersonaje() {
		PersonajeDTO personajeDTO = new PersonajeDTO();
		String nombre;
		do{
			nombre = v.pedirNombre();	
		}while(!validaEntrada.validarString(nombre));
		personajeDTO.setNombre(nombre);

		Integer vida;
		do{
			vida = v.pedirVida();	
		}while(!validaEntrada.validarPositivo(vida));
		personajeDTO.setVida(vida);
		
		Integer nivel;
		do{
			nivel = v.pedirNivel();	
		}while(!validaEntrada.validarPositivo(nivel));
		personajeDTO.setNivel(nivel);
		
		Integer fuerza;
		do{
			fuerza = v.pedirFuerza();	
		}while(!validaEntrada.validarPositivo(fuerza));
		personajeDTO.setFuerza(fuerza);
		
		Integer destreza;
		do{
			destreza = v.pedirDestreza();	
		}while(!validaEntrada.validarPositivo(destreza));
		personajeDTO.setDestreza(destreza);
		
		Integer cons;
		do{
			cons = v.pedirConstitucion();	
		}while(!validaEntrada.validarPositivo(cons));
		personajeDTO.setConstitucion(cons);
		
		Integer intel;
		do{
			intel = v.pedirInteligencia();	
		}while(!validaEntrada.validarPositivo(intel));
		personajeDTO.setInteligencia(intel);
		
		Double oro;
		do{
			oro = v.pedirOro();	
		}while(!validaEntrada.validarPositivo(oro));
		personajeDTO.setOro(oro);
		
		String tipo;
		do{
			tipo = v.pedirTipo().toLowerCase();	
		}while(!validaEntrada.validarTipo(tipo));
		personajeDTO.setTipo(tipo);
		
		Double dato;
		switch(tipo) {
			case "picaro":
				
				do{
					dato = v.pedirCerraduras();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getPicaroDTO().setAbrirCerraduras(dato);
				do{
					dato = v.pedirFurtivo();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getPicaroDTO().setAtaqueFurtivo(dato);
				do{
					dato = v.pedirSombras();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getPicaroDTO().setEscondeSombras(dato);
				
				break;
				
			case "guerrero":
				do{
					dato = v.pedirBerserker();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getGuerreroDTO().setBerserker(dato);
		
				do{
					dato = v.pedirArmasPesadas();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getGuerreroDTO().setArmasPesadas(dato);
				
				do{
					dato = v.pedirArmasLigeras();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getGuerreroDTO().setArmasLigeras(dato);
				break;
				
			case "mago":
				do{
					dato = v.pedirPiromancia();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getMagoDTO().setPiromancia(dato);
				
				do{
					dato = v.pedirNigromancia();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getMagoDTO().setNigromancia(dato);
				
				do{
					dato = v.pedirIlusion();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getMagoDTO().setIlusion(dato);
				
				do{
					dato = v.pedirTransmutacion();	
				}while(!validaEntrada.validarPorcentaje(dato));
				personajeDTO.getMagoDTO().setTransmutacion(dato);
				break;
		}
		
		pDAO.crearPersonaje(personajeDTO);
		v.mostrarSuceso(pDAO.getSuceso());
		 
	}
	
	public void buscarPersonaje() {
		PersonajeDTO pDTO = new PersonajeDTO();
		String dato;
		do{
			dato = v.pedirNombre();	
		}while(!validaEntrada.validarString(dato));
		dato = validaEntrada.comprobarNombre(dato);
		pDTO.setNombre(dato);
		
		pDAO.buscarPersonaje(pDTO);
		//v.mostrarSuceso(pDAO.getSuceso());
		this.mostrarPersonajes(pDAO.getPersonajes());
	}
	
	public void buscarPersonajeFiltro() {
		PersonajeDTO pers = new PersonajeDTO();
		Boolean salir = false;

		MenusVista menuV = new MenusVista();
		menuV.menuBuscarFiltroPersonaje();
		
		Integer opt;
		do {
			opt = menuV.pedirOpcion();	
		}while(!validaEntrada.validarSelectorBuscarPersonaje(opt));
	
		salir = selectorGeneralPersonaje(pers, opt);
		pDAO.buscarPersonaje(pers);
		if(!salir) {
			if(pDAO.getPersonajes().size()==0) {
				v.mostrarSuceso("No se han encontrado coincidencias");
			}else {
				mostrarPersonajes(pDAO.getPersonajes());
			}
		}	
		
	}
	
	public void eliminarPersonaje() {
		PersonajeDTO pDTO = new PersonajeDTO();
		String dato;
		do{
			dato = v.pedirNombre();	
		}while(!validaEntrada.validarString(dato));
		pDTO.setNombre(dato);
		
		pDAO.eliminarPersonaje(pDTO);
		v.mostrarSuceso(pDAO.getSuceso());
	}
	
	public void modificarPersonaje() {
		PersonajeDTO pDTO = new PersonajeDTO();
		String nombre;
		do {
			do{
				nombre = v.pedirNombre();	
			}while(!validaEntrada.validarString(nombre));
			nombre = validaEntrada.comprobarNombre(nombre);
			pDTO.setNombre(nombre);
			pDAO.buscarPersonaje(pDTO);
			this.mostrarPersonajes(pDAO.getPersonajes());
			if(pDAO.getPersonajes().size()!=1) {
				v.mostrarConcretaNombre();
			}
		}while(pDAO.getPersonajes().size()!=1 || "".equals(pDAO.getPersonaje().getNombre()));
		selectorModificarPersonaje(pDAO.getPersonajes().get(0));
	}
	
	public void selectorGeneralPersonaje_old(PersonajeDTO persOrig, Integer modo) {
		PersonajeDTO pers = new PersonajeDTO();
		pers.setNombre(persOrig.getNombre());
		String repetir = "n";
		Boolean salir = false;				
		Integer opt = 0;
		do {
			MenusVista menuV = new MenusVista();
			if(modo == MODIFICAR) {
					menuV.menuEditarPersonaje();
				if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())){
					menuV.menuAtributosPicaro();
				}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())){
					menuV.menuAtributosGuerrero();
				}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())){
					menuV.menuAtributosMago();
				}
				do {
					opt = menuV.pedirOpcion();	
				}while(!validaEntrada.validarSelectorModificarPersonaje(opt, persOrig.getTipo()));
			}else if(modo == BUSCAR) {
				menuV.menuBuscarFiltroPersonaje();
				do {
					opt = menuV.pedirOpcion();	
				}while(!validaEntrada.validarSelectorBuscarPersonaje(opt));
			}
			
			Integer dato;
			Double datoD;
			switch(opt) {
				case 1:
					/*String nombre;
					String nombreOld;
					do{
						nombre = v.pedirNombre();	
					}while(!validaEntrada.validarString(nombre));
					nombreOld = pers.getNombre();
					pers.setNombre(nombre);*/
					System.out.println("DEBUG: To-Do");
					break;
				case 2:
					do{
						dato = v.pedirNivel();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setNivel(dato);
					break;
				case 3:
					do{
						dato = v.pedirVida();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setVida(dato);
					break;
				case 4:
					do{
						dato = v.pedirFuerza();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setFuerza(dato);
					break;
				case 5:
					do{
						dato = v.pedirDestreza();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setDestreza(dato);
					break;
				case 6:
					do{
						dato = v.pedirConstitucion();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setConstitucion(dato);
					break;
				case 7:
					do{
						dato = v.pedirInteligencia();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setInteligencia(dato);
					break;
				case 8:
					do{
						datoD = v.pedirOro();	
					}while(!validaEntrada.validarPositivo(datoD));
					pers.setOro(datoD);
					break;
				case 9:
					pers.setTipo(persOrig.getTipo());
					if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirSombras();
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getPicaroDTO().setEscondeSombras(datoD);
					}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirBerserker();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getGuerreroDTO().setBerserker(datoD);
					}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirPiromancia();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getMagoDTO().setPiromancia(datoD);
					}
					break;
				case 10:
					pers.setTipo(persOrig.getTipo());
					if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirFurtivo();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getPicaroDTO().setAtaqueFurtivo(datoD);
					}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirArmasPesadas();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getGuerreroDTO().setArmasPesadas(datoD);
					}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirNigromancia();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getMagoDTO().setNigromancia(datoD);
					}
					break;
				case 11:
					pers.setTipo(persOrig.getTipo());
					if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirCerraduras();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getPicaroDTO().setAbrirCerraduras(datoD);
					}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirArmasLigeras();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getGuerreroDTO().setArmasLigeras(datoD);
					}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirIlusion();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getMagoDTO().setIlusion(datoD);
					}
					break;
				case 12:
					if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirTransmutacion();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.setTipo(persOrig.getTipo());
						pers.getMagoDTO().setTransmutacion(datoD);
					}
					else
						salir = true;
					break;
				default:
					salir = true;
					break;
			}
			if(modo == MODIFICAR) {
				pDAO.modificarPersonaje(pers);
			}else if(modo == BUSCAR) {
				pDAO.buscarPersonaje(pers);
				salir = true;
			}
			if(salir) {
				return;
			}
			if(modo == MODIFICAR) {
				do{
					repetir = v.pedirRepetirModifica();	
				}while(!validaEntrada.validarSiNo(repetir));
			}
			
		
		}while("s".equalsIgnoreCase(repetir));
		
	}
	
	public Boolean selectorGeneralPersonaje(PersonajeDTO pers, Integer opt) {
		
		Integer dato;
		Double datoD;
		switch(opt) {
			case 1:
				String nombre;
				//String nombreOld;
				do{
					nombre = v.pedirNombre();	
				}while(!validaEntrada.validarString(nombre));
				//nombreOld = pers.getNombre();
				pers.setNombre(nombre);
				//System.out.println("DEBUG: To-Do");
				break;
			case 2:
				do{
					dato = v.pedirNivel();	
				}while(!validaEntrada.validarPositivo(dato));
				pers.setNivel(dato);
				break;
			case 3:
				do{
					dato = v.pedirVida();	
				}while(!validaEntrada.validarPositivo(dato));
				pers.setVida(dato);
				break;
			case 4:
				do{
					dato = v.pedirFuerza();	
				}while(!validaEntrada.validarPositivo(dato));
				pers.setFuerza(dato);
				break;
			case 5:
				do{
					dato = v.pedirDestreza();	
				}while(!validaEntrada.validarPositivo(dato));
				pers.setDestreza(dato);
				break;
			case 6:
				do{
					dato = v.pedirConstitucion();	
				}while(!validaEntrada.validarPositivo(dato));
				pers.setConstitucion(dato);
				break;
			case 7:
				do{
					dato = v.pedirInteligencia();	
				}while(!validaEntrada.validarPositivo(dato));
				pers.setInteligencia(dato);
				break;
			case 8:
				do{
					datoD = v.pedirOro();	
				}while(!validaEntrada.validarPositivo(datoD));
				pers.setOro(datoD);
				break;
			case 9:
				if(Constantes.PICARO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirSombras();
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getPicaroDTO().setEscondeSombras(datoD);
				}else if(Constantes.GUERRERO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirBerserker();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getGuerreroDTO().setBerserker(datoD);
				}else if(Constantes.MAGO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirPiromancia();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getMagoDTO().setPiromancia(datoD);
				}
				break;
			case 10:
				if(Constantes.PICARO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirFurtivo();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getPicaroDTO().setAtaqueFurtivo(datoD);
				}else if(Constantes.GUERRERO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirArmasPesadas();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getGuerreroDTO().setArmasPesadas(datoD);
				}else if(Constantes.MAGO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirNigromancia();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getMagoDTO().setNigromancia(datoD);
				}
				break;
			case 11:
				if(Constantes.PICARO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirCerraduras();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getPicaroDTO().setAbrirCerraduras(datoD);
				}else if(Constantes.GUERRERO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirArmasLigeras();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getGuerreroDTO().setArmasLigeras(datoD);
				}else if(Constantes.MAGO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirIlusion();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getMagoDTO().setIlusion(datoD);
				}
				break;
			case 12:
				if(Constantes.MAGO.equalsIgnoreCase(pers.getTipo())) {
					do{
						datoD = v.pedirTransmutacion();	
					}while(!validaEntrada.validarPorcentaje(datoD));
					pers.getMagoDTO().setTransmutacion(datoD);
				}
				else
					return true;
				break;
			default:
				return true;
		}
		return false;
	}
	
	public void selectorModificarPersonaje(PersonajeDTO persOrig) {
		PersonajeDTO pers = new PersonajeDTO();
		pers.setNombre(persOrig.getNombre());
		String repetir = "n";
		Boolean salir = false;
		do {
			MenusVista menuV = new MenusVista();
			menuV.menuEditarPersonaje();
			if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())){
				menuV.menuAtributosPicaro();
				
			}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())){
				menuV.menuAtributosGuerrero();
			}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())){
				menuV.menuAtributosMago();
			}
			
			Integer opt;
			do {
				opt = menuV.pedirOpcion();	
			}while(!validaEntrada.validarSelectorModificarPersonaje(opt, persOrig.getTipo()));
		
			if((opt > 8 && opt <13)) {
				pers.setTipo(persOrig.getTipo());
			}
			
			salir = selectorGeneralPersonaje(pers, opt);
			pDAO.modificarPersonaje(pers);
			if(salir) {
				return;
			}
			do{
				repetir = v.pedirRepetirModifica();	
			}while(!validaEntrada.validarSiNo(repetir));
		
		}while("s".equalsIgnoreCase(repetir));		
	}
	
	public void selectorModificarPersonaje_old(PersonajeDTO persOrig) {
		PersonajeDTO pers = new PersonajeDTO();
		pers.setNombre(persOrig.getNombre());
		String repetir = "n";
		Boolean salir = false;
		do {
			MenusVista menuV = new MenusVista();
			menuV.menuEditarPersonaje();
			if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())){
				menuV.menuAtributosPicaro();
			}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())){
				menuV.menuAtributosGuerrero();
			}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())){
				menuV.menuAtributosMago();
			}
			
			Integer opt;
			do {
				opt = menuV.pedirOpcion();	
			}while(!validaEntrada.validarSelectorModificarPersonaje(opt, persOrig.getTipo()));
		
			Integer dato;
			Double datoD;
			switch(opt) {
				case 1:
					/*String nombre;
					String nombreOld;
					do{
						nombre = v.pedirNombre();	
					}while(!validaEntrada.validarString(nombre));
					nombreOld = pers.getNombre();
					pers.setNombre(nombre);*/
					System.out.println("DEBUG: To-Do");
					break;
				case 2:
					do{
						dato = v.pedirNivel();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setNivel(dato);
					//pDAO.modificarPersonaje(pers);
					break;
				case 3:
					do{
						dato = v.pedirVida();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setVida(dato);
					//pDAO.modificarPersonaje(pers);
					break;
				case 4:
					do{
						dato = v.pedirFuerza();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setFuerza(dato);
					break;
				case 5:
					do{
						dato = v.pedirDestreza();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setDestreza(dato);
					break;
				case 6:
					do{
						dato = v.pedirConstitucion();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setConstitucion(dato);
					break;
				case 7:
					do{
						dato = v.pedirInteligencia();	
					}while(!validaEntrada.validarPositivo(dato));
					pers.setInteligencia(dato);
					break;
				case 8:
					do{
						datoD = v.pedirOro();	
					}while(!validaEntrada.validarPositivo(datoD));
					pers.setOro(datoD);
					break;
				case 9:
					pers.setTipo(persOrig.getTipo());
					if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirSombras();
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getPicaroDTO().setEscondeSombras(datoD);
					}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirBerserker();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getGuerreroDTO().setBerserker(datoD);
					}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirPiromancia();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getMagoDTO().setPiromancia(datoD);
					}
					break;
				case 10:
					pers.setTipo(persOrig.getTipo());
					if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirFurtivo();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getPicaroDTO().setAtaqueFurtivo(datoD);
					}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirArmasPesadas();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getGuerreroDTO().setArmasPesadas(datoD);
					}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirNigromancia();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getMagoDTO().setNigromancia(datoD);
					}
					break;
				case 11:
					pers.setTipo(persOrig.getTipo());
					if(Constantes.PICARO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirCerraduras();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getPicaroDTO().setAbrirCerraduras(datoD);
					}else if(Constantes.GUERRERO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirArmasLigeras();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getGuerreroDTO().setArmasLigeras(datoD);
					}else if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirIlusion();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.getMagoDTO().setIlusion(datoD);
					}
					break;
				case 12:
					if(Constantes.MAGO.equalsIgnoreCase(persOrig.getTipo())) {
						do{
							datoD = v.pedirTransmutacion();	
						}while(!validaEntrada.validarPorcentaje(datoD));
						pers.setTipo(persOrig.getTipo());
						pers.getMagoDTO().setTransmutacion(datoD);
					}
					else
						salir = true;
					break;
				default:
					salir = true;
					break;
			}
			pDAO.modificarPersonaje(pers);
			if(salir) {
				return;
			}
			do{
				repetir = v.pedirRepetirModifica();	
			}while(!validaEntrada.validarSiNo(repetir));
		
		}while("s".equalsIgnoreCase(repetir));
		
		//System.out.println("DEBUG: To-Do: Falta implementar actualizacion en BBDD");
	}
	
	
	
	public void mostrarPersonajes(ArrayList<PersonajeDTO> listaPers) {
		for(PersonajeDTO p: listaPers) {
			mostrarPersonaje(p);
		}
	}
	
	public void mostrarPersonaje(PersonajeDTO p) {
		if("".equals(p.getNombre())) {
			v.mostrarSuceso(pDAO.getSuceso());
			return;
		}
		v.mostrarPersonaje(p);
		if(Constantes.PICARO.equalsIgnoreCase(p.getTipo())){
			v.mostrarPicaro(p);
		}else if(Constantes.GUERRERO.equalsIgnoreCase(p.getTipo())){
			v.mostrarGuerrero(p);
		}else if(Constantes.MAGO.equalsIgnoreCase(p.getTipo())){
			v.mostrarMago(p);
		}
	}
}
