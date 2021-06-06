package ar.unrn.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ar.unrn.modelo.RegistroCarga;
import ar.unrn.persistencia.JdbcRegistroCombustible;

class JdbcTest {

	@Test
	void registrarCargaJDBCTest() {
		JdbcRegistroCombustible DataBase = new JdbcRegistroCombustible();

		RegistroCarga registro = new RegistroCarga("Super", 15, LocalDate.now().toString(), 93);

		DataBase.registrarCargaCombustible(registro);

		assertTrue(DataBase.existeRegistroCombustible(registro));
	}

}
