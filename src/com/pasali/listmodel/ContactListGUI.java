package com.pasali.listmodel;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.pasali.database.Message;
import com.pasali.database.MsgDAO;
import com.pasali.ulakserver.ServerGUI;

public class ContactListGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Contact> contacts;
	private JList<?> contactList;
	static HashMap<String, String> numbers = null;
	static MsgDAO msgdao;

	public ContactListGUI() {
		setLayout(new BorderLayout());
		numbers = new HashMap<String, String>();
		contacts = new ArrayList<Contact>();
		msgdao = new MsgDAO();
		numbers = msgdao.getAllMsg();
		
		for (Object s : numbers.keySet().toArray()) {
			contacts.add(new Contact(s.toString(), numbers.get(s)));
		}

		contactList = new JList<Object>(contacts.toArray());
		contactList.setCellRenderer(new ContactRenderer());
		contactList.setVisibleRowCount(4);
		JScrollPane pane = new JScrollPane(contactList);

		contactList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
				int selected[] = contactList.getSelectedIndices();
				for (int i = 0; i < selected.length; i++) {
					Contact element = (Contact) contactList.getModel()
							.getElementAt(selected[i]);
					Message m = msgdao.getMsg(Integer.valueOf(element.getId()));
					new ServerGUI(m.getNo(), m.getBody());
				}
			}}
		});

		add(pane, BorderLayout.NORTH);
	}

	public static void init() {
		JFrame frame = new JFrame("Gelen Mesajlar");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new ContactListGUI());
		frame.pack();
		frame.setVisible(true);
	}
}
