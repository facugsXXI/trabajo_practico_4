package ar.edu.unju.fi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import ar.edu.unju.fi.model.Materia;

@Service
public class MateriaService {
	private static List<Materia> materias;

	static {
		materias = new ArrayList<>();
	}

	public List<Materia> getMaterias() {
		
		return materias;
	}

	//guardar una Materia
	public void guardar(Materia materia) {
		materias.add(materia);
	}
	
	//devolver una Materia por su codigo
	public Materia getMateriaBy(String codigo) {
		for (Materia materia : materias) {
			if (materia.getCodigo().equals(codigo)) {
				return materia;
			}
		}
		return null;
	}
	
	//Método para buscar el index de una Materiay saber si existe
	public Integer getIndexFor(Materia materia) {
		for (int i = 0 ; i < materias.size(); i++) {
			if(materias.get(i).getCodigo().equals(materia.getCodigo())) {
				return i;
			}
		}
		
		return null;
	}

	 // Método para modificar una Materia
	  public void modificarMateria(Materia materiaModificada) {
		  Integer ind = getIndexFor(materiaModificada);
		  if(ind != null) {
			  materias.set(ind, materiaModificada);
		  }
	  }

	  // Método para eliminar una Materia
	  public void eliminarMateria(String codigo) {
	    materias.removeIf(materia -> materia.getCodigo().equals(codigo));
	  }
}
