package Model;

import java.awt.Rectangle;

public class Tile extends Rectangle {

  private byte myTileID;
  private boolean myWall;
  private int mySizeX;
  private int mySizeY;

  public Tile(byte theID, int thePosXInRoom, int thePosYInRoom, boolean isWall, int theSizeX, int theSizeY){
    super(thePosXInRoom * theSizeX, thePosYInRoom * theSizeY, theSizeX, theSizeY);
    this.myTileID = theID;
    this.myWall = isWall;
    this.mySizeX = theSizeX;
    this.mySizeY = theSizeY;
  }

  public byte getID() {
    return myTileID;
  }

  public boolean isMyWall() {
    return myWall;
  }
}
