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
public class MainMenu extends GameScreen {
  private static final String CHARACTER_SELECT = "Character Select";
  private static final String POLYMORPHISM = "Polymorphism";
  private static final String ENCAPSULATION = "Encapsulation";
  private static final String INHERITANCE = "Inheritance";
  private static final String ABSTRACTION = "Abstraction";
  private static String MYSTERY = "???????";
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
  private Image heroImage;
  private String heroType;
  private boolean[][] myProgress;
  private boolean isAbstractionUnlock;
  private boolean isInheritanceUnlock;
  private boolean isEncapsulationUnlock;
  private boolean isPolymorphismUnlock;
  private boolean isMysteryUnlocked;
  private Hero myHero;
  private int myHeroIndex;



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
            gameScreenStack.addScreen(new CharacterScreen(gameScreenStack, myProgress));
            break;
            case QUIT_GAME:
            System.exit(0);
            break;
          case POLYMORPHISM:
            if(!heroType.equals(""))
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, heroImage, 3, myHeroIndex, 1, myProgress));
            break;
          case ENCAPSULATION:
            if(!heroType.equals(""))
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, heroImage, 4, myHeroIndex, 2, myProgress));
            break;
          case INHERITANCE:
            if(!heroType.equals(""))
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, heroImage, 5, myHeroIndex, 3, myProgress));
            break;
          case ABSTRACTION:
            if(!heroType.equals(""))
            gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, heroImage, 6, myHeroIndex, 4, myProgress));
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

  private void unlockMystery(){
    myOptionMenu[5] = "Final Level";
  }

  private void unlockLevel(int index) {
    switch (index) {
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
