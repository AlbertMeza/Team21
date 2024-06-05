package Model.Tests;

import Model.Items.GameItem;
import Model.Items.OgreLoot;

public class OgreLootTests {

    public static void main(String[] theArgs) {
        OgreLoot theLoot = new OgreLoot();
        System.out.println(verifyGetLoot());
        System.out.println(verifyGetLoot1(theLoot));
        System.out.println(verifyGetLoot2(theLoot));
    }

    private static String verifyGetLoot() {
        int ClubCount = 0;
        int SpeedCount = 0;
        int CloakCount = 0;
        int CoinCount = 0;
        for (int i = 0; i < 100; i++) {
            OgreLoot theLoot = new OgreLoot();
            GameItem[] theItems = theLoot.getLoot();
            if ("Ogre Club".equals(theItems[0].getItemName())) {
                ClubCount++;
            } else if ("Speed Potion".equals(theItems[0].getItemName())) {
                SpeedCount++;
            } else if ("Goblinhide Cloak".equals(theItems[0].getItemName())) {
                CloakCount++;
            } else CoinCount++;
            if ("Ogre Club".equals(theItems[1].getItemName())) {
                ClubCount++;
            } else if ("Speed Potion".equals(theItems[1].getItemName())) {
                SpeedCount++;
            } else if ("Goblinhide Cloak".equals(theItems[1].getItemName())) {
                CloakCount++;
            } else CoinCount++;
        }
        if (ClubCount >= 15 && SpeedCount >= 15 && CloakCount >= 15 && CoinCount >= 15) {
            return "getLoot works properly";
        } else return "getLoot not working properly!";
    }

    private static String verifyGetLoot1(OgreLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootOne().getItemName().equals(loot[0].getItemName())) {
            return "getLoot1 works properly";
        } else return "getLoot1 doesn't work properly!";
    }

    private static String verifyGetLoot2(OgreLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootTwo().getItemName().equals(loot[1].getItemName())) {
            return "getLoot2 works properly";
        } else return "getLoot2 doesn't work properly!";
    }
}
