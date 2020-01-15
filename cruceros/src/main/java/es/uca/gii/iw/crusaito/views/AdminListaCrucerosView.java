package es.uca.gii.iw.crusaito.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.gatanaso.MultiselectComboBox;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.BarcoService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.ServicioService;

@Route(value = "ListaCruceros",layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Admin")
public class AdminListaCrucerosView extends VerticalLayout implements BeforeEnterObserver{
	
	private CruceroService cruceroService;
	private BarcoService barcoService;
	private ServicioService servicioService;
	
	private GridCrud<Crucero> crud = new GridCrud<>(Crucero.class);
	
	@Autowired
	public AdminListaCrucerosView(CruceroService cruceroService, BarcoService barcoService, ServicioService servicioService) {
		this.cruceroService = cruceroService;
		this.barcoService = barcoService;
		this.servicioService = servicioService;
		
		crud.getGrid().setColumns("cNombre","cOrigen","cDestino","cDuracion","cDescripcion","cImagen","cPrecio");
		crud.getGrid().getColumnByKey("cNombre").setHeader("Nombre");
		crud.getGrid().getColumnByKey("cDescripcion").setHeader("Descripcion");
		crud.getGrid().getColumnByKey("cPrecio").setHeader("Precio");
		crud.getGrid().getColumnByKey("cOrigen").setHeader("Origen");
		crud.getGrid().getColumnByKey("cDestino").setHeader("Destino");
		crud.getGrid().getColumnByKey("cDuracion").setHeader("Duracion");
		crud.getGrid().getColumnByKey("cImagen").setHeader("Imagen");
		
		crud.getGrid().addColumn(Crucero::getBarco).setHeader("Barco");
		crud.getGrid().addColumn(Crucero::getServicios).setHeader("Servicios");
		
		DefaultCrudFormFactory<Crucero> formFactory = new DefaultCrudFormFactory<>(Crucero.class);

		formFactory.setVisibleProperties("cNombre","cDescripcion","cPrecio","cOrigen","cDestino","cDuracion","cImagen","barco","servicios");

		formFactory.setFieldProvider("barco", () -> {
			
			List<Barco> barcoList = this.barcoService.findByCruceroIsNull();
			ItemFilter<Barco> filter = (barco, filterString) -> barco.getbNombre().startsWith(filterString);
		    ComboBox<Barco> combobox = new ComboBox<>();
		    combobox.setItems(filter,barcoList);
		    combobox.setItemLabelGenerator(Barco::getbNombre);
		    combobox.setClearButtonVisible(true);
		    
		    return combobox;
		});
				
		formFactory.setFieldProvider("servicios", ()->{
			MultiselectComboBox<Servicio> multibox = new MultiselectComboBox<>();
			
			multibox.setLabel("Selecciona servicios");
			multibox.setItems(this.servicioService.load());
			multibox.setItemLabelGenerator(Servicio::getsNombre);
			return multibox;
		}); 
		
		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.cruceroService::load); 
		crud.setAddOperation(this.cruceroService::save);
		crud.setUpdateOperation(this.cruceroService::save);
		crud.setDeleteOperation(this.cruceroService::delete);
		
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
