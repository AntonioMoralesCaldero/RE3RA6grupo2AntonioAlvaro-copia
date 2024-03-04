package com.example.demo.service;

import java.util.List;


import com.example.demo.entity.Alumno;
import com.example.demo.model.AlumnoModel;

public interface AlumnoService {
	List<AlumnoModel> ListAllAlumnos();
	int removeAlumno(int id);
	boolean setEnable(int idAlumno);
	String setRole();
	Alumno updateAlumno(AlumnoModel alumnoModel);
	public Alumno registrar(AlumnoModel alumnomodel);
	public Alumno registrarEmpresas(AlumnoModel alumnomodel);
	AlumnoModel getAlumnoById(int id);

	Alumno findAlumnoByUsername(String username);

	   
	

	
}
