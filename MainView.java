import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Button;
import javax.swing.JTextField;

public class MainView {
	private JFrame frame;
	private static JTextField txtFieldDatarate;
	private static JTextField txtFieldWirelessPower;
	private static JTextField txtFieldADCPower;
	private static JTextField txtFieldTotalPower;
	private static double energyPerBit;
	private static double energyPerConversion;
	private static Device device;
	private static ArrayList<Sensor> sensors;
	private static String[] selectedNames;
	private static JList<String> selectedSensors;
	private static String[] names;
	private static JList<String> nameList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		device = new Device();	
		sensors = new ArrayList<Sensor>();
		energyPerBit = 5*Math.pow(10, -9);
		energyPerConversion = 3*Math.pow(10, -14);
		populateDefaultSensors();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		names = new String[sensors.size()];
		
		for (int i=0;i<sensors.size();i++)
		{
			names[i] = sensors.get(i).getName();
		}
		
		nameList = new JList<String>(new AbstractListModel() {
			String[] values = names;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		
		JScrollPane scrollPaneSensors = new JScrollPane(nameList);
		scrollPaneSensors.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneSensors.setBounds(20, 50, 150, 200);
		frame.getContentPane().add(scrollPaneSensors);
		
		txtFieldDatarate = new JTextField();
		txtFieldDatarate.setEditable(false);
		txtFieldDatarate.setBounds(217, 346, 206, 20);
		frame.getContentPane().add(txtFieldDatarate);
		txtFieldDatarate.setColumns(10);
		
		JButton btnAddSensors = new JButton("Select Sensors");
		btnAddSensors.setBounds(20, 274, 142, 23);
		frame.getContentPane().add(btnAddSensors);
		btnAddSensors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedNameIndex = nameList.getSelectedIndices();
				for (int i=selectedNameIndex.length-1;i>-1;i--)
				{
					device.addSensor(sensors.get(selectedNameIndex[i]));
				}
				refresh();
			}
		});
		
		JLabel lblTotalDatarate = new JLabel("Total Datarate:");
		lblTotalDatarate.setBounds(20, 349, 187, 14);
		frame.getContentPane().add(lblTotalDatarate);
		
		JLabel lblWirelessPowerConsumption = new JLabel("Wireless Power Consumption:");
		lblWirelessPowerConsumption.setBounds(20, 374, 187, 14);
		frame.getContentPane().add(lblWirelessPowerConsumption);
		
		txtFieldWirelessPower = new JTextField();
		txtFieldWirelessPower.setEditable(false);
		txtFieldWirelessPower.setBounds(217, 371, 206, 20);
		frame.getContentPane().add(txtFieldWirelessPower);
		txtFieldWirelessPower.setColumns(10);
		
		JLabel lblBps = new JLabel("bps");
		lblBps.setBounds(433, 349, 46, 14);
		frame.getContentPane().add(lblBps);
		
		JLabel lblW = new JLabel("W");
		lblW.setBounds(433, 374, 46, 14);
		frame.getContentPane().add(lblW);
		
		JLabel lblAdcPowerConsumption = new JLabel("ADC Power Consumption:");
		lblAdcPowerConsumption.setBounds(20, 399, 187, 14);
		frame.getContentPane().add(lblAdcPowerConsumption);
		
		txtFieldADCPower = new JTextField();
		txtFieldADCPower.setEditable(false);
		txtFieldADCPower.setBounds(217, 396, 206, 20);
		frame.getContentPane().add(txtFieldADCPower);
		txtFieldADCPower.setColumns(10);
		
		JLabel lblW_1 = new JLabel("W");
		lblW_1.setBounds(433, 399, 46, 14);
		frame.getContentPane().add(lblW_1);
		
		JLabel lblTotalPower = new JLabel("Total Power:");
		lblTotalPower.setBounds(20, 424, 187, 14);
		frame.getContentPane().add(lblTotalPower);
		
		txtFieldTotalPower = new JTextField();
		txtFieldTotalPower.setEditable(false);
		txtFieldTotalPower.setBounds(217, 421, 206, 20);
		frame.getContentPane().add(txtFieldTotalPower);
		txtFieldTotalPower.setColumns(10);
		
		JLabel lblW_2 = new JLabel("W");
		lblW_2.setBounds(433, 424, 46, 14);
		frame.getContentPane().add(lblW_2);
		
		JButton btnClear = new JButton("Clear Selection");
		btnClear.setBounds(224, 308, 150, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel lblSensors = new JLabel("Available Sensors");
		lblSensors.setBounds(20, 25, 150, 14);
		frame.getContentPane().add(lblSensors);
		
		selectedNames = new String[device.getSensorListSize()];
		
		for (int i=0;i<device.getSensorListSize();i++)
		{
			selectedNames[i] = device.getSensor(i).getName();
		}
		
		selectedSensors = new JList<String>(new AbstractListModel() {
			String[] values = selectedNames;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane scrollPaneSelectedSensors = new JScrollPane(selectedSensors);
		scrollPaneSelectedSensors.setBounds(224, 50, 150, 200);
		frame.getContentPane().add(scrollPaneSelectedSensors);
		
		JLabel lblSelectedSensors = new JLabel("Selected Sensors");
		lblSelectedSensors.setBounds(224, 25, 150, 14);
		frame.getContentPane().add(lblSelectedSensors);
		
		JButton btnRemoveSensors = new JButton("Remove Sensors");
		btnRemoveSensors.setBounds(224, 274, 150, 23);
		frame.getContentPane().add(btnRemoveSensors);
		btnRemoveSensors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedSensorIndex = selectedSensors.getSelectedIndices();
				for (int i=selectedSensorIndex.length-1;i>-1;i--)
				{
					device.removeSensor(selectedNames[selectedSensorIndex[i]]);
				}
				refresh();
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmEditGlobalConstants = new JMenuItem("Edit Global Constants");
		mnSettings.add(mntmEditGlobalConstants);
		mntmEditGlobalConstants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.newScreen();
			}
		});
		
		JMenuItem mntmEditSensorData = new JMenuItem("Edit Sensor Data");
		mnSettings.add(mntmEditSensorData);
		mntmEditSensorData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditSensorView.newScreen();
			}
		});
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmImport = new JMenuItem("Import");
		mnFile.add(mntmImport);
		mntmImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser();
				int returnValue = chooser.showOpenDialog(frame);
				
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();
					FileReader reader;
					
					try 
					{
						reader = new FileReader(chooser.getSelectedFile());
						int i;
						
						ArrayList<Character> charList = new ArrayList<Character>();
						while((i=reader.read()) != -1)
						{
							charList.add((char) i);
						}
						char[] charArray = new char[charList.size()];
						for (int j=0;j<charList.size();j++)
						{
							charArray[j] = (char) charList.get(j);
						}
						
						String deviceInfo = String.valueOf(charArray);
						System.out.println(deviceInfo);
						device = new Device(deviceInfo);
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
					refresh();
				}
			}
			
			
		});
		
		JMenuItem mntmExport = new JMenuItem("Export");
		mnFile.add(mntmExport);
		mntmExport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				int returnValue = chooser.showSaveDialog(frame);
				
				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();
					FileWriter writer;
					try {
						writer = new FileWriter(file);
						writer.write(device.getDeviceInfo());
						writer.flush();
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
				}
				
				
			}
			
		});
		
		
		
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nameList.clearSelection();
				device.clearList();
				refresh();
				
				
			}
		});
		
		
	}
	
	static void refresh()
	{
		txtFieldDatarate.setText(Long.toString(device.getTotalDatarate()));
		txtFieldWirelessPower.setText(Double.toString(device.getWirelessTransmissionPower()));
		txtFieldADCPower.setText(Double.toString(device.getADCConversionPower()));
		txtFieldTotalPower.setText(Double.toString(device.getTotalPower()));
		
		names = new String[sensors.size()];
		
		for (int i=0;i<sensors.size();i++)
		{
			names[i] = sensors.get(i).getName();
		}
		
		nameList.setModel(new AbstractListModel() {
			String[] values = names;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		selectedNames = new String[device.getSensorListSize()];
		
		for (int i=0;i<device.getSensorListSize();i++)
		{
			selectedNames[i] = device.getSensor(i).getName();
		}
		
		selectedSensors.setModel(new AbstractListModel() {
			String[] values = selectedNames;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	
	private void populateDefaultSensors()
	{
		sensors.add(new Sensor("Heart Rate", 10000, 4, 12, 0.000000001));
		sensors.add(new Sensor("Humidity (capacitive)", 10, 8, 16, 0));
		sensors.add(new Sensor("Battery Monitor", 10, 5, 8, 0));
		sensors.add(new Sensor("Temperature", 100, 6, 12, 0.0000000005));
		sensors.add(new Sensor("Accelerometer", 1000, 8, 14, 0.00000001));
		sensors.add(new Sensor("Magnetometer", 1000, 8, 16, 0.00000001));
		sensors.add(new Sensor("Altimeter/Pressure", 1000, 8, 24, 0.00000001));
		sensors.add(new Sensor("Imager (VGA, RGB)", 100000000, 8, 10, 0.001));
		sensors.add(new Sensor("Imager (MP4 compressed)", 10000000, 8, 10, 1.00E-03));
		sensors.add(new Sensor("Infrared Proximity", 1000, 10, 16, 0.00000001));
		sensors.add(new Sensor("Gyroscope", 1000, 12, 16, 0.000001));
		sensors.add(new Sensor("Microphone", 10000, 12, 16, 0.00001));
		sensors.add(new Sensor("CO2", 100000, 14, 16, 0.0000000005));
		sensors.add(new Sensor("Light", 100, 16, 24, 0.0000005));
		sensors.add(new Sensor("Strain", 1000, 16, 24, 0.00005));
		sensors.add(new Sensor("Default 1", 0, 0, 0, 0));
		sensors.add(new Sensor("Default 2", 0, 0, 0, 0));
		sensors.add(new Sensor("Default 3", 0, 0, 0, 0));
	}
	
	public static ArrayList<Sensor> getSensorList()
	{
		return sensors;
	}
	
	public static void setEnergyPerBit(double newEnergyPerBit)
	{
		energyPerBit = newEnergyPerBit;
	}
	
	public static void setEnergyPerConversion(double newEnergyPerConversion)
	{
		energyPerConversion = newEnergyPerConversion;
	}
	
	public static double getEnergyPerBit()
	{
		return energyPerBit;
	}
	
	public static double getEnergyPerConversion()
	{
		return energyPerConversion;
	}
}
