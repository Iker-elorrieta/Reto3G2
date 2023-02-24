package controlador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Salas;
import modelo.Sesion;

public class Metodos {
	
	final static String direccion = "jdbc:mysql://localhost/reto3";
	final static String usuario = "root";
	final static String contra = "";
	
	/**
	final static String direccion = "jdbc:mysql://10.5.14.210:3306/Cines";
	final static String usuario = "usuario";
	final static String contra = "Elorrieta00+";
	**/
	//COLUMNAS
	final static String DNIC="DNI",nombreCliente="nombreCliente",apellidos="apellidos",contrasena="contrasena",clientUser="usuario",sex="sexo",
				nCine="nombreCine",codCine="idCine",
				nSala="nombreSala",fechaE="FechaEmision",horaE="HoraEmision",
				precioI="precioInicial",codP="codPelicula",nPeli="nombrePelicula",idE="idEmision",genP="genero",duraP="duracion",
				precioT="precioTotal",descuentoC="descuento",horaC="horaCompra",
				precioF="precioFinal",codC="codCompra";
	//TABLAS
	final static String cine="Cine",cliente="Cliente",peli="Pelicula",salas="Sala",emi="Emision",entra="Entrada",compra="Compra";
	Calendar cal = Calendar.getInstance();
	Date fecha = null;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public Cliente[] cargarClientes() {
		Cliente[] arrayClientes = new Cliente[0];
		Connection conexion;

		try {
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			ResultSet cargaCliente = comando.executeQuery("SELECT * FROM `"+cliente+"`");
			while (cargaCliente.next()) {
				String dni = cargaCliente.getString(DNIC);
				String nombre = cargaCliente.getString(nombreCliente);
				String apellido = cargaCliente.getString(apellidos);
				String pass = cargaCliente.getString(contrasena);
				String user = cargaCliente.getString(clientUser);
				String sexo = cargaCliente.getString(sex);
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
			ResultSet cargaCines= comando.executeQuery("SELECT * FROM "+cine);
			while(cargaCines.next()) {	
				String nombreCine=cargaCines.getString(nCine);
				String idCine=cargaCines.getString(codCine);
				Statement comandoUno = (Statement) conexion.createStatement();
				ResultSet cargaSala= comandoUno.executeQuery("SELECT * FROM "+salas+" WHERE "+codCine+"='"+idCine+"'");
				Salas [] arraySalas = new Salas[0];
				while(cargaSala.next()) {
					String codCi=cargaSala.getString(codCine);
					String sala=cargaSala.getString(nSala);
					Statement comandoDos = (Statement) conexion.createStatement();
					fecha=cal.getTime();
					ResultSet cargaSesiones= comandoDos.executeQuery("SELECT * FROM "+emi
							+ " WHERE "+codCine+"='"+idCine+"' AND "+nSala+"='"+sala+"' AND "+fechaE+" >='"+dt.format(fecha)+"' "
							+ " ORDER BY "+fechaE+" ASC, "+horaE+" ASC");
					Sesion [] arraySesion = new Sesion[0];
					while(cargaSesiones.next()) {
						String salaSesion = cargaSesiones.getString(nSala);
						Date dia=cargaSesiones.getDate(fechaE);
						String hora=cargaSesiones.getString(horaE);
						LocalTime horaF=LocalTime.of(Integer.parseInt(hora.split(":")[0]),Integer.parseInt(hora.split(":")[1]));
						Float precio=cargaSesiones.getFloat(precioI);
						String pelicula=cargaSesiones.getString(codP);
						Integer idemision=cargaSesiones.getInt(idE);
						Statement comandoTres = (Statement) conexion.createStatement();
						ResultSet cargaPeliculas= comandoTres.executeQuery("SELECT * FROM "+peli+" WHERE "+codP+"='"+pelicula+"'");
						Pelicula peli=null;
						while(cargaPeliculas.next()) {
							String id = cargaPeliculas.getString(codP);
							String nombre = cargaPeliculas.getString(nPeli);
							String genero = cargaPeliculas.getString(genP);
							int dura =cargaPeliculas.getInt(duraP);
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

	public Pelicula[] arrayPeliculas(Cine[] arrayCines, int seleccion) {
		Pelicula[] nombrePeliculas = new Pelicula[0];

		int numeroSalas = arrayCines[seleccion].getSalasCine().length;
		for (int x = 0; x < numeroSalas; x++) {
			int numeroSesionesPorSala = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala().length;
			for (int y = 0; y < numeroSesionesPorSala; y++) {
				Pelicula peli=arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPeliSesion();
				if (comprobarPeliculas(peli.getNombrePelicula(), nombrePeliculas) == false) {
					Pelicula[] peliculasAux = new Pelicula[nombrePeliculas.length + 1];
					for (int c = 0; c < nombrePeliculas.length; c++) {
						peliculasAux[c] = nombrePeliculas[c];
					}
					peliculasAux[nombrePeliculas.length] = peli;
					nombrePeliculas = peliculasAux;
				}
			}
		}

		return nombrePeliculas;
	}
	public String [] cogerNombrePeliculas(Pelicula [] arrayPeliculas) {
		String [] nombrePeliculas=new String [arrayPeliculas.length];
		for(int i=0;i<arrayPeliculas.length;i++) {
			nombrePeliculas[i]=arrayPeliculas[i].getNombrePelicula();
		}
		return nombrePeliculas;
	}
	public boolean comprobarPeliculas(String nombrePelicula, Pelicula[] peliculasGuardadas) {
		boolean repetido = false;
		for (int i = 0; i < peliculasGuardadas.length; i++) {
			if (peliculasGuardadas[i].getNombrePelicula().equals(nombrePelicula)) {
				repetido = true;
			}
		}
		return repetido;
	}

	public Sesion [] cargarArraySesiones(String peliculaSeleccionada, Cine[] arrayCines, int seleccion,String fechaEscogida) {
		Sesion [] Sesiones = new Sesion[0];

		int numeroSalas = arrayCines[seleccion].getSalasCine().length;
		for (int x = 0; x < numeroSalas; x++) {
			int numeroSesionesPorSala = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala().length;
			for (int y = 0; y < numeroSesionesPorSala; y++) {
				Pelicula peliSesion=arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPeliSesion();
				Date fecha = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getFechaSesion();
				String fechaComparacion=String.valueOf(fecha);
				String pelicula = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPeliSesion().getNombrePelicula();
				LocalTime Hora = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getHoraSesion();
				String sala = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getNombreSala();
				float precio = Float.valueOf(arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPrecio());
				int id=Integer.valueOf(arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getIdEmision());
				Sesion unaSesion= new Sesion(peliSesion,id,sala,fecha,Hora,precio);
				if (fechaComparacion.equals(fechaEscogida) && pelicula.equals(peliculaSeleccionada)) {
						Sesion [] sesionesAux = new Sesion[Sesiones.length + 1];
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
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			comando.executeUpdate("INSERT INTO "+cliente+" VALUES ('" + dni + "' ,'" + user + "' ,'" + nombre + "','"+apellido + "','" + sexo + "' ,'"+contrasena+"')");
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
	public float calcularPrecioResumen(String [][] entradaTabla) {
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
	public String[] mostrarSesiones(Sesion[] arraySesiones) {
		// TODO Auto-generated method stubç
		String [] mostrarSesiones=new String[arraySesiones.length];
		for(int i=0;i<arraySesiones.length;i++) {
			mostrarSesiones[i]=arraySesiones[i].getHoraSesion()+" - "+arraySesiones[i].getNombreSala()+" - "+arraySesiones[i].getPrecio();
		}
		if (arraySesiones.length==0) {
			mostrarSesiones=new String[1];
			mostrarSesiones[0] = "No se emite este dia";
		}
		return mostrarSesiones;
	}

	public Sesion[] guardarSesiones(Sesion[] carritoCompra, Sesion[] arraySesiones, int sesionSeleccionada) {
		// TODO Auto-generated method stub
		Sesion [] sesionAuxiliar=new Sesion[carritoCompra.length+1];
		for(int i=0;i<carritoCompra.length;i++) {
			sesionAuxiliar[i]=carritoCompra[i];
		}
		sesionAuxiliar[carritoCompra.length]=arraySesiones[sesionSeleccionada];
		carritoCompra=sesionAuxiliar;
		return carritoCompra;
	}

	public Cliente encontrarCliente(Cliente[] arrayClientes, String usuario2, String contraseña) {
		// TODO Auto-generated method stub
		Cliente cliente=null;
		for(int i = 0; i<arrayClientes.length;i++) {
			if(arrayClientes[i].getUser().equals(usuario2) && arrayClientes[i].getContrasenaCliente().equals(contraseña)) {
				cliente= arrayClientes[i];
			}
		}
		return cliente;
	}

	public void insertarDatosCompra(Entrada entrada) {
		// TODO Auto-generated method stub
		Connection conexion;
		int codCompra=0;
		int descuento=0;
		if(entrada.getSesionPorTicket().length==2) {
			descuento=20;
		}else if(entrada.getSesionPorTicket().length>2) {
			descuento=30;
		}
		try {
			Calendar cal = Calendar.getInstance();
			fecha=cal.getTime();
			conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
			Statement comando = (Statement) conexion.createStatement();
			comando.executeUpdate("INSERT INTO "+compra+"("+precioT+","+descuentoC+","+horaC+","+DNIC+")"
					+ "VALUES ('"+entrada.getPrecioTotal()+"','"+descuento+"','"+dt1.format(fecha)+"','"+entrada.getCliente().getDniCliente()+"')");
			Statement buscarCodCompra = (Statement) conexion.createStatement();
			ResultSet codigoCompra=buscarCodCompra.executeQuery("SELECT "+codC+" FROM "+compra+" WHERE "+DNIC+"='"+entrada.getCliente().getDniCliente()+"' AND "+horaC+"='"+dt1.format(fecha)+"'");
			while(codigoCompra.next()) {
			codCompra=codigoCompra.getInt(codC);
			}
			for(int i=0;i<entrada.getSesionPorTicket().length;i++) {
				Statement insertarEntradas = (Statement) conexion.createStatement();
				insertarEntradas.executeUpdate("INSERT INTO "+entra+"("+precioF+","+idE+","+codC+") VALUES ('"+entrada.getSesionPorTicket()[i].getPrecio()+"','"+entrada.getSesionPorTicket()[i].getIdEmision()+"','"+codCompra+"')");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void generarFactura(Entrada entrada) {
		// TODO Auto-generated method stub
		Connection conexion;
			try {
				SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				fecha=cal.getTime();
				conexion = (Connection) DriverManager.getConnection(direccion, usuario, contra);
				FileWriter fich = new FileWriter(".\\Facturas\\"+entrada.getCliente().getUser()+dtf.format(fecha)+".txt");
				BufferedWriter linea=new BufferedWriter(fich);
				Statement cogerNombreCine = (Statement) conexion.createStatement();
				for(int i=0;i<entrada.getSesionPorTicket().length;i++) {
					String elCine="";
					ResultSet nombreCine=cogerNombreCine.executeQuery("SELECT "+nCine+" FROM "+cine+" JOIN "+salas+" USING ("+codCine+") JOIN "+emi+" USING ("+codCine+","+nSala+") WHERE "+idE+"='"+entrada.getSesionPorTicket()[i].getIdEmision()+"'");
					while(nombreCine.next()){
						elCine=nombreCine.getString(nCine);
					}
					Calendar cal = Calendar.getInstance();
					fecha=cal.getTime();
					linea.write(dt1.format(fecha)+": En el cine "+elCine+" ha comprado "+entrada.getSesionPorTicket()[i].toString()+"\n");
				}
				linea.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public String [] fechasAprobadas(String peliEscogida, Cine[] arrayCines, int seleccion) {
		int numeroSalas = arrayCines[seleccion].getSalasCine().length;
		String [] fechasDevolver=new String [0];
		for (int x = 0; x < numeroSalas; x++) {
			int numeroSesionesPorSala = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala().length;
			for (int y = 0; y < numeroSesionesPorSala; y++) {
				Date fecha = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getFechaSesion();
				String fechaComparacion=String.valueOf(fecha);
				String pelicula = arrayCines[seleccion].getSalasCine()[x].getSesionesPorSala()[y].getPeliSesion().getNombrePelicula();
				if ( pelicula.equals(peliEscogida) && comprobarfechas(fechaComparacion, fechasDevolver) == false) {
					String [] fechasAux = new String[fechasDevolver.length + 1];
					for (int c = 0; c < fechasDevolver.length; c++) {
						fechasAux[c] = fechasDevolver[c];
					}
					fechasAux[fechasDevolver.length] = fechaComparacion;
					fechasDevolver = fechasAux;
				}
			}
		}
		return fechasDevolver;
	}
	public boolean comprobarfechas(String fechaComparar, String [] fechasGuardadas) {
		boolean repetido = false;
		for (int i = 0; i < fechasGuardadas.length; i++) {
			if (fechasGuardadas[i].equals(fechaComparar)) {
				repetido = true;
			}
		}
		return repetido;
	}

	public boolean comprobarBotoneFechasDT(String[] fechasDisponibles, String fechaUnica) {
		// TODO Auto-generated method stub
		boolean disponible=false;
		for(int i=0;i<fechasDisponibles.length && disponible==false;i++) {
			if(fechasDisponibles[i].equals(fechaUnica)) {
				disponible=true;
			}
		}
		return disponible;
	}
}
