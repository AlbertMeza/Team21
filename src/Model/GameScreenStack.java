package Model;

import Controller.AudioManager;

import java.awt.Graphics;
import java.io.File;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class is the stack object that holds each window during game play.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class GameScreenStack {

  /**
   * screens field is the stack that holds all game screens in the game
   */
  private Stack<GameScreen> myScreens;

  private final AudioManager myMusicManager;
  private final AudioManager mySoundManager;


  /**
 * GameScreenStack constructor makes a game screen stack and
 * initializes the fields
 */
  public GameScreenStack() {
    myScreens = new Stack<>();
    myMusicManager = new AudioManager();
    mySoundManager = new AudioManager();
    myMusicManager.loadAllAudio(new File("src/Assets/BackgroundMusic"));
    mySoundManager.loadAllAudio(new File("src/Assets/SoundEffects"));
  }

/**
 * addState method puts a game screen on the stack
 *
 * @param theScreen is the game screen to be added
 */
  public void addScreen(GameScreen theScreen) {
      myScreens.add(theScreen);


  }

  /**
   * backToPreviousState method pops off the top screen of the stack
   */
  public void backToPreviousState() {
    myScreens.pop();
  }

  /**
   * clearStack method empties the stack
   */
  public void clearStack() {
    myScreens.clear();
  }

  /**
   * loop method repeatedly looks at the screen at the top of the stack
   */
  public void loop() {
    try {
      myScreens.peek().loop();
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  /**
   * render method displays the graphics of the top screen
   *
   * @param graphics is the graphics from the screen at the top of the stack
   */
  public void render(Graphics graphics) {
    try {
      myScreens.peek().render(graphics);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  /**
   * keyPressed method looks for key press events
   * in the screen at the top of the stack.
   *
   * @param keyCode is the code for the key pressed
   */
  public void keyPressed(int keyCode) {
    try {
      myScreens.peek().keyPressed(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  /**
   * keyReleased method looks for key released
   * events in the screen at the top of the stack
   *
   * @param keyCode is the code for the key released
   */
  public void keyReleased(int keyCode) {
    try {
      myScreens.peek().keyReleased(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  public AudioManager getMusicManager() {
    return myMusicManager;
  }

  public AudioManager getSoundManger() {
    return mySoundManager;
  }
}
