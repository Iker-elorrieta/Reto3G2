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
			if("Dracula".equals(pelis[i])) {
				encontrar=true;
			}
		}
		assertEquals(pelis.length,15);
		assertTrue(encontrar);
	}

}
