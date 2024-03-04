package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Empresa;
import com.example.demo.model.EmpresaModel;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.service.EmpresaService;

import userdetails.CustomUserDetails;

@Service("empresaService")
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<Empresa> ListAllEmpresas() {
		return empresaRepository.findAll();
	}
	
	@Override
	public Empresa ListAllEmpresasById(int id) {
		Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        return optionalEmpresa.orElse(null);
	}
	
	@Override
	public int deleteEmpresa(int id) {
		Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
		
		if (optionalEmpresa.isPresent()) {
	        Empresa empresa = optionalEmpresa.get();
	        empresa.setBorrado(true);
	        empresaRepository.save(empresa); 
	        return id;
	    }

	    return 0; 
	}


	
    public Empresa updateEmpresa(Empresa empresa) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(empresa.getId());

        if (optionalEmpresa.isPresent()) {
            Empresa empresaExistente = optionalEmpresa.get();
            empresaExistente.setNombre(empresa.getNombre());
            empresaExistente.setDireccion(empresa.getDireccion());
            empresaExistente.setTelefono(empresa.getTelefono());
            empresaExistente.setEmail(empresa.getEmail());
            empresaExistente.setLogo(empresa.getLogo());

            return empresaRepository.save(empresaExistente);
        }

        throw new IllegalArgumentException("Empresa no encontrada con ID: " + empresa.getId());
    }
    
    
    
    @Override
    public Empresa updateEmpresa(EmpresaModel empresaModel) {
        Empresa empresa = convertToEmpresa(empresaModel);
        return updateEmpresa(empresa);
    }

	
	private Empresa convertToEmpresa(EmpresaModel empresaModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(empresaModel, Empresa.class);
	}
	
	private EmpresaModel convertToEmpresaModel(Empresa empresa) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(empresa, EmpresaModel.class);		
	}


	@Override
	public Empresa guardarEmpresa(Empresa empresa) {
		
		return empresaRepository.save(empresa);
	}
	
	@Override
	public EmpresaModel getEmpresaById(int id) {
	    Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);

	    if (optionalEmpresa.isPresent()) {
	        Empresa empresa= optionalEmpresa.get();
	        return convertToEmpresaModel(empresa);
	    }

	    throw new IllegalArgumentException("Empresa no encontrada con ID: " + id);
	}
	@Override
	public Empresa findById(int id) {
		Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
		return optionalEmpresa.orElse(null);
	}

	public int obtenerIdUsuarioActual() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof CustomUserDetails) {
			int id = ((CustomUserDetails) principal).getId();
			System.out.println("El id es: " + id);
			return id;
		} else {
			return -1; 
		}
	}

	public String obtenerCorreoUsuarioActual() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			System.out.println(principal);
			if (principal instanceof User) {
				String prueba = ((User) principal).getUsername();
				System.out.println(prueba);
				return prueba;
			}
		}

		return null;
	}

	@Override
	public Empresa findByEmail() {
		String correoAlumno = obtenerCorreoUsuarioActual();

		if (correoAlumno != null) {
			Empresa prueba = empresaRepository.findByEmail(correoAlumno);
			System.out.println("Esta es la ultima porueba " + prueba.getId());
			return prueba;

		}

		throw new IllegalArgumentException(
				"No se pudo encontrar la empresa para el correo electrónico del alumno actual.");
	}





	
	
}