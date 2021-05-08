package ar.unrn.modelo;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioCombustible {

	List<String> obtenerTiposCombustibles() throws RuntimeException;

	void registrarCargaCombustible(RegistroCarga registro) throws RuntimeException;

	List<RegistroCarga> devolverRegistroDeVentas(String fechaInicio, String fechaFin) throws RuntimeException;

	boolean existeRegistroCombustible(RegistroCarga registroCarga);
}
