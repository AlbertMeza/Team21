package View;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * FrameManager class contains all screens for the game
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class FrameManager {

  /**
   * WIDTH constant is the width of the frame
   */
  private static final int WIDTH = 800;

  /**
   * HEIGHT constant is the height of the frame
   */
  private static final int HEIGHT = 600;

  /**
   * myFrame is the frame for the game
   */
  private final JFrame myFrame;

  /**
   * myPanel is the panel that covers the frame
   */
  private JPanel myPanel;

  /**
   * FrameManager constructor creates a main frame.
   */
  public FrameManager() {
    myFrame = new JFrame("Dungeon Adventure");
    myFrame.setBounds(70, 70, 0, 0);
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.setResizable(false);
  }

  /**
   * addPanel method adds a panel to the frame to be seen by user
   *
   * @param thePanel is the panel to be added to the frame
   */
  public void addPanel(JPanel thePanel){
    this.myPanel = thePanel;
    this.myPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.myPanel.setFocusable(true);
    this.myPanel.requestFocusInWindow();
  }

  /**
   * addKeyListener method makes this manager's panel a keylistener
   *
   * @param theListener is the keylistener for the panel
   */
  public void addKeyListener(KeyListener theListener) {
        try {
      myPanel.addKeyListener(theListener);
    } catch(NullPointerException e) {
      System.err.println("[WindowManager]: Error! Tried to add KeyListener before JPanel");
      System.exit(-1);
    }
  }

  /**
   * createWindow method adds myPanel to myFrame
   */
  public void createWindow() {
    myFrame.setContentPane(myPanel);
    myFrame.pack();
    myFrame.setVisible(true);
  }

  /**
   * getHeight method provides the height of the frame
   *
   * @return the frame height
   */
  public static int getHeight() {
    return HEIGHT;
  }

  /**
   * getWidth method provides the width of the frame
   *
   * @return the frame width
   */
  public static int getWidth() {
    return WIDTH;
  }

  private JPanel getMyPanel(){
    return myPanel;
  }
}
