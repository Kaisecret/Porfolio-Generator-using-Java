package content;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToggleSkillButton extends JToggleButton {

    private static final Color BORDER_COLOR = new Color(200, 200, 200); // light gray
    private static final Color SELECTED_COLOR = new Color(0, 102, 255); // blue when selected
    private static final Color DEFAULT_BACKGROUND = Color.WHITE; // default background
    private static final Color DEFAULT_TEXT_COLOR = new Color(0, 102, 255); // blue text
    private static final Color SELECTED_TEXT_COLOR = Color.WHITE; // white text when selected

    private boolean hovered = false;

    public ToggleSkillButton(String text) {
        super(text);
        setupButton();
    }

    public ToggleSkillButton() {
        super();
        setupButton();
    }

    private void setupButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(DEFAULT_TEXT_COLOR);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(100, 40)); // size

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

        // Background
        if (isSelected()) {
            g2.setColor(SELECTED_COLOR);
        } else if (hovered) {
            g2.setColor(new Color(230, 230, 230)); // slight hover background when not selected
        } else {
            g2.setColor(DEFAULT_BACKGROUND);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Border
        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        // Text
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(getText());
        int stringHeight = fm.getAscent();

        if (isSelected()) {
            g2.setColor(SELECTED_TEXT_COLOR);
        } else {
            g2.setColor(DEFAULT_TEXT_COLOR);
        }
        g2.drawString(getText(), (getWidth() - stringWidth) / 2, (getHeight() + stringHeight) / 2 - 4);

        g2.dispose();
    }
}
