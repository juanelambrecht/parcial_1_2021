package ar.unrn.modelo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstacionDeServicio {
	private RepositorioCombustible repositorio;
	private Map<String, Combustible> tipoCombustible = new HashMap<String, Combustible>();

	public EstacionDeServicio(RepositorioCombustible repositorio) {
		super();
		this.repositorio = repositorio;
		// llave super y valor combustibleSuper
		// Con el metodo put() agrego un item al HashMap.
		this.tipoCombustible.put("Super", new CombustibleSuper());
		this.tipoCombustible.put("Comun", new CombustibleComun());
	}

	public int montoTotalCombustible(int cantidadLitros, String tipoDeCombustible) {

		if (esLitroValido(cantidadLitros)) {
			throw new RuntimeException("La cantidad de litros debe ser mayor a 0");
		}
		if (esCombustibleValido(tipoDeCombustible)) {
			throw new RuntimeException("El tipo de combustible se encuentra vacio");
		}
		// obtengo del map el combustible - segun la key
		Combustible combustible = tipoCombustible.get(tipoDeCombustible);

		return combustible.calcularMontoTotal(cantidadLitros);
	}

	public void realizarVenta(int cantidadLitros, String tipoDeCombustible) {
		
		int precioTotal = this.montoTotalCombustible(cantidadLitros, tipoDeCombustible);

		String hoy = LocalDate.now().toString();

		RegistroCarga registro = new RegistroCarga(tipoDeCombustible, cantidadLitros, hoy, precioTotal);
		
		repositorio.registrarCargaCombustible(registro);
	}

	public List<String> obtenerTiposCombustibles() {
		return this.repositorio.obtenerTiposCombustibles();
	}

	public List<RegistroCarga> RegistrosDeVentas(String fechaInicio, String fechaFin) {
		return this.repositorio.devolverRegistroDeVentas(fechaInicio, fechaFin);
	}

	private boolean esLitroValido(int cantidadLitros) {
		System.out.println(cantidadLitros);
		return cantidadLitros < 1;
	}

	public boolean esCombustibleValido(String tipoCombustible) {
		return tipoCombustible.equals("");
	}
}
