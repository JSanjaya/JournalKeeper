package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a Journal object and implements a Writable interface
// to convert the object's variables to the Json file
// Journals contain the name of the author as well as their list of entries

public class Journal implements Writable {
    ArrayList<Entry> entries;
    String name;

    // EFFECTS: constructs new Journal object with a name
    public Journal(String name) {
        this.name = name;
        this.entries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // EFFECTS: adds entry to list
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    // EFFECTS: removes entry from list
    public void removeEntry(Entry entry) {
        entries.remove(entry);
    }

    // MODIFIES: author
    // EFFECTS: views the name of the journal holder and the journal's number of entries
    public String viewAuthor() {
        String author = name + "'s Journal";
        int count = 0;
        for (Entry e : entries) {
            count++;
        }
        author = author.concat(" currently has " + count + " entries");
        return author;
    }

    // MODIFIES: journalPage
    // EFFECTS: views the journal entries in vertical order
    public String viewEntries() {
        String date;
        String entry;
        String journalPage = "";
        for (Entry e : entries) {
            date = (e.getDate().getDay() + "/" + e.getDate().getMonth() + "/" + e.getDate().getYear());
            entry = (e.getEntry());
            journalPage = journalPage.concat("\n" + date + "\n" + entry + "\n");
        }
        return journalPage;
    }

    // EFFECTS: returns list of entries
    public List<Entry> getEntries() {
        return entries;
    }

    // EFFECTS: converts a Journal to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        JSONArray entryArray = new JSONArray();
        for (Entry e : entries) {
            entryArray.put(e.toJson());
        }
        json.put("entries", entryArray);
        return json;
    }

}
