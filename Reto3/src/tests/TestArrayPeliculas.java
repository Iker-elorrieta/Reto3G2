package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;
import modelo.Pelicula;

class TestArrayPeliculas {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] arrayCine;
		arrayCine=mc.cargarDatos();
		boolean encontrar=false;
		Pelicula [] pelis=mc.arrayPeliculas(arrayCine, 0);
		for(int i=0;i<pelis.length;i++) {
			if(pelis[i]!=null) {
				encontrar=true;
			}
		}
		assertTrue(pelis.length>=1);
		assertFalse(pelis.length<1);
		assertTrue(encontrar);
		assertTrue(pelis!=null);
	}

}
