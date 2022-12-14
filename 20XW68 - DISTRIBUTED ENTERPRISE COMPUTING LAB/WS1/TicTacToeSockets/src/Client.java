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
        System.out.println("Enter the lobby code:");
        Client client = new Client(new Socket("localhost", 65456), sc.nextLine());
        // this.username is actually the lobby code till the this.sendMessage() method
        // is called and the user enters their name
        client.writer.write(client.username);
        client.writer.newLine();
        client.writer.flush();
        client.listenForOpponent();
        client.sendToOpponent();
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

    public void listenForOpponent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket.isConnected()) {
                        synchronized (this) {
                            String temp = reader.readLine();
                            System.out.println(temp);
                            if (temp.equalsIgnoreCase("YOUR TURN")) {
                            }
                            if (temp.equalsIgnoreCase("THEIR TURN")) {
                            }
                            if (temp.equalsIgnoreCase(".exit")) {
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    closeEverything(socket, writer, reader);
                }
            }
        }).start();
    }

    public void sendToOpponent() {
        try {
            Scanner sc = new Scanner(System.in);
            synchronized (this) {
                System.out.println("Enter your name to let your opponent know who you are:");
                String message = sc.nextLine();
                this.username = message;
                writer.write(message);
                writer.newLine();
                writer.flush();
                System.out.println("Name sent.");
                while (this.socket.isConnected()) {
                    String temp = sc.nextLine();
                    if (temp.equalsIgnoreCase(".exit")) {
                        break;
                    }
                    writer.write(temp);
                    writer.newLine();
                    writer.flush();
                }
            }
            System.out.println("END OF GAME. CLOSING CLIENT.");
            sc.close();
        } catch (IOException e) {
            closeEverything(socket, writer, reader);
        }
    }
}
