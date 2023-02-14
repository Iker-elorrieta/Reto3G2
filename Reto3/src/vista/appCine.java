package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.Metodos;
import modelo.Cine;
import modelo.Cliente;
import modelo.DateLabelFormatter;
import modelo.Entrada;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Color;

public class appCine extends JFrame {

	/**
	 * Objetos visuales
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDatePickerImpl datePicker;
	JComboBox<Object> cbSesion = new JComboBox<Object>();
	private UtilDateModel modelo = new UtilDateModel();
	private Properties p = new Properties();
	private JDatePanelImpl datePanel = new JDatePanelImpl(modelo, p);
	private JComboBox<Object> cbCines = new JComboBox<Object>();
	private JComboBox<Object> cbPeliculas = new JComboBox<Object>();
	private String sexos[] = {"Hombre",  "Mujer"};
	/**
	 * Objetos practicos
	 */
	private Cliente[] arrayClientes;
	private Cine[] arrayCines;
	private String[] nombreCines, nombrePeliculas, arraySesiones, sinSesiones;
	private Entrada entrada;
	private Metodos mc = new Metodos();
	private Calendar fechaMomento = Calendar.getInstance();
	private Date fecha = (Date) fechaMomento.getTime();
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	int cuentaSalas;
	private String [][] entradaTabla=new String[0][6];
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
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
		lblBienvenido.setBounds(98, 59, 216, 78);
		lblBienvenido.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 30));
		panel.add(lblBienvenido);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Segunda", null, panel_1, null);
		panel_1.setLayout(null);

		cbCines.setModel(new DefaultComboBoxModel<Object>(nombreCines));
		cbCines.setBounds(131, 0, 135, 22);
		panel_1.add(cbCines);

		JLabel lblSelecCine = new JLabel("Seleccione el cine");
		lblSelecCine.setBounds(10, 0, 135, 14);
		panel_1.add(lblSelecCine);

		cbPeliculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setValue(fecha);
			}
		});

		JButton btnFinalizar = new JButton("Siguiente");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				nombrePeliculas = mc.mostrarPeliculas(arrayCines, cbCines.getSelectedIndex());
				cbPeliculas.setModel(new DefaultComboBoxModel<Object>(nombrePeliculas));
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				modelo.setValue(fecha);
			}
		});
		btnFinalizar.setBounds(330, 199, 89, 23);
		panel_1.add(btnFinalizar);
		JPanel panel_3 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 409, 139);
		
		
		
		JButton btnComprar = new JButton("");
		btnComprar.setIcon(new ImageIcon(".\\Imagenes\\carrito.png"));
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnComprar.setBounds(10, 199, 89, 23);
		btnComprar.setVisible(false);
		panel_1.add(btnComprar);
		
		

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tercera", null, panel_2, null);
		panel_2.setLayout(null);

		cbPeliculas.setBounds(113, 11, 202, 22);
		panel_2.add(cbPeliculas);

		JLabel lblPeliculas = new JLabel("Peliculas:");
		lblPeliculas.setBounds(10, 19, 46, 14);
		panel_2.add(lblPeliculas);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				cbPeliculas.setModel(new DefaultComboBoxModel<Object>());
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnAtras.setBounds(10, 199, 89, 23);
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
		entradas.getColumnModel().getColumn(4).setPreferredWidth(15);
		entradas.getColumnModel().getColumn(3).setPreferredWidth(40);
		entradas.getColumnModel().getColumn(5).setPreferredWidth(5);
		entradas.getColumnModel().getColumn(6).setPreferredWidth(5);
		entradas.setBounds(10, 30, 409, 158);
		scrollPane.setViewportView(entradas);
		panel_3.add(scrollPane);
		
		txtPrecioTotal = new JTextField();
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
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
				
				txtPrecioTotal.setText(String.valueOf(mc.calcularPrecioTotal(entradaTabla))+"€");
				
				cbPeliculas.setModel(new DefaultComboBoxModel<Object>(nombrePeliculas));
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				modelo.setValue(fecha);
				btnComprar.setVisible(true);
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(330, 199, 89, 23);
		panel_2.add(btnAceptar);
		
		contentPane.setLayout(null);
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 11));
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modelo.getValue().before(fecha)) {
					modelo.setValue(fecha);
				}
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				arraySesiones = mc.mostrarSesiones(String.valueOf(cbPeliculas.getSelectedItem()), arrayCines,
						cbCines.getSelectedIndex(), String.valueOf(dt.format(modelo.getValue())));
				if (arraySesiones.length==0) {
					sinSesiones = new String[1];
					sinSesiones[0] = "No se emite este dia";
					cbSesion.setModel(new DefaultComboBoxModel<Object>(sinSesiones));
					btnAceptar.setEnabled(false);
				} else {
					cbSesion.setModel(new DefaultComboBoxModel<Object>(arraySesiones));
					btnAceptar.setEnabled(true);
				}
			}
		});
		datePicker.setBounds(113, 44, 202, 23);
		panel_2.add(datePicker);

		JLabel lblFechas = new JLabel("Fecha:");
		lblFechas.setBounds(10, 53, 46, 14);
		panel_2.add(lblFechas);

		
		cbSesion.setBounds(113, 78, 202, 22);
		panel_2.add(cbSesion);

		JLabel lblSesion = new JLabel("Sesion:");
		lblSesion.setBounds(10, 86, 46, 14);
		panel_2.add(lblSesion);

		
		tabbedPane.addTab("Cuarta", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnCompra = new JButton("Comprar");
		btnCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnCompra.setBounds(330, 199, 89, 23);
		panel_3.add(btnCompra);
		
		JButton btnCines = new JButton("Atras");
		btnCines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnCines.setBounds(10, 199, 89, 23);
		panel_3.add(btnCines);
		
		JButton btnBorrarDatos = new JButton("Borrar entradas");
		btnBorrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPrecioTotal.setText("");
				panel_3.remove(scrollPane);
				model.getDataVector().removeAllElements();
				entradaTabla=new String[0][6];
				panel_3.add(scrollPane);
			}
		});
		btnBorrarDatos.setBounds(141, 199, 140, 23);
		panel_3.add(btnBorrarDatos);
		
		JLabel precioTotal = new JLabel("Precio Total:");
		precioTotal.setBounds(244, 176, 86, 14);
		panel_3.add(precioTotal);
		
		
		txtPrecioTotal.setEditable(false);
		txtPrecioTotal.setBounds(330, 173, 86, 20);
		panel_3.add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Quinta", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblLogin = new JLabel("Introduzca su usuario y contraseña");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLogin.setBounds(23, 11, 347, 27);
		panel_4.add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setBounds(23, 54, 65, 14);
		panel_4.add(lblUsuario);
		
		JLabel lblerror = new JLabel("Rellena todos los campos");
		lblerror.setForeground(new Color(255, 0, 0));
		lblerror.setBounds(137, 124, 159, 14);
		panel_4.add(lblerror);
		lblerror.setVisible(false);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(126, 53, 144, 20);
		panel_4.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contraseña: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 100, 97, 14);
		panel_4.add(lblNewLabel);
		
		tfpass = new JPasswordField();
		tfpass.setBounds(127, 99, 143, 20);
		panel_4.add(tfpass);
		
		JLabel lblnoencontrado = new JLabel("No se ha encontrado el usuario o la contraseña es incorrecta");
		lblnoencontrado.setForeground(new Color(255, 0, 0));
		lblnoencontrado.setBounds(51, 149, 357, 14);
		panel_4.add(lblnoencontrado);
		lblnoencontrado.setVisible(false);
		
		JLabel lblnoencon = new JLabel("Puedes registrarte pulsando el boton de REGISTRARSE");
		lblnoencon.setForeground(new Color(255, 0, 0));
		lblnoencon.setBounds(64, 174, 311, 14);
		panel_4.add(lblnoencon);
		lblnoencon.setVisible(false);
		
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
					boolean encontrado = false;
					for(int i = 0; i<arrayClientes.length;i++) {
						if(arrayClientes[i].getUser().equals(usuario) && arrayClientes[i].getContrasenaCliente().equals(contraseña)) {
							encontrado = true;
						}
					}
					if(encontrado) {
						lblnoencontrado.setVisible(false);
						lblnoencon.setVisible(false);
						mc.insertarDatosCompra(arrayClientes,entradaTabla,arrayCines,usuario,precioFinal);
						
					}else {
						lblnoencontrado.setVisible(true);
						lblnoencon.setVisible(true);
					}
				}
				
			}
		});
		btnaceplogin.setBounds(151, 199, 119, 23);
		panel_4.add(btnaceplogin);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
				
			}
		});
		btnRegistro.setBounds(299, 199, 109, 23);
		panel_4.add(btnRegistro);
		
		JButton btnAtrasResumen = new JButton("Atras");
		btnAtrasResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnAtrasResumen.setBounds(28, 199, 89, 23);
		panel_4.add(btnAtrasResumen);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Sexta", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblregistro = new JLabel("Registro");
		lblregistro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblregistro.setBounds(154, 0, 79, 20);
		panel_5.add(lblregistro);
		
		JLabel lbldni = new JLabel("DNI: ");
		lbldni.setBounds(89, 34, 31, 14);
		panel_5.add(lbldni);
		
		tfdni = new JTextField();
		tfdni.setBounds(154, 31, 86, 20);
		panel_5.add(tfdni);
		tfdni.setColumns(9);
		
		JLabel lblnombre = new JLabel("Nombre: ");
		lblnombre.setBounds(81, 67, 46, 14);
		panel_5.add(lblnombre);
		
		tfnombre = new JTextField();
		tfnombre.setBounds(154, 62, 86, 20);
		panel_5.add(tfnombre);
		tfnombre.setColumns(10);
		
		JLabel lblapellido = new JLabel("Apellido: ");
		lblapellido.setBounds(81, 92, 61, 14);
		panel_5.add(lblapellido);
		
		tfapellido = new JTextField();
		tfapellido.setBounds(154, 89, 120, 20);
		panel_5.add(tfapellido);
		tfapellido.setColumns(10);
		
		JLabel lblUsuarioreg = new JLabel("Usuario: ");
		lblUsuarioreg.setBounds(81, 123, 51, 14);
		panel_5.add(lblUsuarioreg);
		
		tfusuarioreg = new JTextField();
		tfusuarioreg.setBounds(154, 120, 120, 20);
		panel_5.add(tfusuarioreg);
		tfusuarioreg.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña: ");
		lblNewLabel_1.setBounds(58, 148, 69, 14);
		panel_5.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 145, 113, 20);
		panel_5.add(passwordField);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setBounds(81, 180, 46, 14);
		panel_5.add(lblSexo);
		
		JComboBox<String> sexocombo = new JComboBox<String>();
		sexocombo.setBounds(154, 176, 79, 22);
		panel_5.add(sexocombo);
		sexocombo.setModel(new DefaultComboBoxModel<String>(sexos));
		
		JLabel lblerrordni = new JLabel("Dni incorrecto");
		lblerrordni.setForeground(new Color(255, 0, 0));
		lblerrordni.setBounds(250, 34, 97, 14);
		panel_5.add(lblerrordni);
		lblerrordni.setVisible(false);
		
		JLabel lbluserrepe = new JLabel("Usuario repetido");
		lbluserrepe.setForeground(new Color(255, 0, 0));
		lbluserrepe.setBounds(287, 123, 103, 14);
		panel_5.add(lbluserrepe);
		lbluserrepe.setVisible(false);
		
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
					
					
				}else {
					lblerrordni.setVisible(false);
					lbluserrepe.setVisible(false);
					boolean comprobardni = mc.comprobarDni(dni);
					if(!comprobardni) {
						
						lblerrordni.setVisible(true);
					}
					
					boolean repeuser = mc.comprobarUser(arrayClientes, usuario);
					if(!repeuser) {
						lbluserrepe.setVisible(true);
					}
					if(repeuser && comprobardni) {
						mc.registrarCliente(dni, nombre, apellido, usuario, contrasena, sexo);
						JOptionPane.showMessageDialog(null, "Te has registrado correctamente");
					}
					
				}
			}
		});
		btnaceptarreg.setBounds(301, 199, 89, 23);
		panel_5.add(btnaceptarreg);
		
		JButton btnAtrasSesion = new JButton("Atras");
		btnAtrasSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnAtrasSesion.setBounds(10, 199, 89, 23);
		panel_5.add(btnAtrasSesion);
		
		
		
		
	}
}
