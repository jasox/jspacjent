/*
 * PrzypacJPanel.java
 *
 * Created on 28 grudzieÒ 2006, 21:33
 */

package okl.jspacjent.guiswing;

import java.util.*;
import javax.swing.*;
import java.io.*;

import okl.jspacjent.model.*;
import okl.jspacjent.dao.*;
import okl.jspacjent.utility.*;

/**
 * Panel jednoczesnej edycji przyjÍcia i pacjenta  
 *
 * @author  janusz.swol@lot.pl
 */
public class PrzypacJPanel extends javax.swing.JPanel {  
  /** A return status code - returned if still edited and not updated */
  public static final int RET_EDIT = -1;
  /** A return status code - returned if Cancel button has been pressed */
  public static final int RET_CANCEL = 0;
  /** A return status code - returned if OK button has been pressed */
  public static final int RET_OK = 1;      
  /** A return status code - returned if data has been updated */
  public static final int RET_UPDATED = 2;
  
  /** return status of this panel */
  private int returnStatus = RET_EDIT;   
  
  /** czy edytujemy ca≥kiem nowe przyjÍcie */
  private boolean nowePrzyjecie = true;
  
  /** czy dane zosta≥y juø zapisane do bazy danych */
  private boolean updated = false;
  
  
  /** data access object for this application */
  private okl.jspacjent.dao.DAO dao;
  
  /** utility class for this application */
  private Utilities util;  
  
  // Access methods
  
  public void setDao( DAO dao ) {
    this.dao = dao;
  }
  
  public DAO getDao() {
    return dao;
  }
  
  public Utilities getUtil() {
    return util;
  }

  public void setUtil(Utilities util) {
    this.util = util;
  }
  
  public boolean isNowePrzyjecie() {
    return this.nowePrzyjecie;
  }
  
  public void setNowePrzyjecie( boolean nowePrzyjecie ) {
    this.nowePrzyjecie = nowePrzyjecie;
  }
  
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
  
  public PacjentJPanel getOrCreatePacjentPanel() {
    if ( getPacjentPanel() == null ) {
      setPacjentPanel( new PacjentJPanel() );
    }    
    return this.pacjentPanel;
  }
  
  public void setPrzyjeciePanel( PrzyjecieJPanel przyjeciePanel ) {
    this.przyjeciePanel = przyjeciePanel;  
  }
  
  public PrzyjecieJPanel getPrzyjeciePanel() {        
    return this.przyjeciePanel;
  }
  
  public PrzyjecieJPanel getOrCreatePrzyjeciePanel() {
    if ( getPrzyjeciePanel() == null ) {
      setPrzyjeciePanel( new PrzyjecieJPanel() );
    }    
    return this.przyjeciePanel;
  } 
  
  public void updateComponents() {
    // gdy nie ma co edytowaÊ
    if ( getPrzyjeciePanel().getPrzyjecie() != null ) {
      if ( nowePrzyjecie ) {
        pacjentButton.setEnabled( true );
        badaniaButton.setEnabled( true );
        leczenieButton.setEnabled( true );
        raportButton.setEnabled( false );
      }  
      else {
        pacjentButton.setEnabled( false );
        badaniaButton.setEnabled( true );
        leczenieButton.setEnabled( true );
        raportButton.setEnabled( true );
      }
      okButton.setEnabled( true );
      cancelButton.setEnabled( true );
    }
    else {
      pacjentButton.setEnabled( false );
      badaniaButton.setEnabled( false );
      leczenieButton.setEnabled( false );
      raportButton.setEnabled( false );
      okButton.setEnabled( false );
      cancelButton.setEnabled( false );
    }      
  }
  
  /** */
  public void requestFocus() {
    getPrzyjeciePanel().requestFocus();
  } 
  
  /** Creates new form PrzypacJPanel */
  public PrzypacJPanel() {
    initComponents();
    updateComponents();
  }
  
   /** Creates new form PrzypacJPanel */
  public PrzypacJPanel( PrzyjecieJPanel przyjeciePanel,
                        PacjentJPanel   pacjentPanel,
                        boolean         nowePrzyjecie ) {           
    setPacjentPanel( pacjentPanel );
    setPrzyjeciePanel( przyjeciePanel );           
    setNowePrzyjecie( nowePrzyjecie ); // czy to nowe przyjÍcie
    setDao( getPrzyjeciePanel().getDao() );    
    initComponents();
    updateComponents();
    getPrzyjeciePanel().requestFocus();
  }
  
