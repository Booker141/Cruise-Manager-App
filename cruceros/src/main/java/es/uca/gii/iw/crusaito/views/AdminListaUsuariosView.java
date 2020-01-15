package es.uca.gii.iw.crusaito.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Rol;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;
import es.uca.gii.iw.crusaito.servicios.rolService;

@SuppressWarnings("serial")
@Route(value = "ListaUsuarios",layout = MainView.class)
@Secured("Admin")
public class AdminListaUsuariosView extends VerticalLayout implements BeforeEnterObserver{
	
	private UsuarioService usuarioService;
	private rolService rolService;
	private CruceroService cruceroService;
	
	private GridCrud<Usuario> crud = new GridCrud<>(Usuario.class);
	
	@Autowired
	public AdminListaUsuariosView(UsuarioService usuarioService, rolService rolService,
			CruceroService cruceroService) {
		
		this.usuarioService = usuarioService;
		this.rolService = rolService;
		this.cruceroService = cruceroService;

		crud.getGrid().setColumns("firstName","lastName","email","username","dni","phoneNumber");
		crud.getGrid().getColumnByKey("firstName").setHeader("Nombre");
		crud.getGrid().getColumnByKey("lastName").setHeader("Apellidos");
		crud.getGrid().getColumnByKey("email").setHeader("Email");
		crud.getGrid().getColumnByKey("phoneNumber").setHeader("Telefono");
		crud.getGrid().addColumn(Usuario::getRole).setHeader("Rol");
		crud.getGrid().addColumn(Usuario::getCrucero).setHeader("Crucero");
		
		DefaultCrudFormFactory<Usuario> formFactory = new DefaultCrudFormFactory<>(Usuario.class);
		
		formFactory.setUseBeanValidation(true);
		formFactory.setVisibleProperties(CrudOperation.ADD,"firstName","lastName","email",
				"username","password","dni","phoneNumber","bornDate","address","city","role","crucero","enabled");
		formFactory.setVisibleProperties(CrudOperation.UPDATE,"firstName","lastName","email",
				"username","password","dni","phoneNumber","bornDate","address","city","role","crucero","enabled","pEncoded");

		formFactory.setFieldProvider("role", () -> {
		    ComboBox<Rol> combobox = new ComboBox<>("Rol", this.rolService.findAll());
		    combobox.setAllowCustomValue(false);
		    combobox.setItemLabelGenerator(Rol::getName);
		    combobox.setRequired(true);
		    return combobox;
		});
	
		formFactory.setFieldProvider("crucero", ()->{
			List<Crucero> crucerosList = this.cruceroService.load();
			
			ItemFilter<Crucero> filter = (crucero, filterString) -> crucero.getcNombre().startsWith(filterString);

			ComboBox<Crucero> combobox = new ComboBox<>();
			combobox.setItems(filter, crucerosList);
			combobox.setItemLabelGenerator(Crucero::getcNombre);
			combobox.setClearButtonVisible(true);
			return combobox;
		});
		
		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.usuarioService::load); 
		crud.setAddOperation(this.usuarioService::save);
		crud.setUpdateOperation(this.usuarioService::save);
		crud.setDeleteOperation(this.usuarioService::delete);
		
		crud.setSizeFull();
		this.setSizeFull();
		
		add(crud);
	}
	
	public void beforeEnter(BeforeEnterEvent event) {
		final boolean accessGranted = SecurityUtils.isAccessGranted(event.getNavigationTarget());
		if(!accessGranted) {
			if(SecurityUtils.isUserLoggedIn()) {
				event.rerouteTo(ProhibidoView.class);
			}
			else {
				event.rerouteTo(LoginView.class);
			}
		} 
	}
}
