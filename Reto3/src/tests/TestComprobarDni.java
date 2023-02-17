package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;

class TestComprobarDni {
	Metodos mc=new Metodos();
	@Test
	void testDniCorrecto() {
		String dni = "20981435F";
		boolean comprobar = mc.comprobarDni(dni);
		assertEquals(true, comprobar);
	}
	
	@Test
	void testDniInCorrecto() {
		String dni = "2098145F";
		boolean comprobar = mc.comprobarDni(dni);
		assertEquals(false, comprobar);
	}
	
	@Test
	void testLetraInCorrecta() {
		String dni = "20981435A";
		boolean comprobar = mc.comprobarDni(dni);
		assertEquals(false, comprobar);
	}

}
