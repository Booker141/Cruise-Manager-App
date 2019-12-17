package es.uca.gii.iw.crusaito.clases;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Crucero {
	
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
	@OneToMany(fetch = FetchType.LAZY)
	private List<Camarote> idCamarotes;
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Ciudad> Ciudad;
	@OneToOne(fetch = FetchType.LAZY)
	private Reserva idReserva;
	

	

	public Crucero(Long id, String cNombre, String cOrigen, String cDestino, String cDuracion, String cDescripcion,
			String cImagen, double cPrecio, List<Camarote> idCamarotes,
			List<Ciudad> ciudad, Reserva idReserva) {
		this.id = id;
		this.cNombre = cNombre;
		this.cOrigen = cOrigen;
		this.cDestino = cDestino;
		this.cDuracion = cDuracion;
		this.cDescripcion = cDescripcion;
		this.cImagen = cImagen;
		this.cPrecio = cPrecio;
		this.idCamarotes = idCamarotes;
		Ciudad = ciudad;
		this.idReserva = idReserva;
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


	public List<Ciudad> getCiudad() {
		return Ciudad;
	}


	public void setCiudad(List<Ciudad> ciudad) {
		Ciudad = ciudad;
	}


	public List<Camarote> getIdCamarotes() {
		return idCamarotes;
	}


	public void setIdCamarotes(List<Camarote> idCamarotes) {
		this.idCamarotes = idCamarotes;
	}


	public String getcImagen() {
		return cImagen;
	}


	public void setcImagen(String cImagen) {
		this.cImagen = cImagen;
	}

	
	

}
