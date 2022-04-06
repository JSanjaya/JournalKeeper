package persistence;

import exceptions.InvalidDateException;
import model.Date;
import model.Entry;
import model.Journal;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Reads the Json file from a designated source, which allows information
// to be displayed to the user

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads a Journal JSON file, and return the containing item
    public Journal read() throws IOException, InvalidDateException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseJournal(jsonObject);
    }

    // EFFECTS: returns a Journal from a read JSONObject
    private Journal parseJournal(JSONObject jsonObject) throws InvalidDateException {
        String name = jsonObject.getString("name");
        Journal j = new Journal(name);
        List<Entry> entries = readEntries(jsonObject);
        for (Entry e : entries) {
            j.addEntry(e);
        }
        return j;
    }

    // EFFECTS: Parses a Journal JSONObject and returns a list of entries
    private List<Entry> readEntries(JSONObject jsonObject) throws InvalidDateException {
        JSONArray entriesArray = jsonObject.getJSONArray("entries");
        List<Entry> entries = new ArrayList<>();
        for (Object e : entriesArray) {
            JSONObject entryObject = (JSONObject) e;
            String entry = entryObject.getString("entry");
            Date date = getOneDate(entryObject);
            entries.add(new Entry(date, entry));
        }
        return entries;

    }

    // Parses a date JSONObject, and returns a single date object.
    private Date getOneDate(JSONObject object) throws InvalidDateException {
        JSONObject dateObject = object.getJSONObject("date");
        Date d1 = new Date(dateObject.getInt("day"), dateObject.getInt("month"), dateObject.getInt("year"));
        return d1;

    }
}
