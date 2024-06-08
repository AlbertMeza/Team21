package View;

import Model.Character.Hero;
import Model.GameScreen;
import Model.GameScreenStack;
import Model.Items.GameItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Used to display usable loot
 */
public class InventoryScreen extends GameScreen {

    /**
     * Used if bag is empty
     */
    private static final String EMPTY = "Empty";

    /**
     * Hero object that holds items.
     */
    private final Hero myHero;

    /**
     * Array of string of items in bag.
     */
    private String[] myItemOptions;

    /**
     * Array of gameItems in bag.
     */
    private final GameItem[] myGameItems;

    /**
     * The selected item.
     */
    private int mySelected;

    /**
     * Image for background
     */
    private Image myInventoryBackgroundImage;

    /**
     * Screen for displaying and using loot.
     * @param theStack Stack of game screens
     * @param theHero Hero that holds items.
     */
    protected InventoryScreen(final GameScreenStack theStack, final Hero theHero) {
        super(Objects.requireNonNull(theStack));
        myHero = Objects.requireNonNull(theHero);
        myGameItems = myHero.getItems();
        mySelected = 0;

        List<String> tempList = new ArrayList<>();
        for (GameItem item : myGameItems) {
            tempList.add(item.getItemName());
        }
        myItemOptions = tempList.toArray(new String[tempList.size()]);
        try {
            myInventoryBackgroundImage = ImageIO.read(new File(
                    "src/Assets/Images/inventoryBackground.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loop() {
    }

    /**
     * Used to draw the inventory screen.
     * @param theGraphics Graphics object for drawing.
     */
    @Override
    protected void render(final Graphics theGraphics) {
        theGraphics.drawImage(myInventoryBackgroundImage, 0, 0,
                FrameManager.getWidth(),
                FrameManager.getHeight(), null);

        // Set color and draw the background of the battle log area
        theGraphics.setColor(new Color(50, 50, 50, 180)); // Semi-transparent dark color
        int boxWidth = 300; // Adjust the width of the box as needed
        int boxX = (FrameManager.getWidth() - boxWidth) / 2; // Center horizontally
        int boxY = (FrameManager.getHeight() - myItemOptions.length * 30) / 2; // Center vertically
        theGraphics.fillRect(boxX, boxY, boxWidth, myItemOptions.length * 30);

        theGraphics.setFont(getCustomFont());
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 24));
        theGraphics.setColor(Color.WHITE);
        int startY = boxY + 24; // Adjusted startY to align text with the box
        if (myItemOptions.length == 0) {
            myItemOptions = new String[] {EMPTY};
        }
        FontMetrics fontMetrics = theGraphics.getFontMetrics();
        int maxOptionWidth = 0;
        for (String option : myItemOptions) {
            int optionWidth = fontMetrics.stringWidth(option);
            if (optionWidth > maxOptionWidth) {
                maxOptionWidth = optionWidth;
            }
        }
        int startX = (FrameManager.getWidth() - maxOptionWidth) / 2;
        for (int i = 0; i < myItemOptions.length; i++) {
            boolean isSelected = (i == mySelected);
            if (isSelected) {
                theGraphics.setColor(Color.YELLOW);
            }
            theGraphics.drawString(myItemOptions[i], startX, startY + i * 30);
            theGraphics.setColor(Color.WHITE);
        }
    }


    /**
     * Controls the inventory screen through input.
     * @param keyCode is the code for the key pressed
     */
    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (mySelected > 0) {
                    mySelected--;
                    playSoundEffect("BattleSwitchEffect");
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (mySelected < myItemOptions.length - 1) {
                    mySelected++;
                    playSoundEffect("BattleSwitchEffect");
                }
                break;
            case KeyEvent.VK_ESCAPE:
                playSoundEffect("BattleRun");
                myGameScreenStack.backToPreviousState();
                break;
            case KeyEvent.VK_ENTER:
                if (!myItemOptions[mySelected].equals(EMPTY)) {
                    myGameScreenStack.backToPreviousState();
                    System.out.println(myHero.useItem(myGameItems[mySelected]));
                    playSoundEffect("InventoryUseItem");
                } else {
                    myGameScreenStack.backToPreviousState();
                }
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
