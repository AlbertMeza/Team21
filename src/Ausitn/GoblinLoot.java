package Ausitn;

import java.util.Random;


/**
 * GoblinLoot randomly generates two loot game items
 * for a unique goblin character stored in an array.
 */
public class GoblinLoot {

    /**
     * RAND constant is a random generator for
     * selecting the loot game items randomly.
     */
    final Random RAND = new Random();

    /**
     * myLoot is an array of GameItems that
     * is the loot for the calling goblin character.
     */
    private final GameItem[] myLoot;

    /**
     * GoblinLoot constructor creates an array of
     * GameItem and fills it with two randomly selected
     * goblin loot game items.
     */
    public GoblinLoot() {
        myLoot = new GameItem[2];
        myLoot[0] = getRandomLoot();
        myLoot[1] = getRandomLoot();
    }

    /**
     * getRandomLoot method returns a random
     * Goblin specific loot GameItem.
     *
     * @return returns a random goblin loot item
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new GoblinSalve();
            case 1:
                result = new GoblinSwiftPowder();
            case 2:
                result = new GoblinTricksterTalisman();
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
            if(myLoot[i].getItemName().equals("Goblin Salve")) {
                loot[i] = new GoblinSalve();
            } else if (myLoot[i].getItemName().equals("Goblin Swift Powder")) {
                loot[i] = new GoblinSwiftPowder();
            } else if(myLoot[i].getItemName().equals("Goblin Trickster Talisman")) {
                loot[i] = new GoblinTricksterTalisman();
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
        if(myLoot[0].getItemName().equals("Goblin Salve")) {
            loot = new GoblinSalve();
        } else if (myLoot[0].getItemName().equals("Goblin Swift Powder")) {
            loot = new GoblinSwiftPowder();
        } else if(myLoot[0].getItemName().equals("Goblin Trickster Talisman")) {
            loot = new GoblinTricksterTalisman();
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
        if(myLoot[1].getItemName().equals("Goblin Salve")) {
            loot = new GoblinSalve();
        } else if (myLoot[1].getItemName().equals("Goblin Swift Powder")) {
            loot = new GoblinSwiftPowder();
        } else if(myLoot[1].getItemName().equals("Goblin Trickster Talisman")) {
            loot = new GoblinTricksterTalisman();
        } else loot = new GoldCoin();
        return loot;
    }
}
