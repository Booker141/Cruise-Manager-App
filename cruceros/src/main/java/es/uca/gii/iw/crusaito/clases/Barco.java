package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Barco 
{	
	@Id
	@GeneratedValue
	private long id;
	private String bNombre;
	private String bCodCamarote;
	private int bAforoPasajeros;
	private int bAforoTripulantes;
	private int bPeso;
	private LocalDate bFchPuestaServicio;
	private String bDescripcion;


	public Barco(String bNombre, String bCodCamarote, int bAforoPasajeros, int bAforoTripulantes,
			int bPeso, LocalDate bFchPuestaServicio, String bDescripcion) {
		
		this.bNombre = bNombre;
		this.bCodCamarote = bCodCamarote;
		this.bAforoPasajeros = bAforoPasajeros;
		this.bAforoTripulantes = bAforoTripulantes;
		this.bPeso = bPeso;
		this.bFchPuestaServicio = bFchPuestaServicio;
		this.bDescripcion = bDescripcion;
	}
	
	public String getbNombre() {
		return bNombre;
	}

	public void setbNombre(String bNombre) {
		this.bNombre = bNombre;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barco other = (Barco) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getbDescripcion() {
		return bDescripcion;
	}

	public void setbDescripcion(String bDescripcion) {
		this.bDescripcion = bDescripcion;
	}
}