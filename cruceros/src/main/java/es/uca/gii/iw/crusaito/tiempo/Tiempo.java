package es.uca.gii.iw.crusaito.tiempo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Tiempo {
	
	private Coordenada coord;
	private Weather weather;
	private String base;
	private Main main;
	private String visibility;
	private Wind wind;
	private Cloud clouds;
	private String dt;
	private Sys sys;
	private String id;
	private String name;
	private String cod;
	

}
