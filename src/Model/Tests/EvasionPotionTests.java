package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.GameItem;
import Model.Items.EvasionPotion;

public class EvasionPotionTests {

    public static void main(String[] theArgs) {
        GameItem item = new EvasionPotion();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Evasion Potion".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        double previousDodgeRate = theCharacter.getDodgeRate();
        theItem.useItem(theCharacter);
        double newDodgeRate = theCharacter.getDodgeRate();
        if (previousDodgeRate + 0.5 <= newDodgeRate && previousDodgeRate + 1.5 >= newDodgeRate) {
            return "Item used";
        } else return "Item not used!";
    }
}


