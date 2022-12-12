import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private String username;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to be known as ?");
        Client client = new Client(new Socket("localhost", 65456), sc.nextLine());
        client.sendMessage();
        client.closeEverything(client.socket, client.writer, client.reader);
        sc.close();
    }

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, writer, reader);
        }
    }

    public void closeEverything(Socket socket, BufferedWriter writer, BufferedReader reader) {
        try {
            if (socket != null)
                socket.close();
            if (writer != null)
                writer.close();
            if (reader != null)
                reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            while (true) {
                System.out.println("I WILL SEND A MESSAGE NOW TO CLIENT HANDLER.");
                writer.write(this.username);
                writer.newLine();
                writer.flush();
                System.out.println("SENT MY USERNAME.");
                System.out.println("The lobby code is: " + reader.readLine());
            }
        } catch (IOException e) {
            closeEverything(socket, writer, reader);
        }
    }
}
