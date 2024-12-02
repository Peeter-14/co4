package nye;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(String player1Name, String player2Name, int rows, int columns) {
        this.board = new Board(rows, columns);
        this.player1 = new Player(player1Name, "Yellow");
        this.player2 = new AIPlayer("Red");
        this.currentPlayer = this.player1;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getColor() + ")");
            int column;

            if (currentPlayer instanceof AIPlayer) {
                column = ((AIPlayer) currentPlayer).chooseColumn(board);
                System.out.println("AI chose column: " + (char) ('a' + column));
            } else {
                System.out.print("Enter column (a-g): ");
                char colChar = scanner.next().charAt(0);
                column = colChar - 'a';
            }

            if (board.dropDisc(column, currentPlayer.getColor())) {
                if (board.checkWin(currentPlayer.getColor())) {
                    board.printBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Column is full, try again.");
            }
        }

        scanner.close();
    }
}

