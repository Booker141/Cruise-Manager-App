package es.uca.gii.iw.crusaito.tiempo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coordinates {
	
	@Id
	@GeneratedValue
	private long id;
	
	private int lon, lat;

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}
	
}
