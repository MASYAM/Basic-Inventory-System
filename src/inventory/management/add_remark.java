/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author MA
 */
public class add_remark extends javax.swing.JFrame {

    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    IMS_form imsForm;
    
    public add_remark(IMS_form i) {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("login.png")));
        imsForm = i;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addRemark_textField = new javax.swing.JTextField();
        insert_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        result_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 200));
        setMinimumSize(new java.awt.Dimension(400, 200));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ADD REMARK");

        insert_button.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insert_button.setText("Insert");
        insert_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        insert_button.setFocusable(false);
        insert_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insert_buttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Result :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(result_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addRemark_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(insert_button, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(insert_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addRemark_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(result_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void insert_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_buttonActionPerformed
        // TODO add your handling code here:

        String sqlCheck = "SELECT * FROM product_remark WHERE remark='"+StringEscapeUtils.escapeSql(addRemark_textField.getText())+"' ";

        String sql = "INSERT INTO product_remark (date,time,remark,user) ";
        sql += "VALUES (";
        sql += "'"+imsForm.date()+"','"+imsForm.time()+"','"+StringEscapeUtils.escapeSql(addRemark_textField.getText())+"','"+ConnectDB.username+"')";

        try{

            pstmt = imsForm.conn.prepareStatement(sqlCheck);
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                addRemark_textField.requestFocusInWindow();
                result_label.setText( "Remark "+addRemark_textField.getText()+" already exists");
                result_label.setForeground(Color.RED);

            }else if(addRemark_textField.getText().trim().isEmpty())
            {
                addRemark_textField.requestFocusInWindow();
                result_label.setText( "Remark cannot be empty");
                result_label.setForeground(Color.RED);
            }else if(addRemark_textField.getText().length() > 50 || addRemark_textField.getText().length() > 50 )
            {
                addRemark_textField.requestFocusInWindow();
                result_label.setText("Fields can't exceed character limit 50");
                result_label.setForeground(Color.RED);
            }else{

                pstmt = imsForm.conn.prepareStatement(sql);
                int rows = pstmt.executeUpdate();

                if(rows > 0)
                {
                    result_label.setText("Remark "+addRemark_textField.getText()+" inserted succesfully");
                    result_label.setForeground(new Color(0,102,0));
                    addRemark_textField.setText("");
                    addRemark_textField.requestFocusInWindow();
                    
                    sql = "SELECT remark FROM product_remark ORDER BY remark ASC";
                    pstmt = imsForm.conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();

                    imsForm.entry_remark_comboBox.setModel(new DefaultComboBoxModel());
                    imsForm.entry_remark_comboBox.addItem("Remark");
                    while(rs.next())
                    {
                        imsForm.entry_remark_comboBox.addItem(rs.getString("remark"));
                    }
                }
            }

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_insert_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(add_remark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_remark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_remark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_remark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new add_remark().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addRemark_textField;
    private javax.swing.JButton insert_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel result_label;
    // End of variables declaration//GEN-END:variables
}