package tsi.rp.s1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {
	public static Connection obtenerConexion() {
		String url = "jdbc:sqlserver://localhost:1433; databaseName=tsirp2022; "
		+ "TrustServerCertificate=true; username=sa; password=02101996o;";
//		cadena de configuración para acceder a la base de datos H2
//		String url = "jdbc:h2:tcp://localhost:9092/c:/eclipse/data/tsirp2022";
		Connection con = null;
//		se intenta utilizar el driver jdbc para obtener una conexión a la base de datos
		try {
			con = DriverManager.getConnection(url);
//			solo para h2 es necesario indicar la carga del driver jdbc
//			Class.forName("org.h2.Driver");
//			con = DriverManager.getConnection(url, "sa", "sa2022");
		} catch (SQLException e) {			
//			como se esta tratando de cargar un driver, se debe tener en cuenta la posibilidad de no encontrar el mismo
//		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error al obtener conexion a H2, " + e.getMessage());
		}
		return con;
	}
}