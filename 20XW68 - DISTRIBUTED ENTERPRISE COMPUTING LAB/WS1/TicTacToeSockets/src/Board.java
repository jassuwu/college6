import java.util.Arrays;

public class Board {
    private char[][] board;
    private Boolean currentTurn;

    public Board() {
        this.board = new char[3][3];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        currentTurn = true;
    }

    public void displayBoard() {
        for (char[] row : board) {
            System.out.print("|");
            for (char el : row) {
                System.out.print(el + "|");
            }
            System.out.println();
        }
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
