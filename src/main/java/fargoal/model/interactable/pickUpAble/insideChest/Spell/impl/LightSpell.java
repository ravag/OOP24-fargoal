package fargoal.model.interactable.pickUpAble.insideChest.Spell.impl;

import fargoal.model.interactable.pickUpAble.insideChest.Spell.api.AbstractSpell;
import fargoal.model.interactable.pickUpAble.insideChest.Spell.api.SpellType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements the Light Spell. It extends the abstract class AbstractSpell
 * When the player cast this spell he can uncover more map of the floor: 
 * he can see two tiles near him. If the player is not visible, it makes him visible.
 * The spell ends when the player change floor level.
 */
public class LightSpell extends AbstractSpell {

    /**
     * The constructor of the class. When The spell is found in a chest 
     * it is stored immediately in the player's inventory.
     * @param floorManager - it contains al the element in the floor.
     */
    public LightSpell(final FloorManager floorManager) {
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return SpellType.LIGHT.getName();
    }

    /**
     * This method can turn on and off the light spell of the player.
     * @param floorManager - it numberInInventoryains all the elements of the floor.
     */
    public void turnLight(final FloorManager floorManager) {
        if (floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.LIGHT.getName())) {
            if (floorManager.getPlayer().hasLight()) {
                floorManager.getPlayer().setHasLight(false);
                if (floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.INVISIBILITY.getName())) {
                    floorManager.getPlayer().setIsVisible(false);
                }
            } else {
                floorManager.getPlayer().setHasLight(true);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.LIGHT.getName())) {
            if (this.getFloorLevelSpellCast() != floorManager.getFloorLevel()) {
                floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.LIGHT.getName(), false);
                floorManager.getPlayer().setHasLight(false);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.LIGHT.getName(), true);
        floorManager.getPlayer().setHasLight(true);
        this.removeSpell();
        if (!floorManager.getPlayer().isVisible()) {
            floorManager.getPlayer().setIsVisible(true);
        }
    }

}
