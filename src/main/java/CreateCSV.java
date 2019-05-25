import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ANDY on 24-May-19.
 */

class CreateCSV {

    static FileWriter createFile() throws IOException {
        String fileName = new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date());

        return new FileWriter("bad-data-"+fileName);
    }

}