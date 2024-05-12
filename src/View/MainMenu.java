package View;

import Controller.MazeGenerator;
import Controller.MusicManager;
import Controller.SoundEffectsManager;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

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
  public MainMenu(GameScreenStack theSM, MusicManager theMM, SoundEffectsManager theSEM) {
      super(theSM);
      this.OPTION_MENU = new String[] {START_GAME, POLYMORPHISM, ENCAPSULATION, INHERITANCE, ABSTRACTION, MYSTERY, QUIT_GAME};
      this.myMusicManager = theMM;
      this.mySoundEffectsManager = theSEM;
      this.mySelected = 0;
      abstractionUnlock = false;
      inheritanceUnlock = false;
      encapsulationUnlock = false;
      polymorphismUnlock = false;
      mysteryUnlock = false;
      playBackgroundMusic();

    try {
      SELECTOR_IMAGE = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
      MENU_BACKGROUND_IMAGE = ImageIO.read(new File("src/Assets/Images/title.png"));
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
  protected void render(Graphics graphics) {
    graphics.drawImage(MENU_BACKGROUND_IMAGE, 0, 0, FrameManager.getWidth(),
        FrameManager.getHeight(), null);
    graphics.setColor(new Color(30, 30, 70,120));
    graphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
    graphics.setFont(new Font("Arial", Font.PLAIN, 25));
    int optionHeight = graphics.getFontMetrics().getHeight();
    int totalHeight = OPTION_MENU.length * optionHeight;
    int yStart = (FrameManager.getHeight() - totalHeight) / 2;
    for (int i = 0; i < OPTION_MENU.length; i++) {
      String optionText = OPTION_MENU[i];
      int textWidth = graphics.getFontMetrics().stringWidth(optionText);
      int xStart = (FrameManager.getWidth() - textWidth) / 2;
      if (i == mySelected) {
        graphics.setColor(Color.magenta);
        graphics.drawImage(SELECTOR_IMAGE, xStart - SELECTOR_IMAGE.getWidth(null) - 5,
            yStart + i * optionHeight - optionHeight / 2, null);
      } else {
        if (isOptionEnabled(i)) {
          graphics.setColor(Color.white);
        } else {
          graphics.setColor(Color.gray); // Grey out disabled options
        }
      }
      graphics.drawString(optionText, xStart, yStart + i * optionHeight);
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
        if(this.mySelected > 0) this.mySelected--;
        playSoundEffect(SWITCH_EFFECT);
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        if(this.mySelected < this.OPTION_MENU.length-1) this.mySelected++;
        playSoundEffect(SWITCH_EFFECT);

        break;
      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        switch(this.OPTION_MENU[mySelected]) {
          case START_GAME:
            MazeGenerator generator = new MazeGenerator();
            while(!generator.finished()) {
              generator.generate();
            }
            break;
          case QUIT_GAME:
            System.exit(0);
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

  /**
   * playBackgroundMusic enables playing background music
   */
  @Override
  protected void playBackgroundMusic() {
    myMusicManager.playBackgroundMusic(START_MENU_MUSIC);
  }

  /**
   * stopBackgroundMusic enables stopping background music
   */
  @Override
  protected void stopBackgroundMusic() {
    myMusicManager.stopBackgroundMusic();
  }

  /**
   * playSoundEffect enables playing audio sound effects
   */
  @Override
  protected void playSoundEffect(String effectName) {
    mySoundEffectsManager.playSoundEffect(effectName);
  }

  /**
   * returns whether the passed level is unlocked
   *
   * @param theLevel is the level to be checked if unlocked
   * @return returns true if the level is unlocked, false otherwise
   */
  private boolean isOptionEnabled(int theLevel) {
    switch (theLevel) {
      case 0:
      case 6:
        return true;
      case 1:
        return polymorphismUnlock;
      case 2:
        return encapsulationUnlock;
      case 3:
        return inheritanceUnlock;
      case 4:
        return abstractionUnlock;
      case 5:
        return mysteryUnlock;
      default:
        return false;
    }
  }

  /**
   * unlockMystery changes the "?????" to "final level"
   */
  private void unlockMystery(){
    OPTION_MENU[5] = "Final Level";
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
