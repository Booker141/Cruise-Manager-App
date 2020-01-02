package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Servicio {
	
	@Id
	@GeneratedValue
	private long id;
	private String sNombre;
	private String sDescripcion;
	private double sPrecio;
	private ServicioTipo sTipo;
	private String sImagen;
	private int sAforoActual;
	private int sAforoMaximo;
	private LocalDate sFecha;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "servicios")
	private List<Crucero> cruceros;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "servicio_usuario",
    	joinColumns = {@JoinColumn(name = "servicio_id", referencedColumnName = "id")},
    	inverseJoinColumns = {@JoinColumn(name = "usuario_id", referencedColumnName = "id")}
	)
	private List<Usuario> usuarios;
	
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
		this.usuarios = new ArrayList<>();
		this.cruceros = new ArrayList<>();
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
		this.usuarios = new ArrayList<>();
	}

	//Constructor vacio
	public Servicio() {}

	//Regla de negocio que comprueba si se puede realizar una reserva dejando hueco a los que van sin reserva
	public boolean AforoHuecoLibre(int AforoReserva) {
		if((this.getsAforoActual()+AforoReserva) >= (this.getsAforoMaximo() * 0.70)) return false;
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		usuario.getServicios().add(this);
	}
	
	public void removeUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
		usuario.getServicios().remove(this);
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eItinerario == null) ? 0 : eItinerario.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + sAforoActual;
		result = prime * result + sAforoMaximo;
		result = prime * result + ((sDescripcion == null) ? 0 : sDescripcion.hashCode());
		result = prime * result + ((sImagen == null) ? 0 : sImagen.hashCode());
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sPrecio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sTipo == null) ? 0 : sTipo.hashCode());
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
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
		if (eItinerario == null) {
			if (other.eItinerario != null)
				return false;
		} else if (!eItinerario.equals(other.eItinerario))
			return false;
		if (id != other.id)
			return false;
		if (sAforoActual != other.sAforoActual)
			return false;
		if (sAforoMaximo != other.sAforoMaximo)
			return false;
		if (sDescripcion == null) {
			if (other.sDescripcion != null)
				return false;
		} else if (!sDescripcion.equals(other.sDescripcion))
			return false;
		if (sImagen == null) {
			if (other.sImagen != null)
				return false;
		} else if (!sImagen.equals(other.sImagen))
			return false;
		if (sNombre == null) {
			if (other.sNombre != null)
				return false;
		} else if (!sNombre.equals(other.sNombre))
			return false;
		if (Double.doubleToLongBits(sPrecio) != Double.doubleToLongBits(other.sPrecio))
			return false;
		if (sTipo != other.sTipo)
			return false;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}

	public List<Crucero> getCruceros() {
		return cruceros;
	}

	public void setCruceros(List<Crucero> cruceros) {
		this.cruceros = cruceros;
	}
	
	
}
