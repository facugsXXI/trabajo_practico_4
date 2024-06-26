package ar.edu.unju.fi.model;

import java.time.LocalDate;

public class Alumno {
	private int dni;
	private String nombre;
	private String apellido;
	private String email;
	private int telefono;
	private LocalDate fechaNacimiento;
	private String domicilio;
	private String libretaUniversitaria;
	
	
	public Alumno() {} 
	public Alumno(int dni, String nombre, String apellido, String email, int telefono, LocalDate fechaNacimiento,
			String domicilio, String libretaUniversitaria) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.libretaUniversitaria = libretaUniversitaria;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public String getLibretaUniversitaria() {
		return libretaUniversitaria;
	}


	public void setLibretaUniversitaria(String libretaUniversitaria) {
		this.libretaUniversitaria = libretaUniversitaria;
	}
	
	
}
