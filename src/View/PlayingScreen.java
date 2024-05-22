package View;

import Controller.MazeGenerator;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * PlayingScreen class is the screen for rooms in the dungeon
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class PlayingScreen extends GameScreen {

  /**
   * myGenerator field is the maze generator for randomly making the map
   */
  private MazeGenerator myGenerator;

  /**
   * PlayingScreen constructor creates a randomly generated maze and adds
   * it to the stack.
   *
   * @param stack the gamescreen stack for the game
   */
  protected PlayingScreen(GameScreenStack stack) {
    super(stack);
    myGenerator = new MazeGenerator();
    this.generateMaze();
  }

  /**
   * loop method is for any looping events during game play
   */
  @Override
  protected void loop() {

  }

  /**
   * render method displays the screen
   *
   * @param graphics is the graphics for game controller
   */
  @Override
  protected void render(Graphics graphics) {
    for(int i = 0; i < MazeGenerator.WORLD_SIZE; i++){
      for(int j = 0; j < MazeGenerator.WORLD_SIZE; j++) {

      }
    }
  }

  /**
   * keyPressed method enables handling key press events
   *
   * @param keyCode is the code for the key pressed
   */
  @Override
  protected void keyPressed(int keyCode) {
    if(keyCode == KeyEvent.VK_ENTER) this.generateMaze();
  }

  /**
   * keyReleased method enables handling key release events
   *
   * @param keyCode is the code for the key pressed
   */
  @Override
  protected void keyReleased(int keyCode) {

  }

  /**
   * playBackgroundMusic method plays background music
   */
  @Override
  protected void playBackgroundMusic() {

  }

  /**
   * stopBackgroundMusic method stops background music.
   */
  @Override
  protected void stopBackgroundMusic() {

  }

  /**
   * playSoundEffect method plays sound effects
   * when their coordinating string name is passed
   *
   * @param theEffectName is the name of the sound effect to be played
   */
  @Override
  protected void playSoundEffect(String theEffectName) {

  }

  /**
   * generateMaze method creates the game maze
   */
  private void generateMaze() {
    while(!myGenerator.finished()) {
      myGenerator.generate();
    }
  }
}
