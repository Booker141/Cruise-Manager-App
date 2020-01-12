package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ProhibidoView")
@SuppressWarnings("serial")
public class ProhibidoView extends VerticalLayout {

	private Label content = new Label("Tiene prohibido el acceso a esta página. ");
	private NativeButton buttonInside = new NativeButton("Volver");
	private Notification notification;
	
	@Autowired
	public ProhibidoView() {

		notification = new Notification(content,buttonInside);

		notification.setPosition(Position.MIDDLE);
		
		buttonInside.addClickListener(event -> {
			UI.getCurrent().navigate("MainView");
			notification.close();
		});
		
		notification.open();
	}

}
