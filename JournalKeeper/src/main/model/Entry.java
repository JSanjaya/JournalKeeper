package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Entry object and implements a Writable interface
// to convert the object's variables to the Json file
// Entries have a Date and a String of an entry to go along with it

public class Entry implements Writable {
    String entry;
    Date date;

    public Entry(Date date, String entry) {
        this.date = date;
        this.entry = entry;
    }

    // EFFECTS: retrieves the day of the date
    public Date getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: sets the date for the entry
    public void setDate(Date date) {
        this.date = date;
    }

    // EFFECTS: retrieves the day of the date
    public String getEntry() {
        return entry;
    }

    // MODIFIES: this
    // EFFECTS: sets the entry
    public void setEntry(String entry) {
        this.entry = entry;
    }

    // EFFECTS: converts a Entry to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date.toJson());
        json.put("entry", entry);
        return json;
    }


}
