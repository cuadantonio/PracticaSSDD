package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Tratamiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne
	private Cultivo cultivo;
	@OneToOne
	private Producto producto;
	private String numeroLote;
	private LocalDate fechaRealizacion;
	private LocalDate fechaPlazoReentrada;
	private LocalDate fechaPlazoRecoleccion;
	
	public Tratamiento(Cultivo cultivo, Producto producto, String numeroLote, LocalDate fechaRealizacion,
			LocalDate fechaPlazoReentrada, LocalDate fechaPlazoRecoleccion) {
		super();
		this.cultivo = cultivo;
		this.producto = producto;
		this.numeroLote = numeroLote;
		this.fechaRealizacion = fechaRealizacion;
		this.fechaPlazoReentrada = fechaPlazoReentrada;
		this.fechaPlazoRecoleccion = fechaPlazoRecoleccion;
	}
	public Tratamiento() {
		
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public LocalDate getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(LocalDate fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public LocalDate getFechaPlazoReentrada() {
		return fechaPlazoReentrada;
	}

	public void setFechaPlazoReentrada(LocalDate fechaPlazoReentrada) {
		this.fechaPlazoReentrada = fechaPlazoReentrada;
	}

	public LocalDate getFechaPlazoRecoleccion() {
		return fechaPlazoRecoleccion;
	}

	public void setFechaPlazoRecoleccion(LocalDate fechaPlazoRecoleccion) {
		this.fechaPlazoRecoleccion = fechaPlazoRecoleccion;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
