package content;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ModernDropDownMenu extends JFrame {

    public ModernDropDownMenu() {
        setTitle("Modern Dropdown UI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Panel with padding and background
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JLabel label = new JLabel("Choose a theme color:");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(60, 60, 60));

        String[] options = {"Red", "Green", "Blue", "Purple"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Custom UI
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setFont(new Font("Arial", Font.PLAIN, 12));
                button.setForeground(Color.GRAY);
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorder(null);
                return button;
            }
        });

        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setForeground(new Color(50, 50, 50));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));
        comboBox.setPreferredSize(new Dimension(200, 40));

        // Rounded border
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus
            ) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setBorder(new EmptyBorder(5, 10, 5, 10));
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                if (isSelected) {
                    lbl.setBackground(new Color(100, 149, 237));
                    lbl.setForeground(Color.WHITE);
                }
                return lbl;
            }
        });

        panel.add(label);
        panel.add(comboBox);
        add(panel);

        setVisible(true);
    }

   
}
