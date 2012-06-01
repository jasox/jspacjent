/*
 * RodzleczJPanel.java
 *
 * Created on 18 grudzie� 2006, 14:33
 */
package okl.jspacjent.guiswing;

import java.util.*;
import javax.swing.*;

import okl.jspacjent.model.*;
import okl.jspacjent.dao.*;

/** 
 *
 * @author  janusz.swol@lot.pl
 */
public class RodzleczJPanel extends javax.swing.JPanel {
  
  // Fields  
  
  /** data access object for this application */
  private DAO dao;
  
  /** lista element�w do edycji */
  private List rodzajeLeczenia;
  
  /** edytowany bean */
  private Rodzlecz rodzlecz; 
  
  /** model listy element�w do edycji dla komponentu JList */
  private DefaultListModel listModel = new DefaultListModel();  
  

  // Access methods
  
  public void setDao( DAO dao ) {
    this.dao = dao;
  }
  
  public DAO getDao() {
    return dao;
  }
  
  public List getRodzajeLeczenia() {
    return rodzajeLeczenia;
  }
  
  public void setRodzajeLeczenia( List rodzajeLeczenia ) {
    this.rodzajeLeczenia = rodzajeLeczenia;
  }  
  
  public Rodzlecz getRodzlecz() {
    return this.rodzlecz;
  } 
  
  public void setRodzlecz( Rodzlecz rodzlecz ) {
    this.rodzlecz = rodzlecz;
  }
  
  // Other metods
  
  /** wybrany z listy element */
  public Rodzlecz getRodzleczSelected() {
    return (Rodzlecz)editedList.getSelectedValue();         
  }
  
  /** ustawienie elementu wybranego z listy jako edytowanego */
  public void setRodzlecz() {    
    this.rodzlecz = getRodzleczSelected();         
  }  
  
  /** od�wie�enie zawarto�ci element�w GUI */
  public void updateComponents() {
    if ( getRodzlecz() == null ) {
      opisField.setText( null );
      uwagiField.setText( null );
      opisField.setEnabled( false );
      uwagiField.setEnabled( false );
      deleteButton.setEnabled( false );
      updateButton.setEnabled( false );
    }
    else {
      opisField.setEnabled( true );
      uwagiField.setEnabled( true );
      opisField.setText( getRodzlecz().getOpis() );
      uwagiField.setText( getRodzlecz().getUwagi() );      
      deleteButton.setEnabled( true );
      updateButton.setEnabled( true );
    }  
  }
  
  /** wpisanie edytowanych warto�ci do obiektu */
  public void updateRodzlecz() {
    if ( getRodzlecz() != null ) {
      getRodzlecz().setOpis( opisField.getText().trim() );
      getRodzlecz().setUwagi( uwagiField.getText().trim() );
    }  
  }
  
  /** utworzenie modelu listy */
  public void setListModel() {
    listModel.removeAllElements();
    if ( getRodzajeLeczenia() == null ) {
      Rodzlecz a = new Rodzlecz();
      setRodzajeLeczenia( new LinkedList() );
      getRodzajeLeczenia().add( a );
      listModel.addElement( a ); 
    }
    else {
      Iterator iter = getRodzajeLeczenia().iterator();
      while ( iter.hasNext() ) {
        Rodzlecz a = (Rodzlecz)iter.next();
        listModel.addElement(a);
        //System.out.println(a);
      }
      //System.out.println(listModel);
    }
  }
  
  // Constructors  
  
  /** Creates new form RodzleczJPanel */
  public RodzleczJPanel() {
    initComponents();
    updateComponents();
  }
  
  /** Creates new form RodzleczJPanel */
  public RodzleczJPanel( List rodzajeLeczenia ) {
    setRodzajeLeczenia( rodzajeLeczenia );
    initComponents();
    updateComponents();
  }
  
  /** Creates new form RodzleczJPanel */
  public RodzleczJPanel( DAO dao ) {
    setDao( dao );
    setRodzajeLeczenia( dao.getAllRodzlecz() );
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
    opisField = new javax.swing.JTextField();
    uwagiField = new javax.swing.JTextField();
    buttonPanel = new javax.swing.JPanel();
    deleteButton = new javax.swing.JButton();
    addButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();

    listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wyb\u00f3r rodzaju leczenia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
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
        .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        .addContainerGap())
    );
    listPanelLayout.setVerticalGroup(
      listPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(listPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        .addContainerGap())
    );

    editPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edycja rodzaju leczenia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    opisField.setFont(new java.awt.Font("Tahoma", 0, 12));
    opisField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    uwagiField.setFont(new java.awt.Font("Tahoma", 0, 12));
    uwagiField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Uwagi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    org.jdesktop.layout.GroupLayout editPanelLayout = new org.jdesktop.layout.GroupLayout(editPanel);
    editPanel.setLayout(editPanelLayout);
    editPanelLayout.setHorizontalGroup(
      editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(editPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(opisField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(uwagiField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
        .addContainerGap())
    );
    editPanelLayout.setVerticalGroup(
      editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(editPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(opisField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(uwagiField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
          .add(deleteButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
          .add(addButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
          .add(updateButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
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
          .add(layout.createSequentialGroup()
            .add(listPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(buttonPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .add(org.jdesktop.layout.GroupLayout.TRAILING, editPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(listPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(editPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    // ...
    setRodzlecz( new Rodzlecz() );
    if ( getRodzajeLeczenia() != null ) {
      getRodzajeLeczenia().add( getRodzlecz() );
    }  
    listModel.addElement( getRodzlecz() );    
    updateComponents();
    editedList.setSelectedValue( getRodzlecz(), true );        
    if ( ( getRodzlecz() != null ) && ( getDao() != null ) ){
      dao.updateRodzlecz( getRodzlecz() );
    }  
  }//GEN-LAST:event_addButtonActionPerformed

  private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    // ...
    if ( ( getRodzlecz() != null ) && ( getDao() != null ) ) {      
      dao.deleteRodzlecz( getRodzlecz() );      
    }
    if ( ( getRodzajeLeczenia() != null ) && ( getRodzlecz() != null ) ) {
      getRodzajeLeczenia().remove( getRodzlecz() );
    }  
    if (  getRodzleczSelected() != null ) {
      listModel.removeElement( getRodzleczSelected() );
    }              
    editedList.updateUI();
        
  }//GEN-LAST:event_deleteButtonActionPerformed

  private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
    // ...
    this.updateRodzlecz();
    if ( ( getDao() != null ) && ( getRodzlecz() != null ) ) {
      dao.updateRodzlecz( getRodzlecz() );
    }  
    editedList.updateUI();   
  }//GEN-LAST:event_updateButtonActionPerformed

  private void editedListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_editedListValueChanged
    // ...
    setRodzlecz();  
    updateComponents();
    opisField.requestFocus();
  }//GEN-LAST:event_editedListValueChanged
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton deleteButton;
  private javax.swing.JPanel editPanel;
  private javax.swing.JList editedList;
  private javax.swing.JScrollPane jScrollPane;
  private javax.swing.JPanel listPanel;
  private javax.swing.JTextField opisField;
  private javax.swing.JButton updateButton;
  private javax.swing.JTextField uwagiField;
  // End of variables declaration//GEN-END:variables
  
}