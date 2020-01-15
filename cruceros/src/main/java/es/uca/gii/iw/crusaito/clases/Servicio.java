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
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Ciudad ciudad;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "servicios")
	private Set<Crucero> cruceros;
	
	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
	private Set<ServicioUsuario> serviciosUsuarios;
		
	/**
	 * Constructor de la entidad Servicio
	 * 
	 * @param sNombre - sNombre define el nombre del servicio.
	 * @param sDescripcion - sDescripcion define una descripción del servicio a reservar.
	 * @param sPrecio - sPrecio define el precio que cuesta el servicio al reservar el mismo.
	 * @param sTipo - sTipo define el tipo de servicio que se va a reservar [Restaurante, Excursion, Tienda]
	 * @param sImagen - sImagen define una imagen del servicio.
	 * @param sAforoActual - sAforoActual define el número de personas que han reservado actualmente el servicio.
	 * @param sAforoMaximo - sAforoMaximo define el número de personas máximo que pueden disfrutar del servicio al mismo tiempo.
	 * @param sFecha - sFecha define la fecha del servicio.
	 */
	
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
	
	
	/**
	 * Constructor de la entidad Servicio, en su variante Excursión
	 * 
	 * @param sNombre - sNombre define el nombre del servicio.
	 * @param sDescripcion - sDescripcion define una descripción del servicio a reservar.
	 * @param sPrecio - sPrecio define el precio que cuesta el servicio al reservar el mismo.
	 * @param sTipo - sTipo define el tipo de servicio que se va a reservar [Restaurante, Excursion, Tienda]
	 * @param sImagen - sImagen define una imagen del servicio.
	 * @param sAforoActual - sAforoActual define el número de personas que han reservado actualmente el servicio.
	 * @param sAforoMaximo - sAforoMaximo define el número de personas máximo que pueden disfrutar del servicio al mismo tiempo.
	 * @param sFecha - sFecha define la fecha del servicio.
	 * @param eItinerario- eItinerario define la ciudad en la que transcurre la excursión.
	 */
	
	public Servicio(String sNombre, String sDescripcion, double sPrecio, ServicioTipo sTipo, int sAforoActual,
			String sImagen, int sAforoMaximo, LocalDate sFecha) {
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

	/**
	 * Constructor de clase vacio
	 */
	public Servicio() {}

	/**

     * Método de tipo booleano que comprueba si se puede realizar una reserva dejando hueco
     * los que no tienen reserva

     * @return true/false si hay o no hueco libre.
     * 

     */

	public boolean AforoHuecoLibre(int participantes) {
		if((this.getsAforoActual()+participantes) >= (this.getsAforoMaximo() * 0.70)) return false;
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

	public int AforoDisponible() {
		int res = ((int)(this.sAforoMaximo*0.70))-this.sAforoActual;
		return res;
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


	public Ciudad getCiudad() {
		return ciudad;
	}


	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	
}
