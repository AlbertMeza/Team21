package Model;

import View.FrameManager;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LightningBolt {
  private int x, y;
  private int velocityX, velocityY;
  private Image image;
  private boolean isActive;

  public LightningBolt(int startX, int startY, int velocityX, int velocityY) {
    this.x = startX;
    this.y = startY;
    this.velocityX = velocityX;
    this.velocityY = velocityY;
    this.isActive = true;

    try {
      this.image = ImageIO.read(new File("src/Assets/Images/bolt.jpeg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    if (!isActive) return;
    x += velocityX;
    y += velocityY;

    if (x < 0 || x > FrameManager.getWidth() || y < 0 || y > FrameManager.getHeight()) {
      isActive = false;
    }
  }

  public void render(Graphics graphics) {
    if (!isActive) return;
    graphics.drawImage(image, x, y, 20, 20, null);
  }

  public boolean isActive() {
    return isActive;
  }
}
