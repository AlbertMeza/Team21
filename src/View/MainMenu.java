package View;

import Model.Character.*;
import Model.Character.Saving.HeroSave;
import Model.Character.Saving.SavedGameLister;
import Model.GameScreen;
import Model.GameScreenStack;
import Model.PlayableHero;
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
  private static final String SELECT_EFFECT = "steelsword";
  private static final String SWITCH_EFFECT = "215029__taira-komori__extracting_knife";
  private static final String START_MENU_MUSIC = "POL-misty-dungeon-short";
  private final String[] myOptionMenu;
  private int mySelected;
  private Image mySelectorImage;
  private Image menuBackgroundImage;
  private boolean isAbstractionUnlock;
  private boolean isInheritanceUnlock;
  private boolean isEncapsulationUnlock;
  private boolean isPolymorphismUnlock;
  private boolean isMysteryUnlocked;
  private Hero myHero;

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
  private final String[] OPTION_MENU;

  /**
   * mySelected field stores the currently selected game option
   */
  private int mySelected;

  /**
   * SELECTOR_IMAGE constant is the Image for the selector
   */
  private Image SELECTOR_IMAGE;

  /**
   * MENU_BACKGROUND_IMAGE constant is the background image for the menu screen
   */
  private Image MENU_BACKGROUND_IMAGE;

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


    /**
     * MainMenu constructor initializes all fields
     * and renders the menu screen for the game.
     *
     * @param theSM is the game screen stack this menu goes on
     * @param theMM is the music manager for the game
     * @param theSEM is the sound effects manager for the game
     */
  public MainMenu(GameScreenStack theManager) {
    super(theManager);
    String MYSTERY = "???????";
    this.myOptionMenu = new String[] {START_GAME, POLYMORPHISM, ENCAPSULATION, INHERITANCE, ABSTRACTION,
            MYSTERY, QUIT_GAME, BATTLE_SCREEN};
    try {
      mySelectorImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
      menuBackgroundImage = ImageIO.read(new File("src/Assets/Images/title.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    isAbstractionUnlock = true;
    isEncapsulationUnlock = true;
    isInheritanceUnlock = true;
    isPolymorphismUnlock = true;
  }

  @Override
  protected void loop() {
  }

  @Override
  protected void render(Graphics theGraphics) {
    theGraphics.drawImage(menuBackgroundImage, 0, 0, FrameManager.getWidth(),
                       FrameManager.getHeight(), null);
    theGraphics.setColor(new Color(30, 30, 70,120));
    theGraphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
    theGraphics.setFont(new Font("Arial", Font.PLAIN, 25));
    int optionHeight = theGraphics.getFontMetrics().getHeight();
    int totalHeight = myOptionMenu.length * optionHeight;
    int yStart = (FrameManager.getHeight() - totalHeight) / 2;
    for (int i = 0; i < myOptionMenu.length; i++) {
      String optionText = myOptionMenu[i];
      int textWidth = theGraphics.getFontMetrics().stringWidth(optionText);
      int xStart = (FrameManager.getWidth() - textWidth) / 2;
      if (i == mySelected) {
        theGraphics.setColor(Color.magenta);
        theGraphics.drawImage(mySelectorImage, xStart - mySelectorImage.getWidth(null) - 5,
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

  @Override
  protected void keyPressed(int theKeyCode) {
    switch(theKeyCode) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        if(this.mySelected > 0) this.mySelected--;

        playSoundEffect(SWITCH_EFFECT);
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        if(this.mySelected < this.myOptionMenu.length-1) this.mySelected++;
        playSoundEffect(SWITCH_EFFECT);

        break;
      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        stopBackgroundMusic();
        switch(this.myOptionMenu[mySelected]) {
          case START_GAME:
            gameScreenStack.addScreen(new CharacterScreen(gameScreenStack));
            break;
            case QUIT_GAME:
            System.exit(0);
            break;
          case POLYMORPHISM:
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, 3, 1));
            break;
          case ENCAPSULATION:
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, 4, 2));
            break;
          case INHERITANCE:
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, 5, 3));
            break;
          case ABSTRACTION:
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, 6, 4));
            break;
          case BATTLE_SCREEN:
            gameScreenStack.addScreen(new BattleScreen(gameScreenStack, myHero, new Skeleton()));
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


  @Override
  protected void keyReleased(int theKeyCode) {
  }
  private boolean isOptionEnabled(int theIndex) {
    return switch (theIndex) {
      case 0, 6, 7 -> true;
      case 1 -> isPolymorphismUnlock;
      case 2 -> isEncapsulationUnlock;
      case 3 -> isInheritanceUnlock;
      case 4 -> isAbstractionUnlock;
      case 5 -> isMysteryUnlocked;
      default -> false;
    };
  }

  /**
   * unlockMystery changes the "?????" to "final level"
   */
  private void unlockMystery(){
    myOptionMenu[5] = "Final Level";
  }

  /**
   * unlockLevel unlocks each level when the level is passed
   *
   * @param theLevel is the level to be unlocked
   */
  private void unlockLevel(int theLevel) {
    switch (theLevel) {
      case 1:
        isPolymorphismUnlock = true;
      case 2:
        isEncapsulationUnlock = true;
      case 3:
        isInheritanceUnlock = true;
      case 4:
        isAbstractionUnlock = true;
      case 5:
        isMysteryUnlocked = true;
    }
  }
}
