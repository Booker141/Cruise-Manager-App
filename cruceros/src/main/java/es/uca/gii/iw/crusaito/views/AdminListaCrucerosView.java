package es.uca.gii.iw.crusaito.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "ListaCruceros",layout = MainView.class)
public class AdminListaCrucerosView extends Div{
	/*
	private CruceroService cruceroService;

	private GridCrud<Crucero> crud = new GridCrud<>(Crucero.class);
	
	@Autowired
	public AdminListaReservasView(CruceroService cruceroService) {
		this.cruceroService = cruceroService;
		
		this.add(crud);
		
		crud.getCrudFormFactory().setUseBeanValidation(true);
		
		crud.setFindAllOperation(this.cruceroService::load); 
		crud.setAddOperation(this.cruceroService::save);
		crud.setUpdateOperation(this.cruceroService::save);
		crud.setDeleteOperation(this.cruceroService::delete);
		
	}
	*/
}
