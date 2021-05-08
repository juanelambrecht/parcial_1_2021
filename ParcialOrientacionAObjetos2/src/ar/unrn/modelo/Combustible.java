package ar.unrn.modelo;

public class Combustible {
	String nombre;
	int precioPorLitro;
	
	public Combustible(String nombre, int precioPorLitro) {
		super();
		this.nombre = nombre;
		this.precioPorLitro = precioPorLitro;
	}
	
	public int calcularMontoTotal(int cantidadLitros) {
		return this.precioPorLitro * cantidadLitros;
	}
	
}
