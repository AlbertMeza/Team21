package Model.Items;

import Ausitn.GameItem;

/**
 * GoblinSwiftPowder class is a GameItem
 * that buffs speed
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class GoblinSwiftPowder extends GameItem {

    /**
     * SP_BUFF is the amount of sp to buff
     * character speed by.
     */
    private final int SP_BUFF = 1;

    /**
     * GoblinSwiftPowder constructor creates an instance of
     * Goblin Swift Powder.
     */
    public GoblinSwiftPowder() {
        super("Goblin Swift Powder");
    }

    /**
     * useItem buffs speed of the passed character
     *
     * @param theCharacter is the hero using the Goblin Swift Powder
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffSpeed(SP_BUFF);
    }
}

