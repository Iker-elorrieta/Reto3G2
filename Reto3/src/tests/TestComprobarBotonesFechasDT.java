package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class TestComprobarBotonesFechasDT {

	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] cinePrueba=mc.cargarDatos();
		int cineUno=0;
		String peliculaPrueba="Handia";
		String [] fechas=mc.fechasAprobadas(peliculaPrueba, cinePrueba, cineUno);
		boolean correcta=mc.comprobarBotoneFechasDT(fechas, "2023-02-28");
		assertTrue(correcta);
		correcta=mc.comprobarBotoneFechasDT(fechas, "2022-02-28");
		assertFalse(correcta);
	}
}
