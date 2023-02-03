package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Pelicula;
import modelo.Salas;
import modelo.Sesion;

class TestSalas {
	Object o=new Object();
	//SALAS
	String codSala="YC-Sala-11",nombreSala="Sala01";
	Calendar cal=Calendar.getInstance();
	Date fecha=null;
	int hora=9;
	int minutos=30;
	LocalTime tiempo = LocalTime.of(hora, minutos);
	Float precio= (float)7.99;
	Pelicula p=new Pelicula("p1","Avatar","Aventura",150);
	@Test
	void testSala() {
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		Sesion s=new Sesion(p,fecha,tiempo,precio);
		Sesion [] arrayS=new Sesion[1];
		arrayS[0]=s;
		Salas sala=new Salas(codSala,nombreSala,arrayS);
		Salas s1=new Salas("","",null);
		assertFalse(sala.equals(s1));
		assertFalse(sala.equals(o));
		assertTrue(sala.equals(sala));
		String linea5="La sala de codigo " + codSala + ", nombre " + nombreSala + "y con las sesiones "	+ Arrays.toString(arrayS);
		assertEquals(sala.toString(),linea5);
		s1.setCodigoSala(codSala);
		s1.setNombreSala(nombreSala);
		s1.setSesionesPorSala(arrayS);
		assertEquals(sala.getCodigoSala(),s1.getCodigoSala());
		assertEquals(sala.getSesionesPorSala(),s1.getSesionesPorSala());
		assertEquals(sala.getNombreSala(),s1.getNombreSala());
		assertTrue(sala.equals(s1));
		assertFalse(sala.equals(null));
		int cod=sala.hashCode();
		assertEquals(sala.hashCode(),cod);
	}

}
