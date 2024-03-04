package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.FamiliaProfesional;
import com.example.demo.entity.Servicio;
import com.example.demo.model.AlumnoModel;
import com.example.demo.model.ServicioModel;
import com.example.demo.repository.FamiliaProfesionalRepository;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.EmpresaService;
import com.example.demo.service.FamiliaProfesionalService;
import com.example.demo.service.ServicioService;

@Controller
@RequestMapping("/empresa/servicios")
public class ServicioController {

	@Autowired
	private ServicioService servicioService;

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private FamiliaProfesionalService familiaProfesionalService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private FamiliaProfesionalRepository familiaProfesionalRepository;

	@GetMapping
	public String mostrarServicios(
			@RequestParam(name = "familiaProfesional", required = false) String familiaProfesional,
			@RequestParam(name = "estado", required = false, defaultValue = "Todos") String estado, Model model) {
		Empresa empresaActual = empresaService.findByEmail();
		List<ServicioModel> servicios;

		if (familiaProfesional != null && !familiaProfesional.isEmpty()
				&& !familiaProfesional.equals("Todas las Familias Profesionales")) {
			servicios = servicioService.findServiciosByEmpresaAndFamiliaProfesional(empresaActual, familiaProfesional);
		} else {
			servicios = servicioService.findServiciosByEmpresa(empresaActual);
		}

		servicios = filtrarServiciosPorEstado(servicios, estado);

		List<FamiliaProfesional> familiasProfesionales = familiaProfesionalRepository.findAll();

		for (ServicioModel servicio : servicios) {
			servicio = asignarNombresAFamiliaYAlumno(servicio);
		}

		model.addAttribute("familiaProfesional", familiaProfesional);
		model.addAttribute("estado", estado);
		model.addAttribute("servicios", servicios);
		model.addAttribute("familiasProfesionales", familiasProfesionales);

		return "ServicioEmpresa";
	}

	private List<ServicioModel> filtrarServiciosPorEstado(List<ServicioModel> servicios, String estado) {
		switch (estado) {
		case "Realizados":
			return servicios.stream().filter(ServicioModel::isRealizado).collect(Collectors.toList());
		case "AsignadosNoRealizados":
			return servicios.stream().filter(servicio -> servicio.getIdAlumno() != null && !servicio.isRealizado())
					.collect(Collectors.toList());
		case "NoAsignados":
			return servicios.stream().filter(servicio -> servicio.getIdAlumno() == null).collect(Collectors.toList());
		default:
			return servicios;
		}
	}

	private ServicioModel asignarNombresAFamiliaYAlumno(ServicioModel servicio) {
		int idFamilia = servicio.getIdFamilia().getId();
		String nombreFamilia = familiaProfesionalService.obtenerNombrePorId(idFamilia);
		servicio.getIdFamilia().setNombre(nombreFamilia);

		if (servicio.getIdAlumno() != null) {
			AlumnoModel alumno = alumnoService.getAlumnoById(servicio.getIdAlumno().getId());
			if (alumno != null) {
				servicio.getIdAlumno().setNombre(alumno.getNombre());
			}
		}

		return servicio;
	}

	@GetMapping("/crear")
	public String mostrarFormularioCreacion(Model model) {
		List<FamiliaProfesional> familiasProfesionales = familiaProfesionalRepository.findAll();
		model.addAttribute("familiasProfesionales", familiasProfesionales);
		model.addAttribute("servicioModel", new ServicioModel());
		return "crearServicio";
	}

	@PostMapping("/crear")
	public String crearServicio(@ModelAttribute("servicioModel") ServicioModel servicioModel) {
		Empresa empresaActual = empresaService.findByEmail(); 
		if (empresaActual != null) {
			servicioModel.setIdEmpresa(empresaActual);
			servicioService.addServicio(servicioModel);
			return "redirect:/empresa/servicios";
		} else {
			return "redirect:/empresa/servicios"; 
		}
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
		ServicioModel servicio = servicioService.getServicioById(id);
		if (servicio != null) {
			List<FamiliaProfesional> familiasProfesionales = familiaProfesionalRepository.findAll();
			model.addAttribute("familiasProfesionales", familiasProfesionales);
			model.addAttribute("servicioModel", servicio);
			return "editarServicio";
		} else {
			return "redirect:/empresa/servicios";
		}
	}

	@PostMapping("/editar")
	public String editarServicio(@ModelAttribute("servicioModel") ServicioModel servicioModel) {
		
		Servicio servicioActualizado = servicioService.updateServicio(servicioModel);

		if (servicioActualizado != null) {
			return "redirect:/empresa/servicios";
		} else {
			return "redirect:/empresa/servicios"; 
		}
	}

	@GetMapping("/borrar/{id}")
	public String borrarServicio(@PathVariable int id) {

		servicioService.removeServicio(id);
		return "redirect:/empresa/servicios";
	}

	@GetMapping("/valorar/{id}")
	public String mostrarFormularioValoracion(@PathVariable int id, Model model) {
		ServicioModel servicioModel = servicioService.getServicioById(id);
		if (servicioModel != null) {
			model.addAttribute("servicio", servicioModel);
			return "formulario_valoracion"; 
		} else {
			return "redirect:/empresa/servicios";
		}
	}

	@PostMapping("/valorar/{id}")
	public String valorarServicio(@PathVariable int id, @RequestParam("valoracion") int valoracion) {
	    ServicioModel servicioModel = servicioService.getServicioById(id);
	    if (servicioModel != null) {
	        servicioModel.setValoracionEmpresa(valoracion);
	        servicioService.updateValoracion(servicioModel);
	    }
	    return "redirect:/empresa/servicios";
	}
	
	   @GetMapping("/comentar/{id}")
	    public String mostrarFormularioComentario(@PathVariable int id, Model model) {
	        ServicioModel servicioModel = servicioService.getServicioById(id);
	        if (servicioModel != null && servicioModel.isRealizado()) {
	            model.addAttribute("servicio", servicioModel);
	            return "formulario_comentario"; 
	        } else {
	            return "redirect:/empresa/servicios";
	        }
	    }

	    @PostMapping("/comentar/{id}")
	    public String comentarServicio(@PathVariable int id, @RequestParam("comentario") String comentario) {
	        ServicioModel servicioModel = servicioService.getServicioById(id);
	        if (servicioModel != null) {
	            servicioModel.setComentarioEmpresa(comentario);
	            servicioService.updateComentario(servicioModel);
	            return "redirect:/empresa/servicios"; 
	        } else {
	            
	            return "redirect:/empresa/servicios";
	        }
	    }

}