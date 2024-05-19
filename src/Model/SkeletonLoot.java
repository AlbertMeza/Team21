package Model;

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

    public SkeletonLoot() {
        myLoot = new GameItem[2];
        myLoot[0] = getRandomLoot();
        myLoot[1] = getRandomLoot();
    }

    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new BoneSword();
            case 1:
                result = new HealthPotion();
            case 2:
                result = new ArchaicBoots();
            case 3:
                result = new GoldCoin();
        }
        return result;
    }

    public GameItem[] getLoot() {
        return myLoot;
    }

}
