package es.uca.gii.iw.crusaito.tiempo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tiempo {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String city;
	private double temperatureCelsius, temperatureKelvin;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getTemperatureCelsius() {
		return temperatureCelsius;
	}
	public void setTemperatureCelsius(double temperatureCelsius) {
		this.temperatureCelsius = temperatureCelsius;
	}
	public double getTemperatureKelvin() {
		return temperatureKelvin;
	}
	public void setTemperatureKelvin(double temperatureKelvin) {
		this.temperatureKelvin = temperatureKelvin;
	}
	
}