   /** Creates new form PrzypacJPanel */
  public PrzypacJPanel( PrzyjecieJPanel przyjeciePanel,
                        PacjentJPanel   pacjentPanel,
                        boolean         nowePrzyjecie,
                        Utilities       util ) {           
    setPacjentPanel( pacjentPanel );
    setPrzyjeciePanel( przyjeciePanel );           
    setNowePrzyjecie( nowePrzyjecie ); // czy to nowe przyjÍcie
    setDao( getPrzyjeciePanel().getDao() );
    setUtil( util );
    initComponents();
    updateComponents();
    getPrzyjeciePanel().requestFocus();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        przyjeciePanel = getOrCreatePrzyjeciePanel();
        pacjentPanel = getOrCreatePacjentPanel();
        buttonPanel = new javax.swing.JPanel();
        pacjentButton = new javax.swing.JButton();
        badaniaButton = new javax.swing.JButton();
        leczenieButton = new javax.swing.JButton();
        raportButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        buttonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"))); // NOI18N

        pacjentButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pacjentButton.setText("Wybierz osobÍ");
        pacjentButton.setToolTipText("wybÛr danych osobowych pacjenta dla nowego przyjÍcia");
        pacjentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pacjentButtonActionPerformed(evt);
            }
        });

        badaniaButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        badaniaButton.setText("Badania");
        badaniaButton.setToolTipText("badania dla tego przyjÍcia");
        badaniaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                badaniaButtonActionPerformed(evt);
            }
        });

        leczenieButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        leczenieButton.setText("Leczenie");
        leczenieButton.setToolTipText("leczenie dla tego przyjÍcia");
        leczenieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leczenieButtonActionPerformed(evt);
            }
        });

        raportButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        raportButton.setText("Raport");
        raportButton.setToolTipText("zapisz i wyúwietl dane dla tego przyjÍcia w formacie do druku");
        raportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raportButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(102, 0, 0));
        cancelButton.setText("Cancel");
        cancelButton.setToolTipText("zamknij i nie zapisuj wprowadzonych danych");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        okButton.setForeground(new java.awt.Color(0, 102, 102));
        okButton.setText("OK");
        okButton.setToolTipText("zamknij i zapisz wprowadzone dane");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout buttonPanelLayout = new org.jdesktop.layout.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonPanelLayout.createSequentialGroup()
                .add(buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, pacjentButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, badaniaButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, leczenieButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, raportButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(pacjentButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(badaniaButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(leczenieButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(raportButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 235, Short.MAX_VALUE)
                .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pacjentPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(przyjeciePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(przyjeciePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(pacjentPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 254, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void raportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raportButtonActionPerformed
    // wpisanie aktualnych danych
    doClose( RET_UPDATED );
    updated = true;
    // wyúwietlanie raportu
    try {
      String str;
      str = getUtil().headWithTitle("PrzyjÍcie pacjenta");
      str += "<b><center>Raport z przyjÍcia pacjenta</center></b><hr>\n";
      str += getPrzyjeciePanel().getPrzyjecie().toStringHtml();      
      str += "</body></html>";
      TextFile.write( 
        getUtil().getPathToApplication() + "/przyjecie.html", str);
      String command = 
        getUtil().getPathToViewer() + " " +
        getUtil().getPathToApplication() + "/przyjecie.html";    
      java.lang.Runtime.getRuntime().exec(command);
    }
    catch ( IOException e ) {
      JOptionPane.showMessageDialog(
        null, 
        "B≥πd w wyúwietleniu danych.\n" + e.getMessage(), 
        "B≥πd!", JOptionPane.ERROR_MESSAGE );
    }    
  }//GEN-LAST:event_raportButtonActionPerformed

  private void pacjentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pacjentButtonActionPerformed
    // WybÛr pacjenta            
    PacwybJPanel wybPanel = 
      new PacwybJPanel( getDao().getAllPacjent(), getDao() );    
    PacwybJDialog wybDialog = 
      new PacwybJDialog(null, true, wybPanel);
    wybDialog.setVisible(true); 
    // Ustawienie pacjenta dla przyjÍcia
    if ( wybDialog.getReturnStatus() == RET_OK ) {    
      Pacjent pacjent = wybPanel.getPacjent();
      if ( pacjent != null ) {
         getPrzyjeciePanel().getPrzyjecie().setPacjent( pacjent );
         getPacjentPanel().setPacjent( pacjent );
         getPacjentPanel().updateComponents();
      }      
    }
  }//GEN-LAST:event_pacjentButtonActionPerformed

  private void leczenieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leczenieButtonActionPerformed
    // edycja leczeÒ dla przyjecia    
    if ( getPacjentPanel().getNazwiskoField().getText().equals("") ) {
      JOptionPane.showMessageDialog(
        null, "Wpisz najpierw dane pacjenta."
        , "Info", JOptionPane.INFORMATION_MESSAGE );  
      return;
    }
    // gdy nowe przyjÍcie nie jest wpisane do bazy danych
    Przyjecie przyjecie = getPrzyjeciePanel().getPrzyjecie();
    if ( ( nowePrzyjecie ) && ( !updated ) ) { 
      doClose( RET_UPDATED );
      updated = true;
    }
    //
    Set leczenia = przyjecie.getLeczenies();
    LeczenieJPanel leczeniePanel = 
      new LeczenieJPanel( przyjecie, leczenia, getDao() );    
    LeczenieJDialog leczenieDialog = 
      new LeczenieJDialog(null, true, leczeniePanel);
    leczenieDialog.setTitle( przyjecie.toStringShort() );
    leczenieDialog.setVisible(true); 
  }//GEN-LAST:event_leczenieButtonActionPerformed

  private void badaniaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_badaniaButtonActionPerformed
    // edycja badaÒ dla przyjecia    
    if ( getPacjentPanel().getNazwiskoField().getText().equals("") ) {
      JOptionPane.showMessageDialog(
        null, "Wpisz najpierw dane pacjenta."
        , "Info", JOptionPane.INFORMATION_MESSAGE );  
      return;
    }
    // gdy nowe przyjÍcie nie jest wpisane do bazy danych
    Przyjecie przyjecie = getPrzyjeciePanel().getPrzyjecie();
    if ( ( nowePrzyjecie ) && ( !updated ) ) { 
      doClose( RET_UPDATED );
      updated = true;
    }
    //
    Set badania = przyjecie.getBadanies();
    BadanieJPanel badaniePanel = 
      new BadanieJPanel( przyjecie, badania, getDao() );    
    BadanieJDialog badanieDialog = 
      new BadanieJDialog(null, true, badaniePanel);
    badanieDialog.setTitle( przyjecie.toStringShort() );
    badanieDialog.setVisible(true); 
  }//GEN-LAST:event_badaniaButtonActionPerformed

  private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    doClose( RET_CANCEL );
  }//GEN-LAST:event_cancelButtonActionPerformed

  private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    if ( getPacjentPanel().getNazwiskoField().getText().equals("") ) {
      JOptionPane.showMessageDialog(
        null, "Wpisz najpierw dane pacjenta."
        , "Info", JOptionPane.INFORMATION_MESSAGE );  
      return;
    }  
    doClose( RET_OK );
  }//GEN-LAST:event_okButtonActionPerformed
  
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton badaniaButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton leczenieButton;
    private javax.swing.JButton okButton;
    private javax.swing.JButton pacjentButton;
    private okl.jspacjent.guiswing.PacjentJPanel pacjentPanel;
    private okl.jspacjent.guiswing.PrzyjecieJPanel przyjeciePanel;
    private javax.swing.JButton raportButton;
    // End of variables declaration//GEN-END:variables
  
  /** */
  private void doClose(int retStatus) {
    try {
      // Wyjúcie z edycji z zapisem
      if ( ( retStatus == RET_OK ) || 
           ( retStatus == RET_UPDATED ) ) {
        try {          
          // ...
          // wpisanie edytowanych wartoúci do BeanÛw
          getPacjentPanel().updatePacjentBean();          
          getPrzyjeciePanel().updatePrzyjecieBean();
          // zapis do bazy danych
          getPrzyjeciePanel().saveOrUpdatePrzyjecie();
          updated = true;
        }
        catch (java.text.ParseException e) {
          System.out.println(e);
          JOptionPane.showMessageDialog(
            null," èle sformatowane dane." , 
            "B≥πd!", JOptionPane.ERROR_MESSAGE ); 
        }
      }
      else // nie wpisywanie lub skasownaie danych
      if ( ( retStatus == RET_CANCEL ) && 
           ( nowePrzyjecie ) && 
           ( updated ) ) {      
        Pacjent   pacjent   = getPacjentPanel().getPacjent();
        Przyjecie przyjecie = getPrzyjeciePanel().getPrzyjecie();
        if ( przyjecie != null  ) {
          getDao().deletePrzyjecie( przyjecie );        
        }
        if ( pacjent != null ) {  
          if ( pacjent.getNazwisko().equals("") ) {
            getDao().deletePacjent( pacjent );
          }  
        }        
      } 
    }
    catch ( org.springframework.dao.DataAccessException e ) {
      JOptionPane.showMessageDialog(
        null,
        " Dane prawdopodobnie nie zosta≥y zapisane, " +
        "K≥opoty z bazπ danych." , 
        "B≥πd!", JOptionPane.ERROR_MESSAGE );   
    }
    // ...
    firePropertyChange("returnStatus", returnStatus, retStatus );    
    returnStatus = retStatus;    
  }
  
  public javax.swing.JButton getCancelButton() {
    return cancelButton;
  };
}
