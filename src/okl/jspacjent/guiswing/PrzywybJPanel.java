/*
 * PrzyjecieJPanel.java
 *
 * Created on 18 grudzieñ 2006, 14:33
 */
package okl.jspacjent.guiswing;

import java.util.*;
import javax.swing.*;
import java.text.*;

import okl.jspacjent.model.*;
import okl.jspacjent.dao.*;

/**  
 * @author  janusz.swol@lot.pl
 *
 * Wybór przyjêcia przyjeciea 
 */
public class PrzywybJPanel extends javax.swing.JPanel {
  
  /** formaty daty */
  protected SimpleDateFormat dateFormat = 
    new SimpleDateFormat("dd.MM.yyyy HH:mm"); 
  /** format wprowadzania PESEL'u */
  NumberFormat peselFormat = NumberFormat.getNumberInstance();  
  
  
  // Fields  
  
  /** data access object for this application */
  private DAO dao;
  
  /** lista elementów do wyboru */
  private List przyjecia;
  
  /** wybierany bean */
  private Przyjecie przyjecie; 
  
  /** lista wszystkich lekarzy */
  private List lekarze;         
  
  /** model listy elementów do wyboru dla komponentu JList */
  private DefaultListModel listModel = new DefaultListModel(); 
  
  /** czy to jest edycja nowego przyjêcia czy ju¿ wpisanego */
  private boolean nowePrzyjecie = false;
  
  /** czy umo¿liwiæ modyfikacjê danych w tym panelu */
  private boolean client = false;
  

  // Access methods
  
  public void setDao( DAO dao ) {
    this.dao = dao;
  }
  
  public DAO getDao() {
    return dao;
  }
  
  public List getPrzyjecia() {
    return przyjecia;
  }
  
  public void setPrzyjecia( List przyjecia ) {
    this.przyjecia = przyjecia;
  }  
  
  public Przyjecie getPrzyjecie() {
    return this.przyjecie;
  } 
  
  public void setPrzyjecie( Przyjecie przyjecie ) {
    this.przyjecie = przyjecie;
  }
  
  public boolean isNowePrzyjecie() {
    return nowePrzyjecie;
  }
  
  public boolean isClient() {
    return client;
  }
  
  public void setClient( boolean client ) {
    this.client = client;
  }
  
  public List getLekarze() {
    return lekarze;
  }
  
  public void setLekarze(List lekarze) {
    this.lekarze = lekarze;
  }
  
  // Other metods
  
  /** */
  public Object[] getLekarzeAsArray() {
    if ( getLekarze() == null ) {
      return new Object[] { new Lekarz() };  
    } 
    else {
      return getLekarze().toArray();
    }
  }
  
  /** */
  public Object getLekarzSelected() {
    if ( getPrzyjecie() == null ) {
      return null; 
    }
    else {
      return getPrzyjecie().getLekarz();  
    }    
  }  
  
  /** wybrany z listy element */
  public Przyjecie getPrzyjecieSelected() {
    return (Przyjecie)editedList.getSelectedValue();         
  }
  
  /** ustawienie elementu wybranego z listy jako edytowanego */
  public void setPrzyjecie() {    
    this.przyjecie = getPrzyjecieSelected();         
  }  
  
