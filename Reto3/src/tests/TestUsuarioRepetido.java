package tests;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cliente;

class TestUsuarioRepetido {
	Metodos mc = new Metodos();
	Cliente[] clientes = mc.cargarClientes();
	
	@Test
	void testUsuarioNoRepetido() {
		String usuario = "igor";
		boolean repetido = mc.comprobarUser(clientes, usuario);
		assertEquals(true, repetido);
	}
	
	@Test
	void testUsuarioRepetido() {
		String usuario = "as";
		boolean repetido = mc.comprobarUser(clientes, usuario);
		assertEquals(false, repetido);
	}

}
