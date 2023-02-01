package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Cine;
import modelo.Cliente;
import modelo.DateLabelFormatter;
import modelo.Entrada;
import modelo.Hora;
import modelo.Pelicula;
import modelo.Salas;

class tests {
	Object o=new Object();
	//CLIENTE
	String DNI="12345678A",nombre="pepe",apellidos="garcia sanchez",contrasena="hola12345";
	char sexo='H';
	String linea= "El cliente con el DNI " + DNI + ", nombre " + nombre + ",  apellidos "
			+ apellidos + ", contrasena " + contrasena + " y sexo " + sexo;
	//HORA
	String linea2="09:05";
	int hora=9,minuto=5;
	
	//PELICULA
	String codPeli="Avatar-1",nombrePeli="Avatar",generoPeli="accion";
	int duracion=150,costeLi=30000;
	String linea3="La pelicula de codigo " + codPeli + ", nombre " + nombrePeli + ", genero "
			+ generoPeli + ", duracion " + duracion + " y coste de licencia " + costeLi;
	//ENTRADA
	String codEntrada="YC-S12-A67",nombreSala="S12";
	Date fecha=null;
	float precio=(float) 7.75;
	Calendar cal=Calendar.getInstance();
	//SALAS
	String codSala="YC-Sala-11";
	Pelicula pe=new Pelicula(codPeli,nombrePeli,generoPeli,duracion,costeLi);
	Date [] fechaSala=new Date[1];
	//CINE
	String codCine="YC",nombreCine="Yelmo Cines",direccion="C.C. Megapark Avda. de la Rivera SN Barrio Ibarreta-Zuloko, 48903 Barakaldo, Biscay";
	int numSalas=12;
	@Test
	void testCliente() {
		Cliente c=new Cliente(DNI,nombre,apellidos,contrasena,sexo);
		assertEquals(c.toString(),linea);
		assertEquals(c.getDniCliente(),DNI);
		assertEquals(c.getNombreCliente(),nombre);
		assertEquals(c.getApellidosCliente(),apellidos);
		assertEquals(c.getContrasenaCliente(),contrasena);
		assertEquals(c.getSexoCliente(),sexo);
		Cliente c1=new Cliente("","","","",'1');
		c1.setApellidosCliente(apellidos);
		c1.setContrasenaCliente(contrasena);
		c1.setDniCliente(DNI);
		c1.setNombreCliente(nombre);
		c1.setSexoCliente(sexo);
		assertEquals(c1.toString(),linea);
		assertEquals(c1.getDniCliente(),DNI);
		assertEquals(c1.getNombreCliente(),nombre);
		assertEquals(c1.getApellidosCliente(),apellidos);
		assertEquals(c1.getContrasenaCliente(),contrasena);
		assertEquals(c1.getSexoCliente(),sexo);
		assertEquals(c1,c);
		assertEquals(c1.hashCode(),169745696);
		assertTrue(c1.equals(c));
		assertFalse(c1.equals(null));
		assertFalse(c1.equals(o));
		assertTrue(c1.equals(c1));
	}
	@Test
	void testHora() {
		Hora h=new Hora();
		h.setHoras(hora);
		h.setMinutos(minuto);
		Hora h1=new Hora();
		assertFalse(h.equals(h1));
		h1.setHoras(hora);
		h1.setMinutos(minuto);
		assertEquals(h,h1);
		assertTrue(h.equals(h1));
		assertFalse(h.equals(null));
		assertEquals(h.toString(),linea2);
		assertEquals(h1.getHoras(),hora);
		assertEquals(h1.getMinutos(),minuto);
		assertEquals(h1.hashCode(),1245);
		assertTrue(h1.equals(h));
		assertFalse(h1.equals(o));
		
	}
	@Test
	void testPelicula() {
	Pelicula p=new Pelicula(codPeli,nombrePeli,generoPeli,duracion,costeLi);
	Pelicula p1=new Pelicula("","","",0,0);
	assertFalse(p.equals(p1));
	p1.setCodigoPelicula(codPeli);
	p1.setNombrePelicula(nombrePeli);
	p1.setGeneroPelicula(generoPeli);
	p1.setDuracionPelicula(duracion);
	p1.setCosteLicencia(costeLi);
	assertEquals(p,p1);
	assertEquals(p.getCodigoPelicula(),p1.getCodigoPelicula());
	assertEquals(p.getNombrePelicula(),p1.getNombrePelicula());
	assertEquals(p.getGeneroPelicula(),p1.getGeneroPelicula());
	assertEquals(p.getDuracionPelicula(),p1.getDuracionPelicula());
	assertEquals(p.getCosteLicencia(),p.getCosteLicencia());
	assertTrue(p.equals(p1));
	assertFalse(p.equals(o));
	assertEquals(p.toString(),linea3);
	assertEquals(p.hashCode(),-183160492);
	assertFalse(p.equals(null));
	assertTrue(p.equals(p));
	}
	@Test
	void testEntrada() {
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		Hora h=new Hora();
		h.setHoras(16);
		h.setMinutos(30);
		Entrada e=new Entrada(codEntrada,fecha,h,nombrePeli,nombreSala,precio);
		Entrada e1=new Entrada("",fecha,h,"","",(float) 0.0);
		assertFalse(e.equals(e1));
		assertFalse(e.equals(o));
		assertTrue(e.equals(e));
		e1.setCodigoEntrada(codEntrada);
		e1.setFechaEmision(fecha);
		e1.setHoraEmision(h);
		e1.setNombrePelicula(nombrePeli);
		e1.setNombreSalaEntrada(nombreSala);
		e1.setPrecioEntrada(precio);
		assertEquals(e.getCodigoEntrada(),e1.getCodigoEntrada());
		assertEquals(e.getFechaEmision(),e1.getFechaEmision());
		assertEquals(e.getHoraEmision(),e1.getHoraEmision());
		assertEquals(e.getNombrePelicula(),e1.getNombrePelicula());
		assertEquals(e.getNombreSalaEntrada(),e1.getNombreSalaEntrada());
		assertEquals(e.getPrecioEntrada(),e1.getPrecioEntrada());
		assertFalse(e.equals(null));
		String linea4="La entrada de codigo " + codEntrada + ", es para el dia" + fecha + ", la hora "
				+ h + ", del nombre " + nombrePeli + " y se emitirá en la sala" + nombreSala
				+ " al precio " + precio;
		assertEquals(e.toString(),linea4);
		assertTrue(e.equals(e1));
		int has=e.hashCode();
		assertEquals(e.hashCode(),has);
	}
	@Test
	void testSalas() {
		Pelicula [] arrayP=new Pelicula[1];
		arrayP[0]=pe;
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		Hora h=new Hora();
		h.setHoras(16);
		h.setMinutos(30);
		Hora [] arrayH=new Hora[1];
		arrayH[0]=h;
		Salas s=new Salas(codSala,nombreSala,arrayP,fechaSala,arrayH);
		Salas s1=new Salas("","",null,null,null);
		assertFalse(s.equals(s1));
		assertFalse(s.equals(o));
		assertTrue(s.equals(s));
		String linea5="La sala con codigo " + codSala + ", nombre " + nombreSala + ", peliculas a emitir"
				+ Arrays.toString(arrayP) + ", en las fechas " + Arrays.toString(fechaSala)
				+ " y a las horas" + Arrays.toString(arrayH);
		assertEquals(s.toString(),linea5);
		s1.setCodigoSala(codSala);
		s1.setNombreSala(nombreSala);
		s1.setFechasEmisionSala(fechaSala);
		s1.setHorasEmisionSala(arrayH);
		s1.setPeliculasEmitidas(arrayP);
		assertEquals(s.getCodigoSala(),s1.getCodigoSala());
		assertEquals(s.getNombreSala(),s1.getNombreSala());
		assertEquals(s.getPeliculasEmitidas(),s1.getPeliculasEmitidas());
		assertEquals(s.getFechasEmisionSala(),s1.getFechasEmisionSala());
		assertEquals(s.getHorasEmisionSala(),s1.getHorasEmisionSala());
		assertTrue(s.equals(s1));
		assertFalse(s.equals(null));
		assertEquals(s.hashCode(),-411275093);
	}
	@Test
	void testCine() {
		Pelicula [] arrayP=new Pelicula[1];
		arrayP[0]=pe;
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		Hora h=new Hora();
		h.setHoras(16);
		h.setMinutos(30);
		Hora [] arrayH=new Hora[1];
		arrayH[0]=h;
		Salas s=new Salas(codSala,nombreSala,arrayP,fechaSala,arrayH);
		Salas [] arrayS=new Salas[1];
		arrayS[0]=s;
		Cine c=new Cine(codCine,nombreCine,numSalas,direccion,arrayS);
		Cine c1=new Cine("","",0,"",null);
		assertTrue(c.equals(c));
		assertFalse(c.equals(c1));
		assertFalse(c.equals(o));
		assertFalse(c.equals(null));
		c1.setCodigoCine(codCine);
		c1.setNombreCine(nombreCine);
		c1.setNumeroSalas(numSalas);
		c1.setDireccion(direccion);
		c1.setSalasCine(arrayS);
		assertEquals(c,c1);
		assertTrue(c.equals(c1));
		assertEquals(c.getCodigoCine(),c1.getCodigoCine());
		assertEquals(c.getNombreCine(),c1.getNombreCine());
		assertEquals(c.getNumeroSalas(),c1.getNumeroSalas());
		assertEquals(c.getDireccion(),c1.getDireccion());
		assertEquals(c.getSalasCine(),c1.getSalasCine());
		assertEquals(c.hashCode(),-57039826);
		String linea6="El cine de codigo " + codCine + ", nombre " + nombreCine + ", con " + numSalas
				+ " salas, direccion " + direccion + " y salasCine=" + Arrays.toString(arrayS);
		assertEquals(c.toString(),linea6);
	}
	@Test
	void testDateLabelFormatter(){
		DateLabelFormatter d=new DateLabelFormatter();
		try {
			assertEquals(String.valueOf(d.stringToValue("15-04-2023")),"Sat Apr 15 00:00:00 CEST 2023");
			cal.set(Calendar.DAY_OF_MONTH, 20);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.YEAR, 2023);
			fecha = cal.getTime();
			assertEquals(d.valueToString(cal),"20-01-2023");
			assertEquals(d.valueToString(null),"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
	}
}