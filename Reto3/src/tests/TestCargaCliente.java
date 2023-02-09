package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cliente;

class TestCargaCliente {

	@Test
	void test() {
		Metodos mc = new Metodos();
		Cliente[] clientes = mc.cargarClientes();
		assertEquals(clientes[0].getApellidosCliente(),"Bueno");
		assertEquals(clientes.length,4);
	}

}
