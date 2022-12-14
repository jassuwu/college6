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
    private Board gameBoard;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the lobby code:");
        Client client = new Client(new Socket("localhost", 65456), sc.nextLine());
        // this.username is actually the lobby code till the this.sendMessage() method
        // is called and the user enters their name
        System.out.println("writing this to CH: " + client.username);
        client.writer.write(client.username);
        client.writer.newLine();
        client.writer.flush();
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

    public String buildBoardFromOneLineString(String line) {
        String board = "";
        System.out.println("line: " + line);
        String[] boardArray = line.split("|");
        System.out.println("boardArray" + boardArray);
        for (String s : boardArray)
            board += s + "|";
        return board;
    }

    public void sendMessage() {
        try {
            System.out.println("Enter your name to let your opponent know who you are:");
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
            this.username = message;
            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println("Name sent.");
            System.out.println("Server response: " + buildBoardFromOneLineString(reader.readLine()));
            sc.close();
        } catch (IOException e) {
            closeEverything(socket, writer, reader);
        }
    }
}
