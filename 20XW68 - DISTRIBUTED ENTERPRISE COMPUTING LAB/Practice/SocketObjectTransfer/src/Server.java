import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int PORT = 65456;

    public static void main(String[] args) throws Exception {
        new Server();
    }

    public Server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Listening on port " + PORT + "...");
        Socket socket = serverSocket.accept();

        // I/O ObjectStreams
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        String messageFromClient = (String) in.readObject();
        System.out.println(messageFromClient);
        String messageToClient = "I GOTCHUR MESSAGE.";
        out.writeObject(messageToClient);

        in.close();
        out.close();
        serverSocket.close();
    }
}
