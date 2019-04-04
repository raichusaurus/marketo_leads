import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

// class for storing the JSON lead objects
// implement JSONAware to help with storing data as json objects

public class Lead implements JSONAware{

    private String _id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String entryDate;


    // extract each field from JSON object to create Lead java object
    public Lead(JSONObject jsonLead) {
        _id = (String) jsonLead.get("_id");
        email = (String) jsonLead.get("email");
        firstName = (String) jsonLead.get("firstName");
        lastName = (String) jsonLead.get("lastName");
        address = (String) jsonLead.get("address");
        entryDate = (String) jsonLead.get("entryDate");
    }

    public Lead(String _id, String email, String firstName, String lastName,
                String address, String entryDate) {
        this._id = _id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.entryDate = entryDate;
    }

    public String get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return entryDate;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Lead)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Lead lead = (Lead) obj;

        if (this._id.equals(lead.get_id()) || this.email.equals(lead.getEmail())) {
            return true;
        }

        return false;
    }

    // turn the object into a string the can be represented as a JSON object
    public String toString() {
        String printer = "{\n";
        printer += "\"_id\": \"" + _id + "\",\n";
        printer += "\"email\": \"" + email + "\",\n";
        printer += "\"firstName\": \"" + firstName + "\",\n";
        printer += "\"lastName\": \"" + lastName + "\",\n";
        printer += "\"address\": \"" + address + "\",\n";
        printer += "\"entryDate\": \"" + entryDate + "\"\n}";

        return printer;
    }

    // required for JSONAware, making the move to JSON object easy
    public String toJSONString() {
        return toString();
    }
}
