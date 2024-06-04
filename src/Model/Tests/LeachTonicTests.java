package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.GameItem;
import Model.Items.LeachTonic;

public class LeachTonicTests {

    public static void main(String[] theArgs) {
        GameItem item = new LeachTonic();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Leach Tonic".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        int previousMaxHP = theCharacter.getHP();
        theItem.useItem(theCharacter);
        int newHP = theCharacter.getHP();
        if (previousMaxHP + 15 == newHP) {
            return "Item used";
        } else return "Item not used!";
    }
}
