package personaje.controlador.validador;

import personaje.controlador.Constantes;

/**
 * U09A01-EjemploPersonaje
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class ValidadorEntradas {

	public boolean validarString(String texto) {
		if(texto.length()>50) {
			return false;
		}
		return true;
	}
	
	public boolean validarInteger(Integer num) {
		
		return true;
	}
	
	public boolean validarDouble(Double num) {
		
		return true;
	}
	
	public boolean validarPositivo(Integer num) {
		if(num>=0)
			return true;
		else
			return false;
	}

	public boolean validarPositivo(Double num) {
		if(num>=0)
			return true;
		else
			return false;
	}
	
	public boolean validarPorcentaje(Double num) {
		if(num>=0 && num<=100)
			return true;
		else
			return false;
	}
	
	public boolean validarTipo(String tipo) {	
		if("picaro".equalsIgnoreCase(tipo) || 
				"mago".equalsIgnoreCase(tipo) || 
				"guerrero".equalsIgnoreCase(tipo)) {
			return true;
		}
		return false;
	}
	
	public boolean validarSelectorMenuPrincipal(Integer dato) {
		if(dato>=1 && dato <=3) {
			return true;
		}
		return false;
	}
	
	public boolean validarSelectorMenuPersonaje(Integer dato) {
		if(dato>=1 && dato <=5) {
			return true;
		}
		return false;
	}
	
	public String comprobarNombre(String nombre) {
		if(nombre.length()==0) {
			return "";
		}else {
			return nombre;
		}
	}
	
	public boolean validarSelectorModificarPersonaje(Integer dato, String tipo) {
		if((Constantes.PICARO.equalsIgnoreCase(tipo) 
				|| Constantes.GUERRERO.equalsIgnoreCase(tipo)) 
				&& dato>=1 && dato <=12) {
			return true;
		}
		if(Constantes.MAGO.equalsIgnoreCase(tipo)  
				&& dato>=1 && dato <=13) {
			return true;
		}
		return false;
	}
	
	public boolean validarSelectorBuscarPersonaje(Integer dato) {
		if(dato>=1 && dato <=8) {
			return true;
		}
		return false;
	}
	
	public boolean validarSiNo(String dato) {
		if("s".equalsIgnoreCase(dato) || "n".equalsIgnoreCase(dato))
			return true;
		return false;
	}
}
