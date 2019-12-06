import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class EditSensorView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Sensor sensor;
	private JButton btnCancel;
	private JButton btnOk;
	private JLabel lblBps;
	private JLabel lblBits;
	private JLabel lblBit;
	private JLabel lblAdcPowerConsumption;
	private JTextField textField_4;


	/**
	 * Create the application.
	 */
	public EditSensorView() {
		initialize();
	}
	
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSensorView window = new EditSensorView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ArrayList<Sensor> sensors = MainView.getSensorList();
		
		String[] names = new String[sensors.size()];
		
		for (int i=0;i<sensors.size();i++)
		{
			names[i] = sensors.get(i).getName();
		}
		
		JList<String> nameList = new JList<String>(new AbstractListModel() {
			String[] values = names;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nameList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				sensor = sensors.get(nameList.getSelectedIndex());
				textField.setText(sensor.getName());
				textField_1.setText(Long.toString(sensor.getDatarate()));
				textField_2.setText(Integer.toString(sensor.getMinResolution()));
				textField_3.setText(Integer.toString(sensor.getMaxResolution()));
				textField_4.setText(Double.toString(sensor.getADCPowerConsumption()));
			}
			
		});
		
		JScrollPane scrollPaneSensors = new JScrollPane(nameList);
		scrollPaneSensors.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneSensors.setBounds(20, 11, 142, 195);
		frame.getContentPane().add(scrollPaneSensors);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(166, 11, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(222, 8, 145, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblDatarate = new JLabel("Datarate:");
		lblDatarate.setBounds(166, 50, 86, 14);
		frame.getContentPane().add(lblDatarate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 47, 96, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblMinResolution = new JLabel("Min Resolution:");
		lblMinResolution.setBounds(166, 81, 96, 14);
		frame.getContentPane().add(lblMinResolution);
		
		textField_2 = new JTextField();
		textField_2.setBounds(272, 78, 96, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblMaxResolution = new JLabel("Max Resolution:");
		lblMaxResolution.setBounds(166, 112, 86, 14);
		frame.getContentPane().add(lblMaxResolution);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(272, 109, 96, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		
		btnOk = new JButton("OK");
		btnOk.setBounds(222, 227, 89, 23);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sensor.setDatarate(Long.parseLong(textField_1.getText()));
				sensor.setName(textField.getText());
				sensor.setMinResolution(Integer.parseInt(textField_2.getText()));
				sensor.setMaxResolution(Integer.parseInt(textField_3.getText()));
				sensor.setADCPowerConsumption(Double.parseDouble(textField_4.getText()));
				MainView.refresh();
				frame.dispose();
			}
		});
		
		lblBps = new JLabel("bps");
		lblBps.setBounds(378, 50, 46, 14);
		frame.getContentPane().add(lblBps);
		
		lblBits = new JLabel("bits");
		lblBits.setBounds(378, 81, 46, 14);
		frame.getContentPane().add(lblBits);
		
		lblBit = new JLabel("bits");
		lblBit.setBounds(378, 112, 46, 14);
		frame.getContentPane().add(lblBit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		lblAdcPowerConsumption = new JLabel("ADC Power Consumption:");
		lblAdcPowerConsumption.setBounds(166, 137, 173, 14);
		frame.getContentPane().add(lblAdcPowerConsumption);
		
		textField_4 = new JTextField();
		textField_4.setBounds(200, 165, 139, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

}
