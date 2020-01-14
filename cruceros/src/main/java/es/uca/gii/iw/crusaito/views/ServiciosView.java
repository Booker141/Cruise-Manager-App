package es.uca.gii.iw.crusaito.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "ServiciosView",layout = MainView.class)
@Secured("Cliente")
public class ServiciosView extends VerticalLayout implements BeforeEnterObserver{

	private UsuarioService usuarioService;
	private ServicioService servicioService;
	
	private Grid<Servicio> grid = new Grid<>(Servicio.class);
	private List<Servicio> serviceList = new ArrayList<Servicio>();
	private ListDataProvider<Servicio> dataProvider;
	
	private HeaderRow filterRow;
	private TextField sNombreField;
	private NumberField participantesField = new NumberField("Numero de personas");
	private H6 sNombre;
	private H6 sDescripcion;
	private H6 sTipo;
	private Image sImagenImage;
	
	private H6 descuentosField = new H6("Descuentos no acumulables");
	private H6 descuentos1Field = new H6("1. Si eres mayor de 55 años, se le aplicará un 25% de descuento sobre el total.");
	private H6 descuentos2Field = new H6("2. Si vas acompañado de 3 o más participantes, se le aplicará un 20% de descuento sobre el total.");
	private Dialog dialog;
	
	private Notification notification;
	private Notification servicioLleno;
	
	private Label label;
	private Button cancelButton;
	private Button confirmButton;
	private Button reservaButton;
	
	private ComboBox<ServicioTipo> sTipoComboBox;
	
	@Autowired
	public ServiciosView(ServicioService servicioService, UsuarioService usuarioService) {
		
		this.servicioService = servicioService;
		this.usuarioService = usuarioService;
				
		dataProvider = new ListDataProvider<>(serviceList);
		grid.setDataProvider(dataProvider);
		
		grid.removeColumnByKey("id"); grid.removeColumnByKey("sTipo"); grid.removeColumnByKey("sAforoActual"); grid.removeColumnByKey("sAforoMaximo");
		grid.removeColumnByKey("serviciosUsuarios"); grid.removeColumnByKey("sPrecio"); grid.removeColumnByKey("sImagen");

		grid.setColumns("sNombre","sDescripcion","ciudad","sTipo");

		grid.getColumnByKey("sNombre").setHeader("Nombre");
		grid.getColumnByKey("sDescripcion").setHeader("Descripcion");
		grid.addColumn(new NumberRenderer<>(Servicio::getsPrecio,"%(,.2f €",new Locale("es"),"0.00 €")).setHeader("Precio");
		grid.getColumnByKey("ciudad").setHeader("Ciudad");
		grid.getColumnByKey("sTipo").setHeader("Tipo");
		
		grid.setSelectionMode(Grid.SelectionMode.NONE);

		dialog = new Dialog();
		
		sNombre = new H6();
		sNombre.setTitle("Nombre");
		
		sDescripcion = new H6();
		sNombre.setTitle("Descripcion");
		
		sTipo = new H6();
		sTipo.setTitle("Tipo");
		
		sImagenImage = new Image();
		sImagenImage.setTitle("Imagen");
		sImagenImage.setHeight("100%");
		sImagenImage.setWidth("100%");
		
		notification = new Notification();
		notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
		
		label = new Label("Confirme la reserva");
		
		cancelButton = new Button("Cancelar", e -> notification.close());

		confirmButton = new Button("Confirmar"); 
		confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		notification.add(label, cancelButton, confirmButton);

		label.getStyle().set("margin-right", "0.5rem");
		cancelButton.getStyle().set("margin-right", "0.5rem");

		reservaButton = new Button("Reservar");
		reservaButton.addClickListener(event -> {
			notification.open();
		});
		
		reservaButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		servicioLleno = new Notification();
		Label labelLleno = new Label("No hay hueco para esos participantes.");
		Button llenoButton = new Button("Aceptar", e -> servicioLleno.close());
		
		servicioLleno.add(labelLleno,llenoButton);
		
		
		dialog.add(sNombre, sDescripcion, sTipo, sImagenImage,
				descuentosField, descuentos1Field, descuentos2Field, 
				reservaButton, participantesField);
		
		grid.addItemClickListener(
		        event -> {
		        	sNombre.setText(event.getItem().getsNombre());
		        	sDescripcion.setText(event.getItem().getsDescripcion());
		            sTipo.setText(String.valueOf(event.getItem().getsTipo()));
		            sImagenImage.setSrc(event.getItem().getsImagen());
		            
		            if(String.valueOf(event.getItem().getsTipo())!="Tienda") {
		            	descuentosField.setVisible(true);
		            	descuentos1Field.setVisible(true);
		            	descuentos2Field.setVisible(true);
		            	participantesField.setVisible(true);
			            reservaButton.setVisible(true);
			            
		            	participantesField.setValue(1d);
			    		participantesField.setHasControls(true);
			    		participantesField.setMin(1);
			    		participantesField.setMax(event.getItem().getsAforoMaximo());
		    				            
			            confirmButton.addClickListener(e -> {
			            	Servicio servicio = event.getItem();
			            	
			            	if(servicio.AforoHuecoLibre(participantesField.getValue().intValue())) {
			            		Usuario user = buscarUsuarioLogin();
			            		servicioService.addServicioToUsuario(servicio, user, participantesField.getValue().intValue());
			            		notification.close();
			            	}else {
			            		servicioLleno.open();
			            		notification.close();
			            	}
			            	
			            });
		            }else {
		            	descuentosField.setVisible(false);
		            	descuentos1Field.setVisible(false);
		            	descuentos2Field.setVisible(false);
		            	participantesField.setVisible(false);
			            reservaButton.setVisible(false);
		            }
		    		dialog.open();
		    		
		        });
		
		filterRow = grid.appendHeaderRow();
		
		/**
		 * Primer filtro
		 */
		
		sNombreField = new TextField();
		sNombreField.addValueChangeListener(event -> dataProvider.addFilter(
		        servicio -> StringUtils.containsIgnoreCase(servicio.getsNombre(),
		                sNombreField.getValue())));

		sNombreField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(grid.getColumnByKey("sNombre")).setComponent(sNombreField);;
		sNombreField.setSizeFull();
		sNombreField.setPlaceholder("Filtrar");
		
		/**
		 * Segundo filtro
		 */
		
		sTipoComboBox = new ComboBox<>("Filtrar por tipo: ");
		sTipoComboBox.setItems(ServicioTipo.values());
		
		sTipoComboBox.addValueChangeListener(event -> {
			applyFilter(dataProvider);
		});
		
		grid.setSizeFull();
		this.setSizeFull();
		add(sTipoComboBox, grid);
	}
	
	private void applyFilter(ListDataProvider<Servicio> dataProvider) {
		dataProvider.clearFilters();
		if(sTipoComboBox.getValue() != null) {
			dataProvider.addFilter(servicio -> 
				sTipoComboBox.getValue() == servicio.getsTipo());
		}
	}
	
	public Usuario buscarUsuarioLogin() {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.usuarioService.findByUsername(username);
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
		}else{
			serviceList = this.servicioService.findCruceroByUsername(SecurityUtils.currentUsername());
			dataProvider = new ListDataProvider<>(serviceList);
			grid.setDataProvider(dataProvider);
		}
	}
	
}
