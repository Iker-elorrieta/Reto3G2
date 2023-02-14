package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class TestMostrarSesiones {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] arrayCine;
		arrayCine=mc.cargarDatos();
		String [] sesiones=mc.mostrarSesiones("Scary Movie 2", arrayCine, 0,"2023-02-14");
		assertEquals(sesiones.length,3);
		assertEquals(sesiones[0],"00:00-Sala 01-7.99");
	}

}
