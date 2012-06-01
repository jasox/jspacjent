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
 * @author  Janusz Sw�,  � PLL "LOT" SA
 * @version 1.0
 *
 */
public class Utilities {

   /** Absolutna �cie�ka do katalogu wykonywalnego programu */
   public String pathToApplication;
   
   /** Absolutna �cie�ka do przegl�darki HTML */
   public String pathToViewer;
   
   /** Absolutna �cie�ka do katalogu z danymi bazy HSQLDB */
   public String pathToData;
   
  // ----------------------------------------------------------------- 
  /** Nag��wek DOCTYPE */
  public static final String DOCTYPE =
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
    "Transitional//EN\">";
  // -----------------------------------------------------------------
  /** Znacznik META */
  public static final String META =
    // j�zyk dokumentu
    "<meta http-equiv=\"Content-Language\" content=\"pl\"> \n" +
    // typ dokumentu
    "<meta http-equiv=\"Content-Type\" " +
    //"content=\"text/html; charset=ISO-8859-2\">\n" +
    "content=\"text/html; charset=windows-1250\">\n" +
    // autor
    "<meta name=\"author\" content=\"Janusz Sw� PLL 'LOT' SA\">\n" +
    // opis
    "<meta name=\"description\" content=\" Raporty z przyj�� pacjent�w" +
    " do Kliniki Neurochirurgii Szpitala Br�dnowskiego" + " \">";
  //
  // -----------------------------------------------------------------
  /** Znacznik META */
  public static final String STYLE =
    // style do��czane do dokumentu
    "<link rel =\"stylesheet\" type=\"text/css\" " + 
    "href=\"c:/Program Files/JSPacjent/jspacjentstyle.comm.css\" > \n" +
    "<link rel =\"stylesheet\" type=\"text/css\" " + 
    "href=\"c:/Program Files/JSPacjent/jspacjentstyle.form.css\" > \n" + 
    "<link rel =\"stylesheet\" type=\"text/css\" " + 
    "href=\"c:/Program Files/JSPacjent/jspacjentstyle.rap.css\" > ";    
  //
  //------------------------------------------------------------------
  /** Podstawowy nag��wek stron */
  public static final String BASIC_HEADER = 
  "<h3 class=\"header\">Szpital Br�dnowski - Klinika Neurochirurgii</h3>\n"; 
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
