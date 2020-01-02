package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
public class Usuario implements UserDetails{
	
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String dni;
	private int phoneNumber;
	private LocalDate bornDate;
	private String address;
	private String city;
	@ManyToOne
	private Rol role;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "usuarios")
	private List<Servicio> servicios;
	private boolean enabled;
	private boolean pEncoded;
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy="usuario")
	private List<Reserva> reservas;
	*/
	
	public Usuario(){}
	
	public Usuario(String firstName, String lastName, String email, String username, 
			String password, String dni, int phoneNumber, LocalDate bornDate, String address,
				String city,Rol role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.dni = dni;
		this.phoneNumber = phoneNumber;
		this.bornDate = bornDate;
		this.address = address;
		this.city = city;
		this.password = password;
		this.role=role;
		this.pEncoded = false;
		this.servicios = new ArrayList<>();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	//Implementación métodos heredados de UserDetails
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	//Implementación método heredado de UserDetails, puede que necesite mas comprobaciones.
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getBornDate() {
		return bornDate;
	}

	public void setBornDate(LocalDate bornDate) {
		this.bornDate = bornDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Rol getRole() {
		return role;
	}

	public void setRole(Rol role) {
		this.role = role;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(this.role.getName()));

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/*
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}*/

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	public void addServicio(Servicio servicio) {
		this.servicios.add(servicio);
		servicio.getUsuarios().add(this);
	}
	
	public void removeServicio(Servicio servicio) {
		this.servicios.remove(servicio);
		servicio.getUsuarios().remove(this);
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean ispEncoded() {
		return pEncoded;
	}

	public void setpEncoded(boolean pEncoded) {
		this.pEncoded = pEncoded;
	}
	
	/*
	public String currentUsername() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails)principal).getUsername();
			} else {
				String username = principal.toString();
			}
				return username;
	}
	*/
}