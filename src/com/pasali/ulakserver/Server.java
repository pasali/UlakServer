package com.pasali.ulakserver;
import java.net.*;
import java.sql.SQLException;
import java.io.*;

import javax.swing.JOptionPane;

import com.pasali.database.Message;
import com.pasali.database.MsgDAO;
import com.pasali.listmodel.ContactListGUI;

public class Server {

	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String inputLine;
	private String[] inData;
	private MsgDAO msgdao;

	public String getInputLine() {								
		return inputLine;		
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}

	public void init() throws IOException {
		msgdao = new MsgDAO();
		try {
			serverSocket = new ServerSocket(5353);
		} catch (IOException e) {
			System.err.println("I/O exception: " + e.getMessage());
			System.exit(1);
		}
		System.out.println("Bağlantı bekleniyor...");

		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		showMsg(clientSocket.getRemoteSocketAddress() + " baglandı.");
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	public void sendMsg() {
		String number = SendSmsGUI.textField.getText();
		String msg = SendSmsGUI.textArea.getText();
		String dataToSend = msg + "|" + number;
		out.print(dataToSend + "\n");
		out.flush();
	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	public void getMsg() throws IOException {

		while ((inputLine = in.readLine()) != null) {
			inData = inputLine.split("\\|");
			try {
				msgdao.addMsg(new Message(inData[1], inData[0]));
				ContactListGUI.updateModel();
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("SQLite kütüphanesi bulunamadı");
			}
		}

	}

	public void close() throws IOException {
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}

}