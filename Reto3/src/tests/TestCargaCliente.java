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
		String [] arrayClientes=new String [clientes.length];
		assertTrue(clientes.length>0);
		assertTrue(clientes!=null);
		for(int i=0;i<clientes.length;i++) {
			arrayClientes[i]=clientes[i].toString();
			assertEquals(arrayClientes[i],clientes[i].toString());
		}
	}

}
