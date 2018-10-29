import java.net.InetAddress;

public class FarmId {
	InetAddress ipaddress;
	int port;
	double cpuUtilisation;
	public FarmId(InetAddress ip ,int port) {
		// TODO Auto-generated constructor stub
	this.port = port;
	this.ipaddress = ip;
	this.cpuUtilisation =0.0 ;
	}

}
