package fargoal.model.interactable.pickUpAble.insideChest.Trap;

import java.util.Random;

import fargoal.commons.api.Position;
import fargoal.model.interactable.pickUpAble.insideChest.api.ChestItem;
import fargoal.model.interactable.pickUpAble.insideChest.api.ChestItemType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements a Teleport, a trap that can be found in a chest.
 * It teleport the player in a random position in the floor and there is a chance the player loses the map.
 */
public class Teleport implements ChestItem {

    /**
     * This is the constructor of the class. When the player finds the trap in a chest it teleport him immediately. 
     * @param floorManager - it contains all the element of the floor the trap was found.
     * @param position - this is the position of the chest the trap was found in.
     */
    public Teleport(FloorManager floorManager) {
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
        return TrapType.TELEPORT.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void use(FloorManager floorManager) {
        Position newPositionPlayer = floorManager.getFloorMap().getRandomTile();
        int chanceOfMapLost = new Random().nextInt(4);
        if (chanceOfMapLost == 0) {
            floorManager.getFloorMask().resetMask();
        }
        floorManager.getPlayer().setPosition(newPositionPlayer);
    }

}
