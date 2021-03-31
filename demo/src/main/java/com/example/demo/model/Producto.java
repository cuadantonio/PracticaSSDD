package com.example.demo.model;

public class Producto {
	private String nombre;
	private String descripcion;
	private int plazoReentrada;
	private int plazoRecoleccion;
	
	public Producto(String nombre, String descripcion, int plazoReentrada, int plazoRecoleccion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plazoReentrada = plazoReentrada;
		this.plazoRecoleccion = plazoRecoleccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPlazoReentrada() {
		return plazoReentrada;
	}

	public void setPlazoReentrada(int plazoReentrada) {
		this.plazoReentrada = plazoReentrada;
	}

	public int getPlazoRecoleccion() {
		return plazoRecoleccion;
	}

	public void setPlazoRecoleccion(int plazoRecoleccion) {
		this.plazoRecoleccion = plazoRecoleccion;
	}
	
	
}
