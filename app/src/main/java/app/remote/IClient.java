package app.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

import app.gui.dinamicobjects.FakeFlogger;

public interface IClient extends Remote {
	public String getHostName() throws RemoteException;
	public void setFlogger(FakeFlogger f) throws RemoteException;
	public void endedGame() throws RemoteException;
}