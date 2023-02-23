package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;
import modelo.Pelicula;

class TestCogerNombrePelicula {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] arrayCine;
		arrayCine=mc.cargarDatos();
		Pelicula [] pelis=mc.arrayPeliculas(arrayCine, 0);
		String [] pelisMostrar=null;
		assertTrue(pelisMostrar==null);
		pelisMostrar=mc.cogerNombrePeliculas(pelis);
		assertTrue(pelisMostrar!=null);
		assertFalse(pelisMostrar==null);
	}

}
