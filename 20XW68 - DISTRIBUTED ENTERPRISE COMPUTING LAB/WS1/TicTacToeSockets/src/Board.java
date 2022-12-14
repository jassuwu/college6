import java.util.Arrays;

public class Board {
    private char[][] board;
    private Boolean currentTurn;

    public Board() {
        this.board = new char[3][3];
        for (char[] row : board) {
            Arrays.fill(row, 'v');
        }
        currentTurn = true;
    }

    public String buildBoard() {
        String board = "";
        for (char[] row : this.board) {
            board += "|";
            for (char el : row) {
                board += el + "|";
            }
            board += "\n";
        }
        return board;
    }

    public void changeTurn() {
        this.currentTurn = !currentTurn;
    }

    public boolean gameWon() {
        int[][] winning_pos = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 },
                { 1, 5, 9 }, { 3, 5, 7 } };
        for (int[] w : winning_pos) {
            if (this.board[w[0] / 3][(w[0] / 3) % 4] == 'X' && this.board[w[1] / 3][(w[1] / 3) % 4] == 'X'
                    && this.board[w[2] / 3][(w[2] / 3) % 4] == 'X') {
                return true;
            }
            if (this.board[w[0] / 3][w[0] % 3] == 'O' && this.board[w[1] / 3][(w[1] / 3) % 4] == 'O'
                    && this.board[w[2] / 3][(w[2] / 3) % 4] == 'O') {
                return true;
            }
        }
        return false;
    }
}
