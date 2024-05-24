package ar.edu.unju.fi.model;

public class Materia {
	private String codigo;
	private String nombre;
	private String curso;
	private int cantidadHoras;
	private Modalidad modalidad;
	private Docente docente;
	private Carrera carrera;
	public Materia(){}
	
	public enum Modalidad {
        VIRTUAL, PRESENCIAL
    }
}
