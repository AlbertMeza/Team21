package Model;

import Controller.MathHelper;
import Controller.MathHelper.Direction;
import Controller.RoomData;
import View.FrameManager;
import java.util.HashSet;
/**
 * The Dungeon class represents a dungeon consisting of multiple rooms.
 * The dungeon is initialized based on the provided room data and world size.
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class Dungeon {
    private final Room[][] myRooms;
    private int myCurrX;
    private int myCurrY;

    /**
     * Constructs a new Dungeon instance.
     *
     * @param theRoomsData  A 2D array of HashSets containing directions indicating room connectivity.
     * @param theWorldSize  The size of the world (number of rooms along one dimension).
     * @param theLevel      The level of the dungeon.
     */
    public Dungeon(HashSet<MathHelper.Direction>[][] theRoomsData, int theWorldSize, int theLevel){
        this.myRooms = new Room[theRoomsData.length][theRoomsData[0].length];
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

    /**
     * Gets the current room in the dungeon where the player is located.
     *
     * @return The current Room instance.
     */
    public Room getRoom() {
        return myRooms[myCurrX][myCurrY];
    }

    /**
     * Changes the current room based on the player's position.
     * If the player moves out of the current room's bounds, the room is updated
     * and the player's position is adjusted accordingly.
     *
     * @param thePlayer The player whose position is used to determine room changes.
     */
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

    /**
     * Gets the current X coordinate of the player in the dungeon.
     *
     * @return The current X coordinate.
     */
    public int getMyCurrX() {
        return myCurrX;
    }
    /**
     * Gets the current Y coordinate of the player in the dungeon.
     *
     * @return The current Y coordinate.
     */
    public int getMyCurrY() {
        return myCurrY;
    }
}
