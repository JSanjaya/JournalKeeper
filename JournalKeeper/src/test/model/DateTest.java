package model;

import exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTest {
    Date d;

    @BeforeEach
    public void setup() throws InvalidDateException {
        d = new Date(1, 1, 2020);
    }

    @Test
    public void dayTest() {
        assertEquals(1, d.getDay());
    }

    @Test
    public void monthTest() {
        assertEquals(1, d.getMonth());
    }

    @Test
    public void yearTest() {
        assertEquals(2020, d.getYear());
    }

    @Test
    public void setDayTest() {
        try {
            d.setDay(2);
            assertEquals(2, d.getDay());
        } catch (InvalidDateException i) {
            fail();
        }
    }

    @Test
    public void setMonthTest() {
        try {
            d.setMonth(3);
            assertEquals(3, d.getMonth());
        } catch (InvalidDateException i) {
            fail();
        }
    }

    @Test
    public void setYearTest() {
        try {
            d.setYear(2021);
            assertEquals(2021, d.getYear());
        } catch (InvalidDateException i) {
            fail();
        }
    }

    @Test
    public void invalidDayTest() {
        try {
            Date d1 = new Date(56, 10, 2020);
            fail();
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidNegativeDayTest() {
        try {
            Date d1 = new Date(-10, 10, 2020);
            fail();
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidMonthTest() {
        try {
            Date d1 = new Date(10, 56, 2020);
            fail();
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidNegativeMonthTest() {
        try {
            Date d1 = new Date(10, -10, 2020);
            fail();
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidYearTest() {
        try {
            Date d1 = new Date(10, 10, 100);
            fail();
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidSetDayTest() {
        try {
            d.setDay(56);
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidNegativeSetDayTest() {
        try {
            d.setDay(-10);
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidSetMonthTest() {
        try {
            d.setMonth(56);
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidNegativeSetMonthTest() {
        try {
            d.setMonth(-10);
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

    @Test
    public void invalidSetYearTest() {
        try {
            d.setYear(56);
        } catch (InvalidDateException i) {
            // Test passes
        }
    }

}
