//--------------------------------------------------------------------
// :Utilities
//--------------------------------------------------------------------
package okl.jspacjent.utility;
//
import java.io.*;
import java.text.*;


/**
 * Klasa pomocnicza dla aplikacjii
 *
 * @author  Janusz Swó³,  © PLL "LOT" SA
 * @version 1.0
 *
 */
public class Utilities {

   /** Absolutna œcie¿ka do katalogu wykonywalnego programu */
   public String pathToApplication;
   
   /** Absolutna œcie¿ka do przegl¹darki HTML */
   public String pathToViewer;
   
   /** Absolutna œcie¿ka do katalogu z danymi bazy HSQLDB */
   public String pathToData;
   
  // ----------------------------------------------------------------- 
  /** Nag³ówek DOCTYPE */
  public static final String DOCTYPE =
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
    "Transitional//EN\">";
  // -----------------------------------------------------------------
  /** Znacznik META */
  public static final String META =
    // jêzyk dokumentu
    "<meta http-equiv=\"Content-Language\" content=\"pl\"> \n" +
    // typ dokumentu
    "<meta http-equiv=\"Content-Type\" " +
    //"content=\"text/html; charset=ISO-8859-2\">\n" +
    "content=\"text/html; charset=windows-1250\">\n" +
    // autor
    "<meta name=\"author\" content=\"Janusz Swó³ PLL 'LOT' SA\">\n" +
    // opis
    "<meta name=\"description\" content=\" Raporty z przyjêæ pacjentów" +
    " do Kliniki Neurochirurgii Szpitala Bródnowskiego" + " \">";
  //
  // -----------------------------------------------------------------
  /** Znacznik META */
  public static final String STYLE =
    // style do³¹czane do dokumentu
    "<link rel =\"stylesheet\" type=\"text/css\" " + 
    "href=\"c:/Program Files/JSPacjent/jspacjentstyle.comm.css\" > \n" +
    "<link rel =\"stylesheet\" type=\"text/css\" " + 
    "href=\"c:/Program Files/JSPacjent/jspacjentstyle.form.css\" > \n" + 
    "<link rel =\"stylesheet\" type=\"text/css\" " + 
    "href=\"c:/Program Files/JSPacjent/jspacjentstyle.rap.css\" > ";    
  //
  //------------------------------------------------------------------
  /** Podstawowy nag³ówek stron */
  public static final String BASIC_HEADER = 
  "<h3 class=\"header\">Szpital Bródnowski - Klinika Neurochirurgii</h3>\n"; 
  //                                                   
  // -----------------------------------------------------------------
  /** Podstawa stopka stron */
  public static final String BASIC_FOOTER =
  "<table class=\"foot\">                                     \n" +
  "  <tr class=\"foot\" >                                     \n" +
  "    <td class=\"foot\" >                                   \n" +
  "      <p class=\"foot\">&copy;                             \n" +
  "      </p>                                                 \n" +
  "    </td>                                                  \n" +
  "  </tr>                                                    \n" +
  "</table>                                                     ";
  // -----------------------------------------------------------------
  /** Znacznik HEAD wraz z TITLE */
  public static String headWithTitle(String title) {
    return( DOCTYPE + "\n" +
            META    + "\n" +
            STYLE   + "\n" +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body>\n" + 
            BASIC_HEADER );
  }   
  // -----------------------------------------------------------------
  
  public String getPathToApplication() {
    return pathToApplication;
  }
  
  public void setPathToApplication(String pathToApplication) {
    this.pathToApplication = pathToApplication;
  }
  
  public String getPathToData() {
    return pathToData;
  }
  
  public void setPathToData(String pathToData) {
    this.pathToData = pathToData;
  }
  
  public String getPathToViewer() {
    return pathToViewer;
  }
  
  public void setPathToViewer(String pathToViewer) {
    this.pathToViewer = pathToViewer;
  }
  // ...
}
