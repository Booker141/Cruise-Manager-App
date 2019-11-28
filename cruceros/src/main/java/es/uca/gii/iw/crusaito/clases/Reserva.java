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
	private long idUsuario;
	private long idBarco;
	private LocalDate fechaInicio;
	private double precio;

	public Reserva(long id, long idUsuario, long idBarco, LocalDate fechaInicio, double precio) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idBarco = idBarco;
		this.fechaInicio = fechaInicio;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public long getIdBarco() {
		return idBarco;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}}