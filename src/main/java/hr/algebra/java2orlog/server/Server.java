package hr.algebra.java2orlog.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String HOST = "localhost";
    public static final int PORT = 2023;

    public static void main(String[] args) {
        System.out.println("Server started");
        acceptRequests();
    }

    private static void acceptRequests() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            System.err.println("Server listening on port: " + serverSocket.getLocalPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected from port: " + clientSocket.getPort());
                // outer try catch blocks cannot handle the anonymous implementations
                //new Thread(() ->  processSerializableClient(clientSocket)).start();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
