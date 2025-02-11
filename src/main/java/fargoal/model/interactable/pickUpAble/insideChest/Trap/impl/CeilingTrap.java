package fargoal.model.interactable.pickUpAble.insideChest.Trap.impl;

import java.util.Random;

import fargoal.model.interactable.pickUpAble.insideChest.Trap.api.AbstractTrap;
import fargoal.model.interactable.pickUpAble.insideChest.Trap.api.TrapType;
import fargoal.model.manager.api.FloorManager;

/**
 * This is the implementation of the Ceiling Trap, which can be found in a chest.
 * It damages the player when he find it.
 */
public class CeilingTrap extends AbstractTrap {

    /**
     * This is the constructor of the class. When the player finds the trap in the chest it damages him immediately.
     * @param floorManager - it contains all the element of the floor the trap was found.
     */
    public CeilingTrap(FloorManager floorManager) {
        this.use(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return TrapType.CEILING_TRAP.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void effect(FloorManager floorManager) {
        int damage = new Random().nextInt(9) + floorManager.getFloorLevel();
        floorManager.getPlayer().getHealth().decreaseHealth(damage);
    } 
    
}
