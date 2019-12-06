import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Settings {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings window = new Settings();
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
	public Settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnergyPerBit = new JLabel("Energy per Bit (Wireless Transmission):");
		lblEnergyPerBit.setBounds(10, 23, 232, 14);
		frame.getContentPane().add(lblEnergyPerBit);
		
		textField = new JTextField();
		textField.setBounds(10, 54, 152, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(Double.toString(MainView.getEnergyPerBit()));
		
		JLabel lblJ = new JLabel("J");
		lblJ.setBounds(179, 57, 46, 14);
		frame.getContentPane().add(lblJ);
		
		JLabel lblEnergyPerConversion = new JLabel("Energy per conversion step (ADC):");
		lblEnergyPerConversion.setBounds(10, 110, 196, 14);
		frame.getContentPane().add(lblEnergyPerConversion);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 135, 152, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(Double.toString(MainView.getEnergyPerConversion()));
		
		JLabel lblJ_1 = new JLabel("J");
		lblJ_1.setBounds(179, 138, 46, 14);
		frame.getContentPane().add(lblJ_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(322, 227, 89, 23);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(211, 227, 89, 23);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView.setEnergyPerBit(Double.parseDouble(textField.getText()));
				MainView.setEnergyPerConversion(Double.parseDouble(textField_1.getText()));
				MainView.refresh();
				frame.dispose();
			}
		});
	}

}
