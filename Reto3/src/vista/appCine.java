package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.Metodos;
import modelo.Cine;
import modelo.Cliente;
import modelo.DateLabelFormatter;

import javax.swing.JTabbedPane;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;

public class appCine extends JFrame {

	/**
	 * Objetos visuales
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDatePickerImpl datePicker;
	JComboBox<Object> cbSesion = new JComboBox<Object>();
	private UtilDateModel model = new UtilDateModel();
	private Properties p = new Properties();
	private JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	private JComboBox<Object> cbCines = new JComboBox<Object>();
	private JComboBox<Object> cbPeliculas = new JComboBox<Object>();
	private String sexos[] = {"Hombre",  "Mujer"};
	/**
	 * Objetos practicos
	 */
	private Cliente[] arrayClientes;
	private Cine[] arrayCines;
	private String[] nombreCines, nombrePeliculas, arraySesiones, sinSesiones;
	private Metodos mc = new Metodos();
	private Calendar fechaMomento = Calendar.getInstance();
	private Date fecha = (Date) fechaMomento.getTime();
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	int cuentaSalas;
	private JTextField tfUsuario;
	private JPasswordField tfpass;
	private JTextField tfdni;
	private JTextField tfnombre;
	private JTextField tfapellido;
	private JTextField tfusuarioreg;
	private JPasswordField passwordField;

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
		nombreCines = new String[arrayCines.length];
		for (int i = 0; i < arrayCines.length; i++) {
			nombreCines[i] = arrayCines[i].getNombreCine();
		}

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
		cbCines.setBounds(118, 66, 135, 22);
		panel_1.add(cbCines);

		JLabel lblSelecCine = new JLabel("Seleccione el cine");
		lblSelecCine.setBounds(139, 11, 135, 14);
		panel_1.add(lblSelecCine);

		cbPeliculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setValue(fecha);
			}
		});

		JButton btnFinalizar = new JButton("Siguiente");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				nombrePeliculas = mc.mostrarPeliculas(arrayCines, cbCines.getSelectedIndex());
				cbPeliculas.setModel(new DefaultComboBoxModel<Object>(nombrePeliculas));
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				model.setValue(fecha);
			}
		});
		btnFinalizar.setBounds(139, 141, 89, 23);
		panel_1.add(btnFinalizar);

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

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(330, 199, 89, 23);
		panel_2.add(btnAceptar);

		contentPane.setLayout(null);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbSesion.setModel(new DefaultComboBoxModel<Object>());
				arraySesiones = mc.mostrarSesiones(String.valueOf(cbPeliculas.getSelectedItem()), arrayCines,
						cbCines.getSelectedIndex(), String.valueOf(dt.format(model.getValue())));
				if (arraySesiones.length==0) {
					sinSesiones = new String[1];
					sinSesiones[0] = "No se emite este dia";
					cbSesion.setModel(new DefaultComboBoxModel<Object>(sinSesiones));
				} else {
					cbSesion.setModel(new DefaultComboBoxModel<Object>(arraySesiones));
				}
			}
		});
		datePicker.setBounds(113, 44, 202, 23);
		panel_2.add(datePicker);

		JLabel lblFechas = new JLabel("Fecha:");
		lblFechas.setBounds(10, 53, 46, 14);
		panel_2.add(lblFechas);

		cbSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		cbSesion.setBounds(113, 78, 306, 22);
		panel_2.add(cbSesion);

		JLabel lblSesion = new JLabel("Sesion:");
		lblSesion.setBounds(10, 86, 46, 14);
		panel_2.add(lblSesion);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblSeleccion = new JLabel("Seleccion");
		lblSeleccion.setBounds(10, 11, 93, 20);
		panel_3.add(lblSeleccion);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLogin.setBounds(171, 11, 57, 27);
		panel_4.add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setBounds(23, 54, 65, 14);
		panel_4.add(lblUsuario);
		
		JLabel lblerror = new JLabel("Rellena todos los campos");
		lblerror.setForeground(new Color(255, 0, 0));
		lblerror.setBounds(260, 32, 159, 14);
		panel_4.add(lblerror);
		lblerror.setVisible(false);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(98, 53, 144, 20);
		panel_4.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contraseña: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 100, 97, 14);
		panel_4.add(lblNewLabel);
		
		tfpass = new JPasswordField();
		tfpass.setBounds(127, 99, 115, 20);
		panel_4.add(tfpass);
		
		JLabel lblnoencontrado = new JLabel("No se ha encontrado el usuario o la contraseña es incorrecta");
		lblnoencontrado.setForeground(new Color(255, 0, 0));
		lblnoencontrado.setBounds(10, 177, 357, 14);
		panel_4.add(lblnoencontrado);
		lblnoencontrado.setVisible(false);
		
		JLabel lblnoencon = new JLabel("Puedes registrarte pulsando el boton de REGISTRARSE");
		lblnoencon.setForeground(new Color(255, 0, 0));
		lblnoencon.setBounds(10, 202, 311, 14);
		panel_4.add(lblnoencon);
		lblnoencon.setVisible(false);
		
		JButton btnaceplogin = new JButton("Aceptar");
		btnaceplogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = tfUsuario.getText() ;
				String contraseña = String.valueOf(tfpass.getPassword());
				
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
						System.out.println("Encontrado");//Pasa a comprar la compra
					}else {
						lblnoencontrado.setVisible(true);
						lblnoencon.setVisible(true);
					}
				}
			}
		});
		btnaceplogin.setBounds(10, 136, 89, 23);
		panel_4.add(btnaceplogin);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
				
			}
		});
		btnRegistro.setBounds(282, 98, 109, 23);
		panel_4.add(btnRegistro);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblregistro = new JLabel("Registro");
		lblregistro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblregistro.setBounds(10, 11, 79, 20);
		panel_5.add(lblregistro);
		
		JLabel lbldni = new JLabel("DNI: ");
		lbldni.setBounds(10, 42, 31, 14);
		panel_5.add(lbldni);
		
		tfdni = new JTextField();
		tfdni.setBounds(41, 39, 86, 20);
		panel_5.add(tfdni);
		tfdni.setColumns(9);
		
		JLabel lblnombre = new JLabel("Nombre: ");
		lblnombre.setBounds(10, 75, 46, 14);
		panel_5.add(lblnombre);
		
		tfnombre = new JTextField();
		tfnombre.setBounds(63, 72, 86, 20);
		panel_5.add(tfnombre);
		tfnombre.setColumns(10);
		
		JLabel lblapellido = new JLabel("Apellido: ");
		lblapellido.setBounds(172, 75, 61, 14);
		panel_5.add(lblapellido);
		
		tfapellido = new JTextField();
		tfapellido.setBounds(226, 72, 120, 20);
		panel_5.add(tfapellido);
		tfapellido.setColumns(10);
		
		JLabel lblUsuarioreg = new JLabel("Usuario: ");
		lblUsuarioreg.setBounds(10, 112, 51, 14);
		panel_5.add(lblUsuarioreg);
		
		tfusuarioreg = new JTextField();
		tfusuarioreg.setBounds(63, 109, 120, 20);
		panel_5.add(tfusuarioreg);
		tfusuarioreg.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña: ");
		lblNewLabel_1.setBounds(10, 148, 69, 14);
		panel_5.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(89, 145, 113, 20);
		panel_5.add(passwordField);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setBounds(10, 190, 46, 14);
		panel_5.add(lblSexo);
		
		JComboBox<String> sexocombo = new JComboBox<String>();
		sexocombo.setBounds(63, 186, 79, 22);
		panel_5.add(sexocombo);
		sexocombo.setModel(new DefaultComboBoxModel<String>(sexos));
		
		JLabel lblerrordni = new JLabel("Dni incorrecto");
		lblerrordni.setForeground(new Color(255, 0, 0));
		lblerrordni.setBounds(172, 39, 97, 14);
		panel_5.add(lblerrordni);
		lblerrordni.setVisible(false);
		
		JLabel lbluserrepe = new JLabel("Usuario repetido");
		lbluserrepe.setForeground(new Color(255, 0, 0));
		lbluserrepe.setBounds(211, 112, 103, 14);
		panel_5.add(lbluserrepe);
		lbluserrepe.setVisible(false);
		
		JButton btnaceptarreg = new JButton("Aceptar");
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
		btnaceptarreg.setBounds(277, 166, 89, 23);
		panel_5.add(btnaceptarreg);
		
		
		
		
	}
}
