package tebeoteca.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tebeoteca.modelo.Consultas;
import tebeoteca.modelo.conexion.Conectar;
import tebeoteca.modelo.dto.TebeoDTO;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class TebeoDAO implements Consultas{

	private TebeoDTO tebeo = new TebeoDTO();
	private PreparedStatement ps = null;
	private Conectar con = null;
	private ArrayList<TebeoDTO> tebeos = new ArrayList<TebeoDTO>();
	private ResultSet resultSet = null;
	private String suceso;
	
	public void crearTebeo(TebeoDTO tDTO) {
		this.suceso = "La consulta se ha realizado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_ISBN);
			this.ps.setInt(1, Integer.parseInt(tDTO.getIsbn()));
			this.resultSet = this.ps.executeQuery();
			//this.resultSet = this.ps.getResultSet();
			if(this.resultSet.next()==false) {
				this.ps = this.con.getConnect().prepareStatement(INSERTAR_TEBEO);
				this.ps.setInt(1, Integer.parseInt(tDTO.getIsbn()));
				this.ps.setString(2, tDTO.getTitulo());
				this.ps.setInt(3, Integer.parseInt(tDTO.getNumero()));
				this.ps.setString(4, tDTO.getColeccion());

				this.ps.executeUpdate();
				
			}else {
				this.suceso = "El tebeo ya existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
		
	}
	
	public void eliminarTebeo(TebeoDTO tDTO) {
		this.suceso = "El tebeo se ha eliminado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_ISBN);
			this.ps.setInt(1, Integer.parseInt(tDTO.getIsbn()));
			this.resultSet = this.ps.executeQuery();
			//this.resultSet = this.ps.getResultSet();
			if(this.resultSet.next()==true) {
				this.ps = this.con.getConnect().prepareStatement(BORRAR_TEBEO);
				this.ps.setInt(1, Integer.parseInt(tDTO.getIsbn()));
				this.ps.executeUpdate();
				
			}else {
				this.suceso = "El tebeo no existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
	}
	
	public void buscarTebeo(TebeoDTO tDTO) {
		this.suceso = "La consulta se ha realizado con éxito";
		this.tebeos.clear();
		this.con = new Conectar();
		try {
			if(tDTO.getIsbn()!=null) {
				this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_ISBN);
				this.ps.setInt(1, Integer.parseInt(tDTO.getIsbn()));
				this.resultSet = this.ps.executeQuery();
				if(this.resultSet.next()==true) {
					this.setTebeo(new TebeoDTO());
					this.tebeo.setIsbn(resultSet.getString(1));
					this.tebeo.setTitulo(resultSet.getString(2));
					this.tebeo.setNumero(resultSet.getString(3));
					this.tebeo.setColeccion(resultSet.getString(4));
				}else {
                    this.suceso = "El tebeo no existe";
                    this.getTebeo().setIsbn("");
                }
				this.tebeos.add(this.getTebeo());
			}
			else {
				if(null==tDTO.getTitulo()) {
					if(null==tDTO.getColeccion()) {
						if(null==tDTO.getNumero()) {

							/*
							 * ISBN			desconocido
							 * Titulo 		desconocido
							 * Coleccion	desconocida
							 * Numero		desconocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEOS);
						
						}else {
							/*
							 * ISBN			desconocido
							 * Titulo 		desconocido
							 * Coleccion	desconocida
							 * Numero		conocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_NUMERO);
							this.ps.setInt(1, Integer.parseInt(tDTO.getNumero()));
						}
					}else {
						if(null==tDTO.getNumero()) {
							/*
							 * ISBN			desconocido
							 * Titulo 		desconocido
							 * Coleccion	conocida
							 * Numero		desconocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_COLECCION);
							this.ps.setString(1, tDTO.getColeccion());
						}else {
							/*
							 * ISBN			desconocido
							 * Titulo 		desconocido
							 * Coleccion	conocida
							 * Numero		conocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_COLECCION_NUMERO);
							this.ps.setString(1, tDTO.getColeccion());	
							this.ps.setInt(2, Integer.parseInt(tDTO.getNumero()));
						}
					}
				}else {
					if(null==tDTO.getColeccion()) {
						/*
						 * ISBN			desconocido
						 * Titulo 		conocido
						 * Coleccion	desconocida
						 * Numero		?
						 */
						if(null==tDTO.getNumero()) {
							/*
							 * ISBN			desconocido
							 * Titulo 		conocido
							 * Coleccion	desconocida
							 * Numero		desconocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_TITULO);
							this.ps.setString(1, tDTO.getTitulo());
						}else {
							/*
							 * ISBN			desconocido
							 * Titulo 		conocido
							 * Coleccion	desconocida
							 * Numero		conocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_TITULO_NUMERO);
							this.ps.setString(1, tDTO.getTitulo());	
							this.ps.setInt(2, Integer.parseInt(tDTO.getNumero()));
						}
					}else {
						/*
						 * ISBN			desconocido
						 * Titulo 		conocido
						 * Coleccion	conocida
						 * Numero		?
						 */
						if(null==tDTO.getNumero()) {
							/*
							 * ISBN			desconocido
							 * Titulo 		conocido
							 * Coleccion	conocida
							 * Numero		desconocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_TITULO_COLECCION);
							this.ps.setString(1, tDTO.getTitulo());
							this.ps.setString(2, tDTO.getColeccion());
						}else {
							/*
							 * ISBN			desconocido
							 * Titulo 		conocido
							 * Coleccion	conocida
							 * Numero		conocido
							 */
							this.ps = this.con.getConnect().prepareStatement(BUSCAR_TEBEO_TITULO_COLECCION_NUMERO);
							this.ps.setString(1, tDTO.getTitulo());	
							this.ps.setString(2, tDTO.getColeccion());
							this.ps.setInt(3, Integer.parseInt(tDTO.getNumero()));
						}
					}
				}
				//this.ps = this.con.getConnect().prepareStatement(Consultas.BUSCAR_PERSONAJES);
				this.resultSet = this.ps.executeQuery();
				while (resultSet.next()) {
					this.tebeo = new TebeoDTO();
					this.tebeo.setIsbn(resultSet.getString(1));
					this.tebeo.setTitulo(resultSet.getString(1));
					this.tebeo.setNumero(resultSet.getString(1));
					this.tebeo.setColeccion(resultSet.getString(1));

    				this.tebeos.add(this.tebeo);
				}
			}
			
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
	}
	
	
	public void modificarTebeo(TebeoDTO tDTO) {
		this.suceso = "El tebeo se ha modificado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(Consultas.BUSCAR_TEBEO_ISBN);
			this.ps.setInt(1, Integer.parseInt(tDTO.getIsbn()));
			this.resultSet = this.ps.executeQuery();
			if(this.resultSet.next()==true) {
				/*Titulo*/
				if(null!=tDTO.getTitulo()) {
					this.ps = this.con.getConnect().prepareStatement(Consultas.ACTUALIZAR_TEBEO_TITULO);
					this.ps.setString(1, tDTO.getTitulo());
					this.ps.setInt(2, Integer.parseInt(tDTO.getIsbn()));
					this.ps.executeUpdate();
				}
				/*Numero*/
				else if(null!=tDTO.getNumero()) {
					this.ps = this.con.getConnect().prepareStatement(Consultas.ACTUALIZAR_TEBEO_NUMERO);
					this.ps.setInt(1, Integer.parseInt(tDTO.getNumero()));
					this.ps.setInt(2, Integer.parseInt(tDTO.getIsbn()));
					this.ps.executeUpdate();
				}
				/*Coleccion*/
				else if(null!=tDTO.getColeccion()) {
					this.ps = this.con.getConnect().prepareStatement(Consultas.ACTUALIZAR_TEBEO_COLECCION);
					this.ps.setString(1, tDTO.getColeccion());
					this.ps.setInt(2, Integer.parseInt(tDTO.getIsbn()));
					this.ps.executeUpdate();
				}
	
			}else {
				this.suceso = "El tebeo no existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
		
	}

	public TebeoDTO getTebeo() {
		return tebeo;
	}

	public void setTebeo(TebeoDTO tebeo) {
		this.tebeo = tebeo;
	}

	public ArrayList<TebeoDTO> getTebeos() {
		return tebeos;
	}

	public void setTebeos(ArrayList<TebeoDTO> tebeos) {
		this.tebeos = tebeos;
	}

	public String getSuceso() {
		return suceso;
	}

	public void setSuceso(String suceso) {
		this.suceso = suceso;
	}
}
