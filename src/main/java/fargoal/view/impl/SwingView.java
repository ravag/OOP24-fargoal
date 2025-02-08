package fargoal.view.impl;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.util.function.Consumer;
import javax.swing.WindowConstants;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fargoal.view.api.View;

public class SwingView implements View {

    private static final int FLOOR_LENGTH = 40;
    private static final int FLOOR_HEIGHT = 25;

    private SwingViewCanvas canvas;
    private JPanel top;
    private JPanel bottom;
    private JFrame frame;

    private int tilePixelDimWidth;
    private int tilePixelDimHeight;

    public SwingView() {
        this.frame = new JFrame();
        this.canvas = new SwingViewCanvas();
        this.top = new JPanel();
        this.bottom = new JPanel();
        this.frame.setLayout(new BorderLayout());
        this.frame.setSize(1500, 1000);
        var text = new JTextArea("TOP");
        text.setEditable(false);
        this.top.add(text);
        this.bottom.add(new JTextField("BOTTOM"));
        this.frame.getContentPane().add(canvas, BorderLayout.CENTER);
        this.frame.getContentPane().add(this.top, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.bottom, BorderLayout.SOUTH);
        this.frame.setResizable(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        calculateDimensions();
        this.frame.setVisible(true);
    }

    public int getTilePixelHeight() {
        return this.tilePixelDimHeight;
    }

    public int getTilePixelWidth() {
        return this.tilePixelDimWidth;
    }

    @Override
    public void update() {
        this.canvas.enableDraw(true);
        calculateDimensions();
        SwingUtilities.invokeLater(() -> this.canvas.repaint());
    }
    
    public void registerDrawingAction(Consumer<Graphics2D> g2d) {
        this.canvas.addToList(g2d);
    }

    public int getMapHeight() {
        return this.canvas.getBounds().height;
    }

    public int getMapWidth() {
        return this.canvas.getBounds().width;
    }

    private void calculateDimensions() {
        this.tilePixelDimHeight = (int) (this.getMapHeight() / FLOOR_HEIGHT);
        this.tilePixelDimWidth = (int) (this.getMapWidth() / FLOOR_LENGTH);
    }
}
