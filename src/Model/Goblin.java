package Model;

import java.util.Random;

/**
 * This class represents a goblin monster
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Goblin extends Monster implements Healable {

    /**
     * HEAL_MAX_BOUND constant is the max bound, i.e.
     * range for amount of hp healed
     */
    private static final int HEAL_MAX_BOUND = 20;

    /**
     * HEAL_MIN constant is the minimum hp to be healed
     */
    private static final int HEAL_MIN = 10;

    /**
     * RAND constant is for randomness for goblins
     */
    final Random RAND = new Random();

    /**
     * Goblin constructor creates a goblin character and adds 2 random items
     * to it's bag to be looted by heros.
     */
    public Goblin() {
        super("Goblin", 70, 30, 3, 0.3, new GameItem[] {});
        super.pickUpItem(getRandomLoot());
        super.pickUpItem(getRandomLoot());
    }

    /**
     * getRandomLoot method gets 1 of 4 random game items
     * to be stored in the goblin's bag
     *
     * @return returns a random game item for the bag
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new GoblinSalve();
            case 1:
                result = new GoblinSwiftPowder();
            case 2:
                result = new GoblinTricksterTalisman();
            case 3:
                result = new GoldCoin();
        }
        return result;
    }

    /**
     * heal method increases goblin's hp between 10 and 30 points at random
     */
    @Override
    public void heal() {
        int healAmount = RAND.nextInt(HEAL_MAX_BOUND) + HEAL_MIN;
        super.buffHP(healAmount);
    }
}
