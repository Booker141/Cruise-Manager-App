package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Barco 
{	
	@Id
	@GeneratedValue
	private long id;
	private String bNombre;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "cBarco")
	private List<Camarote> bCamarotes;
	private String bImagen;
	private int bAforoPasajeros;
	private int bAforoTripulantes;
	private int bPeso;
	private LocalDate bFchPuestaServicio;
	private String bDescripcion;


	protected Barco() {}
	
	//Añadir Lista de camarotes al constructor
	public Barco(String bNombre, String bImagen, int bAforoPasajeros, int bAforoTripulantes,
			int bPeso, LocalDate bFchPuestaServicio, String bDescripcion) {

		this.bNombre = bNombre;
		this.bImagen = bImagen;
		this.bAforoPasajeros = bAforoPasajeros;
		this.bAforoTripulantes = bAforoTripulantes;
		this.bPeso = bPeso;
		this.bFchPuestaServicio = bFchPuestaServicio;
		this.bDescripcion = bDescripcion;
	}
	
	public String getbImagen() {
		return bImagen;
	}
	
	public void setbImagen(String bImagen) {
		this.bImagen = bImagen;
	}
	public String getbNombre() {
		return bNombre;
	}

	public void setbNombre(String bNombre) {
		this.bNombre = bNombre;
	}

	public List<Camarote> getbCamarotes() {
		return bCamarotes;
	}
	public void setbCamarotes(List<Camarote> bCamarotes) {
		this.bCamarotes = bCamarotes;
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