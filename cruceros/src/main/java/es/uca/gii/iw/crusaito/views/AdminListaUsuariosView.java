package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@Route(value = "ListaUsuarios",layout = MainView.class)
public class AdminListaUsuariosView extends Div{
	
	private UsuarioService usuarioService;

	private GridCrud<Usuario> crud = new GridCrud<>(Usuario.class);
	
	@Autowired
	public AdminListaUsuariosView(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
		
		this.add(crud);
		
		crud.getCrudFormFactory().setUseBeanValidation(true);
		
		crud.setFindAllOperation(this.usuarioService::load); 
		crud.setAddOperation(this.usuarioService::save);
		crud.setUpdateOperation(this.usuarioService::save);
		crud.setDeleteOperation(this.usuarioService::delete);
		
	}
}
