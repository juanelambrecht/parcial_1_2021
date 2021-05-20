package ar.unrn.main;

import ar.unrn.modelo.EstacionDeServicio;
import ar.unrn.persistencia.DiscoRegistroCombustible;
import ar.unrn.ui.PantallaPrincipal;

public class MainPersistenciaDisco {

	public static void main(String[] args) {

		String nombreArchivoTipoNafta = "Carga combustible.txt";
		String nombreArchivoRegistro = "RegistroCargaNafta.txt";

		new PantallaPrincipal(new EstacionDeServicio(new DiscoRegistroCombustible(nombreArchivoTipoNafta, nombreArchivoRegistro)))
				.setVisible(true);
	}

}
