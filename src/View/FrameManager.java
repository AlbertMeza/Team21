package View;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameManager {
  public static final int WIDTH = 800; //Should these be private and have getters?
  public static final int HEIGHT = 600;
  private final JFrame myFrame;
  private JPanel myPanel;

  public FrameManager() {
    this.myFrame = new JFrame("Dungeon Adventure");
    this.myFrame.setBounds(70, 70, 0, 0);
    this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.myFrame.setResizable(false);
  }

  public void addPanel(JPanel thePanel){
    this.myPanel = thePanel;
    this.myPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.myPanel.setFocusable(true);
    this.myPanel.requestFocusInWindow();
  }

  public void addKeyListener(KeyListener theListener) {
        try {
      this.myPanel.addKeyListener(theListener);
    } catch(NullPointerException e) {
      System.err.println("[WindowManager]: Error! Tried to add KeyListener before JPanel");
      System.exit(-1);
    }
  }

  public void createWindow() {
    this.myFrame.setContentPane(getMyPanel());
    this.myFrame.pack();
    this.myFrame.setVisible(true);
  }

  public static int getHeight() {
    return HEIGHT;
  }

  public static int getWidth() {
    return WIDTH;
  }

  private JPanel getMyPanel(){
    return this.myPanel;
  }
}
