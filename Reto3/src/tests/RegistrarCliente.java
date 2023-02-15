package tests;

import static org.junit.Assert.assertEquals;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import controlador.Metodos;
import modelo.Cliente;

class RegistrarCliente {
	
	Metodos mc = new Metodos();
	String direccion = "jdbc:mysql://localhost/reto3";
	String usuario = "root";
	String contra = "";
	
	@Test
	void testRegistroCorrecto() {
		String dni = "20981430F";
		String nombre = "manolo";
		String apellido = "rodriguez";
		String usuariop = "user";
		String contrasena = "contrasena";
		char sexo = 'H';
		mc.registrarCliente(dni, nombre, apellido, usuariop, contrasena, sexo);
		
		Cliente[] clientes = mc.cargarClientes();
		System.out.println(clientes[clientes.length-1].getDniCliente());
		assertEquals(clientes[clientes.length-1].getDniCliente(),dni);
		
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando2 = (Statement) conexion.createStatement();
			comando2.executeUpdate("DELETE FROM cliente WHERE DNI='"+dni+"'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
