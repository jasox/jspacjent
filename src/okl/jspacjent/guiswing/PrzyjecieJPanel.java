/*
 * PrzyjecieJPanel.java
 *
 * Created on 14 grudzieñ 2006, 14:36
 */
package okl.jspacjent.guiswing;

import java.util.*;
import java.text.*;

import okl.jspacjent.model.*;
import okl.jspacjent.dao.*;

/**
 *
 * @author  janusz.swol@lot.pl
 */
public class PrzyjecieJPanel extends javax.swing.JPanel {
  /** format wprowadzania daty */  
  SimpleDateFormat dateFormat = 
    new SimpleDateFormat("dd.MM.yyyy HH:mm");
  /** format wprowadzania numerów */
  NumberFormat nrFormat = NumberFormat.getNumberInstance();
  
  // Fields
  
  /** data access object for this application */
  private DAO dao;
  
  /** edytowane przyjêcie na oddzia³ */ 
  private Przyjecie przyjecie;  
  
  /** lista wszystkich lekarzy */ 
  private List lekarze;
  
  /** pe³na lista miejsc sk¹d i dok¹d 
      kierowani s¹ pacjenci - jako Stringi */
  private List skierowania; 
  
  /** pe³na lista rozpoznañ chorobowych - jako Stringi  */
  private List rozpoznania;     

  // Access methods
  //
  
  public void setDao( DAO dao ) {
    this.dao = dao;
  }
  
  public DAO getDao() {
    return dao;
  }
  
  public void setPrzyjecie( Przyjecie przyjecie ) {
    this.przyjecie = przyjecie;
  }
  
  public Przyjecie getPrzyjecie() {
    return this.przyjecie;
  }   
  
  public List getLekarze() {
    return lekarze;
  }
  
  public void setLekarze(List lekarze) {
    this.lekarze = lekarze;
  }
  
  public Object[] getLekarzeAsArray() {
    if ( getLekarze() == null ) {
      return new Object[] { new Lekarz() };  
    } 
    else {
      return getLekarze().toArray();
    }
  }
  
  public Object getLekarzSelected() {
    if ( getPrzyjecie() == null ) {
      return null; 
    }
    else {
      return getPrzyjecie().getLekarz();  
    }    
  }
  
  public List getSkierowania() {
    return skierowania;
  }
  
  public void setSkierowania(List skierowania) {
    this.skierowania = skierowania;
  }
  
  public Object[] getSkierowaniaAsArray() {
    if ( getSkierowania() == null ) {
      return new String[] { "" };  
    } 
    else {
      return getSkierowania().toArray();
    }
  }  
  
  public List getRozpoznania() {
    return rozpoznania;
  }
  
  public void setRozpoznania(List rozpoznania) {
    this.rozpoznania = rozpoznania;
  }
  
  public Object[] getRozpoznaniaAsArray() {
    if ( getRozpoznania() == null ) {
      return new String[] { "" };  
    } 
    else {
      return getRozpoznania().toArray();
    }
  }
  
  /** Wpisanie edytowanych wartoœci do modelu tj. beana Przyjecie */
  public void updatePrzyjecieBean() throws ParseException {
    if ( getPrzyjecie() != null ) {     
      nrglownyField.commitEdit();
      if ( ( nrglownyField.getText() != null ) && 
          !( nrglownyField.getText().equals("") ) ) {        
        getPrzyjecie().setNrglowny(        
          ( (Number)nrglownyField.getValue() ).longValue() );    
      }
      else {
        getPrzyjecie().setNrglowny( 0L );
      }      
      nroddzialField.commitEdit();
      if ( ( nroddzialField.getText() != null ) && 
          !( nroddzialField.getText().equals("")  )  ) {
        
        getPrzyjecie().setNroddzial(        
          ( (Number)nroddzialField.getValue() ).longValue() );    
      }
      else {
        getPrzyjecie().setNroddzial( 0L );
      }     
      getPrzyjecie().setKsg( ksgField.getText().trim() );
      getPrzyjecie().setDataPrz( (Date)dataprzField.getValue() );
      getPrzyjecie().setDataWyp( (Date)datawypField.getValue() );    
      getPrzyjecie().setSkUwagi( skuwagiField.getText().trim() );
      getPrzyjecie().setSskierow( (String)sskierowBox.getSelectedItem() );
      getPrzyjecie().setGdziewyp( (String)gdziewypBox.getSelectedItem() );
      getPrzyjecie().setLekarz( (Lekarz)lekarzBox.getSelectedItem() );
      getPrzyjecie().setRozpwst( (String)rozpwstBox.getSelectedItem() );
      getPrzyjecie().setRozpost( (String)rozpostBox.getSelectedItem() );    
    }    
  }
  
