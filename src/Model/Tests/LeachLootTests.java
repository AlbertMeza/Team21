package Model.Tests;

import Model.GameItem;
import Model.Items.LeachLoot;

public class LeachLootTests {

    public static void main(String[] theArgs) {
        LeachLoot theLoot = new LeachLoot();
        System.out.println(verifyGetLoot());
        System.out.println(verifyGetLoot1(theLoot));
        System.out.println(verifyGetLoot2(theLoot));
    }

    private static String verifyGetLoot() {
        int FangCount = 0;
        int EvasionCount = 0;
        int TonicCount = 0;
        int CoinCount = 0;
        for (int i = 0; i < 100; i++) {
            LeachLoot theLoot = new LeachLoot();
            GameItem[] theItems = theLoot.getLoot();
            if ("Leach Fang".equals(theItems[0].getItemName())) {
                FangCount++;
            } else if ("Evasion Potion".equals(theItems[0].getItemName())) {
                EvasionCount++;
            } else if ("Leach Tonic".equals(theItems[0].getItemName())) {
                TonicCount++;
            } else CoinCount++;
            if ("Leach Fang".equals(theItems[1].getItemName())) {
                FangCount++;
            } else if ("Evasion Potion".equals(theItems[1].getItemName())) {
                EvasionCount++;
            } else if ("Leach Tonic".equals(theItems[1].getItemName())) {
                TonicCount++;
            } else CoinCount++;
        }
        if (FangCount >= 15 && EvasionCount >= 15 && TonicCount >= 15 && CoinCount >= 15) {
            return "getLoot works properly";
        } else return "getLoot not working properly!";
    }

    private static String verifyGetLoot1(LeachLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootOne().getItemName().equals(loot[0].getItemName())) {
            return "getLoot1 works properly";
        } else return "getLoot1 doesn't work properly!";
    }

    private static String verifyGetLoot2(LeachLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootTwo().getItemName().equals(loot[1].getItemName())) {
            return "getLoot2 works properly";
        } else return "getLoot2 doesn't work properly!";
    }
}
