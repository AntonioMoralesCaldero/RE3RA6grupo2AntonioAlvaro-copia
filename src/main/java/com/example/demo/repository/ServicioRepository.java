package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.Servicio;

@Repository("servicioRepository")
public interface ServicioRepository extends JpaRepository<Servicio, Serializable> {
	public abstract Servicio findById(Servicio servicio);
	
	  @Query("SELECT s FROM Servicio s WHERE s.empresa = :empresa")
	  List<Servicio> findByEmpresa(@Param("empresa") Empresa empresa);
	  @Query("SELECT s FROM Servicio s WHERE s.empresa = :empresa AND s.familiaProfesional.nombre = :familiaProfesional")
	  List<Servicio> findServiciosByEmpresaAndFamiliaProfesional(@Param("empresa") Empresa empresa, @Param("familiaProfesional") String familiaProfesional);

	  @Query("SELECT s FROM Servicio s WHERE s.alumno = :alumno")
	    List<Servicio> findServiciosByAlumno(@Param("alumno") Alumno alumno);

	  
	  
}
