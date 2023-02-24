package tests;

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
		Pelicula [] pelis1=mc.arrayPeliculas(arrayCine, 1);
		String [] pelisMostrar1=mc.cogerNombrePeliculas(pelis1);
		assertTrue(pelisMostrar!=pelisMostrar1);
	}

}
