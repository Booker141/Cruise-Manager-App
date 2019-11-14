package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.uca.gii.iw.crusaito.spring.UsuarioRol;

@SuppressWarnings("serial")
@EntityScan
public class Usuario implements UserDetails{
	
	private String cNombre;
	private String cApellidos;
	private String cUsername;
	private String cPassword;
	private String cDNI;
	private int cTelefono;
	private LocalDate cFchNac;
	private String cDireccion;
	private String cCiudad;
	private UsuarioRol rol;
	private boolean enabled;
	
	public Usuario(String cNombre, String cApellidos, String cUsername, String cPassword, String cDNI, int cTelefono, LocalDate cFchNac, String cDireccion,
			String cCiudad, UsuarioRol rol) {
		this.cNombre = cNombre;
		this.cApellidos = cApellidos;
		this.cUsername = cUsername;
		this.cDNI = cDNI;
		this.cTelefono = cTelefono;
		this.cFchNac = cFchNac;
		this.cDireccion = cDireccion;
		this.cCiudad = cCiudad;
		this.cPassword = cPassword;
		this.rol = rol;
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
	
	public String getcUsername() {
		// TODO Auto-generated method stub
		return cUsername;
	}
	
	public void setcUsername(String cUsername) {
		this.cUsername = cUsername;
	}

	public void setcApellidos(String cApellidos) {
		this.cApellidos = cApellidos;
	}
	
	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
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
	
	public UsuarioRol getRol() {
		return rol;
	}

	public void setRol(UsuarioRol rol) {
		this.rol = rol;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		return list;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}