package modelo;

import java.util.Objects;

public class Cliente {
	private String dniCliente;
	private String nombreCliente;
	private String apellidosCliente;
	private String contrasenaCliente;
	private char sexoCliente;
	
	public Cliente(String dniCliente, String nombreCliente, String apellidosCliente, String contrasenaCliente,
			char sexoCliente) {
		super();
		this.dniCliente = dniCliente;
		this.nombreCliente = nombreCliente;
		this.apellidosCliente = apellidosCliente;
		this.contrasenaCliente = contrasenaCliente;
		this.sexoCliente = sexoCliente;
	}
	
	@Override
	public String toString() {
		return "El cliente con el DNI " + dniCliente + ", nombre " + nombreCliente + ",  apellidos "
				+ apellidosCliente + ", contrasena " + contrasenaCliente + " y sexo " + sexoCliente;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellidosCliente, contrasenaCliente, dniCliente, nombreCliente, sexoCliente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dniCliente, other.dniCliente);
				
	}
	public String getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidosCliente() {
		return apellidosCliente;
	}
	public void setApellidosCliente(String apellidosCliente) {
		this.apellidosCliente = apellidosCliente;
	}
	public String getContrasenaCliente() {
		return contrasenaCliente;
	}
	public void setContrasenaCliente(String contrasenaCliente) {
		this.contrasenaCliente = contrasenaCliente;
	}
	public char getSexoCliente() {
		return sexoCliente;
	}
	public void setSexoCliente(char sexoCliente) {
		this.sexoCliente = sexoCliente;
	}
	
}
