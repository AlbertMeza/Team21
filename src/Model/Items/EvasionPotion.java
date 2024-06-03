package Model.Items;

import Model.Character.AbstractCharacter;

import java.util.Random;

/**
 * EvasionPotion class is a Game Item that buffs evasion
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class EvasionPotion extends GameItem {

    /**
     * MINIMUM_EP constant is the minimum evasion
     * points to be buffed by the evasion potion
     */
    double MINIMUM_EP = 0.5;

    /**
     * EP_RANGE constant is the range of ep to be buffed
     * by evasion potion, increasing from minimum.
     */
    int EP_RANGE = 2;

    /**
     * RAND constant is for random generation in damage potion
     */
    Random RAND = new Random();

    /**
     * EvasionPotion constructor creates an evasion potion instance
     */
    public EvasionPotion() {
        super("Evasion Potion");

    }

    /**
     * useItem buffs evasion of the passed hero
     *
     * @param theCharacter is the hero to use evasion potion
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        double ep = RAND.nextInt(EP_RANGE) + MINIMUM_EP;
        theCharacter.buffDodgeRate(ep);
    }
}

