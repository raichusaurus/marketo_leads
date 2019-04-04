// main method for project

public class DeDuplicate {

    public static void main(String[] args) {
        String filepath = System.getProperty("user.dir") + "\\";
        if (args.length < 1) {
            System.out.println("Must provide .json file");
            return;
        }
        String filename = args[0];
        Leads leads = new Leads();
        leads.getNewLeads(filepath + filename);
        leads.storeCurrentLeads(filepath + filename);
    }
}
