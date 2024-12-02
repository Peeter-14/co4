package nye;

import java.util.Random;

public class AIPlayer extends Player {

    public AIPlayer(String color) {
        super("AI", color);
    }

    public int chooseColumn(Board board) {
        Random random = new Random();
        int col;
        do {
            col = random.nextInt(board.getColumns());
        } while (!board.canDrop(col));
        return col;
    }
}

