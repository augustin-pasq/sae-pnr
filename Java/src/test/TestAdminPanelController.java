package test;

import controleur.AdminPanelController;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the class AdminPanelController
 *
 * @see org.junit.Test
 * @author Groupe SAE PNR 1D1
 */
public class TestAdminPanelController {
    @Test
    public void test() {
        AdminPanelController apc = new AdminPanelController();
        assertEquals("AdminPanelController", apc.getClass().getSimpleName());
        String[] tables = {"Lieu", "Observateur", "AObserve", "Obs_Hippocampe", "Obs_Loutre", "Obs_GCI", "Nid_GCI", "Chouette", "Obs_Chouette", "ZoneHumide", "Obs_Batracien", "Vegetation", "Lieu_Vegetation"};
        for (int i = 0; i < apc.tables.length; i++) {
            assertEquals(tables[i], apc.tables[i]);
        }

        File file = new File("test.zip");
        apc.exportData(file);
        assertTrue(file.exists());
        assertEquals("test.zip", file.getName());
        assertTrue(file.delete());
    }
}
