package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.Items.GameItem;
import Model.Items.TimeTurner;

public class TimeTurnerTests {

    public static void main(String[] theArgs) {
        GameItem item = new TimeTurner();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Time Turner".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        int previousMaxHP = theCharacter.getHP();
        int previousDamage = theCharacter.getDamage();
        int previousSpeed = theCharacter.getSpeed();
        double previousDodgeRate = theCharacter.getDodgeRate();
        theCharacter.buffMaxHP(20);
        theCharacter.buffDamage(20);
        theCharacter.buffSpeed(5);
        theCharacter.buffDodgeRate(0.2);
        theItem.useItem(theCharacter);
        int newMaxHP = theCharacter.getHP();
        int newDamage = theCharacter.getDamage();
        int newSpeed = theCharacter.getSpeed();
        double newDodgeRate = theCharacter.getDodgeRate();
        theItem.useItem(theCharacter);
        newMaxHP = theCharacter.getHP();
        newDamage = theCharacter.getDamage();
        newSpeed = theCharacter.getSpeed();
        newDodgeRate = theCharacter.getDodgeRate();
        if (previousMaxHP != newMaxHP) {
            return "Item didn't reset Max hp!";
        } else if (previousDamage != newDamage) {
            return "Item didnt' reset damage!";
        }else if (previousSpeed != newSpeed) {
            return "Item didn't reset speed!";
        } else if (previousDodgeRate != newDodgeRate) {
            return "Item didn't reset dodge rate!";
        } else return "Item used properly";
    }
}
