package modelo;

import java.util.Objects;

public class Pelicula {
	private String codigoPelicula;
	private String nombrePelicula;
	private String generoPelicula;
	private int duracionPelicula;
	
	public Pelicula(String codigoPelicula, String nombrePelicula, String generoPelicula, int duracionPelicula) {
		this.codigoPelicula = codigoPelicula;
		this.nombrePelicula = nombrePelicula;
		this.generoPelicula = generoPelicula;
		this.duracionPelicula = duracionPelicula;
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
	
	@Override
	public String toString() {
		return "La pelicula de codigo " + codigoPelicula + ", nombre " + nombrePelicula + ", genero "
				+ generoPelicula + ", duracion " + duracionPelicula;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigoPelicula, nombrePelicula ,generoPelicula, duracionPelicula);
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
		return Objects.equals(codigoPelicula, other.codigoPelicula)
				&& duracionPelicula == other.duracionPelicula && Objects.equals(generoPelicula, other.generoPelicula)
				&& Objects.equals(nombrePelicula, other.nombrePelicula);
	}
}
