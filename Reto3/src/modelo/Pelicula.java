package modelo;

import java.util.Objects;

public class Pelicula {
	private String codigoPelicula;
	private String nombrePelicula;
	private String generoPelicula;
	private int duracionPelicula;
	private int costeLicencia;
	
	public Pelicula(String codigoPelicula, String nombrePelicula, String generoPelicula, int duracionPelicula,
			int costeLicencia) {
		this.codigoPelicula = codigoPelicula;
		this.nombrePelicula = nombrePelicula;
		this.generoPelicula = generoPelicula;
		this.duracionPelicula = duracionPelicula;
		this.costeLicencia = costeLicencia;
	}
	
	public String getCodigoPelicula() {
		return codigoPelicula;
	}
	public void setCodigoPelicula(String codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}
	public String getNombrePelicula() {
		return nombrePelicula;
	}
	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}
	public String getGeneroPelicula() {
		return generoPelicula;
	}
	public void setGeneroPelicula(String generoPelicula) {
		this.generoPelicula = generoPelicula;
	}
	public int getDuracionPelicula() {
		return duracionPelicula;
	}
	public void setDuracionPelicula(int duracionPelicula) {
		this.duracionPelicula = duracionPelicula;
	}
	public int getCosteLicencia() {
		return costeLicencia;
	}
	public void setCosteLicencia(int costeLicencia) {
		this.costeLicencia = costeLicencia;
	}
	
	@Override
	public String toString() {
		return "La pelicula de codigo " + codigoPelicula + ", nombre " + nombrePelicula + ", genero "
				+ generoPelicula + ", duracion " + duracionPelicula + " y coste de licencia " + costeLicencia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigoPelicula, costeLicencia, duracionPelicula, generoPelicula, nombrePelicula);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return Objects.equals(codigoPelicula, other.codigoPelicula) && costeLicencia == other.costeLicencia
				&& duracionPelicula == other.duracionPelicula && Objects.equals(generoPelicula, other.generoPelicula)
				&& Objects.equals(nombrePelicula, other.nombrePelicula);
	}
}
