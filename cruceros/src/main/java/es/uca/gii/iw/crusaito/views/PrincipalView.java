package es.uca.gii.iw.crusaito.views;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import es.uca.gii.iw.crusaito.security.SecurityUtils;

//Se ha creado esta vista con motivos de impedir más duplicidad de código
public class PrincipalView extends VerticalLayout implements BeforeEnterObserver{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		final boolean accessGranted =
				SecurityUtils.isAccessGranted(event.getNavigationTarget());
		if(!accessGranted) {
			if(SecurityUtils.isUserLoggedIn()) {
				event.rerouteTo(ProhibidoView.class);
			}
			else {
				event.rerouteTo(LoginView.class);
			}
		} 
	}

}
