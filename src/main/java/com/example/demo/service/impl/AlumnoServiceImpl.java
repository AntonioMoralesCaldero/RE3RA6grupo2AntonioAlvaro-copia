package com.example.demo.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.FamiliaProfesional;
import com.example.demo.model.AlumnoModel;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.FamiliaProfesionalRepository;
import com.example.demo.service.AlumnoService;

import userdetails.CustomUserDetails;

@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService, UserDetailsService {

	@Autowired
    private AlumnoRepository alumnoRepository;
	
	@Autowired
    private FamiliaProfesionalRepository familiaProfesionalRepository;

    
	@Override
    public List<AlumnoModel> ListAllAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream()
                .map(this::convertToAlumnoModel)
                .collect(Collectors.toList());
    }

 

	@Override
	public int removeAlumno(int id) {
	    Optional<Alumno> optionalAlumno = alumnoRepository.findById(id);

	    if (optionalAlumno.isPresent()) {
	        Alumno alumno = optionalAlumno.get();
	        alumno.setBorrado(true);
	        alumnoRepository.save(alumno); 
	        return id;
	    }

	    return 0;
	}


    @Override
    public Alumno updateAlumno(AlumnoModel alumnoModel) {
        Alumno alumno = alumnoRepository.findById(alumnoModel.getId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

    
        alumno.setNombre(alumnoModel.getNombre());
        alumno.setApellidos(alumnoModel.getApellidos());
        alumno.setUsername(alumnoModel.getUsername());
     
        alumno.setFamiliaProfesional(familiaProfesionalRepository.findById(alumnoModel.getIdFamilia())
                .orElseThrow(() -> new RuntimeException("FamiliaProfesional no encontrada")));

        
        return alumnoRepository.save(alumno);
    }

    private Alumno convertToAlumno(AlumnoModel alumnoModel) {
    	ModelMapper mapper = new ModelMapper();
        return mapper.map(alumnoModel, Alumno.class);
    }
    
    
    private AlumnoModel convertToAlumnoModel(Alumno alumno) {
    	ModelMapper mapper = new ModelMapper();
        return mapper.map(alumno, AlumnoModel.class);
    }
    
    @Override
    public Alumno registrar(AlumnoModel alumnoModel) {
        Alumno alumno = convertToAlumno(alumnoModel);


        FamiliaProfesional familia = familiaProfesionalRepository.findById(alumnoModel.getIdFamilia())
                .orElseThrow(() -> new IllegalArgumentException("Familia no encontrada con ID: " + alumnoModel.getIdFamilia()));
        alumno.setFamiliaProfesional(familia);

        alumno.setPassword(passwordEncoder().encode(alumno.getPassword()));

        alumno.setActivado(false);
        alumno.setBorrado(false);
        alumno.setRole("ROL_ALUMNO");

        return alumnoRepository.save(alumno);
      
    }

   


    @Override
    public boolean setEnable(int idAlumno) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(idAlumno);

        if (optionalAlumno.isPresent()) {
            Alumno alumno = optionalAlumno.get();
            alumno.setActivado(!alumno.isActivado()); 
            alumnoRepository.save(alumno);
            return alumno.isActivado();
        } else {
            throw new IllegalArgumentException("Alumno no encontrado con ID: " + idAlumno);
        }
    }


	@Override
	public String setRole() {
		// TODO Auto-generated method stub
		return null;
	}


	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
	    return Set.of(new SimpleGrantedAuthority(role));
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    com.example.demo.entity.Alumno alumno = alumnoRepository.findAlumnoByUsername(username);

	    if (alumno != null && alumno.isEnabled() && !alumno.isBorrado()) {
	        CustomUserDetails customUserDetails = new CustomUserDetails(alumno);

	        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
	        builder.password(alumno.getPassword());
	        builder.authorities(new SimpleGrantedAuthority(alumno.getRole()));
	        builder.accountExpired(!customUserDetails.isAccountNonExpired());
	        builder.accountLocked(!customUserDetails.isAccountNonLocked());
	        builder.credentialsExpired(!customUserDetails.isCredentialsNonExpired());
	        builder.disabled(!customUserDetails.isEnabled());

	        return builder.build();
	    } else {
	        throw new UsernameNotFoundException("Alumno no encontrado");
	    }
	}




	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	@Override
	public AlumnoModel getAlumnoById(int id) {
	    Optional<Alumno> optionalAlumno = alumnoRepository.findById(id);

	    if (optionalAlumno.isPresent()) {
	        Alumno alumno = optionalAlumno.get();
	        return convertToAlumnoModel(alumno);
	    }

	    throw new IllegalArgumentException("Alumno no encontrado con ID: " + id);
	}



	@Override
	public Alumno registrarEmpresas(AlumnoModel alumnomodel) {
		 Alumno alumno = convertToAlumno(alumnomodel);


	       
	        alumno.setFamiliaProfesional(null);

	        alumno.setPassword(passwordEncoder().encode(alumno.getPassword()));

	        alumno.setActivado(true);
	        alumno.setBorrado(false);
	        alumno.setRole("ROL_EMPRESA");

	        return alumnoRepository.save(alumno);
	}


	@Override
    public Alumno findAlumnoByUsername(String username) {
        return alumnoRepository.findAlumnoByUsername(username);
    }



}
