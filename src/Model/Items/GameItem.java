package Model.Items;

import Model.Character.AbstractCharacter;

import java.io.Serializable;

/**
 * This class represents a game item on an abstract level.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public abstract class GameItem implements Serializable {

    /**
     * myName field is the name for this Game Item
     */
    String myName;
    /**
     * GameItem constructor creates an abstract level
     * game item.
     */
    public GameItem(String theName){myName = theName;};

    /**
     * getItemName method returns the name of this game item
     *
     * @return returns the name of this item
     */
    public String getItemName() {
        return myName;
    }

    /**
     * useItem method uses the game item on the passed hero param
     */
    public abstract void useItem(AbstractCharacter theCharacter);
}
