package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Empresa;
import com.example.demo.model.AlumnoModel;
import com.example.demo.model.EmpresaModel;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.EmpresaService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/index")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/empresas")
    public String mostrarEmpresas(Model model) {
        model.addAttribute("empresas", empresaService.ListAllEmpresas());
        return "empresaAdmin";
    }
    
    @GetMapping("/crearempresa")
    public String mostrarFormularioNuevaEmpresa(Model model) {
        model.addAttribute("nuevaEmpresa", new Empresa());
        return "crearempresa";
    }

    @PostMapping("/crearempresa")
    public String crearEmpresa(@ModelAttribute("nuevaEmpresa") Empresa nuevaEmpresa,
        @RequestParam("logoImagen") MultipartFile file,
        RedirectAttributes flash, HttpServletResponse response) {
        AlumnoModel nuevoAlumnoModel = new AlumnoModel();
        nuevoAlumnoModel.setNombre(nuevaEmpresa.getNombre());
        nuevoAlumnoModel.setUsername(nuevaEmpresa.getEmail());
        nuevoAlumnoModel.setPassword("empresa");
        nuevoAlumnoModel.setRole("ROLE_EMPRESA");

        nuevoAlumnoModel.setIdFamilia(nuevaEmpresa.getId());

        alumnoService.registrarEmpresas(nuevoAlumnoModel);

        
        try {
            String projectDir = System.getProperty("user.dir");
            String uploadDir = projectDir + "/src/main/resources/static/imgs/logos/";

            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String logoName = file.getOriginalFilename();

            if (nuevaEmpresa.getLogo() != null && !nuevaEmpresa.getLogo().isEmpty()) {
                File oldImageFile = new File(uploadDir + nuevaEmpresa.getLogo());
                if (oldImageFile.exists()) {
                    oldImageFile.delete();
                }
            }

            file.transferTo(new File(uploadDir + logoName));

            nuevaEmpresa.setLogo(logoName);

        } catch (IOException e) {
            e.printStackTrace();
            
        }

        empresaService.guardarEmpresa(nuevaEmpresa);

        return "redirect:/index/empresas";
    }

    @PostMapping("/empresas/{empresaId}")
    public String deleteEmpresa(@PathVariable int empresaId) {
        empresaService.deleteEmpresa(empresaId);
        return "redirect:/index/empresas";
    }
    
    @GetMapping("/editEmpresa/{empresaId}")
    public String showEditFormEmpresa(@PathVariable("empresaId") int empresaId, Model model) {
        Empresa empresa = empresaService.ListAllEmpresasById(empresaId);

        model.addAttribute("empresa", empresa);

        return "editEmpresa";
    }

    @PostMapping("/editEmpresa")
    public String processEditFormEmpresa(@ModelAttribute("empresa") EmpresaModel empresa,
    						@RequestParam("logoImagen") MultipartFile file,
    						RedirectAttributes flash, HttpServletResponse response) {
    	EmpresaModel empresaModel = empresaService.getEmpresaById(empresa.getId());
        
        if (empresaModel != null) {
        	if(empresaModel.getNombre().isEmpty()) {
        		flash.addFlashAttribute("error", "Campo Nombre vacio");
        	} else {
        		String projectDir = System.getProperty("user.dir");
        		
        		String uploadDir = projectDir + "/src/main/resources/static/imgs/logos/";
        		
        		try {
        			File uploadDirFile = new File(uploadDir);
        			if(!uploadDirFile.exists()) {
        				uploadDirFile.mkdirs();
        			}
        			
        			String logoName = file.getOriginalFilename();
        			
        			if (empresa.getLogo() != null) {
        				File oldImageFile = new File(uploadDir + empresa.getLogo());
        				if (oldImageFile.exists()) {
        					oldImageFile.delete();
        				}
        			}
        			
        			file.transferTo(new File(uploadDir + logoName));
        			
        			empresa.setLogo(logoName);
        			
         		} catch (IOException e) {
         			e.printStackTrace();
         		}
        		
        		empresaService.updateEmpresa(empresa);
        		flash.addFlashAttribute("success", "Empresa actualizada con exito");
        	}
        }
        return "redirect:/index/empresas";
    }



}