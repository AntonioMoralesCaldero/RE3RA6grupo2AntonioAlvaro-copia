package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.Servicio;
import com.example.demo.model.ServicioModel;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.FamiliaProfesionalRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.EmpresaService;
import com.example.demo.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {
	@Autowired
	private ServicioRepository servicioRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private FamiliaProfesionalRepository familiaProfesionalRepository;

	@Autowired
	private EmpresaService empresaService;

	@Override
	public List<ServicioModel> ListAllServiciosModel() {
		List<Servicio> servicios = servicioRepository.findAll();
		List<ServicioModel> servicioModels = servicios.stream().map(this::convertToServicioModel)
				.collect(Collectors.toList());
		return servicioModels;
	}

	@Override
	public ServicioModel addServicio(ServicioModel servicioModel) {
		Servicio servicio = convertToServicio(servicioModel);
		Empresa empresaActual = empresaService.findByEmail();
		LocalDate fechaActual = LocalDate.now();

		servicio.setFechaAlta(fechaActual);
		servicio.setAlumno(null); 
		servicio.setFechaRealizacion(null); 
		servicio.setValoracionEmpresa(0); 
		servicio.setRealizado(false); 
		servicio.setComentarioEmpresa(null); 
		servicio.setEmpresa(empresaActual);

		servicio = servicioRepository.save(servicio);
		return convertToServicioModel(servicio);
	}

	@Override
	public int removeServicio(int id) {
		Optional<Servicio> servicioOptional = servicioRepository.findById(id);

		if (servicioOptional.isPresent()) {
			Servicio servicio = servicioOptional.get();
			servicio.setBorrado(true);
			servicioRepository.save(servicio);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Servicio updateServicio(ServicioModel servicioModel) {
		Servicio servicioExistente = servicioRepository.findById(servicioModel.getId()).orElse(null);

		if (servicioExistente != null) {
			servicioExistente.setTitulo(servicioModel.getTitulo());
			servicioExistente.setDescripcion(servicioModel.getDescripcion());
			servicioExistente.setFechaRealizacion(servicioModel.getFechaRealizacion());
			servicioExistente.setFamiliaProfesional(servicioModel.getIdFamilia());

			return servicioRepository.save(servicioExistente);

		} else {
			return null; 
		}

	}

	private Servicio convertToServicio(ServicioModel servicioModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(servicioModel, Servicio.class);
	}

	private ServicioModel convertToServicioModel(Servicio servicio) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(servicio, ServicioModel.class);
	}

	@Override
	public Servicio ListAllServiciosById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Servicio guardarServicio(Servicio servicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteServicio(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean setRealizado(int idServicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Servicio registrarEmpresas(ServicioModel servicioModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServicioModel addServicio(ServicioModel servicioModel, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServicioModel getServicioById(int id) {
		Optional<Servicio> optionalServicio = servicioRepository.findById(id);

		if (optionalServicio.isPresent()) {
			Servicio servicio = optionalServicio.get();
			return convertToServicioModel(servicio);
		}

		throw new IllegalArgumentException("Alumno no encontrado con ID: " + id);
	}

	@Override
	public List<Servicio> ListAllServicios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServicioModel> findServiciosByEmpresa(Empresa empresa) {
		List<Servicio> servicios = servicioRepository.findByEmpresa(empresa);

		List<ServicioModel> servicioModels = new ArrayList<>();
		for (Servicio servicio : servicios) {
			ServicioModel servicioModel = convertToServicioModel(servicio); // Implementa este método según tus
																			// necesidades
			servicioModels.add(servicioModel);
		}

		return servicioModels;
	}

	@Override
	public List<ServicioModel> findServiciosByEmpresaAndFamiliaProfesional(Empresa empresaActual,
			String familiaProfesional) {
		List<Servicio> servicios = servicioRepository.findServiciosByEmpresaAndFamiliaProfesional(empresaActual,
				familiaProfesional);

		List<ServicioModel> servicioModels = new ArrayList<>();
		for (Servicio servicio : servicios) {
			ServicioModel servicioModel = convertToServicioModel(servicio); // Implementa este método según tus
																			// necesidades
			servicioModels.add(servicioModel);
		}

		return servicioModels;
	}

	@Override
	public Alumno findAlumnoById(int id) {
		Optional<Alumno> alumno = alumnoRepository.findById(id);
		return alumno.orElse(null);
	}

	public Servicio updateValoracion(ServicioModel servicioModel) {
        Servicio servicio = servicioRepository.findById(servicioModel.getId()).orElse(null);

        if (servicio != null) {
            servicio.setValoracionEmpresa(servicioModel.getValoracionEmpresa());

            return servicioRepository.save(servicio);
        }
		return servicio; 
    }

	@Override
	public Servicio updateComentario(ServicioModel servicioModel) {
		Servicio servicio = servicioRepository.findById(servicioModel.getId()).orElse(null);

        if (servicio != null) {

            servicio.setComentarioEmpresa(servicioModel.getComentarioEmpresa());


            return servicioRepository.save(servicio);
        }
		return servicio; 
    
	}
	
	@Override
    public List<ServicioModel> findServiciosByAlumno(Alumno alumno) {
        List<Servicio> servicios = servicioRepository.findServiciosByAlumno(alumno);

        return servicios.stream()
                .map(this::convertToServicioModel)
                .collect(Collectors.toList());
    }


}