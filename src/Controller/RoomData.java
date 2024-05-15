package Controller;

import Controller.MathHelper.Direction;
import Model.Tile;
import View.FrameManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

public class RoomData {

  public static final int SIZE_X = FrameManager.getWidth() / 5;
  public static final int SIZE_Y = FrameManager.getHeight() / 5;
  private BufferedImage myWallTexture;
  private BufferedImage myTileTexture;
  private BufferedImage myLeftWall;
  private BufferedImage myRightWall;
  private BufferedImage myBlank;

  private Tile[][] myTilesData;
  private HashSet<Direction> myExits;
  private int myWidth;
  private int myHeight;
  private int myLevel;
  public RoomData(byte[][] theTilesData, int theLevel, int theWidth, int theHeight, MathHelper.Direction... theExits) {
    this.myTilesData = new Tile[theTilesData.length][theTilesData[0].length];
    for(int i=0;i<this.myTilesData.length;i++) {
      for(int j=0;j<this.myTilesData[i].length;j++) {
        this.myTilesData[i][j] = new Tile(
            theTilesData[i][j], j, i, theTilesData[i][j] == 1 || theTilesData[i][j] == 2, SIZE_X, SIZE_Y);
      }
    }
    this.myExits = new HashSet<>();
    for(MathHelper.Direction direction : theExits) {
      this.myExits.add(direction);
    }
    this.myWidth = theWidth;
    this.myHeight = theHeight;
    this.myLevel = theLevel;
    try {
      if (getMyLevel() == 2) {
        myWallTexture = ImageIO.read(new File("src/Assets/Images/Level2Bottom.png"));
        myTileTexture = ImageIO.read(new File("src/Assets/Images/Level2Tile.png"));
        myLeftWall = ImageIO.read(new File("src/Assets/Images/Level2Left.png"));
        myRightWall = ImageIO.read(new File("src/Assets/Images/Level2Right.png"));
        myBlank = ImageIO.read(new File("src/Assets/Images/Level2Blank.png"));
      } else if (getMyLevel() == 3) {
        myWallTexture = ImageIO.read(new File("src/Assets/Images/Level3Wall.png"));
        myTileTexture = ImageIO.read(new File("src/Assets/Images/Level3Tile.png"));
        myLeftWall = ImageIO.read(new File("src/Assets/Images/Level3LeftWall.png"));
        myRightWall = ImageIO.read(new File("src/Assets/Images/Level3RightWall.png"));
        myBlank = ImageIO.read(new File("src/Assets/Images/Level3Blank.png"));
      } else if (getMyLevel() == 4) {
        myWallTexture = ImageIO.read(new File("src/Assets/Images/Level4Wall.png"));
        myTileTexture = ImageIO.read(new File("src/Assets/Images/Level4Tile.png"));
        myLeftWall = ImageIO.read(new File("src/Assets/Images/Level4Left.png"));
        myRightWall = ImageIO.read(new File("src/Assets/Images/Level4Right.png"));
        myBlank = ImageIO.read(new File("src/Assets/Images/Level4Blank.png"));
      }
      else {
        myWallTexture = ImageIO.read(new File("src/Assets/Images/Wall.png"));
        myTileTexture = ImageIO.read(new File("src/Assets/Images/Tile.png"));
        myLeftWall = ImageIO.read(new File("src/Assets/Images/leftWall.png"));
        myRightWall = ImageIO.read(new File("src/Assets/Images/rightWall.png"));
        myBlank = ImageIO.read(new File("src/Assets/Images/blank.png"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int getSizeX() {
    return myTilesData.length;
  }

  public int getSizeY() {
    return myTilesData[0].length;
  }

  public void render(Graphics theGraphics) {
    for(int i =0; i < myTilesData.length; i++){
      for(int j=0; j < myTilesData[i].length; j++){
        if((i == 0 && j == 0) || (i == myTilesData.length-1 && j == 0)
            || (i == myTilesData.length -1 && j == myTilesData.length -1)
            || (i == 0 && j == myTilesData.length -1)) {
          theGraphics.drawImage(myBlank, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        } else if(this.myTilesData[i][j].getID() == 0) {
          theGraphics.drawImage(myTileTexture, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        } else if (this.myTilesData[i][j].getID() == 1 && j == 0) {
          theGraphics.drawImage(myLeftWall, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        } else if (this.myTilesData[i][j].getID() == 1 && j == myTilesData[i].length -1) {
          theGraphics.drawImage(myRightWall, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        }
        else {
          theGraphics.drawImage(myWallTexture, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        }

      }
    }
  }

  public HashSet<MathHelper.Direction> getMyExits() {
    return myExits;
  }

  public Tile getTileAt(int theX, int theY) {
    return myTilesData[theX][theY];
  }

  public int getMyLevel() {
    return myLevel;
  }
}
