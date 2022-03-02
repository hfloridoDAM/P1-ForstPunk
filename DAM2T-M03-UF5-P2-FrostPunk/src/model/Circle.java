package model;

import java.util.Map.Entry;
import java.util.TreeMap;

import manager.Manager;

public class Circle {
	private TreeMap<Integer, Edificio> secciones;
	
	public Circle () {
		this.secciones = new TreeMap<>();
		generateSecciones();
	}

	private void generateSecciones() {
		for (int i = 1; i <= 10; i++) {
			this.secciones.put(i, null);
		}
	}
	
	public Edificio addEdificio(int seccion, String edificio) {
		Edificio e = Edificios.getEdificio(edificio);
		if (this.secciones.get(seccion) == null) {
			this.secciones.put(seccion, e);
			return e;
		}
		System.out.println("La parcela no está vacía");
		return null;
	}

	public boolean addPersona(int seccion, String nombre) {
		if (this.secciones.get(seccion).getNombre().equalsIgnoreCase("Refugio")) {
			this.secciones.get(seccion).addPersona(nombre);
			return true;
		} else {
			System.out.println("Este edificio no es un refugio");
			return false;
		}
			
	}
	
	public Persona getPersonaNoOcupada(int seccion) {
		if (this.secciones.get(seccion).getNombre().equalsIgnoreCase(Edificios.REFUGIO.getNombre())) {
			for (Persona p : this.secciones.get(seccion).getPersonas()) {
				if (!p.isOcupado()) return p;
			}
		}
		return null;
			
	}

	public void ocuparPersona(int seccion, Persona p) {
		if (this.secciones.get(seccion).getNombre().equals(Edificios.REFUGIO.getNombre()))
			System.out.println("No se puede mover de un refugio a otro");
		else if (this.secciones.get(seccion).getNombre().equals(Edificios.MOTORVAPOR.getNombre()))
			System.out.println("No se puede mover a un motor de vapor");
		else 
			this.secciones.get(seccion).addPersona(p);
	}
	
	public TreeMap<Integer, Edificio> getSecciones() {
		return this.secciones;
	}
	
	@Override
	public String toString() {
		String resumen = "\n";
		for (Entry<Integer, Edificio> entidad : secciones.entrySet()) {
			int calor = getCalor(entidad.getValue());
			resumen += "\t" + (entidad.getValue() != null?entidad.getValue().checkMinimo(calor):"") + "Seccion: " + entidad.getKey() + " calor: " + calor
					+ " Edificio: " + (entidad.getValue() != null?entidad.getValue().toString():"vacio") + "\n";
		}
		return resumen;
	}

	private int getCalor(Edificio edificio) {
		if (edificio == null) return Manager.tiempo;
		return edificio.getCalor() + Manager.tiempo;
	}

	public boolean checkParcela(int seccion) {
		if (this.secciones.get(seccion) != null && this.secciones.get(seccion).getNombre().equalsIgnoreCase(Edificios.REFUGIO.getNombre())) {
			return true;
		} else {
			System.out.println("No hay un edificio en esta parcela");
			return false;
		}
	}
}
