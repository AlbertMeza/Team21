package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.GameItem;
import Model.Items.SoulCharm;

public class SoulCharmTests {

    public static void main(String[] theArgs) {
        GameItem item = new SoulCharm();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Soul Charm".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        int previousMaxHP = theCharacter.getHP();
        int previousDamage = theCharacter.getDamage();
        int previousSpeed = theCharacter.getSpeed();
        double previousDodgeRate = theCharacter.getDodgeRate();
        theItem.useItem(theCharacter);
        int newMaxHP = theCharacter.getHP();
        int newDamage = theCharacter.getDamage();
        int newSpeed = theCharacter.getSpeed();
        double newDodgeRate = theCharacter.getDodgeRate();
        if (previousMaxHP + 25 == newMaxHP) {
            return "Item raised Max hp";
        } else if (previousDamage + 25 == newDamage) {
            return "Item raised damage";
        }else if (previousSpeed + 3 == newSpeed) {
            return "Item raised speed";
        } else if (previousDodgeRate + 0.3 == newDodgeRate) {
            return "Item raised dodge rate";
        } else return "Item not used properly!";
    }
}
