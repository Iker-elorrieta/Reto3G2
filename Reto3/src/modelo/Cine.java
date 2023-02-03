package modelo;

import java.util.Arrays;
import java.util.Objects;

public class Cine {
	private String nombreCine;
	private Salas [] salasCine;

	public Cine(String nombreCine,Salas [] salasCine) {
		this.nombreCine=nombreCine;
		this.salasCine=salasCine;
	}

	public String getNombreCine() {
		return nombreCine;
	}

	public void setNombreCine(String nombreCine) {
		this.nombreCine = nombreCine;
	}
	
	public Salas [] getSalasCine() {
		return salasCine;
	}

	public void setSalasCine(Salas [] salasCine) {
		this.salasCine = salasCine;
	}
	
	@Override
	public String toString() {
		return "El cine de nombre " + nombreCine + " y con las salas " + Arrays.toString(salasCine);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(salasCine);
		result = prime * result + Objects.hash(nombreCine);
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
		Cine other = (Cine) obj;
		return  Objects.equals(nombreCine, other.nombreCine) && Arrays.equals(salasCine, other.salasCine);
	}
}
