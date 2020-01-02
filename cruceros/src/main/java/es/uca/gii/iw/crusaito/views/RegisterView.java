package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.common.Funciones;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;
import es.uca.gii.iw.crusaito.servicios.rolService;

@SuppressWarnings("serial")
@Route(value = "Registrar",layout = MainView.class)
public class RegisterView extends VerticalLayout{
	
	private FormLayout formulario = new FormLayout();
	
	private TextField firstName = new TextField("Nombre");
	private TextField lastName = new TextField("Apellidos");
	private NumberField telefono = new NumberField("Teléfono");
	private TextField dni = new TextField("DNI");
	private TextField address = new TextField("Dirección");
	private TextField city = new TextField("Ciudad");
	private DatePicker bornDate = new DatePicker("Fecha de nacimiento");
	private TextField username = new TextField("Usuario");
	private EmailField email = new EmailField("Correo electrónico");
	private PasswordField password = new PasswordField("Contraseña");
	private PasswordField rPassword = new PasswordField("Repita la contraseña");
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private rolService rolService;
	
	private Button Save = new Button("Guardar",click -> {
		if(!Funciones.existeEmail(usuarioService, email.getValue()) && !Funciones.existeUsuario(usuarioService, username.getValue())) {
			int phoneNumber = telefono.getValue().intValue();
		
			Usuario user = new Usuario(firstName.getValue(), lastName.getValue(), email.getValue(), 
				username.getValue(), password.getValue(), dni.getValue(), phoneNumber, 
				bornDate.getValue(),address.getValue(),city.getValue(),rolService.findByName("Cliente"));
		//Operacion de guardado en la BD, implementada en la clase UsuarioService
			usuarioService.save(user);
		}
	});
	
	public RegisterView(UsuarioService usuarioService) {
		
		this.usuarioService = usuarioService;
		
		//Header header = new Header();
		
		Binder<Usuario> binder = new Binder<>(Usuario.class);
		binder.bindInstanceFields(this);
		
		firstName.setRequiredIndicatorVisible(true);
		lastName.setRequiredIndicatorVisible(true);
		email.setRequiredIndicatorVisible(true);
		username.setRequiredIndicatorVisible(true);
		password.setRequiredIndicatorVisible(true);
		dni.setRequiredIndicatorVisible(true);
		address.setRequiredIndicatorVisible(true);
		city.setRequiredIndicatorVisible(true);

		//Validación campos obligatorios
		
		binder.forField(firstName)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 2 y 32 letras",2, 32))
		.bind(Usuario::getFirstName,Usuario::setFirstName);
		
		binder.forField(lastName)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 2 y 32 letras",2, 32))
		.bind(Usuario::getLastName,Usuario::setLastName);
		
		binder.forField(email)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 6 y 64 letras",6, 64))
		.bind(Usuario::getEmail,Usuario::setEmail);
		
		binder.forField(username)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 4 y 16 letras",4, 16))
		.bind(Usuario::getUsername,Usuario::setUsername);
		
		binder.forField(password)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 4 y 16 letras",4, 16))
		.bind(Usuario::getPassword,Usuario::setPassword);
		
		binder.forField(dni)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener 7 dígitos y una letra",7, 1))
		.bind(Usuario::getDni,Usuario::setDni);
		
		binder.forField(address)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 4 y 64 letras",4, 64))
		.bind(Usuario::getAddress,Usuario::setAddress);
		
		binder.forField(city)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 6 y 24 letras",6, 24))
		.bind(Usuario::getCity,Usuario::setCity);
		
		
		email.setErrorMessage("Dirección de correo no válida");
		formulario.add(email,username,password,rPassword,firstName,lastName,telefono,dni,address,city,bornDate,Save);
		
		add(formulario);
	}
}
