package fargoal.manager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fargoal.model.core.GameEngine;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.Map;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.manager.impl.FloorManagerImpl;
import fargoal.model.map.api.FloorMap;
import fargoal.model.map.api.FloorMask;
import fargoal.model.map.impl.FloorMaskImpl;

class TestFloorManager {

    private static FloorManager manager;

    @BeforeAll
    static void init() {
        manager = new FloorManagerImpl(new GameEngine());
    }

    //Test to see if increaseFloorLevel and decreaseFloorLevel work appropriatly
    @Test
    void testFloorLevel() {
        assertEquals(1, manager.getFloorLevel());
        manager.increaseFloorLevel();
        manager.increaseFloorLevel();
        manager.increaseFloorLevel();
        assertEquals(4, manager.getFloorLevel());
        manager.decreaseFloorLevel();
        manager.decreaseFloorLevel();
        assertEquals(2, manager.getFloorLevel());
        manager.decreaseFloorLevel();
        assertThrows(IllegalStateException.class, () -> manager.decreaseFloorLevel()); //NOPMD cannot change lambda
        resetConditions();
    }

    //This test is aimed at seeing if everything gets a correct position associated with them,
    //basically it is trying to see if any element gets assigned a position where another element already was
    //CHECKSTYLE: MagicNumber OFF
    //All the numbers present in this test are number that have already given problems,
    //and are supposed to be limits
    @Test
    void testInitFloor() {
        for (long k = 0; k < 20; k++) {
            final FloorMap map = manager.getFloorMap();
            manager.increaseFloorLevel();
            assertNotEquals(map, manager.getFloorMap());
            final List<Monster> monsters = manager.getMonsters();
            assertEquals(7, monsters.size());
            for (int i = 0; i < monsters.size(); i++) {
                for (int j = 0; j < monsters.size(); j++) {
                    if (i != j) {
                        assertNotEquals(monsters.get(i).getPosition(), monsters.get(j).getPosition());
                    }
                }
            }
            assertTrue(manager.getInteractables().size() > 11);
            assertTrue(manager.getInteractables().size() <= 41);
            final var item = manager.getInteractables();
            for (int i = 0; i < manager.getInteractables().size(); i++) {
                for (int j = 0; j < manager.getInteractables().size(); j++) {
                    if (i != j) {
                        assertNotEquals(item.get(i).getPosition(), manager.getInteractables().get(j).getPosition()); 
                    }
                }
            }
            assertTrue(manager.getFloorMap().isTile(manager.getTemple().getPosition()));
        }
        resetConditions();
    }
    //CHECKSTYLE: MagicNumber ON
    @Test
    void testFloorChange() {
        //I need to test that the map, the monsters and the items change after every floor
        final FloorMap map = manager.getFloorMap();
        final List<Monster> monsters = List.copyOf(manager.getMonsters());
        final List<Interactable> items = List.copyOf(manager.getInteractables());
        manager.increaseFloorLevel();
        assertNotEquals(map, manager.getFloorMap());
        assertNotEquals(monsters, manager.getMonsters());
        assertNotEquals(items, manager.getInteractables());

        //I need to test if the floor for which a map was found gets cleared
        final Map testMap = new Map();
        testMap.store();
        final FloorMask mask = new FloorMaskImpl();
        mask.clearMask();
        //CHECKSTYLE: MagicNumber OFF
        //20 is the minimum numbeer of floors I need to check to see if the map was saved correctly
        for (int i = 0; i < 20; i++) {
            if (testMap.getListOfMaps().contains(manager.getFloorLevel())) {
                assertEquals(mask, manager.getFloorMask());
            }
        }
        //CHECKSTYLE: MagicNumber ON
        resetConditions();
    }

    //Test to see if it can exit when the player has the sword
    //This has already given issue
    @Test
    void testExitWithSword() {
        manager.getPlayer().setHasSword(true);
        assertDoesNotThrow(() -> manager.decreaseFloorLevel()); //NOPMD cannot change lambda expression
        manager.getPlayer().setHasSword(false);
        resetConditions();
    }

    private void resetConditions() {
        while (manager.getFloorLevel() != 1) {
            if (manager.getFloorLevel() > 1) {
                manager.decreaseFloorLevel();
            } else {
                manager.increaseFloorLevel();
            }
        }
    }
}
