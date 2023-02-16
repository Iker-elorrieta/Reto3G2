package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;

class TestCalcularPrecioTotal {
Metodos mc=new Metodos();
	@Test
	void test() {
		String [][] tabla=new String[0][6];
		tabla=mc.actualizarTabla(tabla, "Cines Golem", "Sala 01", "Dracula", "2023-02-14", "00:00","7.99" );
		tabla=mc.actualizarTabla(tabla, "Cines Zubiarte", "Sala 02", "Scary Movie", "2023-02-18", "15:00","4.99" );
		Float resultado=mc.calcularPrecioTotal(tabla);
		float suma=(float) (4.99+7.99);
		assertEquals(resultado,(float) (Math.round((suma*0.8)*100.0)/100.0));
		tabla=mc.actualizarTabla(tabla, "Cines Zubiarte", "Sala 02", "Scary Movie", "2023-02-18", "15:00","4.99" );
		resultado=mc.calcularPrecioTotal(tabla);
		suma=(float) (4.99+4.99+7.99);
		assertEquals(resultado,(float) (Math.round((suma*0.7)*100.0)/100.0));
	}

}
