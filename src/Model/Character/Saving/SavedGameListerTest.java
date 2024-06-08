package Model.Character.Saving;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SavedGameListerTest {

    private static final String TEST_FILE_1 = "test_hero_save_1.sav";
    private static final String TEST_FILE_2 = "test_hero_save_2.sav";
    private static final String NON_SAV_FILE = "test_hero_save.txt";
    private final int myPriorSavedGames = SavedGameLister.listSavedGames().size();

    @BeforeEach
    public void setUp() throws IOException {
        createTestFile(TEST_FILE_1);
        createTestFile(TEST_FILE_2);
        createTestFile(NON_SAV_FILE);

    }

    @AfterEach
    public void tearDown() {
        deleteTestFile(TEST_FILE_1);
        deleteTestFile(TEST_FILE_2);
        deleteTestFile(NON_SAV_FILE);
    }

    @Test
    public void testListSavedGames() {
        List<String> savedGames = SavedGameLister.listSavedGames();
        assertNotNull(savedGames, "The saved games list should not be null");
        assertEquals(2, savedGames.size() - myPriorSavedGames, "The number of saved games does not match");
        assertTrue(savedGames.contains(TEST_FILE_1), "Saved games should contain " + TEST_FILE_1);
        assertTrue(savedGames.contains(TEST_FILE_2), "Saved games should contain " + TEST_FILE_2);
        assertFalse(savedGames.contains(NON_SAV_FILE), "Saved games should not contain non-.sav file");
    }

    @Test
    public void testListSavedGamesEmptyDirectory() {
        tearDown();  // Ensure the directory is empty
        List<String> savedGames = SavedGameLister.listSavedGames();
        assertNotNull(savedGames, "The saved games list should not be null");
        assertTrue(savedGames.isEmpty(), "The saved games list should be empty");
    }

    private void createTestFile(String fileName) throws IOException {
        File file = new File(HeroSave.SAVED_GAME_DIR + fileName);
        File dir = new File(HeroSave.SAVED_GAME_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (file.createNewFile()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Test content");
            }
        }
    }

    private void deleteTestFile(String fileName) {
        File file = new File(HeroSave.SAVED_GAME_DIR + fileName);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete the test file: " + fileName);
        }
    }
}
