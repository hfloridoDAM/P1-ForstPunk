package model;

public class Persona implements Comparable<Persona> {
	private String nombre;
	private String circle;
	private String distrito;
	private boolean ocupado;
	private boolean enfermo;

	public Persona(String nombre) {
		this.ocupado = false;
		this.enfermo = false;
		this.nombre = nombre;
	}
	
	public Persona(String nombre, String circle, String distrito) {
		this.ocupado = false;
		this.enfermo = false;
		this.nombre = nombre;
		this.circle = circle;
		this.distrito = distrito;
	}
	
	public void ocupar() {
		this.ocupado = true;
	}
	
	public boolean isEnfermo() {
		return this.enfermo;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public String getCircle() {
		return circle;
	}

	public String getDistrito() {
		return distrito;
	}

	public boolean isOcupado() {
		return this.ocupado;
	}

	@Override
	public int compareTo(Persona arg0) {
		if (this.nombre.equals(arg0.getNombre())) return 0;
		else {
			if (this.circle == null || distrito == null) 
				return 1;
			if (Integer.parseInt(this.circle) - Integer.parseInt(arg0.getCircle()) == 0) {
				if (Integer.parseInt(this.distrito) - Integer.parseInt(arg0.getDistrito()) == 0) return 1;
				else return Integer.parseInt(this.distrito) - Integer.parseInt(arg0.getDistrito());
			} else {
				return Integer.parseInt(this.circle) - Integer.parseInt(arg0.getCircle());
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (this.circle == null || distrito == null) 
			return "Persona [nombre=" + nombre + "]";
		else
			return "Persona [nombre=" + nombre + ", circle=" + circle
					+ ", distrito=" + distrito + "]";
	}
	
	public void curar() {
		this.enfermo = false;
	}

	public void enfermar() {
		this.enfermo = true;
	}

}