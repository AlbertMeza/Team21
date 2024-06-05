package Model.Tests;

import Model.Character.AbstractCharacter;
import Model.Character.Wizard;
import Model.Items.GameItem;
import Model.Items.GoldCoin;

public class GoldCoinTests {

    public static void main(String[] theArgs) {
        GameItem item = new GoldCoin();
        AbstractCharacter wizard = new Wizard();
        System.out.println(verifyName(item));
        System.out.println(verifyFunction(item, wizard));
    }

    private static String verifyName(GameItem theItem) {
        if ("Gold Coin".equals(theItem.getItemName())) {
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
        if (previousMaxHP + 5 != newMaxHP) {
            return "Item didn't raise Max hp!";
        } else if (previousDamage + 5 != newDamage) {
            return "Item didnt' raise damage!";
        }else if (previousSpeed + 1 != newSpeed) {
            return "Item didn't raise speed!";
        } else if (previousDodgeRate + 0.1 != newDodgeRate) {
            return "Item didn't raise dodge rate!";
        } else return "Item used properly";
    }
}
