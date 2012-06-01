package okl.jspacjent.dao;


import java.util.*;

import okl.jspacjent.model.*;

/**
 * Interfejs definiuj¹cy wszystkie metody dostêpu do danych 
 *  i metody biznesowe w naszej aplikacji.
 *
 * Implementation: <br/>
 *
 * @author janusz.swol@lot.pl
 */
public interface DAO {
  
  /** zamkniêcie po³¹czenia z baz¹ danych i zakoñczenie pracy */
  void closeDatabaseConnection();
  
  // PACJENCI

  /** Zwraca wszystkich pacjentów zapisanych w bazie */
  List getAllPacjent();
  
  /** Zwraca pacjentów o podanym peselu zapisanych w bazie */
  List getPacjentByPesel( Long pesel );
  
  /** Zwraca pacjentów o podanym nazwisku zapisanych w bazie */
  List getPacjentByNazwisko( String nazwisko );
  
  /** Zapisuje pacjentów do bazy  */
  void updatePacjentList( List pacjenci );
  
  /** Zapisuje pacjenta do bazy  */
  void updatePacjent( Pacjent pacjent );
  
  /** Usuwa pacjenta z bazy  */
  void deletePacjent( Pacjent pacjent );
  
  // PRZYJÊCIA  
  
  /** Zwraca wszystkie przyjêcia pacjentów zapisane w bazie */
  List getAllPrzyjecie();
  
  /** Zwraca przyjêcia pacjentów o podanym peselu zapisane w bazie */
  List getPrzyjecieByPesel( Long pesel );
  
  /** Zwraca przyjecia pacjentów o podanym nazwisku zapisane w bazie */
  List getPrzyjecieByNazwisko( String nazwisko );
  
  /** Zwraca przyjecia pacjentów o podanej dacie przyjêcia 
      zapisane w bazie */
  List getPrzyjecieByDataPrz( Date dataPrz );
  
  /** Zwraca przyjecia pacjentów o podanym zakresie dat przyjêcia 
      i wybranym lekarzu zapisane w bazie */
  List getPrzyjecieByDatyAndLekarz( 
    Date dataOd, Date dataDo, Lekarz lekarz );
  
  /** Zwraca przyjecia pacjentów o podanym zakresie dat przyjêcia, 
      wybranym lekarzu i roku ksiêgi oddzia³owej zapisane w bazie */
  List getPrzyjecieByDatyAndLekarz( 
    Date dataOd, Date dataDo, Lekarz lekarz, String ksg );
  
  /** zwraca maksymalny numer oddzia³owy przyjêæ w bierz¹cym roku */
  Long getMaxNrOddzial();
  
  /** zwraca maksymalny numer g³ówny przyjêæ w bierz¹cym roku */
  Long getMaxNrGlowny();
  
  /** zwraca minimaln¹ datê przyjêcia dla danego roku ksiêgi */
  Date getMinDateKsg( String ksg );
  
  /** Zapisuje przyjêcia pacjentów do bazy  */
  void updatePrzyjecieList( List przyjecia );
  
  /** Zapisuje przyjêcie pacjenta do bazy  */
  void updatePrzyjecie( Przyjecie przyjecie );
  
  /** Usuwa przyjêcie pacjenta z bazy  */
  void deletePrzyjecie( Przyjecie przyjecie );
  
  // KASY CHORYCH
  
  /** Zwraca wszystkie kasy chorych zapisane w bazie */
  List getAllKasach();
  
  /** Zapisuje kasy chorych do bazy  */
  void updateKasachList( List kasy );
  
  /** Zapisuje kasê chorych do bazy  */
  void updateKasach( Kasach kasach );
  
  /** Usuwa kasê chorych z bazy  */
  void deleteKasach( Kasach kasach );
  
  // LEKARZE
  
  /** Zwraca wszystkich lekarzy zapisanych w bazie   */
  List getAllLekarz();
  
  /** Zapisuje lekarzy do bazy  */
  void updateLekarzList( List lekarze );
  
  /** Zapisuje lekarza do bazy  */
  void updateLekarz( Lekarz lekarz );
  
  /** Usuwa lekarza z bazy  */
  void deleteLekarz( Lekarz lekarz );    
  
  // RODZAJE BADAÑ
  
