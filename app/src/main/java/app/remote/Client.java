package app.remote;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import app.gui.dinamicobjects.FakeFlogger;

public class Client implements IClient {
	private ConnectionManager connectionManager;
	private String hostName;
	
	public Client(ConnectionManager m) {
		this.connectionManager = m;
		
		try {
			this.hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public String getHostName() throws RemoteException {
		return this.hostName;
	}
	
	public void endedGame() throws RemoteException {
		this.connectionManager.endedGame();
	}
	
	public void setFlogger(FakeFlogger f) throws RemoteException {
		this.connectionManager.setFlogger(f);
	}	
}
