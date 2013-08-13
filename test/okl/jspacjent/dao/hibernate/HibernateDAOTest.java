package okl.jspacjent.dao.hibernate;

import java.util.*;

import okl.jspacjent.dao.*;
import okl.jspacjent.model.*;
import okl.jspacjent.guiswing.*;
import okl.utility.swing.Console;

import org.hibernate.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

/**
 * HibernateDao Test.
 *
 * @author Janusz Swó³
 */
public class HibernateDAOTest extends AbstractDAOTest {

  protected String[] getConfigLocations() {
    return new String[]{"testApplicationContext.xml"};
  }

  protected DAO createDao() {
    applicationContext =
      new ClassPathXmlApplicationContext( getConfigLocations() );
    DAO dao = (HibernateDAO)applicationContext.getBean("dao");
    return dao;
  }

  protected void beforeAssertions() {
    if ( dao == null ) {
      dao = createDao();
    }
    ((HibernateDAO) dao).getHibernateTemplate().flush();
  }


  protected void onTearDownInTransaction() throws Exception {
    super.onTearDownInTransaction();
    clearCache();
  }

  private void clearCache() {
    /*
    SessionFactory sessionFactory =
      ((HibernateDAO) dao).getSessionFactory();
    */
  }

  public void testPacjent() {
    //beforeAssertions();
    // ...
    List pacjenci = dao.getAllPacjent();
    List kasy     = dao.getAllKasach();
    //
    List lekarze     = dao.getAllLekarz();
    List skierowania = dao.getAllSkierowAsStrings();
    List rozpoznania = dao.getAllRozpoznanieAsStrings();
    //
    List rodzajeBadan    = dao.getAllRodzbad();
    List rodzajeLeczenia = dao.getAllRodzlecz();
    /*
    RodzbadJPanel rodzbadPanel = new RodzbadJPanel( dao );
    RodzbadJDialog rodzbadDialog = 
      new RodzbadJDialog( new java.awt.Frame(), true, rodzbadPanel );
    rodzbadDialog.setVisible(true);
    */
    /*
    RodzleczJPanel rodzleczPanel = new RodzleczJPanel( dao );
    RodzleczJDialog rodzleczDialog = 
      new RodzleczJDialog( new java.awt.Frame(), true, rodzleczPanel );
    rodzleczDialog.setVisible(true);
    */    
    
    Iterator iter = pacjenci.iterator();
    Pacjent pacjent; 
    Przyjecie przyjecie;
    
    //while ( iter.hasNext() ) {      
      pacjent = (Pacjent)iter.next();
      Set przyjecia = pacjent.getPrzyjecies();
      Iterator iter1 = pacjent.getPrzyjecies().iterator();
      przyjecie = (Przyjecie)iter1.next();
      
      System.out.println(pacjent + " " + pacjent.getKasach() );
      
      PacjentJPanel pacjentPanel = new PacjentJPanel(pacjent, kasy );
      PrzyjecieJPanel przyjeciePanel = 
        new PrzyjecieJPanel(przyjecie, lekarze, skierowania, rozpoznania);
      PacjentDialogUI pacjentDialog =
        new PacjentDialogUI(null, true, pacjentPanel, przyjeciePanel);      
      pacjentDialog.setVisible(true);           
      
      System.out.println(pacjent + " " + pacjent.getKasach() );
    //}
     
    /*  
    iter = kasy.iterator(); 
    Kasach kasa; 
    while ( iter.hasNext() ) {   
      kasa = (Kasach)iter.next();
      System.out.println(kasa);
    }
    */
  }  
  
}
