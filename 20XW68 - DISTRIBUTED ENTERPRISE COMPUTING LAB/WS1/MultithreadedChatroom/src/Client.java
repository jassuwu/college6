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
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
        } catch (Exception e) {
            e.printStackTrace();
            closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner sc = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = sc.nextLine();
                bufferedWriter.write("[" + this.username + "]: " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
            closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromChatRoom;
                while (socket.isConnected()) {
                    try {
                        msgFromChatRoom = bufferedReader.readLine();
                        System.out.println(msgFromChatRoom);
                    } catch (IOException e) {
                        e.printStackTrace();
                        closeEverything(socket, bufferedReader, bufferedWriter);

                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (this.bufferedReader != null) {
                bufferedReader.close();
            }
            if (this.bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (this.socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What will you be known as? ");
        String username = sc.nextLine();
        Socket socket = new Socket("localhost", 65456);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();
        sc.close();
    }
}
