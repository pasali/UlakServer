package com.pasali.listmodel;

import java.net.*;
import java.util.*;

public class IpAdress {

	static String displayInterfaceInformation()
			throws SocketException {
		Enumeration<NetworkInterface> nets = NetworkInterface
				.getNetworkInterfaces();
		for (NetworkInterface netint : Collections.list(nets))
			if (netint.getDisplayName().equalsIgnoreCase("wlan0")) {
				Enumeration<InetAddress> inetAddresses = netint
						.getInetAddresses();
				return "IP Adresiniz:" + Collections.list(inetAddresses).get(1).toString();
			}

		return null;
	}
}
