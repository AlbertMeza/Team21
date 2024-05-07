package View;

import Controller.MathHelper;
import Controller.MathHelper.Direction;
import Controller.MazeGenerator;
import Controller.RoomData;
import Model.Dungeon;
import Model.PlayableHero;
import Model.GameScreen;
import Model.GameScreenStack;
import Model.Room;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

public class PlayingScreen extends GameScreen {

  private MazeGenerator generator;
  private Dungeon dungeon;
  private PlayableHero player;

    protected PlayingScreen(GameScreenStack stack) {
    super(stack);
    generator = new MazeGenerator();
      this.generateMaze();
    this.player = new PlayableHero((byte)0, FrameManager.getWidth()/2, FrameManager.getHeight()/2);

//    try {
//      roomImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  @Override
  protected void loop() {
    this.player.move();
    this.dungeon.changeRoom(player);

    RoomData roomIn = this.dungeon.getRoom().getData();
    for(int i=0; i < roomIn.getSizeX(); i++){
      for(int j=0; j < roomIn.getSizeY(); j++){
        this.player.handleCollisionWith(this.dungeon.getRoom().getData().getTileAt(i, j));
      }
    }
  }

  @Override
  protected void render(Graphics graphics) {
//    Image imageOne = null;
//    for(int i = 0; i < generator.getWORLD_SIZE(); i++){
//      for(int j = 0; j < generator.getWORLD_SIZE(); j++) {
//        try {
//        imageOne = ImageIO.read(getRoom(generator.getDataForRoom(j, i)));
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//        graphics.drawImage(imageOne, j*50, i*50, 50,50,null);
//      }
//    }
      //tempRoom.getData().render(graphics);
      dungeon.getRoom().getData().render(graphics);

      //Maze Drawing
//      for(int i = 0; i < generator.getWORLD_SIZE(); i++){
//        for(int j = 0; j < generator.getWORLD_SIZE(); j++){
//          Image image = null;
//          Image imageTwo = null;
//          try {
//          image = ImageIO.read(getRoom(dungeon.getRoom(j, i).getData().getExits()));
//          imageTwo = ImageIO.read(getRoom(generator.getDirForRoom(j, i)));
//          } catch (IOException e) {
//          e.printStackTrace();
//          }
//          graphics.drawImage(imageTwo, 400+j*30, 200+i*30, 30, 30, null);
//        }
//      }
    this.player.render(graphics);

  }

  @Override
  protected void keyPressed(int keyCode) {
//
//    if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP && roomY > 0) this.roomY--;
//    else if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT && roomX > 0) this.roomX--;
//    else if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN && roomY < generator.getWORLD_SIZE()-1) this.roomY++;
//    else if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT && roomX < generator.getWORLD_SIZE()-1) this.roomX++;
//    else if(keyCode == KeyEvent.VK_ENTER) this.generateMaze();
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
    }

  }

  @Override
  protected void playBackgroundMusic() {

  }

  @Override
  protected void stopBackgroundMusic() {

  }

  @Override
  protected void playSoundEffect(String theEffectName) {

  }

  private void generateMaze() {
    this.generator.reset(5);
    while(!generator.finished()) {
      generator.generate(5);
    }
    this.dungeon = new Dungeon(this.generator.getRoomData(), generator.getWORLD_SIZE());
    for(int i = 0; i < generator.getWORLD_SIZE(); i++){
      for(int j = 0; j < generator.getWORLD_SIZE(); j++){
        System.out.println(generator.getDirForRoom(j,i).toString());
      }
    }
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

  private void collision(){}

}
