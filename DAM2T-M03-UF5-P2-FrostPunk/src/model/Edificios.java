package model;

public enum Edificios {
	REFUGIO(1,1, "Refugio"), COCINA(0,1, "Cocina"), ASERRADERO(-2,0,"Aserradero"), CAVANACAZA(-2,0,"CavanaCaza"), MINACARBON(-2,0,"MinaCarbon"), PUESTOMEDICO(1,0,"PuestoMedico"), MOTORVAPOR(-5,3,"MotorVapor");
	
	private int calorMinimo;
	private int calor;
	private String nombre;

	Edificios(int calorMinimo, int calor, String nombre) {
		this.calorMinimo = calorMinimo;
		this.calor = calor;
		this.nombre = nombre;
	}

	static Edificio getEdificio(String edificio) {
		switch (edificio.toLowerCase()) {
			case "refugio":
				return new Edificio(REFUGIO.getCalorMinimo(), REFUGIO.getNombre(), REFUGIO.getCalor());
			case "cocina":
				return new Edificio(COCINA.getCalorMinimo(), COCINA.getNombre(), COCINA.getCalor());
			case "aserradero":
				return new Edificio(ASERRADERO.getCalorMinimo(), ASERRADERO.getNombre(), ASERRADERO.getCalor());
			case "cavanacaza":
				return new Edificio(CAVANACAZA.getCalorMinimo(), CAVANACAZA.getNombre(), CAVANACAZA.getCalor());
			case "minacarbon":
				return new Edificio(MINACARBON.getCalorMinimo(), MINACARBON.getNombre(), MINACARBON.getCalor());
			case "puestomedico":
				return new Edificio(PUESTOMEDICO.getCalorMinimo(), PUESTOMEDICO.getNombre(), PUESTOMEDICO.getCalor());
			case "motorvapor":
				return new Edificio(MOTORVAPOR.getCalorMinimo(), MOTORVAPOR.getNombre(), MOTORVAPOR.getCalor());
			default:
				return new Edificio(0, "", 0);
		}
	}
	
	public int getCalorMinimo() {
		return this.calorMinimo;
	}

	public int getCalor() {
		return calor;
	}

	public String getNombre() {
		return nombre;
	}
	
}
