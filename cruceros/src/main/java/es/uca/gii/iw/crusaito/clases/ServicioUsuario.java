package es.uca.gii.iw.crusaito.clases;


import javax.persistence.*;

@Entity
public class ServicioUsuario {
	
	@EmbeddedId
	private ServicioUsuarioId id = new ServicioUsuarioId();
	
	@ManyToOne
	@MapsId("servicioId")
	private Servicio servicio;
	
	@ManyToOne
	@MapsId("usuarioId")
	private Usuario usuario;
	
	private int participantes;
	private double precio;
	
	public int getParticipantes() {
		return participantes;
	}

	public void setParticipantes(int participantes) {
		this.participantes = participantes;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
