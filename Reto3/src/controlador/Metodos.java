package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import modelo.Cine;
import modelo.Cliente;
import modelo.Pelicula;
import modelo.Salas;
import modelo.Sesion;

public class Metodos {
	// conexion = (Connection)
	// DriverManager.getConnection("jdbc:mysql://10.5.14.210:3306/Cines", "Cliente",
	// "Elorrieta00+");
	String direccion = "jdbc:mysql://localhost/reto3";
	String usuario = "root";
	String contra = "";
	Calendar cal = Calendar.getInstance();
	Date fecha = null;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

	public Cliente[] cargarClientes() {
		Cliente[] arrayClientes = new Cliente[0];
		Connection conexion;

		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCliente = comando.executeQuery("SELECT * FROM Cliente");

			while (cargaCliente.next()) {
				String dni = cargaCliente.getString("DNI");
				String nombre = cargaCliente.getString("nombreCliente");
				String apellido = cargaCliente.getString("apellidos");
				String pass = cargaCliente.getString("contrasena");
				String user = cargaCliente.getString("usuario");
				String sexo = cargaCliente.getString("sexo");
				Cliente client = new Cliente(dni, nombre, user, apellido, sexo, pass);

				Cliente[] arrayNuevo = new Cliente[arrayClientes.length + 1];
				for (int c = 0; c < arrayClientes.length; c++) {
					arrayNuevo[c] = arrayClientes[c];
				}
				arrayNuevo[arrayClientes.length] = client;
				arrayClientes = arrayNuevo;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return arrayClientes;
	}

	public  Cine [] cargarDatos() {
		Cine [] arrayCine = new Cine[0];
		Connection conexion;
		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario,contra);
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCines= comando.executeQuery("SELECT * FROM cine");
			while(cargaCines.next()) {	
				String nombreCine=cargaCines.getString("nombreCine");
				String idCine=cargaCines.getString("idCine");
				Statement comandoUno = (Statement) conexion.createStatement();
				ResultSet cargaSala= comandoUno.executeQuery("SELECT * FROM sala WHERE idCine='"+idCine+"'");
				Salas [] arraySalas = new Salas[0];
				while(cargaSala.next()) {
					String codCi=cargaSala.getString("idCine");
					String sala=cargaSala.getString("nombreSala");
					Statement comandoDos = (Statement) conexion.createStatement();
					fecha=cal.getTime();
					ResultSet cargaSesiones= comandoDos.executeQuery("SELECT * FROM emision"
							+ " WHERE idCine='"+codCi+"' AND nombreSala='"+sala+"' AND FechaEmision >='"+dt.format(fecha)+"' "
							+ " ORDER BY FechaEmision ASC, HoraEmision ASC");
					Sesion [] arraySesion = new Sesion[0];
					while(cargaSesiones.next()) {
						String salaSesion = cargaSesiones.getString("nombreSala");
						Date dia=cargaSesiones.getDate("FechaEmision");
						String hora=cargaSesiones.getString("HoraEmision");
						LocalTime horaF=LocalTime.of(Integer.parseInt(hora.split(":")[0]),Integer.parseInt(hora.split(":")[1]));
						Float precio=cargaSesiones.getFloat("precioInicial");
						String pelicula=cargaSesiones.getString("codPeli");
						Integer idemision=cargaSesiones.getInt("idEmision");
						Statement comandoTres = (Statement) conexion.createStatement();
						ResultSet cargaPeliculas= comandoTres.executeQuery("SELECT * FROM pelicula WHERE codPeli='"+pelicula+"'");
						Pelicula peli=null;
						while(cargaPeliculas.next()) {
							String id = cargaPeliculas.getString("codPeli");
							String nombre = cargaPeliculas.getString("nombrePelicula");
							String genero = cargaPeliculas.getString("genero");
							int dura =cargaPeliculas.getInt("duracion");
							peli = new Pelicula(id,nombre,genero,dura);
						}
						
						Sesion se = new Sesion(peli,idemision,salaSesion,dia,horaF,precio);
						Sesion [] arrayNuevo = new Sesion[arraySesion.length+1];	
						for(int c = 0;c<arraySesion.length;c++) {
							arrayNuevo[c]=arraySesion[c];
						}
						arrayNuevo[arraySesion.length] = se;
						arraySesion = arrayNuevo;
					}
					
					Salas salaU = new Salas(codCi,sala,arraySesion);
					Salas [] arrayNuevo = new Salas[arraySalas.length+1];	
					for(int c = 0;c<arraySalas.length;c++) {
						arrayNuevo[c]=arraySalas[c];
					}
					arrayNuevo[arraySalas.length] = salaU;
					arraySalas = arrayNuevo;
				}
				
				Cine cineU = new Cine(nombreCine,arraySalas);
				Cine [] arrayNuevo = new Cine[arrayCine.length+1];	
				for(int c = 0;c<arrayCine.length;c++) {
					arrayNuevo[c]=arrayCine[c];
				}
				arrayNuevo[arrayCine.length] = cineU;
				arrayCine = arrayNuevo;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return arrayCine;
	}

	public String[] mostrarPeliculas(Cine[] arrayCines, int seleccion) {
		String[] nombrePeliculas = new String[0];

		int numeroSalas = arrayCines[seleccion].getSalasCine().length;
		for (int x = 0; x < numeroSalas; x++) {
			int numeroSesionesPorSala = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala().length;
			for (int y = 0; y < numeroSesionesPorSala; y++) {
				String pelicula = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPeliSesion()
						.getNombrePelicula();
				if (comprobarPeliculas(pelicula, nombrePeliculas) == false) {
					String[] peliculasAux = new String[nombrePeliculas.length + 1];
					for (int c = 0; c < nombrePeliculas.length; c++) {
						peliculasAux[c] = nombrePeliculas[c];
					}
					peliculasAux[nombrePeliculas.length] = pelicula;
					nombrePeliculas = peliculasAux;
				}
			}
		}

		return nombrePeliculas;
	}

	public boolean comprobarPeliculas(String nombrePelicula, String[] peliculasGuardadas) {
		boolean repetido = false;
		for (int i = 0; i < peliculasGuardadas.length; i++) {
			if (peliculasGuardadas[i].equals(nombrePelicula)) {
				repetido = true;
			}
		}
		return repetido;
	}

	public String[] mostrarSesiones(String peliculaSeleccionada, Cine[] arrayCines, int seleccion,
			String fechaEscogida) {
		String[] Sesiones = new String[0];

		int numeroSalas = arrayCines[seleccion].getSalasCine().length;
		for (int x = 0; x < numeroSalas; x++) {
			int numeroSesionesPorSala = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala().length;
			for (int y = 0; y < numeroSesionesPorSala; y++) {
				String fecha = String
						.valueOf(arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getFechaSesion());
				String pelicula = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPeliSesion()
						.getNombrePelicula();
				String Hora = String
						.valueOf(arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getHoraSesion());
				String sala = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getNombreSala();
				String precio = String
						.valueOf(arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPrecio());
				String unaSesion = Hora + " - "+ sala+" - " + precio;
				if (fecha.equals(fechaEscogida) && pelicula.equals(peliculaSeleccionada)) {
						String[] sesionesAux = new String[Sesiones.length + 1];
						for (int c = 0; c < Sesiones.length; c++) {
							sesionesAux[c] = Sesiones[c];
						}
						sesionesAux[Sesiones.length] = unaSesion;
						Sesiones = sesionesAux;
				}
			}
		}
		return Sesiones;
	}

	public void registrarCliente(String dni, String nombre, String apellido, String user, String contrasena,
			char sexo) {
		Connection conexion;
		try {
			System.out.println(dni);
			System.out.println(sexo);
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			comando.executeUpdate("insert into cliente VALUES('" + dni + "' ,'" + user + "' ,'" + nombre + "' ,'"
					+ apellido + "' ,'" + sexo + "' ,'" + contrasena + "')");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean comprobarDni (String dni) {
		boolean dnicom = true;
		if(dni.length() != 9) {
			dnicom = false;
		}
		String letras[] = {"t","r","w","a","g","m","y","f","p","d","x","b","n","j","z","s","q","v","h","l","c","k","e"};
		try {
			int numero = Integer.valueOf(dni.substring(0, 8));
			String letra = String.valueOf(dni.charAt(8));
			int resultado = numero%23;
			if(!(letras[resultado].equals(letra.toLowerCase()))) {
				dnicom = false;
			}
			
		}catch(Exception e ) {
			dnicom = false;
		}
		
		return dnicom;
		
	}

	public boolean comprobarUser(Cliente[] arrayClientes, String usuario2) {
		boolean userrepe = true;
		for(int i = 0; i<arrayClientes.length; i++) {
			if(arrayClientes[i].getUser().equals(usuario2)) {
				userrepe = false;
			}
		}
		return userrepe;
	}
	
	public String [] mostrarCines(Cine [] arrayCines) {
		String []nombreCines =new String[arrayCines.length];
		for (int i = 0; i < arrayCines.length; i++) {
			nombreCines[i] = arrayCines[i].getNombreCine();
		}
		return nombreCines;
	}
	public String [][] actualizarTabla(String[][] entradaTabla, String cineT, String salaT, String peliculaT, String fechaT, String horaT, String precioT){
		String [][] arrayAux=new String [entradaTabla.length+1][6];
		for(int i=0;i<entradaTabla.length;i++) {
		arrayAux[i][0]=entradaTabla[i][0];
		arrayAux[i][1]=entradaTabla[i][1];
		arrayAux[i][2]=entradaTabla[i][2];
		arrayAux[i][3]=entradaTabla[i][3];
		arrayAux[i][4]=entradaTabla[i][4];
		arrayAux[i][5]=entradaTabla[i][5];
		}
		arrayAux[entradaTabla.length][0]=cineT;
		arrayAux[entradaTabla.length][1]=salaT;
		arrayAux[entradaTabla.length][2]=peliculaT;
		arrayAux[entradaTabla.length][3]=fechaT;
		arrayAux[entradaTabla.length][4]=horaT;
		arrayAux[entradaTabla.length][5]=precioT;
		entradaTabla=arrayAux;
		return entradaTabla;
	}
	public float calcularPrecioTotal(String [][] entradaTabla) {
		float sumaPrecio=0;
		for(int i=0;i<entradaTabla.length;i++) {
			sumaPrecio+=Float.valueOf(entradaTabla[i][5]);
		}
		if(entradaTabla.length==2) {
			sumaPrecio=(float) (sumaPrecio*0.8);
		}else if(entradaTabla.length>2){
			sumaPrecio=(float) (sumaPrecio*0.7);
		}
		
		return (float) (Math.round(sumaPrecio*100.0)/100.0);
	}
}
