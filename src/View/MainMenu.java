package View;

import Model.Character.*;
import Model.Character.Saving.HeroSave;
import Model.Character.Saving.SavedGameLister;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.File;
import java.util.List;

//Music: “Misty Dungeon”, from PlayOnLoop.com
//Licensed under Creative Commons by Attribution 4.0

/**
 * MainMenu class is the screen for the game main menu
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class MainMenu extends GameScreen {

  /**
   * START_GAME constant for "start screen" text to be displayed
   */
  private static final String START_GAME = "Start Game";

  /**
   * POLYMORPHISM constant for "Polymorphsim" text to be displayed
   */
  private static final String POLYMORPHISM = "Polymorphism";

  /**
   * ENCAPSULATION constant for "Encapsulation" text to be displayed
   */
  private static final String ENCAPSULATION = "Encapsulation";

  /**
   * INHERITANCE constant for "Inheritance" text to be displayed
   */
  private static final String INHERITANCE = "Inheritance";

  /**
   * ABSTRACTION constant for "Abstraction" text to be displayed
   */
  private static final String ABSTRACTION = "Abstraction";

  /**
   * MYSTERY  constant for "???????" text to be displayed
   */
  private static String MYSTERY = "???????";

  /**
   * QUIT_GAME  constant for "Quit Game" text to be displayed
   */
  private static final String QUIT_GAME = "Quit Game";

    private static final String SAVE_GAME = "Save Game";
    private static final String LOAD_GAME = "Load Game";
    private static final String BATTLE_SCREEN = "DEBUG Battle Screen";

    /**
   * SELECT_EFFECT constant is the name of the audio file for selecting an option
   */
  private static final String SELECT_EFFECT = "steelsword.wav";

  /**
   * SWITCH_EFFECT constant is the name of the audio file for switching options
   */
  private static final String SWITCH_EFFECT = "215029__taira-komori__extracting_knife.wav";

  /**
   * START_MENU_MUSIC constant for name of the audio file for background music
   */
  private static final String START_MENU_MUSIC = "POL-misty-dungeon-short.wav";

  /**
   * OPTION_MENU constant stores the gameplay option strings
   */
  private final String[] myOptionMenu;

  /**
   * mySelected field stores the currently selected game option
   */
  private int mySelected;

  /**
   * SELECTOR_IMAGE constant is the Image for the selector
   */
  private Image mySelectorImg;

  /**
   * MENU_BACKGROUND_IMAGE constant is the background image for the menu screen
   */
  private Image myMenuBackgroundImg;

  /**
   * abstractionUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean abstractionUnlock;

  /**
   * inheritanceUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean inheritanceUnlock;

  /**
   * encapsulationUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean encapsulationUnlock;

  /**
   * polymorphismUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean polymorphismUnlock;

  /**
   * mysteryUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean mysteryUnlock;
  private Hero myHero;
  private final File saveLocation;



    /**
     * MainMenu constructor initializes all fields
     * and renders the menu screen for the game.
     */
  public MainMenu(GameScreenStack manager) {
    super(manager);
    myHero = new Elf();
    String MYSTERY = "???????";
    myOptionMenu = new String[] {START_GAME, POLYMORPHISM, ENCAPSULATION, INHERITANCE, ABSTRACTION,
            MYSTERY, QUIT_GAME, BATTLE_SCREEN, SAVE_GAME, LOAD_GAME};
    mySelected = 0;
    abstractionUnlock = false;
    inheritanceUnlock = false;
    encapsulationUnlock = false;
    polymorphismUnlock = false;
    mysteryUnlock = false;
    playBackgroundMusic(START_MENU_MUSIC);
    saveLocation = new File("src/SavedGame");
    try {
      mySelectorImg = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
      myMenuBackgroundImg = ImageIO.read(new File("src/Assets/Images/title.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * loop method enables any looping behavior in the class
   */
  @Override
  protected void loop() {
  }

  /**
   * render handles all graphics and displays them in the window
   *
   * @param graphics is the graphics for the game
   */
  @Override
  protected void render(Graphics theGraphics) {
    graphics.drawImage(myMenuBackgroundImg, 0, 0, FrameManager.getWidth(),
        FrameManager.getHeight(), null);
    graphics.setColor(new Color(30, 30, 70,120));
    graphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
    graphics.setFont(new Font("Arial", Font.PLAIN, 25));
    int optionHeight = graphics.getFontMetrics().getHeight();
    int totalHeight = myOptionMenu.length * optionHeight;
    int yStart = (FrameManager.getHeight() - totalHeight) / 2;
    for (int i = 0; i < optionMenu.length; i++) {
      String optionText = optionMenu[i];
      int textWidth = theGraphics.getFontMetrics().stringWidth(optionText);
      int xStart = (FrameManager.getWidth() - textWidth) / 2;
      if (i == mySelected) {
        graphics.setColor(Color.magenta);
        graphics.drawImage(mySelectorImg, xStart - mySelectorImg.getWidth(null) - 5,
            yStart + i * optionHeight - optionHeight / 2, null);
      } else {
        if (isOptionEnabled(i)) {
          theGraphics.setColor(Color.white);
        } else {
          theGraphics.setColor(Color.gray); // Grey out disabled options
        }
      }
      theGraphics.drawString(optionText, xStart, yStart + i * optionHeight);
    }
  }

  /**
   * keyPressed method enables key press event handling
   *
   * @param keyCode is the code for the key pressed
   */
  @Override
  protected void keyPressed(int keyCode) {
    switch(keyCode) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        if(mySelected > 0) selected--;
        playSoundEffect(SWITCH_EFFECT);
        break;
        
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        if(selected < optionMenu.length - 1) selected++;
        playSoundEffect(SWITCH_EFFECT);
        break;
        
      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        switch(optionMenu[selected]) {
          case START_GAME:
            stopBackgroundMusic();
            myGameScreenStack.addScreen(new CharacterScreen(myGameScreenStack));
            break;

          case QUIT_GAME:
            System.exit(0);
            break;

          case BATTLE_SCREEN:
            stopBackgroundMusic();
            myGameScreenStack.addScreen(new BattleScreen(myGameScreenStack, myHero, new Skeleton()));
            break;

          case SAVE_GAME:
            String saveFileName = JOptionPane.showInputDialog("Enter a name for your save file:");
            if (saveFileName != null && !saveFileName.trim().isEmpty()) {
              HeroSave.saveHero(myHero, saveFileName + ".sav");
              JOptionPane.showMessageDialog(null, "Game saved successfully.");
            } else {
              JOptionPane.showMessageDialog(null, "Invalid file name. Game not saved.");
            }
            break;

          case LOAD_GAME:
            List<String> savedGames = SavedGameLister.listSavedGames();
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
                myHero = HeroSave.loadHero(selectedGame);
                JOptionPane.showMessageDialog(null, "Game loaded successfully.");
                // Transition to the game screen or perform any additional setup
              }
            }
            break;
        }
        break;
    }
  }

  /**
   * keyReleased method enables key release event handling
   *
   * @param keyCode is the code for the key pressed
   */
  @Override
  protected void keyReleased(int keyCode) {
  }

  private boolean isOptionEnabled(int index) {
    return switch (index) {
      case 0, 6, 7, 8, 9 -> true;
      case 1 -> polymorphismUnlock;
      case 2 -> encapsulationUnlock;
      case 3 -> inheritanceUnlock;
      case 4 -> abstractionUnlock;
      case 5 -> mysteryUnlock;
      default -> false;
    };
  }

  /**
   * unlockMystery changes the "?????" to "final level"
   */
  private void unlockMystery(){
    myOptionMenu[5] = "Final Level";
    unlockLevel(5);
  }

  /**
   * unlockLevel unlocks each level when the level is passed
   *
   * @param theLevel is the level to be unlocked
   */
  private void unlockLevel(int theLevel) {
    switch (theLevel) {
      case 1:
        polymorphismUnlock = true;
      case 2:
        encapsulationUnlock = true;
      case 3:
        inheritanceUnlock = true;
      case 4:
        abstractionUnlock = true;
      case 5:
        mysteryUnlock = true;
    }
  }
}
