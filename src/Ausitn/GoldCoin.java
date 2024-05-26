package Ausitn;

/**
 * GoldCoin class is a gold coin
 * Game Item. Its purpose is currently unknown
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class GoldCoin extends GameItem {

    /**
     * GoldCoin constructor creates a gold coin
     * game item
     */
    public GoldCoin(){
        super("Gold Coin");
    }

    /**
     * useItem doesn't currently do anything
     * for Gold Coins
     *
     * @param theCharacter is the hero trying to use the gold coin
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        //add an action for this item here
    }
}
