import java.sql.*;

/**
 * Created by ANDY on 21-May-19.
 */

class ClientDB {

    // Get the connection to the SQLite DB
    static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + ClientReader.DB_NAME;

        return DriverManager.getConnection(url);
    }

    // Creating the table in the SQLite DB
    static void createTable() throws SQLException, ClassNotFoundException {

        Connection conTable = getConnection();
        Statement stmt = conTable.createStatement();

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + ClientReader.TABLE_NAME + " ( \n"
                + " id integer NOT NULL PRIMARY KEY,\n"
                + " A text NOT NULL,\n"
                + " B text NOT NULL,\n"
                + " C text NOT NULL,\n"
                + " D text NOT NULL,\n"
                + " E text NOT NULL,\n"
                + " F text NOT NULL,\n"
                + " G text NOT NULL,\n"
                + " H text NOT NULL,\n"
                + " I text NOT NULL,\n"
                + " J text NOT NULL \n"
                + ");";

        stmt.execute(sql);
        stmt.close();
    }

    // Selecting valid records from the table
    static void selectAll() throws ClassNotFoundException, SQLException {
        // SQL query for selecting records from DB
        String sql = "SELECT * FROM clients ;";

        Connection conS = getConnection();
        Statement stmt = conS.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        // loop through the result set
        while (rs.next()) {

            System.out.println(rs.getInt("id") + "\t" +
                    rs.getString("A") + "\t" +
                    rs.getDouble("B") + "\t" +
                    rs.getDouble("C") + "\t" +
                    rs.getDouble("D") + "\t" +
                    rs.getDouble("E") + "\t" +
                    rs.getDouble("F") + "\t" +
                    rs.getDouble("G") + "\t" +
                    rs.getDouble("H") + "\t" +
                    rs.getDouble("I") + "\t" +
                    rs.getDouble("J") + "\t");
        }

    }

}