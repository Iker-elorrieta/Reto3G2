package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;


import controlador.Metodos;
import modelo.Cine;
import modelo.Sesion;

class TestMostrarSesiones {
	Metodos mc =new Metodos();
	
	String fechaEmision="FechaEmision",horaEmision="HoraEmision",precioInicial="precioInicial",idCine="idCine",
			nombreSala="nombreSala",codPelicula="codPelicula",horaCompra="horaCompra",precioTotal="precioTotal",
			idEmision="idEmision",nombreCine="nombreCine";
	String Pelicula="Pelicula",Emision="Emision",Compra="Compra",Entrada="Entrada",Cine="Cine",Sala="Sala";
	int codP=16,duracion=60;
	String nombreP="prueba",genero="comedia";
	
	/**
	final static String direccion = "jdbc:mysql://10.5.14.210:3306/Cines";
	final static String usuario = "Cliente";
	final static String contra = "Elorrieta00+";
	**/
	final static String direccion = "jdbc:mysql://localhost/reto3";
	final static String usuario = "root";
	final static String contra = "";
	Calendar cal = Calendar.getInstance();
	Date fecha = null;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	int hora=9;
	int minutos=30;
	LocalTime tiempo = LocalTime.of(hora, minutos);
	@Test
	void test() {
		fecha=cal.getTime();
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			comando.executeUpdate("INSERT INTO "+Pelicula+" VALUES ('"+codP+"','"+nombreP+"','"+duracion+"','"+genero+"')");
			comando.executeUpdate("INSERT INTO "+Emision+" ("+fechaEmision+","+horaEmision+","+precioInicial+","+idCine+","+nombreSala+","+codPelicula+") VALUES ('"+dt.format(fecha)+"' ,'"+tiempo+"','"+(float) 9.99+"','GC' ,'Sala 3' ,'"+codP+"')");
			Cine [] arrayCines=mc.cargarDatos();
			Sesion [] arraySesion=mc.cargarArraySesiones(nombreP, arrayCines,0,String.valueOf(dt.format(fecha)));
			String [] mostrarSesion=mc.mostrarSesiones(arraySesion);
			assertEquals(mostrarSesion[0],"09:30 - Sala 3 - 9.99");
			comando.executeUpdate("DELETE FROM "+Emision+" WHERE "+idEmision+"='"+arraySesion[0].getIdEmision()+"'");
			comando.executeUpdate("DELETE FROM "+Pelicula+" WHERE "+codPelicula+"='"+codP+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
