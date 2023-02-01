package modelo;

import java.util.Arrays;
import java.util.Objects;

public class Cine {
	private String codigoCine;
	private String nombreCine;
	private int numeroSalas;
	private String direccion;
	private Salas [] salasCine;

	public Cine(String codigoCine,String nombreCine,int numeroSalas, String direccion,Salas [] salasCine) {
		this.codigoCine=codigoCine;
		this.nombreCine=nombreCine;
		this.numeroSalas=numeroSalas;
		this.direccion=direccion;
		this.salasCine=salasCine;
	}
	public String getCodigoCine() {
		return codigoCine;
	}

	public int getNumeroSalas() {
		return numeroSalas;
	}

	public void setNumeroSalas(int numeroSalas) {
		this.numeroSalas = numeroSalas;
	}

	public String getNombreCine() {
		return nombreCine;
	}

	public void setNombreCine(String nombreCine) {
		this.nombreCine = nombreCine;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCodigoCine(String codigoCine) {
		this.codigoCine = codigoCine;
	}
	
	public Salas [] getSalasCine() {
		return salasCine;
	}

	public void setSalasCine(Salas [] salasCine) {
		this.salasCine = salasCine;
	}
	
	@Override
	public String toString() {
		return "El cine de codigo " + codigoCine + ", nombre " + nombreCine + ", con " + numeroSalas
				+ " salas, direccion " + direccion + " y salasCine=" + Arrays.toString(salasCine);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(salasCine);
		result = prime * result + Objects.hash(codigoCine, direccion, nombreCine, numeroSalas);
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
		return Objects.equals(codigoCine, other.codigoCine) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nombreCine, other.nombreCine) && numeroSalas == other.numeroSalas
				&& Arrays.equals(salasCine, other.salasCine);
	}
}
