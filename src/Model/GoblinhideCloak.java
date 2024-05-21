package Model;

/**
 * GoblinhideCloak class is a GameItem
 * that buffs evasion
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class GoblinhideCloak extends GameItem {

    /**
     * EP_BUFF is the amount of ep to buff
     * character dodge rate by.
     */
    private final double EP_BUFF = 0.15;

    /**
     * GoblinhideCloak constructor creates an instance of
     * Goblinhide Cloak.
     */
    public GoblinhideCloak() {
        super("Goblinhide Cloak");
    }

    /**
     * useItem buffs evasion of the passed character
     *
     * @param theCharacter is the hero using the Goblinhide Cloak
     */
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffDodgeRate(EP_BUFF);
    }
}


