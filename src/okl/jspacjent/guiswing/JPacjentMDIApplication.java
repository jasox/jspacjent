/*
 * JPacjentMDIApplication.java
 *
 * Created on 11 grudzieñ 2006, 14:59
 */
package okl.jspacjent.guiswing;

import okl.jspacjent.model.*;
import okl.jspacjent.dao.*;
import okl.jspacjent.dao.hibernate.*;
import okl.jspacjent.utility.*;

import java.util.*;
import javax.swing.*;
import java.io.*;
import java.text.*;

import org.springframework.context.*;
import org.springframework.context.support.*;

/**
 *
 * @author  janusz.swol@lot.pl
 */
public class JPacjentMDIApplication extends javax.swing.JFrame {
  /** A return status code - returned if Cancel button has been pressed */
  public static final int RET_CANCEL = 0;
  /** A return status code - returned if OK button has been pressed */
  public static final int RET_OK = 1; 
  /** ikona aplikacjii */
  //public IconImage iconImage = new IconImage("WSZB-Logo.jpg");
  
  // Fields
  
  /** data access object for this application */
  private DAO dao;
  
  /** utility class for this application */
  private Utilities util;
  
  /** is client application - read only */
  private boolean client = false;
  
  /** lista pacjentów */
  private List pacjenci;
  
  /** lista przyjêæ pacjentów */
  private List przyjecia;
  
  /** lista kas chorych */
  private List kasy;
  
  /** lista lekarzy */
  private List lekarze;
  
  /** lista miejsc z których i do których kierowani s¹ pacjenci*/
  private List skierowania;
  
  /** lista rozpoznañ chorobowych */
  private List rozpoznania;
  
  /** lista rodzajów badañ */
  private List rodzajeBadan;
  
  /** lista rodzajów leczenia */
  private List rodzajeLeczenia;
  
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
  
  public void setClient( boolean client ) {
    this.client = client;
  }
  
  public boolean isClient() {
    return this.client;
  }

  public void setPacjenci( List pacjenci ) {
    this.pacjenci = pacjenci;
  }
  
  public List getPacjenci() {
    return pacjenci;
  }
  
  public void setPrzyjecia( List przyjecia ) {
    this.przyjecia = przyjecia;
  }
  
  public List getPrzyjecia() {
    return przyjecia;
  }
  
  public List getKasy() {
    return kasy;
  }

  public void setKasy(List kasy) {
    this.kasy = kasy;
  }

  public List getLekarze() {
    return lekarze;
  }

  public void setLekarze(List lekarze) {
    this.lekarze = lekarze;
  }

  public List getRodzajeBadan() {
    return rodzajeBadan;
  }

  public void setRodzajeBadan(List rodzajeBadan) {
    this.rodzajeBadan = rodzajeBadan;
  }

  public List getRodzajeLeczenia() {
    return rodzajeLeczenia;
  }

  public void setRodzajeLeczenia(List rodzajeLeczenia) {
    this.rodzajeLeczenia = rodzajeLeczenia;
  }

  public List getRozpoznania() {
    return rozpoznania;
  }

  public void setRozpoznania(List rozpoznania) {
    this.rozpoznania = rozpoznania;
  }

  public List getSkierowania() {
    return skierowania;
  }

  public void setSkierowania(List skierowania) {
    this.skierowania = skierowania;
  }
  /*
  public void setIconImage( IconImage iconImage ) {
    this.iconImage = iconImage;
  }
  
  public IconImage getIconImage() {
    return this.iconImage;
  }
  */
  
  // Other methods
  
  protected String[] getConfigLocations() {
    if ( isClient() ) {
      return new String[] { "clientApplicationContext.xml" };
    } 
    else {
      return new String[] { "applicationContext.xml" };
    }      
  }    

  protected void createDao() {    
    ClassPathXmlApplicationContext applicationContext =
      new ClassPathXmlApplicationContext( getConfigLocations() );
    dao = (HibernateDAO)applicationContext.getBean("dao");        
  }
  
  protected void createUtilities() {    
    ClassPathXmlApplicationContext applicationContext =
      new ClassPathXmlApplicationContext( getConfigLocations() );
    util = (Utilities)applicationContext.getBean("utilities");    
  }
  
