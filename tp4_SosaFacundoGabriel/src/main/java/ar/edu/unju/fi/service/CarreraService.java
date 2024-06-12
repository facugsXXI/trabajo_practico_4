package ar.edu.unju.fi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Carrera;

@Service
public class CarreraService {
	private static List<Carrera> carreras;

	static {
		Carrera carrera1 = new Carrera("C001", "Programación", 3, "Activa");
		Carrera carrera2 = new Carrera("C002", "Analista", 4, "Activa");
		Carrera carrera3 = new Carrera("C003", "Juegos", 5, "Activa");
		carreras = new ArrayList<>();
		carreras.add(carrera1);
		carreras.add(carrera2);
		carreras.add(carrera3);
	}

	public List<Carrera> getCarreras() {
		
		return carreras;
	}

	//guardar una carrera
	public void guardar(Carrera carrera) {
		carreras.add(carrera);
	}
	
	//devolver una carrera por su codigo
	public Carrera getCarreraBy(String codigo) {
		for (Carrera carrera : carreras) {
			if (carrera.getCodigo().equals(codigo)) {
				return carrera;
			}
		}
		return null;
	}
	
	//Método para buscar el index de una carrera y saber si existe
	public Integer getIndexFor(Carrera carrera) {
		for (int i = 0 ; i < carreras.size(); i++) {
			if(carreras.get(i).getCodigo().equals(carrera.getCodigo())) {
				return i;
			}
		}
		
		return null;
	}

	 // Método para modificar una Carrera
	  public void modificarCarrera(Carrera carreraModificada) {
		  Integer ind = getIndexFor(carreraModificada);
		  if(ind != null) {
			  carreras.set(ind, carreraModificada);
		  }
	  }

	  // Método para eliminar una Carrera
	  public void eliminarCarrera(String codigo) {
	    carreras.removeIf(carrera -> carrera.getCodigo().equals(codigo));
	  }
}
