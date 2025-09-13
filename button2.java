/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package content;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 *
 * @author janna
 */
public class button2  extends JButton {
     private static final Color PRIMARY_COLOR = new Color(0, 120, 215);
    private static final Color HOVER_COLOR = new Color(0, 100, 190);
    private static final Color TEXT_COLOR = Color.WHITE;

    private boolean hovered = false;

    public button2(String text) {
        super(text);
        setupButton();
        setText(text);
    }

    public button2() {
        super();
        setupButton();
    }

    private void setupButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(TEXT_COLOR);
        setBackground(PRIMARY_COLOR);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        int size = 80; // Diameter of the circle
        setPreferredSize(new Dimension(size, size));

        // Hover effect
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape circle = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
        g2.setColor(hovered ? HOVER_COLOR : getBackground());
        g2.fill(circle);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape circle = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
        return circle.contains(x, y);
    }
    
}
