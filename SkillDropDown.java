package content;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class SkillDropDown extends JPanel {
    private final JButton dropdownButton;
    private final JPopupMenu popupMenu;
    private final JCheckBox[] checkBoxes;
    private final String[] skills = {
        "Communication",
        "Teamwork",
        "Problem-Solving",
        "Adaptability",
        "Time Management",
        "Leadership",
        "Emotional Intelligence",
        "Creativity"
    };

    private final Color primaryColor = new Color(0, 102, 255);

    public SkillDropDown() {
        setLayout(new BorderLayout());
        setOpaque(false);

        // Button styling
        dropdownButton = new JButton("Select Skills ▼");
        dropdownButton.setFocusPainted(false);
        dropdownButton.setForeground(Color.WHITE);
        dropdownButton.setBackground(primaryColor);
        dropdownButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dropdownButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        dropdownButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dropdownButton.setHorizontalAlignment(SwingConstants.LEFT);

        // Rounded border
        dropdownButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        // Popup menu
        popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createLineBorder(primaryColor, 2, true));
        popupMenu.setBackground(Color.WHITE);

        checkBoxes = new JCheckBox[skills.length];

        for (int i = 0; i < skills.length; i++) {
            checkBoxes[i] = new JCheckBox(skills[i]);
            checkBoxes[i].setBackground(Color.WHITE);
            checkBoxes[i].setForeground(Color.DARK_GRAY);
            checkBoxes[i].setFont(new Font("Segoe UI", Font.PLAIN, 13));
            checkBoxes[i].setFocusPainted(false);
            popupMenu.add(checkBoxes[i]);
        }

        dropdownButton.addActionListener(e -> {
            popupMenu.show(dropdownButton, 0, dropdownButton.getHeight());
        });

        add(dropdownButton, BorderLayout.CENTER);
    }

    // Get selected skills
    public List<String> getSelectedSkills() {
        List<String> selected = new ArrayList<>();
        for (JCheckBox box : checkBoxes) {
            if (box.isSelected()) {
                selected.add(box.getText());
            }
        }
        return selected;
    }

    // Optional: Update button text with selected items
    public void updateButtonText() {
        List<String> selected = getSelectedSkills();
        if (selected.isEmpty()) {
            dropdownButton.setText("Select Skills ▼");
        } else {
            dropdownButton.setText("Selected: " + String.join(", ", selected));
        }
    }

    // Modern TextField Class
    public static class ModernTextField extends JTextField {
        private final int radius = 15;
        private final Color borderColor = new Color(0, 102, 255);

        public ModernTextField(int columns) {
            super(columns);
            setOpaque(false);
            setForeground(Color.DARK_GRAY);
            setBackground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.PLAIN, 13));
            setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(getBackground());
            g2.fill(shape);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape border = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(2));
            g2.draw(border);
            g2.dispose();
        }
    }

    // Main method to run a sample UI with Skill Dropdown and Modern Text Field
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Skill Dropdown Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // Add Skill Dropdown
            SkillDropDown skillDropDown = new SkillDropDown();
            panel.add(skillDropDown, BorderLayout.NORTH);

            // Add Modern TextField
            ModernTextField searchField = new ModernTextField(15);
            searchField.setText("Search Skills...");
            panel.add(searchField, BorderLayout.CENTER);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
