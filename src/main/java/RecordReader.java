import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

// class that can read in a json file and store the json objects in the Leads class as java objects

public class RecordReader {

    public void readNewLeads(String filePath, Leads leads) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filePath));

            JSONObject records = (JSONObject) obj; // the object holding the array of leads

            JSONArray newLeads = (JSONArray) records.get("leads"); // get the array holding all of the leads
            Iterator<JSONObject> iterator = newLeads.iterator();
            while (iterator.hasNext()) {
                JSONObject newLead = iterator.next(); // grab json object
                Lead leadToAdd = new Lead(newLead); // create new lead java object
                leads.add(leadToAdd); // add the new lead to the Leads class to hold it
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
