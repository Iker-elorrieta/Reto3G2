package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cliente;

class TestEncontrarCliente {
	Metodos mc=new Metodos();
	@Test
	void test() {
		Cliente [] arrayC=mc.cargarClientes();
		Cliente encontrado=mc.encontrarCliente(arrayC, "elorrieta1", "elorrieta00");
		assertEquals(encontrado.getDniCliente(),"21918362X");
		assertEquals(encontrado.getContrasenaCliente(),"elorrieta00");
		assertEquals(encontrado.getNombreCliente(),"Elorrieta");
		assertEquals(encontrado.getSexo(),"H");
		assertEquals(encontrado.getUser(),"elorrieta1");
	}

}
