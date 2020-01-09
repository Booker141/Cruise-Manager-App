package es.uca.gii.iw.crusaito.clases;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CiudadCruceroId implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	private long ciudadId;
	private long cruceroId;
		
	public CiudadCruceroId() {}
		
	/**
	 * Constructor de la entidad CiudadCruceroId
	 * 
	 * @param ciudadId - ciudadId define el ID de la ciudad que pertenece a la relación.
	 * @param cruceroId - cruceroId define el ID del crucero que pertenece a la relación.
	 */
	
	public CiudadCruceroId(long ciudadId, long cruceroId) {
		super();
		this.ciudadId = ciudadId;
		this.cruceroId = cruceroId;
	}

	public long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(long ciudadId) {
		this.ciudadId = ciudadId;
	}

	public long getCruceroId() {
		return cruceroId;
	}

	public void setCruceroId(long cruceroId) {
		this.cruceroId = cruceroId;
	}
	
}
