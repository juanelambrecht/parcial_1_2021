package ar.unrn.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ar.unrn.modelo.RegistroCarga;
import ar.unrn.persistencia.DiscoRegistroCombustible;
import ar.unrn.ui.PantallaPrincipal;

class DiscoTest {

	@Test
	void registrarCargaDiscoTest() {
		String nombreArchivoTipoNafta = "Carga combustible.txt";
		String nombreArchivoRegistro = "RegistroCargaNafta.txt";

		DiscoRegistroCombustible archivoCargaNafta = new DiscoRegistroCombustible(nombreArchivoTipoNafta,
				nombreArchivoRegistro);
		RegistroCarga registro = new RegistroCarga("Super", 15,LocalDate.now().toString(), 90);

		archivoCargaNafta.registrarCargaCombustible(registro);

		assertTrue(archivoCargaNafta.existeRegistroCombustible(registro));

	}

}
