package es.uca.gii.iw.crusaito.views;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.CiudadService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;

@Route(value = "ListaCiudades",layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Admin")
public class AdminListaCiudadesView extends VerticalLayout implements BeforeEnterObserver{
	
	private CiudadService ciudadService;
	private CruceroService cruceroService;
	
	private GridCrud<Ciudad> crud = new GridCrud<>(Ciudad.class);
	
	@Autowired
	public AdminListaCiudadesView(CiudadService ciudadService, CruceroService cruceroService) {
		this.ciudadService = ciudadService;
		this.cruceroService = cruceroService;
		
		crud.getGrid().setColumns("cNombre");
		crud.getGrid().getColumnByKey("cNombre").setHeader("Nombre");
		
		DefaultCrudFormFactory<Ciudad> formFactory = new DefaultCrudFormFactory<>(Ciudad.class);

		formFactory.setVisibleProperties("cNombre");
		
		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.ciudadService::load); 
		crud.setAddOperation(this.ciudadService::save);
		crud.setUpdateOperation(this.ciudadService::save);
		crud.setDeleteOperation(this.ciudadService::delete);
		
		crud.setSizeFull();
		this.setSizeFull();
		
		this.add(crud);
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

