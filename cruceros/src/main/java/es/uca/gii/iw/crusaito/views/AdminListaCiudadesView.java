package es.uca.gii.iw.crusaito.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.gatanaso.MultiselectComboBox;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.servicios.BarcoService;
import es.uca.gii.iw.crusaito.servicios.CiudadService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;
import es.uca.gii.iw.crusaito.servicios.ServicioService;

@Route(value = "ListaCiudades",layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Admin")
public class AdminListaCiudadesView extends VerticalLayout{
	
	private CiudadService ciudadService;
	private CruceroService cruceroService;
	
	private GridCrud<Ciudad> crud = new GridCrud<>(Ciudad.class);
	
	@Autowired
	public AdminListaCiudadesView(CiudadService ciudadService, CruceroService cruceroService) {
		this.ciudadService = ciudadService;
		this.cruceroService = cruceroService;
		
		crud.getGrid().setColumns("cNombre");
		crud.getGrid().getColumnByKey("cNombre").setHeader("Nombre");
		crud.getGrid().addColumn(Ciudad::getCruceros).setHeader("Cruceros");
		
		DefaultCrudFormFactory<Ciudad> formFactory = new DefaultCrudFormFactory<>(Ciudad.class);

		formFactory.setVisibleProperties("cNombre","cruceros");
		
		formFactory.setFieldProvider("cruceros", ()->{
			MultiselectComboBox<Crucero> multibox = new MultiselectComboBox<>();
			
			multibox.setLabel("Selecciona cruceros");
			multibox.setItems(this.cruceroService.load());
			multibox.setItemLabelGenerator(Crucero::getcNombre);
			return multibox;
		}); 
		
		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.ciudadService::load); 
		crud.setAddOperation(this.ciudadService::save);
		crud.setUpdateOperation(this.ciudadService::save);
		crud.setDeleteOperation(this.ciudadService::delete);
		
		this.add(crud);
	}
	
}

