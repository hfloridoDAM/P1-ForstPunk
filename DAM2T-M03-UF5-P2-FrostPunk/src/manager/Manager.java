package manager;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Map.Entry;

import dao.Reader;
import model.Circle;
import model.Edificio;
import model.Edificios;
import model.Persona;

public class Manager {
	public static int tiempo;
	private static Manager manager;
	private Reader reader;
	private ArrayList<Circle> circles;
	private TreeSet<Persona> personas;

	private Manager () {
		tiempo = 0;
		this.circles = new ArrayList<>();
		this.personas = new TreeSet<>();
		this.reader = new Reader("files/entrada.txt");
	}
	
	public static Manager getInstance() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}

	public void init() {
		createDistricts();
		readFile();
	}
	
	private void createDistricts() {
		for (int i = 0; i < 5; i++) {
			this.circles.add(new Circle());
		}
	}

	public void readFile() {
		String line;
		while ((line = reader.read()) != null) {
			switch (line.substring(0, 1).toUpperCase()) {
				case "<":
					addEdificio(line.split(" "));
					break;
				case "+":
					addPersona(line.split(" "));
					break;
				case "S":
					showPersonas();
					break;
				case "C":
					showCiudad();
					break;
				case ">":
					ocuparPersona(line.split(" "));
					break;
				case "T":
					tiempo = Integer.parseInt(line.split(" ")[1]);
					break;
				default:
					System.out.println("Instrucción incorrecta");
			}
		}
		checkTemperaturas();
	}

	private void checkTemperaturas() {
		for (Circle c : this.circles) {
			for (Entry<Integer, Edificio> entidad : c.getSecciones().entrySet()) {
				Integer calor = entidad.getValue() != null? entidad.getValue().getCalor() : null;
				if (calor != null && !entidad.getValue().checkMinimo(calor).isEmpty()) {
					for (Persona p : entidad.getValue().getPersonas()) {
						p.enfermar();
					}
				}
			}
		}
	}

	private void ocuparPersona(String[] split) {
		Persona p = circles.get(Integer.parseInt(split[1].replace("<", "").replace(">", "").split(",")[0]))
				.getPersonaNoOcupada(Integer.parseInt(split[1].replace("<", "").replace(">", "").split(",")[1]));
		if (p != null) {
			circles.get(Integer.parseInt(split[2].replace("<", "").replace(">", "").split(",")[0]))
					.ocuparPersona(Integer.parseInt(split[2].replace("<", "").replace(">", "").split(",")[1]), p);
			System.out.println("Persona asignada"); //TODO: revisar syso
		} else {
			System.out.println("No hay personas disponibles o no se pueden asignar");
		}
	}

	private void showCiudad() {
		System.out.println("--- Mostrando Ciudad ---");
		int i = 0;
		for(Circle c : this.circles) {
			System.out.println("Circulo " + i);
			System.out.println(c.toString());
			i++;
		}
		System.out.println("------------------------");
	}

	private void showPersonas() {
		System.out.println("--- Mostrando Personas ---");
		for (Persona p : this.personas) {
			System.out.println(p);
		}
		System.out.println("--------------------------");
	}

	private void addPersona(String[] split) {
		if (circles.get(Integer.parseInt(split[2].replace("<", "").split(",")[0]))
				.checkParcela(Integer.parseInt(split[2].replace(">", "").split(",")[1]))
				&& personas.add(new Persona(split[1], split[2].replace("<", "").replace(">", "").split(",")[0],
				split[2].replace("<", "").replace(">", "").split(",")[1]))) {
			if (circles.get(Integer.parseInt(split[2].replace("<", "").replace(">", "").split(",")[0]))
					.addPersona(Integer.parseInt(split[2].replace("<", "").replace(">", "").split(",")[1]), split[1])) {
				System.out.println("Persona added");
			}
		} else {
			System.out.println("Persona NO added");
		}
	}

	private void addEdificio(String[] split) {
		Edificio e = circles.get(Integer.parseInt(split[0].replace("<", "").split(",")[0]))
				.addEdificio(Integer.parseInt(split[0].replace(">", "").split(",")[1]), split[1]);
		if (e != null) {
			if (e.getNombre().equals(Edificios.MOTORVAPOR.getNombre())) {
				maquinaVaporAdded(split[0]);
			}
			System.out.println("Edificio added");
		}
	}

	private void maquinaVaporAdded(String split) {
		int circle = Integer.parseInt(split.replace("<", "").replace(">", "").split(",")[0]);
		int seccion = Integer.parseInt(split.replace("<", "").replace(">", "").split(",")[1]);
		//extra
		
	}
	
}
