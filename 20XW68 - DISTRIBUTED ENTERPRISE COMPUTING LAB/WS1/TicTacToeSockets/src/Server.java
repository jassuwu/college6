import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(65456);
        Server server = new Server(serverSocket);
        server.startServer();
        server.closeServer();
    }

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            Socket acceptedConn = this.serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(acceptedConn);
            new Thread(clientHandler).start();
        } catch (IOException e) {
            closeServer();
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
}
