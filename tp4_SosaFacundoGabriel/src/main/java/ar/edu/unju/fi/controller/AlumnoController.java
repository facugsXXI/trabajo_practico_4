package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.AlumnoService;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/")
	public String alumnoList(Model model) {
		List<Alumno> alumnos = alumnoService.getAlumnos();
		model.addAttribute("alumnos", alumnos);
		return "ListaAlumnos";
	}
	
	@GetMapping("/nuevo")
	public ModelAndView alumnoNuevo() {
		Alumno alumno = new Alumno();
		ModelAndView model = new ModelAndView("FormAlumno");
		model.addObject("alumno", alumno);
		model.addObject("band", false);
		return model;
	}

	@PostMapping("/guardar")
	public String alumnoGuardar(@ModelAttribute("alumno") Alumno alumno) {
			alumnoService.guardar(alumno);
			return "redirect:/alumnos/";
	}
	
	@GetMapping("/modificar/{dni}")
	public ModelAndView editarAlumno(@PathVariable(name="dni") int dni) {
		//buscar
		Alumno alumnoParaModificar = alumnoService.getAlumnoBy(dni);
		 if (alumnoParaModificar == null) {
	            // Manejar el caso cuando el docente no es encontrado 
	            ModelAndView errorView = new ModelAndView("error");
	            errorView.addObject("mensaje", "Alumno no encontrado");
	            return errorView;
	        }
		//mostrar el nuevo formulario
		ModelAndView modelView = new ModelAndView("FormAlumno");
		modelView.addObject("alumno", alumnoParaModificar);	
		modelView.addObject("band", true);
		return modelView;		
		}
	
	@PostMapping("/modificarAlumno")
	public String modificarAlumno(@ModelAttribute("alumno") Alumno alumnoModificado) {	
		//guardar
		alumnoService.modificarAlumno(alumnoModificado);
		
		return "redirect:/alumnos/";		
	}
	
	@GetMapping("/borrar/{dni}")
	public String borrarAlumno(@PathVariable(name="dni") int dni) {
		//borrar
		alumnoService.eliminarAlumno(dni);	
		return "redirect:/alumnos/";		
	}
}
