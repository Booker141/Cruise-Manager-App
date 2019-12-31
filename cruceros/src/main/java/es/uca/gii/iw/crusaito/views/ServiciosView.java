package es.uca.gii.iw.crusaito.views;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;
import es.uca.gii.iw.crusaito.servicios.ServicioService;

@Route(value = "ServiciosView",layout = MainView.class)
public class ServiciosView extends VerticalLayout{

	private ServicioService servicioService;
	private Grid<Servicio> grid = new Grid<>(Servicio.class);
	private List<Servicio> personList;
	private ListDataProvider<Servicio> dataProvider;
	
	private HeaderRow filterRow;
	private TextField sNombreField;
	
	private ComboBox<ServicioTipo> sTipoComboBox;
	
	@Autowired
	public ServiciosView(ServicioService servicioService) {
		
		this.servicioService = servicioService;
		personList = this.servicioService.load();
		dataProvider = new ListDataProvider<>(personList);
		grid.setDataProvider(dataProvider);
		
		grid.removeColumnByKey("id"); grid.removeColumnByKey("sTipo"); grid.removeColumnByKey("sAforoActual"); grid.removeColumnByKey("sAforoMaximo");
		grid.removeColumnByKey("usuarios");

		grid.setColumns("sNombre","sDescripcion","sPrecio","eItinerario");

		grid.getColumnByKey("sNombre").setHeader("Nombre");
		grid.getColumnByKey("sDescripcion").setHeader("Descripcion");
		grid.getColumnByKey("sPrecio").setHeader("Precio");
		grid.getColumnByKey("eItinerario").setHeader("Itinerario");
		//grid.getColumnByKey("sTipo").setHeader("Tipo");
		
		filterRow = grid.appendHeaderRow();
		
		//Primer filtro
		sNombreField = new TextField();
		sNombreField.addValueChangeListener(event -> dataProvider.addFilter(
		        servicio -> StringUtils.containsIgnoreCase(servicio.getsNombre(),
		                sNombreField.getValue())));

		sNombreField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(grid.getColumnByKey("sNombre")).setComponent(sNombreField);;
		sNombreField.setSizeFull();
		sNombreField.setPlaceholder("Filter");
		
		//Segundo filtro
		sTipoComboBox = new ComboBox<>("Filtrar por tipo: ");
		sTipoComboBox.setItems(ServicioTipo.values());
		
		sTipoComboBox.addValueChangeListener(event -> {
			applyFilter(dataProvider);
		});
		
		add(sTipoComboBox,grid);

	}
	
	private void applyFilter(ListDataProvider<Servicio> dataProvider) {
		dataProvider.clearFilters();
		if(sTipoComboBox.getValue() != null) {
			dataProvider.addFilter(servicio -> 
				sTipoComboBox.getValue() == servicio.getsTipo());
		}
	}
	
}
