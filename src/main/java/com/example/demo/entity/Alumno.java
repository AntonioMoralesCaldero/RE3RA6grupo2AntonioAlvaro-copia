package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Alumno implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nombre;
    private String apellidos;

    @Column(unique = true, nullable = false)
    private String username;

    private String role;
    private boolean activado;
    private boolean borrado;

    private String password;

    @ManyToOne
    @JoinColumn(name = "idFamilia")
    private FamiliaProfesional familiaProfesional;

    @OneToMany(mappedBy = "alumno")
    private List<Servicio> servicioList = new ArrayList<>();
    
    
    



	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    public Alumno() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FamiliaProfesional getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(FamiliaProfesional familiaProfesional) {
        if (familiaProfesional != null) {
            this.familiaProfesional = familiaProfesional;
            familiaProfesional.getAlumnoList().add(this);
        } else {
            // Manejar el caso donde familiaProfesional es null, por ejemplo, limpiar la lista de alumnos
            if (this.familiaProfesional != null) {
                this.familiaProfesional.getAlumnoList().remove(this);
            }
            this.familiaProfesional = null;
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ALUMNO"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.activado;
    }
}
