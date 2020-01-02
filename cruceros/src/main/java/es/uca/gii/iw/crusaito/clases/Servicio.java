package es.uca.gii.iw.crusaito.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "servicios")
	private List<Usuario> usuarios;
	private String eItinerario;
	
	//Constructor de servicio
	public Servicio(String sNombre, String sDescripcion, double sPrecio, ServicioTipo sTipo,
			String sImagen, int sAforoActual, int sAforoMaximo) {
		this.sNombre = sNombre;
		this.sDescripcion = sDescripcion;
		this.sPrecio = sPrecio;
		this.sTipo = sTipo;
		this.sImagen = sImagen;
		this.sAforoActual = sAforoActual;
		this.sAforoMaximo = sAforoMaximo;
		this.usuarios = new ArrayList<>();
	}
	
	//Constructor de excursion
	public Servicio(String sNombre, String sDescripcion, double sPrecio, ServicioTipo sTipo, int sAforoActual,
			String sImagen, int sAforoMaximo, String eItinerario) {
		this.sNombre = sNombre;
		this.sDescripcion = sDescripcion;
		this.sPrecio = sPrecio;
		this.sTipo = sTipo;
		this.sImagen = sImagen;
		this.sAforoActual = sAforoActual;
		this.sAforoMaximo = sAforoMaximo;
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
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
	
	
}
