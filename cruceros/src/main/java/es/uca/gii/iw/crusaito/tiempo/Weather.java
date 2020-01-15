package es.uca.gii.iw.crusaito.tiempo;

import java.net.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import es.uca.gii.iw.crusaito.clases.Ciudad;
import es.uca.gii.iw.crusaito.common.Funciones;

@Component
public class Weather{
	
	final static private String token = "83829333432ed41a10b897e79ed488a7";

	String URLtext;
	URL url;
	URLConnection conn;
	HttpURLConnection conn2;
	int statusCode;
	
	String temperatura;
	String humedad;
	String viento;
	String direccion;
	
	String icono;
	String iconoURL;
	
	public Weather() {}
	
	public Weather(Ciudad ciudad) {
		
		try {
			
			URLtext = "http://api.openweathermap.org/data/2.5/weather?q="+ciudad.getcNombre()+"&units=metric&lang=es&mode=xml&appid=" + token;	
			url = new URL(URLtext);
			conn = url.openConnection();	// establecemos conexion con la URL de la API
			
			conn2 = (HttpURLConnection) conn;
			statusCode = conn2.getResponseCode();	// obtenemos el codigo de respuesta de la API
					
			if(statusCode != 404)	// peticion exitosa
			{
				//Obtenemos el documento XML
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document file = builder.parse(url.openStream());
				
				// Recorremos las etiquetas XML para acceder al dato tiempo actual
				temperatura = file.getElementsByTagName("temperature").item(0).getAttributes().item(0).getTextContent();
				humedad = file.getElementsByTagName("humidity").item(0).getAttributes().getNamedItem("value").getTextContent();
				viento = file.getElementsByTagName("wind").item(0).getFirstChild().getAttributes().getNamedItem("value").getTextContent();
				direccion = file.getElementsByTagName("wind").item(0).getLastChild().getAttributes().getNamedItem("name").getTextContent();
				icono = file.getElementsByTagName("weather").item(0).getAttributes().getNamedItem("icon").getTextContent();
				
				iconoURL = "http://openweathermap.org/img/wn/" + icono + "@2x.png" ;
				
			}
		}catch(Exception e) {
			
			Funciones.notificacionError("No se ha podido obtener el tiempo en la ciudad destino");
			
		}
	}
	
	/**
	*Función encargada de establecer una conexión con la API meteorológica y procesar la información recibida en formato XML.
	*
	*@param ciudad - ciudad define la ciudad de la que presentaremos su información meteorológica.
	 * @return layout - layout define la estructura de representación de la información meteorológica.
	*/
	
	public VerticalLayout requestWeather(Ciudad ciudad) throws Exception
	{

		String URLtext = "http://api.openweathermap.org/data/2.5/weather?q="+ciudad.getcNombre()+"&units=metric&lang=es&mode=xml&appid=" + token;	
		URL url = new URL(URLtext);
		URLConnection conn = url.openConnection();	// establecemos conexion con la URL de la API
		
		HttpURLConnection conn2 = (HttpURLConnection) conn;
		int statusCode = conn2.getResponseCode();	// obtenemos el codigo de respuesta de la API
		
		VerticalLayout layout = new VerticalLayout();
		
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
			String viento = file.getElementsByTagName("wind").item(0).getFirstChild().getAttributes().getNamedItem("value").getTextContent();
			String icono = file.getElementsByTagName("weather").item(0).getAttributes().getNamedItem("icon").getTextContent();
			
			String iconoURL = "http://openweathermap.org/img/w" + icono + ".png" ;
			
			H2 Titulo = new H2("Información meteorológica: ");
	        Label Temperatura = new Label("Temperatura: " + (Double.parseDouble(temperatura)-273.15));
	        Label Humedad = new Label("Humedad: " + humedad);
	        Label Presion = new Label("Presión: " + presion);
	        Label Viento = new Label("Viento: " + viento);
	        Label iconoVista = new Label(iconoURL);
	        
	        layout = new VerticalLayout(Titulo, Temperatura, Humedad, Presion, Viento, iconoVista);
			
		}
		
		return layout;
		
	}

	public String getURLtext() {
		return URLtext;
	}

	public void setURLtext(String uRLtext) {
		URLtext = uRLtext;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public URLConnection getConn() {
		return conn;
	}

	public void setConn(URLConnection conn) {
		this.conn = conn;
	}

	public HttpURLConnection getConn2() {
		return conn2;
	}

	public void setConn2(HttpURLConnection conn2) {
		this.conn2 = conn2;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getHumedad() {
		return humedad;
	}

	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}

	public String getViento() {
		return viento;
	}

	public void setViento(String viento) {
		this.viento = viento;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getIconoURL() {
		return iconoURL;
	}

	public void setIconoURL(String iconoURL) {
		this.iconoURL = iconoURL;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
	
