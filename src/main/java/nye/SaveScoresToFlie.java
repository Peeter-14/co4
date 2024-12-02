package nye;

import java.sql.*;
import java.io.*;
import java.util.*;

public class SaveScoresToFlie {

    // Függvény az adatbázisból történő adatlekérdezéshez és fájlba íráshoz
    public static void saveScoresToFile(String dbName, String fileName) {
        // Adatbázis kapcsolat inicializálása
        try (Connection conn = DriverManager.getConnection( dbName)) {
            // SQL lekérdezés: minden adat lekérdezése a scores táblából
            String query = "SELECT * FROM scores";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query);
                 BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

                // Fejléc írása (oszlopnevek)
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                List<String> columnNames = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }
                writer.write(String.join(",", columnNames));
                writer.newLine();

                // Az adatok írása fájlba
                while (rs.next()) {
                    List<String> row = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getString(i));
                    }
                    writer.write(String.join(",", row));
                    writer.newLine();
                }

                System.out.println("Adatok sikeresen mentve a fájlba: " + fileName);

            } catch (SQLException | IOException e) {
                System.err.println("Hiba történt a fájlba íráskor: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Hiba történt az adatbázis kapcsolatban: " + e.getMessage());
        }
    }

}

