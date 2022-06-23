package test;

import controleur.Data;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestData {
    @Test
    public void constructor() {
        Data data = new Data("test", "test2", "test3");
        assertEquals("test", data.get(0));
        assertEquals("test2", data.get(1));
        assertEquals("test3", data.get(2));
        assertEquals(3, data.size());
        assertFalse(data.isAdmin());
        data.setAdmin(true);
        assertTrue(data.isAdmin());
        assertEquals("test, test2, test3, ", data.toString());
    }
}
