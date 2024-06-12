package ar.edu.unju.fi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Alumno;


@Service
public class AlumnoService {
	private static List<Alumno> alumnos;

	static {
		Alumno alumno1 = new Alumno(11111111, "Ariel", "Alaba", "ariel@alaba", 4111111, LocalDate.now(), "casa1", "1111");
		Alumno alumno2 = new Alumno(22222222, "Emilio", "Emilce", "emilio@emilce", 4222222, LocalDate.now(), "casa2", "2222");
		Alumno alumno3 = new Alumno(33333333, "Irina", "Irlan", "irina@irlan", 4333333, LocalDate.now(), "casa3", "3333");
		alumnos = new ArrayList<>();
		alumnos.add(alumno1);
		alumnos.add(alumno2);
		alumnos.add(alumno3);
	}

	public List<Alumno> getAlumnos() {
		
		return alumnos;
	}

	//guardar un alumno
	public void guardar(Alumno alumno) {
		alumnos.add(alumno);
	}
	
	//devolver un alumno por su dni
	public Alumno getAlumnoBy(int dni) {
		for (Alumno alumno : alumnos) {
			if (alumno.getDni() == dni) {
				return alumno;
			}
		}
		return null;
	}
	
	//Método para buscar el index de un alumno y saber si existe
	public Integer getIndexFor(Alumno alumno) {
		for (int i = 0 ; i < alumnos.size(); i++) {
			if(alumnos.get(i).getDni() == alumno.getDni()) {
				return i;
			}
		}
		
		return null;
	}

	 // Método para modificar un Alumno
	  public void modificarAlumno(Alumno alumnoModificado) {
		  Integer ind = getIndexFor(alumnoModificado);
		  if(ind != null) {
			  alumnos.set(ind, alumnoModificado);
		  }
	  }

	  // Método para eliminar un Alumno
	  public void eliminarAlumno(int dni) {
	    alumnos.removeIf(alumno -> alumno.getDni() == dni);
	  }
}
