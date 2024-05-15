package View;

import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.File;

public class CharacterScreen extends GameScreen {

  private final String chooseText = "Choose your Hero";
  private final String elf = "Elf";
  private final String rogue = "Rogue";
  private final String wizard = "Wizard";
  private final String barbarian = "Barbarian";
  private Image barbarianImage;
  private Image characterSelectionBackgroundImage;
  private Image elfImage;
  private Image rogueImage;
  private Image wizardImage;
  private static final String SELECT_EFFECT = "steelsword";
  private static final String SWITCH_EFFECT = "215029__taira-komori__extracting_knife";
  private static final String START_MENU_MUSIC = "POL-misty-dungeon-short";

  int selected;

  protected CharacterScreen(GameScreenStack theStack) {
    super(theStack);
    selected = 0;
    //playBackgroundMusic(START_MENU_MUSIC);

    try {
      elfImage = ImageIO.read(new File("src/Assets/Images/ElfBattle.png"));
      wizardImage = ImageIO.read(new File("src/Assets/Images/wizard.png"));
      rogueImage = ImageIO.read(new File("src/Assets/Images/rogue.png"));
      barbarianImage = ImageIO.read(new File("src/Assets/Images/barbarian.png"));
      characterSelectionBackgroundImage = ImageIO.read(new File("src/Assets/Images/characterScreen.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Override
  protected void loop() {

  }

  @Override
  protected void render(Graphics graphics) {
    graphics.drawImage(characterSelectionBackgroundImage, 0, 0, FrameManager.getWidth(),
            FrameManager.getHeight(), null);
    graphics.setColor(new Color(30, 30, 70,120));
    graphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
    graphics.setColor(Color.white);
    graphics.setFont(new Font("Arial", Font.BOLD, 40));
    graphics.drawImage(elfImage, 20, 350, 170, 250, null);
    graphics.drawImage(wizardImage, 200, 350, 250, 250, null);
    graphics.drawImage(rogueImage, 400, 350, 200, 250, null);
    graphics.drawImage(barbarianImage, 550, 350, 250, 250, null);
    graphics.drawString(chooseText,220, 120);
    if (selected == 0) {
      graphics.setColor(Color.magenta);
    }
    graphics.drawString(elf, 100, 300);
    graphics.setColor(Color.white);
    if (selected == 1) {
      graphics.setColor(Color.magenta);
    }
    graphics.drawString(wizard, 250, 300);
    graphics.setColor(Color.white);
    if (selected == 2) {
      graphics.setColor(Color.magenta);
    }
    graphics.drawString(rogue, 420, 300);
    graphics.setColor(Color.white);
    if (selected == 3) {
      graphics.setColor(Color.magenta);
    }
    graphics.drawString(barbarian, 600, 300);
    graphics.setColor(Color.white);


  }

  @Override
  protected void keyPressed(int keyCode) {
    switch(keyCode) {
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_D:
        if (selected < 3) {
          selected++;
          playSoundEffect(SWITCH_EFFECT);
        }
        break;
      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_A:
        if (selected > 0) {
          selected--;
          playSoundEffect(SWITCH_EFFECT);
        }
        break;
      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        gameScreenStack.addScreen(new PlayingScreen(gameScreenStack, 5, 1)); //Will this accept a Hero object?
    }
  }

  @Override
  protected void keyReleased(int keyCode) {

  }
}
