import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        new Client();
    }

    public Client() throws Exception {
        Socket socket = new Socket("localhost", Server.PORT);

        // I/O ObjectStreams
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        String messageToServer = "bro valorant is dogwater.";
        out.writeObject(messageToServer);
        String messageFromServer = (String) in.readObject();
        System.out.println(messageFromServer);
        System.out.println("OK THEN.");

        in.close();
        out.close();
        socket.close();
    }
}
