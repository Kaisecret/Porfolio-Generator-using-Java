/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package content;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.Authenticator;
import static java.awt.font.TextHitInfo.leading;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import Session.sessions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author janna
 */
public class Generator extends javax.swing.JFrame {
    
    /**
     * Creates new form Generator
     */
    public Generator() {
        initComponents();
         setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
       
    }
    public class DBConnection {
                public static Connection getConnection() {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sbp", "root", "");
                    } catch (Exception e) {
                        System.out.println("DB Error: " + e.getMessage());
                        return null;
                    }
                }
            }
      public void addToTable(String companyName, String position, String duration, String description) {
    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
    model.addRow(new Object[]{companyName, position, duration, description});
}
      public void addToTable1(String projectName, String technologies, String duration, String description) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.addRow(new Object[]{projectName, technologies, duration, description});
}
 public static void generate(JTextField fullName, JTextField jobTitle, JTextField location,
                                JTextField email, JTextField phone, JTextField linkedIn,
                                JTextField summary, JTextField techSkills, JComboBox<String> softSkills,
                                JTextField specificTech, JTable experienceTable, JTable projectsTable,
                                JCheckBox[] topTechCheckboxes, JLabel pictureBox) {

        try (PDDocument document = new PDDocument()) {
            PDFont font = PDType1Font.HELVETICA;
            PDPage page1 = new PDPage(PDRectangle.A4);
            PDPage page2 = new PDPage(PDRectangle.A4);
            document.addPage(page1);
            document.addPage(page2);

            PDPageContentStream contentStream = new PDPageContentStream(document, page1);
            float y = 750;

            // --- Insert Image from PictureBox1
            Icon icon = pictureBox.getIcon();
            if (icon instanceof ImageIcon imageIcon) {
                BufferedImage bufferedImage = new BufferedImage(
                        imageIcon.getIconWidth(),
                        imageIcon.getIconHeight(),
                        BufferedImage.TYPE_INT_RGB
                );
                imageIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
                File imageFile = new File("profile_temp.jpg");
                ImageIO.write(bufferedImage, "jpg", imageFile);

                PDImageXObject pdImage = PDImageXObject.createFromFile("profile_temp.jpg", document);
                contentStream.drawImage(pdImage, 30, y - 100, 70, 70);
            }

            // --- Header: Name & Job Title
            contentStream.beginText();
            contentStream.setFont(font, 14);
            contentStream.newLineAtOffset(110, y - 40);
            contentStream.showText(fullName.getText() + " - " + jobTitle.getText());
            contentStream.endText();

            // --- Contact Details
            y -= 110;
            addText(contentStream, font, 10, 30, y, "Location: " + location.getText());
            addText(contentStream, font, 10, 30, y -= 15, "Email: " + email.getText());
            addText(contentStream, font, 10, 30, y -= 15, "Phone: " + phone.getText());
            addText(contentStream, font, 10, 30, y -= 15, "LinkedIn: " + linkedIn.getText());

            // --- Professional Summary
            y -= 30;
            addText(contentStream, font, 12, 30, y, "Professional Summary:");
            y -= 15;
            addWrappedText(contentStream, font, 10, 30, y, summary.getText(), 80);

            // --- Experience Table
            y -= 60;
            addText(contentStream, font, 12, 30, y, "Experience:");
            y -= 15;
            drawTable(contentStream, font, 10, 30, y, experienceTable);

            contentStream.close();

            // === PAGE 2 ===
            PDPageContentStream cs2 = new PDPageContentStream(document, page2);
            float y2 = 780;

            // --- Projects Table
            addText(cs2, font, 12, 30, y2, "Projects:");
            y2 -= 20;
            drawTable(cs2, font, 10, 30, y2, projectsTable);

            // --- Technical Skills
            y2 -= (projectsTable.getRowCount() + 2) * 15;
            addText(cs2, font, 12, 30, y2, "Technical Skills:");
            addText(cs2, font, 10, 40, y2 - 15, techSkills.getText());

            // --- Soft Skills
            y2 -= 35;
            addText(cs2, font, 12, 30, y2, "Soft Skills:");
            addText(cs2, font, 10, 40, y2 - 15, softSkills.getSelectedItem().toString());

            // --- Top Technologies (from checkboxes)
            y2 -= 35;
            addText(cs2, font, 12, 30, y2, "Top Technologies:");
            ArrayList<String> selectedTechs = new ArrayList<>();
            for (JCheckBox cb : topTechCheckboxes) {
                if (cb.isSelected()) selectedTechs.add(cb.getText());
            }
            addWrappedText(cs2, font, 10, 40, y2 - 15, String.join(", ", selectedTechs), 80);

            // --- Specific Technologies
            y2 -= 55;
            addText(cs2, font, 12, 30, y2, "Specific Technology:");
            addText(cs2, font, 10, 40, y2 - 15, specificTech.getText());

            cs2.close();

            // --- Save
            document.save("Generated_Portfolio.pdf");
            JOptionPane.showMessageDialog(null, "Portfolio PDF Created Successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addText(PDPageContentStream cs, PDFont font, int size, float x, float y, String text) throws IOException {
        cs.beginText();
        cs.setFont(font, size);
        cs.newLineAtOffset(x, y);
        cs.showText(text);
        cs.endText();
    }

    private static void addWrappedText(PDPageContentStream cs, PDFont font, int size, float x, float y, String text, int maxLines) throws IOException {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int lineCount = 0;
        for (String word : words) {
            if (line.length() + word.length() > 80) {
                addText(cs, font, size, x, y - (lineCount * 12), line.toString());
                line = new StringBuilder(word + " ");
                lineCount++;
                if (lineCount >= maxLines) break;
            } else {
                line.append(word).append(" ");
            }
        }
        if (lineCount < maxLines) {
            addText(cs, font, size, x, y - (lineCount * 12), line.toString());
        }
    }

    private static void drawTable(PDPageContentStream cs, PDFont font, int size, float x, float y, JTable table) throws IOException {
        TableModel model = table.getModel();
        for (int col = 0; col < model.getColumnCount(); col++) {
            addText(cs, font, size, x + col * 150, y, model.getColumnName(col));
        }

        y -= 15;
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object value = model.getValueAt(row, col);
                addText(cs, font, size, x + col * 150, y, value != null ? value.toString() : "");
            }
            y -= 15;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button17 = new content.button1();
        roundedPanel11 = new content.RoundedPanel1();
        button18 = new content.button1();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        button19 = new content.button1();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button21 = new content.button1();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        button15 = new content.button1();
        button22 = new content.button1();
        button23 = new content.button1();
        button26 = new content.button1();
        pictureBox1 = new Main.PictureBox();
        button16 = new content.button1();
        roundedPanel12 = new content.RoundedPanel1();
        button20 = new content.button1();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        skillDropDown1 = new con.SkillDropDown();
        generate = new content.button1();
        one = new content.ToggleSkillButton();
        one1 = new content.ToggleSkillButton();
        one3 = new content.ToggleSkillButton();
        one4 = new content.ToggleSkillButton();
        one5 = new content.ToggleSkillButton();
        one6 = new content.ToggleSkillButton();
        one2 = new content.ToggleSkillButton();
        one7 = new content.ToggleSkillButton();
        one10 = new content.ToggleSkillButton();
        one13 = new content.ToggleSkillButton();
        one16 = new content.ToggleSkillButton();
        one17 = new content.ToggleSkillButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        button17.setBackground(new java.awt.Color(0, 102, 255));
        button17.setText("String Builder Portfolio Generator");
        button17.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        button17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button17MouseClicked(evt);
            }
        });
        button17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button17ActionPerformed(evt);
            }
        });

        button18.setBackground(new java.awt.Color(204, 204, 255));
        button18.setForeground(new java.awt.Color(0, 102, 255));
        button18.setText("Projects");
        button18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button18MouseClicked(evt);
            }
        });
        button18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button18ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Full name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Job Title:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Location:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Phone number:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Linkedin:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Profesional Summary:");

        button19.setBackground(new java.awt.Color(204, 204, 255));
        button19.setForeground(new java.awt.Color(0, 102, 255));
        button19.setText("Personal Information");
        button19.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button19MouseClicked(evt);
            }
        });
        button19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button19ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project Name", "Technologies", "Duration", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        button21.setBackground(new java.awt.Color(204, 204, 255));
        button21.setForeground(new java.awt.Color(0, 102, 255));
        button21.setText("Experience");
        button21.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button21MouseClicked(evt);
            }
        });
        button21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button21ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Company Name", "Position", "Duration", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        button15.setBackground(new java.awt.Color(0, 102, 255));
        button15.setText("+ Add Experience");
        button15.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        button15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button15ActionPerformed(evt);
            }
        });

        button22.setBackground(new java.awt.Color(255, 0, 51));
        button22.setText("Delete");
        button22.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        button22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button22ActionPerformed(evt);
            }
        });

        button23.setBackground(new java.awt.Color(0, 102, 255));
        button23.setText("+ Add Project");
        button23.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        button23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button23ActionPerformed(evt);
            }
        });

        button26.setBackground(new java.awt.Color(255, 0, 51));
        button26.setText("Delete");
        button26.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        button26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pictureBox1Layout = new javax.swing.GroupLayout(pictureBox1);
        pictureBox1.setLayout(pictureBox1Layout);
        pictureBox1Layout.setHorizontalGroup(
            pictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pictureBox1Layout.setVerticalGroup(
            pictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 105, Short.MAX_VALUE)
        );

        button16.setBackground(new java.awt.Color(0, 102, 255));
        button16.setText("Upload");
        button16.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        button16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel11Layout = new javax.swing.GroupLayout(roundedPanel11);
        roundedPanel11.setLayout(roundedPanel11Layout);
        roundedPanel11Layout.setHorizontalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addComponent(button21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundedPanel11Layout.createSequentialGroup()
                                .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(29, Short.MAX_VALUE))))
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addComponent(button18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button26, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(645, 645, 645))
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField7)
                            .addGroup(roundedPanel11Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(button16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(67, 67, 67)
                                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                        .addComponent(jTextField3))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(40, 40, 40))))
        );
        roundedPanel11Layout.setVerticalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(button19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(roundedPanel11Layout.createSequentialGroup()
                            .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(roundedPanel11Layout.createSequentialGroup()
                            .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(roundedPanel11Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        button20.setBackground(new java.awt.Color(204, 204, 255));
        button20.setForeground(new java.awt.Color(0, 102, 255));
        button20.setText("Skills");
        button20.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button20MouseClicked(evt);
            }
        });
        button20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button20ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Expert Areas:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Top Technologies :");

        generate.setBackground(new java.awt.Color(0, 102, 255));
        generate.setText("Generate PDF");
        generate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });

        one.setText("JavaScript");
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }
        });

        one1.setText("PHP");
        one1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one1ActionPerformed(evt);
            }
        });

        one3.setText("Node.js");
        one3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one3ActionPerformed(evt);
            }
        });

        one4.setText("React");
        one4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one4ActionPerformed(evt);
            }
        });

        one5.setText("SQL");
        one5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one5ActionPerformed(evt);
            }
        });

        one6.setText("Java");
        one6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one6ActionPerformed(evt);
            }
        });

        one2.setText("Go (Golang)");
        one2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one2ActionPerformed(evt);
            }
        });

        one7.setText("C++");
        one7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one7ActionPerformed(evt);
            }
        });

        one10.setText("C#");
        one10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one10ActionPerformed(evt);
            }
        });

        one13.setText("TypeScript");
        one13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one13ActionPerformed(evt);
            }
        });

        one16.setText("Python");
        one16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one16ActionPerformed(evt);
            }
        });

        one17.setText("Web");
        one17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one17ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Soft skills:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Specify:");

        javax.swing.GroupLayout roundedPanel12Layout = new javax.swing.GroupLayout(roundedPanel12);
        roundedPanel12.setLayout(roundedPanel12Layout);
        roundedPanel12Layout.setHorizontalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(skillDropDown1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(roundedPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 298, Short.MAX_VALUE))
                            .addComponent(generate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(roundedPanel12Layout.createSequentialGroup()
                        .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel12Layout.createSequentialGroup()
                                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(one5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(roundedPanel12Layout.createSequentialGroup()
                                            .addGap(43, 43, 43)
                                            .addComponent(one4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(one, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundedPanel12Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(one2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(one1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(roundedPanel12Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(one3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(one6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(one16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(one17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(1, 1, 1)
                                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(one13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(one10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(one7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(roundedPanel12Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundedPanel12Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(300, Short.MAX_VALUE)))
        );
        roundedPanel12Layout.setVerticalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(skillDropDown1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(one3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(one5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(one16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(generate, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundedPanel12Layout.createSequentialGroup()
                    .addGap(199, 199, 199)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(476, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(roundedPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(roundedPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(button17, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundedPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_button17MouseClicked

    private void button18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_button18MouseClicked

    private void button18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button18ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void button19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_button19MouseClicked

    private void button19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button19ActionPerformed

    private void button20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_button20MouseClicked

    private void button20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button20ActionPerformed

    private void button21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_button21MouseClicked

    private void button21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button21ActionPerformed

    private void button15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button15ActionPerformed
       Addexpience a = new Addexpience(this); 
    a.setVisible(true);
       
    }//GEN-LAST:event_button15ActionPerformed

    private void button22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button22ActionPerformed
      
        int selectedRow = jTable2.getSelectedRow();

        
        if (selectedRow != -1) {
            
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

            
            model.removeRow(selectedRow);

            
            JOptionPane.showMessageDialog(null, "Row deleted successfully.");
        } else {
            
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }//GEN-LAST:event_button22ActionPerformed

    private void button23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button23ActionPerformed
       Addproject  a = new Addproject(this);
      a.setVisible(true);
    }//GEN-LAST:event_button23ActionPerformed

    private void button26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button26ActionPerformed

        int selectedRow = jTable1.getSelectedRow();

        
        if (selectedRow != -1) {
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            
            model.removeRow(selectedRow);

            
            JOptionPane.showMessageDialog(null, "Row deleted successfully.");
        } else {
           
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }       
    }//GEN-LAST:event_button26ActionPerformed


    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
    if (jTextField1.getText().trim().isEmpty() ||
    jTextField2.getText().trim().isEmpty() ||
    jTextField3.getText().trim().isEmpty() ||
    jTextField4.getText().trim().isEmpty() ||
    jTextField5.getText().trim().isEmpty() ||
    jTextField6.getText().trim().isEmpty() ||
    jTextField7.getText().trim().isEmpty() ||
    jTextField8.getText().trim().isEmpty() ||
    jTextField9.getText().trim().isEmpty() ||
    pictureBox1.getImage() == null ||
    jTable1.getRowCount() == 0 ||
    jTable2.getRowCount() == 0 ||
    skillDropDown1.getSelectedSkills().isEmpty()) {

    JOptionPane.showMessageDialog(this,
        "Please fill in all fields, select an image, add entries to both tables, and choose at least one soft skill.",
        "Missing Information",
        JOptionPane.WARNING_MESSAGE);
    return;
}
    PDDocument document = null;
    String pdfFilePath = null;
    
    try {
        document = new PDDocument();
        // Define fonts and colors
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontRegular = PDType1Font.HELVETICA;
        
        // Define colors (approximating the blue scheme from the React design)
        Color headerBgColor = new Color(37, 99, 235); // Blue-600
        Color textColor = new Color(17, 24, 39);      // Gray-900
        Color subtextColor = new Color(107, 114, 128); // Gray-500
        Color accentColor = new Color(59, 130, 246);  // Blue-500
        Color lightBlueColor = new Color(219, 234, 254); // Blue-100
        
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream cs = new PDPageContentStream(document, page);
        float margin = 40;
        float pageWidth = page.getMediaBox().getWidth() - 2 * margin;
        
        // Create unique filename with timestamp
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String username = "user";
        
        // Get username if logged in
        if (sessions.userId > 0) {
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            
            try {
                conn = DBConnection.getConnection();
                if (conn != null) {
                    String sql = "SELECT username FROM user_accounts WHERE id = ?";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, sessions.userId);
                    rs = pst.executeQuery();
                    if (rs.next() && rs.getString("username") != null) {
                        username = rs.getString("username").replaceAll("\\s+", "_");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error fetching username for file naming: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    System.err.println("Error closing database resources: " + e.getMessage());
                }
            }
        }
        
        // Create directory if it doesn't exist
        java.io.File directory = new java.io.File("generated_portfolios");
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        pdfFilePath = "generated_portfolios/" + username + "_portfolio_" + timestamp + ".pdf";
        
        // Start y position from the very top of the page
        float y = page.getMediaBox().getHeight(); 
        
        // Add colored header background - extend to full width and start from top
        cs.setNonStrokingColor(headerBgColor);
        cs.addRect(0, y - 100, page.getMediaBox().getWidth(), 100);
        cs.fill();
        
        // Reset color for text
        cs.setNonStrokingColor(Color.WHITE);

        // Insert profile image (top-left corner) - adjusted position for full-height usage
        BufferedImage bufferedImage = pictureBox1.getImage();
        if (bufferedImage != null) {
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferedImage);
            cs.drawImage(pdImage, margin + 10, y - 80, 60, 60);
        }

        // Name and Job Title (in header section) - adjusted for full height
        cs.beginText();
        cs.setFont(fontBold, 20);
        cs.newLineAtOffset(margin + 80, y - 35);
        cs.showText(jTextField1.getText()); // Full Name
        cs.endText();

        cs.beginText();
        cs.setFont(fontRegular, 14);
        cs.newLineAtOffset(margin + 80, y - 55);
        cs.showText(jTextField2.getText()); // Job Title
        cs.endText();
        
        // Reset color for main content
        cs.setNonStrokingColor(textColor);
        
        y -= 120;
        
        // Two-column layout for contact and summary
        float leftColWidth = pageWidth * 0.35f;
        float rightColWidth = pageWidth * 0.65f;
        float rightColX = margin + leftColWidth + 20;
        
        // Contact Info - Left Column
        // Draw section title with accent underline
        drawSectionHeader(cs, fontBold, "Contact", margin, y, accentColor);
        
        y -= 25;
        drawContactItem(cs, fontRegular, "Location: ", jTextField3.getText(), margin, y);
        y -= 15;
        drawContactItem(cs, fontRegular, "Email: ", jTextField4.getText(), margin, y);
        y -= 15;
        drawContactItem(cs, fontRegular, "Phone: ", jTextField5.getText(), margin, y);
        y -= 15;
        drawContactItem(cs, fontRegular, "LinkedIn: ", jTextField6.getText(), margin, y);
        
        // Professional Summary - Right Column
        float summaryY = y + 70; // Reset to match Contact section top
        // Align Professional Summary header with the same margin as other sections
        drawSectionHeader(cs, fontBold, "Professional Summary", margin + leftColWidth + 20, summaryY, accentColor);
        summaryY -= 25;
        addWrappedText(cs, fontRegular, 10, margin + leftColWidth + 20, summaryY, jTextField7.getText(), 65);
        
        // Experience Section - Full Width
        y -= 40;
        drawSectionHeader(cs, fontBold, "Experience", margin, y, accentColor);
        y -= 20;
        
        // Check if there's enough space for the experience table
        float experienceTableHeight = (jTable2.getRowCount() * 50) + 20;
        if (y - experienceTableHeight < 50) {
            // Not enough space, create a new page
            cs.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            cs = new PDPageContentStream(document, page);
            
            // Add blue header to new page
            cs.setNonStrokingColor(headerBgColor);
            cs.addRect(0, page.getMediaBox().getHeight() - 40, page.getMediaBox().getWidth(), 40);
            cs.fill();
            
            // Reset color and y position
            cs.setNonStrokingColor(textColor);
            y = page.getMediaBox().getHeight() - 60;
            
            // Redraw section header on new page
            drawSectionHeader(cs, fontBold, "Experience", margin, y, accentColor);
            y -= 20;
        }
        
        drawEnhancedTable(cs, document, fontBold, fontRegular, margin, y, jTable2, pageWidth, accentColor, lightBlueColor);
        
        // Calculate space used by the table
        y -= (jTable2.getRowCount() * 50) + 20;
        
        // Check if there's enough space for next sections
        if (y < 150) {
            // Not enough space, create a new page
            cs.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            cs = new PDPageContentStream(document, page);
            
            // Add blue header to new page
            cs.setNonStrokingColor(headerBgColor);
            cs.addRect(0, page.getMediaBox().getHeight() - 40, page.getMediaBox().getWidth(), 40);
            cs.fill();
            
            // Reset color and y position
            cs.setNonStrokingColor(textColor);
            y = page.getMediaBox().getHeight() - 60;
        }
        
        // Projects Section
        drawSectionHeader(cs, fontBold, "Projects", margin, y, accentColor);
        y -= 20;
        
        // Check if there's enough space for the projects table
        float projectsTableHeight = (jTable1.getRowCount() * 50) + 20;
        if (y - projectsTableHeight < 100) {
            // Not enough space, create a new page
            cs.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            cs = new PDPageContentStream(document, page);
            
            // Add blue header to new page
            cs.setNonStrokingColor(headerBgColor);
            cs.addRect(0, page.getMediaBox().getHeight() - 40, page.getMediaBox().getWidth(), 40);
            cs.fill();
            
            // Reset color and y position
            cs.setNonStrokingColor(textColor);
            y = page.getMediaBox().getHeight() - 60;
            
            // Redraw section header on new page
            drawSectionHeader(cs, fontBold, "Projects", margin, y, accentColor);
            y -= 20;
        }
        
        drawEnhancedTable(cs, document, fontBold, fontRegular, margin, y, jTable1, pageWidth, accentColor, lightBlueColor);
        
        // Calculate space used by the table
        y -= (jTable1.getRowCount() * 50) + 20;
        
        // Check if there's enough space for skills sections
        if (y < 150) {
            // Not enough space, create a new page
            cs.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            cs = new PDPageContentStream(document, page);
            
            // Add blue header to new page
            cs.setNonStrokingColor(headerBgColor);
            cs.addRect(0, page.getMediaBox().getHeight() - 40, page.getMediaBox().getWidth(), 40);
            cs.fill();
            
            // Reset color and y position
            cs.setNonStrokingColor(textColor);
            y = page.getMediaBox().getHeight() - 60;
        }
        
        // Two columns for skills sections
        float skillsY = y;
        
        // Technical Skills - Left Column
        drawSectionHeader(cs, fontBold, "Technical Skills", margin, skillsY, accentColor);
        skillsY -= 25;
        addWrappedText(cs, fontRegular, 10, margin, skillsY, jTextField8.getText(), 35);
        
        // Soft Skills - Right Column
        float softSkillsY = y;
        drawSectionHeader(cs, fontBold, "Soft Skills", rightColX, softSkillsY, accentColor);
        softSkillsY -= 25;
        List<String> selectedSkills = skillDropDown1.getSelectedSkills();
        String skillsString = selectedSkills.isEmpty() ? "None" : String.join(", ", selectedSkills);
        addWrappedText(cs, fontRegular, 10, rightColX, softSkillsY, skillsString, 35);
        
        // Determine which section used more vertical space
        y = Math.min(skillsY - 60, softSkillsY - 60);
        
        // Check if there's enough space for remaining sections
        if (y < 100) {
            // Not enough space, create a new page
            cs.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            cs = new PDPageContentStream(document, page);
            
            // Add blue header to new page
            cs.setNonStrokingColor(headerBgColor);
            cs.addRect(0, page.getMediaBox().getHeight() - 40, page.getMediaBox().getWidth(), 40);
            cs.fill();
            
            // Reset color and y position
            cs.setNonStrokingColor(textColor);
            y = page.getMediaBox().getHeight() - 60;
        }
        
        // Top Technologies
        drawSectionHeader(cs, fontBold, "Top Technologies", margin, y, accentColor);
        y -= 25;
        
        ArrayList<String> selectedTechs = new ArrayList<>();
        if(one.isSelected()) selectedTechs.add("JavaScript");
        if(one2.isSelected()) selectedTechs.add("Go Lang");
        if(one1.isSelected()) selectedTechs.add("PHP");
        if(one7.isSelected()) selectedTechs.add("C++");
        if(one4.isSelected()) selectedTechs.add("React.js");
        if(one3.isSelected()) selectedTechs.add("Node.js");
        if(one17.isSelected()) selectedTechs.add("Web");
        if(one10.isSelected()) selectedTechs.add("C#");
        if(one5.isSelected()) selectedTechs.add("SQL");
        if(one6.isSelected()) selectedTechs.add("Java");
        if(one16.isSelected()) selectedTechs.add("Python");
        if(one13.isSelected()) selectedTechs.add("TypeScript");
        
        drawTechTags(cs, document, fontRegular, margin, y, selectedTechs, pageWidth, lightBlueColor, accentColor);
        
        // Check remaining space for Specific Technology section
        if (y - 80 < 60) {
            // Not enough space, create a new page
            cs.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            cs = new PDPageContentStream(document, page);
            
            // Add blue header to new page
            cs.setNonStrokingColor(headerBgColor);
            cs.addRect(0, page.getMediaBox().getHeight() - 40, page.getMediaBox().getWidth(), 40);
            cs.fill();
            
            // Reset color and y position
            cs.setNonStrokingColor(textColor);
            y = page.getMediaBox().getHeight() - 60;
        } else {
            y -= 40;
        }
        
        // Specific Technology
        drawSectionHeader(cs, fontBold, "Specific Technology", margin, y, accentColor);
        y -= 25;
        addText(cs, fontRegular, 10, margin + 5, y, jTextField9.getText());
        
        // Footer
        float footerY = 40;
        cs.setNonStrokingColor(subtextColor);
        cs.beginText();
        cs.setFont(fontRegular, 8);
        cs.newLineAtOffset(page.getMediaBox().getWidth() / 2 - 100, footerY);
        cs.showText("This professional portfolio was generated using a custom template");
        cs.endText();

        cs.close();
        
        // Save the document
        document.save(pdfFilePath);
        document.close();
        document = null;
        
        // Show success message with options for email
        int choice = JOptionPane.showConfirmDialog(this, 
                "Portfolio PDF created successfully!\nWould you like to send it via email?", 
                "PDF Created", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        if (choice == JOptionPane.YES_OPTION) {
            // Use our improved email handling method
            String emailTo = getUserEmail();
            sendEmailWithAttachment(emailTo, pdfFilePath);
        } else {
            // Just show where the file was saved
            JOptionPane.showMessageDialog(this, 
                    "Portfolio PDF created successfully! File saved at:\n" + pdfFilePath, 
                    "PDF Created", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error creating PDF: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Clean up resources
        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }//GEN-LAST:event_generateActionPerformed
/**
 * Gets the user's email address through a dialog
 * @return The email address entered by the user
 */
private String getUserEmail() {
    String userEmail = null;
    boolean isLoggedIn = (sessions.userId > 0);
    
    if (isLoggedIn) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT email FROM user_accounts WHERE id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, sessions.userId);
                ResultSet rs = pst.executeQuery();
                
                if (rs.next() && rs.getString("email") != null && !rs.getString("email").isEmpty()) {
                    userEmail = rs.getString("email");
                }
                
                rs.close();
                pst.close();
                conn.close();
            }
        } catch (Exception e) {
            System.err.println("Error fetching email from database: " + e.getMessage());
        }
    }
    
    // Create option pane with choices
    String[] options;
    String message;
    
    if (userEmail != null && !userEmail.isEmpty()) {
        // User has an email in the database
        options = new String[]{"Send to my account email: " + userEmail, "Send to a different email", "Cancel"};
        message = "Where would you like to send the PDF?";
    } else {
        // No email found in database
        options = new String[]{"Enter email address", "Cancel"};
        message = "No email found for your account. Would you like to enter an email address?";
    }
    
    int choice = JOptionPane.showOptionDialog(
        this, 
        message,
        "Email Options",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]
    );
    
    // Handle user's choice
    if (userEmail != null && !userEmail.isEmpty()) {
        // User has email in database
        if (choice == 0) {
            // Use account email
            return userEmail;
        } else if (choice == 1) {
            // Ask for different email
            String newEmail = JOptionPane.showInputDialog(
                this,
                "Enter email address to send PDF to:",
                "Email Address",
                JOptionPane.QUESTION_MESSAGE
            );
            return newEmail; // Will be null if canceled
        }
    } else {
        // No email in database
        if (choice == 0) {
            // Ask for email
            String newEmail = JOptionPane.showInputDialog(
                this,
                "Enter email address to send PDF to:",
                "Email Address",
                JOptionPane.QUESTION_MESSAGE
            );
            return newEmail; // Will be null if canceled
        }
    }
    
    // User canceled
    return null;
}
/**
 * Sends the generated PDF as an email attachment
 * @param toEmail The recipient's email address
 * @param filePath The path to the PDF file
 */
private void sendEmailWithAttachment(String toEmail, String filePath) {
    if (toEmail == null || toEmail.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Email sending canceled. PDF saved at: " + filePath, 
            "Email Canceled", 
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    final String fromEmail = "sanom6268@gmail.com";
    final String password = "zhdcnfitaceblupq"; // App password, no spaces
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(fromEmail, password);
        }
    });
    
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail,"Portfolio PDF Generator"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Your Professional Portfolio PDF");
     
        String userName = "";
        if (sessions.userId > 0) {
            try {
                Connection conn = DBConnection.getConnection();
                if (conn != null) {
                    String sql = "SELECT username FROM user_accounts WHERE id = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1, sessions.userId);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        userName = rs.getString("username");
                    }
                    rs.close();
                    pst.close();
                    conn.close();
                }
            } catch (Exception e) {
                System.err.println("Error fetching username: " + e.getMessage());
            }
        }
        
        String greeting = userName.isEmpty() ? "Dear User," : "Dear " + userName + ",";
        
        String htmlContent = "<html><body style='font-family: Arial, sans-serif;'>"
                + "<div style='padding: 20px; background-color: #f8f9fa; border-radius: 5px;'>"
                + "<h2 style='color: #2563eb;'>Portfolio PDF Generator</h2>"
                + "<p>" + greeting + "</p>"
                + "<p>Thank you for using our Portfolio PDF Generator. Attached is your professionally formatted PDF file with the information you provided.</p>"
                + "<p>This document is ready to share with potential employers or clients.</p>"
                + "<p style='margin-top: 20px;'>Best regards,<br>Portfolio Generator Team</p>"
                + "</div>"
                + "</body></html>";
       
        // Add HTML content to message
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(htmlContent, "text/html");
        
        // Add PDF attachment
        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(filePath);
        
        // Combine parts into multipart message
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);
        message.setContent(multipart);
        
        // Send the email
        Transport.send(message);
        
        // Always store email history in database if user is logged in,
        // regardless of whether this is their account email or not
        if (sessions.userId > 0) {
            storeEmailHistoryInDatabase(toEmail, filePath);
        }
        
        JOptionPane.showMessageDialog(this, "Email sent successfully to " + toEmail);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Email send error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
    private void storeEmailHistoryInDatabase(String emailAddress, String filePath) {
    Connection conn = null;
    PreparedStatement pst = null;
    
    try {
        conn = DBConnection.getConnection();
        if (conn == null) {
            System.err.println("Database connection failed");
            return;
        }

        // Get filename from path
        String fileName = new java.io.File(filePath).getName();

        // Insert into email_history table
        String sql = "INSERT INTO email_history (user_id, email_address, file_name, sent_date) VALUES (?, ?, ?, NOW())";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, sessions.userId);
        pst.setString(2, emailAddress);
        pst.setString(3, fileName);
        
        int affected = pst.executeUpdate();
        if (affected > 0) {
            System.out.println("Email history saved to database successfully");
        } else {
            System.err.println("Failed to save email history: No rows affected");
        }
    } catch (Exception e) {
        System.err.println("Database error while saving email history: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Properly close resources
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.err.println("Error closing database resources: " + e.getMessage());
        }
    }
}
private void drawSectionHeader(PDPageContentStream cs, PDFont font, String text, float x, float y, Color accentColor) throws IOException {
    // Draw section title
    cs.beginText();
    cs.setFont(font, 14);
    cs.newLineAtOffset(x, y);
    cs.showText(text);
    cs.endText();
    
    // Draw accent line under section title
    float[] accentColorComponents = accentColor.getRGBColorComponents(null);
    cs.setStrokingColor(accentColorComponents[0], accentColorComponents[1], accentColorComponents[2]);
    cs.setLineWidth(1.5f);
    cs.moveTo(x, y - 5);
    cs.lineTo(x + 100, y - 5);
    cs.stroke();
    
    // Reset color to black
    cs.setStrokingColor(0, 0, 0);
}

private void drawContactItem(PDPageContentStream cs, PDFont font, String label, String value, float x, float y) throws IOException {
    cs.beginText();
    cs.setFont(font, 9);
    cs.newLineAtOffset(x, y);
    cs.showText(label + value);
    cs.endText();
}

private void drawEnhancedTable(PDPageContentStream cs, PDDocument document, PDFont headerFont, PDFont contentFont, 
                            float x, float y, JTable table, float tableWidth, Color accentColor, Color bgColor) throws IOException {
    if (table == null || table.getRowCount() == 0 || table.getColumnCount() == 0) {
        return;
    }
    
    int cols = table.getColumnCount();
    float colWidth = tableWidth / cols;
    float rowHeight = 45;
    float cellPadding = 5;
    
    // Table headers
    float[] accentColorComponents = accentColor.getRGBColorComponents(null);
    cs.setNonStrokingColor(accentColorComponents[0], accentColorComponents[1], accentColorComponents[2]);
    for (int i = 0; i < cols; i++) {
        cs.beginText();
        cs.setFont(headerFont, 10);
        cs.newLineAtOffset(x + (i * colWidth) + cellPadding, y);
        cs.showText(table.getColumnName(i));
        cs.endText();
    }
    
    y -= 15;
    
    // Table rows
    for (int row = 0; row < table.getRowCount(); row++) {
        float currentY = y - (row * rowHeight);
        
        // Draw card background
        float[] bgColorComponents = bgColor.getRGBColorComponents(null);
        cs.setNonStrokingColor(bgColorComponents[0], bgColorComponents[1], bgColorComponents[2]);
        cs.addRect(x, currentY - rowHeight + 5, tableWidth, rowHeight - 8);
        cs.fill();
        
        cs.setNonStrokingColor(0, 0, 0); // Reset to black
        
        // Draw cell content
        for (int col = 0; col < cols; col++) {
            Object value = table.getValueAt(row, col);
            String text = value != null ? value.toString() : "";
            
            cs.beginText();
            cs.setFont(contentFont, 9);
            cs.newLineAtOffset(x + (col * colWidth) + cellPadding, currentY - cellPadding - 10);
            cs.showText(text);
            cs.endText();
        }
    }
}

private void drawTechTags(PDPageContentStream cs, PDDocument document, PDFont font, float x, float y, 
                        List<String> technologies, float maxWidth, Color bgColor, Color textColor) throws IOException {
    if (technologies.isEmpty()) {
        addText(cs, font, 10, x, y, "None");
        return;
    }
    
    float tagPadding = 6;
    float tagHeight = 20;
    float tagSpacing = 8;
    float currentX = x;
    float currentY = y;
    float lineHeight = tagHeight + tagSpacing;
    
    for (String tech : technologies) {
        float tagWidth = getTextWidth(font, 9, tech) + (2 * tagPadding);
        
        // Move to next line if tag doesn't fit
        if (currentX + tagWidth > x + maxWidth) {
            currentX = x;
            currentY -= lineHeight;
        }
        
        // Draw tag background
        float[] bgColorComponents = bgColor.getRGBColorComponents(null);
        cs.setNonStrokingColor(bgColorComponents[0], bgColorComponents[1], bgColorComponents[2]);
        cs.addRect(currentX, currentY - tagHeight + 5, tagWidth, tagHeight);
        cs.fill();
        
        // Draw tag text
        float[] textColorComponents = textColor.getRGBColorComponents(null);
        cs.setNonStrokingColor(textColorComponents[0], textColorComponents[1], textColorComponents[2]);
        cs.beginText();
        cs.setFont(font, 9);
        cs.newLineAtOffset(currentX + tagPadding, currentY - tagHeight + 10);
        cs.showText(tech);
        cs.endText();
        
        currentX += tagWidth + tagSpacing;
    }
}

// You'll need to add these helper methods which I'm assuming are similar to what you already have

private void addText(PDPageContentStream cs, PDFont font, float fontSize, float x, float y, String text) throws IOException {
    cs.beginText();
    cs.setFont(font, fontSize);
    cs.newLineAtOffset(x, y);
    cs.showText(text);
    cs.endText();
}


private void addWrappedText(PDPageContentStream cs, PDFont font, float fontSize,
                            float x, float y, String text, int maxLinesPerPage,
                            PDPage page, float margin) throws IOException {
    if (text == null || text.isEmpty()) {
        return;
    }

    float leading = 1.5f * fontSize;
    float width = page.getMediaBox().getWidth() - 2 * margin;

    // Split the text into words
    String[] words = text.split(" ");
    StringBuilder line = new StringBuilder();
    float lineWidth = 0;
    int lineCount = 0;

    for (String word : words) {
        float wordWidth = font.getStringWidth(word) / 1000 * fontSize;
        float spaceWidth = font.getStringWidth(" ") / 1000 * fontSize;

        if (lineWidth + wordWidth > width) {
            // Draw the current line
            cs.beginText();
            cs.setFont(font, fontSize);
            cs.newLineAtOffset(x, y - (lineCount * leading));
            cs.showText(line.toString().trim());
            cs.endText();

            // Reset for the next line
            line = new StringBuilder(word + " ");
            lineWidth = wordWidth + spaceWidth;
            lineCount++;

            if (lineCount >= maxLinesPerPage) {
                break; // Stop if max lines are reached
            }
        } else {
            line.append(word).append(" ");
            lineWidth += wordWidth + spaceWidth;
        }
    }

    // Draw the last line if any
    if (line.length() > 0 && lineCount < maxLinesPerPage) {
        cs.beginText();
        cs.setFont(font, fontSize);
        cs.newLineAtOffset(x, y - (lineCount * leading));
        cs.showText(line.toString().trim());
        cs.endText();
    }
}
   
   

// Helper method to calculate text width (needed for tag sizing)
private float getTextWidth(PDFont font, float fontSize, String text) throws IOException {
    return font.getStringWidth(text) / 1000 * fontSize;
}
    private void oneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneActionPerformed
        
    }//GEN-LAST:event_oneActionPerformed

    private void one1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one1ActionPerformed

    private void one3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one3ActionPerformed

    private void one4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one4ActionPerformed

    private void one5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one5ActionPerformed

    private void one6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one6ActionPerformed

    private void one2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one2ActionPerformed

    private void one7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one7ActionPerformed

    private void one10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one10ActionPerformed

    private void one13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one13ActionPerformed

    private void one16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one16ActionPerformed

    private void one17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_one17ActionPerformed

    private void button16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button16ActionPerformed
   JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Choose an image");

FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
fileChooser.setFileFilter(filter);

if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    File selectedFile = fileChooser.getSelectedFile();

    try {
        BufferedImage newImage = ImageIO.read(selectedFile); // Might throw IOException
        pictureBox1.setImage(newImage); // Custom component method
        pictureBox1.repaint();
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to read image: " + ex.getMessage());
    }
}

    }//GEN-LAST:event_button16ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Generator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public content.button1 button15;
    public content.button1 button16;
    public content.button1 button17;
    public content.button1 button18;
    public content.button1 button19;
    public content.button1 button20;
    public content.button1 button21;
    public content.button1 button22;
    public content.button1 button23;
    public content.button1 button26;
    public content.button1 generate;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    public javax.swing.JTextField jTextField9;
    public content.ToggleSkillButton one;
    public content.ToggleSkillButton one1;
    public content.ToggleSkillButton one10;
    public content.ToggleSkillButton one13;
    public content.ToggleSkillButton one16;
    public content.ToggleSkillButton one17;
    public content.ToggleSkillButton one2;
    public content.ToggleSkillButton one3;
    public content.ToggleSkillButton one4;
    public content.ToggleSkillButton one5;
    public content.ToggleSkillButton one6;
    public content.ToggleSkillButton one7;
    public Main.PictureBox pictureBox1;
    public content.RoundedPanel1 roundedPanel11;
    public content.RoundedPanel1 roundedPanel12;
    public con.SkillDropDown skillDropDown1;
    // End of variables declaration//GEN-END:variables
}
