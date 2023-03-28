package tebeoteca.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tebeoteca.modelo.Consultas;
import tebeoteca.modelo.conexion.Conectar;
import tebeoteca.modelo.dto.RealizaDTO;

/**
 * U09A01-EjemploTebeoteca
 * 
 * @author Raul Rubio
 *
 * @version 1.0
 *
 */

public class RealizaDAO implements Consultas{

	private RealizaDTO realiza = new RealizaDTO();
	private PreparedStatement ps = null;
	private Conectar con = null;
	private ArrayList<RealizaDTO> realizan = new ArrayList<RealizaDTO>();
	private ResultSet resultSet = null;
	private String suceso;
	
	public void crearRealiza(RealizaDTO rDTO) {
		this.suceso = "La consulta se ha realizado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZA_TEBEO_AUTOR);
			this.ps.setInt(1, Integer.parseInt(rDTO.getTebeo()));
			this.ps.setInt(2, Integer.parseInt(rDTO.getAutor()));
			this.resultSet = this.ps.executeQuery();
			//this.resultSet = this.ps.getResultSet();
			if(this.resultSet.next()==false) {
				this.ps = this.con.getConnect().prepareStatement(INSERTAR_REALIZA);
				this.ps.setInt(1, Integer.parseInt(rDTO.getTebeo()));
				this.ps.setInt(2, Integer.parseInt(rDTO.getAutor()));

				this.ps.executeUpdate();
				
			}else {
				this.suceso = "El Realiza ya existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
		
	}
	
	public void eliminarRealiza(RealizaDTO rDTO) {
		this.suceso = "El Realiza se ha eliminado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZA_TEBEO_AUTOR);
			this.ps.setInt(1, Integer.parseInt(rDTO.getTebeo()));
			this.ps.setInt(2, Integer.parseInt(rDTO.getAutor()));
			this.resultSet = this.ps.executeQuery();
			//this.resultSet = this.ps.getResultSet();
			if(this.resultSet.next()==true) {
				this.ps = this.con.getConnect().prepareStatement(BORRAR_REALIZA);
				this.ps.setInt(1, Integer.parseInt(rDTO.getTebeo()));
				this.ps.setInt(2, Integer.parseInt(rDTO.getAutor()));				
				this.ps.executeUpdate();
				
			}else {
				this.suceso = "El Realiza no existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
	}
	
	public void buscarRealiza(RealizaDTO rDTO) {
		this.suceso = "La consulta se ha realizado con éxito";
		this.realizan.clear();
		this.con = new Conectar();
		try {
			if(rDTO.getTebeo()!=null && rDTO.getAutor()!=null) {
				this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZA_TEBEO_AUTOR);
				this.ps.setInt(1, Integer.parseInt(rDTO.getTebeo()));
				this.ps.setInt(2, Integer.parseInt(rDTO.getAutor()));
				this.resultSet = this.ps.executeQuery();
				if(this.resultSet.next()==true) {
					this.setRealiza(new RealizaDTO());
					this.realiza.setTebeo(resultSet.getString(1));
					this.realiza.setAutor(resultSet.getString(2));
				}else {
                    this.suceso = "El Realiza no existe";
                    this.getRealiza().setTebeo("");
                    this.getRealiza().setAutor("");
                }
				this.realizan.add(this.getRealiza());
			}
			else {
				if(null==rDTO.getTebeo()) {
					if(null==rDTO.getAutor()) {
						/*
						 * Tebeo		desconocido
						 * Autor 		desconocido
						 */
						this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZAN);	

					}else {
						/*
						 * Tebeo		desconocido
						 * Autor 		conocido
						 */
						this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZA_AUTOR);	
						this.ps.setString(1, rDTO.getAutor());
					}
				}else {
					if(null==rDTO.getAutor()) {
						/*
						 * Tebeo		conocido
						 * Autor 		desconocido
						 */
						this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZA_TEBEO);	
						this.ps.setString(1, rDTO.getTebeo());

					}else {
						/*
						 * Tebeo		conocido
						 * Autor 		conocido
						 */
						this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZA_TEBEO_AUTOR);	
						this.ps.setString(1, rDTO.getTebeo());
						this.ps.setString(2, rDTO.getAutor());
					}
				}
				//this.ps = this.con.getConnect().prepareStatement(Consultas.BUSCAR_PERSONAJES);
				this.resultSet = this.ps.executeQuery();
				while (resultSet.next()) {
					this.realiza = new RealizaDTO();
					this.realiza.setTebeo(resultSet.getString(1));
					this.realiza.setAutor(resultSet.getString(2));

    				this.realizan.add(this.realiza);
				}
			}
			
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
	}
	
	
	public void modificarRealiza(RealizaDTO viejo_rDTO, RealizaDTO nuevo_rDTO) {
		this.suceso = "El Realiza se ha modificado con éxito";
		this.con = new Conectar();
		try {
			this.ps = this.con.getConnect().prepareStatement(BUSCAR_REALIZA_TEBEO_AUTOR);
			this.ps.setInt(1, Integer.parseInt(viejo_rDTO.getTebeo()));
			this.ps.setInt(2, Integer.parseInt(viejo_rDTO.getAutor()));
			this.resultSet = this.ps.executeQuery();
			if(this.resultSet.next()==true) {
				/*Tebeo*/
				if(null!=nuevo_rDTO.getTebeo()) {
					this.ps = this.con.getConnect().prepareStatement(ACTUALIZAR_REALIZA_TEBEO);
					this.ps.setInt(1, Integer.parseInt(nuevo_rDTO.getTebeo()));
					this.ps.setInt(2, Integer.parseInt(viejo_rDTO.getTebeo()));
					this.ps.setInt(3, Integer.parseInt(viejo_rDTO.getAutor()));
					this.ps.executeUpdate();
				}
				/*Autor*/
				else if(null!=nuevo_rDTO.getAutor()) {
					this.ps = this.con.getConnect().prepareStatement(ACTUALIZAR_REALIZA_AUTOR);
					this.ps.setInt(1, Integer.parseInt(nuevo_rDTO.getAutor()));
					this.ps.setInt(2, Integer.parseInt(viejo_rDTO.getTebeo()));
					this.ps.setInt(3, Integer.parseInt(viejo_rDTO.getAutor()));
					this.ps.executeUpdate();
				}
	
			}else {
				this.suceso = "El Realiza no existe";
			}
		}catch(Exception e) {
			this.suceso = "Error: "+e;
		}finally {
			con.cerrarConexion(this.resultSet, this.con.getConnect(), this.ps);
		}
		
	}

	public RealizaDTO getRealiza() {
		return realiza;
	}

	public void setRealiza(RealizaDTO realiza) {
		this.realiza = realiza;
	}

	public ArrayList<RealizaDTO> getRealizan() {
		return realizan;
	}

	public void setRealizan(ArrayList<RealizaDTO> realizan) {
		this.realizan = realizan;
	}

	public String getSuceso() {
		return suceso;
	}

	public void setSuceso(String suceso) {
		this.suceso = suceso;
	}
}
