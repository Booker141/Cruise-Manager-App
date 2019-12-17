package es.uca.gii.iw.crusaito.clases;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reserva 
{	
	
	public Reserva() {
	}

	@Id
	@GeneratedValue
	private long id;
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario rUsuario;
	@OneToOne(fetch = FetchType.LAZY)
	private Crucero rCrucero;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private boolean isExpired;
	private double precio;
	private String estado; // Abierta, Finalizada y Cancelada
	
	public Reserva(LocalDate fechaInicio, LocalDate fechaFin, double precio, String estado) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
		this.estado = estado;
		this.isExpired = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getrUsuario() {
		return rUsuario;
	}

	public void setrUsuario(Usuario rUsuario) {
		this.rUsuario = rUsuario;
	}

	public Crucero getrCrucero() {
		return rCrucero;
	}

	public void setrCrucero(Crucero rCrucero) {
		this.rCrucero = rCrucero;
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

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

}