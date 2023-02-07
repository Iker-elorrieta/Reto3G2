package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Cine;
import modelo.Cliente;
import modelo.Mensaje;

public class Metodos {
	
	public void Cliente[] cargarCines() {
		Cliente[] arrayClientes = new Cliente[0];
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCliente = comando.executeQuery("select * from Cliente");
			
			for (int i = 0; i < arrayClientes.length; i++) {
				arrayNuevo[i] = arrayClientes[i];
			}
			arrayNuevo[arrayClientes.length] = client;
			arrayClientes = arrayNuevo;
			return arrayClientes;
			
			while (cargaCliente.next()) {
				Cliente[] arrayNuevo = new Cliente[arrayClientes.length + 1];
				String dni = cargaCliente.getString("DNI");
				String nombre = cargaCliente.getString("nombreCliente");
				String apellido = cargaCliente.getString("apellidos");
				String pass = cargaCliente.getString("password");
			
				Cliente client = new Cliente(dni,nombre,apellido,pass);
				System.out.println(client.getContrasenaCliente());
			}
			
			
			
//			
//			ResultSet cargaSalas = comando.executeQuery("select * from salas");
//			while (cargaCines.next()) {
//				
//			}
//			
//			ResultSet cargaPeliculas = comando.executeQuery("select * from cines");
//			while (cargaCines.next()) {
//				
//			}
//			
//			ResultSet carga = comando.executeQuery("select * from cines");
//			while (cargaCines.next()) {
//				
//			}
		} catch (SQLException ex) {
					ex.printStackTrace();
				}
	}
}
