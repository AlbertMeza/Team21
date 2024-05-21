package Model;

/**
 * BoneSword class is a GameItem
 * that buffs damage
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class BoneSword extends GameItem {

    /**
     * DP_BUFF is the amount of dp to buff
     * character damage by.
     */
    private final int DP_BUFF = 10;

    /**
     * BoneSword constructor creates an instance of
     * Bone Sword.
     */
    public BoneSword() {
        super("Bone Sword");
    }

    /**
     * useItem buffs damage of the passed character
     *
     * @param theCharacter is the hero using the Bone Sword
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffDamage(DP_BUFF);
    }
}



