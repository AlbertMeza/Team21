package Model;

import java.util.Random;

/**
 * SpeedPotion class is a Game Item that buffs speed
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class SpeedPotion extends GameItem{

    /**
     * MINIMUM_SP constant is the minimum speed
     * points to be buffed by the speed potion
     */
    int MINIMUM_SP = 1;

    /**
     * SP_RANGE constant is the range of sp to be buffed
     * by speed potion, increasing from minimum.
     */
    int SP_RANGE = 3;

    /**
     * RAND constant is for random generation in damage potion
     */
    Random RAND = new Random();

    /**
     * SpeedPotion constructor creates a speed potion instance
     */
    public SpeedPotion() {
        super("Speed Potion");
    }

    /**
     * useItem buffs speed of the passed hero
     *
     * @param theCharacter is the hero to use speed potion
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        int sp = RAND.nextInt(SP_RANGE) + MINIMUM_SP;
        theCharacter.buffSpeed(sp);
    }
}


