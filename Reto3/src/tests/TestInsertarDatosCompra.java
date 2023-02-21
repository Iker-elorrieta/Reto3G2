package tests;


import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;


import controlador.Metodos;
import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Sesion;

class TestInsertarDatosCompra {

	Metodos mc =new Metodos();
	/**
	final static String direccion = "jdbc:mysql://10.5.14.210:3306/Cines";
	final static String usuario = "Cliente";
	final static String contra = "Elorrieta00+";
	**/
	final static String direccion = "jdbc:mysql://localhost/reto3";
	final static String usuario = "root";
	final static String contra = "";
	
	String fechaEmision="FechaEmision",horaEmision="HoraEmision",precioInicial="precioInicial",idCine="idCine",
			nombreSala="nombreSala",codPelicula="codPelicula",horaCompra="horaCompra",precioTotal="precioTotal",
			idEmision="idEmision";
	String Pelicula="Pelicula",Emision="Emision",Compra="Compra",Entrada="Entrada";
	int codP=16,duracion=60;
	String nombreP="prueba",genero="comedia";
	Calendar cal = Calendar.getInstance();
	Date fecha = null;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	int hora=9;
	int minutos=30;
	LocalTime tiempo = LocalTime.of(hora, minutos);
	SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Test
	void test() {
		fecha=cal.getTime();
		Sesion [] carritoCompraP = new Sesion [0];
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			comando.executeUpdate("INSERT INTO "+Pelicula+" VALUES ('"+codP+"','"+nombreP+"','"+duracion+"','"+genero+"')");
			comando.executeUpdate("INSERT INTO "+Emision+" ("+fechaEmision+","+horaEmision+","+precioInicial+","+idCine+","+nombreSala+","+codPelicula+") VALUES ('"+dt.format(fecha)+"' ,'"+tiempo+"','"+(float) 9.99+"','GC' ,'Sala 3' ,'16')");
			Cine [] arrayCines=mc.cargarDatos();
			Sesion [] arraySesion=mc.cargarArraySesiones(nombreP, arrayCines,0,String.valueOf(dt.format(fecha)));
			Cliente [] arrayC=mc.cargarClientes();
			Cliente encontrado=mc.encontrarCliente(arrayC, "elorrieta1", "elorrieta00");
			String [][] tabla=new String[0][6];
			carritoCompraP=mc.guardarSesiones(carritoCompraP, arraySesion, 0);
			carritoCompraP=mc.guardarSesiones(carritoCompraP, arraySesion, 0);
			tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 3", "prueba", String.valueOf(dt.format(fecha)), String.valueOf(tiempo),"9.99" );
			tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 3", "prueba", String.valueOf(dt.format(fecha)), String.valueOf(tiempo),"9.99" );
			float precio=mc.calcularPrecioTotal(tabla);
			Entrada pruebaEntrada=new Entrada(encontrado,carritoCompraP,precio);
			mc.insertarDatosCompra(pruebaEntrada);
			ResultSet testCompra=comando.executeQuery("SELECT * FROM "+Compra+" WHERE "+horaCompra+"='"+dt1.format(fecha)+"'");	
			String fecha1=dt1.format(fecha)+".0";
			while(testCompra.next()) {
			assertTrue(testCompra.getFloat(precioTotal)==precio);	
			assertTrue(testCompra.getString(horaCompra).equals(fecha1));
			}
			for(int i=0;i<arraySesion.length;i++) {
				comando.executeUpdate("DELETE FROM "+Entrada+" WHERE "+idEmision+"='"+arraySesion[i].getIdEmision()+"'");	
			}
			comando.executeUpdate("DELETE FROM "+Compra+" WHERE "+horaCompra+"='"+dt1.format(fecha)+"'");
			comando.executeUpdate("DELETE FROM "+Emision+" WHERE "+idEmision+"='"+arraySesion[0].getIdEmision()+"'");
			comando.executeUpdate("DELETE FROM "+Pelicula+" WHERE "+codPelicula+"='"+codP+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
