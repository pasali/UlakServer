package com.pasali.listmodel;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import com.pasali.database.Message;
import com.pasali.database.MsgDAO;
import com.pasali.ulakserver.ServerGUI;

public class ContactListGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Contact> contacts;
	private static JList<?> contactList;
	static HashMap<String, String> numbers = null;
	static MsgDAO msgdao;
	static DefaultListModel model;

	public ContactListGUI() throws SocketException {
		setLayout(new BorderLayout());
		// İp adresi yazdır
		new IpAdress();
		JLabel ipAdress = new JLabel(IpAdress.displayInterfaceInformation());
		
		numbers = new HashMap<String, String>();
		contacts = new ArrayList<Contact>();
		msgdao = new MsgDAO();
		numbers = msgdao.getAllMsg();
		model = new DefaultListModel<Contact>();
		//Veritabanında alınan verileri listeye aktar
		for (Object s : numbers.keySet().toArray()) {
			//contacts.add(new Contact(s.toString(), numbers.get(s)));
			model.addElement(new Contact(s.toString(), numbers.get(s)));
		}

		contactList = new JList(model);
		contactList.setCellRenderer(new ContactRenderer());
		contactList.setVisibleRowCount(5);
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
		add(ipAdress, BorderLayout.SOUTH);
	}
	public static void updateModel() {
		numbers = new HashMap<String, String>();
		numbers = msgdao.getAllMsg();
		model.removeAllElements();
		//Veritabanında alınan verileri listeye aktar
		for (Object s : numbers.keySet().toArray()) {
			//contacts.add(new Contact(s.toString(), numbers.get(s)));
			model.addElement(new Contact(s.toString(), numbers.get(s)));
		}
		contactList.setModel(model);
		contactList.repaint();
		contactList.updateUI();
	}

	public static void init() throws SocketException {
		JFrame frame = new JFrame("Gelen Mesajlar");
		frame.setBounds(300, 100, 250, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new ContactListGUI());
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
}
