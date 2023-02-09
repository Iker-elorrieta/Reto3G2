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
	public  Cliente [] cargarClientes() {
		Cliente [] arrayClientes = new Cliente[0];
		Connection conexion;
	
		try {
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCliente = comando.executeQuery("SELECT * FROM Cliente");
			
			while(cargaCliente.next()) {	
				String dni = cargaCliente.getString("DNI");
				String nombre = cargaCliente.getString("nombreCliente");
				String apellido = cargaCliente.getString("apellidos");
				String pass = cargaCliente.getString("contrasena");
				String user = cargaCliente.getString("usuario");
				Cliente client = new Cliente(dni,nombre,user,apellido,pass);
				
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

public  Pelicula [] cargarPeliculas() {
	Pelicula [] arrayPelicula = new Pelicula[0];
	Connection conexion;

	try {
		conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
		Statement comando = (Statement) conexion.createStatement();
		ResultSet cargaPeliculas= comando.executeQuery("SELECT * FROM Pelicula");
		
		while(cargaPeliculas.next()) {
			String id = cargaPeliculas.getString("idPelicula");
			String cod = cargaPeliculas.getString("nombrePelicula");
			String genero = cargaPeliculas.getString("genero");
			int dura =cargaPeliculas.getInt("duracion");
			Pelicula peli = new Pelicula(id,cod,genero,dura);
			
			Pelicula [] arrayNuevo = new Pelicula[arrayPelicula.length+1];	
			for(int c = 0;c<arrayPelicula.length;c++) {
				arrayNuevo[c]=arrayPelicula[c];
			}
			arrayNuevo[arrayPelicula.length] = peli;
			arrayPelicula = arrayNuevo;
		}		
		
	} catch (SQLException ex) {
				ex.printStackTrace();
			}
	return arrayPelicula;
}
public Sesion [] cargarSesiones(Pelicula [] arrayPeliculas) {
	
	Sesion [] arraySesion = new Sesion[0];
	Connection conexion;

	try {
		conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
		Statement comando = (Statement) conexion.createStatement();
		ResultSet cargaSesiones= comando.executeQuery("SELECT * FROM emision");
		
		while(cargaSesiones.next()) {	
			int CogePeli=0;
			String sala = cargaSesiones.getString("nombreSala");
			Date dia=cargaSesiones.getDate("FechaEmision");
			String hora=cargaSesiones.getString("HoraEmision");
			LocalTime horaF=LocalTime.of(Integer.parseInt(hora.split(":")[0]),Integer.parseInt(hora.split(":")[1]));
			Float precio=cargaSesiones.getFloat("precioInicial");
			String pelicula=cargaSesiones.getString("nombrePelicula");
			for(int i=0;i<arrayPeliculas.length;i++) {
				if(pelicula.equals(arrayPeliculas[i].getNombrePelicula())) {
					CogePeli=i;
				}
			}
			Sesion se = new Sesion(arrayPeliculas[CogePeli],sala,dia,horaF,precio);
			Sesion [] arrayNuevo = new Sesion[arraySesion.length+1];	
			for(int c = 0;c<arraySesion.length;c++) {
				arrayNuevo[c]=arraySesion[c];
			}
			arrayNuevo[arraySesion.length] = se;
			arraySesion = arrayNuevo;
		}		
	} catch (SQLException ex) {
				ex.printStackTrace();
			}
	return arraySesion;
}

public Salas [] cargarSalas(Sesion [] arraySesiones) {
	Salas [] arraySalas = new Salas[0];
	Sesion [] arraySesionSala=new Sesion[0];
	Connection conexion;

	try {
		conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
		Statement comando = (Statement) conexion.createStatement();
		ResultSet cargaSalas= comando.executeQuery("SELECT * FROM sala");
		
		while(cargaSalas.next()) {	
			String codCi=cargaSalas.getString("idCine");
			String sala=cargaSalas.getString("nombreSala");
					ResultSet cargaEmision= comando.executeQuery("SELECT * FROM emision");
					int [] cuentaSesion=new int[0];
					for(int i=0;cargaEmision.next();i++) {
					String cineSesion=cargaEmision.getString("idCine");
					String salaSesion=cargaEmision.getString("nombreSala");
						if(codCi.equals(cineSesion) && sala.equals(salaSesion)) {
							int [] arrayNuevoInt = new int[cuentaSesion.length+1];	
							for(int c = 0;c<arraySalas.length;c++) {
							arrayNuevoInt[c]=cuentaSesion[c];
							}
							arrayNuevoInt[cuentaSesion.length]=i;
							cuentaSesion=arrayNuevoInt;
						}
					}
					Sesion [] arrayAuxSesion=new Sesion[arraySesionSala.length];
					for(int i=0;i<cuentaSesion.length;i++) {
					arrayAuxSesion[i]=arraySesiones[cuentaSesion[i]];
					}
					arraySesionSala=arrayAuxSesion;
					
			Salas salaU = new Salas(codCi,sala,arrayAuxSesion);
			
			Salas [] arrayNuevo = new Salas[arraySalas.length+1];	
			for(int c = 0;c<arraySalas.length;c++) {
				arrayNuevo[c]=arraySalas[c];
			}
			arrayNuevo[arraySalas.length] = salaU;
			arraySalas = arrayNuevo;
			
		}		
	} catch (SQLException ex) {
				ex.printStackTrace();
			}
	return arraySalas;
}

public Cine [] cargarCines(Salas [] arraySalas) {
	Cine [] arrayCine = new Cine[0];
	Connection conexion;

	try {
		conexion = (Connection) DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente", "Elorrieta00+");
		Statement comando = (Statement) conexion.createStatement();
		ResultSet cargaCines= comando.executeQuery("SELECT * FROM cine");
		
		while(cargaCines.next()) {	
//			int CogePeli=0;
//			String idCine=cargaCines.getString("idCine");
//			Salas [] auxSala=new Salas[(arraySalas.length/3)];
//			for(int i=0;i<arraySalas.length;i++) {
//				if(idCine.equals(arraySalas[i])){
//					auxSala[CogePeli]=arraySalas[i];
//					CogePeli++;
//				}
//			}
			
			Cine cineU = new Cine(idCine,auxSala);
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
