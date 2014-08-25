package com.pasali.ulakserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGUI {

	private JFrame frame;
	private JTextField textField;
	private  JTextArea textArea;
	

	/**
	 * Create the application.
	 */
	public ServerGUI(String no, String msg) {
		initialize(no,msg);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String no, String msg) {
		frame = new JFrame();
		frame.setBounds(700, 100, 212, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnGnder = new JButton("Cevapla");
		btnGnder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SendSmsGUI(textField.getText());
				frame.dispose();
			}
		});
		btnGnder.setBounds(82, 234, 100, 25);
		frame.getContentPane().add(btnGnder);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(12, 38, 122, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(no);
		JLabel lblGnderen = new JLabel("Gönderen:");
		lblGnderen.setBounds(12, 23, 97, 15);
		frame.getContentPane().add(lblGnderen);

		JLabel lblGelenMesaj = new JLabel("Gelen Mesaj:");
		lblGelenMesaj.setBounds(12, 81, 110, 15);
		frame.getContentPane().add(lblGelenMesaj);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(15, 103, 167, 119);
		textArea.setText(msg);
		
		frame.setVisible(true);
		frame.getContentPane().add(textArea);
	}
}
