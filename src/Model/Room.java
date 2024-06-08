package Model;

import Controller.RoomData;

/**
 * Represents a room in the dungeon.
 */
public class Room {


  private RoomData myData;
  /**
   * Constructs a new Room with the specified RoomData.
   *
   * @param theData The data associated with this room.
   */
  public Room(RoomData theData) {
    this.myData = theData;
  }
  /**
   * Gets the data associated with this room.
   *
   * @return The RoomData instance.
   */
  public RoomData getMyData() {
    return myData;
  }

}
