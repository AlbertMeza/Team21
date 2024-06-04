package Model.Tests;

import Model.GameItem;
import Model.Items.SkeletonLoot;

public class SkeletonLootTests {

    public static void main(String[] theArgs) {
        SkeletonLoot theLoot = new SkeletonLoot();
        System.out.println(verifyGetLoot());
        System.out.println(verifyGetLoot1(theLoot));
        System.out.println(verifyGetLoot2(theLoot));
    }

    private static String verifyGetLoot() {
        int SwordCount = 0;
        int HealthCount = 0;
        int BootsCount = 0;
        int CoinCount = 0;
        for (int i = 0; i < 100; i++) {
            SkeletonLoot theLoot = new SkeletonLoot();
            GameItem[] theItems = theLoot.getLoot();
            if ("Bone Sword".equals(theItems[0].getItemName())) {
                SwordCount++;
            } else if ("Health Potion".equals(theItems[0].getItemName())) {
                HealthCount++;
            } else if ("Archaic Boots".equals(theItems[0].getItemName())) {
                BootsCount++;
            } else CoinCount++;
            if ("Bone Sword".equals(theItems[1].getItemName())) {
                SwordCount++;
            } else if ("Health Potion".equals(theItems[1].getItemName())) {
                HealthCount++;
            } else if ("Archaic Boots".equals(theItems[1].getItemName())) {
                BootsCount++;
            } else CoinCount++;
        }
        if (SwordCount >= 15 && HealthCount >= 15 && BootsCount >= 15 && CoinCount >= 15) {
            return "getLoot works properly";
        } else return "getLoot not working properly!";
    }

    private static String verifyGetLoot1(SkeletonLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootOne().getItemName().equals(loot[0].getItemName())) {
            return "getLoot1 works properly";
        } else return "getLoot1 doesn't work properly!";
    }

    private static String verifyGetLoot2(SkeletonLoot theLoot) {
        GameItem[] loot = theLoot.getLoot();
        if (theLoot.getLootTwo().getItemName().equals(loot[1].getItemName())) {
            return "getLoot2 works properly";
        } else return "getLoot2 doesn't work properly!";
    }
}
