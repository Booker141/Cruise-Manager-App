package es.uca.gii.iw.crusaito.common;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;


public class Funciones {
	
	/**

     * Método que crea una notificación de aviso de un error y que es mostrada al usuario.

     * @param cadena El parámetro cadena define la cadena que mostrará el mensaje de error.

     */
	
	public static void notificacionError(String cadena)
	{
		Notification notification = new Notification(cadena, 3000, Position.TOP_CENTER);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		notification.open();
	}
	
	/**

     * Método que crea una notificación de exito y que es mostrada al usuario.

     * @param cadena El parámetro cadena define la cadena que mostrará el mensaje de exito.

     */
	
	public static void notificacionAcierto(String cadena)
	{
		Notification notification = new Notification(cadena, 2000, Position.TOP_CENTER);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.open();
	}
	


}
