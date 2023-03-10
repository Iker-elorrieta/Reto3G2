package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Cliente;

class TestCliente {
	Object o=new Object();
	//CLIENTE
	String DNI="12345678A",nombre="pepe",apellidos="garcia sanchez",contrasena="hola12345",user="igortxi";
	String sexo="H";
	String linea= "El cliente con el DNI " + DNI + ", nombre " + nombre + ",  apellidos "
			+ apellidos + ", contrasena " + contrasena + " y sexo ... tiene el user: " +user;
	@Test
	void testClientes() {
		Cliente c=new Cliente(DNI,nombre,user,apellidos,sexo,contrasena);
		assertEquals(c.toString(),linea);
		assertEquals(c.getDniCliente(),DNI);
		assertEquals(c.getNombreCliente(),nombre);
		assertEquals(c.getApellidosCliente(),apellidos);
		assertEquals(c.getContrasenaCliente(),contrasena);
		assertEquals(c.getSexo(),sexo);
		Cliente c1=new Cliente("","","","","","");
		c1.setSexo(sexo);
		c1.setApellidosCliente(apellidos);
		c1.setUser(user);
		c1.setContrasenaCliente(contrasena);
		c1.setDniCliente(DNI);
		c1.setNombreCliente(nombre);
		assertEquals(c1.toString(),linea);
		assertEquals(c1.getDniCliente(),DNI);
		assertEquals(c1.getUser(),user);
		assertEquals(c1.getNombreCliente(),nombre);
		assertEquals(c1.getApellidosCliente(),apellidos);
		assertEquals(c1.getContrasenaCliente(),contrasena);
		assertEquals(c1,c);
		int cod=c1.hashCode();
		assertEquals(c1.hashCode(),cod);
		assertTrue(c1.equals(c));
		assertFalse(c1.equals(null));
		assertFalse(c1.equals(o));
		assertTrue(c1.equals(c1));
	}

}
