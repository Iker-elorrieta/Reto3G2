package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;

class TestEntrada {
	Object o=new Object();
	//ENTRADA
	String codEntrada="YC-S12-A67",nombreSala="S12";
	Date fecha=null;
	Calendar cal=Calendar.getInstance();
	int hora=9;
	int minutos=30;
	LocalTime tiempo = LocalTime.of(hora, minutos);
	Float precio= (float)7.99;
	Pelicula p=new Pelicula("p1","Avatar","Aventura",150);
	@Test
	void testEntradas() {
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		String [] arrayString=new String [1];
		arrayString[0]="Sala1";
		Sesion s=new Sesion(p,fecha,tiempo,precio);
		Sesion [] arrayS=new Sesion[1];
		arrayS[0]=s;
		Entrada e=new Entrada(arrayString,arrayS);
		Entrada e1=new Entrada(null,null);
		assertFalse(e.equals(e1));
		assertFalse(e.equals(o));
		assertTrue(e.equals(e));
		assertFalse(e.equals(null));
		e1.setNombreSalaEntrada(arrayString);
		e1.setSesionPorTicket(arrayS);
		assertEquals(e.getNombreSalaEntrada(),e.getNombreSalaEntrada());
		assertEquals(e.getSesionPorTicket(),e.getSesionPorTicket());
		assertTrue(e.equals(e1));
		int has=e.hashCode();
		assertEquals(e.hashCode(),has);
		String linea="El ticket con los nombres de sala "+ Arrays.toString(arrayString) + " y sesiones " + Arrays.toString(arrayS);
		assertEquals(e.toString(),linea);
	}

}
