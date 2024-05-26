package Ausitn;

/**
 * ArchaicBoots class is a GameItem
 * that buffs speed
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class ArchaicBoots extends GameItem {

    /**
     * SP_BUFF is the amount of sp to buff
     * character speed by.
     */
    private final int SP_BUFF = 1;

    /**
     * ArchaicBoots constructor creates an instance of
     * Archaic Boots.
     */
    public ArchaicBoots() {
        super("Archaic Boots");
    }

    /**
     * useItem buffs speed of the passed character
     *
     * @param theCharacter is the hero using the Archaic Boots
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffSpeed(SP_BUFF);
    }
}

