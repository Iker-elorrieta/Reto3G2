package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Cine;
import modelo.Cliente;
import modelo.Pelicula;
import modelo.Salas;
import modelo.Sesion;


public class Metodos {
	//conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
	String direccion="jdbc:mysql://localhost/cines";
	String usuario="root";
	String contra="";
	public  Cliente [] cargarClientes() {
		Cliente [] arrayClientes = new Cliente[0];
		Connection conexion;
	
		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario,contra);
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCliente = comando.executeQuery("SELECT * FROM Cliente");
			
			while(cargaCliente.next()) {	
				String dni = cargaCliente.getString("DNI");
				String nombre = cargaCliente.getString("nombreCliente");
				String apellido = cargaCliente.getString("apellidos");
				String pass = cargaCliente.getString("contrasena");
				String user = cargaCliente.getString("usuario");
				String sexo=cargaCliente.getString("sexo");
				Cliente client = new Cliente(dni,nombre,user,apellido,sexo,pass);
				
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
	public  Cine [] cargarDatos() {
		Cine [] arrayCine = new Cine[0];
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario,contra);
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCines= comando.executeQuery("SELECT * FROM cine");
			while(cargaCines.next()) {	
				String nombreCine=cargaCines.getString("nombreCine");
				String idCine=cargaCines.getString("idCine");
				Statement comandoUno = (Statement) conexion.createStatement();
				ResultSet cargaSala= comandoUno.executeQuery("SELECT * FROM sala WHERE idCine='"+idCine+"'");
				Salas [] arraySalas = new Salas[0];
				while(cargaSala.next()) {
					String codCi=cargaSala.getString("idCine");
					String sala=cargaSala.getString("nombreSala");
					Statement comandoDos = (Statement) conexion.createStatement();
					ResultSet cargaSesiones= comandoDos.executeQuery("SELECT * FROM emision WHERE idCine='"+codCi+"' AND nombreSala='"+sala+"'");
					Sesion [] arraySesion = new Sesion[0];
					while(cargaSesiones.next()) {
						String salaSesion = cargaSesiones.getString("nombreSala");
						Date dia=cargaSesiones.getDate("FechaEmision");
						String hora=cargaSesiones.getString("HoraEmision");
						LocalTime horaF=LocalTime.of(Integer.parseInt(hora.split(":")[0]),Integer.parseInt(hora.split(":")[1]));
						Float precio=cargaSesiones.getFloat("precioInicial");
						String pelicula=cargaSesiones.getString("nombrePelicula");
						Statement comandoTres = (Statement) conexion.createStatement();
						ResultSet cargaPeliculas= comandoTres.executeQuery("SELECT * FROM pelicula WHERE nombrePelicula='"+pelicula+"'");
						Pelicula peli=null;
						while(cargaPeliculas.next()) {
							String id = cargaPeliculas.getString("nombrePelicula");
							String cod = cargaPeliculas.getString("nombrePelicula");
							String genero = cargaPeliculas.getString("genero");
							int dura =cargaPeliculas.getInt("duracion");
							peli = new Pelicula(id,cod,genero,dura);
						}
						
						Sesion se = new Sesion(peli,salaSesion,dia,horaF,precio);
						Sesion [] arrayNuevo = new Sesion[arraySesion.length+1];	
						for(int c = 0;c<arraySesion.length;c++) {
							arrayNuevo[c]=arraySesion[c];
						}
						arrayNuevo[arraySesion.length] = se;
						arraySesion = arrayNuevo;
					}
					
					Salas salaU = new Salas(codCi,sala,arraySesion);
					Salas [] arrayNuevo = new Salas[arraySalas.length+1];	
					for(int c = 0;c<arraySalas.length;c++) {
						arrayNuevo[c]=arraySalas[c];
					}
					arrayNuevo[arraySalas.length] = salaU;
					arraySalas = arrayNuevo;
				}
				
				Cine cineU = new Cine(nombreCine,arraySalas);
				Cine [] arrayNuevo = new Cine[arrayCine.length+1];	
				for(int c = 0;c<arrayCine.length;c++) {
					arrayNuevo[c]=arrayCine[c];
				}
				arrayNuevo[arrayCine.length] = cineU;
				arrayCine = arrayNuevo;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return arrayCine;
	}
}
