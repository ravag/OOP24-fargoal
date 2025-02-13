package fargoal.model.interactable.pickUpAble.insideChest.Utility.impl;

import java.util.Random;

import fargoal.model.interactable.pickUpAble.insideChest.Utility.api.Utility;
import fargoal.model.interactable.pickUpAble.insideChest.api.ChestItemType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements an Healing Potion, which the player found in a chest.
 * It heal the player, incresing his health.
 */
public class HealingPotion implements Utility {

    private int numberInInventory;

    /**
     * This is the constructor of the class. It store right away the item the player found in a chest.
     * @param floorManager - it contains all the element of the floor the item is.
     * @param position - this is the position of the chest the item was found.
     */
    public HealingPotion(FloorManager floorManager) {
        this.numberInInventory = 0;
    }

    public HealingPotion() {
    } 

    /** {@inheritDoc} */
    @Override
    public String getChestItemType() {
        return ChestItemType.UTILITY.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return UtilityType.HEALING_POTION.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void use(FloorManager floorManager) {
        int healthToAdd = new Random().nextInt(19) + 3 * floorManager.getPlayer().getLevel();
        floorManager.getPlayer().getHealth().setHealth(floorManager.getPlayer().getHealth().getCurrentHealth() + healthToAdd);
        this.removeUtility();
    }

    /** {@inheritDoc} */
    @Override
    public void store(FloorManager floorManager) {
        this.addUtility();
    }

    /** {@inheritDoc} */
    @Override
    public int getNumberInInventory() {
        return this.numberInInventory;
    }

    /**
     * This method add an utility in the player's inventory.
     */
    private void addUtility() {
        this.numberInInventory++;
    }

    /** {@inheritDoc} */
    @Override
    public void removeUtility() {
        this.numberInInventory--;
    }
    
}
