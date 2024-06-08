package Model;

import java.awt.Rectangle;

/**
 * Represents a tile in a room of the dungeon.
 */
public class Tile extends Rectangle {

  private byte myTileID;
  private boolean myWall;
  private int mySizeX;
  private int mySizeY;
  /**
   * Constructs a new Tile with the specified parameters.
   *
   * @param theID The unique ID of the tile.
   * @param thePosXInRoom The x-position of the tile within the room grid.
   * @param thePosYInRoom The y-position of the tile within the room grid.
   * @param isWall True if the tile is a wall, false otherwise.
   * @param theSizeX The width of the tile.
   * @param theSizeY The height of the tile.
   */
  public Tile(byte theID, int thePosXInRoom, int thePosYInRoom, boolean isWall, int theSizeX, int theSizeY){
    super(thePosXInRoom * theSizeX, thePosYInRoom * theSizeY, theSizeX, theSizeY);
    this.myTileID = theID;
    this.myWall = isWall;
    this.mySizeX = theSizeX;
    this.mySizeY = theSizeY;
  }

  /**
   * Gets the unique ID of the tile.
   *
   * @return The tile's ID.
   */
  public byte getID() {
    return myTileID;
  }

  /**
   * Checks if the tile is a wall.
   *
   * @return True if the tile is a wall, false otherwise.
   */
  public boolean isMyWall() {
    return myWall;
  }
}
