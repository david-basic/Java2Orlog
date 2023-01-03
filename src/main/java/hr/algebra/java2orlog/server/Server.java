package hr.algebra.java2orlog.server;

import hr.algebra.java2orlog.models.GameState;
import hr.algebra.java2orlog.models.PlayerMetaData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final String HOST = "localhost";
    public static final int PORT = 2023;
    private static Map<Long, PlayerMetaData> players = new HashMap<>();

    public static Map<Long, PlayerMetaData> getPlayersMetaData() {
        return players;
    }

    public static void main(String[] args) {
        System.out.println("Server started");
        acceptRequests();
    }

    private static void acceptRequests() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.err.println("Server listening on port: " + serverSocket.getLocalPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected from port: " + clientSocket.getPort());
                new Thread(() -> processSerializableClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processSerializableClient(Socket clientSocket) {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

            PlayerMetaData playerMetaData = (PlayerMetaData) ois.readObject();

            System.out.println("Connected player metadata: " +
                    playerMetaData.getPlayerName() + " " +
                    playerMetaData.getIpAddress() + " " +
                    playerMetaData.getPort() + " " +
                    playerMetaData.getPid() + " " +
                    playerMetaData.getPlayerIsFirst().toString()); // TODO: 01/01/2023 mozda bude radilo ovako pogledaj malo pri runanju

            if (players.size() < 2) {
                System.out.println("Adding new player to the game!");

                players.put(playerMetaData.getPid(), playerMetaData);
                oos.writeObject("SUCCESS");
            } else {
                oos.writeObject("ERROR");
            }

            if (players.size() == 2) {
                System.out.println("Two players joined!");
                oos.writeObject("SUCCESS");

                Long pidFirstPlayer = players.keySet().stream().filter(p -> !p.equals(playerMetaData.getPid())).findFirst().get();

                PlayerMetaData firstPlayerMetaData = players.get(pidFirstPlayer);

                Socket firstClientSocket = new Socket(firstPlayerMetaData.getIpAddress(), Integer.parseInt(firstPlayerMetaData.getPort()));
                ObjectOutputStream oosFirstClient = new ObjectOutputStream(firstClientSocket.getOutputStream());
                ObjectInputStream oisFirstClient = new ObjectInputStream(firstClientSocket.getInputStream());

                System.err.println("Client is connecting to " + firstClientSocket.getInetAddress() + ":" + firstClientSocket.getPort());

                oos.writeObject("SUCCESS");
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Server is trying to open a connection to the client!");

        while (true) {
            try (Socket serverClientSocket = new Socket(Server.HOST, 1987)) {

                System.out.println("Trying to open output stream to the client!");

                ObjectOutputStream oos = new ObjectOutputStream(serverClientSocket.getOutputStream());

                System.out.println("Connection to the client successfully created!");


                System.out.println("Sending a new GameState object to client!");

                oos.writeObject(new GameState());

                System.out.println("New GameState object successfully sent to client!");
                System.out.println("");

                Thread.sleep(2000);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
