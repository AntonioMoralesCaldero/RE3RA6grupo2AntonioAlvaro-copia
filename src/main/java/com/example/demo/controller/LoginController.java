package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.FamiliaProfesional;
import com.example.demo.model.AlumnoModel;
import com.example.demo.repository.FamiliaProfesionalRepository;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.impl.AlumnoServiceImpl;

@Controller
public class LoginController {
    private AlumnoService alumnoService;

    @Autowired
    private FamiliaProfesionalRepository familiaProfesionalRepository;

    @Autowired
    public LoginController(@Qualifier("alumnoService") AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @Autowired
    public void setAlumnoService(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @Autowired
    private AlumnoServiceImpl alumnoServiceImpl;

    @GetMapping("/auth/login")
    public String login(Model model, 
                        @RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout) 
    {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("error", error != null);  // Cambiado aquí
        model.addAttribute("logout", logout);
        return "login";
    }

 


    @GetMapping("/auth/register")
    public String register(Model model) {
        List<FamiliaProfesional> familiasProfesionales = familiaProfesionalRepository.findAll();
        model.addAttribute("familiasProfesionales", familiasProfesionales);
        model.addAttribute("alumnoModel", new AlumnoModel());
        return "register";
    }

    @PostMapping("/auth/register")
    public String registerform(@ModelAttribute("alumnoModel") AlumnoModel alumnoModel, RedirectAttributes flash) {
        alumnoService.registrar(alumnoModel);
        flash.addFlashAttribute("success", "Alumno registrado con éxito!!");
        return "redirect:/auth/login";
    }
    
        
  
    
    

 
}
