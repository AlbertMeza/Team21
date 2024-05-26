package Model.Items;

import Ausitn.GameItem;

/**
 * LeachTonic class is a GameItem
 * that buffs max hp
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class LeachTonic extends GameItem {

    /**
     * MAX_HP_BUFF is the amount of hp to buff
     * max hp by.
     */
    private final int MAX_HP_BUFF = 15;

    /**
     * LeachTonic constructor creates an instance of
     * leach tonic.
     */
    public LeachTonic() {
        super("Leach Tonic");
    }

    /**
     * useItem buffs max hp of the passed character
     *
     * @param theCharacter is the hero using the leach tonic
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffMaxHP(MAX_HP_BUFF);
    }
}
