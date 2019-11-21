package es.uca.gii.iw.crusaito.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
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
	private TextField username = new TextField("Introduce usuario");
	private TextField email = new TextField("Introduce correo electrónico");
	private TextField password = new TextField("Introduce contraseña");
	private TextField rPassword = new TextField("Introduce de nuevo la contraseña");
	
	private Button Save = new Button("Guardar");
	
	public RegisterView() {
		
		Binder<Usuario> binder = new Binder<>(Usuario.class);
		binder.bindInstanceFields(this);
		add(email,username,password,rPassword,Save);
		
	}
}
