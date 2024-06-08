package Model.Character.Saving;

import Model.Character.Elf;
import Model.Character.Hero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeroSaveTest {

    private static final String TEST_FILE_NAME = "test_hero_save.sav";
    private Hero theTestHero;
    private String theTestFileName;

    @BeforeEach
    public void setUp() {
        theTestHero = new Elf();  // Assuming Hero has a constructor Hero(String name, int level)
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete the test file");
        }
    }

    @Test
    public void testLoadHero() {
        HeroSave.saveHero(theTestHero, TEST_FILE_NAME);
        Hero loadedHero = HeroSave.loadHero(TEST_FILE_NAME);
        assertNotNull(loadedHero, "The loaded hero is null");
        assertEquals(theTestHero.getName(), loadedHero.getName(), "Hero name does not match");
        assertEquals(theTestHero.getMaxHP(), loadedHero.getMaxHP(), "Hero HP does not match");
    }

    @Test
    public void testLoadHeroFileNotFound() {
        Hero loadedHero = HeroSave.loadHero("non_existent_file.sav");
        assertNull(loadedHero, "The loaded hero should be null for non-existent file");
    }

    @Test
    public void testUserInteractionForSave() {
        String saveFileName = "test_save_interaction.sav";
        String input = JOptionPane.showInputDialog("Enter a name for your save file:");

        if (input != null && !input.trim().isEmpty()) {
            assertDoesNotThrow(() -> HeroSave.saveHero(theTestHero, input + ".sav"));
            File file = new File(HeroSave.SAVED_GAME_DIR + input + ".sav");
            assertTrue(file.exists(), "The save file was not created");

            JOptionPane.showMessageDialog(null, "Game saved successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid file name. Game not saved.");
        }
    }

    @Test
    public void testUserInteractionForLoad() {
        HeroSave.saveHero(theTestHero, TEST_FILE_NAME);
        List<String> savedGames = List.of(TEST_FILE_NAME);

        if (savedGames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No saved games found.");
        } else {
            String selectedGame = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a saved game to load:",
                    "Load Game",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    savedGames.toArray(),
                    savedGames.get(0)
            );

            if (selectedGame != null) {
                Hero loadedHero = HeroSave.loadHero(selectedGame);
                assertNotNull(loadedHero, "The loaded hero is null");
                assertEquals(theTestHero.getName(), loadedHero.getName(), "Hero name does not match");
                assertEquals(theTestHero.getMaxHP(), loadedHero.getMaxHP(), "Hero HP does not match");

                JOptionPane.showMessageDialog(null, "Game loaded successfully.");
            }
        }
    }
}
