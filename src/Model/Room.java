package Model;

import Controller.RoomData;

public class Room {

  private RoomData myData;

  public Room(RoomData theData) {
    this.myData = theData;
  }

  public RoomData getMyData() {
    return myData;
  }

}
