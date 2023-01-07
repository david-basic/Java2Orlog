package hr.algebra.java2orlog.thread;

import hr.algebra.java2orlog.jndi.JndiHelper;
import hr.algebra.java2orlog.jndi.JndiKeyEnum;
import hr.algebra.java2orlog.models.GameState;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread implements Runnable {
    private GameState gameState;
    private String clientPort;

    public ClientThread(GameState gameState){
        this.gameState = gameState;
    }

    public ClientThread(GameState gameState, String clientPort) {
        this.gameState = gameState;
        this.clientPort = clientPort;
    }

    @Override
    public void run() {
        String clientPort = null;
        try {
            clientPort = JndiHelper.getConfigurationParameter(JndiKeyEnum.CLIENT_1_PORT);
        } catch (NamingException | IOException e) {
            throw new RuntimeException(e);
        }
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(clientPort))) {

            System.out.println("Client server socket opened!");

            while (true) {
                System.out.println("Waiting for request from server!");

                Socket clientSocket = serverSocket.accept();

                System.out.println("The request from server accepted!");

                try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {

                    System.out.println("Object input stream successfully created!");

                    this.gameState = (GameState) ois.readObject();

                    System.out.println("New game state received from server!");
                    System.out.println("");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }
}
