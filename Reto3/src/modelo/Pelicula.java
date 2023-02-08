package modelo;

import java.util.Objects;

public class Pelicula {
	private String nombrePelicula;
	private String generoPelicula;
	private int duracionPelicula;
	
	public Pelicula( String nombrePelicula, String generoPelicula, int duracionPelicula) {
		this.nombrePelicula = nombrePelicula;
		this.generoPelicula = generoPelicula;
		this.duracionPelicula = duracionPelicula;
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
		return "La pelicula de nombre " + nombrePelicula + ", genero "
				+ generoPelicula + ", duracion " + duracionPelicula;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombrePelicula ,generoPelicula, duracionPelicula);
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
		return  duracionPelicula == other.duracionPelicula && Objects.equals(generoPelicula, other.generoPelicula)
				&& Objects.equals(nombrePelicula, other.nombrePelicula);
	}
}
