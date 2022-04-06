package model;

import exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
    Entry e;
    @BeforeEach
    public void setup() throws InvalidDateException {
        e = new Entry(new Date(1,1,2020),"hi");
    }

    @Test
    public void entriesTest(){
        assertEquals("hi",e.getEntry());
    }

    @Test
    public void setDateTest() throws InvalidDateException {
        e.setDate(new Date(2,2,2021));
        assertEquals(2, e.getDate().getDay());
        assertEquals(2, e.getDate().getMonth());
        assertEquals(2021, e.getDate().getYear());
    }
    @Test
    public void setEntryTest(){
        e.setEntry("hello");
        assertEquals("hello",e.getEntry());
    }


}
