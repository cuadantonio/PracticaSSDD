package com.example.demo.model;

import java.time.LocalDate;
import java.util.LinkedList;

public class Cultivo {
	private String especie;
	private String variedad;
	private LocalDate fecha;
	private String zona;
	private LinkedList<Tratamiento> tratamientos;
	
	public Cultivo(String especie, String variedad, LocalDate fecha, String zona,
			LinkedList<Tratamiento> tratamientos) {
		super();
		this.especie = especie;
		this.variedad = variedad;
		this.fecha = fecha;
		this.zona = zona;
		this.tratamientos = tratamientos;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public LinkedList<Tratamiento> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(LinkedList<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}
}
