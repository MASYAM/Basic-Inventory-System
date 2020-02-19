/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management;

import org.apache.commons.lang.StringEscapeUtils;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


/**
 *
 * @author MA
 */
public class add_model extends javax.swing.JFrame {

    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    IMS_form imsForm;
    
    public add_model(IMS_form i) {
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

        addModel_textField = new javax.swing.JTextField();
        insert_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        result_label = new javax.swing.JLabel();
        countable_raiobutton = new javax.swing.JRadioButton();
        uncountable_raiobutton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 200));
        setMinimumSize(new java.awt.Dimension(400, 200));
        setResizable(false);

        insert_button.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insert_button.setText("Insert");
        insert_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        insert_button.setFocusable(false);
        insert_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insert_buttonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ADD MODEL");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Result :");

        countable_raiobutton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        countable_raiobutton.setSelected(true);
        countable_raiobutton.setText("Countable");
        countable_raiobutton.setFocusable(false);
        countable_raiobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countable_raiobuttonActionPerformed(evt);
            }
        });

        uncountable_raiobutton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        uncountable_raiobutton.setText("Uncountable");
        uncountable_raiobutton.setFocusable(false);
        uncountable_raiobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uncountable_raiobuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(countable_raiobutton)
                        .addGap(18, 18, 18)
                        .addComponent(uncountable_raiobutton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(result_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addModel_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(insert_button, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countable_raiobutton)
                    .addComponent(uncountable_raiobutton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(insert_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addModel_textField, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(result_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void insert_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_buttonActionPerformed
        // TODO add your handling code here:
        String type;
        if(countable_raiobutton.isSelected()){ type = "countable"; }else{ type = "uncountable"; }
         
    int response = JOptionPane.showConfirmDialog(null, "Are you sure, "+type+" ?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(response == JOptionPane.YES_OPTION) {
            
             
            String sqlCheck = "SELECT * FROM product_model WHERE model='"+StringEscapeUtils.escapeSql(addModel_textField.getText())+"' ";
            
            String sql = "INSERT INTO product_model (date,time,insert_year,model,type,name,description,price,remark,quantity,total_in,total_out,partially_out,user) ";
                   sql += "VALUES (";
                   sql += "'"+imsForm.date()+"','"+imsForm.time()+"','"+imsForm.year()+"','"+StringEscapeUtils.escapeSql(addModel_textField.getText())+"','"+type+"','','',0,'',0,0,0,0,'"+ConnectDB.username+"')";
                                      
            try{
                
                pstmt = imsForm.conn.prepareStatement(sqlCheck);
                rs = pstmt.executeQuery();
                
                if(rs.next())
                {
                    addModel_textField.requestFocusInWindow();
                    result_label.setText( "Model "+addModel_textField.getText()+" already exists");
                    result_label.setForeground(Color.RED);
                    
                }else if(addModel_textField.getText().trim().isEmpty())
                {
                    addModel_textField.requestFocusInWindow();
                    result_label.setText( "Model cannot be empty");
                    result_label.setForeground(Color.RED);
                }else if(addModel_textField.getText().length() > 50 || addModel_textField.getText().length() > 50 )
                {
                    addModel_textField.requestFocusInWindow();
                    result_label.setText("Fields can't exceed character limit 50");
                    result_label.setForeground(Color.RED);
                }else{
                
                  pstmt = imsForm.conn.prepareStatement(sql);
                  int rows = pstmt.executeUpdate();
                  
                  if(rows > 0)
                  {
                        result_label.setText("Model "+addModel_textField.getText()+" inserted succesfully");
                        result_label.setForeground(new Color(0,102,0));
                        addModel_textField.setText("");                       
                        addModel_textField.requestFocusInWindow();
                        
                        sql = "SELECT model FROM product_model ORDER BY model ASC";
                        pstmt = imsForm.conn.prepareStatement(sql);                      
                        rs = pstmt.executeQuery();
                        
                        imsForm.stat_product_model_comboBox.setModel(new DefaultComboBoxModel());
                        imsForm.status_model_comboBox.setModel(new DefaultComboBoxModel());
                        imsForm.entry_model_comboBox.setModel(new DefaultComboBoxModel());
                        imsForm.review_model_comboBox.setModel(new DefaultComboBoxModel());
                        imsForm.barcode_model_comboBox.setModel(new DefaultComboBoxModel());
                        
                        imsForm.stat_product_model_comboBox.addItem("Model");
                        imsForm.status_model_comboBox.addItem("Model");
                        imsForm.entry_model_comboBox.addItem("Model");
                        imsForm.review_model_comboBox.addItem("All");
                        imsForm.barcode_model_comboBox.addItem("Model");
                        
                        while(rs.next())
                        {
                             imsForm.stat_product_model_comboBox.addItem(rs.getString("model"));
                             imsForm.status_model_comboBox.addItem(rs.getString("model"));
                             imsForm.entry_model_comboBox.addItem(rs.getString("model"));
                             imsForm.review_model_comboBox.addItem(rs.getString("model"));
                             imsForm.barcode_model_comboBox.addItem(rs.getString("model"));
                        }
                  }
                }
                  
            }catch(Exception e)
            {
                  JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_insert_buttonActionPerformed

    private void countable_raiobuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countable_raiobuttonActionPerformed
        // TODO add your handling code here:
        if(uncountable_raiobutton.isSelected())
        {
           uncountable_raiobutton.setSelected(false);
           countable_raiobutton.setSelected(true);
        }else{
           uncountable_raiobutton.setSelected(true);
           countable_raiobutton.setSelected(false);
        }
    }//GEN-LAST:event_countable_raiobuttonActionPerformed

    private void uncountable_raiobuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uncountable_raiobuttonActionPerformed
        // TODO add your handling code here:
        if(countable_raiobutton.isSelected())
        {
           uncountable_raiobutton.setSelected(true);
           countable_raiobutton.setSelected(false);
        }else{
           uncountable_raiobutton.setSelected(false);
           countable_raiobutton.setSelected(true);
        }
    }//GEN-LAST:event_uncountable_raiobuttonActionPerformed

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
            java.util.logging.Logger.getLogger(add_model.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_model.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_model.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_model.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */ 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new add_model().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addModel_textField;
    private javax.swing.JRadioButton countable_raiobutton;
    private javax.swing.JButton insert_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel result_label;
    private javax.swing.JRadioButton uncountable_raiobutton;
    // End of variables declaration//GEN-END:variables
}