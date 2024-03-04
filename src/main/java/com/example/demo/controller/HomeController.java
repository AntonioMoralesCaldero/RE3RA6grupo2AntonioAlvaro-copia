package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.FamiliaProfesional;
import com.example.demo.model.AlumnoModel;
import com.example.demo.repository.FamiliaProfesionalRepository;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.EmpresaService;




@Controller
@RequestMapping("/index")
public class HomeController {
	
	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	EmpresaService empresaService;
	

    @Autowired
    private FamiliaProfesionalRepository familiaProfesionalRepository;
	
	private static final String PRUEBA = "index";
	private static final String ADMIN_PANEL = "adminpanel";
	private static final String EMPRESA_ADMIN = "empresaAdmin";
	
	
	    public String redirigir () {
	         return "redirect:/index/";
	    }
	  @GetMapping("/")
	    public String index() {
	        return PRUEBA;
	    }

	
	
    @GetMapping("/list")
    public String showAllAlumnos(Model model) {
        List<AlumnoModel> alumnos = alumnoService.ListAllAlumnos();
        List<FamiliaProfesional> familiasProfesionales = familiaProfesionalRepository.findAll();
        model.addAttribute("familiasProfesionales", familiasProfesionales);
        
        model.addAttribute("alumnos", alumnos);
        

        return ADMIN_PANEL; 
    }
    
    
    @GetMapping("/activate")
    public String activateAlumno(@RequestParam("alumnoId") int alumnoId, Model model) {
        boolean activado = alumnoService.setEnable(alumnoId);

        List<AlumnoModel> alumnos = alumnoService.ListAllAlumnos();
        List<FamiliaProfesional> familiasProfesionales = familiaProfesionalRepository.findAll();
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("familiasProfesionales", familiasProfesionales);

        return ADMIN_PANEL;
    }
    
    @PostMapping("/edit")
    public String processEditForm(@ModelAttribute AlumnoModel alumnoModel) {
        alumnoService.updateAlumno(alumnoModel);
        return "redirect:/index/list";
    }
    
    @PostMapping("/borrar/{alumnoId}")
    public String borrarAlumno(@PathVariable int alumnoId) {
        alumnoService.removeAlumno(alumnoId);
        return "redirect:/index/list";
    }

    
    
    @GetMapping("/edit/{alumnoId}")
    public String showEditForm(@PathVariable("alumnoId") int alumnoId, Model model) {
        List<FamiliaProfesional> familiasProfesionales = familiaProfesionalRepository.findAll();
        model.addAttribute("familiasProfesionales", familiasProfesionales);
        AlumnoModel alumno = alumnoService.getAlumnoById(alumnoId);
        model.addAttribute("alumno", alumno);
        return "editAlumno";
    }

    
    
    


}
