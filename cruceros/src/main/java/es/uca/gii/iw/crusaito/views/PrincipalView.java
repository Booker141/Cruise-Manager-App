package es.uca.gii.iw.crusaito.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import es.uca.gii.iw.crusaito.servicios.BarcoService;
import es.uca.gii.iw.crusaito.servicios.CiudadCruceroService;
import es.uca.gii.iw.crusaito.servicios.CruceroService;

//Se ha creado esta vista con motivos de impedir más duplicidad de código
public class PrincipalView extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	
	private CruceroService cruceroService;
	private CiudadCruceroService ciudadCruceroService;
	private BarcoService barcoService;

	public PrincipalView(CruceroService cruceroService, CiudadCruceroService ciudadCruceroService, BarcoService barcoService) {
		
		
		
		
		
	}



}


