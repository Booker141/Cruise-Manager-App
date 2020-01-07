package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Servicio {
	
	@Id
	@GeneratedValue
	private long id;
	private String sNombre;
	@Size(max= 1000)
	private String sDescripcion;
	private double sPrecio;
	private ServicioTipo sTipo;
	private String sImagen;
	private int sAforoActual;
	private int sAforoMaximo;
	private LocalDate sFecha;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "servicios")
	private Set<Crucero> cruceros;
	
	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
	private Set<ServicioUsuario> serviciosUsuarios;
	
	
	private String eItinerario;
	
	//Constructor de servicio
	public Servicio(String sNombre, String sDescripcion, double sPrecio, ServicioTipo sTipo,
			String sImagen, int sAforoActual, int sAforoMaximo, LocalDate sFecha) {
		this.sNombre = sNombre;
		this.sDescripcion = sDescripcion;
		this.sPrecio = sPrecio;
		this.sTipo = sTipo;
		this.sImagen = sImagen;
		this.sAforoActual = sAforoActual;
		this.sAforoMaximo = sAforoMaximo;
		this.sFecha = sFecha;
		this.serviciosUsuarios = new HashSet<ServicioUsuario>();
		this.cruceros = new HashSet<Crucero>();
	}
	
	//Constructor de excursion
	public Servicio(String sNombre, String sDescripcion, double sPrecio, ServicioTipo sTipo, int sAforoActual,
			String sImagen, int sAforoMaximo, LocalDate sFecha, String eItinerario) {
		this.sNombre = sNombre;
		this.sDescripcion = sDescripcion;
		this.sPrecio = sPrecio;
		this.sTipo = sTipo;
		this.sImagen = sImagen;
		this.sAforoActual = sAforoActual;
		this.sAforoMaximo = sAforoMaximo;
		this.sFecha = sFecha;
		this.eItinerario = eItinerario;
		this.serviciosUsuarios = new HashSet<ServicioUsuario>();
		this.cruceros = new HashSet<Crucero>();
	}

	//Constructor vacio
	public Servicio() {}

	/**

     * Método de tipo booleano que comprueba si se puede realizar una reserva dejando hueco
     * los que no tienen reserva

     * @return true/false si hay o no hueco libre.
     * 

     */

	public boolean AforoHuecoLibre() {
		if((this.getsAforoActual()+1) >= (this.getsAforoMaximo() * 0.70)) return false;
		else return true;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public String getsDescripcion() {
		return sDescripcion;
	}

	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
	}

	public double getsPrecio() {
		return sPrecio;
	}

	public void setsPrecio(double sPrecio) {
		this.sPrecio = sPrecio;
	}

	public ServicioTipo getsTipo() {
		return sTipo;
	}

	public void setsTipo(ServicioTipo sTipo) {
		this.sTipo = sTipo;
	}

	public int getsAforoActual() {
		return sAforoActual;
	}

	public void setsAforoActual(int sAforoActual) {
		this.sAforoActual = sAforoActual;
	}
	
	public void addAforoActual(int participantes) {
		this.sAforoActual += participantes;
	}
	
	public void removeAforoActual(int participantes) {
		this.sAforoActual -= participantes;
	}

	public int getsAforoMaximo() {
		return sAforoMaximo;
	}

	public void setsAforoMaximo(int sAforoMaximo) {
		this.sAforoMaximo = sAforoMaximo;
	}

	
	public LocalDate getsFecha() {
		return sFecha;
	}

	public void setsFecha(LocalDate sFecha) {
		this.sFecha = sFecha;
	}

	/*public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}*/

	/*public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		usuario.getServicios().add(this);
	}
	
	public void removeUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
		usuario.getServicios().remove(this);
	}*/
	
	public String geteItinerario() {
		return eItinerario;
	}

	public void seteItinerario(String eItinerario) {
		this.eItinerario = eItinerario;
	}

	public String getsImagen() {
		return sImagen;
	}

	public void setsImagen(String sImagen) {
		this.sImagen = sImagen;
	}

	public Set<Crucero> getCruceros() {
		return cruceros;
	}

	public void setCruceros(Set<Crucero> cruceros) {
		this.cruceros = cruceros;
	}

	public Set<ServicioUsuario> getServiciosUsuarios() {
		return serviciosUsuarios;
	}

	public void setServiciosUsuarios(Set<ServicioUsuario> serviciosUsuarios) {
		this.serviciosUsuarios = serviciosUsuarios;
	}

	@Override
	public String toString() {
		return this.sNombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
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
		Servicio other = (Servicio) obj;
		if (sNombre == null) {
			if (other.sNombre != null)
				return false;
		} else if (!sNombre.equals(other.sNombre))
			return false;
		return true;
	}

	
}
