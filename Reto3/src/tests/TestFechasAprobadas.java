package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;
import modelo.Pelicula;

class TestFechasAprobadas {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] cinePrueba=mc.cargarDatos();
		Pelicula [] peli=mc.arrayPeliculas(cinePrueba, 0);
		String [] pelis=mc.cogerNombrePeliculas(peli);
		int cineUno=0;
		String peliculaPrueba="";
		for(int i=0;i<pelis.length;i++) {
			String [] fechas=mc.fechasAprobadas(pelis[i], cinePrueba, cineUno);
			if(fechas.length>1) {
				peliculaPrueba=pelis[i];
				i=pelis.length;
			}
		}
		String [] fechas=mc.fechasAprobadas(peliculaPrueba, cinePrueba, cineUno);
		assertTrue(fechas.length>=1);
		assertTrue(fechas!=null);
	}
}
