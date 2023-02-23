package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

class TestGenerarFactura3Entradas {
	Metodos mc=new Metodos();
	final static String direccion = "jdbc:mysql://localhost/reto3";
	final static String usuario = "root";
	final static String contra = "";
	/**
	final static String direccion = "jdbc:mysql://10.5.14.210:3306/Cines";
	final static String usuario = "Cliente";
	final static String contra = "Elorrieta00+";
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
			carritoCompraP=mc.guardarSesiones(carritoCompraP, arraySesion, 0);
			tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 3", "prueba", String.valueOf(dt.format(fecha)), String.valueOf(tiempo),"9.99" );
			tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 3", "prueba", String.valueOf(dt.format(fecha)), String.valueOf(tiempo),"9.99" );
			tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 3", "prueba", String.valueOf(dt.format(fecha)), String.valueOf(tiempo),"9.99" );
			float precio=mc.calcularPrecioResumen(tabla);
			Entrada pruebaEntrada=new Entrada(encontrado,carritoCompraP,precio);
			
			mc.generarFactura(pruebaEntrada);
			FileReader fich;
			String datos="";
			BufferedReader linea;
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			File textoUno=new File(".\\Facturas\\"+pruebaEntrada.getCliente().getUser()+dtf.format(fecha)+".txt");
			try {
				fich = new FileReader(textoUno);
				
					linea=new BufferedReader(fich);
					for(int i=0;i<pruebaEntrada.getSesionPorTicket().length;i++) {
								datos+=linea.readLine();
					}	
					linea.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				Statement cogerNombreCine = (Statement) conexion.createStatement();
				String primerCine="",segundoCine="",tercerCine="";
				ResultSet cines=cogerNombreCine.executeQuery("SELECT "+nombreCine+" FROM "+Cine+" JOIN "+Sala+" USING ("+idCine+") JOIN "+Emision+" USING ("+idCine+","+nombreSala+") WHERE "+idEmision+"='"+pruebaEntrada.getSesionPorTicket()[0].getIdEmision()+"'");
				while(cines.next()){
					primerCine=cines.getString(nombreCine);
				}
				cines=cogerNombreCine.executeQuery("SELECT "+nombreCine+" FROM cine JOIN "+Sala+" USING ("+idCine+") JOIN "+Emision+" USING ("+idCine+","+nombreSala+") WHERE "+idEmision+"='"+pruebaEntrada.getSesionPorTicket()[1].getIdEmision()+"'");
				while(cines.next()){
					segundoCine=cines.getString(nombreCine);
				}
				cines=cogerNombreCine.executeQuery("SELECT "+nombreCine+" FROM cine JOIN "+Sala+" USING ("+idCine+") JOIN "+Emision+" USING ("+idCine+","+nombreSala+") WHERE "+idEmision+"='"+pruebaEntrada.getSesionPorTicket()[2].getIdEmision()+"'");
				while(cines.next()){
					tercerCine=cines.getString(nombreCine);
				}
				Calendar cal = Calendar.getInstance();
				fecha=cal.getTime();
				String primeraCompra=dt1.format(fecha)+": En el cine "+primerCine+" ha comprado "+pruebaEntrada.getSesionPorTicket()[0].toString()+dt1.format(fecha)+": En el cine "+segundoCine+" ha comprado "+pruebaEntrada.getSesionPorTicket()[1].toString()+dt1.format(fecha)+": En el cine "+tercerCine+" ha comprado "+pruebaEntrada.getSesionPorTicket()[2].toString();
				assertEquals(datos,primeraCompra);
				comando.executeUpdate("DELETE FROM "+Emision+" WHERE "+idEmision+"='"+arraySesion[0].getIdEmision()+"'");
				comando.executeUpdate("DELETE FROM "+Pelicula+" WHERE "+codPelicula+"='"+codP+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
