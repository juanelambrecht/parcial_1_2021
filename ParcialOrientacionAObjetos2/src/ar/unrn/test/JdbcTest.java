package ar.unrn.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.unrn.modelo.RegistroCarga;
import ar.unrn.persistencia.JdbcRegistroCombustible;

class JdbcTest {

	@Test
	void registrarCargaJDBCTest() {
		JdbcRegistroCombustible DataBase = new JdbcRegistroCombustible();
		
		RegistroCarga registro = new RegistroCarga("Super", 15, "2021-05-08", "93");
		
		DataBase.registrarCargaCombustible(registro);
		
		assertTrue(DataBase.existeRegistroCombustible(registro));
	}

}
