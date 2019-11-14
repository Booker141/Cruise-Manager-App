package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.uca.gii.iw.crusaito.spring.UsuarioRol;

@SuppressWarnings("serial")
@Entity
public class Usuario implements UserDetails{
	
	@Id
	private long id;
	private String cNombre;
	private String cApellidos;
	private String username;
	private String password;
	private String dni;
	private int cTelefono;
	private LocalDate cFchNac;
	private String cDireccion;
	private String cCiudad;
	private UsuarioRol rol;
	private boolean enabled;
	
	public Usuario(String cNombre, String cApellidos, String username, String password, String dni, int cTelefono, LocalDate cFchNac, String cDireccion,
			String cCiudad, UsuarioRol rol) {
		this.cNombre = cNombre;
		this.cApellidos = cApellidos;
		this.username = username;
		this.dni = dni;
		this.cTelefono = cTelefono;
		this.cFchNac = cFchNac;
		this.cDireccion = cDireccion;
		this.cCiudad = cCiudad;
		this.password = password;
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

	//Implementación métodos heredados de UserDetails
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setcUsername(String username) {
		this.username = username;
	}

	public void setcApellidos(String cApellidos) {
		this.cApellidos = cApellidos;
	}
	
	//Implementación método heredado de UserDetails, puede que necesite mas comprobaciones.
	public String getPassword() {
		return password;
	}

	public void setcPassword(String password) {
		this.password = password;
	}

	public String getcDNI() {
		return dni;
	}

	public void setcDNI(String dni) {
		this.dni = dni;
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