package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.servicios.BarcoService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;

@Route(value = "ListaBarcos",layout = MainView.class)
@Secured("Admin")
@SuppressWarnings("serial")
public class AdminListaBarcosView extends VerticalLayout{

	private BarcoService barcoService;
	private CruceroService cruceroService;

	private GridCrud<Barco> crud = new GridCrud<>(Barco.class);
	
	@Autowired
	public AdminListaBarcosView(BarcoService barcoService, CruceroService cruceroService) {
		this.barcoService = barcoService;
		this.cruceroService = cruceroService;
		
		crud.getGrid().setColumns("bNombre","bImagen","bAforoPasajeros","bAforoTripulantes","bPeso","bFchPuestaServicio","bDescripcion");
		crud.getGrid().getColumnByKey("bNombre").setHeader("Nombre");
		crud.getGrid().getColumnByKey("bDescripcion").setHeader("Descripcion");
		crud.getGrid().getColumnByKey("bPeso").setHeader("Peso");
		crud.getGrid().getColumnByKey("bAforoPasajeros").setHeader("Pasajeros max.");
		crud.getGrid().getColumnByKey("bAforoTripulantes").setHeader("Tripulantes max.");
		crud.getGrid().getColumnByKey("bFchPuestaServicio").setHeader("Fecha servicio");
		crud.getGrid().getColumnByKey("bImagen").setHeader("Imagen");
		crud.getGrid().addColumn(Barco::getCrucero).setHeader("Crucero");
		
		crud.getCrudFormFactory().setVisibleProperties("bNombre","bImagen","bAforoPasajeros","bAforoTripulantes","bPeso","bFchPuestaServicio","bDescripcion");
		crud.setFindAllOperation(this.barcoService::load); 
		crud.setAddOperation(this.barcoService::save);
		crud.setUpdateOperation(this.barcoService::save);
		crud.setDeleteOperation(this.barcoService::delete);
		
		add(crud);
	}
	
}
