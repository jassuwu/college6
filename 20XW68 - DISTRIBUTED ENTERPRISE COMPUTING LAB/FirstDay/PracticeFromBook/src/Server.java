import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server (ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while(!this.serverSocket.isClosed()){
                System.out.println("Listening for connections...");
                Socket connection = this.serverSocket.accept();
                System.out.println(connection.getInetAddress().getHostName() + " connected to the server.");
                ClientHandler clientHandler = new ClientHandler(connection);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            if(this.serverSocket != null) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
