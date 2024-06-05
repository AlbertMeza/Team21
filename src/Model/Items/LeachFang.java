package Model.Items;

import Model.Character.AbstractCharacter;

/**
 * LeachFang class is a GameItem
 * that buffs damage
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class LeachFang extends GameItem {

    /**
     * DP_BUFF is the amount of dp to buff
     * character damage by.
     */
    private final int DP_BUFF = 15;

    /**
     * LeachFang constructor creates an instance of
     * leach fang.
     */
    public LeachFang() {
        super("Leach Fang");
    }

    /**
     * useItem buffs damage of the passed character
     *
     * @param theCharacter is the hero using the leach fang
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffDamage(DP_BUFF);
    }
}
