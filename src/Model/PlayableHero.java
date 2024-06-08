package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Represents a playable hero character in the game.
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */

public class PlayableHero extends Rectangle {

  private byte myEntityID;
  protected boolean myUp;
  protected boolean myDown;
  protected boolean myLeft;
  protected boolean myRight;
  private Image myHeroImage;
  /**
   * Constructs a new PlayableHero with the specified ID, position, and image.
   *
   * @param theID The unique ID of the hero.
   * @param theX The initial x-coordinate of the hero.
   * @param theY The initial y-coordinate of the hero.
   * @param theHeroImage The image representing the hero.
   */
  public PlayableHero(byte theID, int theX, int theY, Image theHeroImage) {
    super(theX, theY, 34, 24);
    this.myEntityID = theID;
    this.myUp = false;
    this.myDown = false;
    this.myLeft = false;
    this.myRight = false;
    this.myHeroImage = theHeroImage;
  }

  /**
   * Gets the unique ID of the hero.
   *
   * @return The hero's ID.
   */
  public byte getID() {
    return myEntityID;
  }

  /**
   * Moves the hero based on the current direction flags.
   */
  public void move() {
    if(myUp) {
      super.y-=5;
    }
    if(myDown) {
      super.y+=5;
    }
    if(myLeft) {
      super.x-=5;
    }
    if(myRight) {
      super.x+=5;
    }
  }

  /**
   * Sets whether the hero is moving up.
   *
   * @param theUp True if the hero should move up, false otherwise.
   */
  public void setMovingUp(boolean theUp) {
    this.myUp = theUp;
  }
  /**
   * Sets whether the hero is moving down.
   *
   * @param theDown True if the hero should move down, false otherwise.
   */
  public void setMovingDown(boolean theDown) {
    this.myDown = theDown;
  }

  /**
   * Sets whether the hero is moving left.
   *
   * @param theLeft True if the hero should move left, false otherwise.
   */
  public void setMovingLeft(boolean theLeft) {
    this.myLeft = theLeft;
  }

  /**
   * Sets whether the hero is moving right.
   *
   * @param theRight True if the hero should move right, false otherwise.
   */
  public void setMovingRight(boolean theRight) {
    this.myRight = theRight;
  }

  /**
   * Sets the x-coordinate of the hero's center position.
   *
   * @param theX The new x-coordinate for the center.
   */
  public void setCenterX(int theX) {
    super.x = theX - super.width/2;
  }

  /**
   * Sets the y-coordinate of the hero's center position.
   *
   * @param theY The new y-coordinate for the center.
   */
  public void setCenterY(int theY) {
    super.y = theY - super.height/2;
  }

  /**
   * Renders the hero on the screen.
   *
   * @param theGraphics The Graphics context to use for rendering.
   */
  public void render(Graphics theGraphics){
    theGraphics.drawImage(myHeroImage, super.x, super.y, 50, 50, null);
  }

  /**
   * Handles collision between the hero and a tile.
   *
   * @param theTile The tile that the hero collides with.
   */
  public void handleCollisionWith(Tile theTile) {
    Rectangle intersection = this.intersection(theTile);
    if(intersection.isEmpty() || !theTile.isMyWall())
      return;
    if(intersection.width > intersection.height) {
      if(this.y < theTile.y)
        this.y = theTile.y - this.height;
      else
        this.y = theTile.y + this.height;
    }
    else {
      if(this.x < theTile.x)
        this.x = theTile.x - this.width;
      else
        this.x = theTile.x + this.width;
    }
  }

  /**
   * Gets the current direction the hero is moving in as an array.
   *
   * @return An array where the first element is the x-direction and the second element is the y-direction.
   */
  public int[] getDirection(){
    if(myUp) {
      return new int[] {0, -10};
    }
    if(myDown) {
      return new int[] {0, 10};
    }
    if(myLeft) {
      return new int[] {-10, 0};
    }
    if(myRight) {
      return new int[] {10, 0};
    }
    return new int[] {10, 0};
  }
}
