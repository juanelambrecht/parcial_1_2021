package ar.unrn.modelo;

import java.time.LocalTime;

public class CombustibleComun extends Combustible {

	public CombustibleComun() {
		super("Comun");

	}

	public int calcularMontoTotal(int cantidadLitros) {
		LocalTime horaActual = LocalTime.now();

		int descuento = (70 * cantidadLitros) * 5 / 100;

		if (seEncuentraEnRango(horaActual))
			return (70 * cantidadLitros) - descuento;

		return 70 * cantidadLitros;
	}

	private boolean seEncuentraEnRango(LocalTime horaActual) {
		LocalTime ochoAM = LocalTime.of(8, 00, 00);
		LocalTime diezAM = LocalTime.of(10, 00, 00);

		return horaActual.isBefore(diezAM) && horaActual.isAfter(ochoAM);

	}

}
