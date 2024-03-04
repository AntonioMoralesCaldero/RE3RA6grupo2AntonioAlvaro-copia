package com.example.demo.model;

import java.time.LocalDate;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Servicio;

public class InformeModel {
	
	private int id;
	private Alumno idAlumno;
	private Servicio idServicio;
	private LocalDate fechahora;
	private int tiempoServicio;
	private String informe;
	
	
	
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
	public Servicio getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Servicio idServicio) {
		this.idServicio = idServicio;
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
	
	

}
