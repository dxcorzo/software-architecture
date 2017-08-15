import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import listeners.ItemChangeListener;

import javax.swing.SwingConstants;

public class Program implements SerialPortEventListener
{
	SerialPort serialPort;
	
	/** The port we're normally going to use. */
	private static final String port_name = "COM3";
	
	/**
	 * A BufferedReader which will be fed by a InputStreamReader converting the
	 * bytes into characters making the displayed results codepage independent
	 */
	private BufferedReader input;
	
	/** The output stream to the port */
	private OutputStream output;
	
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	JFrame frmVisorDeHumedad;
	JLabel labelHumedad;
	JLabel lblPuerto;

	public void initialize()
	{
		frmVisorDeHumedad = new JFrame();
		frmVisorDeHumedad.setResizable(false);
		frmVisorDeHumedad.setTitle("Visor de Humedad");
		frmVisorDeHumedad.setBounds(100, 100, 411, 206);
		frmVisorDeHumedad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisorDeHumedad.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(10, 11, 385, 106);
		frmVisorDeHumedad.getContentPane().add(panel);
		panel.setLayout(null);

		labelHumedad = new JLabel("22");
		labelHumedad.setBounds(257, 0, 128, 73);
		panel.add(labelHumedad);
		labelHumedad.setForeground(new Color(0, 0, 128));
		labelHumedad.setFont(new Font("Tahoma", Font.BOLD, 54));

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(0, 14, 75, 14);
		panel.add(lblDepartamento);

		JComboBox<model.Departamento> comboBoxDepartamento = new JComboBox<model.Departamento>();
		comboBoxDepartamento.setBounds(85, 11, 162, 20);
		panel.add(comboBoxDepartamento);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(37, 49, 46, 14);
		panel.add(lblCiudad);

		JComboBox<model.Ciudad> comboBoxCiudad = new JComboBox<model.Ciudad>();
		comboBoxCiudad.setBounds(85, 46, 162, 20);
		panel.add(comboBoxCiudad);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConectar.setBounds(139, 58, 89, 23);

		btnConectar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				btnConectar.setVisible(false);
				panel.setVisible(true);
			}
		});

		JButton btnDesconectar = new JButton("Desconectar");

		btnDesconectar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				btnConectar.setVisible(true);
				panel.setVisible(false);
			}
		});

		btnDesconectar.setBackground(Color.ORANGE);
		btnDesconectar.setBounds(131, 83, 116, 23);
		btnDesconectar.setContentAreaFilled(false);
		btnDesconectar.setOpaque(true);

		panel.add(btnDesconectar);

		frmVisorDeHumedad.getContentPane().add(btnConectar);
		
		JLabel lblNewLabel = new JLabel("Puerto:");
		lblNewLabel.setBounds(266, 152, 46, 14);
		frmVisorDeHumedad.getContentPane().add(lblNewLabel);
		
		lblPuerto = new JLabel("SIN CONEXION");
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuerto.setBounds(306, 152, 89, 14);
		frmVisorDeHumedad.getContentPane().add(lblPuerto);

		InfoHumedad dataDb = new InfoHumedad();
		
		Utils.CargarCombo(comboBoxDepartamento, dataDb.ConsultarDepartamentos());
		comboBoxDepartamento.addItemListener(new ItemChangeListener(comboBoxCiudad));
		
		InicializarComunicacionArduino();
	}

	private void InicializarComunicacionArduino()
	{
		CommPortIdentifier portId = null;
		Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();

		System.out.println("portEnum.hasMoreElements(): " + portEnum.hasMoreElements());

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements())
		{
			CommPortIdentifier currPortId = portEnum.nextElement();
			
			if (currPortId.getName().equals(port_name))
			{
				lblPuerto.setText(currPortId.getName());
				portId = currPortId;
				break;
			}
		}

		if (portId == null)
		{
			lblPuerto.setText("Sin conexión");
			return;
		}

		try
		{
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		}
		catch (Exception e)
		{
			System.err.println(e.toString());
		}
	}
	
	/**
	 * This should be called when you stop using the port. This will prevent port
	 * locking on platforms like Linux.
	 */
	public synchronized void close()
	{
		if (serialPort != null)
		{
			serialPort.removeEventListener();
			serialPort.close();
			System.out.println("finalizado");
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent)
	{
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
		{
			try
			{
				String inputLine = input.readLine();
				System.out.println(inputLine);
				labelHumedad.setText(inputLine);
			}
			catch (Exception e)
			{
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	public static void main(String[] args) throws Exception
	{
		Program main = new Program();
		
		main.initialize();
		
		Thread t = new Thread()
		{
			public void run()
			{
				main.frmVisorDeHumedad.setVisible(true);

				// the following line will keep this app alive for 1000 seconds,
				// waiting for events to occur and responding to them (printing incoming
				// messages to console).
				try
				{
					Thread.sleep(1000000);
				}
				catch (InterruptedException ie)
				{
				}
			}
		};
		
		t.start();
		
		System.out.println("Started");
	}
}