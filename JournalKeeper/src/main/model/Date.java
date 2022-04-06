package model;

import exceptions.InvalidDateException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a Date object and implements a Writable interface
// to convert the object's variables to the Json file

public class Date implements Writable {
    int day;
    int month;
    int year;

    // EFFECTS: constructs a new Date object
    public Date(int day, int month, int year) throws InvalidDateException {
        this.day = day;
        this.month = month;
        this.year = year;
        if (day < 1 || day > 31) {
            throw new InvalidDateException();
        } else if (month < 1 || month > 12) {
            throw new InvalidDateException();
        } else if (year < 2020) {
            throw new InvalidDateException();
        }
    }


    // EFFECTS: retrieves the day of the date
    public int getDay() {
        return day;
    }

    // MODIFIES: this
    // EFFECTS: sets the day of the date
    public void setDay(int day) throws InvalidDateException {

        if (day < 1 || day > 31) {
            throw new InvalidDateException();

        } else {
            this.day = day;
        }
    }

    // EFFECTS: gets the month of the date
    public int getMonth() {
        return month;
    }

    // MODIFIES: this
    // EFFECTS: sets the month of the date
    public void setMonth(int month) throws InvalidDateException {
        if (month < 1 || month > 12) {
            throw new InvalidDateException();
        } else {
            this.month = month;
        }
    }

    // EFFECTS: gets the year of the date
    public int getYear() {
        return year;
    }

    // MODIFIES: this
    // EFFECTS: sets the year of the date
    public void setYear(int year) throws InvalidDateException {
        if (year < 2020) {
            throw new InvalidDateException();
        } else {
            this.year = year;
        }

    }


    // EFFECTS: converts an Entry to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("month", month);
        json.put("year", year);
        return json;
    }

}
