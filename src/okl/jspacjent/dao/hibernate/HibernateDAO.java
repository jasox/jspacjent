package okl.jspacjent.dao.hibernate;

import java.util.*;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.*;

import org.hibernate.*;

import okl.jspacjent.dao.*;
import okl.jspacjent.model.*;

/**
 * @author janusz.swol@lot.pl
 *
 * Implementacja Hibernate Dao dla naszej aplikacji.
 */
public class HibernateDAO implements okl.jspacjent.dao.DAO {

  // Fields
  
  // org.hibernate.SessionFactory
  private SessionFactory sessionFactory;

  // org.springframework.orm.hibernate3.HibernateTemplate
  private HibernateTemplate hibernateTemplate;
  
  // Constructors

  /** */
  public HibernateDAO() {
  }

  /** */
  public HibernateDAO(SessionFactory sessionFactory) {
    this.sessionFactory    = sessionFactory;
    this.hibernateTemplate = new HibernateTemplate( sessionFactory );
  }
  
  // Access methods

  /**
   * @return the sessionFactory
   */
  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  /**
   * @param sessionFactory the sessionFactory to set
   */
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory    = sessionFactory;
    this.hibernateTemplate = new HibernateTemplate( sessionFactory );
  }

  /**
   * @return the hibernateTemplate
   */
  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }

  /**
   * @set the hibernateTemplate
   */
  public void setHibernateTemplate() {
    this.hibernateTemplate = new HibernateTemplate( sessionFactory );
  }
  
  /** zamkniêcie po³¹czenia z baz¹ danych i zakoñczenie pracy */
  public void closeDatabaseConnection() {
    getSessionFactory().close();
  };

  // ------------------------ DAO Method -----------------------------
  
  // PACJENCI
  
  /** Zwraca wszystkich pacjentów zapisanych w bazie */
  public List getAllPacjent() {    
    List pacjenci =
      getHibernateTemplate().find(
        "from " + Pacjent.class.getName() + 
        " p order by p.nazwisko, p.imie");      
    return pacjenci;    
  }
  
  /** Zwraca pacjentów o podanym peselu zapisanych w bazie */
  public List getPacjentByPesel( Long pesel ) {
    List pacjenci =      
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getPacjentByPesel",
        new String[] {"pesel"},
        new Object[] { pesel }
      );      
    return pacjenci;
  };
  
  /** Zwraca pacjentów o podanym nazwisku zapisanych w bazie */
  public List getPacjentByNazwisko( String nazwisko ) {
    List pacjenci =      
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getPacjentByNazwisko",
        new String[] {"nazwisko"},
        new Object[] { nazwisko }
      );
    return pacjenci;
  };
  
  /** Zapisuje pacjentów do bazy  */
  public void updatePacjentList( List pacjenci ) {
     getHibernateTemplate().saveOrUpdateAll( pacjenci );
  };
  
  /** Zapisuje pacjenta do bazy  */
  public void updatePacjent( Pacjent pacjent ) {
    getHibernateTemplate().saveOrUpdate( pacjent );
  };
  
  /** Usuwa pacjenta z bazy  */
  public void deletePacjent( Pacjent pacjent ) {
    getHibernateTemplate().delete( pacjent );    
  };
  
  // PRZYJÊCIA
  
  /** Zwraca wszystkie przyjêcia pacjentów zapisane w bazie */
  public List getAllPrzyjecie() {
    // ...
    List przyjecia =
      getHibernateTemplate().find(
        "from " + Przyjecie.class.getName() + " p order by p.dataPrz");      
    return przyjecia;
  };
  
  /** Zwraca przyjêcia pacjentów o podanym peselu zapisane w bazie */
  public List getPrzyjecieByPesel( Long pesel ) {
    List przyjecia =      
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getPrzyjecieByPesel",
        new String[] {"pesel"},
        new Object[] { pesel }
      );      
    return przyjecia;
  };
  
  /** Zwraca przyjecia pacjentów o podanym nazwisku zapisane w bazie */
  public List getPrzyjecieByNazwisko( String nazwisko ) {
    List przyjecia =      
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getPrzyjecieByNazwisko",
        new String[] {"nazwisko"},
        new Object[] { nazwisko }
      );
    return przyjecia;
  };
  
  /** Zwraca przyjecia pacjentów o podanej dacie przyjêcia 
      zapisane w bazie */
  public List getPrzyjecieByDataPrz( Date dataPrz ) {
    List przyjecia =      
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getPrzyjecieByDataPrz",
        new String[] {"dataPrz"},
        new Object[] { dataPrz }
      );
    return przyjecia;
  };
  
  /** Zwraca przyjecia pacjentów o podanym zakresie dat przyjêcia 
      i wybranym lekarzu zapisane w bazie */
  public List getPrzyjecieByDatyAndLekarz( 
              Date dataOd, Date dataDo, Lekarz lekarz ) {
    List przyjecia; 
    if ( lekarz != null ) {
      przyjecia = 
        getHibernateTemplate().findByNamedQueryAndNamedParam(
          "getPrzyjecieByDatyAndLekarz",
          new String[] {"dataOd", "dataDo", "lekarz"},
          new Object[] { dataOd,  dataDo,    lekarz }
        );
    }
    else {
      przyjecia = 
        getHibernateTemplate().findByNamedQueryAndNamedParam(
          "getPrzyjecieByDaty",
          new String[] {"dataOd", "dataDo"},
          new Object[] { dataOd,  dataDo  }
        );
    }
    return przyjecia;
  };
  
  /** Zwraca przyjecia pacjentów o podanym zakresie dat przyjêcia, 
      wybranym lekarzu i roku ksiêgi oddzia³owej zapisane w bazie */
  public List getPrzyjecieByDatyAndLekarz( 
              Date dataOd, Date dataDo, Lekarz lekarz, String ksg ) {
    List przyjecia; 
    if ( lekarz != null ) {
      przyjecia = 
        getHibernateTemplate().findByNamedQueryAndNamedParam(
          "getPrzyjecieByDatyAndLekarzAndKsg",
          new String[] {"dataOd", "dataDo", "lekarz", "ksg" },
          new Object[] { dataOd,  dataDo,    lekarz,   ksg  }
        );
    }
    else {
      przyjecia = 
        getHibernateTemplate().findByNamedQueryAndNamedParam(
          "getPrzyjecieByDatyAndKsg",
          new String[] {"dataOd", "dataDo", "ksg" },
          new Object[] { dataOd,  dataDo,    ksg  }
        );
    }
    return przyjecia;
  };
  
  /** zwraca maksymalny numer oddzia³owy przyjêæ w bierz¹cym roku */
  public Long getMaxNrOddzial() { 
    Long max = 0L;
    List lp = 
      getHibernateTemplate().findByNamedQuery( "getMaxNrOddzial" );
    if ( lp != null ) {
      if ( !lp.isEmpty() ) {
        max = (Long)lp.iterator().next();
      }  
    }  
    if ( max == null ) { 
      max = 0L;
    }     
    return max;
  };
  
  /** zwraca maksymalny numer g³ówny przyjêæ w bierz¹cym roku */
  public Long getMaxNrGlowny() {
    Long max = 0L;    
    List lp = 
      getHibernateTemplate().findByNamedQuery( "getMaxNrGlowny" );
    if ( lp != null ) {
      if ( !lp.isEmpty() ) {
        max = (Long)lp.iterator().next();
      }  
    }
    if ( max == null ) {
      max = 0L;
    }
    return max;      
  };
  
  /** zwraca minimaln¹ datê przyjêcia dla danego roku ksiêgi */
  public Date getMinDateKsg( String ksg ) {
    Date min = new Date();
    List lp = 
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getMinDateKsg",
        new String[] { "ksg" },
        new Object[] {  ksg  }
      );
    if ( lp != null ) {
      if ( !lp.isEmpty() ) {
        Date d = (Date)lp.iterator().next();
        if ( d != null ) {
          min = d;
        }
      }  
    }
    return min;
  };
  
  /** Zapisuje przyjêcia pacjentów do bazy  */
  public void updatePrzyjecieList( List przyjecia ) {
    getHibernateTemplate().saveOrUpdateAll( przyjecia );
  };
  
  /** Zapisuje przyjêcie pacjenta do bazy  */
  public void updatePrzyjecie( Przyjecie przyjecie ) {
    getHibernateTemplate().saveOrUpdate( przyjecie );
  };
  
  /** Usuwa przyjêcie pacjenta z bazy  */
  public void deletePrzyjecie( Przyjecie przyjecie ) {
    getHibernateTemplate().delete( przyjecie );
  };
  
  // KASY CHORYCH
  
  /** Zwraca wszystkie kasy chorych zapisane w bazie */
  public List getAllKasach() {
    List kasy =
      getHibernateTemplate().find(
        "from " + Kasach.class.getName() + " k order by k.opis" );
    return kasy;
  };
  
  /** Zapisuje kasy chorych do bazy  */
  public void updateKasachList( List kasy ) {
    getHibernateTemplate().saveOrUpdateAll( kasy );
  };
  
  /** Zapisuje kasê chorych do bazy  */
  public void updateKasach( Kasach kasach ) {
    getHibernateTemplate().saveOrUpdate( kasach );
  };
  
  /** Usuwa kasê chorych z bazy  */
  public void deleteKasach( Kasach kasach ) {
    getHibernateTemplate().delete( kasach );
  };
  
  // LEKARZE
  
  /** Zwraca wszystkich lekarzy zapisanych w bazie   */
  public List getAllLekarz() {
    List lekarze =
      getHibernateTemplate().find(
        "from " + Lekarz.class.getName() + " l order by l.nazwisko" );
    return lekarze;    
  }
  
  /** Zapisuje lekarzy do bazy  */
  public void updateLekarzList( List lekarze ) {
    getHibernateTemplate().saveOrUpdateAll( lekarze );
  };
  
  /** Zapisuje lekarza do bazy  */
  public void updateLekarz( Lekarz lekarz ) {
    getHibernateTemplate().saveOrUpdate( lekarz );
  };
  
  /** Usuwa lekarza z bazy  */
  public void deleteLekarz( Lekarz lekarz ) {
    getHibernateTemplate().delete( lekarz );
  };  
  
  // RODZAJE BADAÑ
  
  /** Zwraca wszystkie rodzaje badañ zapisane w bazie  */
  public List getAllRodzbad() {
    List rodzajeBadan =
      getHibernateTemplate().find(       
        "from " + Rodzbad.class.getName() + " r order by r.opis" );
    return rodzajeBadan;
  };
  
  /** Zapisuje rodzaje badañ do bazy  */
  public void updateRodzbadList( List rodzajeBadan ) {    
    getHibernateTemplate().saveOrUpdateAll( rodzajeBadan );          
  };
  
  /** Zapisuje rodzaj badañ do bazy  */
  public void updateRodzbad( Rodzbad rodzbad ) {
    getHibernateTemplate().saveOrUpdate( rodzbad );
  };
  
  /** Usuwa rodzaj badañ z bazy  */
  public void deleteRodzbad( Rodzbad rodzbad ) {
    getHibernateTemplate().delete( rodzbad );
  };
  
  // RODZAJE LECZENIA
  
  /** Zwraca wszystkie rodzaje leczeñ zapisane w bazie  */
  public List getAllRodzlecz() {
    List rodzajeLeczenia =
      getHibernateTemplate().find(       
        "from " + Rodzlecz.class.getName() + " r order by r.opis" );
    return rodzajeLeczenia;
  };
  
  /** Zapisuje rodzaje leczeñ do bazy  */
  public void updateRodzleczList( List rodzajeLeczen ) {    
    getHibernateTemplate().saveOrUpdateAll( rodzajeLeczen );          
  };
  
  /** Zapisuje rodzaj leczeñ do bazy  */
  public void updateRodzlecz( Rodzlecz rodzlecz ) {
    getHibernateTemplate().saveOrUpdate( rodzlecz );
  };
  
  /** Usuwa rodzaj leczeñ z bazy  */
  public void deleteRodzlecz( Rodzlecz rodzlecz ) {
    getHibernateTemplate().delete( rodzlecz );
  };
  
  // ROZPOZNANIA CHOROBOWE
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie  
   *  jako listê stringów
   */
  public List getAllRozpoznanieAsStrings() {
    List rozpoznania =
      getHibernateTemplate().find(
        "select r.opis " +
        "from " + Rozpoznanie.class.getName() + " r order by r.opis" );
    return rozpoznania;
  };
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie  
   *  jako listê stringów, podobne wzglêdem opisu do wzorca
   */
  public List getRozpoznanieAsStringsLike( String rozpoznanie ) {
    List rozpoznania =
      getHibernateTemplate().findByNamedQueryAndNamedParam(
          "getRozpoznanieAsStringsLike",
          new String[] {"rozpoznanie"},
          new Object[] { rozpoznanie }
        );      
    return rozpoznania;
  };
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie */
  public List getAllRozpoznanie(){
    List rozpoznania =
      getHibernateTemplate().find(        
        "from " + Rozpoznanie.class.getName() + " r order by r.opis" );
    return rozpoznania;
  };
  
  /** Zapisuje rozpoznania chorobowe do bazy  */
  public void updateRozpoznanieList( List rozpoznania ){
    getHibernateTemplate().saveOrUpdateAll( rozpoznania );
  };
  
  /** Zapisuje rozpoznanie chorobowe do bazy  */
  public void updateRozpoznanie( Rozpoznanie rozpoznanie ) {
    getHibernateTemplate().saveOrUpdate( rozpoznanie );
  };
  
  /** Usuwa rozpoznanie chorobowe z bazy  */
  public void deleteRozpoznanie( Rozpoznanie rozpoznanie ) {
    getHibernateTemplate().delete( rozpoznanie );
  };
  
  /** Dopisuje rozpoznania z tabeli Przyjecie do Rozpoznania  */
  public void dopiszRozpoznania() {
    Session session = 
      getHibernateTemplate().getSessionFactory().openSession();
    Query query = session.createSQLQuery(
      "INSERT INTO Rozpoznanie( Opis )" +
      "  SELECT DISTINCT rtrim( Rozpwst ) FROM Przyjecie" +
      "  WHERE " +
      "    upper( rtrim( Rozpwst ) ) NOT IN" +
      "      ( SELECT DISTINCT upper( rtrim( Opis ) ) FROM Rozpoznanie )" +
      "    AND " +
      "    Rozpwst IS NOT NULL" + 
      "    AND " +
      "    rtrim( Rozpwst ) NOT LIKE '' ");
    query.executeUpdate();
    session.close();
  };
  
  // SKIEROWANIA
  
  /** Zwraca wszystkie skierowania zapisane w bazie    
   * ( miejsca z których i do których kierowani s¹ pacjenci )   
   *  jako listê stringów 
   */
  public List getAllSkierowAsStrings() {
    List skierowania =
      getHibernateTemplate().find(
        "select s.opis " +
        "from " + Skierow.class.getName() + " s order by s.opis" );
    return skierowania;
  }
  
  /** Zwraca wszystkie skierowania zapisane w bazie
   * ( miejsca z których i do których kierowani s¹ pacjenci )   
   *  jako listê stringów, podobne wzglêdem opisu do wzorca
   */
  public List getSkierowAsStringsLike( String skierow ) {
    List skierowania =
      getHibernateTemplate().findByNamedQueryAndNamedParam(
          "getSkierowAsStringsLike",
          new String[] {"skierow"},
          new Object[] { skierow }
        );      
    return skierowania;
  };
  
  /** Zwraca wszystkie skierowania zapisane w bazie 
   *  ( miejsca z których i do których kierowani s¹ pacjenci ) 
   */
  public List getAllSkierow() {
    List skierowania =
      getHibernateTemplate().find(       
        "from " + Skierow.class.getName() + " s order by s.opis" );
    return skierowania;    
  };
  
  /** Zapisuje skierowania do bazy  */
  public void updateSkierowList( List skierowania ) {
    getHibernateTemplate().saveOrUpdateAll( skierowania );
  };
  
  /** Zapisuje skierowanie do bazy  */
  public void updateSkierow( Skierow skierow ) {
    getHibernateTemplate().saveOrUpdate( skierow );
  };
  
  /** Usuwa skierowanie z bazy  */
  public void deleteSkierow( Skierow skierow ) {
    getHibernateTemplate().delete( skierow );
  }; 
  
  /** Dopisuje skierowania z tabeli Przyjecie do Skierow  */
  public void dopiszSkierowania() {
    Session session = 
      getHibernateTemplate().getSessionFactory().openSession();
    Query query = session.createSQLQuery(
      "INSERT INTO Skierow( Opis )" +
      "  SELECT DISTINCT rtrim( Sskierow ) FROM Przyjecie" +
      "  WHERE " +
      "    upper( rtrim( Sskierow ) ) " +
      "      NOT IN ( SELECT DISTINCT upper( rtrim( Opis ) ) FROM Skierow )" +
      "    AND " +
      "    Sskierow IS NOT NULL" + 
      "    AND " +
      "    rtrim( Sskierow ) NOT LIKE '' ");
    query.executeUpdate();
    session.close();
  };
  
  // BADANIA
  
  /** Zwraca wszystkie badania zapisane w bazie  */
  public List getAllBadanie() {
    List badania =
      getHibernateTemplate().find(       
        "from " + Badanie.class.getName() + " b order by b.data" );
    return badania; 
  };
  
  /** Zwraca badania dla danego przyjêcia zapisane w bazie  */
  public List getBadanieByPrzyjecie( Przyjecie przyjecie ) {
    List badania =      
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getBadanieByPrzyjecie",
        new String[] {"przyjecie"},
        new Object[] { przyjecie }
      );
    return badania;    
  };
  
  /** Zapisuje badania do bazy  */
  public void updateBadanieList( List badania ) {
     getHibernateTemplate().saveOrUpdateAll( badania );
  };
  
  /** Zapisuje badanie do bazy  */
  public void updateBadanie( Badanie badanie ) {
    getHibernateTemplate().saveOrUpdate( badanie );
  };
  
  /** Usuwa skierowanie z bazy  */
  public void deleteBadanie( Badanie badanie ) {
    getHibernateTemplate().delete( badanie );    
  }; 
  
  // LECZENIE
  
  /** Zwraca wszystkie leczenia zapisane w bazie  */
  public List getAllLeczenie() {
    List leczenia =
      getHibernateTemplate().find(       
        "from " + Leczenie.class.getName() + " l order by l.data" );
    return leczenia;
  };
  
  /** Zwraca leczenia dla danego przyjêcia zapisane w bazie  */
  public List getLeczenieByPrzyjecie( Przyjecie przyjecie ) {
    List leczenia =      
      getHibernateTemplate().findByNamedQueryAndNamedParam(
        "getLeczenieByPrzyjecie",
        new String[] {"przyjecie"},
        new Object[] { przyjecie }
      );
    return leczenia;
  };
  
  /** Zapisuje leczenia do bazy  */
  public void updateLeczenieList( List leczenia ) {
    getHibernateTemplate().saveOrUpdateAll( leczenia );    
  };
  
  /** Zapisuje leczenie do bazy  */
  public void updateLeczenie( Leczenie leczenie ) {
    getHibernateTemplate().saveOrUpdate( leczenie );    
  };
  
  /** Usuwa leczenie z bazy  */
  public void deleteLeczenie( Leczenie leczenie ) {
    getHibernateTemplate().delete( leczenie );    
  };  

}
