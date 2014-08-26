package com.pasali.listmodel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.pasali.database.Message;
import com.pasali.database.MsgDAO;
import com.pasali.ulakserver.AboutGUI;
import com.pasali.ulakserver.ServerGUI;

public class ContactListGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JList<?> contactList;
	static HashMap<String, String> numbers = null;
	public static MsgDAO msgdao;
	static DefaultListModel model;
	static JFrame frame;
	public static String status;

	public ContactListGUI() throws SocketException, SQLException {
		setLayout(new BorderLayout());
	
		// İp adresi yazdır
		JLabel ipAdress = new JLabel(status);
		
		numbers = new HashMap<String, String>();
		msgdao = new MsgDAO();
		numbers = msgdao.getAllMsg();
		model = new DefaultListModel<Contact>();
		//Veritabanında alınan verileri listeye aktar
		for (Object s : numbers.keySet().toArray()) {
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
					new ServerGUI(m.getNo(), m.getBody(), element.getId());
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
			model.addElement(new Contact(s.toString(), numbers.get(s)));
		}
		contactList.setModel(model);
		contactList.repaint();
		contactList.updateUI();
	}

	public static void init() throws SocketException, SQLException {
		frame = new JFrame("Gelen Mesajlar");
		new IpAdress();
		status = IpAdress.displayInterfaceInformation();
		JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Yardım");
        file.setMnemonic(KeyEvent.VK_Y);

        JMenuItem MenuItemHowTo = new JMenuItem("Nasıl çalışır?");
        MenuItemHowTo.setMnemonic(KeyEvent.VK_N);
        MenuItemHowTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
        
        JMenuItem MenuItemAbout = new JMenuItem("Hakkında");
        MenuItemAbout.setMnemonic(KeyEvent.VK_H);
        MenuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new AboutGUI();
            }

        });

        file.add(MenuItemAbout);
        file.add(MenuItemHowTo);
        menubar.add(file);

        frame.setJMenuBar(menubar);
		frame.setBounds(300, 100, 250, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new ContactListGUI());
		frame.setResizable(false);
		frame.setVisible(true);
		
	
		
	}
}
