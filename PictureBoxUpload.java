package Main;  // <--- make sure same package

import content.button1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PictureBoxUpload extends JFrame {

    private JLabel pictureBox;
    private button1 uploadButton;   // Use your custom button
    private BufferedImage uploadedImage;

    public PictureBoxUpload() {
        setTitle("Picture Box Upload");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Absolute positioning like Windows Forms
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Picture Box
        pictureBox = new JLabel();
        pictureBox.setBounds(50, 30, 400, 300);
        pictureBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        pictureBox.setOpaque(true);
        pictureBox.setBackground(Color.WHITE);
        pictureBox.setHorizontalAlignment(JLabel.CENTER);
        pictureBox.setVerticalAlignment(JLabel.CENTER);
        add(pictureBox);

        // Upload Button
        uploadButton = new button1("Upload Picture");   // <-- Your button1
        uploadButton.setBounds(180, 350, 140, 40);
        add(uploadButton);

        // Upload Button Action
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadImage();
            }
        });

        setVisible(true);
    }

    private void uploadImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select an Image");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image Files (PNG, JPG, JPEG)", "png", "jpg", "jpeg"));

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                uploadedImage = ImageIO.read(selectedFile);
                if (uploadedImage != null) {
                    
                    Image scaled = uploadedImage.getScaledInstance(
                            pictureBox.getWidth(), pictureBox.getHeight(),
                            Image.SCALE_SMOOTH
                    );
                    pictureBox.setIcon(new ImageIcon(scaled));
                } else {
                    JOptionPane.showMessageDialog(this, "Unsupported Image Format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PictureBoxUpload::new);
    }
}
