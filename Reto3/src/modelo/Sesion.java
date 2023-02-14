package modelo;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Sesion {
	private Pelicula peliSesion;
	private Integer idEmision;
	private String nombreSala;
	private Date fechaSesion;
	private LocalTime horaSesion;
	private float precio;
	
	
	public Sesion(Pelicula peliSesion, Integer idEmision, String nombreSala, Date fechaSesion, LocalTime horaSesion,
			float precio) {
		super();
		this.peliSesion = peliSesion;
		this.idEmision = idEmision;
		this.nombreSala = nombreSala;
		this.fechaSesion = fechaSesion;
		this.horaSesion = horaSesion;
		this.precio = precio;
	}
	public Pelicula getPeliSesion() {
		return peliSesion;
	}
	public void setPeliSesion(Pelicula peliSesion) {
		this.peliSesion = peliSesion;
	}
	public Date getFechaSesion() {
		return fechaSesion;
	}
	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}
	public LocalTime getHoraSesion() {
		return horaSesion;
	}
	public void setHoraSesion(LocalTime horaSesion) {
		this.horaSesion = horaSesion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fechaSesion, horaSesion, idEmision, nombreSala, peliSesion, precio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sesion other = (Sesion) obj;
		return Objects.equals(fechaSesion, other.fechaSesion) && Objects.equals(horaSesion, other.horaSesion)
				&& Objects.equals(idEmision, other.idEmision) && Objects.equals(nombreSala, other.nombreSala)
				&& Objects.equals(peliSesion, other.peliSesion)
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio);
	}
	@Override
	public String toString() {
		return "La sesion con la pelicula " + peliSesion + ", nombre de sala " + nombreSala + ", en la fecha " + fechaSesion
				+ ", a la hora " + horaSesion + ", y al precio " + precio+" y el id "+idEmision;
	}
	public Integer getIdEmision() {
		return idEmision;
	}
	public void setIdEmision(Integer idEmision) {
		this.idEmision = idEmision;
	}

	
}
