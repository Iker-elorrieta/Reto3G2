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
	}
}
