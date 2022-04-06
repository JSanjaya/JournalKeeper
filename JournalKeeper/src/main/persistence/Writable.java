package persistence;

import org.json.JSONObject;

// Interface for the model classes to implement their toJson methods

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
