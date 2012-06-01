/*
 * BadanieJPanel.java
 *
 * Created on 18 grudzieñ 2006, 14:33
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
public class BadanieJPanel extends javax.swing.JPanel {
  
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
  
  // Fields  
  
  /** data access object for this application */
  private DAO dao;
  
  /** lista elementów do edycji */
  private Collection badania;
  
  /** edytowany bean */
  private Badanie badanie; 
  
  /** lista rodzajów badañ */
  private List rodzajeBadan;
  
  /** model listy elementów do edycji dla komponentu JList */
  private DefaultListModel listModel = new DefaultListModel();
  
  /** przyjêcie dla tego badania */
  private Przyjecie przyjecie;
  

  // Access methods
  
  public void setDao( DAO dao ) {
    this.dao = dao;
  }
  
  public DAO getDao() {
    return dao;
  }
  
  public Collection getBadaniaList() {
    return badania;
  }
  
  public void setBadaniaList( Collection badania ) {
    this.badania = badania;
  }  
  
  public Badanie getBadanie() {
    return this.badanie;
  } 
  
  public void setBadanie( Badanie badanie ) {
    this.badanie = badanie;
  }
  
  public Przyjecie getPrzyjecie() {
    return this.przyjecie;
  } 
  
  public void setPrzyjecie( Przyjecie przyjecie ) {
    this.przyjecie = przyjecie;
  }
  
  public List getRodzajeBadan() {
    return this.rodzajeBadan;
  }
  
  public void setRodzajeBadan( List rodzajeBadan ) {
    this.rodzajeBadan = rodzajeBadan;
  } 
  
  public Pacjent getPacjent() {    
    return getBadanie().getPrzyjecie().getPacjent();          
  }
  
  // Other metods
  
  /** wybrany z listy element */
  public Badanie getBadanieSelected() {
    return (Badanie)editedList.getSelectedValue();         
  }
  
  /** ustawienie elementu wybranego z listy jako edytowanego */
  public void setBadanie() {    
    setBadanie( getBadanieSelected() );         
  }  
  
  /** odœwie¿enie zawartoœci elementów GUI */
  public void updateComponents() {
    if ( getBadanie() == null ) {
      databadField.setValue( new Date() );
      rodzbadBox.setSelectedItem( null );
      opisField.setText( null );
      uwagiField.setText( null );      
      //
      databadField.setEnabled ( false );
      rodzbadBox.setEnabled( false );
      opisField.setEnabled( false );
      uwagiField.setEnabled( false );      
      deleteButton.setEnabled( false );
      updateButton.setEnabled( false );
    }
    else {
      databadField.setEnabled ( true );
      rodzbadBox.setEnabled( true );
      opisField.setEnabled( true );
      uwagiField.setEnabled( true ); 
      clearComponents();
      //System.out.println( getBadanie() );
      databadField.setValue( getBadanie().getData() );
      rodzbadBox.setSelectedItem( getBadanie().getRodzbad() );      
      opisField.setText( getBadanie().getOpis() );
      uwagiField.setText( getBadanie().getUwagi() );      
      deleteButton.setEnabled( true );
      updateButton.setEnabled( true );
    }  
  }
  
  public void clearComponents() {
    databadField.setValue( null );
    rodzbadBox.setSelectedItem( null );
    opisField.setText( null );
    uwagiField.setText( null );  
  }
  
  /** wpisanie edytowanych wartoœci do obiektu */
  public void updateBadanie() {
    if ( getBadanie() != null ) {
      getBadanie().setData( (Date)databadField.getValue() );
      getBadanie().setRodzbad( (Rodzbad)rodzbadBox.getSelectedItem() );
      getBadanie().setOpis( opisField.getText().trim() );
      getBadanie().setUwagi( uwagiField.getText().trim() );      
    }  
  }
  
  /** utworzenie modelu listy */
  public void setListModel() {
    listModel.removeAllElements();
    if ( getBadaniaList() == null ) {
      Badanie a = new Badanie();
      setBadaniaList( new LinkedList() );
      getBadaniaList().add( a );
      listModel.addElement( a ); 
    }
    else {
      Iterator iter = getBadaniaList().iterator();
      while ( iter.hasNext() ) {
        Badanie a = (Badanie)iter.next();
        listModel.addElement(a);        
      }      
    }
  }
  
  public Object[] getRodzajeBadanAsArray() {
    if ( getRodzajeBadan() == null ) {      
      return new Object[] { new Rodzbad() };  
    } 
    else {
      return getRodzajeBadan().toArray();
    }
  }
  
  // Constructors  
  
  /** Creates new form BadanieJPanel */
  public BadanieJPanel() {
    initComponents();
    updateComponents();
  }
  
  /** Creates new form BadanieJPanel */
  public BadanieJPanel( Collection badania ) {
    setBadaniaList( badania );
    initComponents();
    updateComponents();
  }
  
  /** Creates new form BadanieJPanel */
  public BadanieJPanel( DAO dao ) {
    setDao( dao );
    setBadaniaList( getDao().getAllBadanie() );
    setRodzajeBadan( getDao().getAllRodzbad() );    
    initComponents();
    updateComponents();
  }
  
  /** Creates new form BadanieJPanel */
  public BadanieJPanel( Collection badania, DAO dao ) {
    setBadaniaList( badania );
    setDao( dao );    
    setRodzajeBadan( getDao().getAllRodzbad() );    
    initComponents();
    updateComponents();
  }
  
  /** Creates new form BadanieJPanel */
  public BadanieJPanel( Przyjecie przyjecie, 
                        Collection badania, 
                        DAO dao ) {
    setPrzyjecie( przyjecie );
    setBadaniaList( badania );
    setDao( dao );    
    setRodzajeBadan( getDao().getAllRodzbad() );    
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
    setListModel();
    listPanel = new javax.swing.JPanel();
    jScrollPane = new javax.swing.JScrollPane();
    editedList = new javax.swing.JList( listModel );
    editPanel = new javax.swing.JPanel();
    databadField = new javax.swing.JFormattedTextField(dateFormat);
    opisField = new javax.swing.JTextField();
    rodzbadBox = new JComboBox(getRodzajeBadanAsArray());
    uwagiField = new javax.swing.JTextField();
    buttonPanel = new javax.swing.JPanel();
    deleteButton = new javax.swing.JButton();
    addButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();

    listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wyb\u00f3r badania", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    editedList.setFont(new java.awt.Font("Tahoma", 0, 12));
    editedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    editedList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        editedListValueChanged(evt);
      }
    });

    jScrollPane.setViewportView(editedList);

    org.jdesktop.layout.GroupLayout listPanelLayout = new org.jdesktop.layout.GroupLayout(listPanel);
    listPanel.setLayout(listPanelLayout);
    listPanelLayout.setHorizontalGroup(
      listPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(listPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
        .addContainerGap())
    );
    listPanelLayout.setVerticalGroup(
      listPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(listPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        .addContainerGap())
    );

    editPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edycja badania", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    databadField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data badania", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    databadField.setFont(new java.awt.Font("Tahoma", 0, 12));

    opisField.setFont(new java.awt.Font("Tahoma", 0, 12));
    opisField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    rodzbadBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    rodzbadBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rodzaj bada\u0144", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    uwagiField.setFont(new java.awt.Font("Tahoma", 0, 12));
    uwagiField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uwagi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    org.jdesktop.layout.GroupLayout editPanelLayout = new org.jdesktop.layout.GroupLayout(editPanel);
    editPanel.setLayout(editPanelLayout);
    editPanelLayout.setHorizontalGroup(
      editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(editPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(org.jdesktop.layout.GroupLayout.TRAILING, editPanelLayout.createSequentialGroup()
            .add(rodzbadBox, 0, 209, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(uwagiField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 237, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(editPanelLayout.createSequentialGroup()
            .add(databadField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 10, Short.MAX_VALUE)
            .add(opisField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 334, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    editPanelLayout.setVerticalGroup(
      editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(editPanelLayout.createSequentialGroup()
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(opisField)
          .add(databadField))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(uwagiField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(rodzbadBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    buttonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Operacje na elementach listy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    deleteButton.setFont(new java.awt.Font("Tahoma", 0, 12));
    deleteButton.setForeground(new java.awt.Color(153, 0, 0));
    deleteButton.setText("Usu\u0144 zaznaczone");
    deleteButton.setToolTipText("usuni\u0119cie elementu wybranego na li\u015bcie ");
    deleteButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteButtonActionPerformed(evt);
      }
    });

    addButton.setFont(new java.awt.Font("Tahoma", 0, 12));
    addButton.setForeground(new java.awt.Color(0, 102, 51));
    addButton.setText("Dodaj nowe");
    addButton.setToolTipText("dodanie nowego elementu do listy ");
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addButtonActionPerformed(evt);
      }
    });

    updateButton.setFont(new java.awt.Font("Tahoma", 0, 12));
    updateButton.setText("Wpisz edytowane");
    updateButton.setToolTipText("wpisanie edytowanych ni\u017cej warto\u015bci na list\u0119");
    updateButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        updateButtonActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout buttonPanelLayout = new org.jdesktop.layout.GroupLayout(buttonPanel);
    buttonPanel.setLayout(buttonPanelLayout);
    buttonPanelLayout.setHorizontalGroup(
      buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(buttonPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(deleteButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
          .add(addButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
          .add(updateButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        .addContainerGap())
    );
    buttonPanelLayout.setVerticalGroup(
      buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(buttonPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(deleteButton)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(addButton)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(updateButton)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(org.jdesktop.layout.GroupLayout.TRAILING, editPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(layout.createSequentialGroup()
            .add(listPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(listPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(editPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(12, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    // ...
    setBadanie( new Badanie(0, getPrzyjecie() ) );
    if ( getBadaniaList() != null ) {
      getBadaniaList().add( getBadanie() );
    }  
    listModel.addElement( getBadanie() );    
    updateComponents();
    editedList.setSelectedValue( getBadanie(), true );        
    if ( ( getBadanie() != null ) && ( getDao() != null ) ){
      dao.updateBadanie( getBadanie() );
    }         
  }//GEN-LAST:event_addButtonActionPerformed

  private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    if ( ( getBadanie() != null ) && ( getDao() != null ) ) {      
      dao.deleteBadanie( getBadanie() );      
    }
    if ( ( getBadaniaList() != null ) && ( getBadanie() != null ) ) {
      getBadaniaList().remove( getBadanie() );
    }  
    if (  getBadanieSelected() != null ) {
      listModel.removeElement( getBadanieSelected() );
    }              
    editedList.updateUI();
        
  }//GEN-LAST:event_deleteButtonActionPerformed

  private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
    this.updateBadanie();
    if ( ( getDao() != null ) && ( getBadanie() != null ) ) {
      dao.updateBadanie( getBadanie() );
    }  
    editedList.updateUI();   
  }//GEN-LAST:event_updateButtonActionPerformed

  private void editedListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_editedListValueChanged
    // ...
    setBadanie();  
    updateComponents();
    databadField.requestFocus();
  }//GEN-LAST:event_editedListValueChanged
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JFormattedTextField databadField;
  private javax.swing.JButton deleteButton;
  private javax.swing.JPanel editPanel;
  private javax.swing.JList editedList;
  private javax.swing.JScrollPane jScrollPane;
  private javax.swing.JPanel listPanel;
  private javax.swing.JTextField opisField;
  private javax.swing.JComboBox rodzbadBox;
  private javax.swing.JButton updateButton;
  private javax.swing.JTextField uwagiField;
  // End of variables declaration//GEN-END:variables
  
}
