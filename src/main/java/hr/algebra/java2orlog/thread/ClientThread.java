package hr.algebra.java2orlog.thread;

import hr.algebra.java2orlog.models.GameState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread implements Runnable {
    private GameState gameState;

    public ClientThread(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(1987)) {

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
}
