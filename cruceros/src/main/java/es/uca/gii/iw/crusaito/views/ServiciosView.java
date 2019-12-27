package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import es.uca.gii.iw.crusaito.servicios.ServicioService;

@Route(value = "ServiciosView",layout = MainView.class)
public class ServiciosView extends HorizontalLayout{

	
	@Autowired
	public ServiciosView(ServicioService servicioService) {
		
	}
}
