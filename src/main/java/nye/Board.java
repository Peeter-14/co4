package nye;

public class Board {
    private final String[][] board;
    private final int rows;
    private final int columns;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = " ";
            }
        }
    }

    public boolean canDrop(int col) {
        return board[0][col].equals(" ");
    }

    public boolean dropDisc(int col, String color) {
        for (int row = rows - 1; row >= 0; row--) {
            if (board[row][col].equals(" ")) {
                board[row][col] = color;
                return true;
            }
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    public boolean checkWin(String discColor) {
        // Ellenőrizzük a győzelmet (függőleges, vízszintes, átlós)
        return checkHorizontal(discColor) || checkVertical(discColor) || checkDiagonal(discColor);
    }

    private boolean checkHorizontal(String discColor) {
        // Horizontális ellenőrzés
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col].equals(discColor) && board[row][col + 1].equals(discColor) &&
                        board[row][col + 2].equals(discColor) && board[row][col + 3].equals(discColor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(String discColor) {
        // Vertikális ellenőrzés
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows - 3; row++) {
                if (board[row][col].equals(discColor) && board[row + 1][col].equals(discColor) &&
                        board[row + 2][col].equals(discColor) && board[row + 3][col].equals(discColor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(String discColor) {
        // Balra lefele átlós ellenőrzés
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col].equals(discColor) && board[row + 1][col + 1].equals(discColor) &&
                        board[row + 2][col + 2].equals(discColor) && board[row + 3][col + 3].equals(discColor)) {
                    return true;
                }
            }
        }
        // Jobbra lefele átlós ellenőrzés
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 3; col < columns; col++) {
                if (board[row][col].equals(discColor) && board[row + 1][col - 1].equals(discColor) &&
                        board[row + 2][col - 2].equals(discColor) && board[row + 3][col - 3].equals(discColor)) {
                    return true;
                }
            }
        }return false;
    }



    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }


    public String getCell(int i, int j) {
        return getCell(5,0);
    }

    public void setCell(int row, int col, String s) {
    }
}
