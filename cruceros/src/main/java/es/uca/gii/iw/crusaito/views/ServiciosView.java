package es.uca.gii.iw.crusaito.views;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioTipo;
import es.uca.gii.iw.crusaito.clases.Usuario;
import es.uca.gii.iw.crusaito.servicios.ServicioService;

@Route(value = "ServiciosView",layout = MainView.class)
public class ServiciosView extends HorizontalLayout{

	private ServicioService servicioService;
	private Grid<Servicio> grid = new Grid<>(Servicio.class);
	private List<Servicio> personList;
	private ListDataProvider<Servicio> dataProvider;
	
	@Autowired
	public ServiciosView(ServicioService servicioService) {
		
		this.servicioService = servicioService;
		personList = servicioService.load();
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
		
		
		HeaderRow filterRow = grid.appendHeaderRow();
		
		//Primer filtro
		TextField sNombreField = new TextField();
		sNombreField.addValueChangeListener(event -> dataProvider.addFilter(
		        servicio -> StringUtils.containsIgnoreCase(servicio.getsNombre(),
		                sNombreField.getValue())));

		sNombreField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(grid.getColumnByKey("sNombre")).setComponent(sNombreField);;
		sNombreField.setSizeFull();
		sNombreField.setPlaceholder("Filter");
		
		add(grid);

	}
	
	private void listarServicios() {
		grid.setItems(servicioService.load());
	}
}
