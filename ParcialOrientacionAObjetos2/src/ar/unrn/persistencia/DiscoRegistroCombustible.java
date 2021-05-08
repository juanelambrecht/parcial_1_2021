package ar.unrn.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.modelo.RegistroCarga;
import ar.unrn.modelo.RepositorioCombustible;

public class DiscoRegistroCombustible implements RepositorioCombustible{
	String nombreArchivoTipoNafta;
	String nombreArchivoRegistro;
	List<RegistroCarga> listadoRegistroNafta = new ArrayList<RegistroCarga>();
	
	public DiscoRegistroCombustible(String nombreArchivoTipoNafta,String nombreArchivoRegistro) {
		this.nombreArchivoTipoNafta = nombreArchivoTipoNafta;
		this.nombreArchivoRegistro = nombreArchivoRegistro;
		this.cargarArchivoTipoNafta();
	}
	
	private void cargarArchivoTipoNafta() {
		String nombreFichero = this.nombreArchivoTipoNafta;

		ArrayList<String> tipoCombustible = new ArrayList<String>();
		
		tipoCombustible.add("Super");
		tipoCombustible.add("Comun");
		
		
		try {
			ObjectOutputStream objetoSalida;
			objetoSalida = new ObjectOutputStream(new FileOutputStream(nombreFichero));

			objetoSalida.writeObject(tipoCombustible);

			objetoSalida.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<String> obtenerTiposCombustibles()  throws RuntimeException{
		String nombreFichero = this.nombreArchivoTipoNafta;
		ArrayList<String> tipoNafta = new ArrayList<String>();
		try {
			ObjectInputStream entrada;
			entrada = new ObjectInputStream(new FileInputStream(nombreFichero));
			tipoNafta = (ArrayList<String>) entrada.readObject();

			entrada.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return tipoNafta;
	}

	@Override
	public void registrarCargaCombustible(RegistroCarga registro)  throws RuntimeException{
		String nombreFichero = this.nombreArchivoRegistro;
		listadoRegistroNafta.add(registro);
		try {
			ObjectOutputStream objetoSalida;
			objetoSalida = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			
			objetoSalida.writeObject(listadoRegistroNafta);

			objetoSalida.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<RegistroCarga> devolverRegistroDeVentas(String fechaInicio, String fechaFin)
			throws RuntimeException {
		
		String nombreFichero = this.nombreArchivoRegistro;
		List<RegistroCarga> listadoRegistroNafta = new ArrayList<RegistroCarga>();
		
		List<RegistroCarga> listadoRegistroNaftaRetorno = new ArrayList<RegistroCarga>();
		try {
			FileInputStream file = new FileInputStream(nombreFichero);
			ObjectInputStream entrada;
			entrada = new ObjectInputStream(file);
			
			listadoRegistroNafta = (ArrayList<RegistroCarga>) entrada.readObject();
			
			for(RegistroCarga registro : listadoRegistroNafta) {
				if(verificarRangoFecha(fechaInicio, fechaFin, registro.obtenerFechaCarga()))
					listadoRegistroNaftaRetorno.add(registro);
			}
			entrada.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		}

		

		return listadoRegistroNaftaRetorno;
	}

	private boolean verificarRangoFecha(String fechaInicio,String fechaFin,String FechaRegistro) {
		if(fechaInicio.equals(FechaRegistro) || fechaFin.equals(FechaRegistro))
			return true;
		if(LocalDate.parse(FechaRegistro).isBefore(LocalDate.parse(fechaFin)) && LocalDate.parse(FechaRegistro).isAfter(LocalDate.parse(fechaInicio)))
			return true;
		
		return false;
	}
	@Override
	public boolean existeRegistroCombustible(RegistroCarga registroCarga) {
		for(RegistroCarga registro: listadoRegistroNafta) {
			if(registro.equals(registroCarga))
				return true;
		}
		return false;
	}


}
