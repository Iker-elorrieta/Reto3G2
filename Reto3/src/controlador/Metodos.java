package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Cliente;


public class Metodos {
	
	public  Cliente[] cargarClientes() {
		Cliente[] arrayClientes = new Cliente[0];
		Connection conexion;
	
		try {
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCliente = comando.executeQuery("select * from Cliente");
			
			while(cargaCliente.next()) {	
				String dni = cargaCliente.getString("DNI");
				String nombre = cargaCliente.getString("nombreCliente");
				String apellido = cargaCliente.getString("apellidos");
				String pass = cargaCliente.getString("contrasena");
				String user = cargaCliente.getString("usuario");
				Cliente client = new Cliente(dni,nombre,user,apellido,pass);
				System.out.println(client.getContrasenaCliente());
				
				Cliente[] arrayNuevo = new Cliente[arrayClientes.length+1];	
				for(int c = 0;c<arrayClientes.length;c++) {
					arrayNuevo[c]=arrayClientes[c];
				}
				arrayNuevo[arrayClientes.length] = client;
				arrayClientes = arrayNuevo;
			}		
			
		} catch (SQLException ex) {
					ex.printStackTrace();
				}
		return arrayClientes;
	}
}
	
////	public Cine[] cargasCines(Sesion[] sesiones) {
//		Cine[] cine = new Cine[0];
//		return cine;
//		
//	}
//}
