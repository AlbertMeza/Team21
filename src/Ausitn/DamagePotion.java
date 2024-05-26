package Ausitn;

import java.util.Random;

/**
 * DamagePotion class is a Game Item that buffs damage
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class DamagePotion extends GameItem {

    /**
     * MINIMUM_DP constant is the minimum damage
     * points to be buffed by the damage potion
     */
    int MINIMUM_DP = 5;

    /**
     * DP_RANGE constant is the range of dp to be buffed
     * by damage potion, increasing from minimum.
     */
    int DP_RANGE = 10;

    /**
     * RAND constant is for random generation in damage potion
     */
    Random RAND = new Random();


    /**
     * DamagePotion constructor creates a damage potion instance
     */
    public DamagePotion() {
        super("Damage Potion");

    }

    /**
     * useItem buffs the damage of the passed hero
     *
     * @param theCharacter is the hero to use damage potion
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        int dp = RAND.nextInt(DP_RANGE) + MINIMUM_DP;
        theCharacter.buffDamage(dp);
    }
}

