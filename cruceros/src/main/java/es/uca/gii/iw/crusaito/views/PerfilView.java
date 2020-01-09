package es.uca.gii.iw.crusaito.views;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Servicio;
import es.uca.gii.iw.crusaito.clases.ServicioUsuario;
import es.uca.gii.iw.crusaito.security.SecurityUtils;
import es.uca.gii.iw.crusaito.servicios.ServicioService;
import es.uca.gii.iw.crusaito.servicios.ServicioUsuarioService;
import es.uca.gii.iw.crusaito.servicios.UsuarioService;

@SuppressWarnings("serial")
@Route(value = "Perfil",layout = MainView.class)
@Secured("Cliente")
public class PerfilView extends Div{
	
	private ServicioUsuarioService servicioUsuarioService;
	private UsuarioService usuarioService;
	private ServicioService servicioService;
	
	private Grid<ServicioUsuario> grid = new Grid<>(ServicioUsuario.class);
	private List<ServicioUsuario> serviceList;
	private ListDataProvider<ServicioUsuario> dataProvider;

	@Autowired
	public PerfilView(ServicioService servicioService, UsuarioService susuarioService) {
		
		this.servicioService = servicioService;
		this.usuarioService = usuarioService;
		this.servicioUsuarioService = servicioUsuarioService;
		
		serviceList = this.servicioUsuarioService.findByUsuario(this.usuarioService.findByUsername(SecurityUtils.currentUsername()));
		
		dataProvider = new ListDataProvider<>(serviceList);
		grid.setDataProvider(dataProvider);
		
		double dTotal = 0;
		
		
		/*
		for(ServicioUsuario servicioUsuario: servicioUsuarioService.findAll()) {
            for (Servicio servicio : servicioService.findAll()) {
                if (servicio.getsNombre().equals(servicioUsuario.getServicio().getsNombre())) {
                    dTotal = dTotal + servicio.getsPrecio();
                }
            }
        }*/
		
		
		
		grid.removeColumnByKey("id"); grid.removeColumnByKey("sTipo"); grid.removeColumnByKey("sAforoActual"); grid.removeColumnByKey("sAforoMaximo");
		grid.removeColumnByKey("serviciosUsuarios"); grid.removeColumnByKey("sPrecio"); grid.removeColumnByKey("sImagen");

		grid.setColumns("servicio", "participantes", "precio");

		grid.getColumnByKey("sNombre").setHeader("Nombre");
		grid.getColumnByKey("sDescripcion").setHeader("Descripcion");
		grid.addColumn(new NumberRenderer<>(ServicioUsuario::getPrecio,"%(,.2f €",new Locale("es"),"0.00 €")).setHeader("Precio");
		grid.getColumnByKey("eItinerario").setHeader("Itinerario");
		grid.getColumnByKey("sTipo").setHeader("Tipo");
		
		
		grid.setSelectionMode(Grid.SelectionMode.NONE);

		Label total = new Label("Factura total: " + dTotal);
		
		add(grid, total);
		
		
	}
	
}
