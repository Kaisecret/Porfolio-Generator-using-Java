package MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    // Constructor that sets the default behavior
    public ImagePanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        setPreferredSize(new Dimension(400, 400)); // You can set the default size here
    }

    // Property to set the image in the ImagePanel
    public void setImage(File file) {
        try {
            image = ImageIO.read(file);
            repaint();  // Repaint the panel to show the image
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Paint the image inside the ImagePanel, resizing it to fit the panel with high-quality scaling
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Get the dimensions of the panel
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            // Calculate the aspect ratios
            double imgAspect = (double) imgWidth / imgHeight;
            double panelAspect = (double) panelWidth / panelHeight;

            // Determine the image's draw width and height based on aspect ratios
            int drawWidth = panelWidth;
            int drawHeight = panelHeight;

            if (panelAspect > imgAspect) {
                drawWidth = (int) (panelHeight * imgAspect);
            } else {
                drawHeight = (int) (panelWidth / imgAspect);
            }

            // Calculate the top-left corner position for centering the image
            int x = (panelWidth - drawWidth) / 2;
            int y = (panelHeight - drawHeight) / 2;

            // Use Graphics2D for better scaling quality
            Graphics2D g2d = (Graphics2D) g;
            
            // Set rendering hints for high-quality scaling
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            // Draw the image with high-quality scaling
            g2d.drawImage(image, x, y, drawWidth, drawHeight, this);
        }
    }
}
