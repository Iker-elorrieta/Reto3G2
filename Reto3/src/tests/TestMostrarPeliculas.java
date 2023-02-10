package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class testMostrarPeliculas {

@Test
void test() {
Metodos mc = new Metodos();
Cine[] cine = mc.cargarDatos();
String[] nombres = mc.mostrarPeliculas(cine, 0);
System.out.println(nombres[0]);
assertEquals(nombres[0],"2001: Odisea en el espacio");
assertEquals(nombres[1],"La vida de Brian");
assertEquals(nombres[2],"Dracula");
assertEquals(nombres[3],"Scary Movie 2");
//Las peliculas ya estaban ordenadas por fecha y hora previamente en el localhost y servidor.
}

}
