package Ausitn;

/**
 * GoblinSalve class is a GameItem
 * that buffs health
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class GoblinSalve extends GameItem {

    /**
     * HP_BUFF is the amount of hp to buff
     * character health by.
     */
    private final int HP_BUFF = 25;

    /**
     * GoblinSalve constructor creates an instance of
     * Goblin Salve.
     */
    public GoblinSalve() {
        super("Goblin Salve");
    }

    /**
     * useItem buffs health of the passed character
     *
     * @param theCharacter is the hero using the GoblinSalve
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffHP(HP_BUFF);
    }
}


