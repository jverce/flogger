package app.remote;
import java.lang.reflect.InvocationTargetException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.gui.dinamicobjects.FakeFlogger;
import app.gui.dinamicobjects.IPaintable;

public class Server implements IServer {
	private Map<IClient, IClient> opponent;
	private ConnectionManager connectionManager;
	
	public Server(ConnectionManager m) {
		this.opponent = new HashMap<IClient, IClient>();
		this.connectionManager = m;
	}
	
	public void connect(IClient c) throws RemoteException {
		boolean allow = this.opponent.isEmpty();
		Class[] methodArgs = {IClient.class};
		Object[] args = {c};
				
		try {
			this.getClass().getMethod(allow + "Connect", methodArgs).invoke(this, args);
		} catch (IllegalArgumentException e) {
		} catch (SecurityException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {}
	}
	
	public void trueConnect(IClient c) throws RemoteException {
		Registry registry;
		IClient client;
		
		try {
			registry = LocateRegistry.getRegistry(c.getHostName(), 5567);
			client = (IClient) registry.lookup("Client" + c.getHostName());
		} catch (NotBoundException e) {
			e.printStackTrace();
			throw new RemoteException();
		}
						
		this.opponent.put(client, this.connectionManager);
		this.opponent.put(this.connectionManager, client);
		
		this.connectionManager.newGame();
	}
	
	public void falseConnect(IClient c) throws RemoteException {
		throw new RemoteException();
	}
	
	public void endGame(IClient c) throws RemoteException {
		this.opponent.get(c).endedGame();
		this.opponent.clear();
	}
	
	public List<IPaintable> getFallingObjects(IClient c) throws RemoteException {
		return this.connectionManager.getFallingObjects();
	}

	public void setFlogger(FakeFlogger f, IClient c) throws RemoteException {
		this.opponent.get(c).setFlogger(f);
	}
}