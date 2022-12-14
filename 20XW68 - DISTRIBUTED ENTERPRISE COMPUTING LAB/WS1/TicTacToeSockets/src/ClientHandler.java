import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket player1;
    private Socket player2;
    private PrintWriter pWriter1;
    private PrintWriter pWriter2;
    private BufferedReader reader1;
    private BufferedReader reader2;
    private Board gameBoard;

    public ClientHandler(Socket player1, Socket player2) {
        try {
            this.player1 = player1;
            this.player2 = player2;
            this.pWriter1 = new PrintWriter(new OutputStreamWriter(player1.getOutputStream()));
            this.pWriter2 = new PrintWriter(new OutputStreamWriter(player2.getOutputStream()));
            this.reader1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
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
            synchronized (this) {
                System.out.println("[" + this + "] " + "ClientHandler is running.");
                System.out.println("[" + this + "] '" + reader1.readLine() + "' and '" + reader2.readLine()
                        + "' are the players. Sending them the board.");
                pWriter1.write("THE BOARD:\n" + gameBoard.buildBoard());
                pWriter2.write("THE BOARD:\n" + gameBoard.buildBoard());
                pWriter1.flush();
                pWriter2.flush();
                System.out.println("[" + this + "] " + "Game board sent.");
            }
            while (!gameBoard.gameWon()) {
                synchronized (this) {

                    if (gameBoard.getTurn()) {
                        pWriter1.println("YOUR TURN\nEnter the box number: ");
                        pWriter2.println("THEIR TURN");
                        pWriter1.flush();
                        pWriter2.flush();
                        toChange = Integer.parseInt(reader1.readLine());
                        gameBoard.board[(toChange - 1) / 3][(toChange - 1) % 3] = 'X';
                        pWriter1.write(gameBoard.buildBoard());
                        pWriter2.write(gameBoard.buildBoard());
                        pWriter1.flush();
                        pWriter2.flush();
                    } else {
                        pWriter2.println("YOUR TURN\nEnter the box number: ");
                        pWriter1.println("THEIR TURN");
                        pWriter1.flush();
                        pWriter2.flush();
                        toChange = Integer.parseInt(reader2.readLine());
                        gameBoard.board[(toChange - 1) / 3][(toChange - 1) % 3] = 'O';
                        pWriter1.write(gameBoard.buildBoard());
                        pWriter2.write(gameBoard.buildBoard());
                        pWriter1.flush();
                        pWriter2.flush();
                    }
                    // print the board the board in the session log
                    System.out.println("[" + this + "]\n" + gameBoard.buildBoard());
                    if (gameBoard.gameWon()) {
                        if (gameBoard.getTurn()) {
                            pWriter1.println(
                                    "X WON\ntype .exit to end the game safely, or be a caveman and close it forcefully.");
                            System.out.println("X WON");
                            pWriter2.println(
                                    "O LOST\ntype .exit to end the game safely, or be a caveman and close it forcefully.");
                            pWriter1.flush();
                            pWriter2.flush();
                        } else {
                            pWriter2.println(
                                    "O WON\ntype .exit to end the game safely, or be a caveman and close it forcefully.");
                            System.out.println("O WON");
                            pWriter1.println(
                                    "X LOST\ntype .exit to end the game safely, or be a caveman and close it forcefully.");
                            pWriter1.flush();
                            pWriter2.flush();
                        }
                    }
                    gameBoard.changeTurn();
                }
            }
            System.out.println("[" + this + "] " + "Game over.");

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
