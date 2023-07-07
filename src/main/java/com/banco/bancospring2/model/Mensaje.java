package com.banco.bancospring2.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	
	@ManyToOne
	private Gestor origen;
	
	@ManyToOne
	private Gestor destino;
	
	private String Texto;
	
	private Date fecha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Gestor getOrigen() {
		return origen;
	}

	public void setOrigen(Gestor origen) {
		this.origen = origen;
	}

	public Gestor getDestino() {
		return destino;
	}

	public void setDestino(Gestor destino) {
		this.destino = destino;
	}

	@PrePersist
	public void antesDeGuardar() {
		if (this.fecha == null) {
		this.fecha = new Date();	
		}
	}

}
