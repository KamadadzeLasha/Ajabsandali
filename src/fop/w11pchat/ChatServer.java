package fop.w11pchat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.*;

public class ChatServer {
    private final ServerSocket server;
    private int numOfClients;

    public ChatServer(ServerSocket server) {
        this.server = server;
    }

    public static void main(String[] args) throws IOException {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("""
                        Enter port number that you want to join.
                        In case of a mi
smatch of inputs, port number is set to 3000 by default.""");
                ServerSocket serverSocket;
                try {
                     serverSocket= new ServerSocket(Integer.parseInt(scanner.nextLine()));
                }catch (Exception e){
                    serverSocket = new ServerSocket(3000);
                }
                ChatServer server = new ChatServer(serverSocket);
                System.out.println("Chosen port number " + serverSocket.getLocalPort());
                server.startServer();
            }
    }

    public void startServer() {
        try {
            System.out.println("Server has started.");
            while (!server.isClosed() && numOfClients < 50) {
                Socket socket = server.accept();
                Client client = new Client(socket);
                Thread thread = new Thread(client);
                thread.start();
                System.out.println("> " + LocalTime.now() + " *** " + client.getUsername() + " has joined chatroom. ***");
                numOfClients++;
                System.out.println("waiting client to join on port: " + socket.getLocalPort());
            }
            System.out.println("Number of clients must not exceed 50. Goodnight.");
        } catch (IOException e) {
            // left blank.
        }
    }

    public void close() {
        try {
            if (server != null) server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

