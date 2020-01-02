package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;
import es.uca.gii.iw.crusaito.servicios.rolService;

@Route(value = "ListaUsuarios",layout = MainView.class)
public class AdminListaUsuariosView extends Div{
	
	private UsuarioService usuarioService;
	private rolService rolService;
	
	private GridCrud<Usuario> crud = new GridCrud<>(Usuario.class);
	
	@Autowired
	public AdminListaUsuariosView(UsuarioService usuarioService, rolService rolService) {
		
		this.usuarioService = usuarioService;
		this.rolService = rolService;

		crud.getCrudFormFactory().setUseBeanValidation(true);

		crud.getGrid().setColumns("firstName","lastName","email","username","dni","phoneNumber","city","role");
		crud.getGrid().getColumnByKey("firstName").setHeader("Nombre");
		crud.getGrid().getColumnByKey("lastName").setHeader("Apellidos");
		crud.getGrid().getColumnByKey("email").setHeader("Email");
		crud.getGrid().getColumnByKey("phoneNumber").setHeader("Telefono");
		crud.getGrid().getColumnByKey("city").setHeader("Ciudad");
		crud.getGrid().removeColumnByKey("role");
		crud.getGrid().addColumn(Usuario::getRole).setHeader("Rol");
		
		DefaultCrudFormFactory<Usuario> formFactory = new DefaultCrudFormFactory<>(Usuario.class);
		
		formFactory.setVisibleProperties("firstName","lastName","email",
				"username","password","dni","phoneNumber","bornDate","address","city","role","enabled");
		
		formFactory.setFieldProvider("role", () -> {
		    ComboBox<Rol> combobox = new ComboBox<>("Rol", this.rolService.findAll());
		    combobox.setAllowCustomValue(false);
		    combobox.setItemLabelGenerator(Rol::getName);
		    return combobox;
		});
	
		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.usuarioService::load); 
		crud.setAddOperation(this.usuarioService::save);
		crud.setUpdateOperation(this.usuarioService::save);
		crud.setDeleteOperation(this.usuarioService::delete);
		
		add(crud);
	}
}
