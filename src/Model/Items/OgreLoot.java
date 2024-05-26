package Model.Items;

import Ausitn.GameItem;

import java.util.Random;

/**
 * OgreLoot randomly generates two loot game items
 * for a unique ogre character stored in an array.
 */
public class OgreLoot {

    /**
     * RAND constant is a random generator for
     * selecting the loot game items randomly.
     */
    final Random RAND = new Random();

    /**
     * myLoot is an array of GameItems that
     * is the loot for the calling ogre character.
     */
    private final GameItem[] myLoot;

    /**
     * OgreLoot constructor creates an array of
     * GameItem and fills it with two randomly selected
     * ogre loot game items.
     */
    public OgreLoot() {
        myLoot = new GameItem[2];
        myLoot[0] = getRandomLoot();
        myLoot[1] = getRandomLoot();
    }

    /**
     * getRandomLoot method returns a random
     * Ogre specific loot GameItem.
     *
     * @return returns a random ogre loot item
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new OgreClub();
            case 1:
                result = new Model.SpeedPotion();
            case 2:
                result = new GoblinhideCloak();
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
            if(myLoot[i].getItemName().equals("Ogre Club")) {
                loot[i] = new OgreClub();
            } else if (myLoot[i].getItemName().equals("Speed Potion")) {
                loot[i] = new Model.SpeedPotion();
            } else if(myLoot[i].getItemName().equals("Goblinhide Cloak")) {
                loot[i] = new GoblinhideCloak();
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
        if (myLoot[0].getItemName().equals("Ogre Club")) {
            loot = new OgreClub();
        } else if (myLoot[0].getItemName().equals("Speed Potion")) {
            loot = new Model.SpeedPotion();
        } else if(myLoot[0].getItemName().equals("Goblinhide Cloak")) {
            loot = new GoblinhideCloak();
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
        if (myLoot[1].getItemName().equals("Ogre Club")) {
            loot = new OgreClub();
        } else if (myLoot[1].getItemName().equals("SpeedPotion")) {
            loot = new SpeedPotion();
        } else if(myLoot[1].getItemName().equals("Goblinhide Cloak")) {
            loot = new GoblinhideCloak();
        } else loot = new GoldCoin();
        return loot;
    }
}
