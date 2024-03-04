package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Servicio;
import com.example.demo.model.ServicioModel;
import com.example.demo.service.ServicioService;
import com.example.demo.service.AlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private AlumnoService alumnoService;

    private static final String VISTA = "ServicioAlumno"; 

    @GetMapping("/mostrarServicios")
    public String mostrarServicios(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ServicioModel> serviciosAlumno = servicioService.findServiciosByAlumno(alumnoService.findAlumnoByUsername(authentication.getName()));
        model.addAttribute("servicios", serviciosAlumno);
        return VISTA;
    }
}
