import java.io.FileWriter;
import java.io.IOException;

// simple class that writes our list of leads to a JSON file

public class RecordWriter {

    public void writeNewLeads(String filepath, Leads leads) {

        try (FileWriter file = new FileWriter(filepath)) {

            file.write(leads.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
