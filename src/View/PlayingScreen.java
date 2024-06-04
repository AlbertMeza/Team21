package View;

import Controller.MathHelper;
import Controller.MathHelper.Direction;
import Controller.MazeGenerator;
import Controller.RoomData;
import Model.Character.Hero;
import Model.Character.Monster;
import Model.Dungeon;
import Model.LightningBolt;
import Model.PlayableHero;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

import static View.FrameManager.getWidth;

/**
 * PlayingScreen class is the screen for rooms in the dungeon
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class PlayingScreen extends GameScreen {
  private PlayableHero myPlayer;

  private final MazeGenerator myGenerator;
  private Dungeon myDungeon;
  private final boolean[][] myVisitedRooms;
  private boolean isMazeVisible;

  private Image myMonsterImg;
  private Image myTImg;
  private Image myEscapeImg;
  private Image myExclamationImg;
  private Image myChestImg;
  private Image myOpenedChestImg;
  private Image myWalkingMonster;
  private final int myLevel;

  private Image myEndingImg;
  private final boolean[][] myProgress;
  private final int myHeroIndex;
  private boolean isBattleTextVisible;
  private boolean isEndingTextVisible;
  private final int myMonsterCount;
  private final int[] myMonsterPos;
  private final int myMonstersDefeated;
  private boolean isCheckUnlocked;
  private int monsterPosXOne = 150, monsterPosYOne = 150;
  private int monsterPosXTwo = 250, monsterPosYTwo = 250;
  private int monsterPosXThree = 300, monsterPosYThree = 300;
  private int velocityXOne = 5, velocityYOne = 5;
  private int velocityXTwo = 5, velocityYTwo = 5;
  private int velocityXThree = 5, velocityYThree = 5;
  private final List<LightningBolt> lightningBolts;
  private final Hero myHero;
  private final Monster myMonster;

  protected PlayingScreen(GameScreenStack theStack, Image theHeroImg, int theMazeSize,
      int theHeroIndex, int theLevel, boolean[][] theProgress, Hero theHero, Monster theMonster) {
    super(theStack);
    this.myGenerator = new MazeGenerator();
    this.myLevel = theLevel;
    this.generateMaze(theMazeSize);
    this.myVisitedRooms = new boolean[theMazeSize][theMazeSize];
    myVisitedRooms[0][0] = true;
    myVisitedRooms[theMazeSize - 1][theMazeSize - 1] = true;
    this.myPlayer = new PlayableHero((byte) 0, getWidth() / 2, FrameManager.getHeight() / 2,
        theHeroImg);
    this.isMazeVisible = false;
    this.myMonsterPos = generateMonsterPosition();
    for (int num : myMonsterPos) {
      System.out.println(num);
    }
    try {
      if (theLevel == 4) {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/AbstractionGem.png"));
        myWalkingMonster = ImageIO.read(new File("src/Assets/Images/Monster01-2.png"));
      } else if (theLevel == 3) {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/InheritanceTree.png"));
        myWalkingMonster = ImageIO.read(new File("src/Assets/Images/Monster02-1.png"));
      } else if (theLevel == 2) {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/Encapsulation.png"));
      } else {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/PolymorphismCup.png"));
      }
      myMonsterImg = ImageIO.read(new File("src/Assets/Images/" + theMonster.getName() + "Battle.png"));
      myEscapeImg = ImageIO.read(new File("src/Assets/Images/Escape.jpg"));
      myTImg = ImageIO.read(new File("src/Assets/Images/T.png"));
      myExclamationImg = ImageIO.read(new File("src/Assets/Images/eMark.jpeg"));
      myChestImg = ImageIO.read(new File("src/Assets/Images/CloseChest.png"));
      myOpenedChestImg = ImageIO.read(new File("src/Assets/Images/OpenChest.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    myProgress = theProgress;
    myHeroIndex = theHeroIndex;
    isBattleTextVisible = false;
    isEndingTextVisible = false;
    myMonsterCount = getMyLevel();
    myMonstersDefeated = 0;
    isCheckUnlocked = false;
    lightningBolts = new ArrayList<>();
    myHero = theHero;
    myMonster = theMonster;
  }

  /**
   * loop method is for any looping events during game play
   */
  @Override
  protected void loop() {
    this.myPlayer.move();
    adjustMonsterPos();
    this.myDungeon.changeRoom(myPlayer);
    myVisitedRooms[myDungeon.getMyCurrY()][myDungeon.getMyCurrX()] = true;

    for (LightningBolt bolt : lightningBolts) {
      bolt.update();
    }

    lightningBolts.removeIf(bolt -> !bolt.isActive());

    RoomData roomIn = this.myDungeon.getRoom().getMyData();
    for (int i = 0; i < roomIn.getSizeX(); i++) {
      for (int j = 0; j < roomIn.getSizeY(); j++) {
        this.myPlayer.handleCollisionWith(this.myDungeon.getRoom().getMyData().getTileAt(i, j));
      }
    }

    handleMonsterCollision();
  }

  /**
   * render method displays the screen
   *
   * @param graphics is the graphics for game controller
   */
  @Override
  protected void render(Graphics graphics) {
    myDungeon.getRoom().getMyData().render(graphics);
    this.myPlayer.render(graphics);
    for (LightningBolt bolt : lightningBolts) {
      bolt.render(graphics);
    }
    if (isMazeVisible) {
      for (int i = 0; i < myGenerator.getWorldSize(); i++) {
        for (int j = 0; j < myGenerator.getWorldSize(); j++) {
          int x = getWidth() - (myGenerator.getWorldSize() - j) * 33;
          int y = FrameManager.getHeight() - (myGenerator.getWorldSize() - i) * 24;
          if (myVisitedRooms[i][j]) {
            Image image = null;
            try {
              image = ImageIO.read(getRoom(myGenerator.getDirForRoom(j, i)));
            } catch (IOException e) {
              e.printStackTrace();
            }
            graphics.drawImage(image, x, y, 33, 24, null);
            if (j == myDungeon.getMyCurrX() && i == myDungeon.getMyCurrY()) {
              graphics.setColor(Color.BLUE);
              graphics.fillRect(x + 10, y + 8, 12, 10);
            }
          } else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y, 33, 24);
          }
        }
      }
    }
    if (getMyLevel() >= 2 && !(myDungeon.getMyCurrX() == 0 && myDungeon.getMyCurrY() == 0)
        && !(myDungeon.getMyCurrX() == myGenerator.getWorldSize() - 1
        && myDungeon.getMyCurrY() == myGenerator.getWorldSize() - 1)) {
      graphics.drawImage(myWalkingMonster, monsterPosXOne, monsterPosYOne, 50, 50, null);
      graphics.drawImage(myWalkingMonster, monsterPosXTwo, monsterPosYTwo, 50, 50, null);
      graphics.drawImage(myWalkingMonster, monsterPosXThree, monsterPosYThree, 50, 50, null);
    }
    if (getMyLevel() >= 3 && !myProgress[myHeroIndex][0] && !myProgress[myHeroIndex][1] &&
        myDungeon.getMyCurrX() == 0 && myDungeon.getMyCurrY() == 0) {
      graphics.setFont(new Font("Arial", Font.BOLD, 14));
      graphics.setColor(Color.BLACK);
      graphics.drawString("You do not seem ready!", getWidth() / 2 - 75,
          FrameManager.getHeight() / 2 - 20);
    }

    for (int i = 0; i < myMonsterCount; i++) {
      int monsterX = myMonsterPos[i * 2];
      int monsterY = myMonsterPos[i * 2 + 1];
      if (myDungeon.getMyCurrX() == monsterX && myDungeon.getMyCurrY() == monsterY) {
        int monsterWidth = 50;
        int monsterHeight = 50;

        int centerX = getWidth() / 2;
        int centerY = FrameManager.getHeight() / 2;

        graphics.drawImage(myMonsterImg, centerX, centerY, monsterWidth, monsterHeight, null);
        if (myDungeon.getMyCurrX() == myMonsterPos[0] && myDungeon.getMyCurrY() == myMonsterPos[1]
            && getMyLevel() > 1) {
          graphics.drawImage(myChestImg, centerX - monsterWidth, centerY, monsterWidth,
              monsterHeight, null);
        }
        int exclamationOriginalWidth = myExclamationImg.getWidth(null);
        int exclamationOriginalHeight = myExclamationImg.getHeight(null);
        int exclamationWidth = exclamationOriginalWidth / 20;
        int exclamationHeight = exclamationOriginalHeight / 20;
        int exclamationX = centerX + monsterWidth / 2 - exclamationWidth / 2;
        int exclamationY = centerY - exclamationHeight;

        if (Math.abs(myPlayer.x - centerX) <= 150 && Math.abs(myPlayer.y - centerY) <= 150) {
          graphics.drawImage(myExclamationImg, exclamationX, exclamationY, exclamationWidth,
              exclamationHeight, null);
          isBattleTextVisible = true;
        } else {
          isBattleTextVisible = false;
        }

        if (isBattleTextVisible) {
          graphics.setFont(new Font("Arial", Font.BOLD, 12));
          graphics.setColor(Color.BLACK);
          graphics.drawString("Press Enter to battle!", centerX - 75, centerY - 20);
        }
      }
    }
    if (myDungeon.getMyCurrX() == myGenerator.getWorldSize() - 1
        && myDungeon.getMyCurrY() == myGenerator.getWorldSize() - 1) {
      graphics.drawImage(myEndingImg, getWidth() / 2, FrameManager.getHeight() / 2, 50, 50, null);
      isEndingTextVisible = Math.abs(myPlayer.x - getWidth() / 2) <= 150
          && Math.abs(myPlayer.y - FrameManager.getHeight() / 2) <= 150;
      if (isEndingTextVisible && getMyLevel() == 1) {
        graphics.setFont(new Font("Arial", Font.BOLD, 12));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Press Enter to escape the dungeon floor!", getWidth() / 2 - 75,
            FrameManager.getHeight() / 2 - 20);
      } else if (isEndingTextVisible && getMyLevel() == 2 && !isCheckUnlocked) {
        graphics.setFont(new Font("Arial", Font.BOLD, 12));
        graphics.setColor(Color.BLACK);
        graphics.drawString("There's treasure to collect", getWidth() / 2 - 75,
            FrameManager.getHeight() / 2 - 20);
      } else if (isEndingTextVisible && getMyLevel() == 2 && isCheckUnlocked) {
        graphics.setFont(new Font("Arial", Font.BOLD, 12));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Press Enter to escape the dungeon floor!", getWidth() / 2 - 75,
            FrameManager.getHeight() / 2 - 20);
      }
    }

    if (getMyLevel() >= 3) {
      int whiteBoxX = 0;  // X position of the white box
      int whiteBoxY = 30;   // Y position of the white box
      int whiteBoxWidth = 80;  // Width of the white box
      int whiteBoxHeight = 80; // Height of the white box
      graphics.setColor(Color.WHITE);
      graphics.fillRect(whiteBoxX, whiteBoxY, whiteBoxWidth, whiteBoxHeight);
      graphics.drawImage(myWalkingMonster, 0, 30, 50,
          50, null);
      graphics.setColor(Color.BLACK);
      graphics.setFont(new Font("Arial", Font.BOLD, 12));
      graphics.drawString(myMonstersDefeated + "/25", 0, 105);
    }

    int x = getWidth() - 150;
    int y = 0;
    graphics.drawImage(myTImg, x, y, 30, 30, null);
    graphics.drawImage(myEscapeImg, x, 60, 30, 30, null);
    graphics.setColor(Color.white);
    graphics.drawString("Map Display", x + 40, y + 20);
    graphics.drawString("Menu Display", x + 40, 60 + 20);
  }

  /**
   * keyPressed method enables handling key press events
   *
   * @param keyCode is the code for the key pressed
   */
  @Override
  protected void keyPressed(int keyCode) {
    switch (keyCode) {
      case KeyEvent.VK_G:
        System.out.println(myPlayer.x);
        System.out.println(myPlayer.y);
        break;
      case KeyEvent.VK_F:
        if (myProgress[myHeroIndex][0] && myProgress[myHeroIndex][1])
          shootLightningBolt();
        break;
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        this.myPlayer.setMovingUp(true);
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        this.myPlayer.setMovingDown(true);
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        this.myPlayer.setMovingRight(true);
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        this.myPlayer.setMovingLeft(true);
        break;
      case KeyEvent.VK_ESCAPE:
        String escapePath = "src/Assets/Images/GoBack.jpeg";
        ImageIcon escapeIcon = FrameManager.resizeImage(escapePath, 125, 125);
        int choice = -1;
        while (choice != JOptionPane.YES_OPTION && choice != JOptionPane.NO_OPTION) {
          choice = JOptionPane.showConfirmDialog(null,
                  "Do you want to go back to the home screen?", "Quit Confirmation",
                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, escapeIcon);
        }
        if (choice == JOptionPane.YES_OPTION) {
          myGameScreenStack.clearStack();
          myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHeroIndex, myProgress));
        }
        break;
      case KeyEvent.VK_T:
        this.isMazeVisible = true;
        break;
      case KeyEvent.VK_ENTER:
        if (myDungeon.getMyCurrY() == myGenerator.getWorldSize() - 1
            && myDungeon.getMyCurrX() == myGenerator.getWorldSize() - 1) {
          if (isEndingTextVisible) {
            if (getMyLevel() == 1) {
              myProgress[myHeroIndex][myLevel - 1] = true;
              String imagePath = "src/Assets/Images/frogs.jpeg";
              ImageIcon icon = FrameManager.resizeImage(imagePath, 125, 125);
              SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null,
                    "Polymorphism in OOP lets objects of different types be treated as instances of a common superclass, enabling flexible and reusable code.",
                    "Level Complete", JOptionPane.INFORMATION_MESSAGE, icon);

                myGameScreenStack.clearStack();
                myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHeroIndex, myProgress));
              });
            }
            if (getMyLevel() == 2 && isCheckUnlocked) {
              myProgress[myHeroIndex][myLevel - 1] = true;
              String imagePath = "src/Assets/Images/Encaps.png";
              ImageIcon icon = FrameManager.resizeImage(imagePath, 125, 125);
              SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null,
                    "Encapsulation in OOP is the practice of bundling data and methods that operate on that data within a single unit",
                    "Level Complete", JOptionPane.INFORMATION_MESSAGE, icon);

                myGameScreenStack.clearStack();
                myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHeroIndex, myProgress));
              });
            }
            if (getMyLevel() == 3) {
              myProgress[myHeroIndex][myLevel - 1] = true;
              String imagePath = "src/Assets/Images/Inheritance.jpeg";
              ImageIcon icon = FrameManager.resizeImage(imagePath, 125, 125);
              SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null,
                    "Inheritance in OOP allows a class to inherit properties and behaviors from another class, promoting code reuse and establishing a hierarchical relationship between classes",
                    "Level Complete", JOptionPane.INFORMATION_MESSAGE, icon);

                myGameScreenStack.clearStack();
                myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHeroIndex, myProgress));
              });
            }
            if (getMyLevel() == 4) {
              myProgress[myHeroIndex][myLevel - 1] = true;
              String imagePath = "src/Assets/Images/abstraction.jpeg";
              ImageIcon icon = FrameManager.resizeImage(imagePath, 125, 125);
              SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null,
                    "Abstraction in OOP involves hiding the complex implementation details of a system and exposing only the essential features, simplifying interaction and reducing complexity for the user",
                    "Level Complete", JOptionPane.INFORMATION_MESSAGE, icon);

                myGameScreenStack.clearStack();
                myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHeroIndex, myProgress));
              });
            }
          }
        }
        if (isBattleTextVisible) {
          myGameScreenStack.addScreen(
              new BattleScreen(myGameScreenStack, myHero, myMonster));
          if (myDungeon.getMyCurrX() == myMonsterPos[0] && myDungeon.getMyCurrY() == myMonsterPos[1]
              && getMyLevel() > 1) {
            myChestImg = myOpenedChestImg;
            setChestUnlocked();
          }
        }
        break;
    }
  }


  /**
   * keyReleased method enables handling key release events
   *
   * @param keyCode is the code for the key pressed
   */
  @Override
  protected void keyReleased(int keyCode) {
    switch (keyCode) {
      case KeyEvent.VK_W, KeyEvent.VK_UP -> this.myPlayer.setMovingUp(false);
      case KeyEvent.VK_S, KeyEvent.VK_DOWN -> this.myPlayer.setMovingDown(false);
      case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> this.myPlayer.setMovingRight(false);
      case KeyEvent.VK_A, KeyEvent.VK_LEFT -> this.myPlayer.setMovingLeft(false);
      case KeyEvent.VK_T -> this.isMazeVisible = false;
    }

  }

  /**
   * playSoundEffect method plays sound effects
   * when their coordinating string name is passed
   *
   * @param theEffectName is the name of the sound effect to be played
   */
  @Override
  protected void playSoundEffect(String theEffectName) {

  }

  private void generateMaze(int theNum) {
    this.myGenerator.reset(theNum);
    while (!myGenerator.finished()) {
      myGenerator.generate();
    }
    this.myDungeon = new Dungeon(this.myGenerator.getMyRoomData(), myGenerator.getWorldSize(),
        getMyLevel());
  }

  public static File getRoom(HashSet<Direction> dirs) {
    if (dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH)
        && dirs.contains(MathHelper.Direction.EAST) && dirs.contains(MathHelper.Direction.NORTH))
      return new File("src/Assets/Directions/nswe.png");
    else if (dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH)
        && dirs.contains(MathHelper.Direction.EAST))
      return new File("src/Assets/Directions/nes.png");
    else if (dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH)
        && dirs.contains(MathHelper.Direction.EAST))
      return new File("src/Assets/Directions/sew.png");
    else if (dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH)
        && dirs.contains(MathHelper.Direction.NORTH))
      return new File("src/Assets/Directions/nsw.png");
    else if (dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.NORTH)
        && dirs.contains(MathHelper.Direction.EAST))
      return new File("src/Assets/Directions/new.png");
    else if (dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH))
      return new File("src/Assets/Directions/ns.png");
    else if (dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.EAST))
      return new File("src/Assets/Directions/we.png");
    else if (dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.EAST))
      return new File("src/Assets/Directions/ne.png");
    else if (dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.WEST))
      return new File("src/Assets/Directions/nw.png");
    else if (dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
      return new File("src/Assets/Directions/se.png");
    else if (dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.WEST))
      return new File("src/Assets/Directions/sw.png");
    else if (dirs.contains(MathHelper.Direction.NORTH))
      return new File("src/Assets/Directions/n.png");
    else if (dirs.contains(MathHelper.Direction.SOUTH))
      return new File("src/Assets/Directions/s.png");
    else if (dirs.contains(MathHelper.Direction.WEST))
      return new File("src/Assets/Directions/w.png");
    else if (dirs.contains(MathHelper.Direction.EAST))
      return new File("src/Assets/Directions/e.png");
    else
      return new File("src/Assets/Directions/deh.png");
  }


  public int[] generateMonsterPosition() {
    Random rand = new Random();
    int[] result = new int[getMyLevel() * 2];
    int worldSize = myGenerator.getWorldSize();
    for (int i = 0; i < getMyLevel(); i++) {
      do {
        result[i * 2] = rand.nextInt(worldSize);
        result[i * 2 + 1] = rand.nextInt(worldSize);
      } while ((result[i * 2] == 0 && result[i * 2 + 1] == 0) || (result[i * 2] == worldSize - 1
          && result[i * 2 + 1] == worldSize - 1));
    }
    return result;
  }

  public int getMyLevel() {
    return myLevel;
  }

  public void setChestUnlocked() {
    isCheckUnlocked = true;
  }

  public void adjustMonsterPos() {

    Random rand = new Random();
    int next = rand.nextInt(101);
    if (next > 0 && next < 11) {
      velocityXOne = -velocityXOne;
    } else if (next > 10 && next < 21) {
      velocityXTwo = -velocityXTwo;
    } else if (next > 20 && next < 31) {
      velocityXThree = -velocityXThree;
    }

    monsterPosXOne += velocityXOne;
    monsterPosYOne += velocityYOne;
    monsterPosXTwo += velocityXTwo;
    monsterPosYTwo += velocityYTwo;
    monsterPosXThree += velocityXThree;
    monsterPosYThree += velocityYThree;

    if (monsterPosXOne <= 130 || monsterPosXOne >= 600) {
      velocityXOne = -velocityXOne;
    }
    if (monsterPosYOne <= 100 || monsterPosYOne >= 400) {
      velocityYOne = -velocityYOne;
    }
    if (monsterPosXTwo <= 130 || monsterPosXTwo >= 600) {
      velocityXTwo = -velocityXTwo;
    }
    if (monsterPosYTwo <= 100 || monsterPosYTwo >= 400) {
      velocityYTwo = -velocityYTwo;
    }
    if (monsterPosXThree <= 130 || monsterPosXThree >= 600) {
      velocityXThree = -velocityXThree;
    }
    if (monsterPosYTwo <= 100 || monsterPosYTwo >= 400) {
      velocityYThree = -velocityYThree;
    }
  }

  private void shootLightningBolt() {
    int startX = (int) myPlayer.getX();
    int startY = (int) myPlayer.getY();
    int[] dir = myPlayer.getDirection();
    int velocityX = dir[0];
    int velocityY = dir[1];
    LightningBolt bolt = new LightningBolt(startX, startY, velocityX, velocityY);
    lightningBolts.add(bolt);
  }

  void handleMonsterCollision(){
    checkMonster(monsterPosXOne, monsterPosYOne);
    checkMonster(monsterPosXTwo, monsterPosYTwo);
    checkMonster(monsterPosXThree, monsterPosYThree);
  }

  void checkMonster(int theMonsterX, int theMonsterY){
      if(Math.abs(myPlayer.x - theMonsterX) <= 20 && Math.abs(myPlayer.y - theMonsterY) <= 20
          && isSafeRoom(myDungeon.getMyCurrX(), myDungeon.getMyCurrY()) && getMyLevel() > 2){
        System.out.println("calculate damage");
      }
  }

  boolean isSafeRoom(int theRoomX, int theRoomY){
    return !(theRoomX == 0 && theRoomY == 0)
        && (!(theRoomX == myGenerator.getWorldSize()-1)
        && !(theRoomY == myGenerator.getWorldSize()-1));
  }
}
