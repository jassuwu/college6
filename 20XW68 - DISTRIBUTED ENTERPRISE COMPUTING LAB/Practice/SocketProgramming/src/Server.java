import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket welcomeSocket = new ServerSocket(6789);
            System.out.println("Listening on port 6789...");
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(
                            connectionSocket.getInputStream()));

            DataOutputStream outToClient = new
                    DataOutputStream(connectionSocket.getOutputStream());
            String clientSentence = inFromClient.readLine();
            System.out.println(clientSentence);
            inFromClient.close();
            outToClient.close();
            welcomeSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}