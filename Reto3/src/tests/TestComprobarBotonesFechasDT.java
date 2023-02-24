package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;
import modelo.Pelicula;

class TestComprobarBotonesFechasDT {

	Metodos mc=new Metodos();
	@Test
	void test() {
		int cineUno=0;
		Cine [] cinePrueba=mc.cargarDatos();
		Pelicula [] peli=mc.arrayPeliculas(cinePrueba, 0);
		String [] pelis=mc.cogerNombrePeliculas(peli);
		String [] fechaEscogida=null;
		for(int i=0;i<pelis.length;i++) {
			String [] fechas=mc.fechasAprobadas(pelis[i], cinePrueba, cineUno);
			if(fechas.length>1) {
				fechaEscogida=fechas;
				i=pelis.length;
			}
		}
		boolean correcta=false;
		for(int i=0;i<fechaEscogida.length;i++) {
			correcta=mc.comprobarBotoneFechasDT(fechaEscogida, fechaEscogida[i]);
			if(correcta==true) {
				i=fechaEscogida.length;
			}
		}
		assertTrue(correcta);
		correcta=mc.comprobarBotoneFechasDT(fechaEscogida, "2022-02-28");
		assertFalse(correcta);
	}
}
