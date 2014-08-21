package com.pasali.ulakserver;


import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.pasali.database.Message;
import com.pasali.database.MsgDAO;

public class MessagesGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessagesGUI window = new MessagesGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MessagesGUI() throws ClassNotFoundException, SQLException {
		MsgDAO a = new MsgDAO();
		//a.create();
		com.pasali.database.Message msg = new com.pasali.database.Message("124434", "mehmddet");
		//a.addMsg(msg);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 280, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final MsgDAO msgdao = new MsgDAO();
		final HashMap<String, String> numbers = msgdao.getAllMsg();
		
		JList list = new JList(numbers.keySet().toArray());
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            String id = numbers.get(numbers.keySet().toArray()[index]);
		            Message m = msgdao.getMsg(Integer.valueOf(id));
		            new ServerGUI(m.getNo(), m.getBody());
		        } 
		    }
		});
		
		JLabel lblGelenMesajlar = new JLabel("Gelen Mesajlar:");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGelenMesajlar))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblGelenMesajlar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
