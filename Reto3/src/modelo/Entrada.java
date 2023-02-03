package modelo;

import java.util.Arrays;
import java.util.Objects;

public class Entrada {
	
	private Cliente cliente;
	private Sesion [] sesionPorTicket;
	
	public Entrada(Cliente cliente, Sesion[] sesionPorTicket) {
		this.cliente = cliente;
		this.sesionPorTicket = sesionPorTicket;
	}
	public Sesion[] getSesionPorTicket() {
		return sesionPorTicket;
	}
	public void setSesionPorTicket(Sesion[] sesionPorTicket) {
		this.sesionPorTicket = sesionPorTicket;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(sesionPorTicket);
		result = prime * result + Objects.hash(cliente);
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
		return Objects.equals(cliente, other.cliente) && Arrays.equals(sesionPorTicket, other.sesionPorTicket);
	}
	@Override
	public String toString() {
		return "La entrada  del cliente " + cliente + " y de las sesiones " + Arrays.toString(sesionPorTicket);
	}
	
}
