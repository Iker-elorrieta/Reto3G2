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

import modelo.Cine;

public class Metodos {
	public void cargarCliente() {
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
