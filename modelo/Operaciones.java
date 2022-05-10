package tsi.rp.s1.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Operaciones {
	/**
	 * Método que permite listar todos los datos de la tabla personas y almacenarlos en un ArrayList de tipo Persona
	 * @return ArrayList<Persona>
	 */
	public static ArrayList<Persona> listarPersonas() {
		String consulta = "select idP, nroCi, nombre, apellido, email, fechaNac from personas";
		ArrayList<Persona> lp = new ArrayList<Persona>();
		Persona p = null;
		try (
				Connection con = BDConexion.obtenerConexion();
				Statement sent = con.createStatement();
				ResultSet rs = sent.executeQuery(consulta);
			){
			while(rs.next()) {
				p = new Persona(rs.getInt("idP"), rs.getInt("nroCi"), 
						rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("fechaNac"));
				lp.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar los datos de Persona, " + e.getMessage());
		}
		return lp;
	}
	
	/**
	 * Método que permite insertar una nueva Persona en la BD, utilizando sentencias preparadas
	 * @param p Persona
	 * @return resultado boolean
	 */
	public static boolean insertarPersona(Persona p) {
//		consulta sql utilizando comodines permite evitar SQL Injection
//		los comodines corresponden a las columnas de la BD y se asocian con el tipo de dato
		String consulta = "insert into personas values(?,?,?,?,?)";
//		consulta para H2
//		String consulta = "insert into personas values(default,?,?,?,?,?)";
//		para controlar el resultado de la consulta
		boolean resultado = false;
		try (
				Connection con = BDConexion.obtenerConexion();
//				creación de una sentencia preparada en base a la consulta con comodines
				PreparedStatement sentPrep = con.prepareStatement(consulta);
			){
//			asignación de los comodines en correspondencia con su posición en la consulta, el tipo de dato
//			de cada columna y el objeto que representa el dato a insertar
			sentPrep.setInt(1, p.getNroCi());
			sentPrep.setString(2, p.getNombre());
			sentPrep.setString(3, p.getApellido());
			sentPrep.setString(4, p.getEmail());
			sentPrep.setString(5, p.getFechaNac());
			
//			ejecución de la sentencia y verificación de la cantidad de filas afectadas
			if(sentPrep.executeUpdate() == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al insertar nueva Persona, " + e.getMessage());
		}
		return resultado;
	}
	
	/**
	 * Método que permite buscar una Persona mediante su nroCi
	 * @param ci Integer
	 * @return p Persona
	 */
	public static Persona buscarPersonaPorCi(Integer ci) {
		String consulta = "select * from personas where nroCi=?";
		Persona p = null;
		try (
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPrep = con.prepareStatement(consulta);
			){
			sentPrep.setInt(1, ci);
			ResultSet rs = sentPrep.executeQuery();
			if(rs.next()) {
				p = new Persona(rs.getInt("idP"), rs.getInt("nroCi"), 
						rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("fechaNac"));
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar una Persona por nroCi, " + e.getMessage());
		}
		return p;
	}

	/**
	 * Método que permite editar los datos de una Persona mediante su nroCi
	 * @param p Persona
	 * @return resultado boolean
	 */
	public static boolean editarPersona(Persona p) {
		String consulta = "update personas set nombre=?, apellido=?, email=?, fechaNac=? where nroCi=?";
		boolean resultado = false;
		try (
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPrep = con.prepareStatement(consulta);
			){
			sentPrep.setString(1, p.getNombre());
			sentPrep.setString(2, p.getApellido());
			sentPrep.setString(3, p.getEmail());
			sentPrep.setString(4, p.getFechaNac());
			sentPrep.setInt(5, p.getNroCi());
			
			if(sentPrep.executeUpdate() == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al editar una Persona por su nroCi, " + e.getMessage());
		}
		return resultado;
	}
	
	/**
	 * Método que permite eliminar los datos de un Persona mediante su nroCi
	 * @param ci Integer
	 * @return resultado boolean
	 */
	public static boolean eliminarPersona(Integer ci) {
		String consulta = "delete from personas where nroCi=?";
		boolean resultado = false;
		try (
				Connection con = BDConexion.obtenerConexion();
				PreparedStatement sentPrep = con.prepareStatement(consulta);
			){
			sentPrep.setInt(1, ci);
			
			if(sentPrep.executeUpdate() == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al eliminar una Persona por su nroCi, " + e.getMessage());
		}
		return resultado;
	}
}
