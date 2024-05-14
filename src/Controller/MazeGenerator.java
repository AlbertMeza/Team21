package Controller;

import Controller.MathHelper.Direction;
import java.util.HashSet;

public class MazeGenerator {
  private int WORLD_SIZE = 5;
  private int myPosX;
  private int myPosY;

  private HashSet<MathHelper.Direction>[][] myRoomData;
  private boolean myGenerated[][];


  @SuppressWarnings("unchecked")
  public void reset(int theSize){
    WORLD_SIZE = theSize;
    myRoomData = new HashSet[WORLD_SIZE][WORLD_SIZE];
    myGenerated = new boolean[WORLD_SIZE][WORLD_SIZE];
    for(int i=0; i < myRoomData.length; i++){
      for(int j=0; j < myRoomData[i].length; j++){
        this.myRoomData[i][j] = new HashSet<>();
        this.myGenerated[i][j] = false;
      }
    }
    setRandomPosition();
  }
  public void generate() {
    MathHelper.Direction direction = MathHelper.randomDirection();
    if(this.isValidPosition(myPosX + direction.myDirX, myPosY + direction.myDirY)) {
      if(!this.myGenerated[myPosX + direction.myDirX][myPosY + direction.myDirY]) {
        this.myRoomData[myPosX][myPosY].add(direction);
        this.myRoomData[myPosX + direction.myDirX][myPosY + direction.myDirY].add(direction.myOpposite);
      }
      this.myPosX += direction.myDirX;
      this.myPosY += direction.myDirY;
      this.myGenerated[myPosX][myPosY] = true;
    }
    else {
      this.generate();
    }
  }

  private void move() {
    switch (MathHelper.randomDirection()) {
      case NORTH:
        if(isValidPosition(myPosX, myPosY -1)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.NORTH)) this.myRoomData[myPosX][myPosY].add(Direction.NORTH);
          this.myPosY++;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.SOUTH) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.SOUTH);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
      case SOUTH:
        if(isValidPosition(myPosX, myPosY +1)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.SOUTH)) this.myRoomData[myPosX][myPosY].add(Direction.SOUTH);
          this.myPosY--;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.NORTH) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.NORTH);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
      case WEST:
        if(isValidPosition(myPosX -1, myPosY)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.WEST)) this.myRoomData[myPosX][myPosY].add(Direction.WEST);
          this.myPosX--;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.EAST) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.EAST);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
      case EAST:
        if(isValidPosition(myPosX -1, myPosY)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.EAST)) this.myRoomData[myPosX][myPosY].add(Direction.EAST);
          this.myPosX++;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.WEST) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.WEST);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
    }
  }
  private void setRandomPosition(){
    this.myPosX = MathHelper.randomInt(WORLD_SIZE);
    this.myPosY = MathHelper.randomInt(WORLD_SIZE);
    this.myGenerated[myPosX][myPosY] = true;
  }

  private boolean isValidPosition(int theX, int theY) {
    return theX >= 0 && theY >= 0 && theX < WORLD_SIZE && theY < WORLD_SIZE;
  }

  public boolean finished() {
    for (int i = 0; i < myGenerated.length; i++){
      for(int j = 0; j < myGenerated[i].length; j++) {
        if (!this.myGenerated[i][j]) return false;
      }
    }
    return true;
  }

  public HashSet<Direction> getDirForRoom(int theX, int theY){
    return this.myRoomData[theX][theY];
  }

  public int getWORLD_SIZE(){
    return WORLD_SIZE;
  }

  public HashSet<MathHelper.Direction>[][] getMyRoomData(){
    return this.myRoomData;
  }
}
