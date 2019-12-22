package es.uca.gii.iw.crusaito.clases;

public class Excursion extends Servicio {

	private String eItinerario;
	
	public Excursion(String sNombre, String sDescripcion, double sPrecio, String sTipo, int sAforoActual,
			int sAforoMaximo, String eItinerario) {
		super(sNombre, sDescripcion, sPrecio, sTipo, sAforoActual, sAforoMaximo);
		this.eItinerario = eItinerario;
	}

	public Excursion() {}

	public String geteItinerario() {
		return eItinerario;
	}

	public void seteItinerario(String eItinerario) {
		this.eItinerario = eItinerario;
	}

}
