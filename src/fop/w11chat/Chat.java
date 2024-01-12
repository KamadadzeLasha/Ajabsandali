package fop.w11chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Chat extends MiniJava {
    public static void main(String[] args) {
        Socket sock;
        boolean server = false;
        while (true) {
            String input = readString("""
                    Enter <port> in order to start the chat server\s
                     or <host>:<port> in order to connect to a running server.\s
                     Enter exit for exiting the chat.
                    """);
            if (input.equals("exit")) {
                System.out.println("Exiting.");
                return;
            }

            int posColon = input.indexOf(':');
            try {
                if (posColon != -1) {
                    sock = new Socket(InetAddress.getByName(input.substring(0, posColon)), Integer.parseInt(input.substring(posColon + 1)));
                } else {
                    int port = Integer.parseInt(input);
                    ServerSocket serverSock = new ServerSocket(port);
                    write("Server is started, expecting connections");
                    sock = serverSock.accept();
                    serverSock.close();
                    server = true;
                }
                break;
            } catch (UnknownHostException e) {
                write("Host unknown, try again!");
            } catch (NumberFormatException e) {
                write("Port invalid, try again!");
            } catch (ConnectException e) {
                write("Connection refused, try again!");
            } catch (IOException e) {
                write("I/O error, try again!");
            }
        }

        // This notation automatically insert a final close(), see
        // https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        // The true in Printwriter implies that after each print, a flush() is executed.

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(sock.getInputStream()));
             PrintWriter out = new PrintWriter(sock.getOutputStream(),
                     true)) {
            boolean running = true;
            if (server) {
                write("Connection established! You may send something.");
                String input = readString("> ");
                if ("exit".equals(input))
                    running = false;
                else
                    out.println(input);
            }
            while (running) {
                String recieved = in.readLine();
                if ("exit".equals(recieved)) {
                    write("exit received.");
                    break;
                }
                write(recieved);
                String input = readString("> ");
                if ("exit".equals(input))
                    running = false;
                out.println(input);
            }
            write("Exiting.");
        } catch (IOException e1) {
            write("Connection error, exiting.");
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                write("Connection refused.");
            }
        }
    }
}
