package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import modelo.Cliente;
import modelo.Entrada;
import modelo.Sesion;

class TestGenerarFactura {

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
	SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Test
	void test() {
		fecha=cal.getTime();
		Sesion [] carritoCompraP = new Sesion [0];
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			comando.executeUpdate("insert into pelicula VALUES ('16','prueba','60','comedia')");
			comando.executeUpdate("insert into emision (FechaEmision,HoraEmision,precioInicial,idCine,nombreSala,codPeli) VALUES ('"+dt.format(fecha)+"' ,'"+tiempo+"','"+(float) 9.99+"','GC' ,'Sala 03' ,'16')");
			Cine [] arrayCines=mc.cargarDatos();
			Sesion [] arraySesion=mc.cargarArraySesiones("prueba", arrayCines,0,String.valueOf(dt.format(fecha)));
			Cliente [] arrayC=mc.cargarClientes();
			Cliente encontrado=mc.encontrarCliente(arrayC, "elorrieta1", "elorrieta00");
			String [][] tabla=new String[0][6];
			carritoCompraP=mc.guardarSesiones(carritoCompraP, arraySesion, 0);
			carritoCompraP=mc.guardarSesiones(carritoCompraP, arraySesion, 0);
			tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 03", "prueba", String.valueOf(dt.format(fecha)), String.valueOf(tiempo),"9.99" );
			tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 03", "prueba", String.valueOf(dt.format(fecha)), String.valueOf(tiempo),"9.99" );
			float precio=mc.calcularPrecioTotal(tabla);
			Entrada pruebaEntrada=new Entrada(encontrado,carritoCompraP,precio);
			mc.generarFactura(pruebaEntrada);
			FileReader fich;
			try {
				File elorrieta1=new File(".\\Facturas\\"+pruebaEntrada.getCliente().getDniCliente()+".txt");
				fich = new FileReader(elorrieta1);
				BufferedReader linea=new BufferedReader(fich);
				String datos="";
				for(int i=0;i<pruebaEntrada.getSesionPorTicket().length;i++) {
					try {
						datos+=linea.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				assertEquals(datos,pruebaEntrada.getSesionPorTicket()[0].toString()+pruebaEntrada.getSesionPorTicket()[1].toString());
				FileWriter borrar;
				try {
					borrar = new FileWriter(elorrieta1);
					BufferedWriter borrador=new BufferedWriter(borrar);
				for(int i=0;i<pruebaEntrada.getSesionPorTicket().length;i++) {
					borrador.write("");
				}
				borrador.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					linea.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			comando.executeUpdate("DELETE FROM emision WHERE idEmision='"+arraySesion[0].getIdEmision()+"'");
			comando.executeUpdate("DELETE FROM pelicula WHERE codPeli='16'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
