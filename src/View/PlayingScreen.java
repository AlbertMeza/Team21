package View;

import Controller.MathHelper;
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

public class PlayingScreen extends GameScreen {

  private MazeGenerator generator;
  private Dungeon dungeon;
  private boolean[][] visited;
  private boolean showMaze;
  private int monsterX;
  private int monsterY;

  private Image monsterImg;
  private PlayableHero player;

  private int myLevel;

  private Image myEndingImg;


    protected PlayingScreen(GameScreenStack theStack, int theNum, int theLevel) {
    super(theStack);
    this.generator = new MazeGenerator();
    this.myLevel = theLevel;
    this.generateMaze(theNum);
    this.visited = new boolean[theNum][theNum];
    visited[0][0] = true;
    visited[theNum-1][theNum-1] = true;
    this.player = new PlayableHero((byte)0, FrameManager.getWidth()/2, FrameManager.getHeight()/2);
    this.showMaze = false;
    int[] monsterPos = generateMonsterPosition();
    this.monsterX = monsterPos[0];
    this.monsterY = monsterPos[1];
    try {
      if(theLevel == 4) {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/AbstractionGem.png"));
      } else if (theLevel == 3) {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/InheritanceTree.png"));
      } else if (theLevel == 2) {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/Encapsulation.png"));
      } else {
        myEndingImg = ImageIO.read(new File("src/Assets/Images/PolymorphismCup.png"));
      }
      monsterImg = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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

  @Override
  protected void render(Graphics graphics) {
      dungeon.getRoom().getMyData().render(graphics);
    this.player.render(graphics);
    if(showMaze){
      for(int i = 0; i < generator.getWORLD_SIZE(); i++){
        for(int j = 0; j < generator.getWORLD_SIZE(); j++){
          int x = FrameManager.getWidth() - (generator.getWORLD_SIZE() - j) * 33;
          int y = FrameManager.getHeight() - (generator.getWORLD_SIZE() - i) * 24;
          if(visited[i][j]) {
            Image image = null;
            try {
              image = ImageIO.read(getRoom(generator.getDirForRoom(j, i)));
            } catch (IOException e) {
              e.printStackTrace();
            }
            graphics.drawImage(image, x, y, 33, 24, null);
          }
          else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y, 33, 24);
          }
        }
      }
    }
    if(dungeon.getMyCurrX() + dungeon.getMyCurrY() == 1){
        graphics.drawImage(monsterImg, FrameManager.getWidth() / 2, FrameManager.getHeight() / 2, 33, 24, null);
    }
    if(dungeon.getMyCurrX() == generator.getWORLD_SIZE() - 1 && dungeon.getMyCurrY()  == generator.getWORLD_SIZE() - 1 ){
      graphics.drawImage(myEndingImg, FrameManager.getWidth() / 2, FrameManager.getHeight() / 2, 50, 50, null);
    }
  }

  @Override
  protected void keyPressed(int keyCode) {
    switch(keyCode) {
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
      case KeyEvent.VK_T:
        this.showMaze = true;
        break;
        case KeyEvent.VK_ENTER:
          if(dungeon.getMyCurrY() + dungeon.getMyCurrX() == 1){
//            gameScreenStack.addScreen();
          }
          break;

    }
  }

  @Override
  protected void keyReleased(int keyCode) {
    switch(keyCode) {
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

  @Override
  protected void playSoundEffect(String theEffectName) {

  }

  private void generateMaze(int theNum) {
    this.generator.reset(theNum);
    while(!generator.finished()) {
      generator.generate();
    }
    this.dungeon = new Dungeon(this.generator.getMyRoomData(), generator.getWORLD_SIZE(), getMyLevel());
  }

	public static File getRoom(HashSet<Direction> dirs) {
		if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST) && dirs.contains(MathHelper.Direction.NORTH))
			return new File("src/Assets/Directions/nswe.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/nes.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/sew.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.NORTH))
			return new File("src/Assets/Directions/nsw.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/new.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH))
			return new File("src/Assets/Directions/ns.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/we.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/ne.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.WEST))
			return new File("src/Assets/Directions/nw.png");
		else if(dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/se.png");
		else if(dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.WEST))
			return new File("src/Assets/Directions/sw.png");
		else if(dirs.contains(MathHelper.Direction.NORTH))
			return new File("src/Assets/Directions/n.png");
		else if(dirs.contains(MathHelper.Direction.SOUTH))
			return new File("src/Assets/Directions/s.png");
		else if(dirs.contains(MathHelper.Direction.WEST))
			return new File("src/Assets/Directions/w.png");
		else if(dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/e.png");
		else
			return new File("src/Assets/Directions/deh.png");
	}


  public int[] generateMonsterPosition() {
    Random rand = new Random();
    int[] result = new int[2];
    int worldSize = generator.getWORLD_SIZE();
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
