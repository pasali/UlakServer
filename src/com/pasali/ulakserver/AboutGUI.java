package com.pasali.ulakserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class AboutGUI {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public AboutGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Hakkında");
		frame.setBounds(700, 100, 228, 315);
		frame.getContentPane().setLayout(null);

		JButton btnGnder = new JButton("Kapat");
		btnGnder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnGnder.setBounds(59, 238, 100, 25);
		frame.getContentPane().add(btnGnder);
		
		JLabel ulakLabel = new JLabel("ULAK - Bilgisayar Sunucusu");
		ulakLabel.setBounds(12, 130, 200, 31);
		frame.getContentPane().add(ulakLabel);
		
		JLabel copylabel = new JLabel("Telif Hakkı © Mehmet Başal");
		copylabel.setBounds(12, 153, 200, 31);
		frame.getContentPane().add(copylabel);

		
		frame.setVisible(true);
	}
}
