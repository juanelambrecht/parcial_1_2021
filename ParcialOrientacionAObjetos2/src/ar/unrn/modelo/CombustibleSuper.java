package ar.unrn.modelo;

import java.time.LocalDate;

public class CombustibleSuper extends Combustible {

	public CombustibleSuper(String nombre, int precioPorLitro) {
		super(nombre, precioPorLitro);
	}

	public int calcularMontoTotal(int cantidadLitros) {
		int descuentoDomingo = (this.precioPorLitro * cantidadLitros) * 10 / 100;
		int descuentoSabado = (this.precioPorLitro * cantidadLitros * 12) / 100;
		String diaDelMes = LocalDate.now().getDayOfWeek().toString();

		if (isDomingo(diaDelMes))
			return (this.precioPorLitro * cantidadLitros) - descuentoDomingo;

		if (isSabadoAndCantidadLitros(diaDelMes, cantidadLitros))
			return (this.precioPorLitro * cantidadLitros) - descuentoSabado;

		return this.precioPorLitro * cantidadLitros;
	}

	private boolean isDomingo(String fechaHoy) {
		return fechaHoy.equals("FRIDAY");
	}

	private boolean isSabadoAndCantidadLitros(String fechaHoy, int cantidadLitros) {
		return fechaHoy.equals("SATURDAY") && cantidadLitros >= 20;
	}
}
