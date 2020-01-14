package es.uca.gii.iw.crusaito.common;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;

import es.uca.gii.iw.crusaito.servicios.UsuarioService;

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
	

	/**

     * Método de tipo booleano que comprueba si existe un usuario dado.

     * @param servicio El parámetro servicio define el servicio de usuario.
     * @param s El parámetro s define la cadena que contiene el nombre de usuario que buscamos.
     * @return true/false si es o no encontrado el nombre de usuario.

     */
	
	public static boolean existeUsuario(UsuarioService servicio, String s)
	{
		if(servicio.findByUsername(s)!=null)
			return true;
		else
			return false;
	}
	
	/**

     * Método de tipo booleano que comprueba si existe el email de un usuario dado.

     * @param servicio El parámetro servicio define el servicio de usuario.
     * @param s El parámetro s define la cadena que contiene el email del usuario que buscamos.
     * @return true/false si es o no encontrado el email del usuario.

     */
	
	public static boolean existeEmail(UsuarioService servicio, String s)
	{
		if(servicio.findByEmail(s)!=null)
			return true;
		else
			return false;
	}
	
	/**

     * Método de tipo booleano que comprueba si existe el DNI de un usuario dado.

     * @param servicio El parámetro servicio define el servicio de usuario.
     * @param s El parámetro s define la cadena que contiene el DNI del usuario que buscamos.
     * @return true/false si es o no encontrado el DNI del usuario.

     */
	
	public static boolean existeDni(UsuarioService servicio, String s)
	{
		if(servicio.findByDni(s)!=null)
			return true;
		else
			return false;
	}
	

}
