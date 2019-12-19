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
	
	public Reserva() {}

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
	private ReservaEstado estado; // Abierta, Finalizada y Cancelada
	
	public Reserva(long id, LocalDate fechaInicio, LocalDate fechaFin, boolean isExpired, double precio,
			ReservaEstado estado) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.isExpired = isExpired;
		this.precio = precio;
		this.estado = estado;
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


	public ReservaEstado getEstado() {
		return estado;
	}

	public void setEstado(ReservaEstado estado) {
		this.estado = estado;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

}