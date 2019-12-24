package es.uca.gii.iw.crusaito.clases;

public class Restaurante extends Servicio {

	public Restaurante(String sNombre, String sDescripcion, double sPrecio, String sTipo, int sAforoActual,
			int sAforoMaximo) {
		super(sNombre, sDescripcion, sPrecio, sTipo, sAforoActual, sAforoMaximo);
		// TODO Auto-generated constructor stub
	}

	public Restaurante() {}
	
	//Regla de negocio que comprueba si pueden reservar sin sobrepasar el máximo de reservas
	public boolean AforoHuecoLibre(int AforoReserva) {
		if((this.getsAforoActual()+AforoReserva) >= (this.getsAforoMaximo() * 0.70)) return false;
		else return true;
	}
}
