package Ausitn;
import Ausitn.GameItem;
import Model.Healable;
import Model.Hero;

import java.util.Random;

/**
 * This class represents an Elf hero.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Elf extends Hero implements Healable {

    /**
     * HEAL_MAX_BOUND constant is the max bound, i.e.
     * range for amount of hp healed
     */
    private static final int HEAL_MAX_BOUND = 30;

    /**
     * HEAL_MIN constant is the minimum hp to be healed
     */
    private static final int HEAL_MIN = 10;

    /**
     * RAND constant is for randomness in Elf class
     */
    final Random RAND = new Random();

    /**
     * Elf constructor creates an elf hero character
     */
   public Elf() {
       super("Elf", 100,25, 3, 0.2, new GameItem[] {});
   }

    /**
     * heal method satisfies healable interface requirement
     * it heals the elf at random between 10 and 40 hp
     */
    public void heal() {
        int healAmount = RAND.nextInt(HEAL_MAX_BOUND) + HEAL_MIN;
        super.buffHP(healAmount);
    }
}
