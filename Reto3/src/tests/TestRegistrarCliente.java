package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;


import controlador.Metodos;
import modelo.Cliente;

class TestRegistrarCliente {
	
	Metodos mc = new Metodos();
	String direccion = "jdbc:mysql://localhost/reto3";
	String usuario = "root";
	String contra = "";
	
	@Test
	void testRegistroCorrecto() {
		String dni = "99999999Z";
		String nombre = "manolo";
		String apellido = "rodriguez";
		String usuariop = "user";
		String contrasena = "contrasena";
		char sexo = 'H';
		mc.registrarCliente(dni, nombre, apellido, usuariop, contrasena, sexo);
		
		Cliente[] clientes = mc.cargarClientes();
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
