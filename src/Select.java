        import java.sql.DriverManager;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;


public class Select {

    private Connection connect() {

        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/Louise/IdeaProjects/Databaser1/db/Mobilepay";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void selectKunder(){
        String kunder = "SELECT Navn FROM Kunder";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(kunder)){

            System.out.println("\u001B[1m" + "Liste over kunder:" + "\u001b[0m");

            // loop through the result set
            while (rs.next()) {
                System.out.println (rs.getString("Navn")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void selectOverfoersler(){
        String overfoersler = "SELECT Beløb FROM Overførsler";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(overfoersler)){

            System.out.println("\u001B[1m" + "Liste over overførsler:" + "\u001b[0m");

            // loop through the result set
            while (rs.next()) {
                System.out.println (rs.getString("Beløb")
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

}}