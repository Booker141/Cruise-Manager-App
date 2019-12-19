package es.uca.gii.iw.crusaito.clases;

import javax.persistence.*;

@Entity
public class ProblemaReserva{

	@Id
    @GeneratedValue
    private Long id;

	private Reserva reserva;
    private String pTipoProblema;
    private String pDescripcionProblema;
    
    public ProblemaReserva(Long id, Reserva reserva, String tipoProblema, String descripcionProblema) {
		this.id = id;
		this.pTipoProblema = tipoProblema;
		this.pDescripcionProblema = descripcionProblema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getpTipoProblema() {
		return pTipoProblema;
	}

	public void setpTipoProblema(String pTipoProblema) {
		this.pTipoProblema = pTipoProblema;
	}

	public String getpDescripcionProblema() {
		return pDescripcionProblema;
	}

	public void setpDescripcionProblema(String pDescripcionProblema) {
		this.pDescripcionProblema = pDescripcionProblema;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
    
}
