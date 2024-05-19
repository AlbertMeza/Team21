package Model;

import java.util.Random;

/**
 * This class represents all monsters.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Monster extends AbstractCharacter implements Healable{

    /**
     * RAND constant is for randomness for monsters.
     */
    final Random RAND = new Random();

    /**
     * myMinHeal is the minimum amount to be healed
     * not to exceed the monster's max health.
     */
    int myMinHeal;

    /**
     * myHealRange is the range for the randomness
     * of amount to be healed on top of the minimum
     * not to exceed the monster's max health.
     */
    int myHealRange;

    /**
     * Monster constructor creates monsters with varying stats
     * coordinating with monster names.
     *
     * @param theName is the name of the monster
     * @param theHP is the health points for the monster
     * @param theDamage is the damage points for the monster
     * @param theSpeed is the speed points for the monster
     * @param theDodgeRate is the dodge rate for the monster
     * @param theMinHeal is the minimum amount of points to be healed
     * @param theHealRange is the range that can be healed
     * @param theItems is the initial items in the monster's bag

     */
    public Monster(String theName, int theHP, int theDamage, int theSpeed,
                   double theDodgeRate, int theMinHeal, int theHealRange,
                   GameItem[] theItems) {
        super(theName, theHP, theDamage, theSpeed, theDodgeRate, theItems);
        myMinHeal = theMinHeal;
        myHealRange = theHealRange;
    }

    /**
     * heal method gets a random int within this monsters range of amounts
     * to heal, and attempts to heal that amount by buffing HP in the parent
     * class. HP points cannot be healed greater than the monsters max HP.
     */
    public void heal() {
        int healAmount = RAND.nextInt(myHealRange) + myMinHeal;
        super.buffHP(healAmount);
    }

    public void addLoot() {
        //addloot based on the monster name it is
    }
}
