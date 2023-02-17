package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import controlador.Metodos;
import modelo.Cine;
import modelo.Sesion;

class TestCargarArraySesiones {
	Metodos mc =new Metodos();
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
			comando.executeUpdate("insert into pelicula VALUES ('16','prueba','60','comedia')");
			comando.executeUpdate("insert into emision (FechaEmision,HoraEmision,precioInicial,idCine,nombreSala,codPeli) VALUES ('"+dt.format(fecha)+"' ,'"+tiempo+"','"+(float) 9.99+"','GC' ,'Sala 03' ,'16')");
			Cine [] arrayCines=mc.cargarDatos();
			Sesion [] arraySesion=mc.cargarArraySesiones("prueba", arrayCines,0,String.valueOf(dt.format(fecha)));
			assertEquals(arraySesion[0].getPeliSesion().getNombrePelicula(),"prueba");
			assertEquals(arraySesion[0].getHoraSesion(),tiempo);
			assertEquals(arraySesion[0].getNombreSala(),"Sala 03");
			assertEquals(arraySesion[0].getPrecio(),(float)9.99);
			String fecha1=dt.format(fecha);
			assertTrue(String.valueOf(arraySesion[0].getFechaSesion()).equals(fecha1));
			comando.executeUpdate("DELETE FROM emision WHERE idEmision='"+arraySesion[0].getIdEmision()+"'");
			comando.executeUpdate("DELETE FROM pelicula WHERE codPeli='16'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
