package ar.unrn.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import ar.unrn.modelo.Combustible;
import ar.unrn.modelo.CombustibleComun;
import ar.unrn.modelo.CombustibleSuper;

class NaftaTest {

	String diaHoy = LocalDate.now().getDayOfWeek().toString();

	@Test
	void calcularMontoTotalSuperDiaNormaltest() {
		String nombreNafta = "Super";
		int precioPorLitro = 90;
		Combustible combustible = new CombustibleSuper(nombreNafta, precioPorLitro);

		if (!diaHoy.equals("FRIDAY") && diaHoy.equals("SATURDAY"))

			assertEquals(combustible.calcularMontoTotal(10), 900);
	}

	@Test
	void calcularMontoTotalSuperDomingotest() {
		String nombreNafta = "Super";
		int precioPorLitro = 90;
		Combustible combustible = new CombustibleSuper(nombreNafta, precioPorLitro);

		if (diaHoy.equals("FRIDAY"))
			assertEquals(combustible.calcularMontoTotal(10), 810);

	}

	@Test
	void calcularMontoTotalSuperSabadotest() {
		String nombreNafta = "Super";
		int precioPorLitro = 90;
		Combustible combustible = new CombustibleSuper(nombreNafta, precioPorLitro);

		if (diaHoy.equals("SATURDAY"))
			assertEquals(combustible.calcularMontoTotal(20), 1584);

	}

	@Test
	void calcularMontoTotalComuntest() {
		String nombreNafta = "Comun";
		int precioPorLitro = 70;
		Combustible combustible = new CombustibleComun(nombreNafta, precioPorLitro);

		assertEquals(combustible.calcularMontoTotal(10), 700);

	}

	@Test
	void calcularMontoTotalComunEnRangotest() {
		String nombreNafta = "Comun";
		int precioPorLitro = 70;
		Combustible combustible = new CombustibleComun(nombreNafta, precioPorLitro);

		if (seEncuentraEnRango(LocalTime.now()))
			assertEquals(combustible.calcularMontoTotal(10), 665);

	}

	public boolean seEncuentraEnRango(LocalTime horaActual) {
		LocalTime ochoAM = LocalTime.of(8, 00, 00);
		LocalTime diezAM = LocalTime.of(10, 00, 00);

		return horaActual.isBefore(diezAM) && horaActual.isAfter(ochoAM);

	}
}
