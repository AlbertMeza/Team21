package Model;

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
                result = new GameItem("Goblin Salve");
            case 1:
                result = new GameItem("Goblin Swift Powder");
            case 2:
                result = new GameItem("Goblin Trickster Talisman");
            case 3:
                result = new GameItem("Gold Coin");
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
        loot[0] = new GameItem(myLoot[0].getItemName());
        loot[1] = new GameItem(myLoot[1].getItemName());
        return loot;
    }

    /**
     * getLootOne provides users with a deep copy
     * of the first item in myLoot.
     *
     * @return returns a deep copy of the first item in myLoot
     */
    public GameItem getLootOne() {
        return new GameItem(myLoot[0].getItemName());
    }

    /**
     * getLootTwo provides users with a deep copy
     * of the second item in myLoot.
     *
     * @return returns a deep copy of the second item in myLoot
     */
    public GameItem getLootTwo() {
        return new GameItem(myLoot[1].getItemName());
    }
}
