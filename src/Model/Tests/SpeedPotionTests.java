package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.GameItem;
import Model.Items.SpeedPotion;

public class SpeedPotionTests {

    public static void main(String[] theArgs) {
        GameItem item = new SpeedPotion();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Speed Potion".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        int previousSpeed = theCharacter.getSpeed();
        theItem.useItem(theCharacter);
        int newSpeed = theCharacter.getSpeed();
        if (previousSpeed + 1 <= newSpeed && previousSpeed + 3 >= newSpeed) {
            return "Item used";
        } else return "Item not used!";
    }
}

