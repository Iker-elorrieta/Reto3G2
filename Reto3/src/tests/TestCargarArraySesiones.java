package tests;

import static org.junit.Assert.assertTrue;
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

class TestCargarArraySesiones {
	Metodos mc =new Metodos();
	
	final static String direccion = "jdbc:mysql://10.5.14.210:3306/Cines";
	final static String usuario = "usuario";
	final static String contra = "Elorrieta00+";
	/**
	final static String direccion = "jdbc:mysql://localhost/reto3";
	final static String usuario = "root";
	final static String contra = "";
	**/
	
	String fechaEmision="FechaEmision",horaEmision="HoraEmision",precioInicial="precioInicial",idCine="idCine",
			nombreSala="nombreSala",codPelicula="codPelicula",horaCompra="horaCompra",precioTotal="precioTotal",
			idEmision="idEmision",nombreCine="nombreCine";
	String Pelicula="Pelicula",Emision="Emision",Compra="Compra",Entrada="Entrada",Cine="Cine",Sala="Sala";
	int codP=16,duracion=60;
	String nombreP="prueba",genero="comedia";
	
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
			Sesion [] arraySesion=new Sesion[0];
			arraySesion=mc.cargarArraySesiones(nombreP, arrayCines,0,String.valueOf(dt.format(fecha)));
			assertEquals(arraySesion[0].getPeliSesion().getNombrePelicula(),nombreP);
			assertEquals(arraySesion[0].getHoraSesion(),tiempo);
			assertEquals(arraySesion[0].getNombreSala(),"Sala 3");
			assertEquals(arraySesion[0].getPrecio(),(float)9.99);
			String fecha1=dt.format(fecha);
			assertTrue(String.valueOf(arraySesion[0].getFechaSesion()).equals(fecha1));
			comando.executeUpdate("DELETE FROM "+Emision+" WHERE "+idEmision+"='"+arraySesion[0].getIdEmision()+"'");
			comando.executeUpdate("DELETE FROM "+Pelicula+" WHERE "+codPelicula+"='"+codP+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
