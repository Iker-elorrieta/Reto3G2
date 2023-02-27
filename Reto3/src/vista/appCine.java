package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.Metodos;
import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;

public class appCine extends JFrame {

	/**
	 * Objetos visuales
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDatePickerImpl datePicker;
	JComboBox<Object> cbSesion = new JComboBox<Object>();
	private UtilDateModel modelo = new UtilDateModel();
	
	private JComboBox<Object> cbCines = new JComboBox<Object>();
	private JComboBox<Object> cbPeliculas = new JComboBox<Object>();
	private JTextField tfUsuario;
	private JPasswordField tfpass;
	private JTextField tfdni;
	private JTextField tfnombre;
	private JTextField tfapellido;
	private JTextField tfusuarioreg;
	private JPasswordField passwordField;
	private JTable entradas;
	private DefaultTableModel model;
	private JTextField txtPrecioTotal;
	/**
	 * Objetos practicos
	 */
	private Cliente[] arrayClientes;
	private Cliente comprador;
	private Cine[] arrayCines;
	private String [] fechasPelicula;
	private Sesion [] arraySesiones,carritoCompra=new Sesion[0];
	private String[] nombreCines, nombrePeliculas, arraySesionesVisual;
	private Pelicula [] arrayPeliculas=new Pelicula[0];
	private Entrada entrada;
	private Metodos mc = new Metodos();
	private Calendar fechaMomento = Calendar.getInstance();
	private Date fecha = (Date) fechaMomento.getTime();
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	int cuentaSalas;
	private String [][] entradaTabla=new String[0][6];
	private String sexos[] = {"Hombre",  "Mujer"};
	private Boolean comprobarDNI,usuarioRepetido;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					appCine frame = new appCine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public appCine() {
		arrayClientes = mc.cargarClientes();
		arrayCines = mc.cargarDatos();
		nombreCines = mc.mostrarCines(arrayCines);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, -26, 484, 287);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		tabbedPane.addTab("Primera", null, panel, null);
		panel.setLayout(null);

		JLabel lblBienvenido = new JLabel("Bienvenido!");
		lblBienvenido.setBounds(228, 66, 216, 78);
		lblBienvenido.setFont(new Font("Impact", Font.BOLD, 30));
		panel.add(lblBienvenido);
		
		JLabel limagen = new JLabel();
		Image imagen = new ImageIcon(".\\Imagenes\\logo.png").getImage();
		Image imagenmodificada = imagen.getScaledInstance(179, 121, java.awt.Image.SCALE_SMOOTH);
		limagen.setBounds(10, 39, 179, 133);
		limagen.setIcon(new ImageIcon(imagenmodificada));
		panel.add(limagen);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Segunda", null, panel_1, null);
		panel_1.setLayout(null);
		cbCines.setFont(new Font("Tahoma", Font.BOLD, 12));

		cbCines.setModel(new DefaultComboBoxModel<Object>(nombreCines));
		cbCines.setBounds(172, 93, 135, 22);
		panel_1.add(cbCines);

