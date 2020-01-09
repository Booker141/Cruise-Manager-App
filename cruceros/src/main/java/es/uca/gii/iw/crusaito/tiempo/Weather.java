package es.uca.gii.iw.crusaito.tiempo;

import java.net.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import es.uca.gii.iw.crusaito.clases.Ciudad;

@Component
@SuppressWarnings("serial")
public class Weather extends VerticalLayout{
	
	final static private String token = "83829333432ed41a10b897e79ed488a7";

	  	 
	/**
	*Función encargada de establecer una conexión con la API meteorológica y procesar la información recibida en formato XML.
	*
	*@param ciudad - ciudad define la ciudad de la que presentaremos su información meteorológica.
	*/
	
	@Autowired
	public void requestWeather(Ciudad ciudad) throws Exception
	{
		
		//String city = weatherObject.getCity();
		
		String URLtext = "http://api.openweathermap.org/data/2.5/weather?q="+ciudad.getcNombre()+"&mode=xml&appid=" + token;	
		URL url = new URL(URLtext);
		URLConnection conn = url.openConnection();	// establecemos conexion con la URL de la API
		
		HttpURLConnection conn2 = (HttpURLConnection) conn;
		int statusCode = conn2.getResponseCode();	// obtenemos el codigo de respuesta de la API
		
		if(statusCode != 404)	// peticion exitosa
		{
			//Obtenemos el documento XML
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document file = builder.parse(url.openStream());
			
			// Recorremos las etiquetas XML para acceder al dato tiempo actual
			String temperatura = file.getElementsByTagName("temperature").item(0).getAttributes().item(0).getTextContent();
			String humedad = file.getElementsByTagName("humidity").item(0).getAttributes().item(0).getTextContent();
			String presion = file.getElementsByTagName("pressure").item(0).getAttributes().item(0).getTextContent();
			String viento = file.getElementsByTagName("speed").item(0).getAttributes().item(0).getTextContent();
			String direccion = file.getElementsByTagName("direction").item(0).getAttributes().item(0).getTextContent();
			String icono = file.getElementsByTagName("weather").item(2).getAttributes().item(2).getTextContent();
			
			
			
			String iconoURL = "http://openweathermap.org/img/w" + icono + ".png" ;
			
			H2 Titulo = new H2("Información meteorológica: ");
	        Label Temperatura = new Label("Temperatura: " + (Double.parseDouble(temperatura)-273.15));
	        Label Humedad = new Label("Humedad: " + humedad);
	        Label Presion = new Label("Presion: " + presion);
	        Label Viento = new Label("Viento: " + viento);
	        Label Direccion = new Label("Direccion: " + direccion);
	        Label iconoVista = new Label(iconoURL);
	        
	        VerticalLayout layout = new VerticalLayout(Titulo, Temperatura, Humedad, Presion, Viento, Direccion, iconoVista);
	        add(layout);
			
		}
		
	}
}
	
