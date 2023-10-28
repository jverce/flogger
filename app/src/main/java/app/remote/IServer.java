package app.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import app.gui.dinamicobjects.FakeFlogger;
import app.gui.dinamicobjects.IPaintable;

public interface IServer extends Remote {
	public void connect(IClient c) throws RemoteException;
	public void endGame(IClient c) throws RemoteException;
	public void setFlogger(FakeFlogger f, IClient c) throws RemoteException;
	public List<IPaintable> getFallingObjects(IClient c) throws RemoteException;
}