import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket;
    private HashMap<String, Socket> lobbyList;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(65456);
        Server server = new Server(serverSocket);
        server.startServer();
        server.closeServer();
    }

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.lobbyList = new HashMap<>();
    }

    public void startServer() {
        try {
            while (true) {
                System.out.println("[SERVER] Listening for new connections...");
                Socket conn = this.serverSocket.accept();
                System.out.println(conn.toString() + " connected to the server. They are added to the waiting list.");
                BufferedReader brOfConn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String[] inString = brOfConn.readLine().split(" ");
                String clientName = inString[0];
                String lobbyCode = inString[1];
                if (lobbyList.containsKey(lobbyCode)) {
                    // Start a new Thread for a Socket prev stored and the newly arrived one and
                    // remove the lobbyCode.
                    System.out.println("New game started and '" + lobbyCode + "' is now free for use.");
                    new Thread(new ClientHandler(lobbyList.get(lobbyCode), conn)).start();
                    lobbyList.remove(lobbyCode);
                } else {
                    // Otherwise add to the list of waiting sockets.
                    lobbyList.put(lobbyCode, conn);
                    System.out.println(
                            "Added '" + clientName + "' with the lobbyCode of '" + lobbyCode
                                    + "' to the list of waiters. ");
                }
            }
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
