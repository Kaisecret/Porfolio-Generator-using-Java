/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package content;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author janna
 */
public class button1 extends JButton {
    private static final Color PRIMARY_COLOR = new Color(0, 120, 215);   // Default color
    private static final Color HOVER_COLOR = new Color(0, 100, 250);     // Hover color
    private static final Color TEXT_COLOR = Color.WHITE;

    private boolean hovered = false;

    public button1() {
        super();
        setupButton();
    }

    public button1(String text) {
        super(text);
        setupButton();
    }

    private void setupButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(TEXT_COLOR);
        setBackground(PRIMARY_COLOR);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(new EmptyBorder(12, 24, 12, 24));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

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

   
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rounded shape
        Shape round = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 8, 8);
        g2d.setClip(round);

        // Use hover color if hovered, otherwise default
        g2d.setColor(hovered ? HOVER_COLOR : getBackground());
        g2d.fill(round);

        super.paintComponent(g2d);
        g2d.dispose();
    }
}
