package Model;

import java.util.Random;

/**
 * HealthPotion class is a Game Item that restores some health
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class HealthPotion extends GameItem{

    /**
     * MINIMUM_HP constant is the minimum hp
     * to be buffed by the health potion
     */
    int MINIMUM_HP = 15;

    /**
     * HP_RANGE constant is the range of hp to be buffed
     * by health potion, increasing from minimum.
     */
    int HP_RANGE = 15;

    /**
     * RAND constant is for random generation in health potion
     */
    Random RAND = new Random();

    /**
     * HealthPotion constructor creates a health potion instance
     */
    public HealthPotion() {
        super("Health Potion");

    }

    /**
     * useItem heals the passed hero
     *
     * @param theCharacter is the hero to use health potion
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        int hp = RAND.nextInt(HP_RANGE) + MINIMUM_HP;
        theCharacter.buffHP(hp);
    }
}
