package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class TestMostrarPeliculas {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] arrayCine;
		arrayCine=mc.cargarDatos();
		boolean encontrar=false;
		String [] pelis=mc.mostrarPeliculas(arrayCine, 0);
		for(int i=0;i<pelis.length;i++) {
			if(pelis[i]!=null) {
				encontrar=true;
			}
		}
		assertTrue(pelis.length>=1);
		assertFalse(pelis.length<1);
		assertTrue(encontrar);
	}

}
