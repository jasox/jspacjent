package okl.jspacjent.dao;


import java.util.*;

import okl.jspacjent.model.*;

/**
 * Interfejs definiuj�cy wszystkie metody dost�pu do danych 
 *  i metody biznesowe w naszej aplikacji.
 *
 * Implementation: <br/>
 *
 * @author janusz.swol@lot.pl
 */
public interface DAO {
  
  /** zamkni�cie po��czenia z baz� danych i zako�czenie pracy */
  void closeDatabaseConnection();
  
  // PACJENCI

  /** Zwraca wszystkich pacjent�w zapisanych w bazie */
  List getAllPacjent();
  
  /** Zwraca pacjent�w o podanym peselu zapisanych w bazie */
  List getPacjentByPesel( Long pesel );
  
  /** Zwraca pacjent�w o podanym nazwisku zapisanych w bazie */
  List getPacjentByNazwisko( String nazwisko );
  
  /** Zapisuje pacjent�w do bazy  */
  void updatePacjentList( List pacjenci );
  
  /** Zapisuje pacjenta do bazy  */
  void updatePacjent( Pacjent pacjent );
  
  /** Usuwa pacjenta z bazy  */
  void deletePacjent( Pacjent pacjent );
  
  // PRZYJ�CIA  
  
  /** Zwraca wszystkie przyj�cia pacjent�w zapisane w bazie */
  List getAllPrzyjecie();
  
  /** Zwraca przyj�cia pacjent�w o podanym peselu zapisane w bazie */
  List getPrzyjecieByPesel( Long pesel );
  
  /** Zwraca przyjecia pacjent�w o podanym nazwisku zapisane w bazie */
  List getPrzyjecieByNazwisko( String nazwisko );
  
  /** Zwraca przyjecia pacjent�w o podanej dacie przyj�cia 
      zapisane w bazie */
  List getPrzyjecieByDataPrz( Date dataPrz );
  
  /** Zwraca przyjecia pacjent�w o podanym zakresie dat przyj�cia 
      i wybranym lekarzu zapisane w bazie */
  List getPrzyjecieByDatyAndLekarz( 
    Date dataOd, Date dataDo, Lekarz lekarz );
  
  /** Zwraca przyjecia pacjent�w o podanym zakresie dat przyj�cia, 
      wybranym lekarzu i roku ksi�gi oddzia�owej zapisane w bazie */
  List getPrzyjecieByDatyAndLekarz( 
    Date dataOd, Date dataDo, Lekarz lekarz, String ksg );
  
  /** zwraca maksymalny numer oddzia�owy przyj�� w bierz�cym roku */
  Long getMaxNrOddzial();
  
  /** zwraca maksymalny numer g��wny przyj�� w bierz�cym roku */
  Long getMaxNrGlowny();
  
  /** zwraca minimaln� dat� przyj�cia dla danego roku ksi�gi */
  Date getMinDateKsg( String ksg );
  
  /** Zapisuje przyj�cia pacjent�w do bazy  */
  void updatePrzyjecieList( List przyjecia );
  
  /** Zapisuje przyj�cie pacjenta do bazy  */
  void updatePrzyjecie( Przyjecie przyjecie );
  
  /** Usuwa przyj�cie pacjenta z bazy  */
  void deletePrzyjecie( Przyjecie przyjecie );
  
  // KASY CHORYCH
  
  /** Zwraca wszystkie kasy chorych zapisane w bazie */
  List getAllKasach();
  
  /** Zapisuje kasy chorych do bazy  */
  void updateKasachList( List kasy );
  
  /** Zapisuje kas� chorych do bazy  */
  void updateKasach( Kasach kasach );
  
  /** Usuwa kas� chorych z bazy  */
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
  
  // RODZAJE BADA�
  
  /** Zwraca wszystkie rodzaje bada� zapisane w bazie  */
  List getAllRodzbad();
  
  /** Zapisuje rodzaje bada� do bazy  */
  void updateRodzbadList( List rodzajeBadan );
  
  /** Zapisuje rodzaj bada� do bazy  */
  void updateRodzbad( Rodzbad rodzbad );
  
  /** Usuwa rodzaj bada� z bazy  */
  void deleteRodzbad( Rodzbad rodzbad );
  
  // RODZAJE LECZENIA
  
  /** Zwraca wszystkie rodzaje lecze� zapisane w bazie */
  List getAllRodzlecz();
  
  /** Zapisuje rodzaje lecze� do bazy  */
  void updateRodzleczList( List rodzajeLeczen );
  
  /** Zapisuje rodzaj lecze� do bazy  */
  void updateRodzlecz( Rodzlecz rodzlecz );
  
  /** Usuwa rodzaj lecze� z bazy  */
  void deleteRodzlecz( Rodzlecz rodzlecz );
  
  // ROZPOZNANIA
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie  
   *  jako list� string�w 
   */
  List getAllRozpoznanieAsStrings();  
  
  /** Zwraca wszystkie rozpoznania chorobowe zapisane w bazie  
   *  jako list� string�w, podobne wzgl�dem opisu do wzorca
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
   * ( miejsca z kt�rych i do kt�rych kierowani s� pacjenci )   
   *  jako list� string�w  
   */
  List getAllSkierowAsStrings();
  
  /** Zwraca wszystkie skierowania zapisane w bazie
   * ( miejsca z kt�rych i do kt�rych kierowani s� pacjenci )   
   *  jako list� string�w, podobne wzgl�dem opisu do wzorca
   */
  List getSkierowAsStringsLike( String skierow );
  
  /** Zwraca wszystkie skierowania zapisane w bazie 
     ( miejsca z kt�rych i do kt�rych kierowani s� pacjenci ) */
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
  
  /** Zwraca badania dla danego przyj�cia zapisane w bazie  */
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
  
  /** Zwraca leczenia dla danego przyj�cia zapisane w bazie  */
  List getLeczenieByPrzyjecie( Przyjecie przyjecie );
  
  /** Zapisuje leczenia do bazy  */
  void updateLeczenieList( List leczenia );
  
  /** Zapisuje leczenie do bazy  */
  void updateLeczenie( Leczenie leczenie );
  
  /** Usuwa leczenie z bazy  */
  void deleteLeczenie( Leczenie leczenie );    

}
