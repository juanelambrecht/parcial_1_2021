package ar.unrn.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.modelo.RegistroCarga;
import ar.unrn.modelo.RepositorioCombustible;

public class JdbcRegistroCombustible implements RepositorioCombustible {

	@Override
	public List<String> obtenerTiposCombustibles() throws RuntimeException {
		List<String> listadoTiposNafta = new ArrayList<String>();

		Connection conexion = ConexionBaseDeDatos.getConection();
		try {

			Statement sent = conexion.createStatement();

			ResultSet resul = sent.executeQuery("SELECT * FROM `tiposnafta`");

			while (resul.next()) {
				String nombreTipoNafta = resul.getString("nombre");
				listadoTiposNafta.add(nombreTipoNafta);

			}

			conexion.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listadoTiposNafta;

	}

	@Override
	public void registrarCargaCombustible(RegistroCarga registro) {
		Connection conexion = null;
		try {
			conexion = ConexionBaseDeDatos.getConection();
			conexion.setAutoCommit(false);
			Statement sent = conexion.createStatement();

			sent.executeUpdate(
					"INSERT INTO `registro`(`fecha_carga`, `cantidad_litros`, `monto`,`nombre_tipo_nafta`) VALUES (CURRENT_TIMESTAMP(),'"
							+ registro.CantidadLitros() + "','" + registro.MontoTotal() + "','"
							+ registro.NombreCombustible() + "')");

			conexion.commit();
			conexion.close();

		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException();
			}
			throw new RuntimeException();
		}
	}

	@Override
	public List<RegistroCarga> devolverRegistroDeVentas(String fechaInicio, String fechaFin) {
		List<RegistroCarga> listadoRegistroNafta = new ArrayList<RegistroCarga>();

		Connection conexion = ConexionBaseDeDatos.getConection();
		try {

			Statement sent = conexion.createStatement();

			ResultSet resul = sent.executeQuery("SELECT * FROM `registro` WHERE `fecha_carga` BETWEEN DATE_FORMAT('"
					+ fechaInicio + "', '%Y-%m-%d') AND DATE_FORMAT('" + fechaFin + "', '%Y-%m-%d')");

			while (resul.next()) {
				
				int montoTotal = Integer.parseInt(resul.getString("monto"));
				
				listadoRegistroNafta.add(new RegistroCarga(resul.getString("nombre_tipo_nafta"),
						resul.getInt("cantidad_litros"), resul.getString("fecha_carga"),montoTotal ));

			}

			conexion.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listadoRegistroNafta;

	}

	@Override
	public boolean existeRegistroCombustible(RegistroCarga registroCarga) {
		Connection conexion = ConexionBaseDeDatos.getConection();
		try {

			Statement sent = conexion.createStatement();

			ResultSet resul = sent.executeQuery(
					"SELECT * FROM `registro` WHERE `nombre_tipo_nafta` = '" + registroCarga.NombreCombustible()
							+ "' AND `monto` = '" + registroCarga.MontoTotal() + "'");

			if (resul.next()) {
				return true;

			}

			conexion.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

}
