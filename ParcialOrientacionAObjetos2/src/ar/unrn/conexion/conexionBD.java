package ar.unrn.conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class conexionBD {

	public static void main(String[] args) {

	}

	public static Connection getConection() throws RuntimeException {

		Connection conexion = null;
		Properties propiedades = new Properties();

		InputStream entrada = null;

		try {
			entrada = new FileInputStream("datos.properties");

			propiedades.load(entrada);

			Class.forName(propiedades.getProperty("CONTROLADOR"));
			conexion = DriverManager.getConnection(propiedades.getProperty("URL"), propiedades.getProperty("USUARIO"),
					propiedades.getProperty("CONTRASE�A"));

			return conexion;

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return conexion;
	}
}
