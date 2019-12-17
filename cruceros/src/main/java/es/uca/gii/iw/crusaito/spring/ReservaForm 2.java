package es.uca.gii.iw.crusaito.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;

import es.uca.gii.iw.crusaito.clases.Crucero;
import es.uca.gii.iw.crusaito.clases.Reserva;

//Modificar

/*
public class ReservaForm {
	
	private Reserva reserva;
	private Crucero crucero;
	private ComboBox<reserva.getEstado()> estado = new ComboBox<>("Estado");
	private DatePicker fechaIni = new DatePicker("Fecha inicio");
	private DatePicker fechaFin = new DatePicker("Fecha final");
	private NumberField precio = new NumberField("Precio");
	
	private Button guardar = new Button("Guardar");
	private Button cancelar = new Button("Cancelar");
	
	private Binder<Reserva> binder = new Binder<>(Reserva.class);
	
	private ReservaService serviceR;
	private ListaReservasView reservasView;
	
	
	public ReservaForm(ReservaService serviceR, ListaReservasView reservasView) {
		
		this.serviceR = serviceR;
		this.reservasView = reservasView;

	    HorizontalLayout buttons = new HorizontalLayout(guardar, eliminar);
	    guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	    add(fechaIni, fechaFin, precio, fianza, seguro, buttons);
	    
	   // vehiculo.setValue(binder.getBean().getNombreVehiculo());
	   // user.setValue(binder.getBean().getUsername());
	    
	    estado.setItems(ReservaEstado.values());
	    
	    binder.bindInstanceFields(this);
	    
	    prepararCampos();
	    
	    guardar.addClickListener(e -> {
			if(this.binder.isValid()) 
					save();
			else
				FuncionesView.notificacion("Hay campos con errores o vacíos, por favor, revíselos");
	    });
	    
	    eliminar.addClickListener(event -> delete());
	}
	
	
	
	private void save() {
	    Reserva reserva = binder.getBean();
	    serviceR.save(reserva);
	    reservasView.updateList();
	    setReserva(null);
	}
	
	private void delete() {
		Reserva reserva = binder.getBean();
	    serviceR.delete(reserva);
	    reservasView.updateList();
	    setReserva(null);
	}
	
	
	public void setReserva(Reserva r) {
	    binder.setBean(r);

	    if (r == null) {
	        setVisible(false);
	    } else {
	        setVisible(true);
	        fechaIni.focus();
	    }
	}
	
	private void prepararCampos()
	{

		fechaIni.setRequiredIndicatorVisible(true);
		fechaFin.setRequiredIndicatorVisible(true);
		precio.setRequiredIndicatorVisible(true);
		fianza.setRequiredIndicatorVisible(true);
		estado.setRequiredIndicatorVisible(true);
		
		binder.forField(fechaIni)
		.asRequired("Este campo es obligatorio")
		.bind(Reserva::getFechaIni, Reserva::setFechaIni);
		
		binder.forField(fechaFin)
		.asRequired("Este campo es obligatorio")
		.bind(Reserva::getFechaFin,Reserva::setFechaFin);
		
		binder.forField(precio)
		.asRequired("Este campo es obligatorio")
		.bind(Reserva::getPrecio,Reserva::setPrecio);
		
		binder.forField(fianza)
		.asRequired("Este campo es obligatorio")
		.bind(Reserva::getFianza,Reserva::setFianza);
		
		binder.forField(estado)
		.asRequired("Este campo es obligatorio")
		.bind(Reserva::getEstado,Reserva::setEstado);

	}

}
*/