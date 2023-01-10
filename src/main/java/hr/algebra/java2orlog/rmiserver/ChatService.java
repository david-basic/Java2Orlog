package hr.algebra.java2orlog.rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {
    String REMOTE_OBJECT_NAME = "hr.algebra.rmi.service";
    void sendMessage(String message, String user) throws RemoteException;
    List<String> getChatHistory() throws RemoteException;
}
