package Model.Items;

import Model.Character.AbstractCharacter;

import java.util.Random;

/**
 * SoulCharm class is a Game item that
 * randomly buffs one stat.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class SoulCharm extends GameItem {

    /**
     * MAX_HP_BUFF is the buff to max hp
     * when max hp is randomly chosen
     */
    private final int MAX_HP_BUFF = 25;

    /**
     * DP_BUFF is the buff to damage
     * when damage is randomly chosen
     */
    private final int DP_BUFF = 25;

    /**
     * SP_BUFF is the buff to speed
     * when speed is randomly chosen
     */
    private final int SP_BUFF = 3;

    /**
     * EP_BUFF is the buff to dodge
     * rate when evasion is randomly chosen
     */
    private final double EP_BUFF = 0.3;

    /**
     * RAND is a random generator for soul charm usage
     */
    private final Random RAND = new Random();

    /**
     * SoulCharm constructor creates an instance
     * of a soul charm game item
     */
    public SoulCharm() {
        super("Soul Charm");
    }

    /**
     * useItem method buffs a random stat for the hero
     * that calls the method.
     *
     * @param theCharacter is the hero using the soul charm
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        int randomBuff = RAND.nextInt(4);
        switch (randomBuff) {
            case 0 :
                theCharacter.buffMaxHP(MAX_HP_BUFF);
            case 1 :
                theCharacter.buffDamage(DP_BUFF);
            case 2 :
                theCharacter.buffSpeed(SP_BUFF);
            case 3 :
                theCharacter.buffDodgeRate(EP_BUFF);
        }
    }
}
