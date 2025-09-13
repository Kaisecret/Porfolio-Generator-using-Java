package Main;  // <--- make sure same package

import content.button1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PictureBoxUploadSupportGIF extends JFrame {

    private JLabel pictureBox;
    private button1 uploadButton;   // Use your custom button

    public PictureBoxUploadSupportGIF() {
        setTitle("Picture Box Upload (GIF Supported)");
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
        uploadButton = new button1("Upload Picture");   // <-- Your custom button1
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
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(
                "Image Files (PNG, JPG, JPEG, GIF)", "png", "jpg", "jpeg", "gif"));

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());

                // Show image directly; animated GIFs will animate automatically
                Image image = imageIcon.getImage();
                if (!selectedFile.getName().toLowerCase().endsWith(".gif")) {
                    // Resize for static images only
                    image = image.getScaledInstance(
                            pictureBox.getWidth(), pictureBox.getHeight(),
                            Image.SCALE_SMOOTH
                    );
                    pictureBox.setIcon(new ImageIcon(image));
                } else {
                    // Use original image icon for GIF
                    pictureBox.setIcon(imageIcon);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PictureBoxUploadSupportGIF::new);
    }
}
