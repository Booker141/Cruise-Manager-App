package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.clases.Camarote;
import es.uca.gii.iw.crusaito.servicios.CamaroteService;

@Route(value = "ListaReservas",layout = MainView.class)
@SuppressWarnings("serial")
@Secured("Admin")
public class AdminListaReservasView extends Div{
	/*
	private ReservaService reservaService;

	private GridCrud<Reserva> crud = new GridCrud<>(Reserva.class);
	//
	@Autowired
	public AdminListaReservasView(ReservaService reservaService) {
		this.reservaService = reservaService;
		
		this.add(crud);
		
		crud.getCrudFormFactory().setUseBeanValidation(true);
		
		crud.setFindAllOperation(this.reservaService::load); 
		crud.setAddOperation(this.reservaService::save);
		crud.setUpdateOperation(this.reservaService::save);
		crud.setDeleteOperation(this.reservaService::delete);
		
	}
	*/
}
