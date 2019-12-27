package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Barco;
import es.uca.gii.iw.crusaito.clases.Camarote;
import es.uca.gii.iw.crusaito.servicios.BarcoService;
import es.uca.gii.iw.crusaito.servicios.CamaroteService;

@Route(value = "ListaCamarotes",layout = MainView.class)
public class AdminListaCamarotesView extends Div{

	private CamaroteService camaroteService;

	private GridCrud<Camarote> crud = new GridCrud<>(Camarote.class);
	//
	@Autowired
	public AdminListaCamarotesView(CamaroteService camaroteService) {
		this.camaroteService = camaroteService;
		
		this.add(crud);
		
		crud.getCrudFormFactory().setUseBeanValidation(true);
		
		crud.setFindAllOperation(this.camaroteService::load); 
		crud.setAddOperation(this.camaroteService::save);
		crud.setUpdateOperation(this.camaroteService::save);
		crud.setDeleteOperation(this.camaroteService::delete);
		
	}
}
