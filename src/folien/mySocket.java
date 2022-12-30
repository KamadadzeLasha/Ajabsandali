package folien;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

public class mySocket {
    public static void main(String[] args) {
        Socket client;
        while (true) {
            try {
                client = new Socket("myServer.in.tum.de", 4711);
                break;

            } catch (ConnectException e) {
                System.out.println("Connection refused!");
            } catch (IOException e) {
                //

            }
        }
        BufferedReader br;
        try {
            br = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            System.out.println(br.readLine());
        } catch (IOException e) {
            //
        }
    }

}
