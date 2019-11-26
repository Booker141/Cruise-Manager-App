package es.uca.gii.iw.crusaito.tiempo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cloud {
	
	private float all;

	public Cloud(float all) {
		super();
		this.all = all;
	}

	public float getAll() {
		return all;
	}

	public void setAll(float all) {
		this.all = all;
	}

	
}
