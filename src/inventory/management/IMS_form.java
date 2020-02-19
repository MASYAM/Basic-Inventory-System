/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author MA
 */
public class IMS_form extends javax.swing.JFrame {

    
    DefaultTableModel statTableModel,entryProductIdTableModel;
    int statTable_row = 0;
    int limitStartForModelCombobox= 20;
    int limitStartForStatusBox= 20;
    int limitStartForDeliveredNameBox= 20;
    int isTableCreated = 0;
    int itemCount = 0;
    String total_product=null,total_price=null;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     SimpleDateFormat sdf = new SimpleDateFormat("y");
     SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM");
     SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
     SimpleDateFormat sdf4 = new SimpleDateFormat("hh:mm:ss a");
        
     
     int stat_search_choice;
     
     String stat_sql = null,stat_sql2 = null;
     
     //print
     String sql_status_single_report = null;
     int sql_status_multi_report_permission = 0;
     String date_status_multi = null,time_status_multi = null;
     
     //add model,remark jframe
     public JFrame addModelFrame = new add_model(this);
     public JFrame addRemarkFrame = new add_remark(this);
     
     //works while scroll down then load another amount of rows
    AdjustmentListener hListener = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                // System.out.println("Vertical: ");
                //dumpInfo(e);
             int extent = stat_table_scrollpane.getVerticalScrollBar().getModel().getExtent();
             if(stat_table_scrollpane.getVerticalScrollBar().getValue()+extent == stat_table_scrollpane.getVerticalScrollBar().getMaximum() )
             {
                 choiceMethod(stat_search_choice);
             }                        

        }
    };
    
    
    
    SimpleDateFormat formatter;
    
    
    public String year()
    {
        return sdf.format(new Date()); 
    }
    
    public String month()
    {
        return sdf2.format(new Date());
    }
    
    public String date()
    {
       return sdf3.format(new Date());
    }
    
    public String time()
    {
      return sdf4.format(new Date());
    }
    
    /**
     * Creates new form IMS_form
     */
    public IMS_form() {
        
        initComponents();    
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("login.png")));
        
        conn = ConnectDB.connection();
        
        username_label.setText(ConnectDB.username);
        
        home_button.setEnabled(false);
        //calling listener
        stat_table_scrollpane.getVerticalScrollBar().addAdjustmentListener(hListener);
        
        stat_fromDate3.setFormats(new String[] {"yyyy-MM-dd"});
        stat_toDate4.setFormats(new String[] {"yyyy-MM-dd"});
        stat_dateToDelivered.setFormats(new String[] {"yyyy-MM-dd"});
        
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        //set underline
         String htmlFormat = "<html><u>Number of products</u></html>"; 
         jLabel59.setText(htmlFormat);
         htmlFormat = "<html><u>Transaction</u></html>"; 
         jLabel60.setText(htmlFormat);
        
        try{
            //Create year in every year
             String sql = "SELECT year FROM year";
             pstmt = conn.prepareStatement(sql);                      
             rs = pstmt.executeQuery();
             
             while(rs.next())
             {
                  if(!rs.getString("year").equals(sdf.format(new Date())) )
                  {
                     //sql = "INSERT INTO year (year) VALUES ('"+sdf.format(new Date())+"')";
                     //pstmt = conn.prepareStatement(sql);
                    // pstmt.executeUpdate();
                     break;
                  }
             }
            //set model & combox 
             sql = "SELECT model FROM product_model ORDER BY model ASC";
             pstmt = conn.prepareStatement(sql);                      
             rs = pstmt.executeQuery();
             while(rs.next())
             {
                  review_model_comboBox.addItem(rs.getString("model"));
                  stat_product_model_comboBox.addItem(rs.getString("model"));
                  status_model_comboBox.addItem(rs.getString("model"));
                  entry_model_comboBox.addItem(rs.getString("model"));
                  barcode_model_comboBox.addItem(rs.getString("model"));
             }
             
             sql = "SELECT remark FROM product_remark ORDER BY remark ASC";
             pstmt = conn.prepareStatement(sql);                      
             rs = pstmt.executeQuery();
             while(rs.next())
             {
                  entry_remark_comboBox.addItem(rs.getString("remark"));
             }
             
             sql = "SELECT year FROM year ORDER BY year DESC";
             pstmt = conn.prepareStatement(sql);                      
             rs = pstmt.executeQuery();
        
             while(rs.next())
             {
                  stat_year_comboBox.addItem(rs.getString("year"));
                  status_year_comboBox.addItem(rs.getString("year"));
             }
             
             //call summary of today
             summaryOfToday();
             
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        entry_button = new javax.swing.JButton();
        status_button = new javax.swing.JButton();
        statistics_button = new javax.swing.JButton();
        home_button = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        username_label = new javax.swing.JLabel();
        barcode_button = new javax.swing.JButton();
        parent_panel = new javax.swing.JPanel();
        home_panel = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        review_model_comboBox = new javax.swing.JComboBox();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        review_numOfProductArrival_label = new javax.swing.JLabel();
        review_numOfProductIn_label = new javax.swing.JLabel();
        review_numOfProductOut_label = new javax.swing.JLabel();
        review_arrivalTransaction_label = new javax.swing.JLabel();
        review_inTransaction_label = new javax.swing.JLabel();
        review_outTransaction_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        entry_panel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        entry_submit_button = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        entry_model_comboBox = new javax.swing.JComboBox();
        entry_remark_comboBox = new javax.swing.JComboBox();
        entry_product_name = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        entry_product_price = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        entry_numOfProduct_jtextField = new javax.swing.JTextField();
        entry_createField_button = new javax.swing.JButton();
        entry_price_validate_message = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        entry_description_textArea = new javax.swing.JTextArea();
        jLabel62 = new javax.swing.JLabel();
        entry_productType_label = new javax.swing.JLabel();
        entry_modelHidden_label = new javax.swing.JLabel();
        entry_producIdTextField_jScrollPane = new javax.swing.JScrollPane();
        entry_productId_jtable = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        entry_result_message = new javax.swing.JLabel();
        status_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        status_out = new javax.swing.JLabel();
        date_out = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        time_out = new javax.swing.JLabel();
        checkbox_product_out = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        status_in = new javax.swing.JLabel();
        date_in = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        time_in = new javax.swing.JLabel();
        checkbox_product_in = new javax.swing.JCheckBox();
        result_message = new javax.swing.JLabel();
        search_box = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        result_message2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        status_arrive = new javax.swing.JLabel();
        date_arrive = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        time_arrive = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        product_model = new javax.swing.JLabel();
        product_name = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        product_price = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        product_id = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        product_current_status = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        status_singleDescription_textarea = new javax.swing.JTextArea();
        jLabel68 = new javax.swing.JLabel();
        status_singleType_label = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        status_singleSize_label = new javax.swing.JLabel();
        status_report_button = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        status_single_maxOut_label = new javax.swing.JLabel();
        status_single_input_textField = new javax.swing.JTextField();
        status_singleRecipientName_textField = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        status_singleRecipientInfo_label = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        status_singleRecipient_textArea = new javax.swing.JTextArea();
        status_singleRecipientCard_textField = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        status_model_comboBox = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        status_multi_input_textField = new javax.swing.JTextField();
        status_multi_inputSubmit_button = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        status_multi_product_model_label = new javax.swing.JLabel();
        status_multi_product_name_label = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        status_multi_product_price_label = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        status_multi_product_quantity_label = new javax.swing.JLabel();
        status_multi_product_in_label = new javax.swing.JLabel();
        status_multi_product_out_label = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        status_multi_year_label = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        status_multi_productType_label = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        status_multiDescription_textarea = new javax.swing.JTextArea();
        jLabel72 = new javax.swing.JLabel();
        status_multi_product_partiallyOut_label = new javax.swing.JLabel();
        status_multi_productIn_checkBox = new javax.swing.JCheckBox();
        status_multi_productOut_checkBox = new javax.swing.JCheckBox();
        status_report_button2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        status_multi_searchResult_label = new javax.swing.JLabel();
        status_multi_validate_label = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        status_multi_maxOut_label = new javax.swing.JLabel();
        status_multi_finalResult_label = new javax.swing.JLabel();
        status_year_comboBox = new javax.swing.JComboBox();
        jPanel17 = new javax.swing.JPanel();
        status_single_maxOut_label1 = new javax.swing.JLabel();
        status_multiRecipientName_textField = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        status_singleRecipientInfo_label1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        status_multiRecipient_textArea = new javax.swing.JTextArea();
        status_multiRecipientCard_textField = new javax.swing.JTextField();
        statistics_panel = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        stat_product_model_comboBox = new javax.swing.JComboBox();
        stat_year_comboBox = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        stat_model_orderByComboBox = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        stat_toDate4 = new org.jdesktop.swingx.JXDatePicker();
        jLabel50 = new javax.swing.JLabel();
        stat_fromDate3 = new org.jdesktop.swingx.JXDatePicker();
        jLabel52 = new javax.swing.JLabel();
        stat_status_comboBox = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        stat_status_orderByComboBox = new javax.swing.JComboBox();
        jLabel51 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        stat_table_scrollpane = new javax.swing.JScrollPane();
        stat_table = new javax.swing.JTable();
        stat_itemShows_message = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        stat_label_searchMode = new javax.swing.JLabel();
        stat_label_orderBy = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        stat_label_totalProduct = new javax.swing.JLabel();
        stat_label_totalPrice = new javax.swing.JLabel();
        stat_report_button = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        stat_dateToDelivered = new org.jdesktop.swingx.JXDatePicker();
        stat_deliveredName_comboBox = new javax.swing.JComboBox();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        stat_deliveredTo_orderByComboBox = new javax.swing.JComboBox();
        barcode_panel = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        barcode_model_comboBox = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        addModel_jMenuItem = new javax.swing.JMenuItem();
        addRemark_jMenuitem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" IMS");
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(207, 207, 207));

        entry_button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        entry_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/data-entry-icon.png"))); // NOI18N
        entry_button.setText("Entry");
        entry_button.setBorderPainted(false);
        entry_button.setContentAreaFilled(false);
        entry_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        entry_button.setFocusPainted(false);
        entry_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entry_buttonActionPerformed(evt);
            }
        });

        status_button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        status_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/inOut.png"))); // NOI18N
        status_button.setText("In/Out");
        status_button.setBorderPainted(false);
        status_button.setContentAreaFilled(false);
        status_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        status_button.setFocusPainted(false);
        status_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_buttonActionPerformed(evt);
            }
        });

        statistics_button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        statistics_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/statistics2.png"))); // NOI18N
        statistics_button.setText("Statistics");
        statistics_button.setBorderPainted(false);
        statistics_button.setContentAreaFilled(false);
        statistics_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        statistics_button.setFocusPainted(false);
        statistics_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statistics_buttonActionPerformed(evt);
            }
        });

        home_button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        home_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/review.png"))); // NOI18N
        home_button.setText("Review");
        home_button.setBorderPainted(false);
        home_button.setContentAreaFilled(false);
        home_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home_button.setFocusPainted(false);
        home_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_buttonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("USERNAME");

        username_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        username_label.setForeground(new java.awt.Color(0, 102, 153));

        barcode_button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        barcode_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/management/barcode.png"))); // NOI18N
        barcode_button.setText("Barcode");
        barcode_button.setBorderPainted(false);
        barcode_button.setContentAreaFilled(false);
        barcode_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        barcode_button.setFocusPainted(false);
        barcode_button.setFocusable(false);
        barcode_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcode_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(home_button, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entry_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(status_button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statistics_button, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barcode_button)
                        .addGap(0, 621, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username_label, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entry_button)
                    .addComponent(status_button)
                    .addComponent(statistics_button)
                    .addComponent(home_button)
                    .addComponent(barcode_button))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(username_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        parent_panel.setBackground(new java.awt.Color(234, 234, 234));
        parent_panel.setLayout(new java.awt.CardLayout());

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Summary of Today");

        jPanel10.setBackground(new java.awt.Color(250, 250, 250));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setToolTipText("");

        review_model_comboBox.setMaximumRowCount(25);
        review_model_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        review_model_comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                review_model_comboBoxActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Model");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Product on arrival");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Product in");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Product out/delivery");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel59.setText("Number of products");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel60.setText("Transaction");

        review_numOfProductArrival_label.setForeground(new java.awt.Color(1, 55, 0));

        review_numOfProductIn_label.setForeground(new java.awt.Color(1, 55, 0));

        review_numOfProductOut_label.setForeground(new java.awt.Color(1, 55, 0));

        review_arrivalTransaction_label.setForeground(new java.awt.Color(1, 55, 0));

        review_inTransaction_label.setForeground(new java.awt.Color(1, 55, 0));

        review_outTransaction_label.setForeground(new java.awt.Color(1, 55, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(review_model_comboBox, 0, 191, Short.MAX_VALUE)
                        .addComponent(review_numOfProductArrival_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(review_numOfProductIn_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(review_numOfProductOut_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(review_arrivalTransaction_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(review_inTransaction_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(review_outTransaction_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(review_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60))
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(review_numOfProductArrival_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(review_arrivalTransaction_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(review_numOfProductIn_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(review_inTransaction_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(review_numOfProductOut_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(review_outTransaction_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Refresh");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout home_panelLayout = new javax.swing.GroupLayout(home_panel);
        home_panel.setLayout(home_panelLayout);
        home_panelLayout.setHorizontalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(home_panelLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(373, 373, 373)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(681, Short.MAX_VALUE))
        );
        home_panelLayout.setVerticalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        parent_panel.add(home_panel, "card5");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        entry_submit_button.setBackground(new java.awt.Color(215, 215, 215));
        entry_submit_button.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        entry_submit_button.setText("Insert");
        entry_submit_button.setBorderPainted(false);
        entry_submit_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        entry_submit_button.setEnabled(false);
        entry_submit_button.setFocusable(false);
        entry_submit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entry_submit_buttonActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 51));
        jLabel21.setText("Product Entry");

        jPanel6.setBackground(new java.awt.Color(245, 245, 245));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        entry_model_comboBox.setMaximumRowCount(25);
        entry_model_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Model" }));
        entry_model_comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entry_model_comboBoxActionPerformed(evt);
            }
        });

        entry_remark_comboBox.setMaximumRowCount(20);
        entry_remark_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Remark" }));

        entry_product_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                entry_product_nameKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Price           ");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Number of Product");

        entry_product_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entry_product_priceKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Name          ");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Model");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Remark");

        entry_numOfProduct_jtextField.setEnabled(false);
        entry_numOfProduct_jtextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entry_numOfProduct_jtextFieldKeyReleased(evt);
            }
        });

        entry_createField_button.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        entry_createField_button.setText("Create Field >>");
        entry_createField_button.setEnabled(false);
        entry_createField_button.setFocusable(false);
        entry_createField_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entry_createField_buttonActionPerformed(evt);
            }
        });

        entry_price_validate_message.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Description");

        entry_description_textArea.setColumns(20);
        entry_description_textArea.setRows(5);
        jScrollPane1.setViewportView(entry_description_textArea);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Type");

        entry_productType_label.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        entry_modelHidden_label.setForeground(new java.awt.Color(245, 245, 245));
        entry_modelHidden_label.setText("12");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(entry_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entry_remark_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(entry_modelHidden_label, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entry_numOfProduct_jtextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entry_createField_button)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel62)
                                .addGap(56, 56, 56)
                                .addComponent(entry_productType_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(128, 128, 128))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(entry_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(entry_product_price)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(entry_price_validate_message, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                        .addGap(15, 15, 15)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(entry_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entry_modelHidden_label))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(entry_remark_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(entry_productType_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(entry_numOfProduct_jtextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entry_createField_button, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(entry_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(entry_product_price, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(entry_price_validate_message, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        entry_producIdTextField_jScrollPane.setBackground(new java.awt.Color(245, 245, 245));
        entry_producIdTextField_jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        entry_producIdTextField_jScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        entry_productId_jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        entry_productId_jtable.setRowHeight(25);
        entry_producIdTextField_jScrollPane.setViewportView(entry_productId_jtable);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(entry_submit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(entry_producIdTextField_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel21)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(entry_producIdTextField_jScrollPane))
                .addGap(18, 18, 18)
                .addComponent(entry_submit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Result  :");

        entry_result_message.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        entry_result_message.setForeground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout entry_panelLayout = new javax.swing.GroupLayout(entry_panel);
        entry_panel.setLayout(entry_panelLayout);
        entry_panelLayout.setHorizontalGroup(
            entry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entry_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entry_result_message, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        entry_panelLayout.setVerticalGroup(
            entry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entry_panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, entry_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(entry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(entry_result_message, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(45, 45, 45))
        );

        parent_panel.add(entry_panel, "card4");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Product in");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Change Status Result  :");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel36.setText("  Date");
        jLabel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel37.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel37.setText("  Is Out");
        jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        status_out.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        status_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status_out.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        date_out.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        date_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date_out.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel39.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel39.setText("  Change Status");
        jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel40.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel40.setText("  Time");
        jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        time_out.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        time_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time_out.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        checkbox_product_out.setBackground(new java.awt.Color(255, 255, 255));
        checkbox_product_out.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        checkbox_product_out.setText(" Product out/delivery");
        checkbox_product_out.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));
        checkbox_product_out.setBorderPainted(true);
        checkbox_product_out.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_product_out.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        checkbox_product_out.setEnabled(false);
        checkbox_product_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_product_outActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_product_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(date_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(time_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(status_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkbox_product_out, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel31.setText("  Date");
        jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel32.setText("  Is In");
        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        status_in.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        status_in.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        date_in.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        date_in.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel34.setText("  Change Status");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel35.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel35.setText("  Time");
        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        time_in.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        time_in.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        checkbox_product_in.setBackground(new java.awt.Color(255, 255, 255));
        checkbox_product_in.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        checkbox_product_in.setText(" Product in");
        checkbox_product_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));
        checkbox_product_in.setBorderPainted(true);
        checkbox_product_in.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_product_in.setEnabled(false);
        checkbox_product_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_product_inActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_product_in, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(date_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(time_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(status_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_product_in, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        result_message.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        result_message.setForeground(new java.awt.Color(255, 102, 0));

        search_box.setPreferredSize(new java.awt.Dimension(6, 25));
        search_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_boxKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Enter product id");

        result_message2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        result_message2.setForeground(new java.awt.Color(255, 102, 0));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel27.setText("  Date");
        jLabel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel29.setText("  Is On Arrival");
        jLabel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        status_arrive.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        status_arrive.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status_arrive.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        date_arrive.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        date_arrive.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date_arrive.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel28.setText("  Time");
        jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        time_arrive.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        time_arrive.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time_arrive.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 231, 231)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_arrive, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(time_arrive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status_arrive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_arrive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(time_arrive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(status_arrive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Search Result              :");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 55, 0));
        jLabel4.setText("Name");

        product_model.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        product_model.setForeground(new java.awt.Color(0, 157, 4));

        product_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        product_name.setForeground(new java.awt.Color(0, 157, 4));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 55, 0));
        jLabel3.setText("Id   ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(1, 55, 0));
        jLabel12.setText("Price      ");

        product_price.setBackground(new java.awt.Color(255, 255, 255));
        product_price.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        product_price.setForeground(new java.awt.Color(0, 157, 4));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(1, 55, 0));
        jLabel10.setText("Model     ");

        product_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        product_id.setForeground(new java.awt.Color(0, 157, 4));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(1, 55, 0));
        jLabel13.setText("Present Status    ");

        product_current_status.setBackground(new java.awt.Color(255, 255, 255));
        product_current_status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        product_current_status.setForeground(new java.awt.Color(255, 102, 0));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(1, 55, 0));
        jLabel67.setText("Description   ");

        status_singleDescription_textarea.setEditable(false);
        status_singleDescription_textarea.setColumns(20);
        status_singleDescription_textarea.setRows(5);
        jScrollPane2.setViewportView(status_singleDescription_textarea);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(1, 55, 0));
        jLabel68.setText("Type      ");

        status_singleType_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_singleType_label.setForeground(new java.awt.Color(0, 157, 4));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(1, 55, 0));
        jLabel76.setText("Size     ");

        status_singleSize_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_singleSize_label.setForeground(new java.awt.Color(0, 157, 4));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, 0)
                        .addComponent(product_current_status, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(product_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(product_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_model, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status_singleType_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status_singleSize_label, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(product_id, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(product_model, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(status_singleType_label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel77))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_singleSize_label, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(product_price, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(product_current_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        status_report_button.setText("Report");
        status_report_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        status_report_button.setFocusable(false);
        status_report_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_report_buttonActionPerformed(evt);
            }
        });

        status_single_input_textField.setEnabled(false);

        status_singleRecipientName_textField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel69.setText("* Maximum out limit");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(1, 55, 0));
        jLabel75.setText("Recipient Card No.");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(1, 55, 0));
        jLabel74.setText("Recipient Name");

        status_singleRecipientInfo_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_singleRecipientInfo_label.setForeground(new java.awt.Color(1, 55, 0));
        status_singleRecipientInfo_label.setText("Recipient Info :");

        status_singleRecipient_textArea.setColumns(20);
        status_singleRecipient_textArea.setRows(5);
        jScrollPane4.setViewportView(status_singleRecipient_textArea);

        status_singleRecipientCard_textField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("Input ( Size of product out/delivery)");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel69)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(status_single_maxOut_label, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(status_single_input_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(status_singleRecipientInfo_label)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel74)
                                .addComponent(jLabel75))
                            .addGap(24, 24, 24)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(status_singleRecipientName_textField)
                                .addComponent(status_singleRecipientCard_textField)))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(status_singleRecipientName_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(status_singleRecipientCard_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(status_singleRecipientInfo_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addGap(6, 6, 6)
                .addComponent(status_single_input_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status_single_maxOut_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0))
        );

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Product out");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(search_box, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(result_message, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(result_message2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(status_report_button, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status_report_button)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(search_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(result_message, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(result_message2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Product Model");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Input ( Amount of product out/delivery)");

        status_model_comboBox.setMaximumRowCount(25);
        status_model_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Model" }));
        status_model_comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_model_comboBoxActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Change Status");

        status_multi_input_textField.setEnabled(false);
        status_multi_input_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_multi_input_textFieldActionPerformed(evt);
            }
        });
        status_multi_input_textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                status_multi_input_textFieldKeyReleased(evt);
            }
        });

        status_multi_inputSubmit_button.setText("Product out");
        status_multi_inputSubmit_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        status_multi_inputSubmit_button.setEnabled(false);
        status_multi_inputSubmit_button.setFocusable(false);
        status_multi_inputSubmit_button.setPreferredSize(new java.awt.Dimension(89, 22));
        status_multi_inputSubmit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_multi_inputSubmit_buttonActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 55, 0));
        jLabel5.setText("Name");

        status_multi_product_model_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_product_model_label.setForeground(new java.awt.Color(0, 157, 4));

        status_multi_product_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_product_name_label.setForeground(new java.awt.Color(0, 157, 4));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(1, 55, 0));
        jLabel26.setText("Price");

        status_multi_product_price_label.setBackground(new java.awt.Color(255, 255, 255));
        status_multi_product_price_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_product_price_label.setForeground(new java.awt.Color(0, 157, 4));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(1, 55, 0));
        jLabel30.setText("Model");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(1, 55, 0));
        jLabel11.setText("Quantity");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(1, 55, 0));
        jLabel45.setText("In");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(1, 55, 0));
        jLabel49.setText("Out");

        status_multi_product_quantity_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_product_quantity_label.setForeground(new java.awt.Color(0, 157, 4));

        status_multi_product_in_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_product_in_label.setForeground(new java.awt.Color(0, 157, 4));

        status_multi_product_out_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_product_out_label.setForeground(new java.awt.Color(0, 157, 4));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(1, 55, 0));
        jLabel54.setText("Year");

        status_multi_year_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_year_label.setForeground(new java.awt.Color(0, 157, 4));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(1, 55, 0));
        jLabel70.setText("Type");

        status_multi_productType_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_productType_label.setForeground(new java.awt.Color(0, 157, 4));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(1, 55, 0));
        jLabel71.setText("Description");

        status_multiDescription_textarea.setColumns(20);
        status_multiDescription_textarea.setRows(5);
        jScrollPane3.setViewportView(status_multiDescription_textarea);

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(1, 55, 0));
        jLabel72.setText("Partially Out");

        status_multi_product_partiallyOut_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_product_partiallyOut_label.setForeground(new java.awt.Color(0, 157, 4));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(status_multi_product_in_label, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(status_multi_product_out_label, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(status_multi_product_partiallyOut_label, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(status_multi_productType_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(status_multi_product_model_label, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(status_multi_year_label, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(status_multi_product_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel71))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_multi_product_price_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(status_multi_product_quantity_label, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(status_multi_year_label, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(status_multi_product_model_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel70)
                    .addComponent(status_multi_productType_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(status_multi_product_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(status_multi_product_price_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(status_multi_product_quantity_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(status_multi_product_in_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addComponent(status_multi_product_out_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status_multi_product_partiallyOut_label, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addGap(5, 5, 5))
        );

        status_multi_productIn_checkBox.setText("Product in (all remaining)");
        status_multi_productIn_checkBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        status_multi_productIn_checkBox.setEnabled(false);
        status_multi_productIn_checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_multi_productIn_checkBoxActionPerformed(evt);
            }
        });

        status_multi_productOut_checkBox.setText("Product out/delivery (all remaining)");
        status_multi_productOut_checkBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        status_multi_productOut_checkBox.setEnabled(false);
        status_multi_productOut_checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_multi_productOut_checkBoxActionPerformed(evt);
            }
        });

        status_report_button2.setText("Report");
        status_report_button2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        status_report_button2.setFocusable(false);
        status_report_button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_report_button2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Search Result :");

        status_multi_validate_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel33.setText("* Maximum out limit");

        status_multi_finalResult_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_multi_finalResult_label.setForeground(new java.awt.Color(0, 102, 0));

        status_multiRecipientName_textField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(1, 55, 0));
        jLabel79.setText("Recipient Card No.");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(1, 55, 0));
        jLabel80.setText("Recipient Name");

        status_singleRecipientInfo_label1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status_singleRecipientInfo_label1.setForeground(new java.awt.Color(1, 55, 0));
        status_singleRecipientInfo_label1.setText("Recipient Info :");

        status_multiRecipient_textArea.setColumns(20);
        status_multiRecipient_textArea.setRows(5);
        jScrollPane5.setViewportView(status_multiRecipient_textArea);

        status_multiRecipientCard_textField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(status_multiRecipientName_textField)
                            .addComponent(status_singleRecipientInfo_label1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(status_multiRecipientCard_textField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(status_single_maxOut_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status_multiRecipientName_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status_multiRecipientCard_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status_singleRecipientInfo_label1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(status_single_maxOut_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(status_multi_finalResult_label, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(status_multi_validate_label, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel53)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(status_multi_input_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(status_multi_inputSubmit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status_multi_maxOut_label, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status_report_button2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(status_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(status_model_comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(status_multi_searchResult_label, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(status_multi_productIn_checkBox)
                                .addGap(31, 31, 31)
                                .addComponent(status_multi_productOut_checkBox)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(status_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(status_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(status_multi_searchResult_label, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_multi_productIn_checkBox)
                    .addComponent(jLabel44)
                    .addComponent(status_multi_productOut_checkBox))
                .addGap(12, 12, 12)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_multi_input_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status_multi_inputSubmit_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(status_multi_maxOut_label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status_multi_finalResult_label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status_report_button2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(status_multi_validate_label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout status_panelLayout = new javax.swing.GroupLayout(status_panel);
        status_panel.setLayout(status_panelLayout);
        status_panelLayout.setHorizontalGroup(
            status_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_panelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        status_panelLayout.setVerticalGroup(
            status_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, status_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(status_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        parent_panel.add(status_panel, "card3");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Product Model");

        stat_product_model_comboBox.setMaximumRowCount(25);
        stat_product_model_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Model" }));
        stat_product_model_comboBox.setFocusable(false);
        stat_product_model_comboBox.setMaximumSize(new java.awt.Dimension(246, 20));
        stat_product_model_comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stat_product_model_comboBoxActionPerformed(evt);
            }
        });

        stat_year_comboBox.setFocusable(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Year");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Order By");

        stat_model_orderByComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "model", "product_id", "name", "price ascending", "price descending", "date_arrival", "date_in", "date_out" }));
        stat_model_orderByComboBox.setFocusable(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stat_product_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(stat_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stat_model_orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stat_year_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25)
                    .addComponent(stat_model_orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(stat_product_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        stat_toDate4.setEnabled(false);
        stat_toDate4.setFocusable(false);
        stat_toDate4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stat_toDate4ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("From date");

        stat_fromDate3.setFocusable(false);
        stat_fromDate3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stat_fromDate3ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("To date");

        stat_status_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Product On Arrival", "Product In", "Product Out" }));
        stat_status_comboBox.setFocusable(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Order By");

        stat_status_orderByComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "model", "product_id", "name", "price ascending", "price descending", "date_arrival", "date_in", "date_out" }));
        stat_status_orderByComboBox.setFocusable(false);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("According to status");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stat_fromDate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stat_toDate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stat_status_orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stat_status_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel24))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stat_status_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addGap(26, 26, 26)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(stat_status_orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(stat_fromDate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(stat_toDate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Item Shows: ");

        stat_table_scrollpane.setBackground(new java.awt.Color(255, 255, 255));
        stat_table_scrollpane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        stat_table_scrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        stat_table_scrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        stat_table_scrollpane.setAutoscrolls(true);

        stat_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        stat_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        stat_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        stat_table.setRowHeight(30);
        stat_table_scrollpane.setViewportView(stat_table);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Total Product :");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Total Price      :");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Order By         :");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Search Mode  :");

        stat_report_button.setText("Report");
        stat_report_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stat_report_button.setFocusable(false);
        stat_report_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stat_report_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(jLabel23)
                    .addComponent(jLabel41)
                    .addComponent(jLabel47))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stat_label_searchMode, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(stat_label_orderBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stat_label_totalProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(stat_label_totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stat_report_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(stat_label_searchMode, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(stat_label_orderBy, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(stat_label_totalProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(stat_label_totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(stat_report_button)))
                .addGap(11, 11, 11))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        stat_dateToDelivered.setFocusable(false);
        stat_dateToDelivered.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stat_dateToDeliveredActionPerformed(evt);
            }
        });

        stat_deliveredName_comboBox.setMaximumRowCount(15);
        stat_deliveredName_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Name" }));
        stat_deliveredName_comboBox.setEnabled(false);
        stat_deliveredName_comboBox.setFocusable(false);
        stat_deliveredName_comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stat_deliveredName_comboBoxActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("Date");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Delivered Name");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("Order By");

        stat_deliveredTo_orderByComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "model", "product_id", "name", "price ascending", "price descending", "date_arrival", "date_in", "date_out" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stat_dateToDelivered, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stat_deliveredTo_orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(stat_deliveredName_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stat_dateToDelivered, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stat_deliveredName_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stat_deliveredTo_orderByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout statistics_panelLayout = new javax.swing.GroupLayout(statistics_panel);
        statistics_panel.setLayout(statistics_panelLayout);
        statistics_panelLayout.setHorizontalGroup(
            statistics_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statistics_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statistics_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(statistics_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statistics_panelLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stat_itemShows_message, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(stat_table_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        statistics_panelLayout.setVerticalGroup(
            statistics_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statistics_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(statistics_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statistics_panelLayout.createSequentialGroup()
                        .addGroup(statistics_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(stat_itemShows_message, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stat_table_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(statistics_panelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        parent_panel.add(statistics_panel, "card2");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Model");

        barcode_model_comboBox.setMaximumRowCount(25);
        barcode_model_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Model" }));

        javax.swing.GroupLayout barcode_panelLayout = new javax.swing.GroupLayout(barcode_panel);
        barcode_panel.setLayout(barcode_panelLayout);
        barcode_panelLayout.setHorizontalGroup(
            barcode_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barcode_panelLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel63)
                .addGap(18, 18, 18)
                .addComponent(barcode_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(994, Short.MAX_VALUE))
        );
        barcode_panelLayout.setVerticalGroup(
            barcode_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barcode_panelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(barcode_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(barcode_model_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(490, Short.MAX_VALUE))
        );

        parent_panel.add(barcode_panel, "card6");

        getContentPane().add(parent_panel, java.awt.BorderLayout.CENTER);

        jMenuBar1.setBackground(new java.awt.Color(171, 171, 171));
        jMenuBar1.setBorder(null);

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("      Account     ");
        jMenu1.setEnabled(false);

        jMenuItem2.setText("Create           ");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(111, 30));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Update           ");
        jMenuItem1.setPreferredSize(new java.awt.Dimension(115, 30));
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Delete              ");
        jMenuItem3.setPreferredSize(new java.awt.Dimension(119, 30));
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("      Edit     ");

        addModel_jMenuItem.setText("Add Model      ");
        addModel_jMenuItem.setPreferredSize(new java.awt.Dimension(121, 30));
        addModel_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModel_jMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(addModel_jMenuItem);

        addRemark_jMenuitem.setText("Add Remark");
        addRemark_jMenuitem.setPreferredSize(new java.awt.Dimension(135, 30));
        addRemark_jMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRemark_jMenuitemActionPerformed(evt);
            }
        });
        jMenu2.add(addRemark_jMenuitem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void status_show_data()
    {
        result_message2.setText(" ");
        String sql = "SELECT insert_year FROM product_id_list WHERE product_id=? LIMIT 1";
        
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, search_box.getText());
            rs = pstmt.executeQuery();
           
            if(rs.next())
            {
                sql = "SELECT * FROM product_info_"+rs.getString("insert_year")+" WHERE product_id=? LIMIT 1";
                sql_status_single_report = "SELECT product_id,model,name,size,minus,date_in,date_out,price FROM product_info_"+rs.getString("insert_year")+" WHERE product_id='"+product_id.getText()+"'";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, search_box.getText());
                rs = pstmt.executeQuery();
            }
            String found = "not";
                    if(rs.next())
                    {
                        found = "yep";
                        result_message.setText("Result Found");
                        result_message.setForeground(new Color(0,102,0));
                        
                        String id_current_status  = rs.getString("current_status");
                        
                        checkbox_product_in.setEnabled(true);
                        checkbox_product_out.setEnabled(true);
                        checkbox_product_in.setSelected(false);
                        checkbox_product_out.setSelected(false);
                                               
                        product_model.setText(rs.getString("model"));
                        product_name.setText(rs.getString("name"));
                        status_singleSize_label.setText(rs.getString("size"));
                        product_price.setText(rs.getString("price"));
                        product_current_status.setText(id_current_status);
                        
                        status_single_maxOut_label.setText(String.valueOf(Float.parseFloat(rs.getString("size")) - Float.parseFloat(rs.getString("minus"))));
                        
                        date_arrive.setText(rs.getString("date_arrival"));
                        time_arrive.setText(rs.getString("time_arrival"));
                        status_arrive.setText(rs.getString("status_arrival"));
                        
                        date_in.setText(rs.getString("date_in"));
                        time_in.setText(rs.getString("time_in"));
                        status_in.setText(rs.getString("status_in"));
                        
                        date_out.setText(rs.getString("date_out"));
                        time_out.setText(rs.getString("time_out"));
                        status_out.setText(rs.getString("status_out"));       
                        
                         sql = "SELECT type,description FROM product_model WHERE model=? LIMIT 1";
                         pstmt = conn.prepareStatement(sql);
                         pstmt.setString(1, rs.getString("model"));
                         rs = pstmt.executeQuery();
                         if(rs.next())
                         {
                            status_singleType_label.setText(rs.getString("type"));
                            status_singleDescription_textarea.setText(rs.getString("description"));
                            if(rs.getString("type").equals("countable"))
                            {
                               status_singleSize_label.setText("");
                            }
                         }
                         
                         switch (id_current_status) {
                            case "On arrival":
                                checkbox_product_out.setEnabled(false);
                                break;
                            case "Product in":
                                checkbox_product_in.setSelected(true);
                                checkbox_product_in.setEnabled(false);
                                if(rs.getString("type").equals("uncountable"))
                                {
                                    status_single_input_textField.setEnabled(true);
                                }
                                break;
                            case "Product out":
                                checkbox_product_in.setSelected(true);
                                checkbox_product_in.setEnabled(false);
                                if(rs.getString("type").equals("countable"))
                                {
                                   checkbox_product_out.setSelected(true);
                                   checkbox_product_out.setEnabled(false);
                                   status_single_maxOut_label.setText("");
                                   status_single_input_textField.setEnabled(false);
                                    
                                }else if(rs.getString("type").equals("uncountable") && Float.parseFloat(status_single_maxOut_label.getText()) <= 0)
                                {  checkbox_product_out.setSelected(true);
                                   checkbox_product_out.setEnabled(false);
                                }else{
                                   checkbox_product_out.setEnabled(true);
                                   status_single_input_textField.setEnabled(true);
                                }
                                break;
                            }
                    }
            if(found.equals("not"))
            {
                result_message.setText("No Result Found");
                result_message.setForeground(Color.RED);
                        
                product_model.setText(" ");
                product_name.setText(" ");
                product_price.setText(" ");
                product_current_status.setText(" ");
                status_singleType_label.setText("");
                status_singleDescription_textarea.setText("");
                status_single_maxOut_label.setText("");
                status_singleSize_label.setText("");
                
                date_arrive.setText(" ");
                time_arrive.setText(" ");
                status_arrive.setText(" ");

                date_in.setText(" ");
                time_in.setText(" ");
                status_in.setText(" ");

                date_out.setText(" ");
                time_out.setText(" ");
                status_out.setText(" ");
                
                checkbox_product_in.setSelected(false);
                checkbox_product_out.setSelected(false);
                checkbox_product_in.setEnabled(false);
                checkbox_product_out.setEnabled(false);
                status_single_input_textField.setEnabled(false);
                
            }
            status_singleRecipientName_textField.setText("");
            status_singleRecipientCard_textField.setText("");
            status_singleRecipient_textArea.setText("");
            status_single_input_textField.setText("");
            
            //
        }catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }finally{
        
            
        }
    }
    
    
    
    
     private void entry_insert_data(float price)
    {                        
            String sql,remark; int rows = 0; int numOfProduct = Integer.parseInt(entry_numOfProduct_jtextField.getText());
            System.out.println("dd:"+numOfProduct);
            if(entry_remark_comboBox.getSelectedItem().equals("Remark"))
            {
               remark = "";
            }else{
               remark = entry_remark_comboBox.getSelectedItem().toString();
            }
                   
            try{
                 for(int i=0; i < numOfProduct ; i++)
                 {
                    sql = "INSERT INTO product_info_"+year()+" (year_arrival,month_arrival,date_arrival,time_arrival,year_in,month_in,date_in,time_in,year_out,month_out,date_out,time_out,name,model,product_id,size,minus,price,current_status,status_arrival,status_in,status_out,delivered_name,delivered_cardNo,delivered_description,user) ";
                    sql += "VALUES (";
                    sql += "'"+year()+"','"+month()+"','"+date()+"','"+time()+"',";
                    sql += "'','','','','','','','',";
                    sql += "'"+StringEscapeUtils.escapeSql(entry_product_name.getText())+"','"+entry_model_comboBox.getSelectedItem().toString()+"','"+StringEscapeUtils.escapeSql(entry_productId_jtable.getModel().getValueAt(i,0).toString())+"',";
                    if(entry_productType_label.getText().equals("uncountable"))
                    {
                       sql += "'"+StringEscapeUtils.escapeSql(entry_productId_jtable.getModel().getValueAt(i,1).toString())+"',0,'"+entry_productId_jtable.getModel().getValueAt(i,2).toString()+"',";
                    }else{
                       sql += "0,0,'"+price+"',";
                    }
                    sql += "'On arrival','On arrival','','','','','','"+ConnectDB.username+"')";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    
                    sql = "INSERT INTO product_id_list (product_id,model,insert_year,date) ";
                    sql += "VALUES (";
                    sql += "'"+StringEscapeUtils.escapeSql(entry_productId_jtable.getModel().getValueAt(i,0).toString())+"','"+entry_model_comboBox.getSelectedItem().toString()+"','"+year()+"','"+date()+"')";

                    pstmt = conn.prepareStatement(sql);
                    rows  = pstmt.executeUpdate();
                 }
                 
                 sql = "UPDATE product_model SET name='"+StringEscapeUtils.escapeSql(entry_product_name.getText())+"',description='"+StringEscapeUtils.escapeSql(entry_description_textArea.getText())+"',price='"+price+"',remark='"+remark+"',quantity=quantity+'"+numOfProduct+"' WHERE model='"+entry_model_comboBox.getSelectedItem().toString()+"'  AND  insert_year='"+year()+"'";
                 pstmt = conn.prepareStatement(sql);
                 
                 if(pstmt.executeUpdate() == 0)
                 {
                     sql = "INSERT INTO product_model (date,time,insert_year,model,type,name,description,price,remark,quantity,total_in,total_out,partially_out,user) VALUES ('"+date()+"','"+time()+"','"+year()+"','"+entry_model_comboBox.getSelectedItem().toString()+"','"+entry_productType_label.getText()+"','"+StringEscapeUtils.escapeSql(entry_product_name.getText())+"','"+StringEscapeUtils.escapeSql(entry_description_textArea.getText())+"','"+price+"','"+remark+"','"+numOfProduct+"',0,0,0,'"+ConnectDB.username+"') ";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.executeUpdate();
                 }
                 
                  if(rows > 0)
                  {
                        entry_result_message.setText("Data inserted successfully");
                        entry_product_name.setText("");
                        entry_product_price.setText("");
                        entry_numOfProduct_jtextField.setText("");
                        entry_description_textArea.setText("");
                        entry_productType_label.setText("");
                        entry_modelHidden_label.setText("");
                        entryProductIdTableModel.setRowCount(0);
                        entry_submit_button.setEnabled(false);
                        Object obj = "Remark";
                        entry_remark_comboBox.setSelectedItem(obj);
                        obj = "Model";
                        entry_model_comboBox.setSelectedItem(obj);
                  }
            }catch(Exception e)
            {
                  JOptionPane.showMessageDialog(null, e);
            }
             
    }
     
    private void stat_product_model_comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stat_product_model_comboBoxActionPerformed
        // TODO add your handling code here:
         stat_search_choice = 1;
         limitStartForModelCombobox = 20;
         itemCount = 0;
        if(stat_table.getWidth() != 0)
        {
             statTableModel.setRowCount(0); 
             statTable_row = 0;
        }
         stat_display_result_in_table("modelComboBox");
    }//GEN-LAST:event_stat_product_model_comboBoxActionPerformed

    private void stat_fromDate3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stat_fromDate3ActionPerformed
        // TODO add your handling code here:
        stat_toDate4.setEnabled(true);
    }//GEN-LAST:event_stat_fromDate3ActionPerformed

    private void stat_toDate4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stat_toDate4ActionPerformed
        // TODO add your handling code here:
        if(stat_table.getWidth() != 0)
        {
            // statTableModel.setColumnCount(0);
             statTableModel.setRowCount(0);            
        }
        stat_search_choice = 2;
        statTable_row = 0;
        limitStartForStatusBox = 20;
        itemCount = 0;
        
             if(formatter.format(stat_fromDate3.getDate()).substring(0, 4).equals(formatter.format(stat_toDate4.getDate()).substring(0,4))  )
             {
                 stat_display_result_in_table("accordingStatus");
             }else{
                 JOptionPane.showMessageDialog(null, "Please select same year");
             }
    }//GEN-LAST:event_stat_toDate4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void stat_report_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stat_report_buttonActionPerformed
        // TODO add your handling code here:
        
        try{
               JasperDesign jd = JRXmlLoader.load("C:\\IreportDesign\\statistics_report_cherry.jrxml");
               JRDesignQuery newQuery = new JRDesignQuery();
               //String sql = "SELECT * FROM product_info_2016 ORDER BY LENGTH(name),name";
               newQuery.setText(stat_sql);
               jd.setQuery(newQuery);
               
                 HashMap param = new HashMap();
                 param.put("TOTAL_PRODUCT",total_product);
                 param.put("TOTAL_PRICE",total_price);
               
               JasperReport jr = JasperCompileManager.compileReport(jd);
               JasperPrint jp = JasperFillManager.fillReport(jr,param, conn);
               JasperViewer.viewReport(jp, false);
               
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_stat_report_buttonActionPerformed

    private void addModel_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModel_jMenuItemActionPerformed
        // TODO add your handling code here:
        if(!addModelFrame.isVisible())
        { 
           addModelFrame.setVisible(true);
           addModelFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }else{
           addModelFrame.requestFocus();
        }
    }//GEN-LAST:event_addModel_jMenuItemActionPerformed

    private void home_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_buttonActionPerformed
        // TODO add your handling code here:
        home_button.setEnabled(false);
        status_button.setEnabled(true);
        statistics_button.setEnabled(true);
        entry_button.setEnabled(true);
        barcode_button.setEnabled(true);

        parent_panel.removeAll();
        parent_panel.add(home_panel);
        parent_panel.repaint();
        parent_panel.revalidate();
    }//GEN-LAST:event_home_buttonActionPerformed

    private void statistics_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statistics_buttonActionPerformed
        // TODO add your handling code here:
        statistics_button.setEnabled(false);
        home_button.setEnabled(true);
        entry_button.setEnabled(true);
        status_button.setEnabled(true);
        barcode_button.setEnabled(true);

        parent_panel.removeAll();
        parent_panel.add(statistics_panel);
        parent_panel.repaint();
        parent_panel.revalidate();
    }//GEN-LAST:event_statistics_buttonActionPerformed

    private void status_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_buttonActionPerformed
        // TODO add your handling code here:
        status_button.setEnabled(false);
        statistics_button.setEnabled(true);
        home_button.setEnabled(true);
        entry_button.setEnabled(true);
        barcode_button.setEnabled(true);

        parent_panel.removeAll();
        parent_panel.add(status_panel);
        parent_panel.repaint();
        parent_panel.revalidate();

        // table_info.setVisible(false);
    }//GEN-LAST:event_status_buttonActionPerformed

    private void entry_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entry_buttonActionPerformed
        // TODO add your handling code here:
        //entry_product_id.requestFocusInWindow();
        entry_button.setEnabled(false);
        statistics_button.setEnabled(true);
        status_button.setEnabled(true);
        home_button.setEnabled(true);

        parent_panel.removeAll();
        parent_panel.add(entry_panel);
        parent_panel.repaint();
        parent_panel.revalidate();

    }//GEN-LAST:event_entry_buttonActionPerformed

    private void addRemark_jMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRemark_jMenuitemActionPerformed
        // TODO add your handling code here:
        if(!addRemarkFrame.isVisible())
        { 
           addRemarkFrame.setVisible(true);
           addRemarkFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }else{
           addRemarkFrame.requestFocus();
        }
    }//GEN-LAST:event_addRemark_jMenuitemActionPerformed

    private void review_model_comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_review_model_comboBoxActionPerformed
        // TODO add your handling code here:
        summaryOfToday();
    }//GEN-LAST:event_review_model_comboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        summaryOfToday();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void status_report_button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_report_button2ActionPerformed
        // TODO add your handling code here:
        try{
            JasperDesign jd = JRXmlLoader.load("C:\\IreportDesign\\status_multi_product.jrxml");
            JRDesignQuery newQuery = new JRDesignQuery();

            String sql,total_product_multi = null,total_price_multi = null;
            if(sql_status_multi_report_permission == 0 && !status_multi_product_model_label.getText().isEmpty())
            {
                sql = "SELECT COUNT(product_id) AS 'total_product',SUM(price) AS 'total_price' FROM product_info_"+status_multi_year_label.getText()+" WHERE model='"+status_multi_product_model_label.getText()+"' AND status_out='Product out' ORDER BY product_id ASC";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                rs.next();
                total_product_multi = rs.getString("total_product");
                total_price_multi = rs.getString("total_price");

                newQuery.setText("SELECT product_id,model,date_in,date_out,price FROM product_info_"+status_multi_year_label.getText()+" WHERE model='"+status_multi_product_model_label.getText()+"' AND status_out='Product out' ORDER BY product_id ASC");

            }else if(!status_multi_product_model_label.getText().isEmpty()){
                sql = "SELECT COUNT(product_id) AS 'total_product',SUM(price) AS 'total_price' FROM product_info_"+status_multi_year_label.getText()+" WHERE model='"+status_multi_product_model_label.getText()+"' AND current_status ='Product out' AND date_out='"+date_status_multi+"' AND time_out='"+time_status_multi+"' ORDER BY product_id ASC";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                rs.next();
                total_product_multi = rs.getString("total_product");
                total_price_multi = rs.getString("total_price");
                newQuery.setText("SELECT product_id,model,date_in,date_out,price FROM product_info_"+status_multi_year_label.getText()+" WHERE model='"+status_multi_product_model_label.getText()+"' AND current_status ='Product out' AND date_out='"+date_status_multi+"' AND time_out='"+time_status_multi+"' ORDER BY product_id ASC");
            }

            jd.setQuery(newQuery);

            HashMap param = new HashMap();

            param.put("TOTAL_PRODUCT",total_product_multi);
            param.put("TOTAL_PRICE",total_price_multi);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr,param, conn);

            JasperViewer.viewReport(jp, false);

        }catch(JRException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_status_report_button2ActionPerformed

    private void status_multi_productOut_checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_multi_productOut_checkBoxActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to all remaining product out?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(response == JOptionPane.YES_OPTION) {
            status_multi_productOut_checkBox.setEnabled(false);
            try{
                String sql;

                sql_status_multi_report_permission = 1;

                date_status_multi = date();
                time_status_multi = time();

                sql = "UPDATE product_info_"+status_multi_year_label.getText()+" SET year_out='"+year()+"', month_out='"+month()+"', date_out='"+date_status_multi+"',time_out='"+time_status_multi+"', status_out='Product out', current_status='Product out' WHERE  model='"+status_multi_product_model_label.getText()+"' AND current_status='Product in' ";
                pstmt = conn.prepareStatement(sql);
                int rows = pstmt.executeUpdate();

                sql = "UPDATE product_model SET total_out='"+Integer.parseInt(status_multi_product_quantity_label.getText())+"' WHERE model='"+status_multi_product_model_label.getText()+"' AND insert_year='"+status_multi_year_label.getText()+"'";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();

                if(rows > 0)
                {
                    status_multi_product_out_label.setText(status_multi_product_quantity_label.getText());
                    status_multi_maxOut_label.setText("0");
                    status_multi_finalResult_label.setText("Updated Successfully and Delivered/Out : "+rows);
                    status_multi_input_textField.setEnabled(false);
                    status_multi_inputSubmit_button.setEnabled(false);
                }

            }catch(SQLException | NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, e);
            }

        }else if(response == JOptionPane.NO_OPTION)
        {
            status_multi_productOut_checkBox.setSelected(false);
        }
    }//GEN-LAST:event_status_multi_productOut_checkBoxActionPerformed

    private void status_multi_productIn_checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_multi_productIn_checkBoxActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to all remaining product in?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(response == JOptionPane.YES_OPTION) {

            try{
                String sql;

                sql = "UPDATE product_info_"+status_multi_year_label.getText()+" SET year_in='"+year()+"', month_in='"+month()+"', date_in='"+date()+"',time_in='"+time()+"', status_in='Product in', current_status='Product in' WHERE  model='"+status_multi_product_model_label.getText()+"' AND current_status='On arrival' ";
                pstmt = conn.prepareStatement(sql);
                int rows = pstmt.executeUpdate();

                sql = "UPDATE product_model SET total_in='"+Integer.parseInt(status_multi_product_quantity_label.getText())+"' WHERE model='"+status_multi_product_model_label.getText()+"'  AND  insert_year='"+status_multi_year_label.getText()+"'";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();

                if(rows > 0)
                {
                    status_multi_finalResult_label.setText("Updated Successfully");
                    status_multi_product_in_label.setText(status_multi_product_quantity_label.getText());
                }
                status_multi_maxOut_label.setText(String.valueOf(Integer.parseInt(rs.getString("total_in"))-Integer.parseInt(rs.getString("total_out"))));

                status_multi_productIn_checkBox.setEnabled(false);
                status_multi_maxOut_label.setText(String.valueOf(Integer.parseInt(rs.getString("quantity"))-Integer.parseInt(rs.getString("total_out"))));
                status_multi_productOut_checkBox.setEnabled(true);
                status_multi_input_textField.setEnabled(true);
                status_multi_inputSubmit_button.setEnabled(true);

            }catch(SQLException | NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, e);
            }

        }else if(response == JOptionPane.NO_OPTION)
        {
            status_multi_productIn_checkBox.setSelected(false);
        }
    }//GEN-LAST:event_status_multi_productIn_checkBoxActionPerformed

    private void status_multi_inputSubmit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_multi_inputSubmit_buttonActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to proceed ?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(response == JOptionPane.YES_OPTION) {

            if(Pattern.matches("\\d+", status_multi_input_textField.getText()))
            {
                if(status_multi_input_textField.getText().trim().isEmpty() || Integer.parseInt(status_multi_input_textField.getText()) == 0)
                {
                    status_multi_input_textField.requestFocusInWindow();
                    JOptionPane.showMessageDialog(null, "Input field cannot be empty");
                }else if(Integer.parseInt(status_multi_input_textField.getText()) > Integer.parseInt(status_multi_maxOut_label.getText()))
                {
                    status_multi_input_textField.requestFocusInWindow();
                    JOptionPane.showMessageDialog(null, "Maximum out limit exceeds");
                }else{
                    try{
                        String sql ;

                        sql_status_multi_report_permission = 1;

                        date_status_multi = date();
                        time_status_multi = time();

                        sql = "UPDATE product_info_"+status_multi_year_label.getText()+" SET year_out='"+year()+"', month_out='"+month()+"', date_out='"+date_status_multi+"',time_out='"+time_status_multi+"', status_out='Product out', current_status='Product out' WHERE  model='"+status_multi_product_model_label.getText()+"' AND current_status='Product in' ORDER BY product_id LIMIT "+status_multi_input_textField.getText()+"";
                        pstmt = conn.prepareStatement(sql);
                        int rows = pstmt.executeUpdate();

                        sql = "UPDATE product_model SET total_out=total_out+"+Integer.parseInt(status_multi_input_textField.getText())+" WHERE model='"+status_multi_product_model_label.getText()+"' AND insert_year='"+status_multi_year_label.getText()+"'";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();

                        if(rows > 0)
                        {
                            status_multi_product_out_label.setText(String.valueOf(Integer.parseInt(status_multi_input_textField.getText()) + Integer.parseInt(status_multi_product_out_label.getText())));
                            status_multi_maxOut_label.setText(String.valueOf(Integer.parseInt(status_multi_maxOut_label.getText())-Integer.parseInt(status_multi_input_textField.getText())));

                            if(status_multi_product_quantity_label.getText().equals(status_multi_product_out_label.getText()))
                            {
                                status_multi_input_textField.setEnabled(false);
                                status_multi_inputSubmit_button.setEnabled(false);
                            }

                            status_multi_finalResult_label.setText("Updated Successfully and Delivered/Out : "+Integer.parseInt(status_multi_input_textField.getText()));
                            status_multi_input_textField.setText("0");

                        }

                    }catch(SQLException | NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(null, e);
                    }

                }
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }
        }

    }//GEN-LAST:event_status_multi_inputSubmit_buttonActionPerformed

    private void status_multi_input_textFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_status_multi_input_textFieldKeyReleased
        // TODO add your handling code here:
        boolean b = Pattern.matches("\\d+", status_multi_input_textField.getText());

        if(b)
        {
            status_multi_validate_label.setText("valid");
            status_multi_validate_label.setForeground(new Color(0,102,0));
        }else{
            status_multi_validate_label.setText("invalid");
            status_multi_validate_label.setForeground(Color.RED);
        }
    }//GEN-LAST:event_status_multi_input_textFieldKeyReleased

    private void status_model_comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_model_comboBoxActionPerformed
        // TODO add your handling code here:
        sql_status_multi_report_permission = 0;
        status_multi_searchResult_label.setText("");
        status_multi_validate_label.setText("");
        status_multi_maxOut_label.setText("0");
        status_multi_finalResult_label.setText("");

        status_multi_productIn_checkBox.setEnabled(true);
        status_multi_productOut_checkBox.setEnabled(true);
        status_multi_productIn_checkBox.setSelected(false);
        status_multi_productOut_checkBox.setSelected(false);

        status_multi_input_textField.setEnabled(true);
        status_multi_input_textField.setText("");
        status_multi_inputSubmit_button.setEnabled(true);

        status_multi_year_label.setText(status_year_comboBox.getSelectedItem().toString());

        String sql = "SELECT product_id FROM product_info_"+status_multi_year_label.getText()+" WHERE model=? LIMIT 1";
        int productFound = 0;

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status_model_comboBox.getSelectedItem().toString());
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                productFound = 1;
            }

            sql = "SELECT name,price,quantity,total_in,total_out FROM product_model WHERE model=? AND insert_year=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status_model_comboBox.getSelectedItem().toString());
            pstmt.setString(2, status_multi_year_label.getText());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                status_multi_product_model_label.setText(status_model_comboBox.getSelectedItem().toString());
                status_multi_product_name_label.setText(rs.getString("name"));
                status_multi_product_price_label.setText(rs.getString("price"));
                status_multi_product_quantity_label.setText(rs.getString("quantity"));
                status_multi_product_in_label.setText(rs.getString("total_in"));
                status_multi_product_out_label.setText(rs.getString("total_out"));

                if(Integer.parseInt(rs.getString("quantity")) == 0 || productFound == 0)
                {
                    status_multi_searchResult_label.setText("No Product Found");
                    status_multi_searchResult_label.setForeground(Color.red);

                    status_multi_productIn_checkBox.setEnabled(false);
                    status_multi_productOut_checkBox.setEnabled(false);
                    status_multi_input_textField.setEnabled(false);
                    status_multi_inputSubmit_button.setEnabled(false);

                }else{

                    status_multi_searchResult_label.setText("Result Found");
                    status_multi_searchResult_label.setForeground(new Color(0,102,0));
                    status_multi_maxOut_label.setText(String.valueOf(Integer.parseInt(rs.getString("quantity"))-Integer.parseInt(rs.getString("total_out"))));

                    if(rs.getString("quantity").equals(rs.getString("total_in")))
                    {
                        status_multi_productIn_checkBox.setEnabled(true);
                        status_multi_productIn_checkBox.setSelected(true);
                        status_multi_productIn_checkBox.setEnabled(false);
                    }else{

                        if(Integer.parseInt(rs.getString("total_in")) > Integer.parseInt(rs.getString("total_out")))
                        {
                            status_multi_maxOut_label.setText(String.valueOf(Integer.parseInt(rs.getString("total_in"))-Integer.parseInt(rs.getString("total_out"))));
                        }else{
                            status_multi_productOut_checkBox.setEnabled(false);
                            status_multi_input_textField.setEnabled(false);
                            status_multi_inputSubmit_button.setEnabled(false);
                            status_multi_maxOut_label.setText("0");
                        }
                    }

                    if(rs.getString("quantity").equals(rs.getString("total_out")))
                    {
                        status_multi_productOut_checkBox.setEnabled(true);
                        status_multi_productOut_checkBox.setSelected(true);
                        status_multi_productOut_checkBox.setEnabled(false);
                        status_multi_input_textField.setEnabled(false);
                        status_multi_inputSubmit_button.setEnabled(false);
                    }

                }
            }else{
                status_multi_productIn_checkBox.setEnabled(false);
                status_multi_productOut_checkBox.setEnabled(false);
                status_multi_input_textField.setEnabled(false);
                status_multi_inputSubmit_button.setEnabled(false);
            }

        }catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_status_model_comboBoxActionPerformed

    private void status_report_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_report_buttonActionPerformed
        // TODO add your handling code here:
        try{
            JasperDesign jd = JRXmlLoader.load("C:\\IreportDesign\\status_single_product.jrxml");
            JRDesignQuery newQuery = new JRDesignQuery();

            newQuery.setText(sql_status_single_report);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr,null, conn);

            JasperViewer.viewReport(jp, false);

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_status_report_buttonActionPerformed

    private void search_boxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_boxKeyReleased
        // TODO add your handling code here:

        product_id.setText(search_box.getText());
        status_show_data();

    }//GEN-LAST:event_search_boxKeyReleased

    private void checkbox_product_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_product_inActionPerformed
        // TODO add your handling code here:

        String sql = "SELECT insert_year,model FROM product_id_list WHERE product_id=? LIMIT 1";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, search_box.getText());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                sql = "UPDATE product_info_"+rs.getString("insert_year")+" SET year_in='"+year()+"', month_in='"+month()+"', date_in='"+date()+"',time_in='"+time()+"', status_in='Product in', current_status='Product in' WHERE product_id='"+search_box.getText()+"' AND model='"+rs.getString("model")+"'";
                pstmt = conn.prepareStatement(sql);
                int rows = pstmt.executeUpdate();

                sql = "UPDATE product_model SET total_in=total_in+1 WHERE model='"+rs.getString("model")+"' AND  insert_year='"+rs.getString("insert_year")+"'";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();

                if(rows > 0)
                {
                    status_show_data();
                    result_message2.setText("Updated Successfully");
                    result_message2.setForeground(new Color(0,102,0));
                }
            }

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

        search_box.requestFocusInWindow();

    }//GEN-LAST:event_checkbox_product_inActionPerformed

    private void checkbox_product_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_product_outActionPerformed
        // TODO add your handling code here:

        try{
            checkbox_product_out.setSelected(false);
            if(status_singleRecipientName_textField.getText().trim().isEmpty())
            {
                status_singleRecipientName_textField.requestFocusInWindow();
                JOptionPane.showMessageDialog(null, "Recipient name cannot be empty");
            }else if(status_singleType_label.getText().equals("uncountable") && !Pattern.matches("[0-9]*(\\.[0-9]{1,2})?", status_single_input_textField.getText()))
            {
                status_single_input_textField.requestFocusInWindow();
                JOptionPane.showMessageDialog(null, "Product size input invalid !!!");
            }else if(status_singleType_label.getText().equals("uncountable") && Float.parseFloat(status_single_input_textField.getText()) == 0)
            {
                status_single_input_textField.requestFocusInWindow();
                JOptionPane.showMessageDialog(null, "Product size input cannot be empty");
            }else if(status_singleType_label.getText().equals("uncountable") && (Float.parseFloat(status_single_input_textField.getText()) > Float.parseFloat(status_single_maxOut_label.getText())))
            {
                status_single_input_textField.requestFocusInWindow();
                JOptionPane.showMessageDialog(null, "Maximum out limit exceeds");
            }else if(status_singleRecipientName_textField.getText().length() > 50 || status_singleRecipientCard_textField.getText().length() > 50 || (status_singleType_label.getText().equals("uncountable") && status_single_input_textField.getText().length() > 11))
            {
                JOptionPane.showMessageDialog(null, "Recipient Name(50), Card No.(50) and Product size input(11) can't exceed character limit");
            }else{
                String error = "n";
                String sql = "SELECT recipient_cardNo FROM recipients WHERE recipient_name=? LIMIT 1";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, status_singleRecipientName_textField.getText());
                rs = pstmt.executeQuery();
                
                if(rs.next())
                {
                    if(!rs.getString("recipient_cardNo").equals(status_singleRecipientCard_textField.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "This Recipient Name is already exists with card no "+rs.getString("recipient_cardNo"));
                        error = "y";
                    }
                }else{
                   //insert recipient
                    sql = "INSERT INTO recipients (date,recipient_name,recipient_cardNo,recipient_description) ";
                    sql += "VALUES (";
                    sql += "'"+date()+"','"+StringEscapeUtils.escapeSql(status_singleRecipientName_textField.getText())+"','"+StringEscapeUtils.escapeSql(status_singleRecipientCard_textField.getText())+"','"+StringEscapeUtils.escapeSql(status_singleRecipient_textArea.getText())+"')";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                }
                if(error.equals("n"))
                {    //prouct info//product model
                     //Insert uncountable minus if uncountable
                    
                    sql = "SELECT insert_year,model FROM product_id_list WHERE product_id=? LIMIT 1";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, search_box.getText());
                    rs = pstmt.executeQuery();

                    if(rs.next())
                    {   float minus = 0f;
                        if(status_singleType_label.getText().equals("uncountable") )
                        {
                            minus = Float.parseFloat(status_single_input_textField.getText());
                        }
                        sql = "UPDATE product_info_"+rs.getString("insert_year")+" SET year_out='"+year()+"', month_out='"+month()+"', date_out='"+date()+"',time_out='"+time()+"', status_out='Product out', current_status='Product out', minus=minus+"+minus+", delivered_name='"+status_singleRecipientName_textField.getText()+"',delivered_cardNo='"+status_singleRecipientCard_textField.getText()+"',delivered_description='"+status_singleRecipient_textArea.getText()+"' WHERE product_id='"+search_box.getText()+"' AND model='"+rs.getString("model")+"'";
                        pstmt = conn.prepareStatement(sql);
                        int rows = pstmt.executeUpdate();
                                                
                        if(status_singleType_label.getText().equals("uncountable") )
                        {
                            float remaining = Float.parseFloat(status_single_maxOut_label.getText()) - Float.parseFloat(status_single_input_textField.getText());
                            
                            if(status_single_maxOut_label.getText().equals(status_single_input_textField.getText()))
                            {
                                sql = "UPDATE product_model SET total_out=total_out+1 WHERE model='"+rs.getString("model")+"' AND insert_year='"+rs.getString("insert_year")+"'"; 
                            }else if(remaining <= 0)
                            {
                                sql = "UPDATE product_model SET total_out=total_out+1,partially_out=partially_out+1 WHERE model='"+rs.getString("model")+"' AND insert_year='"+rs.getString("insert_year")+"'";
                            }else{
                                sql = "UPDATE product_model SET partially_out=partially_out+1 WHERE model='"+rs.getString("model")+"' AND insert_year='"+rs.getString("insert_year")+"'";
                            }
                           
                            status_single_maxOut_label.setText(String.valueOf(remaining));
                            
                        }else{
                          sql = "UPDATE product_model SET total_out=total_out+1 WHERE model='"+rs.getString("model")+"' AND insert_year='"+rs.getString("insert_year")+"'";
                        }
                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        
                        if(status_singleType_label.getText().equals("uncountable"))
                        {
                            Float cost = (Float.parseFloat(product_price.getText()) * Float.parseFloat(status_single_input_textField.getText()))/Float.parseFloat(status_singleSize_label.getText());
                            sql = "INSERT INTO uncountable_minus (date,time,model,product_id,minus,cost) ";
                            sql += "VALUES (";
                            sql += "'"+date()+"','"+time()+"','"+rs.getString("model")+"','"+search_box.getText()+"','"+status_single_input_textField.getText()+"','"+cost+"')";

                            pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
                        }
                        if(rows > 0)
                        {
                            status_show_data();
                            result_message2.setText("Updated Successfully");
                            result_message2.setForeground(new Color(0,102,0));
                            status_singleRecipientName_textField.setText("");
                            status_singleRecipientCard_textField.setText("");
                            status_singleRecipient_textArea.setText("");
                            status_single_input_textField.setText("");
                            
                        }
                    }
                    search_box.requestFocusInWindow();
                }
            }
        }catch(HeadlessException | NumberFormatException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

        

    }//GEN-LAST:event_checkbox_product_outActionPerformed

    private void entry_createField_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entry_createField_buttonActionPerformed
        // TODO add your handling code here:
        int isYes = 0;
        if(entry_numOfProduct_jtextField.getText().trim().isEmpty() || Integer.parseInt(entry_numOfProduct_jtextField.getText()) == 0 )
        {

        }else if(Integer.parseInt(entry_numOfProduct_jtextField.getText()) > 999)
        {
            JOptionPane.showMessageDialog(null, "Maximum limit 999 at a time");
        }else{
            if(entry_productId_jtable.getHeight() != 0 )
            {
                int response = JOptionPane.showConfirmDialog(null, "Previously created field will be removed, sure ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(response == JOptionPane.YES_OPTION) {
                    entryProductIdTableModel.setRowCount(0);
                    isYes = 1;
                }
                if(response == JOptionPane.NO_OPTION) {
                    isYes = 2;
                }
            }

            if(isYes == 0 || isYes == 1)
            {
                entry_submit_button.setEnabled(true);
                Object[][] resultSet ; 

                if(entry_productType_label.getText().equals("uncountable"))
                {
                   resultSet = new Object[0][3] ;
                   String[] columnNames = {"Product Id","Size","Price"};
                   entryProductIdTableModel = new DefaultTableModel(resultSet,columnNames) {

                        @Override
                        public boolean isCellEditable(int row, int column) {
                            //all cells true
                            return true;
                        }
                    };
                }else{
                   resultSet = new Object[0][1] ;
                   String[] columnNames = {"Product Id"};
                   entryProductIdTableModel = new DefaultTableModel(resultSet,columnNames) {

                        @Override
                        public boolean isCellEditable(int row, int column) {
                            //all cells true
                            return true;
                        }
                    };
                }
                
                
                entry_productId_jtable.setModel(entryProductIdTableModel);

                for(int i = 0;i < Integer.parseInt(entry_numOfProduct_jtextField.getText());i++)
                {
                    if(entry_productType_label.getText().equals("uncountable"))
                    {
                       entryProductIdTableModel.insertRow(i, new Object[]{"","","0"});             
                    }else{
                       entryProductIdTableModel.insertRow(i, new Object[]{""});
                    }
                }
            }
        }
    }//GEN-LAST:event_entry_createField_buttonActionPerformed

    private void entry_numOfProduct_jtextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entry_numOfProduct_jtextFieldKeyReleased
        // TODO add your handling code here:
        boolean b = Pattern.matches("\\d+", entry_numOfProduct_jtextField.getText());

        if(b)
        {

        }else{
            entry_numOfProduct_jtextField.setText("");
        }
    }//GEN-LAST:event_entry_numOfProduct_jtextFieldKeyReleased

    private void entry_product_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entry_product_priceKeyReleased
        // TODO add your handling code here:

        boolean b = Pattern.matches("[0-9]*(\\.[0-9]{1,2})?", entry_product_price.getText());

        if(b)
        {
            entry_price_validate_message.setText("valid");
            entry_price_validate_message.setForeground(new Color(0,102,0));
        }else{
            entry_price_validate_message.setText("invalid");
            entry_price_validate_message.setForeground(Color.RED);
        }
    }//GEN-LAST:event_entry_product_priceKeyReleased

    private void entry_product_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entry_product_nameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            entry_product_price.requestFocusInWindow();
        }
    }//GEN-LAST:event_entry_product_nameKeyPressed

    private void entry_model_comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entry_model_comboBoxActionPerformed
        // TODO add your handling code here:
        Object obj;
        int isYes = 0;
        
        if(entry_productId_jtable.getHeight() != 0 && !entry_modelHidden_label.getText().equals(""))
        {
                int response = JOptionPane.showConfirmDialog(null, "Previously created field will be removed, sure ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(response == JOptionPane.YES_OPTION) {
                    entryProductIdTableModel.setRowCount(0);
                    isYes = 1;
                }
                if(response == JOptionPane.NO_OPTION) {
                    obj = entry_modelHidden_label.getText();
                    entry_model_comboBox.setSelectedItem(obj);
                    isYes = 2;
                }
        }
           
        if(isYes == 0 || isYes == 1)
        {
            entry_modelHidden_label.setText(entry_model_comboBox.getSelectedItem().toString());
            String sql = "SELECT type,name,description,price,remark FROM product_model WHERE model=? ";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, entry_model_comboBox.getSelectedItem().toString());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                obj = rs.getString("remark");
                if(obj.equals(""))
                {
                    obj = "Remark";
                }
                entry_remark_comboBox.setSelectedItem(obj);
                entry_productType_label.setText(rs.getString("type"));
                entry_product_name.setText(rs.getString("name"));
                entry_description_textArea.setText(rs.getString("description"));
                entry_product_price.setText(rs.getString("price"));
                entry_numOfProduct_jtextField.setEnabled(true);
                entry_createField_button.setEnabled(true);
                
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
            }
    }//GEN-LAST:event_entry_model_comboBoxActionPerformed

    private void entry_submit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entry_submit_buttonActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to proceed ?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(response == JOptionPane.YES_OPTION) {

            float price ;

            if(Pattern.matches("[0-9]*(\\.[0-9]{1,2})?", entry_product_price.getText()))
            {
                if(entry_product_price.getText().trim().equals(""))
                {
                    price = 0.0f;
                }else{
                    price = Float.parseFloat(entry_product_price.getText());
                }

                if(entry_product_name.getText().length() > 50 || entry_product_price.getText().length() > 11)
                {
                    JOptionPane.showMessageDialog(null, "Name and Price can't exceed character limit 50 and 11");
                }else if(entry_model_comboBox.getSelectedItem().equals("Model"))
                {
                    JOptionPane.showMessageDialog(null, "Please select a model");
                }else{
                    String sqlCheck ,error = "no";
                    String[] productIdArray = new String[Integer.parseInt(entry_numOfProduct_jtextField.getText())]; 

                    for(int i=0; i<Integer.parseInt(entry_numOfProduct_jtextField.getText());i++)
                    {
                        productIdArray[i] = entry_productId_jtable.getModel().getValueAt(i,0).toString();
                        
                        if(entry_productId_jtable.getModel().getValueAt(i,0).toString().trim().isEmpty())
                        {
                            Rectangle cellRect = entry_productId_jtable.getCellRect(i, 0, false);
                            entry_productId_jtable.scrollRectToVisible(cellRect);
                            entry_productId_jtable.changeSelection(i, 0, false, false);
                            JOptionPane.showMessageDialog(null, "Product id cannot be empty");
                            error = "yes";
                            break;
                        }else if(entry_productType_label.getText().equals("uncountable") && (!Pattern.matches("[0-9]*(\\.[0-9]{1,2})?",entry_productId_jtable.getModel().getValueAt(i,1).toString()) || entry_productId_jtable.getModel().getValueAt(i,1).toString().length() > 11))
                        {
                            Rectangle cellRect = entry_productId_jtable.getCellRect(i, 1, false);
                            entry_productId_jtable.scrollRectToVisible(cellRect);
                            entry_productId_jtable.changeSelection(i, 1, false, false);
                            JOptionPane.showMessageDialog(null, "Invalid product size ! (must be number,char. limit 11)");
                            error = "yes";
                            break;
                        }else if(entry_productType_label.getText().equals("uncountable") && (entry_productId_jtable.getModel().getValueAt(i,1).toString().trim().isEmpty() || Float.parseFloat(entry_productId_jtable.getModel().getValueAt(i,1).toString())==0))
                        {
                            Rectangle cellRect = entry_productId_jtable.getCellRect(i, 1, false);
                            entry_productId_jtable.scrollRectToVisible(cellRect);
                            entry_productId_jtable.changeSelection(i, 1, false, false);
                            JOptionPane.showMessageDialog(null, "Product size cannot be empty");
                            error = "yes";
                            break;
                        }else if(entry_productType_label.getText().equals("uncountable") && entry_productId_jtable.getModel().getValueAt(i,2).toString().trim().isEmpty())
                        {
                            Rectangle cellRect = entry_productId_jtable.getCellRect(i, 2, false);
                            entry_productId_jtable.scrollRectToVisible(cellRect);
                            entry_productId_jtable.changeSelection(i, 2, false, false);
                            JOptionPane.showMessageDialog(null, "Product price cannot be empty");
                            error = "yes";
                            break;
                        }else if(entry_productType_label.getText().equals("uncountable") && (!Pattern.matches("[0-9]*(\\.[0-9]{1,2})?",entry_productId_jtable.getModel().getValueAt(i,2).toString()) || entry_productId_jtable.getModel().getValueAt(i,2).toString().length() > 11))
                        {
                            Rectangle cellRect = entry_productId_jtable.getCellRect(i, 2, false);
                            entry_productId_jtable.scrollRectToVisible(cellRect);
                            entry_productId_jtable.changeSelection(i, 2, false, false);
                            JOptionPane.showMessageDialog(null, "Invalid product price ! (must be number,char. limit 11)");
                            error = "yes";
                            break;
                        }else if(entry_productId_jtable.getModel().getValueAt(i,0).toString().length() > 50)
                        {
                            Rectangle cellRect = entry_productId_jtable.getCellRect(i, 0, false);
                            entry_productId_jtable.scrollRectToVisible(cellRect);
                            entry_productId_jtable.changeSelection(i, 0, false, false);
                            JOptionPane.showMessageDialog(null, "Product id's max. character limit 50");
                            error = "yes";
                            break;
                        }
                        sqlCheck  = "SELECT product_id FROM product_info_"+year()+" WHERE product_id='"+entry_productId_jtable.getModel().getValueAt(i,0).toString()+"' ";
                        try{
                            pstmt = conn.prepareStatement(sqlCheck);
                            rs = pstmt.executeQuery();

                            if(rs.next())
                            {
                                Rectangle cellRect = entry_productId_jtable.getCellRect(i, 0, false);
                                entry_productId_jtable.scrollRectToVisible(cellRect);
                                entry_productId_jtable.changeSelection(i, 0, false, false);
                                JOptionPane.showMessageDialog(null, "Product id: "+entry_productId_jtable.getModel().getValueAt(i,0).toString()+" already exists ");
                                error = "yes";
                                break;
                            }
                        }catch(SQLException | HeadlessException e)
                        {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                    if(error.equals("no"))
                    {  
                        for (int i = 0; i < productIdArray.length-1; i++)
                        {
                            for (int j = i+1; j < productIdArray.length; j++)
                            {
                                if( (productIdArray[i].equals(productIdArray[j])) && (i != j) )
                                {
                                    Rectangle cellRect = entry_productId_jtable.getCellRect(i, 0, false);
                                    entry_productId_jtable.scrollRectToVisible(cellRect);
                                    entry_productId_jtable.changeSelection(i, 0, false, false);
                                    JOptionPane.showMessageDialog(null,"Duplicate product id found : "+productIdArray[i]);
                                    error = "yes";
                                    break;
                                }
                            }
                        }
                        if(error.equals("no"))
                        { entry_insert_data(price); }
                    }
                }

            }else{
                JOptionPane.showMessageDialog(null, "Invalid Price");
            }

        }
    }//GEN-LAST:event_entry_submit_buttonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to exit ?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(response == JOptionPane.YES_OPTION) {
             System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void barcode_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcode_buttonActionPerformed
        // TODO add your handling code here:
        barcode_button.setEnabled(false);
        entry_button.setEnabled(true);
        statistics_button.setEnabled(true);
        status_button.setEnabled(true);
        home_button.setEnabled(true);

        parent_panel.removeAll();
        parent_panel.add(barcode_panel);
        parent_panel.repaint();
        parent_panel.revalidate();
    }//GEN-LAST:event_barcode_buttonActionPerformed

    private void stat_dateToDeliveredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stat_dateToDeliveredActionPerformed
        // TODO add your handling code here:
        String sql;
        
         sql = "SELECT DISTINCT delivered_cardNo,delivered_name FROM product_info_"+formatter.format(stat_dateToDelivered.getDate()).substring(0, 4)+" WHERE date_out='"+formatter.format(stat_dateToDelivered.getDate())+"' ORDER BY delivered_name ";

         try{
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();               
                if(rs.next())
                {
                    stat_deliveredName_comboBox.setEnabled(true);                   
                    stat_deliveredName_comboBox.setModel(new DefaultComboBoxModel());
                    stat_deliveredName_comboBox.addItem("Name");
   
                    stat_deliveredName_comboBox.addItem(rs.getString("delivered_name"));
                }
                while(rs.next())
                {
                    stat_deliveredName_comboBox.addItem(rs.getString("delivered_name"));
                }

         }catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e);
         }
    }//GEN-LAST:event_stat_dateToDeliveredActionPerformed

    private void stat_deliveredName_comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stat_deliveredName_comboBoxActionPerformed
        // TODO add your handling code here:
        if(stat_table.getWidth() != 0)
        {
            // statTableModel.setColumnCount(0);
             statTableModel.setRowCount(0);            
        }
        stat_search_choice = 3;
        statTable_row = 0;
        limitStartForDeliveredNameBox = 20;
        itemCount = 0;
        
        if(!stat_deliveredName_comboBox.getSelectedItem().equals("Name"))
          stat_display_result_in_table("deliveredTo");
             
    }//GEN-LAST:event_stat_deliveredName_comboBoxActionPerformed

    private void status_multi_input_textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_multi_input_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_status_multi_input_textFieldActionPerformed

    
    private void stat_display_result_in_table(String option)
    {
            try{
                 String search_mode="",orderBy="";
                switch (option) 
                {
                    case "modelComboBox":
                        stat_sql2 = "SELECT COUNT(product_id) AS 'total_product',SUM(price) AS 'total_price' FROM product_info_"+stat_year_comboBox.getSelectedItem()+" WHERE model='"+stat_product_model_comboBox.getSelectedItem()+"' ";
                        stat_sql = "SELECT model,product_id,name,current_status,date_arrival,date_in,date_out,price FROM product_info_"+stat_year_comboBox.getSelectedItem()+" WHERE model='"+stat_product_model_comboBox.getSelectedItem()+"' ORDER BY "+choiceOrderBy(stat_model_orderByComboBox.getSelectedItem().toString())+"  ";
                        search_mode = "Product Model";
                        orderBy = stat_model_orderByComboBox.getSelectedItem().toString();
                        break;
                    case "accordingStatus":
                        String twoYear = formatter.format(stat_toDate4.getDate()).substring(0,4);
                        String statusComboBox = stat_status_comboBox.getSelectedItem().toString();
                        stat_sql2 = "SELECT COUNT(product_id) AS 'total_product',SUM(price) AS 'total_price' FROM product_info_"+twoYear+" WHERE "+choiceStatusDate(statusComboBox)+" BETWEEN '"+formatter.format(stat_fromDate3.getDate())+"'  AND '"+formatter.format(stat_toDate4.getDate())+"' ";
                        stat_sql = "SELECT model,product_id,name,current_status,date_arrival,date_in,date_out,price FROM product_info_"+twoYear+" WHERE "+choiceStatusDate(statusComboBox)+" BETWEEN '"+formatter.format(stat_fromDate3.getDate())+"'  AND '"+formatter.format(stat_toDate4.getDate())+"' ORDER BY "+choiceOrderBy(stat_status_orderByComboBox.getSelectedItem().toString())+"  ";
                        search_mode = "Product Status";
                        orderBy = stat_status_orderByComboBox.getSelectedItem().toString();
                        break;
                    case "deliveredTo":
                        stat_sql2 = "SELECT COUNT(product_id) AS 'total_product',SUM(price) AS 'total_price' FROM product_info_"+formatter.format(stat_dateToDelivered.getDate()).substring(0, 4)+" WHERE date_out='"+formatter.format(stat_dateToDelivered.getDate())+"'  AND  delivered_name='"+stat_deliveredName_comboBox.getSelectedItem()+"' ";
                        stat_sql = "SELECT model,product_id,name,current_status,date_arrival,date_in,date_out,price FROM product_info_"+formatter.format(stat_dateToDelivered.getDate()).substring(0, 4)+" WHERE  date_out='"+formatter.format(stat_dateToDelivered.getDate())+"'  AND  delivered_name='"+stat_deliveredName_comboBox.getSelectedItem()+"'  ORDER BY "+choiceOrderBy(stat_deliveredTo_orderByComboBox.getSelectedItem().toString())+"  ";
                        search_mode = "Delivered Recipient";
                        orderBy = stat_deliveredTo_orderByComboBox.getSelectedItem().toString();
                        break;
                }

             pstmt = conn.prepareStatement(stat_sql2);
             rs = pstmt.executeQuery();
             if(rs.next())
             {
                 total_product = rs.getString("total_product");
                 total_price = rs.getString("total_price");
             }
             pstmt = conn.prepareStatement(stat_sql+" LIMIT 0,20");                      
             rs = pstmt.executeQuery();
                
             
             String columnNames[] = {"Product Model", "Id", "Name", "Current Status","On Arrival Date","In Date","Out Date","Price"};
	     
             int found = 0 ;
             Object[][] resultSet = new Object[20][8] ;
               
             
             if(stat_table.getWidth() == 0)
             {
                 
                while(rs.next())
                {      
                     for (int i = 0; i < 8; i++) {
                        resultSet[statTable_row][i] = rs.getObject(i+1);
                     }
                        statTable_row++;
                      found = 1;
                      itemCount++;
                }
                 if(found == 1)
                 {
                        statTableModel = new DefaultTableModel(resultSet, columnNames) {

                                    @Override
                                    public boolean isCellEditable(int row, int column) {
                                       //all cells false
                                       return false;
                                    }
                                };
                        stat_table.setModel(statTableModel);
                        
                        setJTableColumnsWidth(stat_table, 1024,100,100,100,100,100,100,100,100);
                       
                        for(int j=19;j>=itemCount;j--)
                        {
                             statTableModel.removeRow(j);
                        }
                 }
             }else if(rs != null){
                      
                    int decrease = statTable_row-1;
                    int findOut = 0;
                        while(rs.next())
                        {       
                            for(int j=decrease;j>=0;j--)
                            {
                               if(statTableModel.getValueAt(j, 0).equals(rs.getObject(1)) )
                               {
                                  findOut = 1;
                               }
                            }
                            if(findOut == 0)
                            {
                                statTableModel.insertRow(statTable_row++, new Object[] { rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8) });
                                itemCount++;
                            }                      
                        }                 
                
             }
              stat_itemShows_message.setText(itemCount+"  on  "+total_product);
              stat_label_searchMode.setText(search_mode);
              stat_label_orderBy.setText(orderBy);
              stat_label_totalProduct.setText(total_product);
              stat_label_totalPrice.setText(total_price);

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void choiceMethod(int ch)
    {
         switch(ch)
         {
             case 1:
                 stat_model_comboBox_scrollLoad();
                 break;
             case 2:
                 stat_status_scrollLoad();
                 break;
             case 3:
                 stat_deliveredTo_scrollLoad();
                 break;
             default:
                 break;
         }
    
    }
    
    private String choiceStatusDate(String ch)
    {
         switch(ch)
         {
             case "Product On Arrival":
                 return "date_arrival";
                 
             case "Product In":
                 return "date_in";
                 
             case "Product Out":
                 return "date_out";
                 
             default:
                 break;
         }
         return "";
    } 
    
    private String choiceOrderBy(String ch)
    {
         switch(ch)
         {
             case "price ascending":
                 return "price ASC";
                 
             case "price descending":
                 return "price DESC";
                             
             default:
                 return "LENGTH("+ch+"),"+ch+"";
                 
         }        
    }

   
    private void stat_model_comboBox_scrollLoad()
    {
        try{
            
            if(limitStartForModelCombobox <= Integer.parseInt(total_product))
            {
                String sql = "SELECT model,product_id,name,current_status,date_arrival,date_in,date_out,price FROM product_info_"+stat_year_comboBox.getSelectedItem()+" WHERE model='"+stat_product_model_comboBox.getSelectedItem()+"' ORDER BY "+choiceOrderBy(stat_model_orderByComboBox.getSelectedItem().toString())+"  LIMIT "+limitStartForModelCombobox+", 15";
                limitStartForModelCombobox += 15;
                pstmt = conn.prepareStatement(sql);                      
                rs = pstmt.executeQuery();
                
                   while(rs.next())
                   {                                          
                     statTableModel.insertRow(statTable_row++, new Object[] { rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8) });         
                     itemCount++;
                   }

                stat_itemShows_message.setText(itemCount+"  on  "+total_product);    
            }
        }catch(NumberFormatException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void stat_status_scrollLoad()
    {
        try{
            
            if(limitStartForStatusBox <= Integer.parseInt(total_product))
            {
              String twoYear = formatter.format(stat_toDate4.getDate()).substring(0,4);
              String statusComboBox = stat_status_comboBox.getSelectedItem().toString();
              String sql = "SELECT model,product_id,name,current_status,date_arrival,date_in,date_out,price FROM product_info_"+twoYear+" WHERE "+choiceStatusDate(statusComboBox)+" BETWEEN '"+formatter.format(stat_fromDate3.getDate())+"'  AND '"+formatter.format(stat_toDate4.getDate())+"' ORDER BY "+choiceOrderBy(stat_status_orderByComboBox.getSelectedItem().toString())+"  LIMIT "+limitStartForStatusBox+", 15";
              limitStartForStatusBox += 15;
              pstmt = conn.prepareStatement(sql);                      
              rs = pstmt.executeQuery();
               
                while(rs.next())
                {                                          
                  statTableModel.insertRow(statTable_row++, new Object[] { rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8) }); 
                  itemCount++;
                }
            
              stat_itemShows_message.setText(itemCount+"  on  "+total_product);
            }
        }catch(NumberFormatException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void stat_deliveredTo_scrollLoad()
    {
        try{
            
            if(limitStartForDeliveredNameBox <= Integer.parseInt(total_product))
            {
                String sql = "SELECT model,product_id,name,current_status,date_arrival,date_in,date_out,price FROM product_info_"+stat_year_comboBox.getSelectedItem()+" WHERE  date_out='"+formatter.format(stat_dateToDelivered.getDate())+"'  AND  delivered_name='"+stat_deliveredName_comboBox.getSelectedItem()+"'  ORDER BY "+choiceOrderBy(stat_deliveredTo_orderByComboBox.getSelectedItem().toString())+"  LIMIT "+limitStartForDeliveredNameBox+", 15";
                limitStartForDeliveredNameBox += 15;
                pstmt = conn.prepareStatement(sql);                      
                rs = pstmt.executeQuery();
                
                   while(rs.next())
                   {                                          
                     statTableModel.insertRow(statTable_row++, new Object[] { rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8) });         
                     itemCount++;
                   }

                stat_itemShows_message.setText(itemCount+"  on  "+total_product);    
            }
        }catch(NumberFormatException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void summaryOfToday()
    {
           String sql1,sql2,sql3;
           if(review_model_comboBox.getSelectedItem().equals("All"))
           {
               sql1 = "SELECT COUNT(product_id) AS 'numberOfProduct', SUM(price) AS 'transaction' From product_info_"+year()+"  WHERE date_arrival='"+date()+"' AND status_arrival='On arrival' ";
               sql2 = "SELECT COUNT(product_id) AS 'numberOfProduct', SUM(price) AS 'transaction' From product_info_"+year()+"  WHERE date_in='"+date()+"'      AND status_in='Product in' ";
               sql3 = "SELECT COUNT(product_id) AS 'numberOfProduct', SUM(price) AS 'transaction' From product_info_"+year()+"  WHERE date_out='"+date()+"'     AND status_out='Product out' ";
           }else{
               sql1 = "SELECT COUNT(product_id) AS 'numberOfProduct', SUM(price) AS 'transaction' From product_info_"+year()+"  WHERE  model='"+review_model_comboBox.getSelectedItem().toString()+"'  AND   date_arrival='"+date()+"' AND status_arrival='On arrival' ";
               sql2 = "SELECT COUNT(product_id) AS 'numberOfProduct', SUM(price) AS 'transaction' From product_info_"+year()+"  WHERE  model='"+review_model_comboBox.getSelectedItem().toString()+"'  AND   date_in='"+date()+"'      AND status_in='Product in' ";
               sql3 = "SELECT COUNT(product_id) AS 'numberOfProduct', SUM(price) AS 'transaction' From product_info_"+year()+"  WHERE  model='"+review_model_comboBox.getSelectedItem().toString()+"'  AND   date_out='"+date()+"'     AND status_out='Product out' ";
           }
           
           try{
               
               pstmt = conn.prepareStatement(sql1);
               rs = pstmt.executeQuery();
               while(rs.next())
               {
                   review_numOfProductArrival_label.setText(rs.getString("numberOfProduct"));
                   review_arrivalTransaction_label.setText(rs.getString("transaction"));
               }
               pstmt = conn.prepareStatement(sql2);
               rs = pstmt.executeQuery();
               while(rs.next())
               {
                   review_numOfProductIn_label.setText(rs.getString("numberOfProduct"));
                   review_inTransaction_label.setText(rs.getString("transaction"));
               }
               pstmt = conn.prepareStatement(sql3);
               rs = pstmt.executeQuery();
               while(rs.next())
               {
                   review_numOfProductOut_label.setText(rs.getString("numberOfProduct"));
                   review_outTransaction_label.setText(rs.getString("transaction"));
               }
           
           }catch(Exception e)
           {
               JOptionPane.showMessageDialog(null, e);
           }
    }

    
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, 
    		double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }
        
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)
                    (tablePreferredWidth * (percentages[i] / total)));
        }
    }
    
    
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IMS_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new IMS_form().setVisible(true);
        });
        
        
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem addModel_jMenuItem;
    private javax.swing.JMenuItem addRemark_jMenuitem;
    private javax.swing.JButton barcode_button;
    public javax.swing.JComboBox barcode_model_comboBox;
    private javax.swing.JPanel barcode_panel;
    private javax.swing.JCheckBox checkbox_product_in;
    private javax.swing.JCheckBox checkbox_product_out;
    private javax.swing.JLabel date_arrive;
    private javax.swing.JLabel date_in;
    private javax.swing.JLabel date_out;
    private javax.swing.JButton entry_button;
    private javax.swing.JButton entry_createField_button;
    private javax.swing.JTextArea entry_description_textArea;
    private javax.swing.JLabel entry_modelHidden_label;
    public javax.swing.JComboBox entry_model_comboBox;
    private javax.swing.JTextField entry_numOfProduct_jtextField;
    private javax.swing.JPanel entry_panel;
    private javax.swing.JLabel entry_price_validate_message;
    private javax.swing.JScrollPane entry_producIdTextField_jScrollPane;
    private javax.swing.JTable entry_productId_jtable;
    private javax.swing.JLabel entry_productType_label;
    private javax.swing.JTextField entry_product_name;
    private javax.swing.JTextField entry_product_price;
    public javax.swing.JComboBox entry_remark_comboBox;
    private javax.swing.JLabel entry_result_message;
    private javax.swing.JButton entry_submit_button;
    private javax.swing.JButton home_button;
    private javax.swing.JPanel home_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel parent_panel;
    private javax.swing.JLabel product_current_status;
    private javax.swing.JLabel product_id;
    private javax.swing.JLabel product_model;
    private javax.swing.JLabel product_name;
    private javax.swing.JLabel product_price;
    private javax.swing.JLabel result_message;
    private javax.swing.JLabel result_message2;
    private javax.swing.JLabel review_arrivalTransaction_label;
    private javax.swing.JLabel review_inTransaction_label;
    public javax.swing.JComboBox review_model_comboBox;
    private javax.swing.JLabel review_numOfProductArrival_label;
    private javax.swing.JLabel review_numOfProductIn_label;
    private javax.swing.JLabel review_numOfProductOut_label;
    private javax.swing.JLabel review_outTransaction_label;
    private javax.swing.JTextField search_box;
    private org.jdesktop.swingx.JXDatePicker stat_dateToDelivered;
    private javax.swing.JComboBox stat_deliveredName_comboBox;
    private javax.swing.JComboBox stat_deliveredTo_orderByComboBox;
    private org.jdesktop.swingx.JXDatePicker stat_fromDate3;
    private javax.swing.JLabel stat_itemShows_message;
    private javax.swing.JLabel stat_label_orderBy;
    private javax.swing.JLabel stat_label_searchMode;
    private javax.swing.JLabel stat_label_totalPrice;
    private javax.swing.JLabel stat_label_totalProduct;
    private javax.swing.JComboBox stat_model_orderByComboBox;
    public javax.swing.JComboBox stat_product_model_comboBox;
    private javax.swing.JButton stat_report_button;
    private javax.swing.JComboBox stat_status_comboBox;
    private javax.swing.JComboBox stat_status_orderByComboBox;
    private javax.swing.JTable stat_table;
    private javax.swing.JScrollPane stat_table_scrollpane;
    private org.jdesktop.swingx.JXDatePicker stat_toDate4;
    private javax.swing.JComboBox stat_year_comboBox;
    private javax.swing.JButton statistics_button;
    private javax.swing.JPanel statistics_panel;
    private javax.swing.JLabel status_arrive;
    private javax.swing.JButton status_button;
    private javax.swing.JLabel status_in;
    public javax.swing.JComboBox status_model_comboBox;
    private javax.swing.JTextArea status_multiDescription_textarea;
    private javax.swing.JTextField status_multiRecipientCard_textField;
    private javax.swing.JTextField status_multiRecipientName_textField;
    private javax.swing.JTextArea status_multiRecipient_textArea;
    private javax.swing.JLabel status_multi_finalResult_label;
    private javax.swing.JButton status_multi_inputSubmit_button;
    private javax.swing.JTextField status_multi_input_textField;
    private javax.swing.JLabel status_multi_maxOut_label;
    private javax.swing.JCheckBox status_multi_productIn_checkBox;
    private javax.swing.JCheckBox status_multi_productOut_checkBox;
    private javax.swing.JLabel status_multi_productType_label;
    private javax.swing.JLabel status_multi_product_in_label;
    private javax.swing.JLabel status_multi_product_model_label;
    private javax.swing.JLabel status_multi_product_name_label;
    private javax.swing.JLabel status_multi_product_out_label;
    private javax.swing.JLabel status_multi_product_partiallyOut_label;
    private javax.swing.JLabel status_multi_product_price_label;
    private javax.swing.JLabel status_multi_product_quantity_label;
    private javax.swing.JLabel status_multi_searchResult_label;
    private javax.swing.JLabel status_multi_validate_label;
    private javax.swing.JLabel status_multi_year_label;
    private javax.swing.JLabel status_out;
    private javax.swing.JPanel status_panel;
    private javax.swing.JButton status_report_button;
    private javax.swing.JButton status_report_button2;
    private javax.swing.JTextArea status_singleDescription_textarea;
    private javax.swing.JTextField status_singleRecipientCard_textField;
    private javax.swing.JLabel status_singleRecipientInfo_label;
    private javax.swing.JLabel status_singleRecipientInfo_label1;
    private javax.swing.JTextField status_singleRecipientName_textField;
    private javax.swing.JTextArea status_singleRecipient_textArea;
    private javax.swing.JLabel status_singleSize_label;
    private javax.swing.JLabel status_singleType_label;
    private javax.swing.JTextField status_single_input_textField;
    private javax.swing.JLabel status_single_maxOut_label;
    private javax.swing.JLabel status_single_maxOut_label1;
    private javax.swing.JComboBox status_year_comboBox;
    private javax.swing.JLabel time_arrive;
    private javax.swing.JLabel time_in;
    private javax.swing.JLabel time_out;
    private javax.swing.JLabel username_label;
    // End of variables declaration//GEN-END:variables
}
