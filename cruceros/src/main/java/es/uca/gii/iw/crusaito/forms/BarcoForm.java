package es.uca.gii.iw.crusaito.forms;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.servicios.BarcoService;
import es.uca.gii.iw.crusaito.views.AdminListaBarcosView;

public class BarcoForm extends FormLayout{
	
	private AdminListaBarcosView adminBarcos;
	private BarcoService barcoService;
	
	private TextField bNombre = new TextField("Nombre");
	private TextField bImagen = new TextField("Ruta imagen");
	private TextField bAforoPasajeros = new TextField("Capacidad de pasajeros");
	private DatePicker bPuestaServicio = new DatePicker("Puesta en servicio");
	private TextField bDetalles = new TextField("Detalles");
	
	private Button save = new Button("Guardar");
	private Button delete = new Button("Borrar");
	
	private Binder<Barco> binder = new Binder<>(Barco.class);
	
	@Autowired
	public BarcoForm(AdminListaBarcosView adminBarcos, BarcoService barcoService) {
		this.adminBarcos = adminBarcos;
		this.barcoService = barcoService;
		
		binder.forField(bAforoPasajeros).withConverter(new StringToIntegerConverter("Not a number")).bind(Barco::getbAforoPasajeros, Barco::setbAforoPasajeros);
		HorizontalLayout botones = new HorizontalLayout(save,delete);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		add(bNombre,bImagen,bAforoPasajeros,bPuestaServicio,bDetalles,botones);
		binder.bindInstanceFields(this);
		
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
	}
	
	public void setBarco(Barco barco) {
		binder.setBean(barco);
		if(barco == null) {
			setVisible(false);
		}else {
			setVisible(true);
			bNombre.focus();
		}
	}
	
	private void save() {
		Barco barco = binder.getBean();
		barcoService.save(barco);
		//adminBarcos.updateList();
		setBarco(null);
	}
	
	private void delete() {
		Barco barco = binder.getBean();
		barcoService.delete(barco);
		//adminBarcos.updateList();
		setBarco(null);
	}
	
}
