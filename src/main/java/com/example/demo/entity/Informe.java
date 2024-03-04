package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Informe {
	//
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="idAlumno")
	private Alumno idAlumno;
	@ManyToOne
	@JoinColumn(name="idServicio")
	private Servicio servicio;
	private LocalDate fechahora;
	private int tiempoServicio;
	private String informe;
	
		
	public Informe() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Alumno getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(Alumno idAlumno) {
		this.idAlumno = idAlumno;
	}



	public LocalDate getFechahora() {
		return fechahora;
	}


	public void setFechahora(LocalDate fechahora) {
		this.fechahora = fechahora;
	}


	public int getTiempoServicio() {
		return tiempoServicio;
	}


	public void setTiempoServicio(int tiempoServicio) {
		this.tiempoServicio = tiempoServicio;
	}


	public String getInforme() {
		return informe;
	}


	public void setInforme(String informe) {
		this.informe = informe;
	}


	@Override
	public String toString() {
		return "Informe [id=" + id + ", idAlumno=" + idAlumno + ", servicioList=" + ", fechahora="
				+ fechahora + ", tiempoServicio=" + tiempoServicio + ", informe=" + informe + "]";
	}


	
	
}
