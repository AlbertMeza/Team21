import Controller.GameController;
import javax.swing.SwingUtilities;

/**
 * Main is the driver for the game
 */
public class Main {

  /**
   * main method kicks off the game
   *
   * @param args any command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GameController.init();
      GameController.start();
    });
  }
}
