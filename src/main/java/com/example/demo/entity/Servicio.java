package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@Length(max = 100)
	private String titulo;
	@Length(max = 255)
	private String descripcion;
	private LocalDate fechaAlta;
	private LocalDate fechaRealizacion;

	@ManyToOne
	@JoinColumn(name = "idAlumno")
	private Alumno alumno;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	@ManyToOne
	@JoinColumn(name = "idFamilia")
	private FamiliaProfesional familiaProfesional;

	@OneToMany(mappedBy = "servicio", orphanRemoval = true)
	private List<Informe> informeList;

	private Integer valoracionEmpresa;
	private Boolean realizado;
	@Length(max = 255)
	private String comentarioEmpresa;
	private boolean borrado;
	public Servicio(int id, @Length(max = 100) String titulo, @Length(max = 255) String descripcion,
			LocalDate fechaAlta, LocalDate fechaRealizacion, Alumno alumno, Empresa empresa,
			FamiliaProfesional familiaProfesional, List<Informe> informeList, int valoracionEmpresa, boolean realizado,
			@Length(max = 255) String comentarioEmpresa) {
		super();
		Id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaRealizacion = fechaRealizacion;
		this.alumno = alumno;
		this.empresa = empresa;
		this.familiaProfesional = familiaProfesional;
		this.informeList = informeList;
		this.valoracionEmpresa = valoracionEmpresa;
		this.realizado = realizado;
		this.comentarioEmpresa = comentarioEmpresa;
	}

	public Servicio() {
		super();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public FamiliaProfesional getFamiliaProfesional() {
		return familiaProfesional;
	}

	public void setFamiliaProfesional(FamiliaProfesional familiaProfesional) {
		this.familiaProfesional = familiaProfesional;
	}

	public List<Informe> getInformeList() {
		return informeList;
	}

	public void setInformeList(List<Informe> informeList) {
		this.informeList = informeList;
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
	
	public Boolean getRealizado() {
		return realizado;
	}

	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public void setValoracionEmpresa(Integer valoracionEmpresa) {
		this.valoracionEmpresa = valoracionEmpresa;
	}

	@Override
	public String toString() {
		return "Servicio [Id=" + Id + ", titulo=" + titulo + ", description=" + descripcion + ", fechaAlta=" + fechaAlta
				+ ", fechaRealizacion=" + fechaRealizacion + ", alumno=" + alumno + ", empresa=" + empresa
				+ ", familiaProfesional=" + familiaProfesional + ", informeList=" + informeList + ", valoracionEmpresa="
				+ valoracionEmpresa + ", realizado=" + realizado + ", comentarioEmpresa=" + comentarioEmpresa + "]";
	}

}
