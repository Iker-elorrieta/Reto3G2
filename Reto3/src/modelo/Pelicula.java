package modelo;

public class Pelicula {
	private String codPelicula;
	private Hora duracion;
	private String genero;
	private float coste;
	
	public String getCodPelicula() {
		return codPelicula;
	}
	public void setCodPelicula(String codPelicula) {
		this.codPelicula = codPelicula;
	}
	public Hora getDuracion() {
		return duracion;
	}
	public void setDuracion(Hora duracion) {
		this.duracion = duracion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public float getCoste() {
		return coste;
	}
	public void setCoste(float coste) {
		this.coste = coste;
	}
}
