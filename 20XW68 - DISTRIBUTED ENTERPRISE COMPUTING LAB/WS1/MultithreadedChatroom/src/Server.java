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
                System.out.println(acceptedSocket.toString() + " connected to the server.");
                ClientHandler clientHandler = new ClientHandler(acceptedSocket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.closeSocket();
        }
    }

    public void closeSocket() {
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
