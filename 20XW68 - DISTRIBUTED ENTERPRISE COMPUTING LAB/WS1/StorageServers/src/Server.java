import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;

    }

    public void startServer() {
        try {
            while (!this.serverSocket.isClosed()) {
                System.out.println("[SERVER] Listening for connections...");
                Socket acceptedSocket = serverSocket.accept();
                System.out.println(acceptedSocket + " connected to the server.");
                System.out.println("[SERVER] Made new storage for " + acceptedSocket);
                ClientHandler clientHandler = new ClientHandler(acceptedSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("THIS STACKTRACE IS PRINTED BECAUSE I PRINTED IT.");
            e.printStackTrace();
            this.closeServer();
        }
    }

    public void closeServer() {
        try {
            if (this.serverSocket != null) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(65456);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
