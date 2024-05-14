package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayableHero extends Rectangle {

  private byte myEntityID;
  protected boolean myUp;
  protected boolean myDown;
  protected boolean myLeft;
  protected boolean myRight;
  private Image myHeroImage;

  public PlayableHero(byte theID, int theX, int theY) {
    super(theX, theY, 34, 24);
    this.myEntityID = theID;
    this.myUp = false;
    this.myDown = false;
    this.myLeft = false;
    this.myRight = false;
    try {
      this.myHeroImage = ImageIO.read(new File("src/Assets/Images/wizard.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public byte getID() {
    return myEntityID;
  }

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

  public void setMovingUp(boolean theUp) {
    this.myUp = theUp;
  }

  public void setMovingDown(boolean theDown) {
    this.myDown = theDown;
  }

  public void setMovingLeft(boolean theLeft) {
    this.myLeft = theLeft;
  }

  public void setMovingRight(boolean theRight) {
    this.myRight = theRight;
  }

  public void setCenterX(int theX) {
    super.x = theX - super.width/2;
  }

  public void setCenterY(int theY) {
    super.y = theY - super.height/2;
  }

  public void render(Graphics theGraphics){
    theGraphics.drawImage(myHeroImage, super.x, super.y, 50, 50, null);
  }

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
}
