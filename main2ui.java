/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MAIN;

import static MAIN.main2ui.main;
import content.*;
import java.awt.Color;



public class main2ui extends javax.swing.JFrame {

     HomePage a = new HomePage();
     Resources b = new  Resources();
     Template c = new Template();
     Settings d = new Settings();
     
    public main2ui() {
        initComponents();
            setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            main.add(a);
            main.add(b);
            main.add(c);
            main.add(d);
            a.setVisible(false);
            b.setVisible(false);
            c.setVisible(false);
            d.setVisible(false);
            button11.doClick();
        

        
      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        button11 = new content.button1();
        button12 = new content.button1();
        button13 = new content.button1();
        button14 = new content.button1();
        imagePanel3 = new MAIN.ImagePanel();
        main = new javax.swing.JLayeredPane();

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(190, 612));

        button11.setBackground(new java.awt.Color(51, 153, 255));
        button11.setText("  Home");
        button11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });

        button12.setBackground(new java.awt.Color(51, 153, 255));
        button12.setText("      Resources");
        button12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });

        button13.setBackground(new java.awt.Color(51, 153, 255));
        button13.setText("   Template");
        button13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });

        button14.setBackground(new java.awt.Color(51, 153, 255));
        button14.setText("Settings");
        button14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button14ActionPerformed(evt);
            }
        });

        imagePanel3.setBackground(new java.awt.Color(51, 153, 255));
        imagePanel3.setBorder(null);
        imagePanel3.setImage(new java.io.File("C:\\Users\\janna\\OneDrive\\Documents\\images\\icon\\blocks (2).png"));

        javax.swing.GroupLayout imagePanel3Layout = new javax.swing.GroupLayout(imagePanel3);
        imagePanel3.setLayout(imagePanel3Layout);
        imagePanel3Layout.setHorizontalGroup(
            imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );
        imagePanel3Layout.setVerticalGroup(
            imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(imagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(imagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_START);

        main.setLayout(new javax.swing.BoxLayout(main, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(main, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private final Color originalButtonBackground = new Color(51, 153, 255);
    private final Color originalButtonTextColor = new Color(255, 255, 255);

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
         a.setVisible(true);
         b.setVisible(false);
         c.setVisible(false);
         d.setVisible(false);
        button11.setForeground(new java.awt.Color(0, 0, 0));
        button11.setBackground(Color.WHITE);
        button12.setBackground(originalButtonBackground);   
        button12.setForeground(originalButtonTextColor);    
        button13.setBackground(originalButtonBackground);   
        button13.setForeground(originalButtonTextColor);
        button14.setBackground(originalButtonBackground);   
        button14.setForeground(originalButtonTextColor);
        
    }//GEN-LAST:event_button11ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
       b.setVisible(true);
        a.setVisible(false);
        c.setVisible(false);
         d.setVisible(false);
        button11.setBackground(originalButtonBackground);   
        button11.setForeground(originalButtonTextColor);    
        button14.setBackground(originalButtonBackground);   
        button14.setForeground(originalButtonTextColor);
        button12.setForeground(new java.awt.Color(0, 0, 0));
        button12.setBackground(Color.WHITE);
        button13.setBackground(originalButtonBackground);   
        button13.setForeground(originalButtonTextColor);    
    }//GEN-LAST:event_button12ActionPerformed

    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button13ActionPerformed
        a.setVisible(false);
            b.setVisible(false);
            c.setVisible(true); 
             d.setVisible(false);
            
            button11.setBackground(originalButtonBackground);   
            button11.setForeground(originalButtonTextColor);   

            button12.setBackground(originalButtonBackground);   
            button12.setForeground(originalButtonTextColor);    
            
          button14.setBackground(originalButtonBackground);   
        button14.setForeground(originalButtonTextColor);
         button13.setForeground(new java.awt.Color(0, 0, 0));
         button13.setBackground(Color.WHITE);
         
    }//GEN-LAST:event_button13ActionPerformed

    private void button14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button14ActionPerformed
           a.setVisible(false);
            b.setVisible(false);
            c.setVisible(false); 
             d.setVisible(true);
        
        button14.setForeground(new java.awt.Color(0, 0, 0));
         button14.setBackground(Color.WHITE);
          button13.setBackground(originalButtonBackground); 
         button13.setForeground(originalButtonTextColor);
          button11.setBackground(originalButtonBackground);   
            button11.setForeground(originalButtonTextColor);   

            button12.setBackground(originalButtonBackground);   
            button12.setForeground(originalButtonTextColor);   
    }//GEN-LAST:event_button14ActionPerformed

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
            java.util.logging.Logger.getLogger(main2ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main2ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main2ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main2ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main2ui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private content.button1 button11;
    private content.button1 button12;
    private content.button1 button13;
    private content.button1 button14;
    private MAIN.ImagePanel imagePanel2;
    private MAIN.ImagePanel imagePanel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLayeredPane main;
    // End of variables declaration//GEN-END:variables
}
