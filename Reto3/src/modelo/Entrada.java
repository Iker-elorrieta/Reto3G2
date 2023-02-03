package modelo;

import java.util.Arrays;

public class Entrada {
	private String [] nombreSalaEntrada;
	private Sesion [] sesionPorTicket;
	
	
	
	public Entrada(String[] nombreSalaEntrada, Sesion[] sesionPorTicket) {
		super();
		this.nombreSalaEntrada = nombreSalaEntrada;
		this.sesionPorTicket = sesionPorTicket;
	}
	public String[] getNombreSalaEntrada() {
		return nombreSalaEntrada;
	}
	public void setNombreSalaEntrada(String[] nombreSalaEntrada) {
		this.nombreSalaEntrada = nombreSalaEntrada;
	}
	public Sesion[] getSesionPorTicket() {
		return sesionPorTicket;
	}
	public void setSesionPorTicket(Sesion[] sesionPorTicket) {
		this.sesionPorTicket = sesionPorTicket;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(nombreSalaEntrada);
		result = prime * result + Arrays.hashCode(sesionPorTicket);
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
		Entrada other = (Entrada) obj;
		return Arrays.equals(nombreSalaEntrada, other.nombreSalaEntrada)
				&& Arrays.equals(sesionPorTicket, other.sesionPorTicket);
	}
	@Override
	public String toString() {
		return "El ticket con los nombres de sala "+ Arrays.toString(nombreSalaEntrada) + " y sesiones " + Arrays.toString(sesionPorTicket);
	}
	
}
