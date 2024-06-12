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

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;

@Controller
@RequestMapping("/docentes")
public class DocenteController {
	
	@Autowired
	private DocenteService docenteService;
	
	@GetMapping("/")
	public String docenteList(Model model) {
		List<Docente> docentes = docenteService.getDocentes();
		model.addAttribute("docentes", docentes);
		return "ListaDocentes";
	}
	
	@GetMapping("/nuevo")
	public ModelAndView docenteNuevo() {
		Docente docente = new Docente();
		ModelAndView model = new ModelAndView("FormDocente");
		model.addObject("docente", docente);
		model.addObject("band", false);
		return model;
	}

	@PostMapping("/guardar")
	public String docenteGuardar(@ModelAttribute("docente") Docente docente) {
			docenteService.guardar(docente);
			return "redirect:/docentes/";
	}
	
	@GetMapping("/modificar/{legajo}")
	public ModelAndView editarDocente(@PathVariable(name="legajo") String legajo) {
		//buscar
		Docente docenteParaModificar = docenteService.getDocenteBy(legajo);
		 if (docenteParaModificar == null) {
	            // Manejar el caso cuando el docente no es encontrado 
	            ModelAndView errorView = new ModelAndView("error");
	            errorView.addObject("mensaje", "Docente no encontrado");
	            return errorView;
	        }
		//mostrar el nuevo formulario
		ModelAndView modelView = new ModelAndView("FormDocente");
		modelView.addObject("docente", docenteParaModificar);	
		modelView.addObject("band", true);
		return modelView;		
		}
	
	@PostMapping("/modificarDocente")
	public String modificarDocente(@ModelAttribute("docente") Docente docenteModificado) {	
		//guardar
		docenteService.modificarDocente(docenteModificado);
		
		return "redirect:/docentes/";		
	}
	
	@GetMapping("/borrar/{legajo}")
	public String borrarDocente(@PathVariable(name="legajo") String legajo) {
		//borrar
		docenteService.eliminarDocente(legajo);	
		return "redirect:/docentes/";		
	}
	
	
}
