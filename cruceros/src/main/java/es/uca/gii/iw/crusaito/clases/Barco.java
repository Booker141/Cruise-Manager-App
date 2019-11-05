package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;

public class Barco 
{

	private String bNombre;
	private String bCodigo;
	private String bCodCamarote;
	private int bAforoPasajeros;
	private int bAforoTripulantes;
	private int bPeso;
	private LocalDate bFchPuestaServicio;
	private String bOrigen;
	private String bDestino;

	public Barco(String bNombre, String bCodigo, String bCodCamarote, int bAforoPasajeros, int bAforoTripulantes,
			int bPeso, LocalDate bFchPuestaServicio, String bOrigen, String bDestino) {
		
		this.bNombre = bNombre;
		this.bCodigo = bCodigo;
		this.bCodCamarote = bCodCamarote;
		this.bAforoPasajeros = bAforoPasajeros;
		this.bAforoTripulantes = bAforoTripulantes;
		this.bPeso = bPeso;
		this.bFchPuestaServicio = bFchPuestaServicio;
		this.bOrigen = bOrigen;
		this.bDestino = bDestino;
	}
	
	public String getbNombre() {
		return bNombre;
	}

	public void setbNombre(String bNombre) {
		this.bNombre = bNombre;
	}

	public String getbCodigo() {
		return bCodigo;
	}

	public String getbCodCamarote() {
		return bCodCamarote;
	}

	public int getbAforoPasajeros() {
		return bAforoPasajeros;
	}

	public int getbAforoTripulantes() {
		return bAforoTripulantes;
	}

	public void setbAforoTripulantes(int bAforoTripulantes) {
		this.bAforoTripulantes = bAforoTripulantes;
	}

	public int getbPeso() {
		return bPeso;
	}

	public LocalDate getbFchPuestaServicio() {
		return bFchPuestaServicio;
	}

	public String getbOrigen() {
		return bOrigen;
	}

	public void setbOrigen(String bOrigen) {
		this.bOrigen = bOrigen;
	}

	public String getbDestino() {
		return bDestino;
	}

	public void setbDestino(String bDestino) {
		this.bDestino = bDestino;
	}

}