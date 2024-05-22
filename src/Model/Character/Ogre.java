package Model.Character;

import Model.GoblinhideCloak;
import Model.Items.GameItem;
import Model.OgreClub;

import java.util.Random;

/**
 * This class represents an ogre monster
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Ogre extends Monster implements Healable {

    /**
     * HEAL_MAX_BOUND constant is the max bound, i.e.
     * range for amount of hp healed
     */
    private static final int HEAL_MAX_BOUND = 10;

    /**
     * HEAL_MIN constant is the minimum hp to be healed
     */
    private static final int HEAL_MIN = 5;

    /**
     * RAND constant is for randomness for ogres
     */
    final Random RAND = new Random();

    /**
     * Ogre constructor creates an oogre character and adds 2 random items
     * to it's bag to be looted by heros.
     */
    public Ogre() {
        super("Ogre", 100, 50, 1, 0.1, new GameItem[] {});
        super.pickUpItem(getRandomLoot());
        super.pickUpItem(getRandomLoot());
    }

    /**
     * getRandomLoot method gets 1 of 4 random game items
     * to be stored in the ogre's bag
     *
     * @return returns a random game item for the bag
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new OgreClub();
            case 1:
                result = new SpeedPotion();
            case 2:
                result = new GoblinhideCloak();
            case 3:
                result = new GoldCoin();
        }
        return result;
    }

    /**
     * heal method increases ogre's hp between 5 and 15 points at random
     */
    @Override
    public void heal() {
        int healAmount = RAND.nextInt(HEAL_MAX_BOUND) + HEAL_MIN;
        super.buffHP(healAmount);
    }
}
