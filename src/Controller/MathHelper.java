package Controller;

import java.util.Random;

/**
 * MathHelper class provides various math related functions for the game
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class MathHelper {

  /**
   * RANDOM constant is for all random generation number in the game
   */
  private static final Random RANDOM = new Random();

  /**
   * randomInt method provides a random value between 0 and
   * the passed param upper bound, not including the upper bound
   *
   * @param theUpperBound is the value 1 more than the max random value
   * @return returns a random int between 0 inclusive and the upperBound exclusive
   */
  public static int randomInt(int theUpperBound){
    return RANDOM.nextInt(theUpperBound);
  }

  /**
   * randomDirection method provides a random direction enum
   *
   * @return returns a random direction enum
   */
  public static Direction randomDirection() {
    return Direction.values()[RANDOM.nextInt(Direction.values().length)];
  }

  /**
   * nested Direction enum is for game directions
   */
  public enum Direction {

    /**
     * NORTH enum is the direction toward top of the screen
     */
    NORTH(0, -1),

    /**
     * SOUTH enum is the direction toward bottom of the screen
     */
    SOUTH(0,1),

    /**
     * WEST enum is the direction toward left side of the screen
     */
    WEST(-1, 0),

    /**
     * EAST enum is the direction toward right side of the screen
     */
    EAST(1,0);

    /**
     * dirX variable is the change in x for the direction of the enum
     */
    public int myDirX;

    /**
     * dirY variable is the change in y for the direction of the enum
     */
    public int myDirY;

    /**
     * opposite variable is the opposite direction enum for each direction enum
     */
    public Direction myOpposite;

    /**
     * declares opposite directions for each direction
     */
    static {
      NORTH.myOpposite = SOUTH;
      SOUTH.myOpposite = NORTH;
      WEST.myOpposite = EAST;
      EAST.myOpposite = WEST;
    }

    /**
     * Direction constructor creates a direction enum based on
     * changes in x and y.
     *
     * @param theDirX is the change in x
     * @param theDirY is the change in y
     */
    private Direction(int theDirX, int theDirY) {
      this.myDirX = theDirX;
      this.myDirY = theDirY;
    }
  }
}
