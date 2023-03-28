package tebeoteca.controlador.validador;

import java.util.InputMismatchException;
import java.util.Scanner;

import tebeoteca.controlador.Constantes;

/**
 * U09A01-EjemploPersonaje
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public interface ValidadorEntradas {

	public default boolean validarString(String texto) {
		if(texto.length()>50) {
			return false;
		}
		return true;
	}
	
	public default boolean validarPositivo(Integer num) {
		if(num>=0)
			return true;
		else
			return false;
	}
	
	public default boolean validarSelectorMenuPrincipal(Integer dato) {
		if(dato>=1 && dato <=3) {
			return true;
		}
		return false;
	}
	
	public default boolean validarSelectorMenuTebeo(Integer dato) {
		if(dato>=1 && dato <=5) {
			return true;
		}
		return false;
	}
	
	public default boolean validarSelectorMenuAutor(Integer dato) {
		if(dato>=1 && dato <=5) {
			return true;
		}
		return false;
	}
	
	public default String comprobarIsbn(String nombre) {
		if(nombre.length()==0) {
			return "";
		}else {
			return nombre;
		}
	}
	
	public default boolean validarSelectorAtributosEditar(Integer dato, Integer tipo) {
		if(Constantes.TEBEO == tipo && dato>=1 && dato <=4) {
			return true;
		}
		if(Constantes.AUTOR == tipo && dato>=1 && dato <=3) {
			return true;
		}
		return false;
	}
	
	public default boolean validarSelectorAtributosBuscar(Integer dato, Integer tipo) {
		if(Constantes.TEBEO == tipo && dato>=1 && dato <=5) {
			return true;
		}
		if(Constantes.AUTOR == tipo && dato>=1 && dato <=3) {
			return true;
		}
		return false;
	}
	
	public default boolean validarSiNo(String dato) {
		if("s".equalsIgnoreCase(dato) || "n".equalsIgnoreCase(dato))
			return true;
		return false;
	}
	
	/*
	 * VALIDADOR DE OPCION NUMERICA
	 */
	
	public default Integer pedirOpcionNumerica() {
		Scanner sc = new Scanner(System.in);
		Integer op = -1;
		
		do {
			try {
				op = sc.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.out.println("Debes introducir un numero");
				sc.next();
			}
		}while(true);
		
		return op;
	}

	
}
