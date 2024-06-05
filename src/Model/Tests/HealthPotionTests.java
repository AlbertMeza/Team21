package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.Items.GameItem;
import Model.Items.HealthPotion;

public class HealthPotionTests {

    public static void main(String[] theArgs) {
        GameItem item = new HealthPotion();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Health Potion".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        while (!theCharacter.attacked(90));
        int previousHP = theCharacter.getHP();
        theItem.useItem(theCharacter);
        int newHP = theCharacter.getHP();
        if (previousHP + 15 <= newHP && previousHP + 29 >= newHP) {
            return "Item used";
        } else return "Item not used!";
    }
}