		JLabel lblSelecCine = new JLabel("Seleccione el cine:");
		lblSelecCine.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSelecCine.setBounds(172, 68, 166, 14);
		panel_1.add(lblSelecCine);
		JButton btnNewButton = new JButton("New button");
		cbPeliculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setValue(fecha);
				fechasPelicula=mc.fechasAprobadas(String.valueOf(cbPeliculas.getSelectedItem()),arrayCines,cbCines.getSelectedIndex());
				btnNewButton.setEnabled(true);
			}
		});
		JButton btnAceptar = new JButton("Aceptar");
		JPanel panel_2 = new JPanel();
		JTextField txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(145, 52, 178, 20);
		panel_2.add(txtDate);
		txtDate.setColumns(10);
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent arg0) 
			{
				//create frame new object  f
				final JFrame f = new JFrame();
				//set text which is collected by date picker i.e. set date
				txtDate.setText(new DatePicker(f,fechasPelicula).setPickedDate());
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				arraySesiones = mc.cargarArraySesiones(String.valueOf(cbPeliculas.getSelectedItem()), arrayCines,
						cbCines.getSelectedIndex(), String.valueOf(txtDate.getText()));
				arraySesionesVisual=mc.mostrarSesiones(arraySesiones);
					cbSesion.setModel(new DefaultComboBoxModel<Object>(arraySesionesVisual));
					if(arraySesionesVisual[0].equals("No se emite este dia")) {
					btnAceptar.setEnabled(false);	
					}else {
					btnAceptar.setEnabled(true);
					}
			}
		});
		//set button bound
		btnNewButton.setBounds(323, 51, 27, 23);
		//add button in contentPane
		panel_2.add(btnNewButton);
		
		JButton btnFinalizar = new JButton("Siguiente");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				arrayPeliculas = mc.arrayPeliculas(arrayCines, cbCines.getSelectedIndex());
				nombrePeliculas=mc.cogerNombrePeliculas(arrayPeliculas);
				cbPeliculas.setModel(new DefaultComboBoxModel<Object>(nombrePeliculas));
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				modelo.setValue(fecha);
			}
		});
		btnFinalizar.setBounds(380, 225, 89, 23);
		panel_1.add(btnFinalizar);
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 459, 139);
		
		JButton btnComprar = new JButton("");
		btnComprar.setIcon(new ImageIcon(".\\Imagenes\\carrito.png"));
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnComprar.setBounds(10, 225, 89, 23);
		btnComprar.setVisible(false);
		panel_1.add(btnComprar);
		
		tabbedPane.addTab("Tercera", null, panel_2, null);
		panel_2.setLayout(null);

		cbPeliculas.setBounds(145, 15, 202, 22);
		panel_2.add(cbPeliculas);

		JLabel lblPeliculas = new JLabel("Peliculas:");
		lblPeliculas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeliculas.setBounds(10, 19, 89, 14);
		panel_2.add(lblPeliculas);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				cbPeliculas.setModel(new DefaultComboBoxModel<Object>());
				tabbedPane.setSelectedIndex(1);
				btnNewButton.setEnabled(false);
				txtDate.setText("");
				btnAceptar.setEnabled(false);
			}
		});
		btnAtras.setBounds(10, 225, 89, 23);
		panel_2.add(btnAtras);
		
		entradas = new JTable();
		entradas.setToolTipText("");
		entradas.setEnabled(false);
		model=new DefaultTableModel();
		entradas.setModel(model);
		model.addColumn("Nº");
		model.addColumn("Cine");
		model.addColumn("Sala");
		model.addColumn("Pelicula");
		model.addColumn("Fecha");
		model.addColumn("Hora");
		model.addColumn("Precio");
		entradas.getColumnModel().getColumn(0).setPreferredWidth(1);
		entradas.getColumnModel().getColumn(1).setPreferredWidth(40);
		entradas.getColumnModel().getColumn(2).setPreferredWidth(20);
		entradas.getColumnModel().getColumn(4).setPreferredWidth(30);
		entradas.getColumnModel().getColumn(3).setPreferredWidth(40);
		entradas.getColumnModel().getColumn(5).setPreferredWidth(5);
		entradas.getColumnModel().getColumn(6).setPreferredWidth(5);
		entradas.setBounds(10, 30, 409, 158);
		scrollPane.setViewportView(entradas);
		panel_3.add(scrollPane);
		
		txtPrecioTotal = new JTextField();
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				tabbedPane.setSelectedIndex(1);
				int sesionSeleccionada=cbSesion.getSelectedIndex();
				carritoCompra=mc.guardarSesiones(carritoCompra,arraySesiones,sesionSeleccionada);
				
				String cineT=String.valueOf(cbCines.getSelectedItem()),salaT=String.valueOf(((String) cbSesion.getSelectedItem()).split("-")[1]),
				peliculaT=String.valueOf(cbPeliculas.getSelectedItem()),fechaT=String.valueOf(dt.format(modelo.getValue())),
				horaT=String.valueOf(((String) cbSesion.getSelectedItem()).split("-")[0]),precioT=String.valueOf(((String) cbSesion.getSelectedItem()).split("-")[2]);
				JOptionPane.showMessageDialog(null, "Se añadió la pelicula "+peliculaT+", de la fecha "+fechaT+""
						+ " en la sala "+salaT+""
						+ " a la hora "+horaT+" al precio "+precioT);
				entradaTabla=mc.actualizarTabla(entradaTabla,cineT,salaT,peliculaT,fechaT,horaT,precioT);
				panel_3.remove(scrollPane);
				model.getDataVector().removeAllElements();
				for (int i = 0; i < entradaTabla.length; i++) {
					Object [] fila = new Object[7];
					fila[0]= (i+1);
					fila[1] = entradaTabla[i][0];
					fila[2] = entradaTabla[i][1];
					fila[3] = entradaTabla[i][2];
					fila[4] = entradaTabla[i][3];
					fila[5] = entradaTabla[i][4];
					fila[6] = entradaTabla[i][5];
					model.addRow(fila);
				}
				panel_3.add(scrollPane);
				btnAceptar.setEnabled(false);
				txtPrecioTotal.setText(String.valueOf(mc.calcularPrecioResumen(entradaTabla)));
				
				cbPeliculas.setModel(new DefaultComboBoxModel<Object>(nombrePeliculas));
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				modelo.setValue(fecha);
				btnComprar.setVisible(true);
				txtDate.setText("");
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(368, 225, 89, 23);
		panel_2.add(btnAceptar);
		contentPane.setLayout(null);
		
		JLabel lblFechas = new JLabel("Fecha:");
		lblFechas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechas.setBounds(10, 53, 89, 14);
		panel_2.add(lblFechas);

		
		cbSesion.setBounds(145, 157, 202, 22);
		panel_2.add(cbSesion);

		JLabel lblSesion = new JLabel("Sesion:");
		lblSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSesion.setBounds(10, 159, 89, 14);
		panel_2.add(lblSesion);

		
		tabbedPane.addTab("Cuarta", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnCompra = new JButton("Comprar");
		btnCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnCompra.setBounds(380, 225, 89, 23);
		panel_3.add(btnCompra);
		
		JButton btnCines = new JButton("Atras");
		btnCines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnCines.setBounds(10, 225, 89, 23);
		panel_3.add(btnCines);
		
		JButton btnBorrarDatos = new JButton("Borrar entradas");
		btnBorrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnComprar.setVisible(false);
				carritoCompra=new Sesion[0];
				txtPrecioTotal.setText("");
				panel_3.remove(scrollPane);
				model.getDataVector().removeAllElements();
				entradaTabla=new String[0][6];
				panel_3.add(scrollPane);
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnBorrarDatos.setBounds(171, 225, 140, 23);
		panel_3.add(btnBorrarDatos);
		
		JLabel precioTotal = new JLabel("Precio Total:");
		precioTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		precioTotal.setBounds(247, 200, 126, 14);
		panel_3.add(precioTotal);
		
		
		txtPrecioTotal.setEditable(false);
		txtPrecioTotal.setBounds(383, 197, 86, 20);
		panel_3.add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);
		
		JLabel lblDescuentos = new JLabel("Descuentos: 2 películas 20%, 3 o más 30%.");
		lblDescuentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuentos.setBounds(10, 11, 344, 14);
		panel_3.add(lblDescuentos);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Quinta", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblLogin = new JLabel("Introduzca su usuario y contraseña");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLogin.setBounds(61, 11, 347, 27);
		panel_4.add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setBounds(47, 54, 65, 14);
		panel_4.add(lblUsuario);
		
		JLabel lblerror = new JLabel("Rellena todos los campos");
		lblerror.setForeground(new Color(255, 0, 0));
		lblerror.setBounds(167, 107, 159, 14);
		panel_4.add(lblerror);
		lblerror.setVisible(false);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(167, 53, 144, 20);
		panel_4.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contraseña: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 85, 97, 14);
		panel_4.add(lblNewLabel);
		
		tfpass = new JPasswordField();
		tfpass.setBounds(167, 84, 143, 20);
		panel_4.add(tfpass);
		
		JLabel lblnoencontrado = new JLabel("No se ha encontrado el usuario o la contraseña es incorrecta");
		lblnoencontrado.setForeground(new Color(255, 0, 0));
		lblnoencontrado.setBounds(96, 123, 357, 14);
		panel_4.add(lblnoencontrado);
		lblnoencontrado.setVisible(false);
		
		JLabel lblnoencon = new JLabel("¿No tienes cuenta? Registrate");
		lblnoencon.setForeground(Color.GRAY);
		lblnoencon.setBounds(152, 173, 174, 14);
		panel_4.add(lblnoencon);
		
		JButton btnaceplogin = new JButton("Iniciar sesion");
		btnaceplogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = tfUsuario.getText() ;
				String contraseña = String.valueOf(tfpass.getPassword());
				float precioFinal=Float.valueOf(txtPrecioTotal.getText());
				
				if(usuario.equals("") || contraseña.equals("")) {
					lblerror.setVisible(true);
				}else {
					lblerror.setVisible(false);
					comprador=mc.encontrarCliente(arrayClientes,usuario,contraseña);
					if(comprador!=null) {
						lblnoencontrado.setVisible(false);
						entrada=new Entrada(comprador,carritoCompra,precioFinal);
						mc.insertarDatosCompra(entrada);
						int eleccion=JOptionPane.showConfirmDialog(null, "Gracias por su compra! \n Compra de "+carritoCompra.length+" entradas hecha correctamente, ¿Quiere generar una factura?", "Factura", 2);
						if(eleccion==0) {
							mc.generarFactura(entrada);
						}
						tfUsuario.setText("") ;
						tfpass.setText("");
						panel_3.remove(scrollPane);
						entradaTabla=new String [0][6];
						panel_3.add(scrollPane);
						carritoCompra=new Sesion[0];
						btnComprar.setVisible(false);
						tabbedPane.setSelectedIndex(0);
						lblerror.setVisible(false);
						lblnoencontrado.setVisible(false);
					}else {
						lblnoencontrado.setVisible(true);
					}
				}
				
			}
		});
		btnaceplogin.setBounds(177, 139, 119, 23);
		panel_4.add(btnaceplogin);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfUsuario.setText("") ;
				tfpass.setText("");
				tabbedPane.setSelectedIndex(5);
				lblerror.setVisible(false);
				lblnoencontrado.setVisible(false);
			}
		});
		btnRegistro.setBounds(177, 191, 119, 23);
		panel_4.add(btnRegistro);
		
		JButton btnAtrasResumen = new JButton("Atras");
		btnAtrasResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
				tfUsuario.setText("") ;
				tfpass.setText("");
				lblerror.setVisible(false);
				lblnoencontrado.setVisible(false);
			}
		});
		btnAtrasResumen.setBounds(20, 225, 89, 23);
		panel_4.add(btnAtrasResumen);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eleccion=JOptionPane.showConfirmDialog(null, "¿Está seguro de querer volver al inicio borrando su carrito de compra?", "Alerta", 2);
				if(eleccion==0) {
					tfUsuario.setText("") ;
					tfpass.setText("");
					carritoCompra=new Sesion[0];
					txtPrecioTotal.setText("");
					panel_3.remove(scrollPane);
					model.getDataVector().removeAllElements();
					entradaTabla=new String[0][6];
					panel_3.add(scrollPane);
					tabbedPane.setSelectedIndex(0);
					btnComprar.setVisible(false);
					lblerror.setVisible(false);
					lblnoencontrado.setVisible(false);
				}
			}
		});
		btnInicio.setBounds(380, 225, 89, 23);
		panel_4.add(btnInicio);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Sexta", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblregistro = new JLabel("Registro");
		lblregistro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblregistro.setBounds(154, 0, 79, 20);
		panel_5.add(lblregistro);
		
		JLabel lbldni = new JLabel("DNI: ");
		lbldni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbldni.setBounds(81, 34, 39, 14);
		panel_5.add(lbldni);
		
		tfdni = new JTextField();
		tfdni.setBounds(176, 31, 120, 20);
		panel_5.add(tfdni);
		tfdni.setColumns(9);
		
		JLabel lblnombre = new JLabel("Nombre: ");
		lblnombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnombre.setBounds(58, 67, 86, 14);
		panel_5.add(lblnombre);
		
		tfnombre = new JTextField();
		tfnombre.setBounds(176, 64, 120, 20);
		panel_5.add(tfnombre);
		tfnombre.setColumns(10);
		
		JLabel lblapellido = new JLabel("Apellido: ");
		lblapellido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblapellido.setBounds(58, 96, 84, 14);
		panel_5.add(lblapellido);
		
		tfapellido = new JTextField();
		tfapellido.setBounds(176, 95, 120, 20);
		panel_5.add(tfapellido);
		tfapellido.setColumns(10);
		
		JLabel lblUsuarioreg = new JLabel("Usuario: ");
		lblUsuarioreg.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuarioreg.setBounds(58, 127, 74, 14);
		panel_5.add(lblUsuarioreg);
		
		tfusuarioreg = new JTextField();
		tfusuarioreg.setBounds(176, 126, 120, 20);
		panel_5.add(tfusuarioreg);
		tfusuarioreg.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(35, 163, 109, 14);
		panel_5.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 157, 120, 20);
		panel_5.add(passwordField);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSexo.setBounds(80, 192, 62, 14);
		panel_5.add(lblSexo);
		
		JComboBox<String> sexocombo = new JComboBox<String>();
		sexocombo.setBounds(176, 190, 79, 22);
		panel_5.add(sexocombo);
		sexocombo.setModel(new DefaultComboBoxModel<String>(sexos));
		
		JLabel lblerrordni = new JLabel("Dni incorrecto");
		lblerrordni.setForeground(new Color(255, 0, 0));
		lblerrordni.setBounds(321, 34, 97, 14);
		panel_5.add(lblerrordni);
		lblerrordni.setVisible(false);
		
		JLabel lbluserrepe = new JLabel("Usuario repetido");
		lbluserrepe.setForeground(new Color(255, 0, 0));
		lbluserrepe.setBounds(315, 123, 103, 14);
		panel_5.add(lbluserrepe);
		lbluserrepe.setVisible(false);
		
		JLabel lblErrorVacio = new JLabel("Alguno de los campos esta vacío.");
		JButton btnaceptarreg = new JButton("Registrarse");
		btnaceptarreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = tfusuarioreg.getText();
				String dni = tfdni.getText();
				String nombre = tfnombre.getText();
				String apellido = tfapellido.getText();
				String contrasena = String.valueOf(passwordField.getPassword());
				char sexo = String.valueOf(sexocombo.getSelectedItem()).charAt(0);
				if(usuario.equals("") || dni.equals("") || nombre.equals("") || apellido.equals("") || contrasena.equals("")) {
					lblErrorVacio.setVisible(true);
				}else {
					lblerrordni.setVisible(false);
					lbluserrepe.setVisible(false);
					lblErrorVacio.setVisible(false);
					comprobarDNI = mc.comprobarDni(dni);
					if(!comprobarDNI) {
						lblerrordni.setVisible(true);
					}
					usuarioRepetido = mc.comprobarUser(arrayClientes, usuario);
					if(!usuarioRepetido) {
						lbluserrepe.setVisible(true);
					}
					if(usuarioRepetido && comprobarDNI) {
						mc.registrarCliente(dni, nombre, apellido, usuario, contrasena, sexo);
						arrayClientes=mc.cargarClientes();
						JOptionPane.showMessageDialog(null, "Te has registrado correctamente.");
						tabbedPane.setSelectedIndex(4);
						lblerrordni.setVisible(false);
						lbluserrepe.setVisible(false);
						lblErrorVacio.setVisible(false);
					}
				}
			}
		});
		btnaceptarreg.setBounds(366, 225, 103, 23);
		panel_5.add(btnaceptarreg);
		
		JButton btnAtrasSesion = new JButton("Atras");
		btnAtrasSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
				tfusuarioreg.setText("");
				tfdni.setText("");
				tfnombre.setText("");
				tfapellido.setText("");
				passwordField.setText("");
				lblerrordni.setVisible(false);
				lbluserrepe.setVisible(false);
				lblErrorVacio.setVisible(false);
			}
		});
		btnAtrasSesion.setBounds(10, 225, 89, 23);
		panel_5.add(btnAtrasSesion);
		
		lblErrorVacio.setVisible(false);
		lblErrorVacio.setForeground(Color.RED);
		lblErrorVacio.setBounds(176, 229, 227, 14);
		panel_5.add(lblErrorVacio);
	}
}
