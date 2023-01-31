package modelo;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Salas {
	private String codigoSala;
	private String nombreSala;
	private Pelicula [] peliculasEmitidas;
	private Date [] fechasEmisionSala;
	private Hora [] horasEmisionSala;
	
	public Salas(String codigoSala, String nombreSala) {
		super();
		this.codigoSala = codigoSala;
		this.nombreSala = nombreSala;
	}
	
	public String getCodigoSala() {
		return codigoSala;
	}
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	public Pelicula[] getPeliculasEmitidas() {
		return peliculasEmitidas;
	}
	public void setPeliculasEmitidas(Pelicula[] peliculasEmitidas) {
		this.peliculasEmitidas = peliculasEmitidas;
	}
	public Date[] getFechasEmisionSala() {
		return fechasEmisionSala;
	}
	public void setFechasEmisionSala(Date[] fechasEmisionSala) {
		this.fechasEmisionSala = fechasEmisionSala;
	}
	public Hora[] getHorasEmisionSala() {
		return horasEmisionSala;
	}
	public void setHorasEmisionSala(Hora[] horasEmisionSala) {
		this.horasEmisionSala = horasEmisionSala;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fechasEmisionSala);
		result = prime * result + Arrays.hashCode(horasEmisionSala);
		result = prime * result + Arrays.hashCode(peliculasEmitidas);
		result = prime * result + Objects.hash(codigoSala, nombreSala);
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
		Salas other = (Salas) obj;
		return Objects.equals(codigoSala, other.codigoSala) && Arrays.equals(fechasEmisionSala, other.fechasEmisionSala)
				&& Arrays.equals(horasEmisionSala, other.horasEmisionSala)
				&& Objects.equals(nombreSala, other.nombreSala)
				&& Arrays.equals(peliculasEmitidas, other.peliculasEmitidas);
	}
}