  /** Zwraca wszystkie rodzaje badañ zapisane w bazie  */
  List getAllRodzbad();
  
  /** Zapisuje rodzaje badañ do bazy  */
  void updateRodzbadList( List rodzajeBadan );
  
  /** Zapisuje rodzaj badañ do bazy  */
  void updateRodzbad( Rodzbad rodzbad );
  
  /** Usuwa rodzaj badañ z bazy  */
  void deleteRodzbad( Rodzbad rodzbad );
  
  // RODZAJE LECZENIA
  
  /** Zwraca wszystkie rodzaje leczeñ zapisane w bazie */
  List getAllRodzlecz();
  
  /** Zapisuje rodzaje leczeñ do bazy  */
  void updateRodzleczList( List rodzajeLeczen );
  
  /** Zapisuje rodzaj leczeñ do bazy  */
  void updateRodzlecz( Rodzlecz rodzlecz );
  
  /** Usuwa rodzaj leczeñ z bazy  */
  void deleteRodzlecz( Rodzlecz rodzlecz );
  
  // ROZPOZNANIA
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie  
   *  jako listê stringów 
   */
  List getAllRozpoznanieAsStrings();  
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie  
   *  jako listê stringów, podobne wzglêdem opisu do wzorca
   */
  List getRozpoznanieAsStringsLike( String rozpoznanie );
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie */
  List getAllRozpoznanie();
  
  /** Zapisuje rozpoznania chorobowe do bazy  */
  void updateRozpoznanieList( List rozpoznania );
  
  /** Zapisuje rozpoznanie chorobowe do bazy  */
  void updateRozpoznanie( Rozpoznanie rozpoznanie );
  
  /** Usuwa rozpoznanie chorobowe z bazy  */
  void deleteRozpoznanie( Rozpoznanie rozpoznanie );
  
  /** Dopisuje rozpoznania z tabeli Przyjecie do Rozpoznanie  */
  void dopiszRozpoznania();
  
  // SKIEROWANIA
  
  /** Zwraca wszystkie skierowania zapisane w bazie
   * ( miejsca z których i do których kierowani s¹ pacjenci )   
   *  jako listê stringów  
   */
  List getAllSkierowAsStrings();
  
  /** Zwraca wszystkie skierowania zapisane w bazie
   * ( miejsca z których i do których kierowani s¹ pacjenci )   
   *  jako listê stringów, podobne wzglêdem opisu do wzorca
   */
  List getSkierowAsStringsLike( String skierow );
  
  /** Zwraca wszystkie skierowania zapisane w bazie 
     ( miejsca z których i do których kierowani s¹ pacjenci ) */
  List getAllSkierow();
  
  /** Zapisuje skierowania do bazy  */
  void updateSkierowList( List skierowania );
  
  /** Zapisuje skierowanie do bazy  */
  void updateSkierow( Skierow skierow );
  
  /** Usuwa skierowanie z bazy  */
  void deleteSkierow( Skierow skierow );
  
  /** Dopisuje skierowania z tabeli Przyjecie do Skierow  */
  void dopiszSkierowania();
  
  // BADANIA
  
  /** Zwraca wszystkie badania zapisane w bazie  */
  List getAllBadanie();
  
  /** Zwraca badania dla danego przyjêcia zapisane w bazie  */
  List getBadanieByPrzyjecie( Przyjecie przyjecie );
  
  /** Zapisuje badania do bazy  */
  void updateBadanieList( List badania );
  
  /** Zapisuje badanie do bazy  */
  void updateBadanie( Badanie badanie ) ;
  
  /** Usuwa badanie z bazy  */
  void deleteBadanie( Badanie badanie ); 
  
  // LECZENIA
  
  /** Zwraca wszystkie leczenia zapisane w bazie  */
  List getAllLeczenie();
  
  /** Zwraca leczenia dla danego przyjêcia zapisane w bazie  */
  List getLeczenieByPrzyjecie( Przyjecie przyjecie );
  
  /** Zapisuje leczenia do bazy  */
  void updateLeczenieList( List leczenia );
  
  /** Zapisuje leczenie do bazy  */
  void updateLeczenie( Leczenie leczenie );
  
  /** Usuwa leczenie z bazy  */
  void deleteLeczenie( Leczenie leczenie );    

}
