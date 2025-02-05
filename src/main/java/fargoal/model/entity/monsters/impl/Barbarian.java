package fargoal.model.entity.monsters.impl;

import fargoal.commons.api.Position;
import fargoal.model.entity.monsters.ai.Ai;
import fargoal.model.entity.monsters.api.AbstractMonster;
import fargoal.model.entity.monsters.api.MonsterType;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.map.api.FloorMap;

/**
 * A class that develops the monster Barbarian:
 * an enemy that is simple, he only attacks the player
 * and tries to kill him.
 */
public class Barbarian extends AbstractMonster {

    /**
     * A constructor for the Barbarian; it uses the
     * super of the AbstractMonster constructor.
     * 
     * @param position - the starting position
     * @param level - the level of the monster
     * @param floorMap - the floorMap where the monster is located
     * @param floorManager - to get infos about the other entities/items
     */
    public Barbarian(final Position position, final Integer level, final FloorMap floorMap, final FloorManager floorManager) {
        super(position, level, floorMap, floorManager);
        setMonsterType(MonsterType.BARBARIAN);
    }

    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "BARBARIAN";
    }

    /** {@inheritDoc} */
    @Override
    public void steal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'steal'");
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (this.areNeighbours(floorManager, 1)) {
            this.attack();
        } else {
            Ai.move(this, floorManager.getPlayer());
        }
    }
}
