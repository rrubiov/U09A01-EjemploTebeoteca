package tebeoteca.modelo;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public interface Consultas {

	/*INSERT*/
	public static final String INSERTAR_TEBEO = "INSERT INTO tebeo VALUES (?,?,?,?)";
	public static final String INSERTAR_AUTOR = "INSERT INTO autor VALUES (?,?,?,?)";
	public static final String INSERTAR_REALIZA = "INSERT INTO tebeo VALUES (?,?)";


	/*DELETE*/
	public static final String BORRAR_TEBEO = "DELETE FROM tebeo WHERE isbn = ?";
	public static final String BORRAR_AUTOR = "DELETE FROM autor WHERE id = ?";
	public static final String BORRAR_REALIZA = "DELETE FROM realiza WHERE tebeo = ? AND autor = ?";
	
	
	/*UPDATE*/
	public static final String ACTUALIZAR_TEBEO_TITULO = "UPDATE tebeo SET titulo = ? WHERE isbn = ?";
	public static final String ACTUALIZAR_TEBEO_NUMERO = "UPDATE tebeo SET numero = ? WHERE isbn = ?";
	public static final String ACTUALIZAR_TEBEO_COLECCION = "UPDATE tebeo SET coleccion = ? WHERE isbn = ?";

	public static final String ACTUALIZAR_AUTOR_NOMBRE = "UPDATE autor SET nombre = ? WHERE id = ?";
	public static final String ACTUALIZAR_AUTOR_APELLIDO = "UPDATE autor SET apellido = ? WHERE id = ?";
	public static final String ACTUALIZAR_AUTOR_NACIONALIDAD = "UPDATE autor SET nacionalidad = ? WHERE id = ?";

	public static final String ACTUALIZAR_REALIZA_TEBEO = "UPDATE realiza SET tebeo = ? WHERE tebeo = ? AND autor = ?";
	public static final String ACTUALIZAR_REALIZA_AUTOR = "UPDATE realiza SET autor = ? WHERE tebeo = ? AND autor = ?";


	/*BUSCAR*/
	public static final String  BUSCAR_TEBEOS = "SELECT * FROM tebeo";	
	public static final String  BUSCAR_TEBEO_ISBN = "SELECT * FROM tebeo WHERE isbn = ?";
	public static final String  BUSCAR_TEBEO_TITULO = "SELECT * FROM tebeo WHERE titulo = ?";
	public static final String  BUSCAR_TEBEO_NUMERO = "SELECT * FROM tebeo WHERE numero = ?";
	public static final String  BUSCAR_TEBEO_COLECCION = "SELECT * FROM tebeo WHERE coleccion = ?";
	public static final String  BUSCAR_TEBEO_COLECCION_NUMERO = "SELECT * FROM tebeo WHERE coleccion = ? AND numero = ?";
	public static final String  BUSCAR_TEBEO_TITULO_NUMERO = "SELECT * FROM tebeo WHERE titulo = ? AND numero = ?";
	public static final String  BUSCAR_TEBEO_TITULO_COLECCION = "SELECT * FROM tebeo WHERE titulo = ? AND coleccion = ?";
	public static final String  BUSCAR_TEBEO_TITULO_COLECCION_NUMERO = "SELECT * FROM tebeo WHERE titulo = ? AND coleccion = ? AND numero = ?";

	
	
	public static final String  BUSCAR_AUTORES = "SELECT * FROM autor";
	public static final String  BUSCAR_AUTOR_ID = "SELECT * FROM autor WHERE id = ?";
	public static final String  BUSCAR_AUTOR_NOMBRE = "SELECT * FROM autor WHERE nombre = ?";
	public static final String  BUSCAR_AUTOR_APELLIDO = "SELECT * FROM autor WHERE apellido = ?";
	public static final String  BUSCAR_AUTOR_NACIONALIDAD = "SELECT * FROM autor WHERE nacionalidad = ?";
	public static final String  BUSCAR_AUTOR_APELLIDO_NACIONALIDAD = "SELECT * FROM autor WHERE apellido = ? AND nacionalidad = ?";
	public static final String  BUSCAR_AUTOR_NOMBRE_NACIONALIDAD = "SELECT * FROM autor WHERE nombre = ? AND nacionalidad = ?";
	public static final String  BUSCAR_AUTOR_NOMBRE_APELLIDO = "SELECT * FROM autor WHERE nombre = ? AND apellido = ?";
	public static final String  BUSCAR_AUTOR_NOMBRE_APELLIDO_NACIONALIDAD = "SELECT * FROM autor WHERE nombre = ? AND apellido = ? AND nacionalidad = ?";

	
	
	public static final String  BUSCAR_REALIZAN = "SELECT * FROM realiza";
	public static final String  BUSCAR_REALIZA_TEBEO = "SELECT * FROM realiza WHERE tebeo = ?";
	public static final String  BUSCAR_REALIZA_AUTOR = "SELECT * FROM realiza WHERE autor = ?";
	public static final String  BUSCAR_REALIZA_TEBEO_AUTOR = "SELECT * FROM realiza WHERE tebeo = ? AND autor = ?";

	
}
