package es.uca.gii.iw.crusaito.clases;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Crucero {
	
	public Crucero() {}
	
	@Id
	@GeneratedValue
	private Long id;
	private String cNombre;
	private String cOrigen;
	private String cDestino;
	private String cDuracion; //en dias
	private String cDescripcion;
	private String cImagen;
	private double cPrecio;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "cCrucero")
	private List<Camarote> camarotes;
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Ciudad> ciudades;
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "crucero")
	private List<Reserva> reservas;
	*/

	public Crucero(String cNombre, String cOrigen, String cDestino, String cDuracion, String cDescripcion,
			double cPrecio) {

		this.cNombre = cNombre;
		this.cOrigen = cOrigen;
		this.cDestino = cDestino;
		this.cDuracion = cDuracion;
		this.cDescripcion = cDescripcion;
		this.cPrecio = cPrecio;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getcNombre() {
		return cNombre;
	}


	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}


	public String getcOrigen() {
		return cOrigen;
	}


	public void setcOrigen(String cOrigen) {
		this.cOrigen = cOrigen;
	}


	public String getcDestino() {
		return cDestino;
	}


	public void setcDestino(String cDestino) {
		this.cDestino = cDestino;
	}


	public String getcDuracion() {
		return cDuracion;
	}


	public void setcDuracion(String cDuracion) {
		this.cDuracion = cDuracion;
	}


	public String getcDescripcion() {
		return cDescripcion;
	}


	public void setcDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
	}


	public double getcPrecio() {
		return cPrecio;
	}


	public void setcPrecio(double cPrecio) {
		this.cPrecio = cPrecio;
	}


	public List<Ciudad> getCiudades() {
		return ciudades;
	}


	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public String getcImagen() {
		return cImagen;
	}


	public void setcImagen(String cImagen) {
		this.cImagen = cImagen;
	}


	public List<Camarote> getCamarotes() {
		return camarotes;
	}


	public void setCamarotes(List<Camarote> camarotes) {
		this.camarotes = camarotes;
	}

	/*
	public List<Reserva> getReservas() {
		return reservas;
	}


	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	*/
	
	

}
