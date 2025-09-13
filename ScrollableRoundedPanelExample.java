package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class ScrollableRoundedPanelExample extends JFrame {

    // Custom rounded panel with shadow and draggable feature
    public static class RoundedPanel1 extends JPanel {
        private final int radius = 20;
        private final Color shadowColor = new Color(0, 0, 0, 35);
        private final int shadowOffset = 4;
        private Point initialClick;

        public RoundedPanel1() {
            setOpaque(false);

            // Add mouse listener to enable dragging
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    initialClick = e.getPoint();
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (SwingUtilities.getWindowAncestor(RoundedPanel1.this) instanceof JFrame frame) {
                        int x = frame.getLocation().x;
                        int y = frame.getLocation().y;
                        int movedX = e.getX() - initialClick.x;
                        int movedY = e.getY() - initialClick.y;
                        frame.setLocation(x + movedX, y + movedY);
                    }
                }
            });
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
                    width - shadowOffset, height - shadowOffset,
                    radius, radius));

            // Draw white background
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Double(
                    0, 0,
                    width - shadowOffset, height - shadowOffset,
                    radius, radius));

            g2.dispose();
            super.paintComponent(g);
        }
    }

    public ScrollableRoundedPanelExample() {
        setTitle("Scrollable Draggable Rounded Panel");
        setSize(500, 600);
        setUndecorated(true); // Allows dragging the whole frame with custom panel
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RoundedPanel1 roundedPanel = new RoundedPanel1();
        roundedPanel.setLayout(new BoxLayout(roundedPanel, BoxLayout.Y_AXIS));
        roundedPanel.setPreferredSize(new Dimension(450, 1000));

        // Add image labels
        for (int i = 1; i <= 10; i++) {
            JLabel imgLabel = new JLabel();
            ImageIcon icon = new ImageIcon("src/images/sample" + i + ".png");
            Image scaled = icon.getImage().getScaledInstance(400, 120, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaled));
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imgLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            roundedPanel.add(imgLabel);
        }

        JScrollPane scrollPane = new JScrollPane(roundedPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScrollableRoundedPanelExample::new);
    }
}
