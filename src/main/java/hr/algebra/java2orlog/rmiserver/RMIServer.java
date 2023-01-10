package hr.algebra.java2orlog.rmiserver;

import hr.algebra.java2orlog.jndi.JndiHelper;
import hr.algebra.java2orlog.jndi.JndiKeyEnum;

import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    private static final int RANDOM_PORT_HINT = 0;

    public static void main(String[] args) {
        try {
            String rmiPortString = JndiHelper.getConfigurationParameter(JndiKeyEnum.RMI_PORT_KEY);
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(rmiPortString));
            ChatService chatService = new ChatServiceImpl();
            ChatService skeleton = (ChatService) UnicastRemoteObject.exportObject(chatService, RANDOM_PORT_HINT);
            registry.rebind(ChatService.REMOTE_OBJECT_NAME, skeleton);
            System.err.println("Object registered in RMI registry");
        } catch (NamingException | IOException e) {
            e.printStackTrace();
        }
    }
}
