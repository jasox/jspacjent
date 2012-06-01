/*
 * PacjentJPanel.java
 *
 * Created on 11 grudzieñ 2006, 19:50
 */
package okl.jspacjent.guiswing;

import java.util.*;
import java.text.*;
import javax.swing.*;

import okl.jspacjent.model.*;
import okl.jspacjent.dao.*;

/**
 *
 * @author  janusz.swol@lot.pl
 */
public class PacjentJPanel extends javax.swing.JPanel {  
  
  /** format wprowadzania PESEL'u */
  NumberFormat peselFormat = NumberFormat.getNumberInstance();  
  /** format wprowadzania wieku */
  NumberFormat wiekFormat  = NumberFormat.getNumberInstance();
  
  // Fields
  
  /** data access object for this application */
  private DAO dao;
  
  /** edytowany pacjent */
  private Pacjent pacjent;  
  
  /** pe³na lista kas chorych, ( jako obiektów Kasach ) */
  private List kasy;       
  
  // Access methods
  
  public void setDao( DAO dao ) {
    this.dao = dao;
  }
  
  public DAO getDao() {
    return dao;
  }
  
  public void setPacjent( Pacjent pacjent ) {
    this.pacjent = pacjent;      
  }
  
  public Pacjent getPacjent() {
    return this.pacjent;
  }
  
  public void setKasy( List kasy ) {
    this.kasy = kasy;
  }
  
  public List getKasy() {
    return this.kasy;
  }
  
  public JTextField getNazwiskoField() {
    return this.nazwiskoField;
  };  
  
  public Object[] getKasyAsArray() {
    if ( getKasy() == null ) {      
      return new Object[] { new Kasach(0, "?") };  
    } 
    else {
      return getKasy().toArray();
    }
  }
  
  /** Wpisanie edytowanych wartoœci do modelu tj. beana Pacjent */
  public void updatePacjentBean() throws ParseException {
    if ( getPacjent() != null ) {
      getPacjent().setImie( imieField.getText().trim() );
      getPacjent().setNazwisko( nazwiskoField.getText().trim() );      
      if ( ( peselField.getText() != null ) && 
          !( peselField.getText().equals("")  )  ) {        
        peselField.commitEdit();
        getPacjent().setPesel(        
          ( (Number)peselField.getValue() ).longValue() );    
      }
      else {
        getPacjent().setPesel( null );
      }
      getPacjent().setAdres( adresField.getText().trim() );
      if ( ( wiekField.getText() != null ) &&
          !( wiekField.getText().equals("")  )  ) {
        wiekField.commitEdit();    
        getPacjent().setWiek( 
          ( (Number)wiekField.getValue() ).doubleValue() );
      } 
      else {
        getPacjent().setWiek( null );
      }
      getPacjent().setKontakt( kontaktField.getText().trim() );
      getPacjent().setTelkont( telkontField.getText().trim() );
      getPacjent().setUwagi( uwagiField.getText().trim() );
      getPacjent().setKasach( (Kasach)kasaBox.getSelectedItem() );            
    }
  }
  
  /** Wpisanie wartoœci z Beana Pacjent do bazy danych */
  public void saveOrUpdatePacjent() {
    if ( getPacjent() != null ) {
      if ( getDao() != null ) {
        getDao().updatePacjent( getPacjent() );
      }
    }    
  }
  
  /** Wpisanie edytowanych wartoœci do modelu z beana Pacjent */
  public void updateComponents() {
    if ( getPacjent() != null ) {
      clearComponents();
      imieField.setEnabled( true );  
      nazwiskoField.setEnabled( true );
      peselField.setEnabled( true );
      adresField.setEnabled( true );  
      wiekField.setEnabled( true );
      kontaktField.setEnabled( true );
      telkontField.setEnabled( true );
      uwagiField.setEnabled( true );
      kasaBox.setEnabled( true );
      imieField.setText( getPacjent().getImie() );
      nazwiskoField.setText( getPacjent().getNazwisko( ) );
      peselField.setValue( getPacjent().getPesel() );    
      adresField.setText( getPacjent().getAdres( ) );      
      wiekField.setValue( getPacjent().getWiek() );
      kontaktField.setText( getPacjent().getKontakt() );
      telkontField.setText( getPacjent().getTelkont() );
      uwagiField.setText( getPacjent().getUwagi() );  
      kasaBox.setSelectedItem( getPacjent().getKasach() );
    }
    else {
      clearComponents();
      imieField.setEnabled( false );  
      nazwiskoField.setEnabled( false );
      peselField.setEnabled( false );
      adresField.setEnabled( false );  
      wiekField.setEnabled( false );
      kontaktField.setEnabled( false );
      telkontField.setEnabled( false );
      uwagiField.setEnabled( false );
      kasaBox.setEnabled( false );
    }
  }
  
  /** Wyzerowanie edytowanych wartoœci modelu  */
  public void clearComponents() {
    imieField.setText( null );    
    nazwiskoField.setText( null );    
    peselField.setValue( null );    
    adresField.setText( null );   
    wiekField.setValue( null );    
    kontaktField.setText( null );    
    telkontField.setText( null );    
    uwagiField.setText( null );    
    kasaBox.setSelectedItem( null );    
  }
  
