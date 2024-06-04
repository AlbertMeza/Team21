package Model.Items;

import Model.Character.AbstractCharacter;
import Model.GameItem;

/**
 * GoblinTricksterTalisman class is a GameItem
 * that buffs evasion
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class GoblinTricksterTalisman extends GameItem {

    /**
     * EP_BUFF is the amount of ep to buff
     * character dodge rate by.
     */
    private final double EP_BUFF = 0.15;

    /**
     * GoblinTricksterTalisman constructor creates an instance of
     * Goblin Trickster Talisman.
     */
    public GoblinTricksterTalisman() {
        super("Goblin Trickster Talisman");
    }

    /**
     * useItem buffs evasion of the passed character
     *
     * @param theCharacter is the hero using the Goblin Trickster Talisman
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffDodgeRate(EP_BUFF);
    }
}


