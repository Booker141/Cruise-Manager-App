package es.uca.gii.iw.crusaito.clases;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ServicioUsuarioId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long servicioId;
	private long usuarioId;
	
	public ServicioUsuarioId() {}
	
	/**
	 * Constructor de la entidad ServicioUsuarioId
	 * 
	 * @param servicioId - servicioId define la ID del servicio que pertenece a la relación.
	 * @param usuarioId - usuarioId define la ID del usuario que pertenece a la relación.
	 */
	
	public ServicioUsuarioId(long servicioId, long usuarioId) {
		super();
		this.servicioId = servicioId;
		this.usuarioId = usuarioId;
	}

	public long getServicioId() {
		return servicioId;
	}

	public void setServicioId(int servicioId) {
		this.servicioId = servicioId;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}
