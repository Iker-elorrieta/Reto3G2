package modelo;

import java.util.Date;
import java.util.Objects;

public class Entrada {
	private String codigoEntrada;
	private Date fechaEmision;
	private Hora horaEmision;
	private String nombrePelicula;
	private String nombreSalaEntrada;
	private float precioEntrada;
	
	public Entrada(String codigoEntrada, Date fechaEmision, Hora horaEmision, String nombrePelicula,
			String nombreSalaEntrada, float precioEntrada) {
		this.codigoEntrada = codigoEntrada;
		this.fechaEmision = fechaEmision;
		this.horaEmision = horaEmision;
		this.nombrePelicula = nombrePelicula;
		this.nombreSalaEntrada = nombreSalaEntrada;
		this.precioEntrada = precioEntrada;
	}
	
	public String getCodigoEntrada() {
		return codigoEntrada;
	}
	public void setCodigoEntrada(String codigoEntrada) {
		this.codigoEntrada = codigoEntrada;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Hora getHoraEmision() {
		return horaEmision;
	}
	public void setHoraEmision(Hora horaEmision) {
		this.horaEmision = horaEmision;
	}
	public String getNombrePelicula() {
		return nombrePelicula;
	}
	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}
	public String getNombreSalaEntrada() {
		return nombreSalaEntrada;
	}
	public void setNombreSalaEntrada(String nombreSalaEntrada) {
		this.nombreSalaEntrada = nombreSalaEntrada;
	}
	public float getPrecioEntrada() {
		return precioEntrada;
	}
	public void setPrecioEntrada(float precioEntrada) {
		this.precioEntrada = precioEntrada;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigoEntrada, fechaEmision, horaEmision, nombrePelicula, nombreSalaEntrada, precioEntrada);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return Objects.equals(codigoEntrada, other.codigoEntrada) && Objects.equals(fechaEmision, other.fechaEmision)
				&& Objects.equals(horaEmision, other.horaEmision)
				&& Objects.equals(nombrePelicula, other.nombrePelicula)
				&& Objects.equals(nombreSalaEntrada, other.nombreSalaEntrada)
				&& Float.floatToIntBits(precioEntrada) == Float.floatToIntBits(other.precioEntrada);
	}
	@Override
	public String toString() {
		return "La entrada de codigo " + codigoEntrada + ", es para el dia" + fechaEmision + ", la hora "
				+ horaEmision + ", del nombre " + nombrePelicula + " y se emitirá en la sala" + nombreSalaEntrada
				+ " al precio " + precioEntrada;
	}
	
}
