public class LeadTestDrive
{
    public static void main(String[] args) {
        testFile("emptyleads.json");
        testFile("singlelead.json");
        testFile("leads.json");
    }

    public static void testFile(String filename) {
        String filepath = System.getProperty("user.dir") + "\\src\\JSON_Files\\";
        Leads leads = new Leads();
        leads.getNewLeads(filepath + filename);
        leads.storeCurrentLeads(filepath + "new" + filename);
    }
}