  protected void createLists() {
    // ...
    //setKasy( getDao().getAllKasach() );
    //setLekarze( getDao().getAllLekarz() );    
    //setSkierowania( getDao().getAllSkierowAsStrings() );
    //setRozpoznania( getDao().getAllRozpoznanieAsStrings() );
    //setRodzajeBadan( getDao().getAllRodzbad() );
    //setRodzajeLeczenia( getDao().getAllRodzlecz() );
    // ...
    //setPacjenci( getDao().getAllPacjent() );    
    // ...
  }
  
  protected void updateComponents() {
    if ( getDao() == null ) {
      przyjecieMenu.setEnabled( false );
      etableMenu.setEnabled( false );      
      toolLabel.setText("Brak po³¹czenia z baz¹ danych.");
    }
    else {
      przyjecieMenu.setEnabled( true );
      etableMenu.setEnabled( true ); 
      toolLabel.setText("");
    }
    // ...
    if ( isClient() ) {
      exitarchMenuItem.setEnabled( false );
      etableMenu.setEnabled( false ); 
      operMenu.setEnabled( false ); 
      pacjentMenu.setEnabled( false ); 
      nowepMenuItem.setEnabled( false ); 
      edycjapMenuItem.setEnabled( false ); 
    }
    
  }
  
  protected void setUpLookAndFeel() throws Exception {    
    UIManager.setLookAndFeel(
      UIManager.getSystemLookAndFeelClassName());    
  }
  
