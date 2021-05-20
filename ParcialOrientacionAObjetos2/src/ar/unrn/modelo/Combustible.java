package ar.unrn.modelo;

public class Combustible {
	String nombre;

	public Combustible(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int calcularMontoTotal(int cantidadLitros) {
		return 1 * cantidadLitros;
	}

}
