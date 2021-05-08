package ar.unrn.modelo;

import java.time.LocalTime;

public class CombustibleComun extends Combustible{

	public CombustibleComun(String nombre, int precioPorLitro) {
		super(nombre, precioPorLitro);
		
	}

	public int calcularMontoTotal(int cantidadLitros) {
		LocalTime horaActual = LocalTime.now();
		
		int descuento = (this.precioPorLitro * cantidadLitros) * 5/100;
		
		if(seEncuentraEnRango(horaActual))
			return (this.precioPorLitro * cantidadLitros) - descuento;
			
		return this.precioPorLitro * cantidadLitros;
	}
	
	private boolean seEncuentraEnRango(LocalTime horaActual) {
		LocalTime ochoAM = LocalTime.of(8, 00, 00);
		LocalTime diezAM = LocalTime.of(10, 00, 00);
		
		return horaActual.isBefore(diezAM) && horaActual.isAfter(ochoAM);
		
	}
	
}