  /** Creates new form JPacjentMDIApplication */
  public JPacjentMDIApplication() {    
    SplashJFrame sframe = null;       
    sframe = new SplashJFrame();
    sframe.setVisible(true);
    // ...
    setClient( false );
    try {
      createUtilities();
      setUpLookAndFeel();
      // ...
      createDao();
      createLists();
      initComponents();
      updateComponents();
      // ...
      if ( isClient() ) {
        rapMenuItemAction();        
      }
      else {
        addNowePrzyjecie( true );        
      }  
    }
    catch ( org.springframework.dao.DataAccessException e ) {
      String s = ".";
      if ( isClient() ) {
        s = ",\n lub s¹ k³opoty z sieci¹ komputerow¹.";
      }
      JOptionPane.showMessageDialog(
        null,
        "K³opoty z po³aczeniem z baz¹ danych.\n" +
        " Prawdopodobnie nie dzia³a serwer HSQLDB" + s, 
        "B³¹d!", JOptionPane.ERROR_MESSAGE );
      // ... 
      System.exit(0);        
    }
    catch ( Exception e ) {      
    } 
    finally {
      sframe.setVisible(false); 
      sframe.dispose();
    }
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    bakFileChooser = new javax.swing.JFileChooser();
    mainTabbedPane = new javax.swing.JTabbedPane();
    przypacPanel = new okl.jspacjent.guiswing.PrzypacJPanel();
    mainToolBar = new javax.swing.JToolBar();
    toolLabel = new javax.swing.JLabel();
    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    exitMenuItem = new javax.swing.JMenuItem();
    exitarchMenuItem = new javax.swing.JMenuItem();
    przyjecieMenu = new javax.swing.JMenu();
    nowepMenuItem = new javax.swing.JMenuItem();
    edycjapMenuItem = new javax.swing.JMenuItem();
    rapMenuItem = new javax.swing.JMenuItem();
    pacjentMenu = new javax.swing.JMenu();
    nowypMenuItem = new javax.swing.JMenuItem();
    edycjapaMenuItem = new javax.swing.JMenuItem();
    etableMenu = new javax.swing.JMenu();
    lekarzMenuItem = new javax.swing.JMenuItem();
    kasachMenuItem = new javax.swing.JMenuItem();
    rodzbadMenuItem = new javax.swing.JMenuItem();
    rodzleczMenuItem = new javax.swing.JMenuItem();
    rozpoznanieMenuItem = new javax.swing.JMenuItem();
    skierowMenuItem = new javax.swing.JMenuItem();
    operMenu = new javax.swing.JMenu();
    dopskierItem = new javax.swing.JMenuItem();
    doprozItem = new javax.swing.JMenuItem();
    helpMenu = new javax.swing.JMenu();
    contentMenuItem = new javax.swing.JMenuItem();
    aboutMenuItem = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Ewidencja przyj\u0119\u0107 pacjent\u00f3w");
    mainTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
    mainTabbedPane.setFont(new java.awt.Font("Tahoma", 0, 12));
    mainTabbedPane.addTab("Przyj\u0119cie pacjenta", przypacPanel);

    mainToolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    mainToolBar.setEnabled(false);
    toolLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
    toolLabel.setText("...");
    mainToolBar.add(toolLabel);

    menuBar.setFont(new java.awt.Font("Tahoma", 0, 14));
    fileMenu.setText("File");
    exitMenuItem.setText("Wyj\u015bcie");
    exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitMenuItemActionPerformed(evt);
      }
    });

    fileMenu.add(exitMenuItem);

    exitarchMenuItem.setText(" Archiwizacja danych i Wyj\u015bcie");
    exitarchMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitarchMenuItemActionPerformed(evt);
      }
    });

    fileMenu.add(exitarchMenuItem);

    menuBar.add(fileMenu);

    przyjecieMenu.setText("Przyj\u0119cia pacjent\u00f3w");
    nowepMenuItem.setText("Nowe przyj\u0119cie");
    nowepMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nowepMenuItemActionPerformed(evt);
      }
    });

    przyjecieMenu.add(nowepMenuItem);

    edycjapMenuItem.setText("Edycja zapisanych przyj\u0119\u0107");
    edycjapMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        edycjapMenuItemActionPerformed(evt);
      }
    });

    przyjecieMenu.add(edycjapMenuItem);

    rapMenuItem.setText("Raport z przyj\u0119\u0107");
    rapMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rapMenuItemActionPerformed(evt);
      }
    });

    przyjecieMenu.add(rapMenuItem);

    menuBar.add(przyjecieMenu);

    pacjentMenu.setText("Dane osobowe pacjent\u00f3w");
    nowypMenuItem.setText("Nowe dane osobowe pacjenta");
    nowypMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nowypMenuItemActionPerformed(evt);
      }
    });

    pacjentMenu.add(nowypMenuItem);

    edycjapaMenuItem.setText("Edycja wybranych danych osobowych pacjenta\n");
    edycjapaMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        edycjapaMenuItemActionPerformed(evt);
      }
    });

    pacjentMenu.add(edycjapaMenuItem);

    menuBar.add(pacjentMenu);

    etableMenu.setText("Tabele pomocnicze");
    lekarzMenuItem.setText("Lekarze");
    lekarzMenuItem.setToolTipText("Edycja danych lekarzy pracuj\u0105cych na oddziale");
    lekarzMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        lekarzMenuItemActionPerformed(evt);
      }
    });

    etableMenu.add(lekarzMenuItem);

    kasachMenuItem.setText("Kasy chorych");
    kasachMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        kasachMenuItemActionPerformed(evt);
      }
    });

    etableMenu.add(kasachMenuItem);

    rodzbadMenuItem.setText("Rodzaje bada\u0144");
    rodzbadMenuItem.setToolTipText("Edycja rodzaj\u00f3w bada\u0144");
    rodzbadMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rodzbadMenuItemActionPerformed(evt);
      }
    });

    etableMenu.add(rodzbadMenuItem);

    rodzleczMenuItem.setText("Rodzaje leczenia");
    rodzleczMenuItem.setToolTipText("Edycja rodzaj\u00f3w leczenia");
    rodzleczMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rodzleczMenuItemActionPerformed(evt);
      }
    });

    etableMenu.add(rodzleczMenuItem);

    rozpoznanieMenuItem.setText("Rozpoznania");
    rozpoznanieMenuItem.setToolTipText("Edycja rozpozna\u0144 chorobowych");
    rozpoznanieMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rozpoznanieMenuItemActionPerformed(evt);
      }
    });

    etableMenu.add(rozpoznanieMenuItem);

    skierowMenuItem.setText("Skierowania");
    skierowMenuItem.setToolTipText("Edycja miejsc z kt\u00f3rych i do kt\u00f3rych kierowani s\u0105 pacjenci");
    skierowMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        skierowMenuItemActionPerformed(evt);
      }
    });

    etableMenu.add(skierowMenuItem);

    menuBar.add(etableMenu);

    operMenu.setText("Czynno\u015bci pomocnicze");
    dopskierItem.setText("Dopisanie skierowa\u0144");
    dopskierItem.setToolTipText("dopisanie skierowa\u0144 z tabeli przyj\u0119\u0107 do tabeli skierowa\u0144");
    dopskierItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        dopskierItemActionPerformed(evt);
      }
    });

    operMenu.add(dopskierItem);

    doprozItem.setText("Dopisanie rozpozna\u0144");
    doprozItem.setToolTipText("dopisanie rozpozna\u0144 z tabeli przyj\u0119\u0107 do tabeli rozpozna\u0144");
    doprozItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        doprozItemActionPerformed(evt);
      }
    });

    operMenu.add(doprozItem);

    menuBar.add(operMenu);

    helpMenu.setText("Help");
    contentMenuItem.setText("Contents");
    helpMenu.add(contentMenuItem);

    aboutMenuItem.setText("About");
    aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        aboutMenuItemActionPerformed(evt);
      }
    });

    helpMenu.add(aboutMenuItem);

    menuBar.add(helpMenu);

    setJMenuBar(menuBar);

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
          .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(mainToolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .add(org.jdesktop.layout.GroupLayout.LEADING, mainTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(mainTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 555, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .add(mainToolBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void doprozItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doprozItemActionPerformed
    // ...
    getDao().dopiszRozpoznania();
    JOptionPane.showMessageDialog(
      null, "Dopisanie rozpoznañ dokonane.\n", 
      "Info", JOptionPane.INFORMATION_MESSAGE );
  }//GEN-LAST:event_doprozItemActionPerformed

  private void dopskierItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dopskierItemActionPerformed
    // ...
    getDao().dopiszSkierowania();
    JOptionPane.showMessageDialog(
      null, "Dopisanie skierowañ dokonane.\n", 
      "Info", JOptionPane.INFORMATION_MESSAGE );
  }//GEN-LAST:event_dopskierItemActionPerformed

  private void rapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rapMenuItemActionPerformed
    // ...
    rapMenuItemAction();  
  }//GEN-LAST:event_rapMenuItemActionPerformed

  private void rapMenuItemAction() {
    PrzywybJPanel wybPanel = 
      new PrzywybJPanel( getDao(), isClient() );        
    PrzywybJDialog wybDialog = 
      new PrzywybJDialog(this, true, wybPanel);
    wybDialog.setTitle("Wybór przyjêcia/przyjêæ do wyœwietlenia raportu");
    wybDialog.setVisible(true);     
    if ( wybDialog.getReturnStatus() == RET_OK ) {
      List      przyjecia = wybPanel.getPrzyjecia();
      Przyjecie przyjecie = wybPanel.getPrzyjecie();
      if ( przyjecie != null ) {
        // wyœwietlanie raportu dla jednego przyjêcia
        try {
          String str;
          str = getUtil().headWithTitle("Przyjêcie pacjenta");
          str += "<b><center>Raport z przyjêcia pacjenta</center></b><hr>\n";
          str += przyjecie.toStringHtml();      
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
            "B³¹d w wyœwietleniu danych.\n" + e.getMessage(), 
            "B³¹d!", JOptionPane.ERROR_MESSAGE );
        } 
      }
      else {        
        // wyœwietlanie raportu dla listy przyjêæ
        Iterator iter = przyjecia.iterator();        
        try {
          String str;
          str = getUtil().headWithTitle("Przyjêcia pacjentów");
          str += "<b><center>Raport z przyjêæ pacjentów</center></b><hr>\n";
          while ( iter.hasNext() ) {
            przyjecie = (Przyjecie)iter.next();
            str += przyjecie.toStringHtml();      
          }  
          str += "</body></html>";
          TextFile.write( 
            getUtil().getPathToApplication() + "/przyjecia.html", str);
          String command = 
            getUtil().getPathToViewer() + " " +
            getUtil().getPathToApplication() + "/przyjecia.html";    
          java.lang.Runtime.getRuntime().exec(command);
        }
        catch ( IOException e ) {
          JOptionPane.showMessageDialog(
            null, 
            "B³¹d w wyœwietleniu danych.\n" + e.getMessage(), 
            "B³¹d!", JOptionPane.ERROR_MESSAGE );
        }
      }
    }
  }  
  
  private void exitarchMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitarchMenuItemActionPerformed
    // archiwizacja i wyjœcie
    updateComponents();
    getDao().closeDatabaseConnection();
    /** format wprowadzania daty */  
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
    String current = dateFormat.format( new Date() );
    bakFileChooser.setDialogTitle("Wybierz katalog do archiwizacji");
    bakFileChooser.setCurrentDirectory( new File("c:/") );
    bakFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = 
      bakFileChooser.showOpenDialog( JPacjentMDIApplication.this );
    if ( returnVal == JFileChooser.APPROVE_OPTION ) {
      String fromDir = getUtil().getPathToData();            
      try {        
        File file = bakFileChooser.getSelectedFile();
        String toDir = file.getPath(); 
        toolLabel.setText( " Archiwizowanie danych do : " + toDir );
        // archiwizowanie plików tekstowych z danymi
        TextFile text = new TextFile( fromDir + "/jspacjent.script" );
        text.write( toDir + "/jspacjent-" + current + ".script" );
        text = new TextFile( fromDir + "/jspacjent.log" );
        text.write( toDir + "/jspacjent-" + current + ".log" );
      }
      catch ( IOException e ) {  
        JOptionPane.showMessageDialog(
          null, "B³¹d zapisu danych.\n" + e.getMessage(), 
          "B³¹d!", JOptionPane.ERROR_MESSAGE );
      }
    }     
    System.exit(0);    
  }//GEN-LAST:event_exitarchMenuItemActionPerformed

  private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
    updateComponents();
    JOptionPane.showMessageDialog(
            null,"" +
                 " rights jpasic@wp.pl \n" +
                 " author j.swol@lot.pl \n" +
                 " uses \n" +
                 "      Java5SE \n" +
                 "      org.hibernate \n" + 
                 "      org.springframework \n" +
                 "      org.hsqldb \n" +
                 "", 
            "Program info", JOptionPane.INFORMATION_MESSAGE ); 
  }//GEN-LAST:event_aboutMenuItemActionPerformed

  private void nowepMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nowepMenuItemActionPerformed
    updateComponents();
    addNowePrzyjecie( false );
  }//GEN-LAST:event_nowepMenuItemActionPerformed

  private void edycjapMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edycjapMenuItemActionPerformed
    // Wybór przyjêcia        
    updateComponents();
    PrzywybJPanel wybPanel = new PrzywybJPanel( getDao() );    
    PrzywybJDialog wybDialog = 
      new PrzywybJDialog(this, true, wybPanel);
    wybDialog.setTitle("Wybór przyjêcia do edycji");
    wybDialog.setVisible(true); 
    // Edycja przyjêcia    
    if ( wybDialog.getReturnStatus() == RET_OK ) {          
      Przyjecie przyjecie = wybPanel.getPrzyjecie();
      Pacjent   pacjent = null;
      if ( przyjecie != null ) {
        pacjent   = przyjecie.getPacjent();
      }
      if ( ( przyjecie != null ) && ( pacjent != null ) ) {
        PrzyjecieJPanel przyjeciePanel = 
          new PrzyjecieJPanel( przyjecie, getDao() );
        PacjentJPanel pacjentPanel = 
          new PacjentJPanel( pacjent, getDao() );
        PrzypacJPanel przypacPanel = 
          new PrzypacJPanel( przyjeciePanel, 
                             pacjentPanel, 
                             wybPanel.isNowePrzyjecie(),
                             getUtil() );
        przypacPanel.addPropertyChangeListener(
          new java.beans.PropertyChangeListener() {
            public void propertyChange( java.beans.PropertyChangeEvent evt) {
              tabDialog( evt );
            }
          }); 
        // dodanie do panelu z zak³adkami
        mainTabbedPane.addTab( 
          przyjecie.getDataPrzAsString() + " " + 
          pacjent.getNazwisko() + " " + pacjent.getImie() ,  
          przypacPanel );
        // wybranie dodanej zak³adki
        mainTabbedPane.setSelectedIndex( 
          mainTabbedPane.getTabCount() - 1 );       
        mainTabbedPane.getSelectedComponent().requestFocus();
      }      
    }     
  }//GEN-LAST:event_edycjapMenuItemActionPerformed

  private void nowypMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nowypMenuItemActionPerformed
    // Dodanie nowego pacjenta
    updateComponents();
    Pacjent pacjent = new Pacjent(0, "");
    if ( pacjent != null ) {
      PacjentJPanel pacjentPanel = 
        new PacjentJPanel( pacjent, getDao() );
      PacjentDialog pacjentDialog = 
        new PacjentDialog( this, true, pacjentPanel );
      pacjentDialog.setVisible( true );
    } 
  }//GEN-LAST:event_nowypMenuItemActionPerformed

  private void edycjapaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edycjapaMenuItemActionPerformed
    // Wybór pacjenta        
    updateComponents();
    setPacjenci( getDao().getAllPacjent() );
    PacwybJPanel wybPanel = 
      new PacwybJPanel( getPacjenci(), getDao() );    
    PacwybJDialog wybDialog = 
      new PacwybJDialog(this, true, wybPanel);
    wybDialog.setVisible(true); 
    // Edycja pacjenta
    if ( wybDialog.getReturnStatus() == RET_OK ) {
      Pacjent pacjent = wybPanel.getPacjent();
      if ( pacjent != null ) {
        PacjentJPanel pacjentPanel = 
          new PacjentJPanel( pacjent, getDao() );
        PacjentDialog pacjentDialog = 
          new PacjentDialog( this, true, pacjentPanel );
        pacjentDialog.setVisible( true );
      }      
    }
  }//GEN-LAST:event_edycjapaMenuItemActionPerformed

  private void lekarzMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lekarzMenuItemActionPerformed
    // edycja lekarzy 
    updateComponents();
    LekarzJPanel lekarzPanel = new LekarzJPanel( getDao() );    
    LekarzJDialog lekarzDialog = 
      new LekarzJDialog(this, true, lekarzPanel);
    lekarzDialog.setVisible(true); 
  }//GEN-LAST:event_lekarzMenuItemActionPerformed

  private void kasachMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasachMenuItemActionPerformed
    // edycja kas chorych
    updateComponents();
    KasachJPanel kasachPanel = new KasachJPanel( getDao() );    
    KasachJDialog kasachDialog = 
      new KasachJDialog(this, true, kasachPanel);
    kasachDialog.setVisible(true); 
    setKasy( getDao().getAllKasach() );
  }//GEN-LAST:event_kasachMenuItemActionPerformed

  private void skierowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skierowMenuItemActionPerformed
    // edycja skierowañ 
    updateComponents();
    SkierowJPanel skierowPanel = new SkierowJPanel( getDao() );    
    SkierowJDialog skierowDialog = 
      new SkierowJDialog(this, true, skierowPanel);
    skierowDialog.setVisible(true);   
  }//GEN-LAST:event_skierowMenuItemActionPerformed

  private void rozpoznanieMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rozpoznanieMenuItemActionPerformed
    // edycja rozpoznañ 
    updateComponents();
    RozpoznanieJPanel rozpoznaniePanel = 
      new RozpoznanieJPanel( getDao() );    
    RozpoznanieJDialog rozpoznanieDialog = 
      new RozpoznanieJDialog(this, true, rozpoznaniePanel);
    rozpoznanieDialog.setVisible(true);  
  }//GEN-LAST:event_rozpoznanieMenuItemActionPerformed

  private void rodzleczMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rodzleczMenuItemActionPerformed
    // edycja rodzajów leczenia 
    updateComponents();
    RodzleczJPanel rodzleczPanel = new RodzleczJPanel( getDao() );    
    RodzleczJDialog rodzleczDialog = 
      new RodzleczJDialog(this, true, rodzleczPanel);
    rodzleczDialog.setVisible(true);   
  }//GEN-LAST:event_rodzleczMenuItemActionPerformed

  private void rodzbadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rodzbadMenuItemActionPerformed
    // edycja rodzajów badañ         
    updateComponents();
    RodzbadJPanel rodzbadPanel = new RodzbadJPanel( getDao() );    
    RodzbadJDialog rodzbadDialog = 
      new RodzbadJDialog(this, true, rodzbadPanel);
    rodzbadDialog.setVisible(true);                 
  }//GEN-LAST:event_rodzbadMenuItemActionPerformed
  
  private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
    updateComponents();
    getDao().closeDatabaseConnection();
    System.exit(0);
  }//GEN-LAST:event_exitMenuItemActionPerformed
  
  /** dodaje do zak³adkê z nowym przyjêciem */
  public void addNowePrzyjecie( boolean first ) {    
    updateComponents();
    String ksg = new SimpleDateFormat("yy").format( new Date() );
    Pacjent pacjent = new Pacjent(0, "");      
    Long maxg = 1L;  //getDao().getMaxNrGlowny() + 1;
    Long maxo = getDao().getMaxNrOddzial() + 1;       
    Przyjecie przyjecie =  
      new Przyjecie( 0, maxg, maxo, ksg, pacjent, new Date() );
    if ( ( przyjecie != null ) && ( pacjent != null ) ) {
      PrzyjecieJPanel przyjeciePanel = 
        new PrzyjecieJPanel( przyjecie, getDao() );
      PacjentJPanel pacjentPanel = 
        new PacjentJPanel( pacjent, getDao() );
      PrzypacJPanel przypacPanel = 
        new PrzypacJPanel( przyjeciePanel, pacjentPanel, true );
      // dodanie nas³uchu zmiany w³aœciwoœci
      przypacPanel.addPropertyChangeListener(
        new java.beans.PropertyChangeListener() {
          public void propertyChange(java.beans.PropertyChangeEvent evt) {
            tabDialog( evt );
          }
        });          
      // gdy jest to zaraz po uruchomieniu programu     
      if ( first ) {
        mainTabbedPane.removeTabAt( 
          mainTabbedPane.getTabCount() - 1 );        
      }
      // dodanie do panelu z zak³adkami 
      mainTabbedPane.addTab( "Nowe przyjêcie", przypacPanel );
      // wybranie dodanej zak³adki
      mainTabbedPane.setSelectedIndex( 
        mainTabbedPane.getTabCount() - 1 );
      mainTabbedPane.getSelectedComponent().requestFocus();
    }        
  }  
  
  /** */
  protected void tabDialog( java.beans.PropertyChangeEvent evt ) {
    if ( evt.getPropertyName().equals( "returnStatus" ) ) {
      Integer o = (Integer)evt.getOldValue();
      Integer n = (Integer)evt.getNewValue();
      if ( ( n.intValue() == RET_CANCEL ) || 
           ( n.intValue() == RET_OK ) ) {
        mainTabbedPane.removeTabAt(mainTabbedPane.getSelectedIndex());
      }      
    }   
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater( new Runnable() {
      public void run() {
        new JPacjentMDIApplication().setVisible(true);
      }
    });
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem aboutMenuItem;
  private javax.swing.JMenuItem contentMenuItem;
  private javax.swing.JMenuItem doprozItem;
  private javax.swing.JMenuItem dopskierItem;
  private javax.swing.JMenuItem edycjapMenuItem;
  private javax.swing.JMenuItem edycjapaMenuItem;
  private javax.swing.JMenu etableMenu;
  private javax.swing.JMenuItem exitMenuItem;
  private javax.swing.JMenuItem exitarchMenuItem;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JMenu helpMenu;
  private javax.swing.JMenuItem kasachMenuItem;
  private javax.swing.JMenuItem lekarzMenuItem;
  private javax.swing.JTabbedPane mainTabbedPane;
  private javax.swing.JToolBar mainToolBar;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem nowepMenuItem;
  private javax.swing.JMenuItem nowypMenuItem;
  private javax.swing.JMenu operMenu;
  private javax.swing.JMenu pacjentMenu;
  private javax.swing.JMenu przyjecieMenu;
  private okl.jspacjent.guiswing.PrzypacJPanel przypacPanel;
  private javax.swing.JMenuItem rapMenuItem;
  private javax.swing.JMenuItem rodzbadMenuItem;
  private javax.swing.JMenuItem rodzleczMenuItem;
  private javax.swing.JMenuItem rozpoznanieMenuItem;
  private javax.swing.JMenuItem skierowMenuItem;
  private javax.swing.JLabel toolLabel;
  // End of variables declaration//GEN-END:variables

  private javax.swing.JFileChooser bakFileChooser;
  
}

//JOptionPane.showMessageDialog(
//  null, evt.getPropertyName(), "Hej!", JOptionPane.INFORMATION_MESSAGE );
