package Model;

import java.util.Random;

/**
 * This class represents a leach monster
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Leach extends Monster implements Healable {

    /**
     * HEAL_MAX_BOUND constant is the max bound, i.e.
     * range for amount of hp healed
     */
    private static final int HEAL_MAX_BOUND = 20;

    /**
     * HEAL_MIN constant is the minimum hp to be healed
     */
    private static final int HEAL_MIN = 20;

    /**
     * RAND constant is for randomness for leaches
     */
    final Random RAND = new Random();

    /**
     * Leach constructor creates a leach monster character and adds 2 random items
     * to it's bag to be looted by heros.
     */
    public Leach() {
        super("Leach", 55, 15, 6, 0.3, new GameItem[] {});
        super.pickUpItem(getRandomLoot());
        super.pickUpItem(getRandomLoot());
    }

    /**
     * getRandomLoot method gets 1 of 4 random game items
     * to be stored in the leach's bag
     *
     * @return returns a random game item for the bag
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new LeachFang();
            case 1:
                result = new EvasionPotion();
            case 2:
                result = new LeachTonic();
            case 3:
                result = new GoldCoin();
        }
        return result;
    }

    /**
     * heal method increases leach's hp between 20 and 40 points at random
     */
    @Override
    public void heal() {
        int healAmount = RAND.nextInt(HEAL_MAX_BOUND) + HEAL_MIN;
        super.buffHP(healAmount);
    }
}
