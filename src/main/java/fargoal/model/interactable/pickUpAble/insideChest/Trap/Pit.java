package fargoal.model.interactable.pickUpAble.insideChest.Trap;

import java.util.Random;

import fargoal.model.interactable.pickUpAble.insideChest.Spell.impl.SpellType;
import fargoal.model.interactable.pickUpAble.insideChest.api.ChestItem;
import fargoal.model.interactable.pickUpAble.insideChest.api.ChestItemType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements a Pit, a trap which can be found in a chest.
 * It damages the player and there is a chance the player loses the map.
 */
public class Pit implements ChestItem {

/**
     * This is the constructor of the class. When the player finds the trap in a chest it damages him immediately. 
     * @param floorManager - it contains all the element of the floor the trap was found.
     * @param position - this is the position of the chest the trap was found in.
     */

    public Pit(FloorManager floorManager) {
        this.use(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemType() {
        return ChestItemType.TRAP.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return TrapType.PIT.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void use(FloorManager floorManager) {
        int damage = new Random().nextInt(9) + floorManager.getFloorLevel();   
        int chanceOfMapLost = new Random().nextInt(4);
        if (chanceOfMapLost == 0) {
            floorManager.getFloorMask().resetMask();
        }
        if (!floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.DRIFT.getName())) {
            floorManager.getPlayer().getHealth().decreaseHealth(damage);
        } else {
            floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.DRIFT.getName(), false);
        }
    }

}
