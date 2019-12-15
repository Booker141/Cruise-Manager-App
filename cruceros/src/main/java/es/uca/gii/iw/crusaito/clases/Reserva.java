package es.uca.gii.iw.crusaito.clases;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reserva 
{	
	
	@Id
	@GeneratedValue
	private long id;
	private Usuario usuario;
	private Crucero crucero;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double precio;
	private String estado; // Abierta, Finalizada y Cancelada
	
	public Reserva(long id, Usuario usuario, Crucero crucero, LocalDate fechaInicio, LocalDate fechaFin, double precio,
			String estado) {
		this.id = id;
		this.usuario = usuario;
		this.crucero = crucero;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Crucero getCrucero() {
		return crucero;
	}

	public void setCrucero(Crucero crucero) {
		this.crucero = crucero;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}