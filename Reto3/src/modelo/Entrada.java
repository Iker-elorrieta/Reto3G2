package modelo;

import java.util.Date;

public class Entrada {
	private String codPelicula;
	private Date fecha;
	private String peliculas;
	private Hora hora;
	private String sala;
	private float coste;
	public String getCodPelicula() {
		return codPelicula;
	}
	public void setCodPelicula(String codPelicula) {
		this.codPelicula = codPelicula;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(String peliculas) {
		this.peliculas = peliculas;
	}
	public Hora getHora() {
		return hora;
	}
	public void setHora(Hora hora) {
		this.hora = hora;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public float getCoste() {
		return coste;
	}
	public void setCoste(float coste) {
		this.coste = coste;
	}
}
