package com.pasali.ulakserver;

import java.io.IOException;


public class Run {
	public static Server s;
	public static void main(String[] args) throws IOException {
		s = new Server();
		s.init();
		s.getMsg();
		s.close();
	}
}
