package es.uca.gii.iw.crusaito.clases;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

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
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<ServicioUsuario> usuariosServicios;
	
	@ManyToOne
	private Crucero crucero;
	
	private boolean enabled;
	//Comprueba si la contraseña ya ha sido codificada la primera vez
	private boolean pEncoded;
	
	public Usuario(){}
	
	/**
	 * Constructor de la entidad Usuario
	 * 
	 * @param firstName - firstName define el nombre real del usuario.
	 * @param lastName - lastName define los apellidos del usuario.
	 * @param email - email define la dirección de correo electrónico del usuario.
	 * @param username - username define el nombre de usuario (ficticio) que usará para acceder al sistema.
	 * @param password - password define la contraseña que usará el usuario para acceder al sistema.
	 * @param dni - dni define el DNI del usuario.
	 * @param phoneNumber - phoneNumbre define el número de teléfono del usuario.
	 * @param bornDate - bornDate define la fecha de nacimiento del usuario.
	 * @param address - address define la dirección del usuario.
	 * @param city - city define la ciudad en la que reside el usuario.
	 * @param role - role define el rol que tendrá el usuario en el sistema [Cliente, Admin, Gerente].
	 */
	
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
		this.usuariosServicios = new HashSet<ServicioUsuario>();
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
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public Crucero getCrucero() {
		return this.crucero;
	}

	public void setCrucero(Crucero crucero) {
		if(crucero==null) {
			this.crucero = null;
		}else {
			this.crucero = crucero;
			crucero.getUsuarios().add(this);
		}
	}

	@Override
	public String toString() {
		return lastName;
	}

	public Set<ServicioUsuario> getUsuariosServicios() {
		return usuariosServicios;
	}

	public void setUsuariosServicios(Set<ServicioUsuario> usuariosServicios) {
		this.usuariosServicios = usuariosServicios;
	}
	
}