package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.GameItem;
import Model.Items.GoblinhideCloak;

public class GoblinhideCloakTests {

    public static void main(String[] theArgs) {
        GameItem item = new GoblinhideCloak();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Goblinhide Cloak".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        double previousDodgeRate = theCharacter.getDodgeRate();
        theItem.useItem(theCharacter);
        double newDodgeRate = theCharacter.getDodgeRate();
        if (previousDodgeRate + 0.15 == newDodgeRate) {
            return "Item used";
        } else return "Item not used!";
    }
}




