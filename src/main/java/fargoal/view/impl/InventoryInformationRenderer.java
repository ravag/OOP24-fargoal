package fargoal.view.impl;

import java.awt.Color;
import java.awt.Font;

import fargoal.model.entity.player.api.Inventory;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;

/**
 * Class that work to render infos about the Player's inventory.
 */
public class InventoryInformationRenderer implements Renderer {

    private static final int DIVISOR_WIDTH_SECOND_COLUMN = 16;
    private static final int CONSTANT_SIX = 6;
    private static final int CONSTANT_SEVEN = 7;
    private static final int CONSTANT_FIVE = 5;
    private static final int DIVISOR_WIDTH_VALUES = 32;
    private static final int DIVISOR_HEIGHT_LAST_COLUMN = 24;
    private static final int DIVISOR_WIDTH_LAST_COLUMN = 4;
    private static final int CONSTANT_TWENTYTHREE = 23;
    private static final int MULTIPLIER_WIDTH_LAST_COLUMN_VALUES = 31;
    private static final int MULTIPLIER_VALUE_TELEPORT = 11;
    private static final int MULTIPLIER_VALUE_SHIELD = 15;
    private static final int MULTIPLIER_VALUE_LIGHT = 19;
    private static final int FONT_HEIGHT_DIVISOR = 10;

    private SwingRendererBottom renderer;
    private final SwingView view;

    /**
     * Constructor that assigns to the local field view the
     * principal view value of the game.
     * 
     * @param view - the view of the game
     */
    public InventoryInformationRenderer(final View view) {
        this.view = (SwingView) view;
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        renderer.render();
    }
    /**
     * Method that set the render of all the infos about the player's inventory.
     * 
     * @param inventory - the player's inventory
     */
    public void setRenderer(final Inventory inventory) {
        renderer = new SwingRendererBottom(g2d -> {
            g2d.setFont(new Font("Arial", 
                        Font.BOLD, 
                        this.view.getInformationPanel().getBounds().height * 1 / FONT_HEIGHT_DIVISOR));
            g2d.setColor(Color.WHITE);
            g2d.drawString("ENCHANTED SWORD+",
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN,
                    this.view.getInformationPanel().getBounds().height / 3);
            g2d.drawString(String.valueOf(inventory.getEnchantedWeapons().getNumberInInventory()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_SEVEN 
                                / DIVISOR_WIDTH_SECOND_COLUMN, 
                    this.view.getInformationPanel().getBounds().height / 3);
            g2d.drawString("SLAIN FOES", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN, 
                    this.view.getInformationPanel().getBounds().height / CONSTANT_SIX);
            g2d.drawString("DUNGEON LEVEL", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN, 
                    this.view.getInformationPanel().getBounds().height * 2 / 3);
            g2d.drawString("DEEPEST DESCENT", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_FIVE 
                                / CONSTANT_SIX);
            g2d.drawString("BEACONS ",
                    this.view.getInformationPanel().getBounds().width / 2,
                    this.view.getInformationPanel().getBounds().height / CONSTANT_SIX);
            g2d.drawString(String.valueOf(inventory.getBeacons().getNumberInInventory()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height / CONSTANT_SIX);
            g2d.drawString("MAGIC SACKS ",
                    this.view.getInformationPanel().getBounds().width / 2,
                    this.view.getInformationPanel().getBounds().height / 2);
            g2d.drawString(String.valueOf(inventory.getMagicSacks().getNumberInInventory()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height / 2);
            g2d.drawString("HEALING POTIONS ",
                    this.view.getInformationPanel().getBounds().width / 2,
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_FIVE 
                                / CONSTANT_SIX);
            g2d.drawString(String.valueOf(inventory.getHealingPotions().getNumberInInventory()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_FIVE 
                                / CONSTANT_SIX);
            if (inventory.getInvisibilitySpell().getNumberInInventory() > 0 
                        && !inventory.getSpellCasted().get(SpellType.INVISIBILITY.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.INVISIBILITY.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.getInvisibilitySpell().getNumberInInventory() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.INVISIBILITY.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("INVISIBILITY", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height * 3 / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.getInvisibilitySpell().getNumberInInventory()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height * 3 / DIVISOR_HEIGHT_LAST_COLUMN);
            if (inventory.getRegenerationSpell().getNumberInInventory() > 0 
                        && !inventory.getSpellCasted().get(SpellType.REGENERATION.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.REGENERATION.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.getInvisibilitySpell().getNumberInInventory() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.REGENERATION.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("REGENERATION", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_SEVEN 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.getRegenerationSpell().getNumberInInventory()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_SEVEN 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            if (inventory.getTeleportSpell().getNumberInInventory() > 0) {
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("TELEPORT", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_TELEPORT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.getTeleportSpell().getNumberInInventory()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_TELEPORT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            if (inventory.getShieldSpell().getNumberInInventory() > 0 
                        && !inventory.getSpellCasted().get(SpellType.SHIELD.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.SHIELD.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.getInvisibilitySpell().getNumberInInventory() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.SHIELD.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("SHIELD", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_SHIELD 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.getShieldSpell().getNumberInInventory()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_SHIELD 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            if (inventory.getLightSpell().getNumberInInventory() 
                        > 0 && !inventory.getSpellCasted().get(SpellType.LIGHT.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.LIGHT.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.getInvisibilitySpell().getNumberInInventory() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.LIGHT.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("LIGHT", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_LIGHT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.getLightSpell().getNumberInInventory()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_LIGHT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            if (inventory.getDriftSpell().getNumberInInventory() > 0 
                        && !inventory.getSpellCasted().get(SpellType.DRIFT.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.DRIFT.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.getInvisibilitySpell().getNumberInInventory() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.DRIFT.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("DRIFT", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.getDriftSpell().getNumberInInventory()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
        }, view);
    } 
}
