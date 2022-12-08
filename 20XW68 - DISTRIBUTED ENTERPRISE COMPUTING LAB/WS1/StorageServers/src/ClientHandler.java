import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class ClientHandler implements Runnable {

    private HashMap<String, ArrayList<String>> storage;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ClientHandler(Socket socket) {
        try {
            this.storage = new HashMap<>();
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = this.bufferedReader.readLine();
            this.storage.put(this.username, new ArrayList<>());
            System.out.println("[CLIENTHANDLER] initialized storage for " + this.username + ".");
        } catch (IOException e) {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (this.socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient.equals(".exit"))
                    break;
                this.storage.get(this.username).add(messageFromClient);
                System.out.println("[" + this.username + "'s ClientHandler" + "]Message from " + this.username
                        + " '" + messageFromClient + "' stored in storage.");
            } catch (IOException e) {
                e.printStackTrace();
                closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
                break;
            }
        }
    }
}
