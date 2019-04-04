import org.json.simple.JSONAware;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// class to store all of the lead records
// implement JSONAware to help with storing data as json objects

public class Leads implements JSONAware {

    private List leads; // using List in case I decide to step change the type of list later.
    private Logger logger;
    private FileHandler fileHandler;

    public Leads() {
        leads = new ArrayList<Lead>(); // create the list
        logger = Logger.getLogger("Lead log"); // create the log file for adding leads
        try {

            String filepath = System.getProperty("user.dir") + "\\";
            fileHandler = new FileHandler(filepath + "LeadLog.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.info("New Leads list created");
            logger.info(System.getProperty("user.dir"));

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this method adds leads to the list,
    // checking for(and discarding) duplicates(same email or same _id) in the process
    public boolean add(Lead newLead) {
        logger.info("Adding new lead to Leads list:\n" + newLead.toString());
        for (int i = 0; i < leads.size(); i++) {
            Lead currentLead = (Lead) leads.get(i);
            if (newLead.equals(currentLead)) {
                logger.info("FOUND DUPLICATE");
                if (newLead.getDate().compareTo(currentLead.getDate()) < 0) {
                    logger.info("New lead discarded."); // the new lead is out of date; ignore it
                    return false;
                }
                logger.info("New lead replacing duplicate:\n" + currentLead.toString());
                leads.set(i, newLead); // the new lead supercedes a previous entry, replace the old entry
                return true;
            }
        }

        logger.info("No duplicates found for new lead");
        leads.add(newLead); // add the new lead to the end of the list
        return true;
    }

    // method that creates a new RecordReader that will read in a json file and store them in the list.
    public void getNewLeads(String filepath) {
        RecordReader reader = new RecordReader();
        reader.readNewLeads(filepath, this);
    }

    // method that takes the current list of leads and stores the in a json file
    public void storeCurrentLeads(String filepath) {
        RecordWriter writer = new RecordWriter();
        writer.writeNewLeads(filepath, this);
    }

    // required for JSONAWare
    // method to make moving this list into a json file less of a headache for RecordWriter
    public String toJSONString() {
        String output = "{\"leads\":[ \n";
        int leadSize = leads.size();
        int i;
        for (i = 0; i < leadSize - 1; i++) {
            output += ((Lead) leads.get(i)).toJSONString() + ",\n";
        }
        if (i < leadSize) {
            output += ((Lead) leads.get(i)).toJSONString(); // don't want a comma after the last object
        }

        output += "]\n}";
        return output;
    }
}
