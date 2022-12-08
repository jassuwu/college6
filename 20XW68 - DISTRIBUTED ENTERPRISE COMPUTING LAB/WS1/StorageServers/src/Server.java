import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!this.serverSocket.isClosed()) {
                Socket acceptedSocket = serverSocket.accept();
                System.out.println(acceptedSocket + " connected to the server.");
                ClientHandler clientHandler = new ClientHandler(acceptedSocket);
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
