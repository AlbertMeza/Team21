package Model.Items;

import Model.Character.AbstractCharacter;
import Model.GameItem;

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
     * useItem buffs all stats a little and heals to max hp
     *
     * @param theCharacter is the hero using to use the gold coin
     */
    @Override
    public void useItem(AbstractCharacter theCharacter) {
        theCharacter.buffMaxHP(5);
        theCharacter.buffDamage(5);
        theCharacter.buffSpeed(1);
        theCharacter.buffDodgeRate(0.1);
    }
}
