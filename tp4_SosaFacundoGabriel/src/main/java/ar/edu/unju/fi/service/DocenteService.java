package ar.edu.unju.fi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Docente;


@Service 
public class DocenteService {
	private static List<Docente> docentes;

	static {
		Docente docente1 = new Docente("000", "Aja", "Ajaja", "Ajaja@", 4101010);
		Docente docente2 = new Docente("111", "Eja", "Ejaja", "Ejaja@", 4101011);
		Docente docente3 = new Docente("222", "Ija", "Ijaja", "Ijaja@", 4101012);
		docentes = new ArrayList<>();
		docentes.add(docente1);
		docentes.add(docente2);
		docentes.add(docente3);
	}

	public List<Docente> getDocentes() {
		
		return docentes;
	}

	//guardar el docente
	public void guardar(Docente docente) {
		docentes.add(docente);
			
	}
	
	//devolver un docente por su legajo
	public Docente getDocenteBy(String legajo) {
		for (Docente docente : docentes) {
			if (docente.getLegajo().equals(legajo)) {
				return docente;
			}
		}
		return null;
	}
	
	//Método para buscar el index de un docente y saber si existe
	public Integer getIndexFor(Docente docente) {
		for (int i = 0 ; i < docentes.size(); i++) {
			if(docentes.get(i).getLegajo().equals(docente.getLegajo())) {
				return i;
			}
		}
		
		return null;
	}

	 // Método para modificar un Docente
	  public void modificarDocente(Docente docenteModificado) {
		  Integer ind = getIndexFor(docenteModificado);
		  if(ind != null) {
			  docentes.set(ind, docenteModificado);
		  }
	  }

	  // Método para eliminar un Docente
	  public void eliminarDocente(String leg) {
	    docentes.removeIf(docente -> docente.getLegajo().equals(leg));
	  }
}