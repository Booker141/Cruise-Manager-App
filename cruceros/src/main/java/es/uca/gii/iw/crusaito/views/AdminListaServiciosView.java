package es.uca.gii.iw.crusaito.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.servicios.CiudadService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.ServicioService;

@Route(value = "ListaServicios",layout = MainView.class)
@Secured("Admin")
@SuppressWarnings("serial")
public class AdminListaServiciosView extends VerticalLayout implements BeforeEnterObserver{

	private ServicioService servicioService;
	private CruceroService cruceroService;
	private CiudadService ciudadService;
	
	private GridCrud<Servicio> crud = new GridCrud<>(Servicio.class);
	
	@Autowired
	public AdminListaServiciosView(ServicioService servicioService, CruceroService cruceroService,
			CiudadService ciudadService) {
		
		this.servicioService = servicioService;
		this.cruceroService = cruceroService;
		this.ciudadService = ciudadService;
		
		crud.getGrid().setColumns("sNombre","sDescripcion","sPrecio","sTipo","sAforoActual","sAforoMaximo","sFecha","ciudad");
		crud.getGrid().getColumnByKey("sNombre").setHeader("Nombre");
		crud.getGrid().getColumnByKey("sDescripcion").setHeader("Descripcion");
		crud.getGrid().getColumnByKey("sPrecio").setHeader("Precio");
		crud.getGrid().getColumnByKey("sTipo").setHeader("Tipo");
		crud.getGrid().getColumnByKey("sAforoActual").setHeader("Aforo actual");
		crud.getGrid().getColumnByKey("sAforoMaximo").setHeader("Aforo maximo");
		crud.getGrid().getColumnByKey("sFecha").setHeader("Fecha");
		crud.getGrid().getColumnByKey("ciudad").setHeader("Ciudad");

		crud.getGrid().addColumn(Servicio::getCruceros).setHeader("Cruceros");
		
		DefaultCrudFormFactory<Servicio> formFactory = new DefaultCrudFormFactory<>(Servicio.class);
		
		formFactory.setUseBeanValidation(true);
		formFactory.setVisibleProperties("sNombre","sDescripcion","sPrecio","sTipo","sAforoActual","sAforoMaximo","sFecha","ciudad");
		
		formFactory.setFieldProvider("ciudad", () -> {
			
			List<Ciudad> ciudadList = this.ciudadService.load();
			ItemFilter<Ciudad> filter = (ciudad, filterString) -> ciudad.getcNombre().startsWith(filterString);
		    ComboBox<Ciudad> combobox = new ComboBox<>();
		    combobox.setItems(filter,ciudadList);
		    combobox.setItemLabelGenerator(Ciudad::getcNombre);
		    combobox.setClearButtonVisible(true);
		    
		    return combobox;
		});

		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.servicioService::load); 
		crud.setAddOperation(this.servicioService::save);
		crud.setUpdateOperation(this.servicioService::save);
		crud.setDeleteOperation(this.servicioService::delete);
		
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
