package tebeoteca.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tebeoteca.modelo.Consultas;
import tebeoteca.modelo.conexion.Conectar;
import tebeoteca.modelo.dto.AutorDTO;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class AutorDAO implements Consultas{

	private AutorDTO autor = new AutorDTO();
	private PreparedStatement ps = null;
	private Conectar con = null;
	private ArrayList<AutorDTO> autores = new ArrayList<AutorDTO>();
	private ResultSet resultSet = null;
	private String suceso;
	
	public void crearAutor(AutorDTO aDTO) {
		this.suceso = "La consulta se ha realizado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_ID);
			this.ps.setInt(1, Integer.parseInt(aDTO.getId()));
			this.resultSet = this.ps.executeQuery();
			//this.resultSet = this.ps.getResultSet();
			if(this.resultSet.next()==false) {
				this.ps = this.con.getConnect().prepareStatement(INSERTAR_AUTOR);
				this.ps.setInt(1, Integer.parseInt(aDTO.getId()));
				this.ps.setString(2, aDTO.getNombre());
				this.ps.setString(3, aDTO.getApellido());
				this.ps.setString(4, aDTO.getNacionalidad());

				this.ps.executeUpdate();
				
			}else {
				this.suceso = "El Autor ya existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
		
	}
	
	public void eliminarAutor(AutorDTO aDTO) {
		this.suceso = "El Autor se ha eliminado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_ID);
			this.ps.setInt(1, Integer.parseInt(aDTO.getId()));
			this.resultSet = this.ps.executeQuery();
			//this.resultSet = this.ps.getResultSet();
			if(this.resultSet.next()==true) {
				this.ps = this.con.getConnect().prepareStatement(BORRAR_AUTOR);
				this.ps.setInt(1, Integer.parseInt(aDTO.getId()));
				this.ps.executeUpdate();
				
			}else {
				this.suceso = "El Autor no existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
	}
	
	public void buscarAutor(AutorDTO aDTO) {
		this.suceso = "La consulta se ha realizado con éxito";
		this.autores.clear();
		this.con = new Conectar();
		try {
			if(aDTO.getId()!=null) {
				this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_ID);
				this.ps.setInt(1, Integer.parseInt(aDTO.getId()));
				this.resultSet = this.ps.executeQuery();
				if(this.resultSet.next()==true) {
					this.setAutor(new AutorDTO());
					this.autor.setId(resultSet.getString(1));
					this.autor.setNombre(resultSet.getString(2));
					this.autor.setApellido(resultSet.getString(3));
					this.autor.setNacionalidad(resultSet.getString(4));
				}else {
                    this.suceso = "El Autor no existe";
                    this.getAutor().setId("");
                }
				this.autores.add(this.getAutor());
			}
			else {
				if(null==aDTO.getNombre()) {
					if(null==aDTO.getApellido()) {
						if(null==aDTO.getNacionalidad()) {

							/*
							 * ID			desconocido
							 * Nombre 		desconocido
							 * Apellido		desconocido
							 * Nacionalidad	desconocida
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTORES);
						
						}else {
							/*
							 * ID			desconocido
							 * Nombre 		desconocido
							 * Apellido		desconocido
							 * Nacionalidad	conocida
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_NACIONALIDAD);
							this.ps.setString(1, aDTO.getNacionalidad());
						}
					}else {
						if(null==aDTO.getNacionalidad()) {
							/*
							 * ID			desconocido
							 * Nombre 		desconocido
							 * Apellido		conocida
							 * Nacionalidad	desconocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_APELLIDO);
							this.ps.setString(1, aDTO.getApellido());
						}else {
							/*
							 * ID			desconocido
							 * Nombre 		desconocido
							 * Apellido		conocido
							 * Nacionalidad	conocida
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_APELLIDO_NACIONALIDAD);
							this.ps.setString(1, aDTO.getApellido());	
							this.ps.setString(2, aDTO.getNacionalidad());	
						}
					}
				}else {
					if(null==aDTO.getApellido()) {
						/*
						 * ID			desconocido
						 * Nombre 		conocido
						 * Apellido 	desconocido
						 * Nacionalidad	?
						 */
						if(null==aDTO.getNacionalidad()) {
							/*
							 * ID			desconocido
							 * Nombre 		conocido
							 * Apellido 	desconocido
							 * Nacionalidad	desconocida
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_NOMBRE);
							this.ps.setString(1, aDTO.getNombre());
						}else {
							/*
							 * ID			desconocido
							 * Nombre 		conocido
							 * Apellido 	desconocido
							 * Nacionalidad	conocida
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_NOMBRE_NACIONALIDAD);
							this.ps.setString(1, aDTO.getNombre());	
							this.ps.setInt(2, Integer.parseInt(aDTO.getNacionalidad()));
						}
					}else {
						/*
						 * ID			desconocido
						 * Nombre 		conocido
						 * Apellido 	conocido
						 * Nacionalidad	?
						 */
						if(null==aDTO.getNacionalidad()) {
							/*
							 * ID			desconocido
							 * Nombre 		conocido
							 * Apellido 	conocido
							 * Nacionalidad	desconocida
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_NOMBRE_APELLIDO);
							this.ps.setString(1, aDTO.getNombre());
							this.ps.setString(2, aDTO.getApellido());
						}else {
							/*
							 * ID			desconocido
							 * Nombre 		conocido
							 * Apellido 	conocido
							 * Nacionalidad	conocida
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_AUTOR_NOMBRE_APELLIDO_NACIONALIDAD);
							this.ps.setString(1, aDTO.getNombre());	
							this.ps.setString(2, aDTO.getApellido());
							this.ps.setString(3, aDTO.getNacionalidad());
						}
					}
				}
				//this.ps = this.con.getConnect().prepareStatement(Consultas.BUSCAR_PERSONAJES);
				this.resultSet = this.ps.executeQuery();
				while (resultSet.next()) {
					this.autor = new AutorDTO();
					this.autor.setId(resultSet.getString(1));
					this.autor.setNombre(resultSet.getString(1));
					this.autor.setApellido(resultSet.getString(1));
					this.autor.setNacionalidad(resultSet.getString(1));

    				this.autores.add(this.autor);
				}
			}
			
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
	}
	
	
	public void modificarAutor(AutorDTO aDTO) {
		this.suceso = "El Autor se ha modificado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(Consultas.BUSCAR_AUTOR_ID);
			this.ps.setInt(1, Integer.parseInt(aDTO.getId()));
			this.resultSet = this.ps.executeQuery();
			if(this.resultSet.next()==true) {
				/*Nombre*/
				if(null!=aDTO.getNombre()) {
					this.ps = this.con.getConnect().prepareStatement(Consultas.ACTUALIZAR_AUTOR_NOMBRE);
					this.ps.setString(1, aDTO.getNombre());
					this.ps.setInt(2, Integer.parseInt(aDTO.getId()));
					this.ps.executeUpdate();
				}
				/*Apellido*/
				else if(null!=aDTO.getApellido()) {
					this.ps = this.con.getConnect().prepareStatement(Consultas.ACTUALIZAR_AUTOR_APELLIDO);
					this.ps.setString(1, aDTO.getApellido());
					this.ps.setInt(2, Integer.parseInt(aDTO.getId()));
					this.ps.executeUpdate();
				}
				/*Nacionalidad*/
				else if(null!=aDTO.getNacionalidad()) {
					this.ps = this.con.getConnect().prepareStatement(Consultas.ACTUALIZAR_AUTOR_NACIONALIDAD);
					this.ps.setString(1, aDTO.getNacionalidad());
					this.ps.setInt(2, Integer.parseInt(aDTO.getId()));
					this.ps.executeUpdate();
				}
	
			}else {
				this.suceso = "El Autor no existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
		
	}

	public AutorDTO getAutor() {
		return autor;
	}

	public void setAutor(AutorDTO autor) {
		this.autor = autor;
	}

	public ArrayList<AutorDTO> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<AutorDTO> autores) {
		this.autores = autores;
	}

	public String getSuceso() {
		return suceso;
	}

	public void setSuceso(String suceso) {
		this.suceso = suceso;
	}
}
