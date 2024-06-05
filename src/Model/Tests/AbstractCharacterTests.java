package Model.Tests;

import Model.Items.GameItem;
import Model.Items.GoblinSalve;
import Model.Items.LeachFang;
import Model.Items.HealthPotion;
import Model.Character.AbstractCharacter;

/**
 * This class tests abstractCharacter class
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class AbstractCharacterTests {

    /**
     * main method to call test methods
     *
     * @param theArgs is any command line arguments
     */
    public static void main(String[] theArgs) {
        AbstractCharacter myCharacter = testCharacterCreation();
        AbstractCharacter myOtherCharacter = testCharacterCreation();
        System.out.println(getCharacterName(myCharacter));
        System.out.println(getHP(myCharacter));
        System.out.println(getDP(myCharacter));
        System.out.println(getSP(myCharacter));
        System.out.println(getEP(myCharacter));
        System.out.println(attack(myCharacter, myOtherCharacter));
        System.out.println(buffHP(myCharacter));
        System.out.println(buffMaxHP(myCharacter));
        System.out.println(buffDP(myCharacter));
        System.out.println(buffSP(myCharacter));
        System.out.println(buffEP(myCharacter));
        System.out.println(characterAttacked(myCharacter));
        System.out.println(characterDead(myOtherCharacter));
        System.out.println(pickUpItem(myCharacter));
        System.out.println(dropItem(myCharacter));
        System.out.println(useItem(myCharacter));
        System.out.println(resetCharacter(myCharacter));

    }

    /**
     * dropItem checks same name method in AbstractCharacter
     *
     * @param myCharacter is the character
     * @return String whether method works or not
     */
    private static String dropItem(AbstractCharacter myCharacter) {
        String res;
        GameItem theItem = new LeachFang();
        myCharacter.pickUpItem(theItem);
        myCharacter.dropItem(theItem);
        if (!myCharacter.hasItem(theItem)) {
            res = "Item dropped";
        } else res = "Item not dropped!";
        return res;
    }

    /**
     * useItem checks same name method in AbstractCharacter
     *
     * @param myCharacter is the character
     * @return String whether method works or not
     */
    private static String useItem(AbstractCharacter myCharacter) {
        String res;
        myCharacter.resetCharacter();
        while(!myCharacter.attacked(20));
        GameItem theItem = new HealthPotion();
        myCharacter.pickUpItem(theItem);
        myCharacter.useItem(theItem);
        if (!myCharacter.hasItem(theItem) && myCharacter.getHP() != 5) {
            res = "Item used";
        } else res = "Item not used!";
        return res;
    }


    /**
     * pickUpItem checks same name method in AbstractCharacter
     *
     * @param myCharacter is the character
     * @return String whether method works or not
     */
    private static String pickUpItem(AbstractCharacter myCharacter) {
        String res;
        GameItem theItem = new GoblinSalve();
        myCharacter.pickUpItem(theItem);
        if (myCharacter.hasItem(theItem)) {
            res = "Item picked up";
        } else res = "Item not picked up!";
        return res;
    }

    /**
     * chacterDead tests checkIf Dead method in AbstractCharacter
     *
     * @param theCharacter is the character to die
     * @return String if method works or not
     */
    private static String characterDead(AbstractCharacter theCharacter) {
        String res;
        while (theCharacter.getHP() > 0) {
            theCharacter.attacked(30);
        }
        if (theCharacter.checkIfDead()) {
            res = "Character dies";
        } else res = "Character deosn't die!";
        return res;
    }

    /**
     * buffMaxHP tests buffMaxHp of AbstractCharacter
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String buffMaxHP(AbstractCharacter myCharacter) {
        String res;
        myCharacter.buffMaxHP(5);
        if(myCharacter.getHP() == 30) {
            res = "buffMaxHP works";
        } else res = "buffMaxHP doesn't work!";
        return res;
    }

    /**
     * buffDP tests buffDamage of AbstractCharacter
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String buffDP(AbstractCharacter myCharacter) {
        String res;
        myCharacter.buffDamage(5);
        if(myCharacter.getDamage() == 15) {
            res = "buffDamage works";
        } else res = "buffDamage doesn't work!";
        return res;
    }

    /**
     * buffSP tests buffSpeed of AbstractCharacter
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String buffSP(AbstractCharacter myCharacter) {
        String res;
        myCharacter.buffSpeed(2);
        if(myCharacter.getSpeed() == 4) {
            res = "buffSpeed works";
        } else res = "buffSpeed doesn't work!";
        return res;
    }

    /**
     * buffEP tests buffDodgeRate of AbstractCharacter
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String buffEP(AbstractCharacter myCharacter) {
        String res;
        myCharacter.buffDodgeRate(0.2);
        if(myCharacter.getDodgeRate() == 0.4) {
            res = "buffDodgeRate works";
        } else res = "buffDodgeRate doesn't work!";
        return res;
    }

    /**
     * buffHP tests buffHp of AbstractCharacter
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String buffHP(AbstractCharacter myCharacter) {
        String res;
        String s = characterAttacked(myCharacter);
        if (s.equals("Attack landed")) {
            myCharacter.buffHP(5);
            if(myCharacter.getHP() == 20) {
                res = "buffHP works";
            } else res = "buffHP doesn't work!";
        } else res = "Cannot test buffHP!";
        return res;
    }

    /**
     * attack checks attack method
     *
     * @param myCharacter is attacking character
     * @param myOtherCharacter Character is attacked character
     * @return string if method worked or not
     */
    private static String attack(AbstractCharacter myCharacter, AbstractCharacter myOtherCharacter) {
        String res;
        int i = 0;
        while (i < 3 && !myCharacter.attack(myOtherCharacter));
        if (i == 3) {
            res = "Attack fails!";
        } else res = "Attack succeeds";
        return res;
    }

    /**
     * getEp verifies getDodgeRaate works
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String getEP(AbstractCharacter myCharacter) {
        String res;
        if (0.2 == myCharacter.getDodgeRate()) {
            res = "Dodge rate returned";
        } else res = "Dodge rate not returned!";
        return res;
    }

    /**
     * resetCharater tests resetCharacter method of AbstractCharacter
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String resetCharacter(AbstractCharacter myCharacter) {
        String res;
        myCharacter.resetCharacter();
        if (25 == myCharacter.getHP()) {
            if (10 == myCharacter.getDamage()) {
                if (2 == myCharacter.getSpeed()) {
                    if (0.2 == myCharacter.getDodgeRate()) {
                        res = "Reset completed";
                    } else res = "Dodge rate not reset!";
                } else res = "Speed not reset!";
            } else res = "Damage not reset!";
        } else res = "HP not reset!";
        return res;
    }


    /**
     * getSP verifies getSpeed method of AbstractCharacter works
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String getSP(AbstractCharacter myCharacter) {
        String res;
        if (2 == myCharacter.getSpeed()){
            res = "SP returned";
        } else res = "SP not returned!";
        return res;
    }


    /**
     * getDP verifies getDdamage method of AbstractCharacter works
     *
     * @param myCharacter is the character
     * @return string whether method works or not
     */
    private static String getDP(AbstractCharacter myCharacter) {
        String res;
        if (10 == myCharacter.getDamage()){
            res = "DP returned";
        } else res = "DP not returned!";
        return res;
    }

    /**
     * getHP verifies getHP method of AbstractCharacter works
     *
     * @param myCharacter is the character
     * @return is string of if method works
     */
    private static String getHP(AbstractCharacter myCharacter) {
        String res;
        if (25 == myCharacter.getHP()) {
            res = "HP returned";
        } else res = "HP not returned!";
        return res;
    }

    /**
     * testMonsterCreation trys to create an abstract character as a skeleton monster
     *
     * @return returns a skeleton character
     */
    private static AbstractCharacter testCharacterCreation() {
        return new AbstractCharacter("Skeleton", 25, 10, 2, 0.2, new GameItem[] {});
    }

    /**
     * monsterAttacked checks if the monster was correctly attacked
     *
     * @param myMonster is monster to be attacked
     * @returns string whether the attack was succesful or not
     */
    private static String characterAttacked(AbstractCharacter myMonster) {
        int i = 0;
        while (i < 3 && !myMonster.attacked(10));
        String res;
        if (i == 3) {
            res = "Attack does not land!";
        } else res = "Attack landed";
        return res;
    }

    /**
     * getMonsterName checks getName method on abstract character
     *
     * @param myMonster is the monster
     * @return is string either getting name correctly or not
     */
    private static String getCharacterName(AbstractCharacter myMonster) {
        String res;
        if ("Skeleton".equals(myMonster.getName())) {
            res = "Name returned";
        } else res = "Name does not return!";
        return res;
    }
}
