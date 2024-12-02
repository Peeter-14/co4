package nye;
import java.sql.SQLException;
import java.sql.*;
import java.io.*;
import java.util.*;

public class DatabaseHandler {

    private static final String DB_URL = "jdbc:sqlite:connect4.db";  // Use SQLite for simplicity
    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS scores (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "player_name TEXT NOT NULL, " +
                    "wins INTEGER NOT NULL DEFAULT 0" +
                    ")INTO OUTFILE 'c:/Users/vatip/IdealProjects/co/adatok.csv';"
            ;



    private static final String INSERT_SCORE_QUERY =
            "INSERT INTO scores (player_name, wins) VALUES (?, ?);";

    private static final String UPDATE_SCORE_QUERY =
            "UPDATE scores SET wins = wins + 1 WHERE player_name = ?;";

    private static final String GET_HIGHEST_SCORE_QUERY =
            "SELECT player_name, wins FROM scores ORDER BY wins DESC LIMIT 1;";

    // Initialize the database
    public static void initializeDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {
            stmt.execute(CREATE_TABLE_QUERY);
        }
    }

    // Save or update player score
    public static void saveOrUpdateScore(String playerName, boolean isWin) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            if (isWin) {
                // Check if player already exists
                String checkExistenceQuery = "SELECT COUNT(*) FROM scores WHERE player_name = ?";
                try (PreparedStatement checkStmt = connection.prepareStatement(checkExistenceQuery)) {
                    checkStmt.setString(1, playerName);
                    ResultSet rs = checkStmt.executeQuery();
                    int count = rs.getInt(1);

                    if (count > 0) {
                        // Update score
                        try (PreparedStatement updateStmt = connection.prepareStatement(UPDATE_SCORE_QUERY)) {
                            updateStmt.setString(1, playerName);
                            updateStmt.executeUpdate();
                        }
                    } else {
                        // Insert new player
                        try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_SCORE_QUERY)) {
                            insertStmt.setString(1, playerName);
                            insertStmt.setInt(2, 1);  // Starting wins as 1
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }
        }
    }

    // Retrieve the highest score
    public static String getHighestScore() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = connection.prepareStatement(GET_HIGHEST_SCORE_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("player_name") + " with " + rs.getInt("wins") + " wins.";
            } else {
                return "No players yet.";
            }
        }
    }
}

