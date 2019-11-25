package es.uca.gii.iw.crusaito.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.uca.gii.iw.crusaito.clases.Usuario;

@Route("Registrar")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class RegisterView extends FormLayout{
	private static final long serialVersionUID = 1L;
	
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
	private UsuarioService userService;
	
	private Button Save = new Button("Guardar",click -> {
		int phoneNumber = telefono.getValue().intValue();
		
		Usuario user = new Usuario(firstName.getValue(), lastName.getValue(), email.getValue(), 
				username.getValue(), password.getValue(), dni.getValue(), phoneNumber, 
				bornDate.getValue(),address.getValue(),city.getValue());
		//Operacion de guardado en la BD, implementada en la clase UsuarioService
		userService.save(user);
		
	});
	
	public RegisterView() {
		
		Binder<Usuario> binder = new Binder<>(Usuario.class);
		binder.bindInstanceFields(this);
		
		email.setErrorMessage("Dirreción de correo no válida");
		formulario.add(email,username,password,rPassword,firstName,lastName,telefono,dni,address,city,bornDate,Save);
		
		add(formulario);
	}
}