  /** Wpisanie beana Przyjecie  do bazy danych */
  public void saveOrUpdatePrzyjecie() {
    if ( getPrzyjecie() != null ) {
      if ( ( getDao() != null ) &&  
          ( getPrzyjecie().getPacjent() != null ) ) {
        // bez pacjenta zapis przyjêcia nie ma sensu
        getDao().updatePacjent( getPrzyjecie().getPacjent() );
        getDao().updatePrzyjecie( getPrzyjecie() );
      }      
    }
  }  
  
  /** Wpisanie edytowanych wartoœci z modelu tj. beana Przyjecie */
  public void updateComponents() {
    if ( getPrzyjecie() != null ) {
      nrglownyField.setEnabled( true );
      nroddzialField.setEnabled( true );
      ksgField.setEnabled( true );
      dataprzField.setEnabled( true );
      datawypField.setEnabled( true );    
      skuwagiField.setEnabled( true );
      sskierowBox.setEnabled( true );
      gdziewypBox.setEnabled( true );
      lekarzBox.setEnabled( true );
      rozpwstBox.setEnabled( true );
      rozpostBox.setEnabled( true );
      nrglownyField.setValue( getPrzyjecie().getNrglowny() );
      nroddzialField.setValue( getPrzyjecie().getNroddzial() );
      ksgField.setText( getPrzyjecie().getKsg() );
      dataprzField.setValue( getPrzyjecie().getDataPrz() );
      datawypField.setValue( getPrzyjecie().getDataWyp() );    
      skuwagiField.setText( getPrzyjecie().getSkUwagi() );
      sskierowBox.setSelectedItem( getPrzyjecie().getSskierow() );
      gdziewypBox.setSelectedItem( getPrzyjecie().getGdziewyp() );
      lekarzBox.setSelectedItem( getPrzyjecie().getLekarz() );
      rozpwstBox.setSelectedItem( getPrzyjecie().getRozpwst() );
      rozpostBox.setSelectedItem( getPrzyjecie().getRozpost() );    
    }
    else {
      clearComponents();
      nrglownyField.setEnabled( false );
      nroddzialField.setEnabled( false );
      ksgField.setEnabled( false );
      dataprzField.setEnabled( false );
      datawypField.setEnabled( false );    
      skuwagiField.setEnabled( false );
      sskierowBox.setEnabled( false );
      gdziewypBox.setEnabled( false );
      lekarzBox.setEnabled( false );
      rozpwstBox.setEnabled( false );
      rozpostBox.setEnabled( false ); 
    }
  }
  
  /** Wyzerowanie edytowanych wartoœci modelu */
  public void clearComponents() {    
    // ...
    nrglownyField.setValue( 0L );
    nroddzialField.setValue( 0L );
    ksgField.setText( 
      new SimpleDateFormat("yy").format( new Date() ) );
    dataprzField.setValue( new Date() );
    datawypField.setValue( null );    
    skuwagiField.setText( null );
    sskierowBox.setSelectedItem( null );
    gdziewypBox.setSelectedItem( null );
    lekarzBox.setSelectedItem( null );
    rozpwstBox.setSelectedItem( null );
    rozpostBox.setSelectedItem( null );    
  }  
  
  public void initFormat() {
    nrFormat.setGroupingUsed(false);
    nrFormat.setParseIntegerOnly(true);
    nrFormat.setMaximumIntegerDigits(5);   
  }
  
  /** */
  public void requestFocus() {
    nrglownyField.requestFocus();
  } 
  
  // Constructors  
  
  /** Creates new form PrzyjecieJPanel  1 */
  public PrzyjecieJPanel() {    
    initFormat();
    initComponents();
    updateComponents();    
  }
  
  /** Creates new form PrzyjecieJPanel  2 */
  public PrzyjecieJPanel( Przyjecie przyjecie ) {   
    setPrzyjecie( przyjecie );    
    initFormat();
    initComponents();
    updateComponents();
    nrglownyField.requestFocus();
  }
  
  /** Creates new form PrzyjecieJPanel  3 */
  public PrzyjecieJPanel( Przyjecie przyjecie, DAO dao ) {    
    setPrzyjecie( przyjecie );  
    setDao( dao );
    setLekarze( getDao().getAllLekarz() );
    setSkierowania( getDao().getAllSkierowAsStrings() );
    setRozpoznania( getDao().getAllRozpoznanieAsStrings() );
    initFormat();
    initComponents();    
    updateComponents();    
    nrglownyField.requestFocus();
  }
  
