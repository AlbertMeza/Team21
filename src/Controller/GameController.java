package Controller;

import Model.GameScreenStack;
import View.MainMenu;
import View.FrameManager;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameController {
  //This class currently handles both the game loop and window management, which could be separated
  private static GameScreenStack gameScreenStack;
  private static FrameManager frameManager;
  private static Timer timer;
  private static MusicManager musicManager;
  private static SoundEffectsManager soundEffectsManager;

  public static void init() {
    gameScreenStack = new GameScreenStack();
    frameManager = new FrameManager();
    timer = new Timer(20, new MainGameLoop());
    musicManager = new MusicManager();
    soundEffectsManager = new SoundEffectsManager();
    musicManager.loadAllBackgroundMusic();
    soundEffectsManager.loadAllSoundEffects();
  }

  public static void start() {
    gameScreenStack.addState(new MainMenu(gameScreenStack, musicManager, soundEffectsManager));
    frameManager.addPanel(new GameScreen());
    frameManager.addKeyListener(new Keyboard());
    frameManager.createWindow();
    timer.start();
  }

  private static class MainGameLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent theEvent){
      gameScreenStack.loop();
    }
  }

  private static class GameScreen extends JPanel {
    @Override
    protected void paintComponent(Graphics theGraphics) {
      super.paintComponent(theGraphics);
      gameScreenStack.render(theGraphics);
      repaint();
    }
  }

  private static class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent key) {
    }

    @Override
    public void keyPressed(KeyEvent key) {
      gameScreenStack.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent key) {
      gameScreenStack.keyReleased(key.getKeyCode());
    }
  }
}
