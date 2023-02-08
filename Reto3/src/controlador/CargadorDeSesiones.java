package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class CargadorDeSesiones {
	final int DIAS=50;
	public void InsertarSesiones() {
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cines","root","");
			Statement comando=(Statement) conexion.createStatement();
			ResultSet peliculas = comando.executeQuery("Select * from pelicula");
			String peli="",sala="",cine="";
			while(peliculas.next()) {
			peli+=peliculas.getString("nombrePelicula")+"#";
			}
			String [] arrayP=peli.split("#");
			ResultSet salas = comando.executeQuery("Select * from sala");
			while(salas.next()) {
				sala+=salas.getString("nombreSala")+"#";
				cine+=salas.getString("idCine")+"#";
			}
			String [] arrayS=sala.split("#");
			String [] arrayC=cine.split("#");
			LocalTime [] horas= new LocalTime[4];
			horas[0]=LocalTime.of(00, 00);
			horas[1]=LocalTime.of(15, 00);
			horas[2]=LocalTime.of(18, 00);
			horas[3]=LocalTime.of(21, 00);
			Float [] precios=new Float[4];
			precios[0]=(float) 4.99;
			precios[1]=(float) 7.99;
			precios[2]=(float) 10.99;
			precios[3]=(float) 14.99;
			Calendar cal=Calendar.getInstance();
			Date fecha=null;
			SimpleDateFormat dt=new SimpleDateFormat("yyyy-MM-dd");
			File file = new File("logEmisiones");
			BufferedWriter fichero;

				try {
					fichero = new BufferedWriter(new FileWriter(file));
				
		for(int i=0;i<DIAS;i++) {
			cal.add(Calendar.DAY_OF_YEAR,+1);
			fecha=cal.getTime();
				for(int s=0;s<arrayS.length;s++) {
					for(int h=0;h<horas.length;h++) {
						int peliAleatoria=(int) (Math.random()*arrayP.length);
						int precioAleatorio=(int) (Math.random()*precios.length);
		comando.executeUpdate("INSERT INTO emision (FechaEmision,HoraEmision,precioInicial,idCine,nombreSala,nombrePelicula) "
		+ "VALUES ('"+dt.format(fecha)+"','"+horas[h]+"','"+precios[precioAleatorio]+"','"+arrayC[s]+"','"+arrayS[s]+"','"+arrayP[peliAleatoria]+"')");
		fichero.write("INSERT INTO emision (FechaEmision,HoraEmision,precioInicial,idCine,nombreSala,nombrePelicula) "
				+ "VALUES ('"+dt.format(fecha)+"','"+horas[h]+"','"+precios[precioAleatorio]+"','"+arrayC[s]+"','"+arrayS[s]+"','"+arrayP[peliAleatoria]+"')");
		fichero.newLine();
					}	
			}
		}
		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
