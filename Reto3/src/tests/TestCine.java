package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Cine;
import modelo.Pelicula;
import modelo.Salas;
import modelo.Sesion;

class TestCine {
	Object o=new Object();
	String codSala="YC-Sala-11",nombreSala="Sala01",nombreCine="Yelmo Cines";
	Calendar cal=Calendar.getInstance();
	Date fecha=null;
	int hora=9;
	int minutos=30;
	LocalTime tiempo = LocalTime.of(hora, minutos);
	Float precio= (float)7.99;
	Pelicula p=new Pelicula("p1","Avatar","Aventura",150);
	@Test
	void test() {
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		Sesion s=new Sesion(p,nombreSala,fecha,tiempo,precio);
		Sesion [] arrayS=new Sesion[1];
		arrayS[0]=s;
		Salas sala=new Salas(codSala,nombreSala,arrayS);
		Salas [] arraySala=new Salas[1];
		Salas [] arraySala1=new Salas[1];
		arraySala[0]=sala;
		Cine c=new Cine(nombreCine,arraySala);
		Cine c1=new Cine("",arraySala1);
		assertTrue(c.equals(c));
		assertFalse(c.equals(null));
		assertFalse(c.equals(o));
		assertFalse(c.equals(c1));
		c1.setNombreCine(nombreCine);
		c1.setSalasCine(arraySala);
		assertEquals(c.getNombreCine(),c1.getNombreCine());
		assertEquals(c.getSalasCine(),c1.getSalasCine());
		assertTrue(c.equals(c1));
		int cod=c.hashCode();
		assertEquals(c.hashCode(),cod);
		String linea="El cine de nombre " + nombreCine + " y con las salas " + Arrays.toString(arraySala);
		assertEquals(c.toString(),linea);
	}

}
