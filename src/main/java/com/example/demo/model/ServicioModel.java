package com.example.demo.model;

import java.time.LocalDate;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.FamiliaProfesional;

public class ServicioModel {
	private int id;
	private String titulo;
	private String descripcion;
	private LocalDate fechaAlta;
	private LocalDate fechaRealizacion;
	private Alumno idAlumno;
	private Empresa idEmpresa;
	private FamiliaProfesional idFamilia;
	private int valoracionEmpresa;
	private boolean realizado;
	private String comentarioEmpresa;
	private boolean borrado = false;

	public ServicioModel(int id, String titulo, String descripcion, LocalDate fechaAlta, LocalDate fechaRealizacion,
			Alumno idAlumno, Empresa idEmpresa, FamiliaProfesional idFamilia, int valoracionEmpresa, boolean realizado,
			String comentarioEmpresa) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaRealizacion = fechaRealizacion;
		this.idAlumno = idAlumno;
		this.idEmpresa = idEmpresa;
		this.idFamilia = idFamilia;
		this.valoracionEmpresa = valoracionEmpresa;
		this.realizado = realizado;
		this.comentarioEmpresa = comentarioEmpresa;
	}

	public ServicioModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDate getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(LocalDate fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public Alumno getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Alumno idAlumno) {
		this.idAlumno = idAlumno;
	}

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public FamiliaProfesional getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(FamiliaProfesional idFamilia) {
		this.idFamilia = idFamilia;
	}

	public int getValoracionEmpresa() {
		return valoracionEmpresa;
	}

	public void setValoracionEmpresa(int valoracionEmpresa) {
		this.valoracionEmpresa = valoracionEmpresa;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public String getComentarioEmpresa() {
		return comentarioEmpresa;
	}

	public void setComentarioEmpresa(String comentarioEmpresa) {
		this.comentarioEmpresa = comentarioEmpresa;
	}

	
	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return "ServicioModel [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaAlta="
				+ fechaAlta + ", fechaRealizacion=" + fechaRealizacion + ", idAlumno=" + idAlumno + ", idEmpresa="
				+ idEmpresa + ", idFamilia=" + idFamilia + ", valoracionEmpresa=" + valoracionEmpresa + ", realizado="
				+ realizado + ", comentarioEmpresa=" + comentarioEmpresa + "]";
	}

}
