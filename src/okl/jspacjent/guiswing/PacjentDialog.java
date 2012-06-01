/*
 * PacjentUI.java
 *
 * Created on 11 grudzie� 2006, 15:27
 */
package okl.jspacjent.guiswing;

import java.text.*;
import javax.swing.*;

import okl.jspacjent.model.*;

/**
 *
 * @author  JS
 */
public class PacjentDialog extends javax.swing.JDialog {
  /** A return status code - returned if Cancel button has been pressed */
  public static final int RET_CANCEL = 0;
  /** A return status code - returned if OK button has been pressed */
  public static final int RET_OK = 1;  
  
  /** @return the return status of this dialog - 
   *          one of RET_OK or RET_CANCEL          */
  public int getReturnStatus() {
    return returnStatus;
  }  
  
  public void setPacjentPanel( PacjentJPanel pacjentPanel ) {
    this.pacjentPanel = pacjentPanel;  
  }
  
  public PacjentJPanel getPacjentPanel() {        
    return this.pacjentPanel;
  }
  
  public void updateComponents() {
    /*
    this.okButton.setEnabled( false );
    if ( getPacjentPanel().getPacjent() == null ) {
       return;
    }       
    if ( getPacjentPanel().getNazwiskoField().getText() == null ) {
      return;
    }    
    if ( getPacjentPanel().getNazwiskoField().getText().equals("") ) {
       return;
    }    
    else {
      this.okButton.setEnabled( true );
    }
     */
  }
  
  public PacjentJPanel getOrCreatePacjentPanel() {
    if ( getPacjentPanel() == null ) {
      setPacjentPanel( new PacjentJPanel() );
    }    
    return this.pacjentPanel;
  }      
  
  /** Creates new form PacjentUI */
  public PacjentDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);    
    initComponents();
    updateComponents();
  } 
  
  /** Creates new form PacjentUI */
  public PacjentDialog(java.awt.Frame parent, 
                       boolean modal,                         
                       PacjentJPanel pacjentPanel ) {
    super(parent, modal);    
    setPacjentPanel( pacjentPanel );    
    initComponents();   
    updateComponents();
  }
  
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    pacjentPanel = getOrCreatePacjentPanel();
    cancelButton = new javax.swing.JButton();
    okButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Edycja danych osobowych pacjenta");
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    setLocationByPlatform(true);
    setModal(true);
    setName("dialogPacjent");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    cancelButton.setFont(new java.awt.Font("Tahoma", 0, 14));
    cancelButton.setForeground(new java.awt.Color(102, 0, 0));
    cancelButton.setText("Cancel");
    cancelButton.setToolTipText("Nie zapisuj wprowadzonych zmian");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelButtonActionPerformed(evt);
      }
    });

    okButton.setFont(new java.awt.Font("Tahoma", 0, 14));
    okButton.setForeground(new java.awt.Color(0, 102, 51));
    okButton.setText("OK");
    okButton.setToolTipText("Zapisz wprowadzone zmiany");
    okButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        okButtonActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
            .addContainerGap()
            .add(pacjentPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(6, 6, 6)
        .add(pacjentPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(okButton)
          .add(cancelButton))
        .addContainerGap(12, Short.MAX_VALUE))
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents
  
  private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    if ( getPacjentPanel().getNazwiskoField().getText().equals("") ) {
      JOptionPane.showMessageDialog(
        null, "Wpisz najpierw dane pacjenta."
        , "Info", JOptionPane.INFORMATION_MESSAGE );  
      return;
    }
    doClose(RET_OK);    
  }//GEN-LAST:event_okButtonActionPerformed
  
  private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    doClose(RET_CANCEL);
  }//GEN-LAST:event_cancelButtonActionPerformed
  
  /** Closes the dialog */
  private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
    doClose(RET_CANCEL);
  }//GEN-LAST:event_closeDialog
  
  private void doClose(int retStatus) {
    if ( retStatus == RET_OK ) {
      try {
        getPacjentPanel().updatePacjentBean();
        getPacjentPanel().saveOrUpdatePacjent();
      }
      catch (Exception e) {
        System.out.println(e);
      }
    }
    returnStatus = retStatus;
    setVisible(false);
    dispose();    
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new PacjentDialog(
          new javax.swing.JFrame(), true, 
                                    new PacjentJPanel()                                            
        ).setVisible(true);
      }
    });    
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cancelButton;
  private javax.swing.JButton okButton;
  private okl.jspacjent.guiswing.PacjentJPanel pacjentPanel;
  // End of variables declaration//GEN-END:variables
  
  private int returnStatus = RET_CANCEL;       
  
}