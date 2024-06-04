package Model.Items;

import Model.Character.AbstractCharacter;
import Model.GameItem;

/**
 * OgreClub class is a GameItem
 * that buffs damage
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class OgreClub extends GameItem {

    /**
     * DP_BUFF is the amount of dp to buff
     * character damage by.
     */
    private final int DP_BUFF = 20;

    /**
     * OgreClub constructor creates an instance of
     * Ogre Club.
     */
    public OgreClub() {
        super("Ogre Club");
    }

    /**
     * useItem buffs damage of the passed character
     *
     * @param theCharacter is the hero using the Ogre Club
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffDamage(DP_BUFF);
    }
}


