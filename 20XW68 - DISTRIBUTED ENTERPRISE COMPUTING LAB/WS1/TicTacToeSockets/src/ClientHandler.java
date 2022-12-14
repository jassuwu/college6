import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket player1;
    private Socket player2;
    private BufferedWriter writer1;
    private BufferedReader reader1;
    private BufferedWriter writer2;
    private BufferedReader reader2;
    private Board gameBoard;

    public ClientHandler(Socket player1, Socket player2) {
        try {
            this.player1 = player1;
            this.player2 = player2;
            this.writer1 = new BufferedWriter(new OutputStreamWriter(player1.getOutputStream()));
            this.reader1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            this.writer2 = new BufferedWriter(new OutputStreamWriter(player2.getOutputStream()));
            this.reader2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            this.gameBoard = new Board();
            System.out.println("This is the game board.");
            gameBoard.displayBoard();
        } catch (IOException e) {
            System.out.println("IO EXCEPTION AT CLIENTHANDLER CONSTRUCTOR.");
            e.printStackTrace();
            closeEverything();
        }
    }

    public void closeEverything() {
        try {
            if (player1 != null)
                player1.close();
            if (player2 != null)
                player2.close();
            if (writer1 != null)
                writer1.close();
            if (writer2 != null)
                writer2.close();
            if (reader1 != null)
                reader1.close();
            if (reader2 != null)
                reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("[" + this + "] " + "ClientHandler is running.");
            String p1Name = reader1.readLine();
            String p2Name = reader2.readLine();
            System.out.println("[" + this + "] " + p1Name + "and " + p2Name + " are playing.");
            writer1.write("Confirmation to p1.");
            writer1.newLine();
            writer1.flush();
            writer2.write("Confirmation to p2.");
            writer2.newLine();
            writer2.flush();
            System.out.println("[" + this + "] " + "Confirmation sent.");
        } catch (IOException e) {
            System.out.println("IOEXCPETION IN CLIENTHANDLER RUN.");
            e.printStackTrace();
            closeEverything();
        }
    }
}
