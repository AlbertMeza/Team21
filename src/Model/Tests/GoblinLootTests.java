package Model.Tests;

import Model.Items.GameItem;
import Model.Items.GoblinLoot;

public class GoblinLootTests {

    public static void main(String[] theArgs) {
        GoblinLoot theLoot = new GoblinLoot();
        System.out.println(verifyGetLoot());
        System.out.println(verifyGetLoot1(theLoot));
        System.out.println(verifyGetLoot2(theLoot));
    }

    private static String verifyGetLoot() {
        int SalveCount = 0;
        int PowderCount = 0;
        int TalismanCount = 0;
        int CoinCount = 0;
        for (int i = 0; i < 100; i++) {
            GoblinLoot theLoot = new GoblinLoot();
            GameItem[] theItems = theLoot.getLoot();
            if ("Goblin Salve".equals(theItems[0].getItemName())) {
                SalveCount++;
            } else if ("Goblin Swift Powder".equals(theItems[0].getItemName())) {
                PowderCount++;
            } else if ("Goblin Trickster Talisman".equals(theItems[0].getItemName())) {
                TalismanCount++;
            } else CoinCount++;
            if ("Goblin Salve".equals(theItems[1].getItemName())) {
                SalveCount++;
            } else if ("Goblin Swift Powder".equals(theItems[1].getItemName())) {
                PowderCount++;
            } else if ("Goblin Trickster Talisman".equals(theItems[1].getItemName())) {
                TalismanCount++;
            } else CoinCount++;
        }
        if (SalveCount >= 15 && PowderCount >= 15 && TalismanCount >= 15 && CoinCount >= 15) {
            return "getLoot works properly";
        } else return "getLoot not working properly!";
    }

    private static String verifyGetLoot1(GoblinLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootOne().getItemName().equals(loot[0].getItemName())) {
            return "getLoot1 works properly";
        } else return "getLoot1 doesn't work properly!";
    }

    private static String verifyGetLoot2(GoblinLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootTwo().getItemName().equals(loot[1].getItemName())) {
            return "getLoot2 works properly";
        } else return "getLoot2 doesn't work properly!";
    }
}
