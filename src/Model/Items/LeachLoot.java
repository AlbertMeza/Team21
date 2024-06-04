package Model.Items;

import java.util.Random;

/**
 * LeachLoot randomly generates two loot game items
 * for a unique leach character stored in an array.
 */
public class LeachLoot {

    /**
     * RAND constant is a random generator for
     * selecting the loot game items randomly.
     */
    final Random RAND = new Random();

    /**
     * myLoot is an array of GameItems that
     * is the loot for the calling leach character.
     */
    private final GameItem[] myLoot;

    /**
     * LeachLoot constructor creates an array of
     * GameItem and fills it with two randomly selected
     * leach loot game items.
     */
    public LeachLoot() {
        myLoot = new GameItem[2];
        myLoot[0] = getRandomLoot();
        myLoot[1] = getRandomLoot();
    }

    /**
     * getRandomLoot method returns a random
     * Leach specific loot GameItem.
     *
     * @return returns a random leach loot item
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new LeachFang();
            case 1:
                result = new EvasionPotion();
            case 2:
                result = new LeachTonic();
            case 3:
                result = new GoldCoin();
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
            if(myLoot[i].getItemName().equals("Leach Fang")) {
                loot[i] = new LeachFang();
            } else if (myLoot[i].getItemName().equals("Evasion Potion")) {
                loot[i] = new EvasionPotion();
            } else if(myLoot[i].getItemName().equals("Leach Tonic")) {
                loot[i] = new LeachTonic();
            } else loot[i] = new GoldCoin();
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
        if (myLoot[0].getItemName().equals("Leach Fang")) {
            loot = new LeachFang();
        } else if (myLoot[0].getItemName().equals("EvasionPotion")) {
            loot = new EvasionPotion();
        } else if(myLoot[0].getItemName().equals("Leach Tonic")) {
            loot = new LeachTonic();
        } else loot = new GoldCoin();
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
        if (myLoot[1].getItemName().equals("Leach Fang")) {
            loot = new LeachFang();
        } else if (myLoot[1].getItemName().equals("EvasionPotion")) {
            loot = new EvasionPotion();
        } else if(myLoot[1].getItemName().equals("Leach Tonic")) {
            loot = new LeachTonic();
        } else loot = new GoldCoin();
        return loot;
    }
}
