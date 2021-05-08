package ar.unrn.modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistroCarga implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombreNafta,montoTotal;
	int cantidadLitros;
	String fechaCarga;
	
	
	public RegistroCarga(String nombreCombustible, int cantidadLitros, String fechaCarga,String montoTotal) {
		if(nombreCombustible.isEmpty()) {
			throw new RuntimeException("El nombre no esta definido");
		}
		if(fechaCarga.isEmpty()) {
			throw new RuntimeException("No se definio una fecha de carga");
		}
		if(montoTotal.isEmpty()) {
			throw new RuntimeException("El monto total no esta definido");
		}
		
		this.nombreNafta = nombreCombustible;
		this.cantidadLitros = cantidadLitros;
		this.fechaCarga = fechaCarga;
		this.montoTotal = montoTotal;
	}

	public String obtenerNombreCombustible() {
		return nombreNafta;
	}

	public int obtenerCantidadLitros() {
		return cantidadLitros;
	}

	public String obtenerFechaCarga() {
		return fechaCarga;
	}

	public String obtenerMontoTotal() {
		return montoTotal;
	}

	@Override
	public String toString() {
		return "RegistroCarga [nombreNafta=" + nombreNafta + ", montoTotal=" + montoTotal + ", cantidadLitros="
				+ cantidadLitros + ", fechaCarga=" + fechaCarga + "]";
	}
	
	
	
}
