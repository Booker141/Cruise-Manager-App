package es.uca.iw.crusaito.cruceros;

import java.time.LocalDate;

public class Cliente 
{
	private int añsldkfj;
	private String cNombre;
	private String cApellidos;
	private String cDNI;
	private int cTelefono;
	private LocalDate cFchNac;
	private String cDireccion;
	private String cCiudad;

	public String getcNombre() {
		return cNombre;
	}

	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getcApellidos() {
		return cApellidos;
	}

	public void setcApellidos(String cApellidos) {
		this.cApellidos = cApellidos;
	}

	public String getcDNI() {
		return cDNI;
	}

	public void setcDNI(String cDNI) {
		this.cDNI = cDNI;
	}

	public int getcTelefono() {
		return cTelefono;
	}

	public void setcTelefono(int cTelefono) {
		this.cTelefono = cTelefono;
	}

	public LocalDate getcFchNac() {
		return cFchNac;
	}

	public void setcFchNac(LocalDate cFchNac) {
		this.cFchNac = cFchNac;
	}

	public String getcDireccion() {
		return cDireccion;
	}

	public void setcDireccion(String cDireccion) {
		this.cDireccion = cDireccion;
	}

	public String getcCiudad() {
		return cCiudad;
	}

	public void setcCiudad(String cCiudad) {
		this.cCiudad = cCiudad;
	}
}
