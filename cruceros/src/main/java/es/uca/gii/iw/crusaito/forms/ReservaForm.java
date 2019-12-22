package es.uca.gii.iw.crusaito.forms;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;

import es.uca.gii.iw.crusaito.clases.*;
import es.uca.gii.iw.crusaito.common.Funciones;
import es.uca.gii.iw.crusaito.servicios.*;
import es.uca.gii.iw.crusaito.views.MisReservasView;
import es.uca.gii.iw.crusaito.views.PrincipalView;


//Modificar

@SuppressWarnings("serial")
@Secured("Cliente")
public class ReservaForm extends PrincipalView{

	/*
	private Reserva reserva;
	
	VerticalLayout layout = new VerticalLayout();

	private DatePicker fechaInicio = new DatePicker("Fecha inicio");
	private DatePicker fechaFin = new DatePicker("Fecha final");
	private NumberField precio = new NumberField(Double.toString(UI.getCurrent().getSession().getAttribute(Reserva.class).getPrecio()) + "€");
	private Label label = new Label("Estados");
	ListBox<ReservaEstado> listaEstados = new ListBox<>();
	
    private Button guardar = new Button("Hacer reserva");
    private Button eliminar = new Button("Cancelar reserva");
  
	private Binder<Reserva> binder = new Binder<>(Reserva.class);
	
    private ReservaService reservaService;
	private MisReservasView misReservasView;
	
	
	public ReservaForm(ReservaService reservaService, MisReservasView misReservasView) {
		
		this.reservaService = reservaService;
		this.misReservasView = misReservasView;

	    HorizontalLayout buttons = new HorizontalLayout(guardar, eliminar);
	    add(fechaInicio, fechaFin, precio, label, listaEstados, buttons);
	    
	    
	    binder.bindInstanceFields(this);
	    
	    fechaInicio.setRequiredIndicatorVisible(true);
		fechaFin.setRequiredIndicatorVisible(true);
		precio.setRequiredIndicatorVisible(true);

		
	    //Validación campos obligatorios
	    
	    binder.forField(fechaInicio)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 2 y 32 letras",2, 32))
		.bind(Reserva::getFechaInicio, Reserva::setFechaInicio);
		
		binder.forField(fechaFin)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 2 y 32 letras",2, 32))
		.bind(Reserva::getFechaFin, Reserva::setFechaFin);
		
		binder.forField(precio)
		.asRequired("Este campo es obligatorio")
		.withValidator(new StringLengthValidator("Este campo debe tener entre 6 y 64 letras",6, 64))
		.bind(Reserva::getPrecio, Reserva::setPrecio);
		
	    
	    guardar.addClickListener(e -> {
	    	
			if(this.binder.isValid()) 
					save();
			else
				Funciones.notificacionError("Hay campos erróneos");
	    });
	    
	    eliminar.addClickListener(event -> delete());
	}
	
	
	
	private void save() {
	    Reserva reserva = binder.getBean();
	    reservaService.save(reserva);
	    misReservasView.actualizar();
	    setReserva(null);
	}
	
	private void delete() {
		Reserva reserva = binder.getBean();
	    reservaService.delete(reserva);
	    misReservasView.actualizar();
	    setReserva(null);
	}
	
	
	public void setReserva(Reserva r) {
	    binder.setBean(r);

	    if (r == null) {
	        setVisible(false);
	    } else {
	        setVisible(true);
	        fechaInicio.focus();
	    }
	}

}
*/