package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GifPictureBox extends JPanel {
    private BufferedImage image;
    private ImageIcon gifIcon;
    private final JLabel gifLabel;

    public GifPictureBox() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        setPreferredSize(new Dimension(400, 400));

        gifLabel = new JLabel("", JLabel.CENTER);
        gifLabel.setOpaque(false);
        add(gifLabel, BorderLayout.CENTER);
    }

    public void setImage(File file) {
        try {
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith(".gif")) {
                gifIcon = new ImageIcon(file.getAbsolutePath());
                gifLabel.setIcon(gifIcon);
                gifLabel.setVisible(true);
                image = null;
            } else {
                image = ImageIO.read(file);
                gifIcon = null;
                gifLabel.setVisible(false);  // Hide the label when showing static images
            }
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            double imgAspect = (double) imgWidth / imgHeight;
            double panelAspect = (double) panelWidth / panelHeight;

            int drawWidth = panelWidth;
            int drawHeight = panelHeight;

            if (panelAspect > imgAspect) {
                drawWidth = (int) (panelHeight * imgAspect);
            } else {
                drawHeight = (int) (panelWidth / imgAspect);
            }

            int x = (panelWidth - drawWidth) / 2;
            int y = (panelHeight - drawHeight) / 2;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.drawImage(image, x, y, drawWidth, drawHeight, this);
        }
    }
}
