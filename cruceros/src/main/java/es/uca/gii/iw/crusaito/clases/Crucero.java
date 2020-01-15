package es.uca.gii.iw.crusaito.clases;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;


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
	@Size(max= 12000)
	private String cDescripcion;
	private String cImagen;
	private double cPrecio;
	
	@OneToOne
	private Barco barco;
	
	@OneToMany(mappedBy = "crucero")
	private Set<Usuario> usuarios;
	
	/*@ManyToMany(mappedBy = "cruceros")
	private Set<Ciudad> ciudades;*/
	
	@OneToMany(mappedBy = "crucero", cascade = CascadeType.ALL)
	private Set<CiudadCrucero> crucerosCiudades;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "crucero_servicio",
    	joinColumns = {@JoinColumn(name = "crucero_id", referencedColumnName = "id")},
    	inverseJoinColumns = {@JoinColumn(name = "servicio_id", referencedColumnName = "id")}
	)
	private Set<Servicio> servicios;

	/**
	 * Contructor de la entidad Crucero
	 * 
	 * @param cNombre - cNombre define el nombre que recibe el crucero.
	 * @param cOrigen - cOrigen define el origen del que parte el barco perteneciente al crucero.
	 * @param cDestino - cDestino define el destino al que debe llegar el barco perteneciente al crucero.
	 * @param cDuracion - cDuracion define la duración del trayecto del barco perteneciente al crucero.
	 * @param cDescripcion - cDescripcion define una descripción breve del crucero.
	 * @param cPrecio - cPrecio define el precio que hay que pagar para disfrutar del crucero.
	 */
	
	public Crucero(String cNombre, String cOrigen, String cDestino, String cDuracion, String cDescripcion,
			double cPrecio) {

		this.cNombre = cNombre;
		this.cOrigen = cOrigen;
		this.cDestino = cDestino;
		this.cDuracion = cDuracion;
		this.cDescripcion = cDescripcion;
		this.cPrecio = cPrecio;
		this.barco = null;
		this.usuarios = new HashSet<Usuario>();
		//this.ciudades = new HashSet<Ciudad>();
		this.servicios = new HashSet<Servicio>();
		this.crucerosCiudades = new HashSet<CiudadCrucero>();
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


	public String getcImagen() {
		return cImagen;
	}


	public void setcImagen(String cImagen) {
		this.cImagen = cImagen;
	}

	public Set<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	public void addServicio(Servicio servicio) {
		this.servicios.add(servicio);
		servicio.getCruceros().add(this);
	}
	
	public void removeServicio(Servicio servicio) {
		this.servicios.remove(servicio);
		servicio.getCruceros().remove(servicio);
	}


	public Barco getBarco() {
		return barco;
	}


	public void setBarco(Barco barco) {
		if(barco==null) {
			this.barco = null;
		}else {
			this.barco = barco;
			barco.setCrucero(this);
		}
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return cNombre;
	}


	public Set<CiudadCrucero> getCrucerosCiudades() {
		return crucerosCiudades;
	}


	public void setCrucerosCiudades(Set<CiudadCrucero> crucerosCiudades) {
		this.crucerosCiudades = crucerosCiudades;
	}
	
}