  /** Creates new form PrzyjecieJPanel  3 */
  public PrzyjecieJPanel( Przyjecie przyjecie, 
                          List      lekarze,
                          List      skierowania,
                          List      rozpoznania ) {    
    setPrzyjecie( przyjecie );    
    setLekarze( lekarze );
    setSkierowania ( skierowania );
    setRozpoznania( rozpoznania );
    initFormat();
    initComponents();
    updateComponents();
    nrglownyField.requestFocus();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    przyjeciePanel = new javax.swing.JPanel();
    nrglownyField = new javax.swing.JFormattedTextField( nrFormat );
    nroddzialField = new javax.swing.JFormattedTextField( nrFormat );
    ksgField = new javax.swing.JTextField();
    dataprzField = new javax.swing.JFormattedTextField(dateFormat);
    datawypField = new javax.swing.JFormattedTextField(dateFormat);
    sskierowBox = new javax.swing.JComboBox(getSkierowaniaAsArray());
    skuwagiField = new javax.swing.JTextField();
    gdziewypBox = new javax.swing.JComboBox(getSkierowaniaAsArray());
    lekarzBox = new javax.swing.JComboBox(getLekarzeAsArray());
    rozpwstBox = new javax.swing.JComboBox(getRozpoznaniaAsArray());
    rozpostBox = new javax.swing.JComboBox(getRozpoznaniaAsArray());

    przyjeciePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dane przyj\u0119cia pacjenta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    nrglownyField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nr g\u0142\u00f3wny", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    nrglownyField.setFont(new java.awt.Font("Tahoma", 0, 12));

    nroddzialField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nr oddzia\u0142owy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    nroddzialField.setFont(new java.awt.Font("Tahoma", 0, 12));

    ksgField.setFont(new java.awt.Font("Tahoma", 0, 12));
    ksgField.setToolTipText("rok do kt\u00f3rego przynale\u017cy numer ksi\u0119gi oddzia\u0142owej");
    ksgField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rok", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    dataprzField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data przyj\u0119cia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    dataprzField.setFont(new java.awt.Font("Tahoma", 0, 12));

    datawypField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data wypisu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    datawypField.setFont(new java.awt.Font("Tahoma", 0, 12));

    sskierowBox.setEditable(true);
    sskierowBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    sskierowBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sk\u0105d skierowany", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    skuwagiField.setFont(new java.awt.Font("Tahoma", 0, 12));
    skuwagiField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uwagi o skierowaniu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    gdziewypBox.setEditable(true);
    gdziewypBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    gdziewypBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dok\u0105d wypisany", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    lekarzBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    lekarzBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lekarz prowadz\u0105cy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    rozpwstBox.setEditable(true);
    rozpwstBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    rozpwstBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rozpoznanie wst\u0119pne", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    rozpostBox.setEditable(true);
    rozpostBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    rozpostBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rozpoznanie ostateczne", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    org.jdesktop.layout.GroupLayout przyjeciePanelLayout = new org.jdesktop.layout.GroupLayout(przyjeciePanel);
    przyjeciePanel.setLayout(przyjeciePanelLayout);
    przyjeciePanelLayout.setHorizontalGroup(
      przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(przyjeciePanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, przyjeciePanelLayout.createSequentialGroup()
              .add(nrglownyField)
              .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
              .add(nroddzialField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
              .add(ksgField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(gdziewypBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 256, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(rozpwstBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 256, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(sskierowBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 256, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(rozpostBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 276, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(lekarzBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 276, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(przyjeciePanelLayout.createSequentialGroup()
            .add(dataprzField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 136, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(datawypField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(org.jdesktop.layout.GroupLayout.TRAILING, skuwagiField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 276, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    przyjeciePanelLayout.setVerticalGroup(
      przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(przyjeciePanelLayout.createSequentialGroup()
        .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(dataprzField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(datawypField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(ksgField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(nroddzialField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(nrglownyField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(skuwagiField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(sskierowBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(lekarzBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(gdziewypBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(przyjeciePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(rozpostBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(rozpwstBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
    );

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(przyjeciePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(przyjeciePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
    );
  }// </editor-fold>//GEN-END:initComponents

  
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JFormattedTextField dataprzField;
  private javax.swing.JFormattedTextField datawypField;
  private javax.swing.JComboBox gdziewypBox;
  private javax.swing.JTextField ksgField;
  private javax.swing.JComboBox lekarzBox;
  private javax.swing.JFormattedTextField nrglownyField;
  private javax.swing.JFormattedTextField nroddzialField;
  private javax.swing.JPanel przyjeciePanel;
  private javax.swing.JComboBox rozpostBox;
  private javax.swing.JComboBox rozpwstBox;
  private javax.swing.JTextField skuwagiField;
  private javax.swing.JComboBox sskierowBox;
  // End of variables declaration//GEN-END:variables
  
}
