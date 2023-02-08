package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Pelicula;

class TestPelicula {
	Object o=new Object();
	//PELICULA
	String codPeli="Avatar-1",nombrePeli="Avatar",generoPeli="accion";
	int duracion=150;
	String linea3="La pelicula de codigo " + codPeli + ", nombre " + nombrePeli + ", genero "+ generoPeli + ", duracion " + duracion;
	@Test
	void testPelicula() {
		Pelicula p=new Pelicula(nombrePeli,generoPeli,duracion);
		Pelicula p1=new Pelicula("","",0);
		assertFalse(p.equals(p1));
		assertFalse(p.equals(o));
		assertFalse(p.equals(null));
		assertTrue(p.equals(p));
		p1.setGeneroPelicula(generoPeli);
		p1.setDuracionPelicula(duracion);
		p1.setNombrePelicula(nombrePeli);
		assertEquals(p.getDuracionPelicula(),p1.getDuracionPelicula());
		assertEquals(p.getGeneroPelicula(),p1.getGeneroPelicula());
		assertEquals(p.getNombrePelicula(),p1.getNombrePelicula());
		assertEquals(p.toString(),linea3);
		int cod=p.hashCode();
		assertEquals(p.hashCode(),cod);
		assertTrue(p.equals(p1));
	}
}
