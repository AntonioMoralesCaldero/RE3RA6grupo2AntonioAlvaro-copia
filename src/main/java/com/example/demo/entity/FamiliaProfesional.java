package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class FamiliaProfesional {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nombre;

	@OneToMany(mappedBy = "familiaProfesional", cascade = CascadeType.ALL)
	private List<Alumno> alumnoList = new ArrayList<>();

	@OneToMany(mappedBy = "familiaProfesional")
	private List<Servicio> servicioList;

	public FamiliaProfesional() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FamiliaProfesional(int id, String nombre, List<Alumno> alumnoList, List<Servicio> servicioList) {
		super();
		this.id = id;
		this.nombre = nombre;

		this.alumnoList = alumnoList;
		this.servicioList = servicioList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alumno> getAlumnoList() {
		return alumnoList;
	}

	public void setAlumnoList(List<Alumno> alumnoList) {
		this.alumnoList = alumnoList;
	}

	public List<Servicio> getServicioList() {
		return servicioList;
	}

	public void setServicioList(List<Servicio> servicioList) {
		this.servicioList = servicioList;
	}

	@Override
	public String toString() {
		return "FamiliaProfesional [id=" + id + ", nombre=" + nombre + ", alumnoList=" + alumnoList + ", servicioList="
				+ servicioList + "]";
	}

}
