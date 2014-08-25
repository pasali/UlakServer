package com.pasali.ulakserver;

import java.io.IOException;
import java.sql.SQLException;
import com.pasali.listmodel.ContactListGUI;


public class MainClass {
	
	public static Server s;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		new ContactListGUI();
		ContactListGUI.init();;
		s = new Server();
		s.init();
		s.getMsg();
		s.close();
	}
}
