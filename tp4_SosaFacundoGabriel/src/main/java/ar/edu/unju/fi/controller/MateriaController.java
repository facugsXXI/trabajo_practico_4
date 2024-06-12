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

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;

@Controller
@RequestMapping("/materias")
public class MateriaController {
	@Autowired
	private MateriaService materiaService;
	
	@Autowired
	private DocenteService docenteService;
	
	@Autowired
	private CarreraService carreraService;
	
	
	@GetMapping("/")
	public String carreraList(Model model) {
		List<Materia> materias = materiaService.getMaterias();
		model.addAttribute("materias", materias);
		return "ListaMaterias";
	}
	
	@GetMapping("/nuevo")
	public ModelAndView materiaNueva() {
		Materia materia = new Materia();
		ModelAndView model = new ModelAndView("FormMateria");
		model.addObject("materia", materia);
		model.addObject("band", false);
		model.addObject("modalidades", Materia.Modalidad.values());
		model.addObject("docentes", docenteService.getDocentes());
		model.addObject("carreras", carreraService.getCarreras());
		return model;
	}

	@PostMapping("/guardar")
	public String materiaGuardar(@ModelAttribute("materia") Materia materia) {
		Docente unDocente = docenteService.getDocenteBy(materia.getDocente().getLegajo());
		materia.setDocente(unDocente);
		Carrera unaCarrera = carreraService.getCarreraBy(materia.getCarrera().getCodigo());
		materia.setCarrera(unaCarrera);
		materiaService.guardar(materia);
		return "redirect:/materias/";
	}
	
	@GetMapping("/modificar/{codigo}")
	public ModelAndView editarMateria(@PathVariable(name="codigo") String codigo) {
		//buscar
		Materia materiaParaModificar = materiaService.getMateriaBy(codigo);
		 if (materiaParaModificar == null) {
	            // Manejar el caso cuando la materia no es encontrada 
	            ModelAndView errorView = new ModelAndView("error");
	            errorView.addObject("mensaje", "Materia no encontrada");
	            return errorView;
	        }
		//mostrar el nuevo formulario
		ModelAndView modelView = new ModelAndView("FormMateria");
		modelView.addObject("materia", materiaParaModificar);	
		modelView.addObject("band", true);
		modelView.addObject("modalidades", Materia.Modalidad.values());
		modelView.addObject("docentes", docenteService.getDocentes());
		modelView.addObject("carreras", carreraService.getCarreras());
		return modelView;		
		}
	
	@PostMapping("/modificarMateria")
	public String modificarMateria(@ModelAttribute("materia") Materia materiaModificada) {	
		//guardar
		Docente otroDocente = docenteService.getDocenteBy(materiaModificada.getDocente().getLegajo());
		materiaModificada.setDocente(otroDocente);
		Carrera otraCarrera = carreraService.getCarreraBy(materiaModificada.getCarrera().getCodigo());
		materiaModificada.setCarrera(otraCarrera);
		materiaService.modificarMateria(materiaModificada);
		
		return "redirect:/materias/";		
	}
	
	@GetMapping("/borrar/{codigo}")
	public String borrarMateria(@PathVariable(name="codigo") String codigo) {
		//borrar
		materiaService.eliminarMateria(codigo);	
		return "redirect:/materias/";		
	}
}
