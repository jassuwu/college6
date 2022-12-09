import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.username = username;
            out.writeObject(username);
        } catch (IOException e) {
            e.printStackTrace();
            closeEverything(socket, in, out);
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 65456);
        System.out.println("What's yo name, dude?");
        Client client = new Client(socket, sc.nextLine());
        while(true) {
            System.out.println("Wha'd'ya wanna do?");
            System.out.println("1. Store.");
            System.out.println("2. Retrieve.");
            System.out.println("3. Exit.");
            Integer key = Integer.parseInt(sc.nextLine());
            switch(key) {
                case 1:
                    client.out.writeObject("THEY WANNA STORE VRO.");
                    client.out.flush();
                    System.out.println(client.in.readObject());
                    break;
                case 2:
                    client.out.writeObject("THEY WANNA RETRIEVE VRO.");
                    client.out.flush();
                    System.out.println(client.in.readObject());
                    break;
                case 3:
                    client.out.writeObject("THEY LEFT.");
                    System.out.println(client.in.readObject());
                    socket.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

}
