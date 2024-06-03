package View;

import Model.Character.*;
import Model.Character.Saving.HeroSave;
import Model.Character.Saving.SavedGameLister;
import Model.GameScreen;
import Model.GameScreenStack;
import Model.Skeleton;

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
  private static final String CHARACTER_SELECT = "Character Select";
  private static final String POLYMORPHISM = "Polymorphic Cup";
  private static final String ENCAPSULATION = "Shelf of Encapsulation";
  private static final String INHERITANCE = "Tree of Inheritance";
  private static final String ABSTRACTION = "Abstraction Gem";
  private static String MYSTERY = "???????";

  /**
   * QUIT_GAME  constant for "Quit Game" text to be displayed
   */
  private static final String QUIT_GAME = "Quit Game";
  private static final String SAVE_GAME = "Save Game";
  private static final String LOAD_GAME = "Load Game";

  private static final String BATTLE_SCREEN = "DEBUG Battle Screen";
  private final String[] myOptionMenu;
  private Image mySelectorImage;
  private Image menuBackgroundImage;
  private Image heroImage;
  private String heroType;
  private boolean[][] myProgress;
  private Hero myHero;
  private int myHeroIndex;

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
   * mySelected field stores the currently selected game option
   */
  private int mySelected;


  /**
   * abstractionUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean isAbstractionUnlock;

  /**
   * inheritanceUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean isInheritanceUnlock;

  /**
   * encapsulationUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean isEncapsulationUnlock;

  /**
   * polymorphismUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean isPolymorphismUnlock;

  /**
   * mysteryUnlock field stores true if that level is unlocked, false otherwise
   */
  private boolean isMysteryUnlock;


  public MainMenu(GameScreenStack theManager) {
    super(theManager);
    String MYSTERY = "???????";
    heroType = "";
    this.myOptionMenu = new String[] {CHARACTER_SELECT, POLYMORPHISM, ENCAPSULATION, INHERITANCE, ABSTRACTION,
            MYSTERY, QUIT_GAME, BATTLE_SCREEN};
    try {
      mySelectorImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
      menuBackgroundImage = ImageIO.read(new File("src/Assets/Images/title.png"));
      heroImage = ImageIO.read(new File("src/Assets/Images/silhouette.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }
    isAbstractionUnlock = true;
    isEncapsulationUnlock = true;
    isInheritanceUnlock = true;
    isPolymorphismUnlock = true;
    myProgress = new boolean[4][4];
    myHeroIndex = -1;
  }

  public MainMenu(GameScreenStack theManager, int theHero, boolean[][] theProgress){
    this(theManager);
    setHero(theHero);
    setProgress(theProgress);
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
          theGraphics.setColor(Color.gray);
        }
      }
      theGraphics.drawString(optionText, xStart, yStart + i * optionHeight);
    }
    theGraphics.setFont(new Font("Arial", Font.BOLD, 20));
    String heroLabel = "Hero:";
    int heroLabelX = 10;
    int heroLabelY = FrameManager.getHeight() - 50;
    theGraphics.setColor(Color.white);
    theGraphics.drawString(heroLabel, heroLabelX, heroLabelY);

    int bgWidth = 75;
    int bgHeight = 155;
    int bgX = 70;
    int bgY = FrameManager.getHeight() - 150;
    theGraphics.setColor(Color.gray);
    theGraphics.fillRect(bgX, bgY, bgWidth, bgHeight);

    int heroImageX = 60;
    int heroImageY = FrameManager.getHeight() - 150;
    int heroImageWidth = 90;
    int heroImageHeight = 140;
    theGraphics.drawImage(heroImage, heroImageX, heroImageY, heroImageWidth, heroImageHeight, null);

    theGraphics.setFont(new Font("Arial", Font.BOLD, 15));
    int heroNameX = bgX + (bgWidth - theGraphics.getFontMetrics().stringWidth(heroType)) / 2;
    int heroNameY = bgY - 20;
    theGraphics.setColor(Color.white);
    theGraphics.drawString(heroType, heroNameX, heroNameY);

    int starY = heroNameY + 15;
    if(myHeroIndex != -1) {
      for (int i = 0; i < 4; i++) {
        if (myProgress[myHeroIndex][i])
          theGraphics.setColor(new Color(255, 255, 0));
        else
          theGraphics.setColor(new Color(128, 128, 128));
        theGraphics.drawString("★", bgX + i * 20, starY);
      }
      theGraphics.setColor(Color.white);
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
          case CHARACTER_SELECT:
            myGameScreenStack.addScreen(new CharacterScreen(myGameScreenStack, myProgress));
            break;
            case QUIT_GAME:
            System.exit(0);
            break;
          case POLYMORPHISM:
            if(!heroType.equals(""))
            myGameScreenStack.addScreen(new PlayingScreen(myGameScreenStack, heroImage, 3, myHeroIndex, 1, myProgress));
            break;
          case ENCAPSULATION:
            if(!heroType.equals(""))
            myGameScreenStack.addScreen(new PlayingScreen(myGameScreenStack, heroImage, 4, myHeroIndex, 2, myProgress));
            break;
          case INHERITANCE:
            if(!heroType.equals(""))
            myGameScreenStack.addScreen(new PlayingScreen(myGameScreenStack, heroImage, 5, myHeroIndex, 3, myProgress));
            break;
          case ABSTRACTION:
            if(!heroType.equals(""))
            myGameScreenStack.addScreen(new PlayingScreen(myGameScreenStack, heroImage, 6, myHeroIndex, 4, myProgress));
            break;
          case BATTLE_SCREEN:
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
      case 5 -> isMysteryUnlock;
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
        isMysteryUnlock = true;
    }
  }

  public void setHero(int theHero){
      try {
        if(theHero == 0) {
          heroType = "Elf";
          heroImage = ImageIO.read(new File("src/Assets/Images/ElfBattle.png"));
        } else if (theHero == 1) {
          heroType = "Wizard";
          heroImage = ImageIO.read(new File("src/Assets/Images/wizard.png"));
        } else if (theHero == 2) {
          heroType = "Rogue";
          heroImage = ImageIO.read(new File("src/Assets/Images/rogue.png"));
        } else {
          heroType = "Barbarian";
          heroImage = ImageIO.read(new File("src/Assets/Images/barbarian.png"));
        }
        myHeroIndex = theHero;
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

  private void setProgress(boolean[][] theProgress){
    myProgress = theProgress;
  }
}
