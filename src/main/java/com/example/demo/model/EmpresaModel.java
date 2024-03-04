package com.example.demo.model;

public class EmpresaModel {
	private int id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String logo;
	private boolean borrado = false;
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	@Override
	public String toString() {
		return "EmpresaModel [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", email=" + email + ", logo=" + logo + ", borrado=" + borrado + "]";
	}
	public EmpresaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
