package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.GameItem;
import Model.Items.OgreClub;

public class OgreClubTests {

    public static void main(String[] theArgs) {
        GameItem item = new OgreClub();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Ogre Club".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        int previousDamage = theCharacter.getDamage();
        theItem.useItem(theCharacter);
        int newDamage = theCharacter.getDamage();
        if (previousDamage + 20 == newDamage) {
            return "Item used";
        } else return "Item not used!";
    }
}

