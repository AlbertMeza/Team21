package Ausitn;

/**
 * TimeTurner class is a Time Turner Game Item
 * that, when used during battle, brings the
 * hero back to the room before the battle.
 */
public class TimeTurner extends GameItem {

    /**
     * TimeTurner constructor creates and instance
     * of a Time Turner Game Item.
     */
    public TimeTurner() {
        super("Time Turner");
    }

    /**
     * This method resets the wizard back to original stats
     *
     * @param theCharacter is the hero to be reset
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.resetCharacter();
    }
}
