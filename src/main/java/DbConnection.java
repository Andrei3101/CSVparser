import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbConnection {

    // Get the connection to the SQLite DB 2
    static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(ClientReader.prop.getProperty("DRIVER"));
        String dbName = ClientReader.prop.getProperty("DB_NAME");
        String url = "jdbc:sqlite:" + dbName;

        return DriverManager.getConnection(url);
    }
}