  /** odœwie¿enie zawartoœci elementów GUI */
  public void updateComponents() {
    // przyjêcia dodajemy w innym miejscu
    addButton.setEnabled( false );
    // Komponenty zale¿ne od wybranego elementu listy
    if ( getPrzyjecie() == null ) {       
      deleteButton.setEnabled( false );
      wybLabel.setText( null );
    }
    else {
      String tmp = "";
      tmp += getPrzyjecie().getDataPrzAsString() + "  ";
      if ( getPrzyjecie().getPacjent() != null ) {
        tmp += getPrzyjecie().getPacjent().getNazwisko() + " ";
        tmp += getPrzyjecie().getPacjent().getImie();
      }  
      wybLabel.setText( tmp );
      deleteButton.setEnabled( true );      
    } 
    // Komponenty zale¿ne od wpisanych kryteriów wyszukiwania
    if ( nazwiskoField.getText().trim().equals("") && 
         dataodField.getText().trim().equals("") && 
         datadoField.getText().trim().equals("") &&
         ksgField.getText().trim().equals("") &&
         ( lekarzBox.getSelectedItem() == null ) ) {
      searchButton.setEnabled( false );   
    }
    else {
      searchButton.setEnabled( true );       
    }
    // Komponenty zale¿ne od tego czy to aplikacja kliencka
    if ( isClient() ) {
      deleteButton.setEnabled( false );
    }
  }  
  
  /** ustawienie formatów wczytywania danych */
  public void initFormat() {    
    peselFormat.setGroupingUsed(false);
    peselFormat.setParseIntegerOnly(true);
    peselFormat.setMaximumIntegerDigits(11);      
  }
  
  /** utworzenie modelu listy */
  public void setListModel() {
    listModel.removeAllElements();
    if ( getPrzyjecia() == null ) {
      Przyjecie a = 
        new Przyjecie(0, 0, 0, new Pacjent(), new Date() );
      setPrzyjecia( new LinkedList() );
      getPrzyjecia().add( a );
      listModel.addElement( a ); 
    }
    else {
      Iterator iter = getPrzyjecia().iterator();
      while ( iter.hasNext() ) {
        Przyjecie a = (Przyjecie)iter.next();
        listModel.addElement(a);        
      }      
    }
  }
  
  /** */
  public Date getFirstDayOfYear() {
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    Date dat = new Date();
    try {
      dat = dateFormat.parse(
        "01.01." + yearFormat.format( new Date() ) + " 00:00" );
    } 
    catch ( ParseException e ) {      
    }
    finally {
      return dat;
    }
  }
  
  /** */
  public String getCurrentKsg() {
    return new SimpleDateFormat("yy").format( new Date() );
  }
  
  /** */
  public Date getMinDateKsg( String ksg ) {
    return getDao().getMinDateKsg( ksg );
  }
  
  // Constructors  
  
  /** Creates new form PrzyjecieJPanel */
  public PrzywybJPanel() {
    initFormat();
    initComponents();    
    updateComponents();
  }
  
  /** Creates new form PacwybJPanel */
  public PrzywybJPanel( List przyjecia ) {
    setPrzyjecia( przyjecia );
    initFormat();
    initComponents();    
    updateComponents();
  }
  
  /** Creates new form PacwybJPanel */
  public PrzywybJPanel( DAO dao ) {
    setDao( dao );
    if ( getDao() != null ) {
      setPrzyjecia( 
        getDao().getPrzyjecieByDatyAndLekarz( 
           getMinDateKsg( getCurrentKsg() ), 
           new Date(), null, getCurrentKsg() ) );
      setLekarze( getDao().getAllLekarz() );
    }  
    initFormat();
    initComponents();    
    updateComponents();
  }
  
  /** Creates new form PacwybJPanel */
  public PrzywybJPanel( DAO dao, boolean client ) {
    setDao( dao );
    setClient( client );
    if ( getDao() != null ) {
      setPrzyjecia( 
        getDao().getPrzyjecieByDatyAndLekarz( 
           getMinDateKsg( getCurrentKsg() ), 
           new Date(), null, getCurrentKsg() ) );
      setLekarze( getDao().getAllLekarz() );
    }  
    initFormat();
    initComponents();    
    updateComponents();
  }
  
