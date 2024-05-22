package View;

import Controller.MathHelper.Direction;
import Controller.MazeGenerator;
import Controller.RoomData;
import Model.Dungeon;
import Model.PlayableHero;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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

  private final MazeGenerator generator;
  private Dungeon dungeon;
  private final boolean[][] visited;
  private boolean showMaze;
//  private final int monsterX;
//  private final int monsterY;

  private Image monsterImg;
  private Image heroImg;
  private Image myTImg;
  private Image myEscapeImg;
  private final PlayableHero player;

  private final int myLevel;

  private Image myEndingImg;
  private final boolean[][] myProgress;
  private final int myHeroIndex;


    protected PlayingScreen(GameScreenStack theStack, Image theHeroImg, int theMazeSize,int theHeroIndex, int theLevel, boolean[][] theProgress) {
      super(theStack);
      this.generator = new MazeGenerator();
      this.myLevel = theLevel;
      this.generateMaze(theMazeSize);
      this.visited = new boolean[theMazeSize][theMazeSize];
      visited[0][0] = true;
      visited[theMazeSize - 1][theMazeSize - 1] = true;
      this.player = new PlayableHero((byte) 0, getWidth() / 2, FrameManager.getHeight() / 2, theHeroImg);
      this.showMaze = false;
//      int[] monsterPos = generateMonsterPosition();
//      this.monsterX = monsterPos[0];
//      this.monsterY = monsterPos[1];
      try {
        if (theLevel == 4) {
          myEndingImg = ImageIO.read(new File("src/Assets/Images/AbstractionGem.png"));
        } else if (theLevel == 3) {
          myEndingImg = ImageIO.read(new File("src/Assets/Images/InheritanceTree.png"));
        } else if (theLevel == 2) {
          myEndingImg = ImageIO.read(new File("src/Assets/Images/Encapsulation.png"));
        } else {
          myEndingImg = ImageIO.read(new File("src/Assets/Images/PolymorphismCup.png"));
        }

        monsterImg = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
        myEscapeImg = ImageIO.read(new File("src/Assets/Images/Escape.jpg"));
        myTImg = ImageIO.read(new File("src/Assets/Images/T.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      myProgress = theProgress;
      myHeroIndex = theHeroIndex;
    }

  /**
   * loop method is for any looping events during game play
   */
  @Override
  protected void loop() {
    this.player.move();
    this.dungeon.changeRoom(player);
    visited[dungeon.getMyCurrY()][dungeon.getMyCurrX()] = true;
    RoomData roomIn = this.dungeon.getRoom().getMyData();
    for(int i=0; i < roomIn.getSizeX(); i++){
      for(int j=0; j < roomIn.getSizeY(); j++){
        this.player.handleCollisionWith(this.dungeon.getRoom().getMyData().getTileAt(i, j));
      }
    }
  }

  /**
   * render method displays the screen
   *
   * @param graphics is the graphics for game controller
   */
  @Override
  protected void render(Graphics graphics) {
      dungeon.getRoom().getMyData().render(graphics);
    this.player.render(graphics);
    if(showMaze){
      for(int i = 0; i < generator.getWorldSize(); i++){
        for(int j = 0; j < generator.getWorldSize(); j++){
          int x = getWidth() - (generator.getWorldSize() - j) * 33;
          int y = FrameManager.getHeight() - (generator.getWorldSize() - i) * 24;
          if(visited[i][j]) {
            Image image = null;
            try {
              image = ImageIO.read(getRoom(generator.getDirForRoom(j, i)));
            } catch (IOException e) {
              e.printStackTrace();
            }
            graphics.drawImage(image, x, y, 33, 24, null);
            if(j == dungeon.getMyCurrX() && i == dungeon.getMyCurrY()){
              graphics.setColor(Color.BLUE);
              graphics.fillRect(x+10, y+8, 12, 10);
            }
          }
          else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y, 33, 24);
          }
        }
      }
    }
    if(dungeon.getMyCurrX() + dungeon.getMyCurrY() == 1){
        graphics.drawImage(monsterImg, getWidth() / 2, FrameManager.getHeight() / 2, 33, 24, null);
    }
    if(dungeon.getMyCurrX() == generator.getWorldSize() - 1 && dungeon.getMyCurrY()  == generator.getWorldSize() - 1 ){
      graphics.drawImage(myEndingImg, getWidth() / 2, FrameManager.getHeight() / 2, 50, 50, null);
    }

    int x = getWidth() - 150;
    int y = 0;
    graphics.drawImage(myTImg, x,y, 30, 30, null);
    graphics.drawImage(myEscapeImg, x,60, 30,30, null);
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
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        this.player.setMovingUp(true);
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        this.player.setMovingDown(true);
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        this.player.setMovingRight(true);
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        this.player.setMovingLeft(true);
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
        this.showMaze = true;
        break;
      case KeyEvent.VK_ENTER:
        if (dungeon.getMyCurrY() + dungeon.getMyCurrX() == generator.getWorldSize() * 2 - 2) {
          myProgress[myHeroIndex][myLevel - 1] = true;
        }
        String imagePath = "src/Assets/Images/frogs.jpeg";
        ImageIcon icon = FrameManager.resizeImage(imagePath, 125, 125);
        SwingUtilities.invokeLater(() -> {
          JOptionPane.showMessageDialog(null, "Polymorphism in OOP lets objects of different types be treated as instances of a common superclass, enabling flexible and reusable code.",
                  "Level Complete", JOptionPane.INFORMATION_MESSAGE, icon);

          myGameScreenStack.clearStack();
          myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHeroIndex, myProgress));
        });
        break;
    }
  }


  /**
   * keyReleased method enables handling key release events
   *
   * @param keyCode is the code for the key pressed
   */
  @Override
  protected void keyReleased(int keyCode){
      switch (keyCode) {
        case KeyEvent.VK_W:
        case KeyEvent.VK_UP:
          this.player.setMovingUp(false);
          break;
        case KeyEvent.VK_S:
        case KeyEvent.VK_DOWN:
          this.player.setMovingDown(false);
          break;
        case KeyEvent.VK_D:
        case KeyEvent.VK_RIGHT:
          this.player.setMovingRight(false);
          break;
        case KeyEvent.VK_A:
        case KeyEvent.VK_LEFT:
          this.player.setMovingLeft(false);
          break;
        case KeyEvent.VK_T:
          this.showMaze = false;
          break;
      }
  }


  /**
   * stopBackgroundMusic method stops background music.
   */
  @Override
  protected void stopBackgroundMusic() {

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
    this.generator.reset(theNum);
    while(!generator.finished()) {
      generator.generate();
    }
    this.dungeon = new Dungeon(this.generator.getMyRoomData(), generator.getWorldSize(), getMyLevel());
  }

	public static File getRoom(HashSet<Direction> dirs) {
		if(dirs.contains(Direction.WEST) && dirs.contains(Direction.SOUTH) && dirs.contains(Direction.EAST) && dirs.contains(Direction.NORTH))
			return new File("src/Assets/Directions/nswe.png");
		else if(dirs.contains(Direction.NORTH) && dirs.contains(Direction.SOUTH) && dirs.contains(Direction.EAST))
			return new File("src/Assets/Directions/nes.png");
		else if(dirs.contains(Direction.WEST) && dirs.contains(Direction.SOUTH) && dirs.contains(Direction.EAST))
			return new File("src/Assets/Directions/sew.png");
		else if(dirs.contains(Direction.WEST) && dirs.contains(Direction.SOUTH) && dirs.contains(Direction.NORTH))
			return new File("src/Assets/Directions/nsw.png");
		else if(dirs.contains(Direction.WEST) && dirs.contains(Direction.NORTH) && dirs.contains(Direction.EAST))
			return new File("src/Assets/Directions/new.png");
		else if(dirs.contains(Direction.NORTH) && dirs.contains(Direction.SOUTH))
			return new File("src/Assets/Directions/ns.png");
		else if(dirs.contains(Direction.WEST) && dirs.contains(Direction.EAST))
			return new File("src/Assets/Directions/we.png");
		else if(dirs.contains(Direction.NORTH) && dirs.contains(Direction.EAST))
			return new File("src/Assets/Directions/ne.png");
		else if(dirs.contains(Direction.NORTH) && dirs.contains(Direction.WEST))
			return new File("src/Assets/Directions/nw.png");
		else if(dirs.contains(Direction.SOUTH) && dirs.contains(Direction.EAST))
			return new File("src/Assets/Directions/se.png");
		else if(dirs.contains(Direction.SOUTH) && dirs.contains(Direction.WEST))
			return new File("src/Assets/Directions/sw.png");
		else if(dirs.contains(Direction.NORTH))
			return new File("src/Assets/Directions/n.png");
		else if(dirs.contains(Direction.SOUTH))
			return new File("src/Assets/Directions/s.png");
		else if(dirs.contains(Direction.WEST))
			return new File("src/Assets/Directions/w.png");
		else if(dirs.contains(Direction.EAST))
			return new File("src/Assets/Directions/e.png");
		else
			return new File("src/Assets/Directions/deh.png");
	}


  public int[] generateMonsterPosition() {
    Random rand = new Random();
    int[] result = new int[2];
    int worldSize = generator.getWorldSize();
    do {
      result[0] = rand.nextInt(worldSize+1);
      result[1] = rand.nextInt(worldSize+1);
    } while ((result[0] == 0 && result[1] == 0) || (result[0] == worldSize && result[1] == worldSize));

    return result;
  }

  public int getMyLevel() {
    return myLevel;
  }

}
