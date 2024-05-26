package Model.Items;

import Ausitn.ArchaicBoots;
import Ausitn.BoneSword;
import Ausitn.GameItem;

import java.util.Random;

/**
 * SkeletonLoot randomly generates two loot game items
 * for a unique skeleton character stored in an array.
 */
public class SkeletonLoot {

    /**
     * RAND constant is a random generator for
     * selecting the loot game items randomly.
     */
    final Random RAND = new Random();

    /**
     * myLoot is an array of GameItems that
     * is the loot for the calling skeleton character.
     */
    private final GameItem[] myLoot;

    /**
     * SkeletonLoot constructor creates an array of
     * GameItem and fills it with two randomly selected
     * skeleton loot game items.
     */
    public SkeletonLoot() {
        myLoot = new GameItem[2];
        myLoot[0] = getRandomLoot();
        myLoot[1] = getRandomLoot();
    }

    /**
     * getRandomLoot method returns a random
     * skeleton specific loot GameItem.
     *
     * @return returns a random skeleton loot item
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new BoneSword();
            case 1:
                result = new Model.HealthPotion();
            case 2:
                result = new ArchaicBoots();
            case 3:
                result = new Model.GoldCoin();
        }
        return result;
    }

    /**
     * getLoot provides users with an array of
     * deep copies of the Game Items in myLoot.
     *
     * @return returns a deep copy of myLoot
     */
    public GameItem[] getLoot() {
        GameItem[] loot = new GameItem[2];
        for (int i = 0; i < myLoot.length; i++) {
            if(myLoot[i].getItemName().equals("Bone Sword")) {
                loot[i] = new BoneSword();
            } else if (myLoot[i].getItemName().equals("Health Potion")) {
                loot[i] = new Model.HealthPotion();
            } else if(myLoot[i].getItemName().equals("Archaic Boots")) {
                loot[i] = new ArchaicBoots();
            } else loot[i] = new Model.GoldCoin();
        }
        return loot;
    }

    /**
     * getLootOne provides users with a deep copy
     * of the first item in myLoot.
     *
     * @return returns a deep copy of the first item in myLoot
     */
    public GameItem getLootOne() {
        GameItem loot = null;
        if(myLoot[0].getItemName().equals("Bone Sword")) {
            loot = new BoneSword();
        } else if (myLoot[0].getItemName().equals("Health Potion")) {
            loot = new Model.HealthPotion();
        } else if(myLoot[0].getItemName().equals("Archaic Boots")) {
            loot = new ArchaicBoots();
        } else loot = new Model.GoldCoin();
        return loot;
    }

    /**
     * getLootTwo provides users with a deep copy
     * of the second item in myLoot.
     *
     * @return returns a deep copy of the second item in myLoot
     */
    public GameItem getLootTwo() {
        GameItem loot = null;
        if(myLoot[1].getItemName().equals("Bone Sword")) {
            loot = new BoneSword();
        } else if (myLoot[1].getItemName().equals("Health Potion")) {
            loot = new HealthPotion();
        } else if(myLoot[1].getItemName().equals("Archaic Boots")) {
            loot = new ArchaicBoots();
        } else loot = new GoldCoin();
        return loot;
    }
}
