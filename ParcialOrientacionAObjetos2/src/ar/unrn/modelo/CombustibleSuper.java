package ar.unrn.modelo;

import java.time.LocalDate;

public class CombustibleSuper extends Combustible {

	public CombustibleSuper() {
		super("Super");
	}

	public int calcularMontoTotal(int cantidadLitros) {
		int descuentoDomingo = (90 * cantidadLitros) * 10 / 100;
		int descuentoSabado = (90 * cantidadLitros * 12) / 100;
		String diaDelMes = LocalDate.now().getDayOfWeek().toString();

		if (isDomingo(diaDelMes))
			return (90 * cantidadLitros) - descuentoDomingo;

		if (isSabadoAndCantidadLitros(diaDelMes, cantidadLitros))
			return (90 * cantidadLitros) - descuentoSabado;

		return 90 * cantidadLitros;
	}

	private boolean isDomingo(String fechaHoy) {
		return fechaHoy.equals("FRIDAY");
	}

	private boolean isSabadoAndCantidadLitros(String fechaHoy, int cantidadLitros) {
		return fechaHoy.equals("SATURDAY") && cantidadLitros >= 20;
	}
}
