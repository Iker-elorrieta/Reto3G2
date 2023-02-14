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
		assertEquals(arrayCines[0].getNombreCine(),"Cines Golem");
		assertEquals(arrayCines[0].getSalasCine().length,3);
		assertEquals(arrayCines[0].getSalasCine()[0].getNombreSala(),"Sala 01");
		int sesiones=arrayCines[1].getSalasCine()[1].getSesionesPorSala().length;
		assertEquals(arrayCines[0].getSalasCine()[0].getSesionesPorSala().length,sesiones);
		String peli=arrayCines[1].getSalasCine()[1].getSesionesPorSala()[1].getPeliSesion().getNombrePelicula();
		assertNotEquals(arrayCines[0].getSalasCine()[0].getSesionesPorSala()[0].getPeliSesion().getNombrePelicula(),peli);
	}

}
