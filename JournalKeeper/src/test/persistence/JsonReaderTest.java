package persistence;

import exceptions.InvalidDateException;
import model.Date;
import model.Entry;
import model.Journal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests derived from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() throws InvalidDateException {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Journal wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() throws InvalidDateException {
        JsonReader reader = new JsonReader("./data/testReaderEmptyJournal.json");
        try {
            Journal wr = reader.read();
            assertEquals("John", wr.getName());
            assertEquals(0, wr.getEntries().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralJournal() throws InvalidDateException {
        JsonReader reader = new JsonReader("./data/testReaderGeneralJournal.json");
        try {
            Journal wr = reader.read();
            assertEquals("John", wr.getName());
            List<Entry> entries = wr.getEntries();
            assertEquals(2, entries.size());
            assertEquals(1, wr.getEntries().get(0).getDate().getDay());
            assertEquals(1, wr.getEntries().get(0).getDate().getMonth());
            assertEquals(2020, wr.getEntries().get(0).getDate().getYear());

            assertEquals(1, wr.getEntries().get(1).getDate().getDay());
            assertEquals(2, wr.getEntries().get(1).getDate().getMonth());
            assertEquals(2020, wr.getEntries().get(1).getDate().getYear());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

