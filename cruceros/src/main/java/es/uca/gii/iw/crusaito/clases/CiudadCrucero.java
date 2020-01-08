package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CiudadCrucero {

	@EmbeddedId
	private CiudadCruceroId id = new CiudadCruceroId();
	
	@ManyToOne
	@MapsId("ciudadId")
	private Ciudad ciudad;
	
	@ManyToOne
	@MapsId("cruceroId")
	private Crucero crucero;
	
	private LocalDate fechaLlegada;
	private int horaLlegada;
	private LocalDate fechaSalida;
	private int horaSalida;

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Crucero getCrucero() {
		return crucero;
	}

	public void setCrucero(Crucero crucero) {
		this.crucero = crucero;
	}

	public LocalDate getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(LocalDate fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(int horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public int getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(int horaSalida) {
		this.horaSalida = horaSalida;
	}

}
