package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameScreenStackTest {

  private GameScreenStack gameScreenStack;
  GameScreen screen1;
  GameScreen screen2;

  @BeforeEach
  void setUp() {
    gameScreenStack = new GameScreenStack();
    screen1 = new GameScreen(gameScreenStack) {
      @Override
      protected void loop() {

      }

      @Override
      protected void render(Graphics graphics) {

      }

      @Override
      protected void keyPressed(int keyCode) {

      }

      @Override
      protected void keyReleased(int keyCode) {

      }
    };
    screen2 = new GameScreen(gameScreenStack) {
      @Override
      protected void loop() {

      }

      @Override
      protected void render(Graphics graphics) {

      }

      @Override
      protected void keyPressed(int keyCode) {

      }

      @Override
      protected void keyReleased(int keyCode) {

      }
    };
  }

  @Test
  void addScreen() {

    gameScreenStack.addScreen(screen1);
    assertEquals(1, gameScreenStack.getScreenSize());
  }

  @Test
  void backToPreviousState() {
    gameScreenStack.addScreen(screen1);
    GameScreen screen2 = new GameScreen(gameScreenStack) {
      @Override
      protected void loop() {

      }

      @Override
      protected void render(Graphics graphics) {

      }

      @Override
      protected void keyPressed(int keyCode) {

      }

      @Override
      protected void keyReleased(int keyCode) {

      }
    };
    gameScreenStack.addScreen(screen2);
    assertEquals(2, gameScreenStack.getScreenSize());

    gameScreenStack.backToPreviousState();
    assertEquals(1, gameScreenStack.getScreenSize());
    assertEquals(screen1, gameScreenStack.getMyStack().peek());
  }

  @Test
  void clearStack() {
    gameScreenStack.addScreen(screen1);
    gameScreenStack.addScreen(screen2);
    assertEquals(2, gameScreenStack.getScreenSize());

    gameScreenStack.clearStack();
    assertEquals(0, gameScreenStack.getScreenSize());
    assertTrue(gameScreenStack.getMyStack().isEmpty());
  }
}