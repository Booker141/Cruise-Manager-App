package es.uca.gii.iw.crusaito.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ProhibidoView")
@SuppressWarnings("serial")
public class ProhibidoView extends VerticalLayout {

	private Label content = new Label("Tiene prohibido el acceso a esta página. ");
	private Button volver = new Button("Volver");
	private Notification notification;
	
	@Autowired
	public ProhibidoView() {

		volver.addClickListener(event -> {
			UI.getCurrent().navigate("");
			notification.close();
		});
		
		volver.getStyle().set("margin-right", "0.5rem");

		notification = new Notification(content, volver);

		notification.setPosition(Position.MIDDLE);
		
		notification.open();
	}

}
