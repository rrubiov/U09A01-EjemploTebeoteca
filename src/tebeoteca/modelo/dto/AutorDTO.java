package tebeoteca.modelo.dto;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class AutorDTO {

	private String id;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	
	public AutorDTO() {
		this.id = "";
		this.nombre = "";
		this.apellido = "";
		this.nacionalidad = "";
	}
	
	public AutorDTO(String id, String nombre, String apellido, String nacionalidad) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	

	
}
