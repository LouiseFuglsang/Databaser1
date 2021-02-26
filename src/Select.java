import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Select {


    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/Mobilepay";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void selectKunder() {
        String kunder = "SELECT KunderId, Navn, Telefon, Adresse, Kortnumre, Registreringsdato FROM Kunder";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(kunder)) {

            System.out.println("\u001B[1m" + "Liste over kunder:" + "\u001b[0m");

            while (rs.next()) {
                System.out.println(rs.getInt("KunderId") + ". " +
                        rs.getString("Navn") + " * " + "Tlf.nr: " +
                        rs.getInt("Telefon") + " * " +
                        rs.getString("Adresse") + " * " + "Konto nr: " +
                        rs.getInt("Kortnumre") + " * " + "Konto oprettet: " +
                        rs.getString("Registreringsdato")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void selectOverfoersler() {
        String overfoersler = "SELECT OverførslerId, KunderId, Dato, Beløb FROM Overførsler JOIN Kunder USING(KunderId)";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(overfoersler)) {

            System.out.println("\u001B[1m" + "Liste over overførsler:" + "\u001b[0m");

            while (rs.next()) {
                System.out.println(rs.getInt("OverførslerId") + ". KundeId: " +
                                   rs.getString("KunderId") + " * " +
                                   rs.getDouble("Beløb") + "kr. * " +
                                   rs.getString("Dato")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        Select app = new Select();
        app.selectKunder();
        System.out.println("\n");
        app.selectOverfoersler();
    }
}