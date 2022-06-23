package test;

import controleur.Data;
import controleur.DataBatracienController;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Test the Data class and other Data classes
 *
 * @see org.junit.Test
 * @author Groupe SAE PNR 1D1
 */
public class TestData {
    /**
     * Test the Data class
     */
    @Test
    public void object() {
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

    /**
     * Test the DataBatracienController class
     */
    @Test
    public void batracien() {
        DataBatracienController data = new DataBatracienController();
        assertEquals("DataBatracienController", data.getClass().getSimpleName());
        assertEquals("InteractivePage", data.getClass().getSuperclass().getSimpleName());
        LocalDate date = LocalDate.of(2020, 1, 1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "null", "null", "null", "null", "null", "null", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("heure"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "45:65", "null", "null", "null", "null", "null", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("heure") && e.getMessage().contains("valide"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "null", "null", "null", "null", "null", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("Lambert X") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "null", "null", "null", "null", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("Lambert Y") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "45", "null", "null", "null", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("adultes") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "45", "5", "null", "null", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("amplexus") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "45", "5", "4", "null", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("pontes") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "45", "5", "4", "3", "null",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("tetards") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "45", "5", "4", "3", "2",
                "null", "null", "null", "null"));
        assertTrue(e.getMessage().contains("température") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "45", "5", "4", "3", "2",
                "1", "null", "null", "null"));
        assertTrue(e.getMessage().contains("profondeur") && e.getMessage().contains("nombre"));

        e = assertThrows(IllegalArgumentException.class, () -> data.checkFields("null", "null", date,
                "12:12", "45", "45", "5", "4", "3", "2",
                "1", "0", "null", "null"));
        assertTrue(e.getMessage().contains("surface") && e.getMessage().contains("nombre"));

        data.checkFields("null", "null", date,
                "12:12", "45", "45", "5", "4", "3", "2",
                "1", "0", "0", "null");
    }
}