  /** Creates new form PacwybJPanel */
  public PrzywybJPanel( List przyjecia, DAO dao ) {
    setDao( dao );
    if ( przyjecia == null ) {
      if ( getDao() != null ) {
        setPrzyjecia( 
          getDao().getPrzyjecieByDatyAndLekarz( 
            getMinDateKsg( getCurrentKsg() ), 
            new Date(), null, getCurrentKsg() ) );  
      }  
    }
    else {
      setPrzyjecia( przyjecia );
    }          
    if ( getDao() != null ) {
      setLekarze( getDao().getAllLekarz() );
    } 
    initFormat();
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
    dataodField = new javax.swing.JFormattedTextField( dateFormat );
    datadoField = new javax.swing.JFormattedTextField( dateFormat );
    lekarzBox = new javax.swing.JComboBox( getLekarzeAsArray() );
    nleklButton = new javax.swing.JButton();
    nazwiskoField = new javax.swing.JTextField();
    ksgField = new javax.swing.JTextField();
    buttonPanel = new javax.swing.JPanel();
    deleteButton = new javax.swing.JButton();
    addButton = new javax.swing.JButton();
    searchButton = new javax.swing.JButton();
    wybLabel = new javax.swing.JLabel();

    listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wyb\u00f3r przyj\u0119cia pacjenta z listy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
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
        .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        .addContainerGap())
    );
    listPanelLayout.setVerticalGroup(
      listPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(listPanelLayout.createSequentialGroup()
        .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        .addContainerGap())
    );

    editPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wyb\u00f3r przyj\u0119cia wed\u0142ug danych", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    dataodField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Od daty", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    dataodField.setFont(new java.awt.Font("Tahoma", 0, 12));
    dataodField.setValue( getMinDateKsg( getCurrentKsg() ) );
    dataodField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        dataodFieldKeyReleased(evt);
      }
    });

    datadoField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Do daty ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    datadoField.setFont(new java.awt.Font("Tahoma", 0, 12));
    datadoField.setValue( new Date() );
    datadoField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        datadoFieldKeyReleased(evt);
      }
    });

    lekarzBox.setFont(new java.awt.Font("Tahoma", 0, 12));
    lekarzBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lekarz prowadz\u0105cy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    lekarzBox.setSelectedItem( null );
    lekarzBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        lekarzBoxItemStateChanged(evt);
      }
    });

    nleklButton.setFont(new java.awt.Font("Tahoma", 0, 12));
    nleklButton.setText("...");
    nleklButton.setToolTipText("zerowanie wyboru lekarza prowadz\u0105cego");
    nleklButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nleklButtonActionPerformed(evt);
      }
    });

    nazwiskoField.setFont(new java.awt.Font("Tahoma", 0, 12));
    nazwiskoField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nazwisko pacjenta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    nazwiskoField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        nazwiskoFieldKeyReleased(evt);
      }
    });

    ksgField.setFont(new java.awt.Font("Tahoma", 0, 12));
    ksgField.setText(getCurrentKsg());
    ksgField.setToolTipText("rok do kt\u00f3rego przynale\u017cy numer ksi\u0119gi oddzia\u0142owej");
    ksgField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rok", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    ksgField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        ksgFieldKeyReleased(evt);
      }
    });

    org.jdesktop.layout.GroupLayout editPanelLayout = new org.jdesktop.layout.GroupLayout(editPanel);
    editPanel.setLayout(editPanelLayout);
    editPanelLayout.setHorizontalGroup(
      editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(editPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(org.jdesktop.layout.GroupLayout.TRAILING, editPanelLayout.createSequentialGroup()
            .add(dataodField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(datadoField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(editPanelLayout.createSequentialGroup()
            .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
              .add(org.jdesktop.layout.GroupLayout.LEADING, nazwiskoField)
              .add(org.jdesktop.layout.GroupLayout.LEADING, lekarzBox, 0, 214, Short.MAX_VALUE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
              .add(ksgField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
              .add(org.jdesktop.layout.GroupLayout.LEADING, nleklButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))))
        .addContainerGap())
    );
    editPanelLayout.setVerticalGroup(
      editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, editPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(dataodField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(datadoField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(editPanelLayout.createSequentialGroup()
            .add(7, 7, 7)
            .add(lekarzBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(editPanelLayout.createSequentialGroup()
            .add(16, 16, 16)
            .add(nleklButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(nazwiskoField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(ksgField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    buttonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Operacje na elementach listy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
    deleteButton.setFont(new java.awt.Font("Tahoma", 0, 12));
    deleteButton.setForeground(new java.awt.Color(153, 0, 0));
    deleteButton.setText("Usu\u0144 zaznaczone przyj\u0119cie");
    deleteButton.setToolTipText("usuni\u0119cie elementu wybranego na li\u015bcie ");
    deleteButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteButtonActionPerformed(evt);
      }
    });

    addButton.setFont(new java.awt.Font("Tahoma", 0, 12));
    addButton.setForeground(new java.awt.Color(0, 102, 51));
    addButton.setText("Dodaj nowe przyj\u0119cie");
    addButton.setToolTipText("dodanie nowego elementu do listy ");
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addButtonActionPerformed(evt);
      }
    });

    searchButton.setFont(new java.awt.Font("Tahoma", 0, 12));
    searchButton.setText("Szukaj wed\u0142ug danych");
    searchButton.setToolTipText("szukanie przyj\u0119cia pacjenta wed\u0142ug wpisanych poni\u017cej danych");
    searchButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        searchButtonActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout buttonPanelLayout = new org.jdesktop.layout.GroupLayout(buttonPanel);
    buttonPanel.setLayout(buttonPanelLayout);
    buttonPanelLayout.setHorizontalGroup(
      buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(buttonPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(deleteButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
          .add(addButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
          .add(searchButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
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
        .add(searchButton)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    wybLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
    wybLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wybrane przyj\u0119cie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .add(listPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(wybLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
          .add(org.jdesktop.layout.GroupLayout.LEADING, editPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(org.jdesktop.layout.GroupLayout.LEADING, buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(org.jdesktop.layout.GroupLayout.LEADING, listPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(layout.createSequentialGroup()
            .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(editPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(wybLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void ksgFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ksgFieldKeyReleased
    nazwiskoField.setText( null );
    updateComponents();    
  }//GEN-LAST:event_ksgFieldKeyReleased

  private void nleklButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nleklButtonActionPerformed
    lekarzBox.setSelectedItem( null );
  }//GEN-LAST:event_nleklButtonActionPerformed

  private void lekarzBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lekarzBoxItemStateChanged
    nazwiskoField.setText( null );    
    updateComponents();
  }//GEN-LAST:event_lekarzBoxItemStateChanged

  private void dataodFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataodFieldKeyReleased
    nazwiskoField.setText( null );    
    updateComponents();
  }//GEN-LAST:event_dataodFieldKeyReleased

  private void nazwiskoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nazwiskoFieldKeyReleased
    dataodField.setText( "" );
    datadoField.setText( "" );
    lekarzBox.setSelectedItem( null );
    ksgField.setText( null );
    updateComponents();
  }//GEN-LAST:event_nazwiskoFieldKeyReleased

  private void datadoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datadoFieldKeyReleased
    nazwiskoField.setText( null );
    updateComponents();
  }//GEN-LAST:event_datadoFieldKeyReleased

  private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
    // Poszukiwania wed³ug nazwiska
    this.nowePrzyjecie = false;
    if ( !nazwiskoField.getText().equals("") ) {
      String nazwisko = nazwiskoField.getText().trim() + "%";
      if ( getDao() != null ) {
        setPrzyjecia( getDao().getPrzyjecieByNazwisko( nazwisko ) );  
        setListModel();
      }  
    }        
    // Poszukiwania wed³ug daty przyjêcia i lekarza
    Date   dataOd = null;      
    Date   dataDo = null;
    Lekarz lekarz = (Lekarz)lekarzBox.getSelectedItem();
    String ksg    = ksgField.getText();
    if ( ksg != null ) {
      ksg = ksg.trim();
      if ( ksg.equals("") ) {
        ksg = "%";
      }
    }
    else {
      ksg = "%";
    }
    // Data od
    if ( !dataodField.getText().equals("")  ) {       
      try { 
        dataodField.commitEdit();
        if ( dataodField.getValue() != null ) {
          dataOd = (Date)dataodField.getValue();
        }  
      }
      catch ( java.text.ParseException e) {
        dataodField.setText( null );
        e.printStackTrace();  
      }        
    }
    // Data do
    if ( !datadoField.getText().equals("")  ) {       
      try { 
        datadoField.commitEdit();
        if ( datadoField.getValue() != null ) {
          dataDo = (Date)datadoField.getValue();
        }  
      }
      catch ( java.text.ParseException e) {
        datadoField.setText( null );
        e.printStackTrace();  
      }        
    }
    
    if ( ( dataOd != null  ) && ( dataDo != null ) ) {
      if ( getDao() != null ) {
        setPrzyjecia( getDao().getPrzyjecieByDatyAndLekarz( 
                         dataOd, dataDo, lekarz, ksg ) );  
        setListModel();         
      }  
    }
  }//GEN-LAST:event_searchButtonActionPerformed

  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    // dodanie nowego przyjêcia z nowym pacjentem
    this.nowePrzyjecie = true;
    String ksg = new SimpleDateFormat("yy").format( new Date() );
    Pacjent pacjent = new Pacjent(0, "");
    Long maxg = 1L; //getDao().getMaxNrGlowny() + 1;
    Long maxo = getDao().getMaxNrOddzial() + 1;
    setPrzyjecie( 
      new Przyjecie( 0, maxg, maxo, ksg, pacjent, new Date() ) );
    if ( ( getPrzyjecie() != null ) && ( getDao() != null ) ) {
      if ( getPrzyjecie().getPacjent() != null ) {
        getDao().updatePacjent( getPrzyjecie().getPacjent() );
      }
      getDao().updatePrzyjecie( getPrzyjecie() );
    }
    if ( getPrzyjecia() != null ) {
      getPrzyjecia().add( getPrzyjecie() );
    }
    listModel.addElement( getPrzyjecie() );    
    updateComponents();
    editedList.setSelectedValue( getPrzyjecie(), true );     
  }//GEN-LAST:event_addButtonActionPerformed

  private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    // usuniêcie przyjêcia
    this.nowePrzyjecie = false;
    if ( ( getPrzyjecie() != null ) && ( getDao() != null ) ) { 
      getDao().deletePrzyjecie( getPrzyjecie() );      
    }
    if ( ( getPrzyjecia() != null ) && ( getPrzyjecie() != null ) ) {
      getPrzyjecia().remove( getPrzyjecie() );
    }  
    if (  getPrzyjecieSelected() != null ) {
      listModel.removeElement( getPrzyjecieSelected() );
    }              
    editedList.updateUI();        
  }//GEN-LAST:event_deleteButtonActionPerformed

  private void editedListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_editedListValueChanged
    // ...
    setPrzyjecie();  
    updateComponents();
  }//GEN-LAST:event_editedListValueChanged
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JFormattedTextField datadoField;
  private javax.swing.JFormattedTextField dataodField;
  private javax.swing.JButton deleteButton;
  private javax.swing.JPanel editPanel;
  private javax.swing.JList editedList;
  private javax.swing.JScrollPane jScrollPane;
  private javax.swing.JTextField ksgField;
  private javax.swing.JComboBox lekarzBox;
  private javax.swing.JPanel listPanel;
  private javax.swing.JTextField nazwiskoField;
  private javax.swing.JButton nleklButton;
  private javax.swing.JButton searchButton;
  private javax.swing.JLabel wybLabel;
  // End of variables declaration//GEN-END:variables

}
