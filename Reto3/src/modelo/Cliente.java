package modelo;

import java.util.Objects;

public class Cliente {
		private String dniCliente;
		private String nombreCliente;
		private String user;
		private String apellidosCliente;
		private String contrasenaCliente;
		private String sexo;

		
		public Cliente(String dniCliente, String nombreCliente,String user,String apellidosCliente,String sexo, String contrasenaCliente) {
			this.dniCliente = dniCliente;
			this.nombreCliente = nombreCliente;
			this.user = user;
			this.apellidosCliente = apellidosCliente;
			this.sexo = sexo;
			this.contrasenaCliente = contrasenaCliente;
		
		}
		
		public String getSexo() {
			return sexo;
		}

		public void setSexo(String sexo) {
			this.sexo = sexo;
		}

		@Override
		public String toString() {
			return "El cliente con el DNI " + dniCliente + ", nombre " + nombreCliente + ",  apellidos "
					+ apellidosCliente + ", contrasena " + contrasenaCliente + " y sexo ... tiene el user: " +user;
		}
		@Override
		public int hashCode() {
			return Objects.hash(apellidosCliente, contrasenaCliente, dniCliente, nombreCliente);
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
		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
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
}
