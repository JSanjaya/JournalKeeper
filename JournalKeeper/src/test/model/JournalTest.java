package model;

import exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
    Journal j;
    @BeforeEach
    public void setup(){
        this.j = new Journal("NotJohn");
    }

    @Test
    public void viewAuthorTest() throws InvalidDateException {
        assertEquals("NotJohn's Journal currently has 0 entries",j.viewAuthor());
        j.addEntry(new Entry (new Date(1,1,2020), "hello there"));
        assertEquals("NotJohn's Journal currently has 1 entries",j.viewAuthor());
    }

    @Test
    public void viewEntriesTest() throws InvalidDateException {
        String journalPage = "";
        String journalPage2 = "";
        j.addEntry(new Entry (new Date(1,1,2020), "hello there"));
        String date = (1 + "/" + 1 + "/" + 2020);
        String entry = ("hello there");
        journalPage = journalPage.concat("\n" + date + "\n" + entry + "\n");
        assertEquals(journalPage,j.viewEntries());
        j.addEntry(new Entry (new Date(1,2,2020), "hello there"));
        String date2 = (1 + "/" + 2 + "/" + 2020);
        String entry2 = ("hello there");
        journalPage2 = journalPage2.concat(journalPage + "\n" + date2 + "\n" + entry2 + "\n");
        assertEquals(journalPage2,j.viewEntries());

    }
    @Test
    public void removeEntriesTest() throws InvalidDateException {
        Entry e = new Entry(new Date(1,1,2020), "hello there");
        String journalPage = "";
        j.addEntry(e);
        String date = (1 + "/" + 1 + "/" + 2020);
        String entry = ("hello there");
        journalPage = journalPage.concat("\n" + date + "\n" + entry + "\n");
        assertEquals(journalPage,j.viewEntries());
        j.removeEntry(e);
        assertEquals("",j.viewEntries());
    }

    @Test
    public void getEntriesTest() throws InvalidDateException {
        Entry e = new Entry(new Date(1,1,2020), "hello there");
        j.addEntry(e);
        ArrayList<Entry> test = new ArrayList<>();
        test.add(e);
        assertEquals(test,j.getEntries());
    }

}