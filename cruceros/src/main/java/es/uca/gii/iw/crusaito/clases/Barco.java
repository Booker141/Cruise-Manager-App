package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Barco 
{	
	@Id
	@GeneratedValue
	private long id;
	private String bNombre;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "barco")
	private Crucero crucero;
	
	private String bImagen;
	private int bAforoPasajeros;
	private int bAforoTripulantes;
	private int bPeso;
	private LocalDate bFchPuestaServicio;
	@Size(max= 12000)
	private String bDescripcion;
	private String bPlano;

	public Barco() {}
	
	/**
	 * Contructor de la entidad barco
	 * 
	 * @param bNombre - bNombre define el nombre del barco.
	 * @param bImagen - bImagen define una imagen del barco.
	 * @param bAforoPasajeros - bAforoPasajeros define el aforo disponible de pasajeros que posee el barco.
	 * @param bAforoTripulantes - bAforoTripulantes define el aforo disponibles para los tripulantes del barco.
	 * @param bPeso - bPeso define el peso que posee el barco.
	 * @param bFchPuestaServicio - bFchaPuestaServicio define la fecha en la que el barco empezó a estar disponible.
	 * @param bDescripcion - bDescripcion define una descripcion de las caracteristicas e historia del barco.
	 * @param bPlano - bPlano define una imagen de los planos de las instalaciones del barco.
	 */
	
	public Barco(String bNombre, String bImagen, int bAforoPasajeros, int bAforoTripulantes,
			int bPeso, LocalDate bFchPuestaServicio, String bDescripcion, String bPlano) {

		this.bNombre = bNombre;
		this.bImagen = bImagen;
		this.bAforoPasajeros = bAforoPasajeros;
		this.bAforoTripulantes = bAforoTripulantes;
		this.bPeso = bPeso;
		this.bFchPuestaServicio = bFchPuestaServicio;
		this.bDescripcion = bDescripcion;
		this.bPlano = bPlano;
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
	
	public int getbAforoPasajeros() {
		return bAforoPasajeros;
	}
	
	public void setbAforoPasajeros(int bAforoPasajeros) {
		this.bAforoPasajeros = bAforoPasajeros;
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

	public String getbDescripcion() {
		return bDescripcion;
	}

	public void setbDescripcion(String bDescripcion) {
		this.bDescripcion = bDescripcion;
	}
	
	@Override
    public Barco clone() throws CloneNotSupportedException {
        return (Barco) super.clone();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Crucero getCrucero() {
		return crucero;
	}

	public void setCrucero(Crucero crucero) {
		this.crucero = crucero;
	}

	public void setbPeso(int bPeso) {
		this.bPeso = bPeso;
	}

	public void setbFchPuestaServicio(LocalDate bFchPuestaServicio) {
		this.bFchPuestaServicio = bFchPuestaServicio;
	}

	public String getbPlano() {
		return bPlano;
	}

	public void setbPlano(String bPlano) {
		this.bPlano = bPlano;
	}

	@Override
	public String toString() {
		return this.bNombre;
	}
}