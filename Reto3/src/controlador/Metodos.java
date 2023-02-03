package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Cine;

public class Metodos {
	
	public void InsertarSesiones() {
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cine","root","");
			Statement comando=(Statement) conexion.createStatement();
			ResultSet registro = comando.executeQuery("Select");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void SeleccionarCines() {
		Cine [] cine=null;
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cine","root","");
			Statement comando=(Statement) conexion.createStatement();
			ResultSet registro = comando.executeQuery("Select * FROM cine");
			for(int i=0;registro.next();i++) {
				Cine cineU=null;
				cineU.setNombreCine(registro.getString(1));
				cineU.setSalasCine(null);
				cine[i]=cineU;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
