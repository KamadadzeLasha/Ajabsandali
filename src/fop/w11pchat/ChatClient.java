package fop.w11pchat;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;

public class ChatClient {
    private Socket socket;
    private BufferedReader infoIn;
    private BufferedWriter infoOut;
    private String username;
    private boolean closed;

    public ChatClient(Socket socket, String username) {

        try {
            this.socket = socket;
            this.username = username;
            this.infoOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.infoIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.closed = false;
        } catch (IOException e) {
            close(socket, infoOut, infoIn);
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                    Enter port number that you want to join.
                    In case of a mismatch of inputs, you'll connect on port 3000.""");
            Socket socket;
            try {
                socket = new Socket("localhost",Integer.parseInt(scanner.nextLine()));
            } catch (NumberFormatException e) {
                socket = new Socket("localhost", 3000);
            }
            System.out.print("Enter your username: ");
            String name = scanner.nextLine();
            System.out.println(LocalTime.now() + ": Connection accepted on port: " + socket.getPort() + '\n');
            System.out.println("""
                    Hello! Welcome to the chatroom.
                    Commands and instructions:
                    > user command @username<blank>message sends a DM
                        to the respective client and only this client.
                    > User command BAN<blank>Username to ban a specific person,
                        i.e. s(he) can't message you, neither can you message her/him.
                    > UNBAN<blank>username does exact opposite of BAN.
                    > If the client sends WHOIS, (s)he and only (s)he, receives a list of all currently
                        connected clients and since when they are connected.
                    > If a client sends LOGOUT, the connection of this client is closed
                        and all streams and of both sides are also closed.
                    > If a client sends PINGU, all currently connected clients receive an important
                        fact about penguins (what ever that might be :)).
                    > Use HELLO-WORLD to print simple hello world program.
                        say hi to the world.
                    > There also is one Easter-egg, hope you find it yourself :D.
                    """);
            ChatClient client = new ChatClient(socket, name);
            client.messageListener();
            client.sendMessage();

        }catch (IOException e) {
            System.out.println("Wrong server port number.\n" + "server don't yet exists.");
        }
    }

    public void sendMessage() {
        try {
            infoOut.write(username);
            infoOut.newLine();
            infoOut.flush();
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                infoOut.write(message);
                infoOut.newLine();
                infoOut.flush();
                if (message.equals("LOGOUT")) {
                    System.out.println("You logged out");
                    close(socket, infoOut, infoIn);
                    break;
                }

            }
        } catch (IOException e) {
            close(socket, infoOut, infoIn);
        }
    }

    void messageListener() {
        new Thread(() -> {
            String message;
            while (socket.isConnected()) {
                try {
                    message = infoIn.readLine();
                    System.out.println(message);
                    if (message.equals("LOGOUT")){
                        close(socket,infoOut,infoIn);
                    }
                } catch (Exception e) {
                    close(socket, infoOut, infoIn);
                }
            }
        }).start();
    }

    void close(Socket socket, BufferedWriter infoOut, BufferedReader infoIn) {
        if (!closed) {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (infoOut != null) {
                    infoOut.close();
                }
                if (infoIn != null) {
                    infoIn.close();
                }
                closed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