  /** ustawienie formatów wczytywania danych */
  public void initFormat() {
    peselFormat.setGroupingUsed(false);
    peselFormat.setParseIntegerOnly(true);
    peselFormat.setMaximumIntegerDigits(11);      
    
    wiekFormat.setGroupingUsed(false);
    wiekFormat.setParseIntegerOnly( false);
    wiekFormat.setMaximumFractionDigits(1);
  }
  
  /** */
  public void requestFocus() {
    imieField.requestFocus();
  }  
  
  // Constructors
  
  /** Creates new form PacjentJPanel */
  public PacjentJPanel() {   
    initFormat();
    initComponents();
    updateComponents();
  }
  
  /** Creates new form PacjentJPanel from persistent class Pacjent */
  public PacjentJPanel(Pacjent pacjent) {   
    setPacjent( pacjent );                
    initFormat();
    initComponents();
    updateComponents();
    imieField.requestFocus();
  }
  
  /** Creates new form PacjentJPanel from persistent class Pacjent 
      and list of "Kasy Chorych" */
  public PacjentJPanel(Pacjent pacjent, List kasy) {    
    setPacjent( pacjent );          
    setKasy( kasy );
    initFormat();
    initComponents();
    updateComponents();
    imieField.requestFocus();
  }
  
  /** Creates new form PacjentJPanel from persistent class Pacjent 
      and DAO of this application */
  public PacjentJPanel(Pacjent pacjent, DAO dao) {     
    setPacjent( pacjent );          
    setDao( dao );
    setKasy( getDao().getAllKasach() );
    initFormat();
    initComponents();
    updateComponents();
    imieField.requestFocus();
  }
  
  /** Creates new form PacjentJPanel from persistent class Pacjent, 
      List kasy and DAO of this application */
  public PacjentJPanel(Pacjent pacjent, List kasy, DAO dao) {     
    setPacjent( pacjent );
    setKasy( kasy );
    setDao( dao );
    initFormat();
    initComponents();
    updateComponents();    
    imieField.requestFocus();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    pacjentPanel = new javax.swing.JPanel();
    imieField = new javax.swing.JTextField();
    nazwiskoField = new javax.swing.JTextField();
    peselField = new javax.swing.JFormattedTextField( peselFormat );
    adresField = new javax.swing.JTextField();
    telkontField = new javax.swing.JTextField();
    kontaktField = new javax.swing.JTextField();
    kasaBox = new JComboBox(getKasyAsArray());
    wiekField = new javax.swing.JFormattedTextField( wiekFormat );
    uwagiField = new javax.swing.JTextField();

    pacjentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dane osobowe pacjenta", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    pacjentPanel.setFont(new java.awt.Font("Tahoma", 0, 12));
    imieField.setFont(new java.awt.Font("Tahoma", 0, 12));
    imieField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imi\u0119", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    imieField.setName("imie");

    nazwiskoField.setFont(new java.awt.Font("Tahoma", 0, 12));
    nazwiskoField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nazwisko", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    nazwiskoField.setName("nazwisko");

    peselField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    peselField.setFont(new java.awt.Font("Tahoma", 0, 12));

    adresField.setFont(new java.awt.Font("Tahoma", 0, 12));
    adresField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adres zamieszkania", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    telkontField.setFont(new java.awt.Font("Tahoma", 0, 12));
    telkontField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Telefon kontaktowy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    kontaktField.setFont(new java.awt.Font("Tahoma", 0, 12));
    kontaktField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dodatkowy kontakt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    kasaBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    kasaBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kasa Chorych", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    wiekField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wiek", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    wiekField.setFont(new java.awt.Font("Tahoma", 0, 12));

    uwagiField.setFont(new java.awt.Font("Tahoma", 0, 12));
    uwagiField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uwagi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    org.jdesktop.layout.GroupLayout pacjentPanelLayout = new org.jdesktop.layout.GroupLayout(pacjentPanel);
    pacjentPanel.setLayout(pacjentPanelLayout);
    pacjentPanelLayout.setHorizontalGroup(
      pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(pacjentPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(pacjentPanelLayout.createSequentialGroup()
            .add(imieField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(nazwiskoField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 327, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(pacjentPanelLayout.createSequentialGroup()
            .add(pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
              .add(org.jdesktop.layout.GroupLayout.LEADING, telkontField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
              .add(org.jdesktop.layout.GroupLayout.LEADING, kasaBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(kontaktField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(pacjentPanelLayout.createSequentialGroup()
                .add(wiekField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(uwagiField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(68, 68, 68))
          .add(pacjentPanelLayout.createSequentialGroup()
            .add(peselField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(adresField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 400, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
        .addContainerGap())
    );
    pacjentPanelLayout.setVerticalGroup(
      pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(pacjentPanelLayout.createSequentialGroup()
        .add(pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(imieField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(nazwiskoField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(peselField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(adresField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(telkontField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(kontaktField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .add(pacjentPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(uwagiField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(kasaBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(wiekField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
    );

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(pacjentPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 577, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(pacjentPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
    );
  }// </editor-fold>//GEN-END:initComponents
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField adresField;
  private javax.swing.JTextField imieField;
  private javax.swing.JComboBox kasaBox;
  private javax.swing.JTextField kontaktField;
  private javax.swing.JTextField nazwiskoField;
  private javax.swing.JPanel pacjentPanel;
  private javax.swing.JFormattedTextField peselField;
  private javax.swing.JTextField telkontField;
  private javax.swing.JTextField uwagiField;
  private javax.swing.JFormattedTextField wiekField;
  // End of variables declaration//GEN-END:variables
   

}
