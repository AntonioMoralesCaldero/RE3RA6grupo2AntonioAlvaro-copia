package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Empresa {
	//
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToMany(mappedBy = "empresa")
	private List<Servicio> servicioList;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private boolean borrado;
	private String logo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Servicio> getServicioList() {
		return servicioList;
	}
	public void setServicioList(List<Servicio> servicioList) {
		this.servicioList = servicioList;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", servicioList=" + servicioList + ", nombre=" + nombre + ", direccion="
				+ direccion + ", telefono=" + telefono + ", email=" + email + ", borrado=" + borrado + ", logo=" + logo
				+ "]";
	}
	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
}

