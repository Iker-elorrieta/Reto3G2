package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class TestCargarDatos {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cine [] arrayCines=mc.cargarDatos();
		assertEquals(arrayCines.length,3);
		assertEquals(arrayCines[0].getSalasCine().length,6);
		assertEquals(arrayCines[0].getSalasCine()[0].getNombreSala(),"Sala 1");
		int sesiones=arrayCines[0].getSalasCine()[0].getSesionesPorSala().length;
		assertEquals(arrayCines[0].getSalasCine()[0].getSesionesPorSala().length,sesiones);
		String peli=arrayCines[0].getSalasCine()[0].getSesionesPorSala()[0].getPeliSesion().getNombrePelicula();
		assertEquals(arrayCines[0].getSalasCine()[0].getSesionesPorSala()[0].getPeliSesion().getNombrePelicula(),peli);
	}

}
