package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Pelicula;
import modelo.Sesion;

class TestSesion {
	Object o=new Object();
	Calendar cal=Calendar.getInstance();
	Date fecha=null;
	int hora=9;
	int minutos=30;
	LocalTime tiempo = LocalTime.of(hora, minutos);
	Float precio= (float)7.99;
	Pelicula p=new Pelicula("1","Avatar","Aventura",150);
	String nombreSala="Sala 01";
	int id=1;
	@Test
	void testSesion() {
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		Sesion s=new Sesion(p,id,nombreSala,fecha,tiempo,precio);
		Sesion s1=new Sesion(null,0,null,null,null,0);
		assertTrue(s.equals(s));
		assertFalse(s.equals(s1));
		assertFalse(s.equals(null));
		assertFalse(s.equals(s1));
		s1.setFechaSesion(fecha);
		s1.setHoraSesion(tiempo);
		s1.setPeliSesion(p);
		s1.setPrecio(precio);
		s1.setNombreSala(nombreSala);
		s1.setIdEmision(id);
		assertEquals(s.getFechaSesion(),s1.getFechaSesion());
		assertEquals(s.getHoraSesion(),s1.getHoraSesion());
		assertEquals(s.getPeliSesion(),s1.getPeliSesion());
		assertEquals(s.getPrecio(),s1.getPrecio());
		assertEquals(s.getNombreSala(),s1.getNombreSala());
		assertEquals(s.getIdEmision(),s1.getIdEmision());
		int cod=s.hashCode();
		assertEquals(cod,s.hashCode());
		String linea="La sesion con la pelicula " + p + ", nombre de sala " + nombreSala + ", en la fecha " + fecha
				+ ", a la hora " + tiempo + ", y al precio " + precio+" y el id "+id;
		assertFalse(s.equals(o));
		assertTrue(s.equals(s1));
		assertEquals(s1.toString(),linea);
	}
}
