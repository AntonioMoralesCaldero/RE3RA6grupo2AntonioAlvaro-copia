package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.Servicio;
import com.example.demo.model.ServicioModel;

public interface ServicioService {
	List<Servicio> ListAllServicios();
	List<ServicioModel> ListAllServiciosModel();
	Servicio ListAllServiciosById(int id);
	public Servicio registrarEmpresas(ServicioModel servicioModel);
	ServicioModel addServicio(ServicioModel servicioModel, String email);
	ServicioModel getServicioById(int id);
	ServicioModel addServicio(ServicioModel servicioModel);
	Servicio guardarServicio(Servicio servicio);
	int deleteServicio(int id);
	boolean setRealizado(int idServicio);
	Servicio updateServicio(ServicioModel servicioModel);
	Servicio updateValoracion(ServicioModel servicioModel);
	Servicio updateComentario(ServicioModel servicioModel);
	Alumno findAlumnoById(int id);
	List<ServicioModel> findServiciosByAlumno(Alumno alumno);
	int removeServicio(int id);
	List<ServicioModel> findServiciosByEmpresa(Empresa empresa);
	List<ServicioModel> findServiciosByEmpresaAndFamiliaProfesional(Empresa empresaActual, String familiaProfesional);
	
	
	
}
