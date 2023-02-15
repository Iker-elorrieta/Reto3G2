package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;
import modelo.Sesion;

class TestCargarArraySesiones {
	Metodos mc =new Metodos();
	@Test
	void test() {
		Cine [] arrayC=mc.cargarDatos();
	//arrayC[i].getSalasCine()[i].getSesionesPorSala()[i].get
		for(int i=0;i<arrayC.length;i++) {
			for(int x=0;x<arrayC[i].getSalasCine().length;x++) {
				for(int y=0;y>arrayC[i].getSalasCine()[x].getSesionesPorSala().length;y++) {
					
				}
			}
		}
		String fechaEscogida="2023-02-15";
		String peliculaEscogida="Dracula";	
		Sesion [] arrayS=null;
		for(int i=0;i<arrayC.length;i++) {
		arrayS=mc.cargarArraySesiones(peliculaEscogida, arrayC, i, fechaEscogida);
		}
		assertTrue(arrayS!=null);
	}

}
