package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.CheckBoxGroupProvider;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.gatanaso.MultiselectComboBox;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.ServicioService;

@Route(value = "ListaServicios",layout = MainView.class)
@Secured("Admin")
@SuppressWarnings("serial")
public class AdminListaServiciosView extends VerticalLayout{

	private ServicioService servicioService;
	private CruceroService cruceroService;
	
	private GridCrud<Servicio> crud = new GridCrud<>(Servicio.class);
	
	@Autowired
	public AdminListaServiciosView(ServicioService servicioService, CruceroService cruceroService) {
		this.servicioService = servicioService;
		this.cruceroService = cruceroService;
				
		crud.getGrid().setColumns("sNombre","sDescripcion","sPrecio","sTipo","sAforoActual","sAforoMaximo","sFecha","eItinerario");
		crud.getGrid().getColumnByKey("sNombre").setHeader("Nombre");
		crud.getGrid().getColumnByKey("sDescripcion").setHeader("Descripcion");
		crud.getGrid().getColumnByKey("sPrecio").setHeader("Precio");
		crud.getGrid().getColumnByKey("sTipo").setHeader("Tipo");
		crud.getGrid().getColumnByKey("sAforoActual").setHeader("Aforo actual");
		crud.getGrid().getColumnByKey("sAforoMaximo").setHeader("Aforo maximo");
		crud.getGrid().getColumnByKey("sFecha").setHeader("Fecha");
		crud.getGrid().getColumnByKey("eItinerario").setHeader("Itinerario");

		crud.getGrid().addColumn(Servicio::getCruceros).setHeader("Cruceros");
		
		DefaultCrudFormFactory<Servicio> formFactory = new DefaultCrudFormFactory<>(Servicio.class);
		
		formFactory.setUseBeanValidation(true);
		formFactory.setVisibleProperties("sNombre","sDescripcion","sPrecio","sTipo","sAforoActual","sAforoMaximo","sFecha","eItinerario");
		
		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.servicioService::load); 
		crud.setAddOperation(this.servicioService::save);
		crud.setUpdateOperation(this.servicioService::save);
		crud.setDeleteOperation(this.servicioService::delete);
		
		add(crud);
	}
}
