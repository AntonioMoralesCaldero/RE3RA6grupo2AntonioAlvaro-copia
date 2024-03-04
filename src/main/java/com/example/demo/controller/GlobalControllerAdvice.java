package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.Alumno; // Importa tu entidad Alumno
import com.example.demo.repository.AlumnoRepository; // Importa tu repositorio AlumnoRepository

import userdetails.CustomUserDetails;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final AlumnoRepository alumnoRepository;

    public GlobalControllerAdvice(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof CustomUserDetails) {
                String userEmail = ((CustomUserDetails) principal).getUsername(); // Obtén el correo electrónico del usuario autenticado
                String nombre = obtenerNombreDesdeLaBaseDeDatos(userEmail);
                model.addAttribute("user", nombre);
             
            } else if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                model.addAttribute("nombre", username);
                String nombre = obtenerNombreDesdeLaBaseDeDatos(username);
                model.addAttribute("user", nombre);
                
            }
        }
    }

    private String obtenerNombreDesdeLaBaseDeDatos(String userEmail) {
        Alumno alumno = alumnoRepository.findAlumnoByUsername(userEmail);
       
        if (alumno != null) {
  
            return alumno.getNombre();
        } else {
        
            return "Nombre desde la base de datos"; 
        }
    }
}
