package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Elf;

/**
 * This class tests Elf class
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class ElfTests {

    /**
     * main method to call test methods
     *
     * @param theArgs is any command line arguments
     */
    public static void main(String[] theArgs) {
        AbstractCharacter myCharacter = new Elf();
        System.out.println(verifyName(myCharacter));
        System.out.println(verifyHP(myCharacter));
        System.out.println(verifyDamage(myCharacter));
        System.out.println(verifySpeed(myCharacter));
        System.out.println(verifyDodgeRate(myCharacter));
        System.out.println(verifyBag(myCharacter));
        System.out.println(verifyHeal(myCharacter));
    }

    /**
     * verifyName verifies character name properly set
     *
     * @param theCharacter is the character to check
     * @return String whether name was properly set
     */
    private static String verifyName(AbstractCharacter theCharacter) {
        if ("Elf".equals(theCharacter.getName())) {
            return "Name set correctly";
        } else return "Name not set correctly!";
    }

    /**
     * verifyHP verifies character HP properly set
     *
     * @param theCharacter is the character to check
     * @return String whether HP was properly set
     */
    private static String verifyHP(AbstractCharacter theCharacter) {
        if (theCharacter.getHP() == 100) {
            return "HP set correctly";
        } else return "HP not set correctly!";
    }

    /**
     * verifyDamage verifies character damage properly set
     *
     * @param theCharacter is the character to check
     * @return String whether damage was properly set
     */
    private static String verifyDamage(AbstractCharacter theCharacter) {
        if (25 == theCharacter.getDamage()) {
            return "Damage set correctly";
        } else return "Damage not set correctly!";
    }

    /**
     * verifySpeed verifies character speed properly set
     *
     * @param theCharacter is the character to check
     * @return String whether speed was properly set
     */
    private static String verifySpeed(AbstractCharacter theCharacter) {
        if (3 == theCharacter.getSpeed()) {
            return "Speed set correctly";
        } else return "Speed not set correctly!";
    }

    /**
     * verifyDodgeRate verifies character dodge rate properly set
     *
     * @param theCharacter is the character to check
     * @return String whether dodge rate was properly set
     */
    private static String verifyDodgeRate(AbstractCharacter theCharacter) {
        if (0.2 == theCharacter.getDodgeRate()) {
            return "Dodge rate set correctly";
        } else return "Dodge rate not set correctly!";
    }

    /**
     * verifyBag verifies bag is correctly initialized for this character
     *
     * @param theCharacter i the character to be checked
     * @return String whether bag was properly initialized
     */
    private static String verifyBag(AbstractCharacter theCharacter) {
        if (theCharacter.isBagEmpty()) {
            return "Bag initialized correctly";
        } else return "Bag not initialized correctly!";
    }

    /**
     * verifyHeal verifies character heal works
     *
     * @param theCharacter is the character to check
     * @return String whether heal worked or not
     */
    private static String verifyHeal(AbstractCharacter theCharacter) {
        while (!theCharacter.attacked(90));
        ((Elf) theCharacter).heal();
        if (theCharacter.getHP() >= 20 && theCharacter.getHP() <= 39) {
            return "Heal works properly";
        } else return "Heal does not work properly!";
    }
}
