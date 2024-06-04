package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.GameItem;
import Model.Items.GoblinSalve;

public class GoblinSalveTests {

    public static void main(String[] theArgs) {
        GameItem item = new GoblinSalve();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Goblin Salve".equals(theItem.getItemName())) {
            return "Name returned properly";
        } else return "Name not returned correctly!";
    }

    private static String verifyFunction(GameItem theItem, AbstractCharacter theCharacter) {
        while (!theCharacter.attacked(90));
        int previousHP = theCharacter.getHP();
        theItem.useItem(theCharacter);
        int newHP = theCharacter.getHP();
        if (previousHP + 25 == newHP) {
            return "Item used";
        } else return "Item not used!";
    }
}
