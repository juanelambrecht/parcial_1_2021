package ar.unrn.modelo;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioCombustible {

	List<String> obtenerTiposCombustibles();

	void registrarCargaCombustible(RegistroCarga registro);

	List<RegistroCarga> devolverRegistroDeVentas(String fechaInicio, String fechaFin);

	boolean existeRegistroCombustible(RegistroCarga registroCarga);
}
