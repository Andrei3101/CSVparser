import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateLog {

    static void getLog() throws IOException {

        Logger logger = Logger.getLogger(ClientReader.class.getName());

        // Create an instance of FileHandler that writes log to a file called
        // clientData.log. Each new message will be appended at the end of the log file.
        FileHandler fileHandler = new FileHandler("clientData.log", true);
        logger.addHandler(fileHandler);

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Information message. Number of Records Received: " + ClientReader.nrReceived);
            logger.info("Information message. Number of Records Successfully Added: " + ClientReader.nrSuccessful);
        }
        // Failed records are shown as a Warning
        if (logger.isLoggable(Level.WARNING)) {
            logger.warning("WARNING. Number of Records Failed: " + ClientReader.nrFailed);
        }
    }
}