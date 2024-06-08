package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayableHeroTest {

  private PlayableHero hero;
  private Image heroImage;

  @BeforeEach
  void setUp() {
    heroImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
    hero = new PlayableHero((byte) 1, 100, 100, heroImage);
  }

  @Test
  void getID() {
    assertEquals(1, hero.getID());
  }

  @Test
  void move() {
    hero.setMovingUp(true);
    hero.move();
    assertEquals(95, hero.y);

    hero.setMovingDown(true);
    hero.move();
    assertEquals(95, hero.y);

    hero.setMovingLeft(true);
    hero.move();
    assertEquals(95, hero.x);

    hero.setMovingRight(true);
    hero.move();
    assertEquals(95, hero.x);
  }

  @Test
  void setMovingUp() {
    hero.setMovingUp(true);
    assertTrue(hero.myUp);
  }

  @Test
  void setMovingDown() {
    hero.setMovingDown(true);
    assertTrue(hero.myDown);
  }

  @Test
  void setMovingLeft() {
    hero.setMovingLeft(true);
    assertTrue(hero.myLeft);
  }

  @Test
  void setMovingRight() {
    hero.setMovingRight(true);
    assertTrue(hero.myRight);
  }

  @Test
  void setCenterX() {
    hero.setCenterX(200);
    assertEquals(183, hero.x);
  }

  @Test
  void setCenterY() {
    hero.setCenterY(200);
    assertEquals(188, hero.y);
  }

  @Test
  void getDirection() {
    hero.setMovingUp(true);
    assertArrayEquals(new int[]{0, -10}, hero.getDirection());

    hero.setMovingUp(false);
    hero.setMovingDown(true);
    assertArrayEquals(new int[]{0, 10}, hero.getDirection());

    hero.setMovingDown(false);
    hero.setMovingLeft(true);
    assertArrayEquals(new int[]{-10, 0}, hero.getDirection());

    hero.setMovingLeft(false);
    hero.setMovingRight(true);
    assertArrayEquals(new int[]{10, 0}, hero.getDirection());
  }
}