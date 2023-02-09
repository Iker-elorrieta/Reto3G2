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
import modelo.Pelicula;
import modelo.Salas;
import modelo.Sesion;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class appCine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDatePickerImpl datePicker;
	private JTextField textField;
	private Cliente [] arrayClientes;
	private Pelicula [] arrayPeliculas;
	private Sesion [] arraySesiones;
	private Salas [] arraySalas;
	private Cine [] arrayCines;
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
		Metodos mc = new Metodos();
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
				arrayClientes=mc.cargarClientes();
				arrayPeliculas=mc.cargarPeliculas();
				arraySesiones=mc.cargarSesiones(arrayPeliculas);
				arraySalas=mc.cargarSalas(arraySesiones);
				arrayCines=mc.cargarCines(arraySalas);
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
		
		JComboBox<Object> cbCines = new JComboBox<Object>();
		String[] nombres = new String[0];
		for(int i=0;i<arrayCines.length;i++) {
			String name = arrayCines[i].getNombreCine();
			nombres[i] = name;
		}
		cbCines.setModel(new DefaultComboBoxModel<Object>(nombres));
		cbCines.setBounds(118, 66, 135, 22);
		panel_1.add(cbCines);
		
		JLabel lblSelecCine = new JLabel("Seleccione el cine");
		lblSelecCine.setBounds(139, 11, 135, 14);
		panel_1.add(lblSelecCine);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(139, 141, 89, 23);
		panel_1.add(btnFinalizar);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tercera", null, panel_2, null);
		panel_2.setLayout(null);
		
		JComboBox<Object> cbPeliculas = new JComboBox<Object>();
		String[] pelis = new String[0];
		for(int i=0;i<arrayCines.length;i++) {
			String pel = arrayPeliculas[i].getNombrePelicula();
			pelis[i] = pel;
		}
		cbPeliculas.setModel(new DefaultComboBoxModel<Object>(pelis));
		cbPeliculas.setBounds(113, 11, 158, 22);
		panel_2.add(cbPeliculas);
		
		JLabel lblPeliculas = new JLabel("Peliculas:");
		lblPeliculas.setBounds(10, 19, 46, 14);
		panel_2.add(lblPeliculas);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(10, 199, 89, 23);
		panel_2.add(btnAtras);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(330, 199, 89, 23);
		panel_2.add(btnAceptar);
		
		UtilDateModel model = new UtilDateModel();
	//model.setDate(2022, 5, 6);
		Properties p = new Properties();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		contentPane.setLayout(null);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(113, 44, 202, 23);
		panel_2.add(datePicker);
		
		JLabel lblFechas = new JLabel("Fecha:");
		lblFechas.setBounds(10, 53, 46, 14);
		panel_2.add(lblFechas);
		
		JComboBox<Object> cbHoras = new JComboBox<Object>();
		cbHoras.setBounds(113, 78, 72, 22);
		panel_2.add(cbHoras);
		
		JLabel lblHoras = new JLabel("Hora:");
		lblHoras.setBounds(10, 86, 46, 14);
		panel_2.add(lblHoras);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(113, 111, 158, 22);
		panel_2.add(comboBox);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(10, 119, 46, 14);
		panel_2.add(lblSala);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(113, 144, 86, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 150, 46, 14);
		panel_2.add(lblPrecio);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblSeleccion = new JLabel("Seleccion");
		lblSeleccion.setBounds(10, 11, 93, 20);
		panel_3.add(lblSeleccion);
	}
}
