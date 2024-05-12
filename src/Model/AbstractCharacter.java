package Model;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * This class is the abstract level implementation for all game characters.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class AbstractCharacter {

    /**
     * HEALTH_POTION_MAX_BOUND constant is the max bound, i.e. the
     * range for the amount of hp to be returned by health potion
     */
    private static final int HEALTH_POTION_MAX_BOUND = 15;

    /**
     * HEALTH_POTION_MIN constant is the minimum amount
     * of hp to be returned by health potion
     */
    private static final int HEALTH_POTION_MIN = 15;

    /**
     * DAMAGE_POTION_MAX_BOUND constant is the max bound, i.e. the
     * range for the amount of damage points to be buffed by damage potion
     */
    private static final int DAMAGE_POTION_MAX_BOUND = 10;

    /**
     * DAMAGE_POTION_MIN constant is the minimum amount
     * of damage to be buffed by damage potion
     */
    private static final int DAMAGE_POTION_MIN = 5;

    /**
     * SPEED_POTION_MAX_BOUND constant is the max bound, i.e. the
     * range for the amount of speed points to be buffed by speed potion
     */
    private static final int SPEED_POTION_MAX_BOUND = 3;

    /**
     * SPEED_POTION_MIN constant is the minimum amount
     * of speed points to be buffed by speed potion
     */
    private static final int SPEED_POTION_MIN = 1;

    /**
     * EVASION_POTION_MAX_BOUND constant is the max bound, i.e. the range
     * for the amount of evasion percentage to be buffed by evasion potion
     */
    private static final int EVASION_POTION_MAX_BOUND = 2;

    /**
     * EVASION_POTION_MIN constant is the minimum amount
     * of evasion percentage to be buffed by evasion potion
     */
    private static final double EVASION_POTION_MIN = 0.5;

    /**
     * BOOT_SPEED_BUFF constant is the amount
     * of speed points to be buffed by boots
     */
    private static final int BOOT_SPEED_BUFF = 1;

    /**
     * BONE_SWORD_BUFF constant is the amount
     * of damage points to be buffed by Bone Sword
     */
    private static final int BONE_SWORD_BUFF = 10;

    /**
     * SOUL_CHARM_HP_BUFF is the buff to max hp if
     * hp is randomly chosen for soul charm buff
     */
    private static final int SOUL_CHARM_HP_BUFF = 25;

    /**
     * SOUL_CHARM_DAMAGE_BUFF is the buff to damage if
     * damage is randomly chosen for soul charm buff
     */
    private static final int SOUL_CHARM_DAMAGE_BUFF = 25;

    /**
     * SOUL_CHARM_SPEED_BUFF is the buff to speed if
     * speed is randomly chosen for soul charm buff
     */
    private static final int SOUL_CHARM_SPEED_BUFF = 3;

    /**
     * SOUL_CHARM_EVASION_BUFF is the buff to dodge rate if
     * evasion is randomly chosen for soul charm buff
     */
    private static final double SOUL_CHARM_EVASION_BUFF = 0.3;
    private static final int LEACH_TONIC_BUFF = 15;
    private static final int LEACH_FANG_BUFF = 5;
    private static final int GOBLIN_SWIFT_POWDER_BUFF = 1;
    private static final double GOBLIN_TRICKSTER_TALISMAN_BUFF = 0.15;
    private static final int GOBLIN_SALVE_BUFF = 25;
    private static final int OGRE_CLUB_BUFF = 20;
    private static final double GOBLINHIDE_CLOAK_BUFF = 0.15;

    /**
     * RANDOM constant is a Random generator for class usage.
     */
    private final Random RANDOM = new Random();
    /**
     * MAX_DODGE_RATE is the max allowed dodge rate
     */
    double MAX_DODGE_RATE = 0.7;

    /**
     * myName field is the name of the character.
     */
    private final String myName;

    /**
     * myHP field is the character's health points.
     */
    private int myHP;

    /**
     * myMaxHP field is the character's max health points.
     */
    private int myMaxHP;

    /**
     * myDamage field is the character's damage points.
     */
    private int myDamage;

    /**
     * mySpeed field is the character's speed points.
     */
    private int mySpeed;

    /**
     * myDodgeRate field is the character's dodge rate on scale 0.0 - 0.7 (0% to 70%)
     */
    private double myDodgeRate;

    /**
     * myBag field is the character's inventory bag of game items.
     */
    private final Bag myBag;

    /**
     * myDeathStatus is true iff character dies (hp drops to 0)
     */
    private boolean myDeathStatus;

    /**
     * AbstractCharacter constructor initializes all fields.
     *
     * @param theName is the character's name
     * @param theHP is the character's health points
     * @param theDamage is the character's damage points
     * @param theSpeed is the character's speed points
     * @param theDodgeRate is the character's dodge rate
     * @param theItems are the items to be included in initial bag (can be empty)
     */
  public  AbstractCharacter(String theName, int theHP, int theDamage, int theSpeed,
                            double theDodgeRate, GameItem[] theItems) {
      myName = Objects.requireNonNull(theName);
      setHPs(theHP);
      setDamage(theDamage);
      setSpeed(theSpeed);
      setDodgeRate(theDodgeRate);
      myBag = new Bag(theItems);
      myDeathStatus = false;
  }

    /**
     * setHP checks the input is valid. if so, it assigns the
     * parameter to myHP and myMaxHP fields. if not, it throws an exception.
     *
     * @param theHP is the initial health points for this character
     */
    private void setHPs(int theHP) {
        if (theHP <= 0) {
            throw new IllegalArgumentException("The HP must be a positive integer.");
        }
        myHP = theHP;
        myMaxHP = theHP;
    }

    /**
     * setDamage checks the input is valid. if so, it assigns the
     * parameter to myDamage field. if not, it throws an exception.
     *
     * @param theDamage is the damage points for this character
     */
    private void setDamage(int theDamage) {
        if (theDamage <= 0) {
            throw new IllegalArgumentException("The damage must be a positive integer.");
        }
        myDamage = theDamage;
    }

    /**
     * setSpeed checks the input is valid. if so, it assigns the
     * parameter to mySpeed field. if not, it throws an exception.
     *
     * @param theSpeed is the speed points for this character
     */
    private void setSpeed(int theSpeed) {
        if (theSpeed <= 0) {
            throw new IllegalArgumentException("theHP must be a positive integer.");
        }
    }

    /**
     * setDodgeRate checks the input is valid. if so, it assigns the
     * parameter to myDodgeRate field. if not, it throws an exception.
     *
     * @param theDodgeRate is the dodge rate (0 - 1) for this character
     */
    private void setDodgeRate(double theDodgeRate) {
        if (theDodgeRate < 0 || theDodgeRate > MAX_DODGE_RATE) {
            throw new IllegalArgumentException("The dodge rate must be a value between 0 and 0.7.");
        }
        myDodgeRate = theDodgeRate;
    }

    /**
     * attacked method receives an attack damage. Then it determines if the hit
     * lands using the dodge rate.If the hit lands, takeDamage is called.
     *
     * @param theDamage is the damage that will be inflicted on this character
     *                   if the attack lands
     * @return returns boolean true when the attack lands, and false otherwise
     */
  public boolean attacked(int theDamage) {
      if (theDamage <= 0) {
          throw new IllegalArgumentException("Damage must be a positive integer.");
      }
      boolean hitLanded = false;
      double dodge = RANDOM.nextDouble();
      if (dodge >= myDodgeRate) {
          takeDamage(theDamage);
          hitLanded = true;
      }
      return hitLanded;
  }

    /**
     * takeDamage method receives an integer for incoming damage, and removes
     * that much damage from this character's health points. If damage
     * inflicted causes the character's health points to drop below 1,
     * this character's isDead is assigned true.
     *
     * @param theDamage is the damage to be deducted from this character's hp
     */
  private void takeDamage(int theDamage) {
      myHP = myHP - theDamage;
      if (myHP < 1) {
          myHP = 0;
          myDeathStatus = true;
      }
  }

    /**
     * getName method returns the character's name/type
     */
    public String getName() {return myName;}

    /**
     * getHP method returns the character's current health points
     *
     * @return returns character's current health points
     */
  public int getHP() {
      return myHP;
  }

    /**
     * getDamage method returns the character's current damage points
     *
     * @return returns character's current damage points
     */
  public int getDamage() {
      return myDamage;
  }

    /**
     * getSpeed method returns the character's current speed points
     *
     * @return returns character's current speed points
     */
  public int getSpeed() {
      return mySpeed;
  }

    /**
     * attack method receives another character and attempts an attack on that
     * character. It will return truee if the attack lands, and false otherwise.
     *
     * @param theOtherCharacter is the character to be attacked
     * @return returns true when attack lands and false otherwise
     */
  public boolean attack(AbstractCharacter theOtherCharacter) {
      if (null == theOtherCharacter) {
          throw new IllegalArgumentException("Character must not be null");
      }
      boolean attackLanded = theOtherCharacter.attacked(myDamage);
      return attackLanded;
  }

    /**
     * buffHP method receives a number of points to be added to
     * the character's health points up to it's max hp.
     *
     * @param theHP is the points to be added to hp, up to max hp
     */
  public void buffHP(int theHP) {
      if (theHP < 1) {
          throw new IllegalArgumentException("HP boost must be a positive integer");
      }
      myHP += theHP;
      if (myHP > myMaxHP) {
          myHP = myMaxHP;
      }
  }

    /**
     * buffMaxHP method receives a number of points to be added to
     * the characters maximum health points and heals character to the max.
     *
     * @param theHP is the points to be added to max HP
     */
    public void buffMaxHP(int theHP) {
        if (theHP <= 0) {
            throw new IllegalArgumentException("Max HP buff must be a positive integer");
        }
        myMaxHP += theHP;
        myHP = myMaxHP;
    }

    /**
     * buffDamage method receives damage points, and adds
     * them to the character's current damage points.
     *
     * @param theDamage are the damage points to be added.
     */
  public void buffDamage(int theDamage) {
      if (theDamage <= 0) {
          throw new IllegalArgumentException("Damage buff must be a positive integer");
      }
      myDamage += theDamage;
  }

    /**
     * buffSpeed method receives speed points, and adds
     * them to the character's current speed points.
     *
     * @param theSpeed are the speed points to be added.
     */
  public void buffSpeed(int theSpeed) {
      if (theSpeed <= 0) {
          throw new IllegalArgumentException("Speed buff must be a positive integer");
      }
      mySpeed += theSpeed;
  }

    /**
     * buffDodgeRate method receives a dodge rate, and adds
     * it to the character's current dodge rate up to the max.
     *
     * @param theDodgeRate is the dodge rate to be added up to max.
     */
  public void buffDodgeRate(double theDodgeRate) {
      if (theDodgeRate <= 0) {
          throw new IllegalArgumentException("Dodge rate buff must be a positive double");
      }
      myDodgeRate += theDodgeRate;
      if (myDodgeRate > MAX_DODGE_RATE) {
          myDodgeRate = MAX_DODGE_RATE;
      }
  }

    /**
     * checkIfDead method gives status of character's death
     *
     * @return boolean true when dead and false otherwise
     */
    public boolean checkIfDead() {
        return myDeathStatus;
    }

    /**
     * addItemToBag receives an item and puts it into the character's bag
     *
     * @param theItem is the item to be stored in the bag
     */
  public void pickUpItem(GameItem theItem) {
      if (null == theItem) {
          throw new IllegalArgumentException("Item must not be null");
      }
      myBag.addItem(theItem);
  }

    /**
     * dropItem removes the item from the bag without using it
     * and returns the dropped item
     *
     * @return returns the dropped Game Item
     */
    public GameItem dropItem(GameItem theItem) {
        if (null == theItem) {
            throw new IllegalArgumentException("Item must not be null");
        }
        else if (!myBag.hasItem(theItem)) {
            throw new IllegalArgumentException("Item to drop must already"
                                               + " be in character's bag");
        }
        myBag.removeItem(theItem);
        return theItem;
    }

    /**
     * useItem method checks if the item is in the characters bag,
     * and if it is, it will use it. When using it, it will call methods
     * to provide certain status buff, and if applicable, destroy the object.
     *
     * @param theItem is the game item attempting to be used
     * @return returns a string as to what the item does, or that the bag didn't
     *          have the item
     */
  public String useItem(GameItem theItem) {
      if (theItem == null) {
          throw new IllegalArgumentException("Item must not be null");
      } else if (!myBag.hasItem(theItem)) {
          throw new IllegalArgumentException("Item to use must already"
                                            + " be in character's bag");
      }
      String result = "Bag does not contain this item!";
      if (myBag.hasItem(theItem)) {
          switch (theItem.getItemName()) {
              case "Health Potion":
                  int hp = RANDOM.nextInt(HEALTH_POTION_MAX_BOUND) + HEALTH_POTION_MIN;
                  buffHP(hp);
                  myBag.removeItem(theItem);
                  result = "Hero drank health potion."
                            + " Hero's health increased to " + myHP + " points!";
              case "Damage Potion":
                  int dp = RANDOM.nextInt(DAMAGE_POTION_MAX_BOUND) + DAMAGE_POTION_MIN;
                  buffDamage(dp);
                  myBag.removeItem(theItem);
                  result = "Hero drank damage potion."
                            + " Hero's damage increased " + dp + " points!";
              case "Speed Potion":
                  int sp = RANDOM.nextInt(SPEED_POTION_MAX_BOUND) + SPEED_POTION_MIN;
                  buffSpeed(sp);
                  myBag.removeItem(theItem);
                  result = "Hero drank speed potion."
                            + " Hero's speed increased " + sp + " points!";
              case "Evasion Potion":
                  if (myDodgeRate < MAX_DODGE_RATE) {
                      double ep = 0.1 * (RANDOM.nextInt(EVASION_POTION_MAX_BOUND) + EVASION_POTION_MIN);
                      buffDodgeRate(ep);
                      myBag.removeItem(theItem);
                      result = "Hero drank evasion potion."
                                + " Hero's dodge rate increased to "
                                + (myDodgeRate * 100) + " percent!";
                  } else result = "Hero's dodge rate is maximized!"
                                    + " Potion was not used.";
              case "Time Turner" :
                    // implement exiting the battle with the monster and resetting hero back to previous room
                  result = "Hero went back in time to before "
                           + "encountering the monster!";
              case "Soul Charm"  :
                  int randomBuff = RANDOM.nextInt(4);
                  result = "Hero donned Soul Charm.";
                  switch (randomBuff) {
                      case 0 :
                          buffMaxHP(SOUL_CHARM_HP_BUFF);
                          result += "Hero's max HP increased "
                                   + SOUL_CHARM_HP_BUFF + " points!";
                      case 1 :
                          buffDamage(SOUL_CHARM_DAMAGE_BUFF);
                          result += "Hero's damage increased "
                                   + SOUL_CHARM_DAMAGE_BUFF + " points!";
                      case 2 :
                          buffSpeed(SOUL_CHARM_SPEED_BUFF);
                          result += "Hero's speed increased "
                                   + SOUL_CHARM_SPEED_BUFF + " points!";
                      case 3 :
                          buffDodgeRate(SOUL_CHARM_EVASION_BUFF);
                          result += "Hero's dodge rate increased to "
                                  + myDodgeRate * 100 + " percent!";
                  }
                  myBag.removeItem(theItem);
              case "Leach Tonic" :
                  buffMaxHP(LEACH_TONIC_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero drank the leach tonic."
                          + " Hero's max HP increased by "
                          + LEACH_TONIC_BUFF + " points!";
              case "Leach Fang" :
                  buffDamage(LEACH_FANG_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero donned leach fang."
                          + " Hero's damage increased by "
                          + LEACH_FANG_BUFF + " points!";
              case "Goblin Swift Powder" :
                  buffSpeed(GOBLIN_SWIFT_POWDER_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero inhaled goblin swift powder."
                          + "Hero's speed increased by "
                          + GOBLIN_SWIFT_POWDER_BUFF + " point!";
              case "Goblin Trickster Talisman" :
                  buffDodgeRate(GOBLIN_TRICKSTER_TALISMAN_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero donned goblin trickster talisman."
                          + " Hero's dodge rate increased to "
                          + (myDodgeRate * 100) + " percent!";
              case "Goblin Salve" :
                  buffHP(GOBLIN_SALVE_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero applied goblin salve."
                          + " Hero's health increased to " + myHP + " points!";
              case "Ogre Club" :
                  buffDamage(OGRE_CLUB_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero picked up ogre club."
                          + " Hero's damage increased by "
                          + OGRE_CLUB_BUFF + " points!";
              case "Goblinhide Cloak" :
                  buffDodgeRate(GOBLINHIDE_CLOAK_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero donned goblinhide cloak."
                          + " Hero's didge rate increased to "
                          + (myDodgeRate * 100) + " percent!";
              case "Archaic Boots":
                  buffSpeed(BOOT_SPEED_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero donned the archaic boots."
                          + " Hero's speed increased by "
                          + BOOT_SPEED_BUFF + " point!";
              case "Bone Sword":
                  buffDamage(BONE_SWORD_BUFF);
                  myBag.removeItem(theItem);
                  result = "Hero grabbed the bone sword."
                          + " Hero's damage increased by "
                          + BONE_SWORD_BUFF + " points!";
              case "Gold Coin" :
                  result = "Hero admired the gold coin,"
                          + " then put it back in the bag for later.";
          }
      }
      return result;
  }

    /**
     * inner class Bag is an inventory of game items for the character
     *
     * @author Austin Maggert
     * @version 03may2024
     */
  private class Bag {

        /**
         * myBag field stores the game items in an arraylist
         */
      private final ArrayList<GameItem> myImplementedBag;

        /**
         * Bag constructor receives an array of items, sometimes empty,
         * and initializes myBag to the contents of the array.
         *
         * @param theItems the array of game items to be initialized with
         */
      public Bag(GameItem[] theItems) {
          if (null == theItems) {
              throw new IllegalArgumentException("Items array must not be null");
          }
          myImplementedBag = new ArrayList<GameItem>();
          myImplementedBag.addAll(Arrays.asList(theItems));
      }

        /**
         * addItem method adds a game item to the bag
         *
         * @param theItem is the game item to be added to myBag
         */
      public void addItem(GameItem theItem) {
          if (null == theItem) {
              throw new IllegalArgumentException("Item must not be null");
          }
          myImplementedBag.add(theItem);
      }

        /**
         * removeItem method removes the item from the bag,
         * essentially destroying the game item.
         *
         * @param theItem is the game item to be removed
         */
      public void removeItem(GameItem theItem) {
          if (theItem == null) {
              throw new IllegalArgumentException("Item must not be null");
          } else if (!myBag.hasItem(theItem)) {
              throw new IllegalArgumentException("Item to use must already"
                      + " be in character's bag");
          }
          myImplementedBag.remove(theItem);
      }

        /**
         * getItem method provides the item at an index of the myBag arraylist
         *
         * @param theIndex is the index that we want the item from
         * @return returns the game item at theIndex in myBag
         */
      public GameItem getItem(int theIndex) {
          if (theIndex < 0 || theIndex >= myImplementedBag.size()) {
              throw new IllegalArgumentException("Index out of bounds");
          }
          return myImplementedBag.get(theIndex);
      }

        /**
         * getItems method provides external class the contents of the bag
         *
         * @return returns an array containing each item in the bag
         */
      public GameItem[] getItems() {
          GameItem[] items = new GameItem[myImplementedBag.size()];
          int i = 0;
          for (GameItem item : myImplementedBag) {
              items[i++] = item;
          }
          return items;
      }

        /**
         * hasItem method checks if an item is in myBag
         *
         * @param theItem is the item to be checked if it is in the bag
         * @return returns true if the item is in the bag, false otherwise
         */
      public Boolean hasItem(GameItem theItem) {
          if (null == theItem) {
              throw new IllegalArgumentException("Item must not be null");
          }
          Boolean result = false;
          for (GameItem item : myImplementedBag) {
              if (item.getItemName().equals(theItem.getItemName())) {
                  result = true;
              }
          }
          return result;
      }
  }
}
