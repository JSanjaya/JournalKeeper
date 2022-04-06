package persistence;

import exceptions.InvalidDateException;
import model.Date;
import model.Entry;
import model.Journal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

// Tests derived from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Journal j = new Journal("wrong");
            JsonWriter journal = new JsonWriter("./data/my\0illegal:fileName.json");
            journal.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyJournal() throws InvalidDateException {
        try {
            Journal j = new Journal("John");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyJournal.json");
            writer.open();
            writer.write(j);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyJournal.json");
            j = reader.read();
            assertEquals("John", j.getName());
            assertEquals(0, j.getEntries().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralJournal() throws InvalidDateException {
        try {
            Journal wr = new Journal("John");
            wr.addEntry(new Entry(new Date(1,1,2020), "hello"));
            wr.addEntry(new Entry(new Date(1,2,2020), "hello hi"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralJournal.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralJournal.json");
            wr = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }

}

