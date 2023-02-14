package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class TestActualizarTabla {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] arrayCine;
		arrayCine=mc.cargarDatos();
		String [][] tabla=new String[0][6];
		tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 01", "Dracula", "2023-02-14", "00:00","7.99" );
		tabla=mc.actualizarTabla(tabla, "Cines Zubiarte", "Sala 02", "Scary Movie", "2023-02-18", "15:00","4.99" );
		assertEquals(tabla[0][0],"Cines Golem");
		assertEquals(tabla[1][5],"4.99");
	}

}
