package Model;

import java.awt.Graphics;

public abstract class GameScreen {
  protected GameScreenStack gameStateManager;
  protected GameScreen(GameScreenStack manager) {
    this.gameStateManager = manager;
  }

  protected abstract void loop();
  protected abstract void render(Graphics graphics);
  protected abstract void keyPressed(int keyCode);
  protected abstract void keyReleased(int keyCode);
}
