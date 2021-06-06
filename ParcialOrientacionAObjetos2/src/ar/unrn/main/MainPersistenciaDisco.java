package ar.unrn.main;

import java.util.List;

import ar.unrn.modelo.EstacionDeServicio;
import ar.unrn.persistencia.DiscoRegistroCombustible;
import ar.unrn.ui.EmailMonitorDeCarga;
import ar.unrn.ui.PantallaPrincipal;

public class MainPersistenciaDisco {

	public static void main(String[] args) {

		String nombreArchivoTipoNafta = "Carga combustible.txt";
		String nombreArchivoRegistro = "RegistroCargaNafta.txt";
		
		EstacionDeServicio estacion = new EstacionDeServicio(new DiscoRegistroCombustible(nombreArchivoTipoNafta, nombreArchivoRegistro),List.of(new EmailMonitorDeCarga()));
		
		new PantallaPrincipal(estacion, List.of(new EmailMonitorDeCarga())).setVisible(true);
		//new PantallaPrincipal(new EstacionDeServicio(new DiscoRegistroCombustible(nombreArchivoTipoNafta, nombreArchivoRegistro)))
			//	.setVisible(true);
	}

}
