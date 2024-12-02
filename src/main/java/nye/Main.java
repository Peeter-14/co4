package nye;
import java.io.IOException;
import java.util.Scanner;
import  static nye.FileHandler.saveGame;
import static nye.SaveScoresToFlie.saveScoresToFile;

public class Main {
    public static void main(String[] args) {
        System.out.println("Adja meg a nevét: ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        Game game = new Game(playerName, "AI", 6, 7);
        game.start();
        //saveScoresToFile( playerName , "scores.csv");
        //saveGame(Board(6, 7),"scores.csv");

    }
}
