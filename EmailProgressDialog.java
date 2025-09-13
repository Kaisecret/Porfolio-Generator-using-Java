import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
/**
 * Custom progress dialog to display while sending emails
 */
public class EmailProgressDialog extends JDialog {
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private JLabel animatedTextLabel;
    private Timer animationTimer;
    private int animationState = 0;
    private boolean isComplete = false;
    private Color accentColor = new Color(59, 130, 246); // Blue-500
    
    public EmailProgressDialog(Frame parent) {
        super(parent, "Sending Portfolio", true);
        initComponents();
    }
    
    private void initComponents() {
        setSize(400, 180);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        
        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Status label
        statusLabel = new JLabel("Preparing to send your portfolio...");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        
        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(350, 25));
        progressBar.setStringPainted(false);
        progressBar.setForeground(accentColor);
        mainPanel.add(progressBar, BorderLayout.CENTER);
        
        // Animated text for visual feedback
        animatedTextLabel = new JLabel("Please wait");
        animatedTextLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        animatedTextLabel.setForeground(Color.GRAY);
        mainPanel.add(animatedTextLabel, BorderLayout.SOUTH);
        
        // Set up animation timer
        animationTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
        
        // Add a "Cancel" button that appears after delay
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle cancel operation
                int response = JOptionPane.showConfirmDialog(
                    EmailProgressDialog.this,
                    "Are you sure you want to cancel sending the email?",
                    "Cancel Confirmation",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    public void start() {
        animationTimer.start();
        setVisible(true);
    }
    
    public void updateStatus(String status) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText(status);
        });
    }
    
    private void updateAnimation() {
        if (isComplete) {
            return;
        }
        
        String dots = "";
        for (int i = 0; i < animationState; i++) {
            dots += ".";
        }
        
        animatedTextLabel.setText("Please wait" + dots);
        animationState = (animationState + 1) % 4;
    }
    
    public void setProgress(int progress) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setIndeterminate(false);
            progressBar.setValue(progress);
        });
    }
    
    public void completeWithSuccess() {
        isComplete = true;
        animationTimer.stop();
        
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText("Email sent successfully!");
            progressBar.setValue(100);
            animatedTextLabel.setText("✓ Complete");
            animatedTextLabel.setForeground(new Color(34, 197, 94)); // Green color
            
            // Auto-close after delay
            Timer closeTimer = new Timer(2000, e -> dispose());
            closeTimer.setRepeats(false);
            closeTimer.start();
        });
    }
    
    public void completeWithError(String errorMessage) {
        isComplete = true;
        animationTimer.stop();
        
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText("Error sending email");
            progressBar.setValue(0);
            animatedTextLabel.setText("✗ Failed: " + errorMessage);
            animatedTextLabel.setForeground(new Color(220, 38, 38)); // Red color
            
            // Change to manual close with button
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(closeButton);
            
            // Replace the current south component
            Container contentPane = getContentPane();
            BorderLayout layout = (BorderLayout) ((JPanel)contentPane.getComponent(0)).getLayout();
            ((JPanel)contentPane.getComponent(0)).remove(layout.getLayoutComponent(BorderLayout.SOUTH));
            ((JPanel)contentPane.getComponent(0)).add(buttonPanel, BorderLayout.SOUTH);
            revalidate();
            repaint();
        });
    }
}
