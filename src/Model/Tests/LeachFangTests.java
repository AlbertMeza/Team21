package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.Items.GameItem;
import Model.Items.LeachFang;

public class LeachFangTests {

    public static void main(String[] theArgs) {
        GameItem item = new LeachFang();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Leach Fang".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        int previousDamage = theCharacter.getDamage();
        theItem.useItem(theCharacter);
        int newDamage = theCharacter.getDamage();
        if (previousDamage + 15 == newDamage) {
            return "Item used";
        } else return "Item not used!";
    }
}


