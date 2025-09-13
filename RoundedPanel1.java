package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class RoundedPanel1 extends JPanel {
    private final int radius = 20; // Corner radius
    private final Color shadowColor = new Color(0, 0, 0, 35); // Shadow color with transparency
    private final int shadowOffset = 3; // Shadow offset
    private final float shadowBlur = 10.0f; // (Note: not used directly here)

    public RoundedPanel1() {
        setOpaque(false); // Allows custom painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw shadow
        g2.setColor(shadowColor);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.fill(new RoundRectangle2D.Double(
            shadowOffset, 
            shadowOffset, 
            width - shadowOffset, 
            height - shadowOffset, 
            radius, radius
        ));

        // Draw white background on top
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Double(
            0, 0, 
            width - shadowOffset, // Optional: adjust if needed
            height - shadowOffset, // Optional: adjust if needed
            radius, radius
        ));

        g2.dispose();
        super.paintComponent(g);
    }
}
