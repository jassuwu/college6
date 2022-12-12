import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class ClientHandler implements Runnable {

    private ArrayList<UUID> lobbies;
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private UUID lobbyCode;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new UUID(1, 1);
            this.lobbyCode = UUID.randomUUID();
            this.lobbies = new ArrayList<>();
            this.lobbies.add(this.lobbyCode);
        } catch (IOException e) {
            System.out.println("IO EXCEPTION AT CLIENTHANDLER CONSTRUCTOR.");
            closeEverything(socket, writer, reader);
        }
    }

    public void closeEverything(Socket socket, BufferedWriter writer, BufferedReader reader) {
        try {
            if (socket != null)
                socket.close();
            if (writer != null)
                writer.close();
            if (reader != null)
                reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String clientName = reader.readLine();
                System.out.println("[" + this.lobbyCode.toString() + "] Sending lobby code to " + clientName + ".");
                writer.write(this.lobbyCode.toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("IOEXCPETION IN CLIENTHANDLER RUN.");
            closeEverything(socket, writer, reader);
        }
    }
}
