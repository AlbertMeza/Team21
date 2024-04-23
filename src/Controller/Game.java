package Controller;

import Model.GameScreenStack;
import View.MainMenuScreen;
import View.FrameManager;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {
  private static GameScreenStack gameScreenStack;
  private static FrameManager frameManager;
  private static Timer timer;

  public static void init() {
    gameScreenStack = new GameScreenStack();
    frameManager = new FrameManager();
    timer = new Timer(20, new MainGameLoop());
  }

  public static void start() {
    gameScreenStack.addState(new MainMenuScreen(gameScreenStack));
    frameManager.addPanel(new GameScreen());
    frameManager.addKeyListener(new Keyboard());
    frameManager.createWindow();
    timer.start();
  }

  private static class MainGameLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent arg){
      gameScreenStack.loop();
    }
  }

  private static class GameScreen extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      gameScreenStack.render(g);
      repaint();
    }
  }

  private static class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent key) {
      gameScreenStack.keyPressed(key.getKeyCode());
    }
    @Override
    public void keyPressed(KeyEvent key) {
      gameScreenStack.keyPressed(key.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent key) {
      gameScreenStack.keyPressed(key.getKeyCode());
    }
  }
}
