package tebeoteca.controlador.tebeo;

import java.util.ArrayList;

import tebeoteca.controlador.Constantes;
import tebeoteca.controlador.Menu;
import tebeoteca.controlador.validador.ValidadorEntradas;
import tebeoteca.modelo.dao.TebeoDAO;
import tebeoteca.modelo.dto.TebeoDTO;
import tebeoteca.vista.menu.MenusVista;
import tebeoteca.vista.tebeo.TebeoVista;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class Tebeo implements ValidadorEntradas{

	TebeoVista v = new TebeoVista();
	TebeoDAO tDAO = new TebeoDAO();
	private static final Integer BUSCAR = 0;
	private static final Integer MODIFICAR = 1;
	
	public Tebeo() {
		
	}
	
	public void gestionTebeo() {
		MenusVista menu = new MenusVista();
		int opcion;
		do {
			menu.menuTebeo();
			
			do {
				v.pedirOpcion();
				opcion = pedirOpcionNumerica();
			}while(!validarSelectorMenuTebeo(opcion));
			
			switch(opcion) {
				case 1:
					crearTebeo();
					break;
				case 2:
					buscarTebeoFiltro();
					break;
				case 3: 
					modificarTebeo();
					break;
				case 4:
					eliminarTebeo();
					break;
				case 5:
					return;
				default:
					return;
			}
		}while(opcion!=5);
		
	}
	
	public void crearTebeo() {
		TebeoDTO tebeoDTO = new TebeoDTO();
		
		String isbn;
		do{
			isbn = v.pedirIsbn();	
		}while(!validarString(isbn));
		tebeoDTO.setIsbn(isbn);
		
		String titulo;
		do{
			titulo = v.pedirTitulo();	
		}while(!validarString(titulo));
		tebeoDTO.setTitulo(titulo);

		Integer numero;
		do{
			numero = v.pedirNumero();	
		}while(!validarPositivo(numero));
		tebeoDTO.setNumero(numero.toString());
		
		String coleccion;
		do{
			coleccion = v.pedirColeccion();	
		}while(!validarString(coleccion));
		tebeoDTO.setColeccion(coleccion);
		
		tDAO.crearTebeo(tebeoDTO);
		v.mostrarSuceso(tDAO.getSuceso());
		 
	}
	
	public void buscarPersonaje() {
		TebeoDTO tDTO = new TebeoDTO();
		String dato;
		do{
			dato = v.pedirIsbn();	
		}while(!validarString(dato));
		dato = comprobarIsbn(dato);
		tDTO.setIsbn(dato);
		
		tDAO.buscarTebeo(tDTO);
		v.mostrarSuceso(tDAO.getSuceso());
		this.mostrarTebeos(tDAO.getTebeos());
	}
	
	public void buscarTebeoFiltro() {
		TebeoDTO tebeo = new TebeoDTO();
		Boolean salir = false;

		MenusVista menuV = new MenusVista();
		menuV.menuBuscarFiltroTebeo();
		
		Integer opt;
		do {
			v.pedirOpcion();
			opt = pedirOpcionNumerica();	
		}while(!validarSelectorAtributosBuscar(opt, Constantes.TEBEO));
	
		salir = selectorGeneralTebeo(tebeo, opt, Constantes.BUSCAR);
		tDAO.buscarTebeo(tebeo);
		if(!salir) {
			if(tDAO.getTebeos().size()==0) {
				v.sinCoincidencias();
			}else {
				mostrarTebeos(tDAO.getTebeos());
			}
		}	
		
	}
	
	public void eliminarTebeo() {
		TebeoDTO tDTO = new TebeoDTO();
		String dato;
		do{
			dato = v.pedirIsbn();	
		}while(!validarString(dato));
		tDTO.setIsbn(dato);
		
		tDAO.eliminarTebeo(tDTO);
		v.mostrarSuceso(tDAO.getSuceso());
	}
	
	public void modificarTebeo() {
		TebeoDTO tDTO = new TebeoDTO();
		String isbn;
		do {
			do{
				isbn = v.pedirIsbn();	
			}while(!validarString(isbn));
			isbn = comprobarIsbn(isbn);
			tDTO.setIsbn(isbn);
			tDAO.buscarTebeo(tDTO);
			this.mostrarTebeos(tDAO.getTebeos());
			if(tDAO.getTebeos().size()!=1) {
				v.mostrarConcretaIsbn();
			}
		}while(tDAO.getTebeos().size()!=1 || "".equals(tDAO.getTebeo().getIsbn()));
		selectorModificarTebeo(tDAO.getTebeos().get(0));
	}

	public Boolean selectorGeneralTebeo(TebeoDTO tebeo, Integer opt, Integer peticion) {
		
		String dato;
		Integer datoN;
		
		if(peticion == Constantes.EDITAR)
			opt++;
		
		switch(opt) {
			case 1:
				do{
					dato = v.pedirIsbn();	
				}while(!validarString(dato));
				tebeo.setIsbn(dato);
				break;
			case 2:
				do{
					dato = v.pedirTitulo();	
				}while(!validarString(dato));
				tebeo.setTitulo(dato);
				break;
			case 3:
				do{
					datoN = v.pedirNumero();	
				}while(!validarPositivo(datoN));
				tebeo.setNumero(datoN.toString());
				break;
			case 4:
				do{
					dato = v.pedirColeccion();	
				}while(!validarString(dato));
				tebeo.setColeccion(dato);
				break;

			default:
				return true;
		}
		return false;
	}
	
	public void selectorModificarTebeo(TebeoDTO tebeoOrig) {
		TebeoDTO tebeo = new TebeoDTO();
		tebeo.setIsbn(tebeoOrig.getIsbn());
		String repetir = "n";
		Boolean salir = false;
		do {
			MenusVista menuV = new MenusVista();
			menuV.menuEditarTebeo();
			
			Integer opt;
			do {
				opt = pedirOpcionNumerica();	
			}while(!validarSelectorAtributosEditar(opt, Constantes.TEBEO));
			
			salir = selectorGeneralTebeo(tebeo, opt, Constantes.EDITAR);
			tDAO.modificarTebeo(tebeo);
			if(salir) {
				return;
			}
			do{
				repetir = v.pedirRepetirModifica();	
			}while(!validarSiNo(repetir));
		
		}while("s".equalsIgnoreCase(repetir));		
	}
		
	public void mostrarTebeos(ArrayList<TebeoDTO> listaTebeos) {
		for(TebeoDTO t: listaTebeos) {
			mostrarTebeo(t);
		}
	}
	
	public void mostrarTebeo(TebeoDTO tebeo) {
		if("".equals(tebeo.getIsbn())) {
			v.mostrarSuceso(tDAO.getSuceso());
			return;
		}
		v.mostrarTebeo(tebeo);
	}
}
