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
import com.example.demo.model.FamiliaProfesionalModel;
import com.example.demo.service.FamiliaProfesionalService;

@Controller
@RequestMapping("/index/familias")
public class FamiliaController {

    @Autowired
    private FamiliaProfesionalService familiaProfesionalService;

    @GetMapping
    public String mostrarFamilias(Model model) {
        model.addAttribute("familias", familiaProfesionalService.obtenerTodasFamiliasProfesionales());
        return "Familiaadmin";
    }

    @GetMapping("/crear")
    public String mostrarFormularioNuevaFamilia(Model model) {
        FamiliaProfesionalModel nuevaFamiliaModel = new FamiliaProfesionalModel();
        model.addAttribute("nuevaFamilia", nuevaFamiliaModel);
        return "crearFamilias";
    }

    @PostMapping("/crear")
    public String crearFamilia(@ModelAttribute("nuevaFamilia") FamiliaProfesionalModel nuevaFamiliaModel) {
        FamiliaProfesional nuevaFamilia = familiaProfesionalService.guardarFamiliaProfesional(nuevaFamiliaModel);
        return "redirect:/index/familias";
    }


    @PostMapping("/editar")
    public String processEditForm(@ModelAttribute("familia") FamiliaProfesionalModel familiaModel) {
        familiaProfesionalService.updateFamilia(familiaModel);

        return "redirect:/index/familias";
    }

    
    @GetMapping("/editar/{familiaId}")
    public String showEditForm(@PathVariable("familiaId") int familiaId, Model model) {
        List<FamiliaProfesional> familiasProfesionales = familiaProfesionalService.obtenerTodasFamiliasProfesionales();
        model.addAttribute("familiasProfesionales", familiasProfesionales);

        FamiliaProfesional familia = familiaProfesionalService.obtenerFamiliaProfesionalPorId(familiaId);
        model.addAttribute("familia", familia);

        return "editFamilia";
    }
    
    
    
    @PostMapping("/borrar")
    public String borrarFamilia(@RequestParam("id") int id) {
        familiaProfesionalService.borrarFamiliaProfesional(id);
        return "redirect:/index/familias";
    }
}

