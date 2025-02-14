package fargoal.view.impl;

import java.awt.Color;
import java.awt.Font;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;

/**
 * Class that work to render infos about the Player's status.
 */
public class PlayerInformationRenderer implements Renderer {

    private static final int FONT_GOLD_HP_HEIGHT_DIVISOR = 110;
    private static final int FONT_STAT_DIVISOR = 10;
    private static final int FIRST_COLUMN_DIVISOR_WIDTH = 130;
    private static final int FIRST_COLUMN_DIVISOR_HEIGHT = 55;
    private static final int MULTIPLIER_EXPERIENCE_HEIGHT = 15;
    private static final int MULTIPLIER_LEVEL_HEIGHT = 30;
    private static final int MULTIPLIER_SKILL_HEIGHT = 45;
    private static final int CONSTANT_FIVE = 5;
    private static final int CONSTANT_SIX = 6;
    private static final int CONSTANT_SEVEN = 7;
    private static final int DIVISOR_WIDTH_SECOND_COLUMN = 16;

    private SwingRendererBottom rendererBottom; 
    private SwingRendererTop rendererTop;
    private final View view;

    /**
     * Constructor that assigns to the local field view the
     * principal view value of the game.
     * 
     * @param view - the view of the game
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "The class needs to work on the same view as the one given"
            + "so if the one given changes the reference also needs to change"
    )
    public PlayerInformationRenderer(final View view) {
        this.view = view;
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        rendererTop.render();
        rendererBottom.render();
    }

    /**
     * Method that set the render of all the infos about the player's status.
     * 
     * @param floorManager - the floormanager to take all the necessary infos
     */
    public void setRender(final FloorManager floorManager) {
        final SwingView swing = (SwingView) view;
        this.rendererTop = new SwingRendererTop(g2d -> {
            if (floorManager.getPlayer().getHealth().getCurrentHealth() 
                    > floorManager.getPlayer().getHealth().getMaxHealth() / 2) {
                g2d.setColor(Color.GREEN);
            } else if (floorManager.getPlayer().getHealth().getCurrentHealth() 
                    > floorManager.getPlayer().getHealth().getMaxHealth() / 3) {
                g2d.setColor(Color.ORANGE);
            } else {
                g2d.setColor(Color.RED);
            }
            g2d.setFont(new Font("Arial", Font.BOLD, swing.getFrame().getBounds().height * 3 / FONT_GOLD_HP_HEIGHT_DIVISOR));
            g2d.drawString("HP: " 
                    + floorManager.getPlayer().getHealth().getCurrentHealth() 
                    + "/" 
                    + floorManager.getPlayer().getHealth().getMaxHealth(), 
                    swing.getEventPanel().getBounds().width / CONSTANT_SIX, 
                    swing.getEventPanel().getBounds().height * 3 / CONSTANT_FIVE);
            g2d.setColor(Color.YELLOW);
            g2d.drawString("GOLD: " + floorManager.getPlayer().getCurrentGold(), 
                    swing.getEventPanel().getBounds().width / 2,
                    swing.getEventPanel().getBounds().height * 3 / CONSTANT_FIVE);
        }, view);
        this.rendererBottom = new SwingRendererBottom(g2d -> {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, swing.getInformationPanel().getBounds().height * 1 / FONT_STAT_DIVISOR));
            g2d.drawString("SLAIN FOES",
                    swing.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN,
                    swing.getInformationPanel().getBounds().height / CONSTANT_SIX);
            g2d.drawString(String.valueOf(floorManager.getPlayer().getNumberOfSlainFoes()), 
                        swing.getInformationPanel().getBounds().width
                        * CONSTANT_SEVEN
                        / DIVISOR_WIDTH_SECOND_COLUMN, 
                        swing.getInformationPanel().getBounds().height / CONSTANT_SIX);
            g2d.drawString("DUNGEON LEVEL",
                    swing.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN,
                    swing.getInformationPanel().getBounds().height * 2 / 3);
            g2d.drawString(String.valueOf(floorManager.getFloorLevel()),
                    swing.getInformationPanel().getBounds().width
                                * CONSTANT_SEVEN
                                / DIVISOR_WIDTH_SECOND_COLUMN,
                    swing.getInformationPanel().getBounds().height * 2 / 3);
            g2d.drawString("DEEPEST DESCENT",
                    swing.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN,
                    swing.getInformationPanel().getBounds().height
                                * CONSTANT_FIVE
                                / CONSTANT_SIX);
            g2d.drawString(String.valueOf(floorManager.getPlayer().getDeepestDescent()), 
                        swing.getInformationPanel().getBounds().width
                        * CONSTANT_SEVEN
                        / DIVISOR_WIDTH_SECOND_COLUMN, 
                        swing.getInformationPanel().getBounds().height
                        * CONSTANT_FIVE
                        / CONSTANT_SIX);
            g2d.drawString("EXPERIENCE  " + floorManager.getPlayer().getExperiencePoints(),
                    swing.getInformationPanel().getBounds().width * 2 / FIRST_COLUMN_DIVISOR_WIDTH,
                    swing.getInformationPanel().getBounds().height * MULTIPLIER_EXPERIENCE_HEIGHT / FIRST_COLUMN_DIVISOR_HEIGHT);
            g2d.drawString("LEVEL  " + floorManager.getPlayer().getLevel(),
                    swing.getInformationPanel().getBounds().width * 2 / FIRST_COLUMN_DIVISOR_WIDTH,
                    swing.getInformationPanel().getBounds().height * MULTIPLIER_LEVEL_HEIGHT / FIRST_COLUMN_DIVISOR_HEIGHT);
            g2d.drawString("SKILL  " + floorManager.getPlayer().getSkill(),
                    swing.getInformationPanel().getBounds().width * 2 / FIRST_COLUMN_DIVISOR_WIDTH,
                    swing.getInformationPanel().getBounds().height * MULTIPLIER_SKILL_HEIGHT / FIRST_COLUMN_DIVISOR_HEIGHT);
        }, view);
    }
}
