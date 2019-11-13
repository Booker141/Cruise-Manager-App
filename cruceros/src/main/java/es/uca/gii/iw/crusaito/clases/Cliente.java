package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@EntityScan
public class Cliente implements UserDetails{
	private String cNombre;
	private String cApellidos;
	private String cDNI;
	private int cTelefono;
	private LocalDate cFchNac;
	private String cDireccion;
	private String cCiudad;
	
	public Cliente(String cNombre, String cApellidos, String cDNI, int cTelefono, LocalDate cFchNac, String cDireccion,
			String cCiudad) {
		this.cNombre = cNombre;
		this.cApellidos = cApellidos;
		this.cDNI = cDNI;
		this.cTelefono = cTelefono;
		this.cFchNac = cFchNac;
		this.cDireccion = cDireccion;
		this.cCiudad = cCiudad;
	}

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("role_manager"));
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}