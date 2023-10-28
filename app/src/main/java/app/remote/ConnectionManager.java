package app.remote;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import app.gui.dinamicobjects.FakeFlogger;
import app.gui.dinamicobjects.Flogger;
import app.gui.dinamicobjects.IPaintable;
import app.managers.MainManager;

public class ConnectionManager implements IClient {
	private MainManager mainManager;
	private IServer server;
	private IClient client;
	private Registry serverRegistry;
	private Registry clientRegistry;
	
	public ConnectionManager(MainManager m) {
		this.mainManager = m;
	}
	
	private void registerClient() {
		IClient stub;
		this.client = new Client(this);
				
		try {
			this.clientRegistry = LocateRegistry.createRegistry(5567);
			stub = (IClient) UnicastRemoteObject.exportObject(this.client, 5567);
			this.clientRegistry.bind("Client" + this.client.getHostName(), stub);
		} catch (AccessException e1) {
		} catch (RemoteException e1) {
			this.client = null;
			this.clientRegistry = null;
		} catch (AlreadyBoundException e1) {}
	}
	
	public void connect(String address) {
		this.registerClient();
		
		try {
			this.serverRegistry = LocateRegistry.getRegistry(address, 5566);
			this.server = (IServer) this.serverRegistry.lookup("Server");
			this.server.connect(this.client);
			
			this.mainManager.multiGuestGame();
		} catch (AccessException e1) {
		} catch (RemoteException e1) {
			this.clientRegistry = null;
			this.client = null;
			this.serverRegistry = null;
			this.server = null;
			this.mainManager.cancel();
		} catch (NotBoundException e1) {}
	}
	
	public void waitConnection() {
		this.server = new Server(this);
		IServer stub;		
				
		try {
			this.serverRegistry = LocateRegistry.createRegistry(5566);
			stub = (IServer) UnicastRemoteObject.exportObject(this.server, 5566);
			this.serverRegistry.bind("Server", stub);
		} catch (AccessException e) {
		} catch (RemoteException e) {
			this.serverRegistry = null;
			this.server = null;		
		} catch (AlreadyBoundException e) {}
	}
	
	public void cancelWait() {
		try {
			this.serverRegistry.unbind("Server");
			UnicastRemoteObject.unexportObject(this.server, true);
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		} catch (NullPointerException e) { 
		} finally {
			this.serverRegistry = null;			
			this.server = null;
		}
	}
	
	public List<IPaintable> getFallingObjects() {
		return this.mainManager.getFallingObjects();
	}
	
	public List<IPaintable> getRemoteFallingObjects() {
		try {
			return this.server.getFallingObjects(this.client);
		} catch (RemoteException e) {
			this.mainManager.endedGame();
			return null;
		}	
	}
	
	public void sendFlogger(Flogger f) {
		try {
			this.server.setFlogger(f.toFakeFlogger(), this.client);
		} catch (RemoteException e) {
			this.mainManager.endedGame();
		}
	}
	
	public void newGame() {
		this.mainManager.multiHostGame();
	}
	
	public void setFlogger(FakeFlogger f) {
		this.mainManager.setFlogger(f);
	}
	
	public void endGame() {
		try {
			this.server.endGame(this.client);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {}
	}
	
	public void endedGame() {
		this.server = null;
		this.mainManager.endedGame();
	}
	
	public String getHostName() throws RemoteException {
		return this.client.getHostName();
	}
}