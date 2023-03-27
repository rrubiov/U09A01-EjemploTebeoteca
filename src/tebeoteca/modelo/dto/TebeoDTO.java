package tebeoteca.modelo.dto;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class TebeoDTO {

	private String isbn;
	private String titulo;
	private String numero;
	private String coleccion;
	
	public TebeoDTO() {
		this.isbn = null;
		this.titulo = null;
		this.numero = null;
		this.coleccion = null;
	}
	
	public TebeoDTO(String isbn, String titulo, String numero, String coleccion) {
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.numero = numero;
		this.coleccion = coleccion;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getColeccion() {
		return coleccion;
	}

	public void setColeccion(String coleccion) {
		this.coleccion = coleccion;
	}
	
	
}
