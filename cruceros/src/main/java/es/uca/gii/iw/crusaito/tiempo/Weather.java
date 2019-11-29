package es.uca.gii.iw.crusaito.tiempo;

import java.net.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Weather {
	
	final static private String token = "83829333432ed41a10b897e79ed488a7";
	//private String city;

	  	 
	/**
	*	Funcion encargada de establecer una conexion con la API meteorologica y procesar la informacion recibida en formato XML.
	*
	*	@return String - temperatura en caso exitoso; mensaje de error en caso contrario.
	*/
	
	public Tiempo requestWeather(Tiempo weatherObject) throws Exception
	{
		
		String city = weatherObject.getCity();
		
		String URLtext = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&mode=xml&appid=" + token;	
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
			String weather = file.getElementsByTagName("temperature").item(0).getAttributes().item(0).getTextContent();
			
			weatherObject.setTemperatureCelsius((Double.parseDouble(weather)-273.15));
			weatherObject.setTemperatureKelvin(Double.parseDouble(weather));
			
			return weatherObject;
			
		}
		else	// error 404
		{
			Tiempo tiempoError = new Tiempo(); tiempoError.setCity("error");
			return tiempoError;
		}
	}
	
	public Tiempo requestWeatherCoordinates(Coordinates coord) throws Exception
	{
		
		String URLtext = "http://api.openweathermap.org/data/2.5/weather?lat="+coord.getLat()+"&lon="+coord.getLon()+"&mode=xml&appid=" + token;	
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
			String weather = file.getElementsByTagName("temperature").item(0).getAttributes().item(0).getTextContent();
			
			Tiempo weatherObject = new Tiempo();
			weatherObject.setTemperatureCelsius((Double.parseDouble(weather)-273.15));
			weatherObject.setTemperatureKelvin(Double.parseDouble(weather));
			
			return weatherObject;
			
		}
		else	// error 404
		{
			Tiempo tiempoError = new Tiempo(); tiempoError.setCity("error");
			return tiempoError;
		}		
	}
}
	
