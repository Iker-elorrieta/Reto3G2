package modelo;

import java.util.Arrays;
import java.util.Objects;

public class Salas {
	private String codigoSala;
	private String nombreSala;
	private Sesion [] sesionesPorSala;
	
	public Salas(String codigoSala, String nombreSala, Sesion[] sesionesPorSala) {
		this.codigoSala = codigoSala;
		this.nombreSala = nombreSala;
		this.sesionesPorSala = sesionesPorSala;
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
	public Sesion[] getSesionesPorSala() {
		return sesionesPorSala;
	}
	public void setSesionesPorSala(Sesion[] sesionesPorSala) {
		this.sesionesPorSala = sesionesPorSala;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(sesionesPorSala);
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
		return Objects.equals(codigoSala, other.codigoSala) && Objects.equals(nombreSala, other.nombreSala)
				&& Arrays.equals(sesionesPorSala, other.sesionesPorSala);
	}
	@Override
	public String toString() {
		return "La sala de codigo " + codigoSala + ", nombre " + nombreSala + "y con las sesiones "	+ Arrays.toString(sesionesPorSala);
	}


}
