package tebeoteca.modelo.dto;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class RealizaDTO {

	private String tebeo;
	private String autor;

	
	public RealizaDTO() {
		this.tebeo = "";
		this.autor = "";

	}

	public RealizaDTO(String tebeo, String autor) {
		super();
		this.tebeo = tebeo;
		this.autor = autor;
	}

	public String getTebeo() {
		return tebeo;
	}

	public void setTebeo(String tebeo) {
		this.tebeo = tebeo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
