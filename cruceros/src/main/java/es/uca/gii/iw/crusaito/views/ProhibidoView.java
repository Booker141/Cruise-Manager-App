package es.uca.gii.iw.crusaito.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ProhibidoView extends VerticalLayout{
	private static final long serialVersionUID = 1L;

	public ProhibidoView() {
		Label content = new Label(
		        "Tiene prohibido el acceso a esta página");
		NativeButton buttonInside = new NativeButton("Cerrar");
		Notification notification = new Notification(content, buttonInside);
		notification.setDuration(3000);
		buttonInside.addClickListener(event -> notification.close());
		notification.setPosition(Position.MIDDLE);
	}

}
