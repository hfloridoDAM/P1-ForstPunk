package model;

import java.util.TreeSet;

public class Edificio {
	protected TreeSet<Persona> personas;
	private int calor;
	private int calorMinimo;
	private String nombre;

	public Edificio(int calorMinimo, String nombre, int calor) {
		this.personas = new TreeSet<>();
		this.calorMinimo = calorMinimo;
		this.nombre = nombre;
		this.calor = calor;
	}

	public TreeSet<Persona> getPersonas() {
		return this.personas;
	}
	
	public void incrementarCalor() {
		calor++;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getCalor() {
		return this.calor;
	}
	
	public int getCalorMinimo() {
		return this.calorMinimo;
	}

	public void addPersona(String nombre) {
		if (this.personas.size() < 10)
			this.personas.add(new Persona(nombre));
		else
			System.out.println("Máximo alcancado");
	}
	
	public void addPersona(Persona p) {
		if (this.personas.size() < 10)
			this.personas.add(p);
		else
			System.out.println("Máximo alcancado");
	}
	
	public Persona getNoOcupado(boolean enfermo) {
		for (Persona p : this.personas) {
			if (enfermo) {
				if (!p.isOcupado() && p.isEnfermo()) return p;
			} else {
				if (!p.isOcupado()) return p;
			}
		}
		return null;
	}

	public String checkMinimo(int calor) {
		if (calor < this.calorMinimo) return ">> ";
		return "";
	}

	@Override
	public String toString() {
		String resumen = this.nombre + ": \n";
		for (Persona p : this.personas) {
			resumen += "\t\t" + p.toString() + "\n";
		}
		if (this.personas.size() == 0) 
			resumen += "\t\tNo hay personas\n";
		return resumen;
	}

}
