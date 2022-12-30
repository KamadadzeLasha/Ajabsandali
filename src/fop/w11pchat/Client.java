package fop.w11pchat;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.util.*;

public class Client implements Runnable {
    public static Map<String, Client> clients = new HashMap<>();
    private final String[] pinguFacts = {"There Are 18 (Or Maybe More) Species of Penguins.", "Penguins Have Feathers, Not Fur.", "They Eat a Wide Variety of Protein-Rich Food.", "Penguins evolved to fly underwater.", "Penguins live in many locations and habitats.", "Many male penguins gift female penguins with rocks in order to woo them.", "Gentoo Penguins are the fastest of all penguin species!", "Penguins poop every 20 minutes.", "Penguins are expert divers!", "Emperor penguins can spend up to eight years at sea before they are ready to mate and come to land."};
    private Socket socket;
    private BufferedReader infoIn;
    private BufferedWriter infoOut;
    private String username;
    private LocalTime logInTime;
    private Set<String> banned;
    private boolean closed;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.infoIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.infoOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = infoIn.readLine();
            logInTime = LocalTime.now();
            clients.put(this.username, this);
            this.closed = false;
            this.banned = new HashSet<>();
            sendToEveryone("> " + logInTime + " *** " + username + " has joined the chatroom. ***");
        } catch (IOException e) {
            close(socket, infoOut, infoIn);
        }
    }

    @Override
    public void run() {
        String message;
        while (socket.isConnected()) {
            try {
                message = infoIn.readLine();
                if (message != null) {
                    message = message.trim();
                 if (message.contains("@") && message.charAt(0) == '@' && message.contains(" ")) {
                        String name = message.substring(1, message.indexOf(" "));
                        Client client;
                        if (clients.containsKey(name) && !banned.contains(name) && !(client = clients.get(name)).banned.contains(this.username)) {
                            client.infoOut.write("> " + this.username + ": " + message);
                            client.infoOut.newLine();
                            client.infoOut.flush();
                        } else {
                            this.infoOut.write("Client does not exist, or even worse (s)he banned you!");
                            this.infoOut.newLine();
                            this.infoOut.flush();
                        }
                    } else if (message.contains("BAN")) {
                        String name = clients.get(message.substring(4)).username;
                        if (banned.add(name)) {
                            Client client = clients.get(name);
                            this.infoOut.write("> %s You banned %s.".formatted(LocalTime.now(), name));
                            this.infoOut.newLine();
                            this.infoOut.flush();
                            client.infoOut.write("> %s %s has banned you.".formatted(LocalTime.now(), this.username));
                            client.infoOut.newLine();
                            client.infoOut.flush();
                        } else {
                            this.infoOut.write("Client can not be banned");
                            this.infoOut.newLine();
                            this.infoOut.flush();
                        }
                    } else if (message.contains("UNBAN")) {
                        String name = message.substring(4);
                        if (banned.remove(name)) {
                            Client client = clients.get(name);
                            this.infoOut.write("> %s You unbanned %s.".formatted(LocalTime.now(), name));
                            this.infoOut.newLine();
                            this.infoOut.flush();
                            client.infoOut.write("> " + LocalTime.now() + this.username + " has unbanned you.");
                            client.infoOut.newLine();
                            client.infoOut.flush();
                        } else {
                            this.infoOut.write("Can not unban " + name);
                        }
                    } else {
                        switch (message) {
                            case "WHOIS":
                                int i = 0;
                                for (Client client : clients.values()) {
                                    this.infoOut.write("> " + ++i + ") " + client.username + " since " + client.logInTime);
                                    this.infoOut.newLine();
                                    this.infoOut.flush();
                                }
                                break;
                            case "LOGOUT":
                                close(socket, infoOut, infoIn);
                                break;
                            case "PINGU":
                                String fact = pinguFacts[new Random().nextInt(pinguFacts.length)];
                                sendToEveryone(fact);
                                this.infoOut.write(fact);
                                this.infoOut.newLine();
                                this.infoOut.flush();
                                break;
                            case "HELLO-WORLD":
                                this.infoOut.write("""
                                        class HelloWorld {
                                            public static void main(String[] args) {
                                                System.out.println("Hello, World!");\s
                                            }
                                        }""");
                                this.infoOut.newLine();
                                this.infoOut.flush();
                                break;
                            case "ART":
                                this.infoOut.write("""
                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                        ⠀⠀⠀⠀⠀⠀⠀⢀⣠⠶⠞⢛⣿⣿⣿⣿⣿⣿⣿⣷⣶⣄⡀⠀⠀⠀⠀⠀⠀⠀
                                        ⠀⠀⠀⠀⠀⣠⡾⠋⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀
                                        ⠀⠀⠀⢀⡾⠋⠀⠀⠀⢰⣿⣿⡿⠉⠀⠀⠉⢿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀
                                        ⠀⠀⢠⡞⠀⠀⠀⠀⠀⢸⣿⣿⣇⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀
                                        ⠀⠀⣾⠁⠀⠀⠀⠀⠀⠘⣿⣿⣿⣷⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀
                                        ⠀⢸⡏⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀
                                        ⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠛⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀
                                        ⠀⢸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀
                                        ⠀⠀⢿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⡀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⡿⠀⠀
                                        ⠀⠀⠘⢧⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⡆⠀⠀⢸⣿⣿⣿⣿⣿⡿⠃⠀⠀
                                        ⠀⠀⠀⠈⢷⣄⠀⠀⠀⠀⠀⠀⠈⠿⣿⣿⠿⠁⠀⠀⣸⣿⣿⣿⣿⡿⠁⠀⠀⠀
                                        ⠀⠀⠀⠀⠀⠙⢷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⡿⠋⠀⠀⠀⠀⠀
                                        ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠶⢦⣤⣄⣀⣀⣀⣠⣤⣾⡿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀
                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢉⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠙⣿⣷⡄⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣋⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠈⢿⣿⡄⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠈⢿⣿⡄⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⡿⠁⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠈⣿⣷⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⠃⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠘⣿⣧⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⢹⣿⡀⢸⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠈⣿⡇⠸⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢻⣷⠀⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⢸⣿⠀⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠘⣿⠀⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⣿⡀⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣈⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠞⠉⠉⢻⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠛⢿⣿⣿⣽⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⣴⣾⣿⣶⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠹⣿⣿⣿⣿⣿⣿⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠞⠉⠻⣿⣧⠀⠙⠻⣿⣿⣿⡀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠟⠹⢿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠈⢿⣿⣿⣯⠉⠛⠿⠿⣿⣿⣿⣿⣿⣿⡿⣿⣴⣶⣶⣿⣿⣷⣶⣶⣾⣿⣿⣿⣿⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣿⣯⠝⣛⣖⡀⠀⣿⠈⠙⠛⠿⣿⣷⠹⣿⠿⣿⣿⣿⣿⣿⣿⡟⠙⠛⢙⣿⣿⠿⠋⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⢿⢿⣽⢟⣽⣿⠇⠀⢹⠀⠀⠀⠀⠀⠉⠀⠀⠘⠛⠿⠻⠟⠻⠟⠓⠒⠒⢘⣏⢀⣀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⢳⡟⡡⣻⣿⡝⠁⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⣢⣾⡿⣲⣮⣾⣿⡷⡻⣣⣿⣿⢾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⠈⠀⠈⠈⠀⠀⣴⠋⠀⠀⠀⠀⠀⠀⠀⠀⢞⢽⢿⣿⢞⣽⣿⢿⢟⡝⣱⣿⣿⡗⣸⣽⣿⣿⣿⣿⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠙⢡⠪⡫⡻⢣⢞⡜⡝⠉⠈⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⠀⠀⠙⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠃⠀⠀⠀⠀⠀⢀⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠙⣿⣦⡀⠀⠀⠀⠙⠿⠶⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⢻⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠻⢿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠷⣄⡀⠀⠉⠻⢿⣿⣷⣄⣀⣀⣤⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢺⣿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡔⠈⠉⠓⢦⡀⠀⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿
                                        ⢿⠿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⣧⠀⠀⠀⠀⠙⣦⠀⠘⡇⠂⠉⠉⠉⠙⠛⠛⠛⠛⠉⠉⠉⠉⢀⡾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠄⠀⠀⢹⣿⣿⣿⠿⠿
                                        ⡆⠠⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⡀⢻⡄⠀⠀⠀⠀⢿⠆⠀⣿⠓⠒⠶⠶⠤⠤⠤⠤⠤⠶⠶⠶⠒⣾⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⢸⡇⠀⠀⠀⠀
                                        ⡇⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠇⠸⡇⠀⠀⠀⠀⢸⠁⠀⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠸⣇⠀⠀⠀⠀
                                        ⡇⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⣷⠀⠀⠀⠀⢸⡇⠀⢸⣾⣿⣶⡄⠀⠀⠀⠀⠀⠀⢠⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⣿⡟⠁⠀⠀⠀⢿⡀⠀⠀⠀
                                        ⣧⣤⣄⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⣸⡆⠀⠀⠀⠈⡇⠀⢸⣿⣿⣿⡃⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣴⡦⠀⠀⠘⠃⠀""");
                                infoOut.newLine();
                                infoOut.flush();
                                break;
                            default:
                                sendToEveryone("> " + this.username + ": " + message);
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                close(socket, infoOut, infoIn);
            }
        }
    }

    public void sendToEveryone(String message) {
        try {
            for (Client client : clients.values()) {
                if (!client.username.equals(this.username) && !banned.contains(client.username) && !client.banned.contains(this.username)) {
                    client.infoOut.write(message);
                    client.infoOut.newLine();
                    client.infoOut.flush();
                }
            }
        } catch (IOException e) {
            close(socket, infoOut, infoIn);
        }
    }

    public void removeClient() {
        clients.remove(this.username);
        System.out.println("> " + LocalTime.now() + " *** " + username + " has logged out from the chatroom. ***");
        sendToEveryone("> " + LocalTime.now() + " *** " + username + " has logged out from the chatroom. ***");
    }

    private void close(Socket socket, BufferedWriter infoOut, BufferedReader infoIn) {
        if (!closed) {
            try {
                if (socket != null) socket.close();
                if (infoIn != null) infoIn.close();
                if (infoOut != null) infoOut.close();
                closed = true;
                removeClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }
}