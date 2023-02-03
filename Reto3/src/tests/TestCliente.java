package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Cliente;

class TestCliente {
	Object o=new Object();
	//CLIENTE
	String DNI="12345678A",nombre="pepe",apellidos="garcia sanchez",contrasena="hola12345";
	char sexo='H';
	String linea= "El cliente con el DNI " + DNI + ", nombre " + nombre + ",  apellidos "
			+ apellidos + ", contrasena " + contrasena + " y sexo " + sexo;
	@Test
	void testClientes() {
		Cliente c=new Cliente(DNI,nombre,apellidos,contrasena,sexo);
		assertEquals(c.toString(),linea);
		assertEquals(c.getDniCliente(),DNI);
		assertEquals(c.getNombreCliente(),nombre);
		assertEquals(c.getApellidosCliente(),apellidos);
		assertEquals(c.getContrasenaCliente(),contrasena);
		assertEquals(c.getSexoCliente(),sexo);
		Cliente c1=new Cliente("","","","",'1');
		c1.setApellidosCliente(apellidos);
		c1.setContrasenaCliente(contrasena);
		c1.setDniCliente(DNI);
		c1.setNombreCliente(nombre);
		c1.setSexoCliente(sexo);
		assertEquals(c1.toString(),linea);
		assertEquals(c1.getDniCliente(),DNI);
		assertEquals(c1.getNombreCliente(),nombre);
		assertEquals(c1.getApellidosCliente(),apellidos);
		assertEquals(c1.getContrasenaCliente(),contrasena);
		assertEquals(c1.getSexoCliente(),sexo);
		assertEquals(c1,c);
		assertEquals(c1.hashCode(),169745696);
		assertTrue(c1.equals(c));
		assertFalse(c1.equals(null));
		assertFalse(c1.equals(o));
		assertTrue(c1.equals(c1));
	}

}
