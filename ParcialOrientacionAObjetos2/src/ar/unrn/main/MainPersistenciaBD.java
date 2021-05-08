package ar.unrn.main;

import ar.unrn.persistencia.JdbcRegistroCombustible;
import ar.unrn.ui.PantallaPrincipal;

public class MainPersistenciaBD {

	public static void main(String[] args) {

		new PantallaPrincipal(new JdbcRegistroCombustible()).setVisible(true);
	}
}
