import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ClientReader {

    // Declaring properties file containing app Strings
    static Properties prop;

    // Define variables for the overall results
    static int nrReceived = 0;
    static int nrSuccessful = 0;
    static int nrFailed = 0;

    private static FileWriter csvWriter;

    public static void main(String[] args) {

        try {
            // Loading data from properties file
            InputStream in = new FileInputStream("app.properties");
            prop = new Properties();
            prop.load(in);

            // Create Database and Table for adding clients
            DbConnection.getConnection();
            ClientDB.createTable();

            // Create csv file for writing bad data records with timestamp
            csvWriter = CreateCSV.createFile();

            // Process data written in the given CSV file
            processCSV();

            // close the csv FileWriter
            csvWriter.close();

            // ClientDB.selectAll();

            // write the results into the LOG file
            CreateLog.getLog();

        } catch (ClassNotFoundException e) {
            System.out.println("Error connecting to the Database. Check DB or Table DDL.");
        } catch (SQLException | IOException ex) {
            System.out.println("Error in processing SQL or input files:" + ex.getMessage());
            ex.printStackTrace();
        }

    }

    private static void processCSV() throws IOException, SQLException, ClassNotFoundException {

        Path pathToFile = Paths.get(prop.getProperty("PATH_TO_CSV"));

        // create an instance of BufferedReader using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            // skip the first heading line from the text file
            String line;
            line = br.readLine();

            //read second line
            line = br.readLine();

            // Connecting to DB and setting auto commit to false, in order to improve overall DB
            Connection connect = DbConnection.getConnection();
            connect.setAutoCommit(false);

            // Setting a prepared statement for inserting data into SQLite DB
            String sql_Insert = "INSERT INTO clients(A, B, C, D, E, F, G, H, I, J ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connect.prepareStatement(sql_Insert);

            // loop until all lines from CSV file are read
            while (line != null) {

                //process each row in the CSV file and getting the valid records
                List<String> goodRecord = processLine(line);

                if (goodRecord != null) {

                    // setting values for the PreparedStatement
                    for (int i = 0; i < 10; i++) {
                        ps.setString(i + 1, goodRecord.get(i));
                    }

                    // Using batch increases performance
                    ps.addBatch();

                    // increase number of successfully added clients in DB
                    nrSuccessful++;
                }

                // read next line before looping if end of file reached, line would be null
                line = br.readLine();
            }

            // Submitting batch of commands to the database for execution
            ps.executeBatch();
            connect.commit();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private static List<String> processLine(String line) throws IOException {

        // increase the number of records received
        nrReceived++;

        List<String> goodData = null;

        // use string.split to load a string array with the values from each line of
        // the file, using a comma as the delimiter and a special regex
        List<String> attributes = Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"));

        // checking if the record is valid and corresponds to the number of columns
        if (attributes.size() == 10) {
            boolean badData = false;
            for (String s : attributes) {
                if (s == null || s.isEmpty()) {
                    badData = true;
                    break;
                }
            }

            if (badData) {
                // increase the number of bad records and write them in the CSV bad data file
                nrFailed++;
                csvWriter.append(String.join(",", attributes));
                csvWriter.append("\n");
            } else {
                // We found a valid record
                goodData = attributes;
            }
        } else {
            // increase the number of bad records and write them in the csv file
            nrFailed++;
            csvWriter.append(String.join(",", attributes));
            csvWriter.append("\n");
        }

        // return the valid record or null
        return goodData;
    }

}