import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ANDY on 21-May-19.
 */

class ClientDB {

    // Creating the table in the SQLite DB
    static void createTable() throws SQLException, ClassNotFoundException {

        Connection conTable = DbConnection.getConnection();
        Statement stmt = conTable.createStatement();

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + ClientReader.prop.getProperty("TABLE_NAME") + " ( \n"
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
        String sql = "SELECT * FROM "+ ClientReader.prop.getProperty("TABLE_NAME") ;

        Connection conS = DbConnection.getConnection();
        Statement stmt = conS.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // loop through the result set
        while (rs.next()) {

            System.out.println(rs.getInt("id") + "\t" +
                    rs.getString("A") + "\t" +
                    rs.getString("B") + "\t" +
                    rs.getString("C") + "\t" +
                    rs.getString("D") + "\t" +
                    rs.getString("E") + "\t" +
                    rs.getString("F") + "\t" +
                    rs.getString("G") + "\t" +
                    rs.getString("H") + "\t" +
                    rs.getString("I") + "\t" +
                    rs.getString("J") + "\t");
        }

    }

}