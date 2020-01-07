package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.gatanaso.MultiselectComboBox;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.clases.CiudadCrucero;
import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.servicios.CiudadCruceroService;
import es.uca.gii.iw.crusaito.servicios.CiudadService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;

@Route(value = "ListaCiudadesCruceros",layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Admin")
public class AdminListaCiudadCruceroView extends VerticalLayout{
	
	private CiudadService ciudadService;
	private CruceroService cruceroService;
	private CiudadCruceroService ciudadCruceroService;
	
	private GridCrud<CiudadCrucero> crud = new GridCrud<>(CiudadCrucero.class);
	
	@Autowired
	public AdminListaCiudadCruceroView(CiudadService ciudadService, CruceroService cruceroService,
			CiudadCruceroService ciudadCruceroService) {
		
		this.ciudadService = ciudadService;
		this.cruceroService = cruceroService;
		this.ciudadCruceroService = ciudadCruceroService;
		
		crud.getGrid().setColumns("ciudad", "crucero", "fechaLlegada",
				"horaLlegada","fechaSalida","horaSalida");
		crud.getGrid().getColumnByKey("ciudad").setHeader("Ciudad");
		crud.getGrid().getColumnByKey("crucero").setHeader("Crucero");
		crud.getGrid().getColumnByKey("fechaLlegada").setHeader("Fecha llegada");
		crud.getGrid().getColumnByKey("horaLlegada").setHeader("Hora llegada");
		crud.getGrid().getColumnByKey("fechaSalida").setHeader("Fecha salida");
		crud.getGrid().getColumnByKey("horaSalida").setHeader("Hora salida");
		
		DefaultCrudFormFactory<CiudadCrucero> formFactory = new DefaultCrudFormFactory<>(CiudadCrucero.class);

		formFactory.setVisibleProperties("ciudad", "crucero", "fechaLlegada",
				"horaLlegada", "fechaSalida", "horaSalida");
		
		formFactory.setFieldProvider("ciudad", ()-> {
			ComboBox<Ciudad> ciudadField = new ComboBox<>();
			ciudadField.setItems(this.ciudadService.load());
			return ciudadField;
		});
		
		formFactory.setFieldProvider("crucero", () -> {
			ComboBox<Crucero> cruceroField = new ComboBox<>();
			cruceroField.setItems(this.cruceroService.load());
			return cruceroField;
		});
		
		formFactory.setFieldProvider("horaLlegada", ()->{
			
			ComboBox<String> horaLlegadaField = new ComboBox<>();
			horaLlegadaField.setItems("01","02","03","04","05","06","07",
					"08","09","10","11","12","13","14","15","16","17","18",
					"19","20","21","22","23","00");
			return horaLlegadaField;
		});

		
		formFactory.setFieldProvider("horaSalida", ()->{
			ComboBox<String> horaSalidaField = new ComboBox<>();
			horaSalidaField.setItems("01","02","03","04","05","06","07",
					"08","09","10","11","12","13","14","15","16","17","18",
					"19","20","21","22","23","00");
			return horaSalidaField;
		});
		
		crud.setCrudFormFactory(formFactory);
		
		crud.setFindAllOperation(this.ciudadCruceroService::load); 
		crud.setAddOperation(this.ciudadCruceroService::save);
		crud.setUpdateOperation(this.ciudadCruceroService::save);
		crud.setDeleteOperation(this.ciudadCruceroService::delete);
		
		this.add(crud);
	}
	
}
