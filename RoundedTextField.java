package MAIN;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

public class RoundedTextField extends JTextField {
    private final int radius = 5;
    private final Color shadowColor = new Color(0, 0, 0, 30); // Light transparent shadow
    private final int shadowOffset = 2;

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false);    // No background painting by Swing
        setBorder(null);     // No border
        setBackground(Color.WHITE); // Background color
        setForeground(Color.BLACK); // Text color
        setCaretColor(Color.BLACK); // Caret color
        setMargin(new java.awt.Insets(5, 10, 5, 10)); // Padding inside
        setFocusable(true);  // Can still focus and type
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw shadow
        g2.setColor(shadowColor);
        g2.fill(new RoundRectangle2D.Double(
            shadowOffset, shadowOffset,
            width - shadowOffset * 2, height - shadowOffset * 2,
            radius, radius
        ));

        // Draw background
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(
            0, 0,
            width - shadowOffset, height - shadowOffset,
            radius, radius
        ));

        g2.dispose();

        // Now draw text and caret normally
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing: No border
    }
}
