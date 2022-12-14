import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket player1;
    private Socket player2;
    private PrintWriter pWriter1;
    private PrintWriter pWriter2;
    private BufferedWriter writer1;
    private BufferedReader reader1;
    private BufferedWriter writer2;
    private BufferedReader reader2;
    private Board gameBoard;

    public ClientHandler(Socket player1, Socket player2) {
        try {
            this.player1 = player1;
            this.player2 = player2;
            this.pWriter1 = new PrintWriter(new OutputStreamWriter(player1.getOutputStream()));
            this.pWriter2 = new PrintWriter(new OutputStreamWriter(player2.getOutputStream()));
            this.writer1 = new BufferedWriter(new OutputStreamWriter(player1.getOutputStream()));
            this.reader1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            this.writer2 = new BufferedWriter(new OutputStreamWriter(player2.getOutputStream()));
            this.reader2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            this.gameBoard = new Board();
            System.out.println("This is the game board.");
            System.out.println(gameBoard.buildBoard());
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
            Integer toChange;
            System.out.println("[" + this + "] " + "ClientHandler is running.");
            System.out.println("[" + this + "] '" + reader1.readLine() + "' and '" + reader2.readLine() + "' are the players. Sending them the board.");
            pWriter1.write(gameBoard.buildBoard());
            pWriter1.flush();
            pWriter2.write(gameBoard.buildBoard());
            pWriter2.flush();
            System.out.println("[" + this + "] " + "Game board sent.");
            while(!gameBoard.gameWon()) {
                if(gameBoard.getTurn()) {
                    pWriter1.println("PLAYER1's TURN");
                    pWriter2.println("PLAYER1's TURN");
                    pWriter1.flush();
                    pWriter2.flush();
//                    System.out.println(reader1.readLine());
                    System.out.println("reading the play from player1");
                    toChange = Integer.parseInt(reader1.readLine());
                    System.out.println("read the play from player1");
                    gameBoard.board[(toChange-1)/3][(toChange-1)%3] = 'x';
                } else {
                    pWriter1.println("PLAYER2's TURN");
                    pWriter2.println("PLAYER2's TURN");
                    pWriter1.flush();
                    pWriter2.flush();
//                    System.out.println(reader2.readLine());
                    toChange = Integer.parseInt(reader2.readLine());
                    gameBoard.board[(toChange-1)/3][(toChange-1)%3] = 'o';
                }
                System.out.println(gameBoard.buildBoard());
                gameBoard.changeTurn();
            }
            pWriter1.write(".exit");
            pWriter2.write(".exit");
            pWriter1.flush();
            pWriter2.flush();
        } catch (IOException e) {
            System.out.println("IOEXCEPTION IN CLIENT-HANDLER RUN.");
            e.printStackTrace();
            closeEverything();
        }
    }
}
