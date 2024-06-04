package Model;

import Controller.MathHelper;
import Controller.MathHelper.Direction;
import Controller.RoomData;
import View.FrameManager;
import java.util.HashSet;

public class Dungeon {
    private Room[][] myRooms;
    private int myCurrX;
    private int myCurrY;
    private int myWorldSize;
    public Dungeon(HashSet<MathHelper.Direction>[][] theRoomsData, int theWorldSize, int theLevel){
        this.myRooms = new Room[theRoomsData.length][theRoomsData[0].length];
        this.myWorldSize = theWorldSize;
        for(int i=0; i < theWorldSize; i++){
            for(int j =0; j < theWorldSize; j++) {
                if (theRoomsData[i][j].contains(Direction.NORTH) && theRoomsData[i][j].contains(
                    Direction.EAST) &&
                    theRoomsData[i][j].contains(Direction.SOUTH) && theRoomsData[i][j].contains(
                    Direction.WEST)) {

                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST));
                } else if (theRoomsData[i][j].contains(Direction.NORTH) &&
                    theRoomsData[i][j].contains(Direction.SOUTH) && theRoomsData[i][j].contains(
                    Direction.WEST)) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.NORTH, Direction.SOUTH, Direction.WEST));
                } else if (theRoomsData[i][j].contains(Direction.NORTH) &&
                    theRoomsData[i][j].contains(Direction.EAST) && theRoomsData[i][j].contains(
                    Direction.WEST)) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.NORTH, Direction.WEST, Direction.EAST));
                } else if (theRoomsData[i][j].contains(Direction.NORTH) &&
                    theRoomsData[i][j].contains(Direction.SOUTH) && theRoomsData[i][j].contains(
                    Direction.EAST)) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.NORTH, Direction.SOUTH, Direction.EAST));
                }  else if (theRoomsData[i][j].contains(Direction.EAST) &&
                    theRoomsData[i][j].contains(Direction.SOUTH) && theRoomsData[i][j].contains(
                    Direction.WEST)) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.EAST, Direction.SOUTH, Direction.WEST));
                } else if (theRoomsData[i][j].contains(Direction.EAST) &&
                    theRoomsData[i][j].contains(Direction.NORTH) ) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.EAST, Direction.NORTH));
                } else if (theRoomsData[i][j].contains(Direction.SOUTH) &&
                    theRoomsData[i][j].contains(Direction.NORTH) ) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.SOUTH, Direction.NORTH));
                }else if (theRoomsData[i][j].contains(Direction.WEST) &&
                    theRoomsData[i][j].contains(Direction.NORTH) ) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.WEST, Direction.NORTH));
                } else if (theRoomsData[i][j].contains(Direction.SOUTH) &&
                    theRoomsData[i][j].contains(Direction.EAST) ) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.SOUTH, Direction.EAST));
                }else if (theRoomsData[i][j].contains(Direction.WEST) &&
                    theRoomsData[i][j].contains(Direction.EAST) ) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.WEST, Direction.EAST));
                }else if (theRoomsData[i][j].contains(Direction.WEST) &&
                    theRoomsData[i][j].contains(Direction.SOUTH) ) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.SOUTH, Direction.EAST));
                } else if (theRoomsData[i][j].contains(Direction.NORTH)) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.NORTH));
                } else if (theRoomsData[i][j].contains(Direction.EAST)) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.EAST));
                } else if (theRoomsData[i][j].contains(Direction.SOUTH)) {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.SOUTH));
                } else {
                    this.myRooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}}, theLevel,
                        FrameManager.getWidth() / theWorldSize, FrameManager.getHeight() / theWorldSize,
                        Direction.WEST));
                }
            }
        }
        this.myCurrX = 0;
        this.myCurrY = 0;
    }

    public Room getRoom() {
        return myRooms[myCurrX][myCurrY];
    }

    public void changeRoom(PlayableHero thePlayer){
        if (thePlayer.getCenterX() < 0) {
            this.myCurrX--;
            thePlayer.setCenterX(FrameManager.getWidth());
        }
        else if (thePlayer.getCenterX() > FrameManager.getWidth()) {
            this.myCurrX++;
            thePlayer.setCenterX(0);
        }
        if (thePlayer.getCenterY() < 0) {
            this.myCurrY--;
            thePlayer.setCenterY(FrameManager.getHeight());
        }
        else if (thePlayer.getCenterY() > FrameManager.getHeight()) {
            this.myCurrY++;
            thePlayer.setCenterY(0);
        }
    }

    public int getMyCurrX() {
        return myCurrX;
    }

    public int getMyCurrY() {
        return myCurrY;
    }
}
