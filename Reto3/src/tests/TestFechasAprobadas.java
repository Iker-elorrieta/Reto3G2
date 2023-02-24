package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class TestFechasAprobadas {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] cinePrueba=mc.cargarDatos();
		int cineUno=0;
		String peliculaPrueba="Handia";
		String [] fechas=mc.fechasAprobadas(peliculaPrueba, cinePrueba, cineUno);
		assertTrue(fechas.length>=1);
		assertTrue(fechas!=null);
	}
}
