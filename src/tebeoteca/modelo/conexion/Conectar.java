package tebeoteca.modelo.conexion;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conectar {
	
	private Connection connect = null;
	private Statement statement = null;
	
	public Connection getConnect() {
		return connect;
	}
	
	public void setConnect(Connection connect) {
		this.connect = connect;
	}
	
	public Statement getStatement() {
		return statement;
	}
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	final private String driver_postgres = "org.postgresql.Driver";
	final private String driver_mysql = "com.mysql.cj.jdbc.Driver";
	final private String host = "localhost";
	final private String bbdd = "tebeoteca";
	final private String user = "raul";
	final private String passwd = "admin";
	  
	public Conectar() {
		try {
			// Cargamos el driver de MySQL. Cada SGDB tiene el suyo.
			Class.forName(driver_mysql);
			  
			// Configuramos la conexion con la BBDD -- jdbc:mysql://localhost/feedback?user=admin&password=1234
			//connect = DriverManager.getConnection("jdbc:postgresql://" + host + "/"+bbdd, user, passwd);
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "/"+bbdd+"?serverTimezone=UTC", user, passwd);
  
			
			// El statement permite realizar consultas SQL a la base de datos.
			statement = connect.createStatement();

			if(statement==null) {
				throw new Exception("Error al crear la conexion. No se pudo obtener el statement");
			}
			
		}catch (Exception e) {
		    System.out.println("Error al crear la conexion: "+e.getLocalizedMessage());
		  } 
	}
	  
	// Para finalizar, necesitamos cerrar el resultSet, el statement y la conexion.
	public void cerrarConexion(ResultSet resultSet, Connection connect, Statement statement) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
	    } catch (Exception e) {
	    	System.out.println("Error al cerrar las conexiones: "+e.getLocalizedMessage());
	    }
	}
	
	public void cerrarConexion(ResultSet resultSet, Connection connect, PreparedStatement ps) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connect != null) {
				connect.close();
			}
	    } catch (Exception e) {
	    	System.out.println("Error al cerrar las conexiones: "+e.getLocalizedMessage());
	    }
	}
}