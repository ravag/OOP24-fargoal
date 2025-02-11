package fargoal.model.interactable.pickUpAble.insideChest.impl;

import fargoal.model.interactable.pickUpAble.insideChest.Spell.api.Spell;
import fargoal.model.interactable.pickUpAble.insideChest.Spell.impl.DriftSpell;
import fargoal.model.interactable.pickUpAble.insideChest.Spell.impl.InvisibilitySpell;
import fargoal.model.interactable.pickUpAble.insideChest.Spell.impl.LightSpell;
import fargoal.model.interactable.pickUpAble.insideChest.Spell.impl.RegenerationSpell;
import fargoal.model.interactable.pickUpAble.insideChest.Spell.impl.ShieldSpell;
import fargoal.model.interactable.pickUpAble.insideChest.Spell.impl.TeleportSpell;
import fargoal.model.interactable.pickUpAble.insideChest.Trap.impl.CeilingTrap;
import fargoal.model.interactable.pickUpAble.insideChest.Trap.impl.Explosion;
import fargoal.model.interactable.pickUpAble.insideChest.Trap.impl.Pit;
import fargoal.model.interactable.pickUpAble.insideChest.Trap.impl.Teleport;
import fargoal.model.interactable.pickUpAble.insideChest.Utility.api.Utility;
import fargoal.model.interactable.pickUpAble.insideChest.Utility.impl.Beacon;
import fargoal.model.interactable.pickUpAble.insideChest.Utility.impl.EnchantedWeapon;
import fargoal.model.interactable.pickUpAble.insideChest.Utility.impl.HealingPotion;
import fargoal.model.interactable.pickUpAble.insideChest.Utility.impl.MagicSack;
import fargoal.model.interactable.pickUpAble.insideChest.Utility.impl.Map;
import fargoal.model.interactable.pickUpAble.insideChest.api.ChestItem;
import fargoal.model.interactable.pickUpAble.insideChest.api.ChestItemFactory;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements the interface ChestItemFactory. It generate the chest items.
 */
public class ChestItemFactoryImpl implements ChestItemFactory {

    /** {@inheritDoc} */
    @Override
    public Spell generateDriftSpell(final FloorManager floorManager) {
        return new DriftSpell(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateInvisibilitySpell(final FloorManager floorManager) {
        return new InvisibilitySpell(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateLightSpell(final FloorManager floorManager) {
        return new LightSpell(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateRegenerationSpell(final FloorManager floorManager) {
        return new RegenerationSpell(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateShieldSpell(final FloorManager floorManager) {
        return new ShieldSpell(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateTeleportSpell(final FloorManager floorManager) {
        return new TeleportSpell(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generateCeilingTrap(final FloorManager floorManager) {
        return new CeilingTrap(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generateExplosion(final FloorManager floorManager) {
        return new Explosion(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generatePit(final FloorManager floorManager) {
        return new Pit(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generateTeleport(final FloorManager floorManager) {
        return new Teleport(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateBeacon(final FloorManager floorManager) {
        return new Beacon(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateEnchantedWeapon(final FloorManager floorManager) {
        return new EnchantedWeapon(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateHealingPotion(final FloorManager floorManager) {
        return new HealingPotion(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateMap(final FloorManager floorManager) {
        return new Map(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateMagicSack(final FloorManager floorManager) {
        return new MagicSack(floorManager);
    }

}
