import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.username = (String) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            closeEverything(socket, in, out);
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                System.out.println(in.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
            closeEverything(socket, in, out);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeEverything(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        try {
            if(socket != null) {
                socket.close();
            }
            if(in != null) {
                in.close();
            }
            if(out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
