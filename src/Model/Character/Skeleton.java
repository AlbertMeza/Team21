package Model;

import Model.Character.Healable;
import Model.Character.Monster;
import Model.Items.ArchaicBoots;
import Model.Items.BoneSword;
import Model.Items.GoldCoin;
import Model.Items.HealthPotion;

import java.util.Random;

/**
 * This class represents a skeleton monster
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Skeleton extends Monster implements Healable {

    /**
     * HEAL_MAX_BOUND constant is the max bound, i.e.
     * range for amount of hp healed
     */
    private static final int HEAL_MAX_BOUND = 20;

    /**
     * HEAL_MIN constant is the minimum hp to be healed
     */
    private static final int HEAL_MIN = 5;

    /**
     * RAND constant is for randomness for skeletons
     */
    static final Random RAND = new Random();

    /**
     * Skeleton constructor creates a skeleton character and adds 2 random items
     * to it's bag to be looted by heros.
     */
    public Skeleton() {
        super("Skeleton", 60, 25, 2, 0.1, getRandomLoot());
        super.pickUpItem(getRandomLoot());
        super.pickUpItem(getRandomLoot());
    }

    /**
     * getRandomLoot method gets 1 of 4 random game items
     * to be stored in the skeleton's bag
     *
     * @return returns a random game item for the bag
     */
    private static GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        return switch (num) {
            case 0 -> new BoneSword();
            case 1 -> new HealthPotion();
            case 2 -> new ArchaicBoots();
            case 3 -> new GoldCoin();
            default -> null;
        };
    }

    /**
     * heal method increases skeleton's hp between 5 and 25 points at random
     */
    @Override
    public void heal() {
        int healAmount = RAND.nextInt(HEAL_MAX_BOUND) + HEAL_MIN;
        super.buffHP(healAmount);
    }
}
