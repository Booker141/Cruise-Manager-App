package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.servicios.BarcoService;

@Route(value = "ListaBarcos",layout = MainView.class)
public class AdminListaBarcosView extends Div{

	private BarcoService barcoService;

	private GridCrud<Barco> crud = new GridCrud<>(Barco.class);
	
	@Autowired
	public AdminListaBarcosView(BarcoService barcoService) {
		this.barcoService = barcoService;
		
		this.add(crud);
		
		crud.getCrudFormFactory().setUseBeanValidation(true);
		
		crud.setFindAllOperation(this.barcoService::load); 
		crud.setAddOperation(this.barcoService::save);
		crud.setUpdateOperation(this.barcoService::save);
		crud.setDeleteOperation(this.barcoService::delete);
		
	}
	
}
