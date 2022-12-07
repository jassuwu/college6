import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = this.bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("[SERVER]: " + this.username + " has entered the chat.");
        } catch (IOException e) {
            e.printStackTrace();
            closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (this.socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                e.printStackTrace();
                closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToBroadcast) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.username.equals(this.username)) {
                    clientHandler.bufferedWriter.write(messageToBroadcast);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("[SERVER]: " + username + " left the chat.");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        this.removeClientHandler();
        try {
            if (this.bufferedReader != null) {
                bufferedReader.close();
            }
            if (this.bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (this.socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
