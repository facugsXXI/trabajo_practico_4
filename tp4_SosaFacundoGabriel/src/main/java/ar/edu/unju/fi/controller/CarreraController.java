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
import ar.edu.unju.fi.service.CarreraService;

@Controller
@RequestMapping("/carreras")
public class CarreraController {
	@Autowired
	private CarreraService carreraService;
	
	@GetMapping("/")
	public String carreraList(Model model) {
		List<Carrera> carreras = carreraService.getCarreras();
		model.addAttribute("carreras", carreras);
		return "ListaCarreras";
	}
	
	@GetMapping("/nuevo")
	public ModelAndView carreraNueva() {
		Carrera carrera = new Carrera();
		ModelAndView model = new ModelAndView("FormCarrera");
		model.addObject("carrera", carrera);
		model.addObject("band", false);
		return model;
	}

	@PostMapping("/guardar")
	public String carreraGuardar(@ModelAttribute("carrera") Carrera carrera) {
			carreraService.guardar(carrera);
			return "redirect:/carreras/";
	}
	
	@GetMapping("/modificar/{codigo}")
	public ModelAndView editarCarrera(@PathVariable(name="codigo") String codigo) {
		//buscar
		Carrera carreraParaModificar = carreraService.getCarreraBy(codigo);
		 if (carreraParaModificar == null) {
	            // Manejar el caso cuando el docente no es encontrado 
	            ModelAndView errorView = new ModelAndView("error");
	            errorView.addObject("mensaje", "Docente no encontrado");
	            return errorView;
	        }
		//mostrar el nuevo formulario
		ModelAndView modelView = new ModelAndView("FormCarrera");
		modelView.addObject("carrera", carreraParaModificar);	
		modelView.addObject("band", true);
		return modelView;		
		}
	
	@PostMapping("/modificarCarrera")
	public String modificarCarrera(@ModelAttribute("carrera") Carrera carreraModificada) {	
		//guardar
		carreraService.modificarCarrera(carreraModificada);
		
		return "redirect:/carreras/";		
	}
	
	@GetMapping("/borrar/{codigo}")
	public String borrarCarrera(@PathVariable(name="codigo") String codigo) {
		//borrar
		carreraService.eliminarCarrera(codigo);	
		return "redirect:/carreras/";		
	}
}
