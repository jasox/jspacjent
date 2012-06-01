package okl.jspacjent.model;
// Generated 2006-12-05 16:02:43 by Hibernate Tools 3.2.0.beta8

import java.util.*;
import java.text.*;

/**
 * Przyjecie generated by hbm2java
 * 
 * @author  janusz.swol@lot.pl
 */
public class Przyjecie implements java.io.Serializable {
  // wewn�trzne formaty wydruku 
  protected SimpleDateFormat dateFormat = 
    new SimpleDateFormat("dd.MM.yyyy");  
  protected NumberFormat nrFormat = NumberFormat.getNumberInstance();

  // Fields    
  private long    idPrzyj;
  private long    nrglowny;  
  private long    nroddzial;
  private String  ksg;
  private Lekarz  lekarz;
  private Pacjent pacjent;
  private Date    dataPrz;
  private String  sskierow;
  private String  skUwagi;
  private String  rozpwst;
  private String  rozpost;
  private Date    dataWyp;
  private String  gdziewyp;
  private String  uwagi;
  private Set     badanies  = new HashSet(0);
  private Set     leczenies = new HashSet(0);

  // Constructors

  /** default constructor */
  public Przyjecie() {
  }

  /** minimal constructor */
  public Przyjecie(long idPrzyj, long nrglowny, long nroddzial, 
      Pacjent pacjent ) {
    this.idPrzyj   = idPrzyj;
    this.nrglowny  = nrglowny;
    this.nroddzial = nroddzial;
    this.pacjent   = pacjent;    
  }
  
  /** constructor */
  public Przyjecie(long idPrzyj, long nrglowny, long nroddzial, 
      Pacjent pacjent, Date dataPrz ) {
    this.idPrzyj   = idPrzyj;
    this.nrglowny  = nrglowny;
    this.nroddzial = nroddzial;
    this.pacjent   = pacjent;    
    this.dataPrz   = dataPrz;
  }
  
  /** constructor */
  public Przyjecie(long idPrzyj, long nrglowny, long nroddzial, 
      String ksg, Pacjent pacjent, Date dataPrz ) {
    this.idPrzyj   = idPrzyj;
    this.nrglowny  = nrglowny;
    this.nroddzial = nroddzial;
    this.ksg       = ksg;
    this.pacjent   = pacjent;    
    this.dataPrz   = dataPrz;
  }
  
  /** full constructor */
  public Przyjecie(long idPrzyj, long nrglowny, String ksg, Lekarz lekarz, 
      Pacjent pacjent, long nroddzial, Date dataPrz, String sskierow, 
      String skUwagi, String rozpwst, String rozpost, Date dataWyp, 
      String gdziewyp, String uwagi, Set badanies, Set leczenies) {
    this.idPrzyj   = idPrzyj;
    this.nrglowny  = nrglowny;
    this.ksg       = ksg;
    this.lekarz    = lekarz;
    this.pacjent   = pacjent;
    this.nroddzial = nroddzial;
    this.dataPrz   = dataPrz;
    this.sskierow  = sskierow;
    this.skUwagi   = skUwagi;
    this.rozpwst   = rozpwst;
    this.rozpost   = rozpost;
    this.dataWyp   = dataWyp;
    this.gdziewyp  = gdziewyp;
    this.uwagi     = uwagi;
    this.badanies  = badanies;
    this.leczenies = leczenies;
  }

  // Property accessors
  
  public long getIdPrzyj() {
    return this.idPrzyj;
  }
  
  public void setIdPrzyj(long idPrzyj) {
    this.idPrzyj = idPrzyj;
  }  
  
  public long getNrglowny() {
    return this.nrglowny;
  }
  
  public void setNrglowny(long nrglowny) {
    this.nrglowny = nrglowny;
  } 
  
  public String getNrglownyAsString() {
    setFormat();
    return nrFormat.format( new Long( getNrglowny() ) );      
  }
  
  public String getKsg() {
    return this.ksg;
  }
  
  public void setKsg(String ksg) {
    this.ksg = ksg;
  }
  
  public Lekarz getLekarz() {
    return this.lekarz;
  }

  public void setLekarz(Lekarz lekarz) {
    this.lekarz = lekarz;
  }
  
  public Pacjent getPacjent() {
    return this.pacjent;
  }

  public void setPacjent(Pacjent pacjent) {
    this.pacjent = pacjent;
  }
  
  public long getNroddzial() {
    return this.nroddzial;
  }

  public void setNroddzial(long nroddzial) {
    this.nroddzial = nroddzial;
  }  
  
  public Date getDataPrz() {
    return this.dataPrz;
  } 

  public void setDataPrz(Date dataPrz) {
    this.dataPrz = dataPrz;
  }
  
  public String getDataPrzAsString() {
    setFormat();
    if ( this.getDataPrz() != null ) {
      return dateFormat.format( this.getDataPrz() );
    }
    else {
      return null;
    }  
  }
  
  public String getSskierow() {
    return this.sskierow;
  }

  public void setSskierow(String sskierow) {
    this.sskierow = sskierow;
  }
  
  public String getSkUwagi() {
    return this.skUwagi;
  }

  public void setSkUwagi(String skUwagi) {
    this.skUwagi = skUwagi;
  }
  
  public String getRozpwst() {
    return this.rozpwst;
  }

  public void setRozpwst(String rozpwst) {
    this.rozpwst = rozpwst;
  }
  
  public String getRozpost() {
    return this.rozpost;
  }

  public void setRozpost(String rozpost) {
    this.rozpost = rozpost;
  }
  
  public Date getDataWyp() {
    return this.dataWyp;
  }

  public void setDataWyp(Date dataWyp) {
    this.dataWyp = dataWyp;
  }  
  
  public String getGdziewyp() {
    return this.gdziewyp;
  }

  public void setGdziewyp(String gdziewyp) {
    this.gdziewyp = gdziewyp;
  }
  
  public String getUwagi() {
    return this.uwagi;
  }

  public void setUwagi(String uwagi) {
    this.uwagi = uwagi;
  }
  
  public Set getBadanies() {
    return this.badanies;
  }
  
  public void setBadanies(Set badanies) {
    this.badanies = badanies;
  }
  
  public Set getLeczenies() {
    return this.leczenies;
  }

  public void setLeczenies(Set leczenies) {
    this.leczenies = leczenies;
  }
  
  protected void setFormat() {
    nrFormat.setMinimumIntegerDigits(4);
    nrFormat.setGroupingUsed( false );  
  };
  
  /** */
  public String toString() {    
    setFormat();    
    String tmp = "";   
    tmp += nrFormat.format( new Long( this.getNroddzial() ) );
    if ( this.getKsg() != null ) {
      tmp += "/" + this.getKsg().trim() + " " ;
    }
    else {
      tmp += "      ";
    }
    if ( this.getDataPrz() != null ) {
      tmp += dateFormat.format( this.getDataPrz() ) + "  " ;
    } 
    if ( this.getPacjent() != null ) {
      tmp += this.getPacjent().toString()  + "  " ;
    }  
    //tmp += this.getDataWyp().toString() + " , " ;
    //tmp += this.getLekarz().toString()  + " \n" ;
    /*
    Iterator iter = getLeczenies().iterator(); 
    while ( iter.hasNext() ) {      
      tmp += ((Leczenie)iter.next()) + "\n";                        
    }
    iter = getBadanies().iterator(); 
    while ( iter.hasNext() ) {      
      tmp += ((Badanie)iter.next()) + "\n";                        
    }
     */
    return tmp; 
  }
  
  /** */
  public String toStringShort() {    
    setFormat();    
    String tmp = "";   
    //tmp += nrFormat.format( new Long( this.getNroddzial() ) ) + "  " ;
    if ( this.getDataPrz() != null ) {
      tmp += dateFormat.format( this.getDataPrz() ) + "  " ;
    } 
    if ( this.getPacjent() != null ) {
      tmp += this.getPacjent().getNazwisko() + " " ;
      tmp += this.getPacjent().getImie() + " " ;
    }  
    //tmp += this.getDataWyp().toString() + " , " ;
    //tmp += this.getLekarz().toString()  + " \n" ;    
    return tmp; 
  }
  
  /** */
  public String toStringHtml() {    
    setFormat();    
    String tmp = "";
    tmp += "<i>Dane osobowe pacjenta</i>\n";
    if ( this.getPacjent() != null ) {
      tmp += this.getPacjent().toStringHtml();
    }
    tmp += "<br /><i>Dane przyj�cia pacjenta</i>\n";
    tmp += "<table class = \"rap\">" + 
      "<tr class = \"rap\"><td class = \"rap\">" + 
      nrFormat.format( new Long( this.getNrglowny() ) ) + "</td>";
    tmp += "<td class = \"rap\">" + 
      nrFormat.format( new Long( this.getNroddzial() ) ) + "</td>";
    tmp += "<td class = \"rap\">" + this.getKsg() + "</td>";
    if ( this.getDataPrz() != null ) {
      tmp += "<td class = \"rap\">" + 
        "<i>Przyj�cie</i> : " + 
        dateFormat.format( this.getDataPrz() ) + "</td>";
    }                
    if ( this.getDataWyp() != null ) {
      tmp += "<td class = \"rap\">" + 
        "<i>Wypis</i> : " +
        dateFormat.format( this.getDataWyp() ) + "</td>";
    }
    tmp += "</tr></table>\n";         
    // ...
    tmp += "<table class = \"rap\">";     
    // Lekarz
    if ( this.getLekarz() != null ) {
      tmp += "<tr class = \"rap\">";
      tmp += "<td class = \"rap\">" + 
        "<i>Lekarz prowadz�cy</i></td>" + "<td class = \"rap\">" +
        this.getLekarz().toString() + "</td></tr>\n" ;
    }
    // Sk�d skierowany
    if ( this.getSskierow() != null ) {
      if ( !this.getSskierow().trim().equals("") ) {
        tmp += "<tr class = \"rap\">";
        tmp += "<td class = \"rap\">" + 
          "<i>Skierowanie z</i></td>" + 
          "<td class = \"rap\">" + this.getSskierow();
        if ( this.getSkUwagi() != null ) {
          if ( !this.getSkUwagi().trim().equals("") ) {
            tmp += "  " + this.getSkUwagi();
          }
        }  
        tmp += "</td></tr>\n";
      }      
    }  
    // Gdzie wypisany
    if ( this.getGdziewyp() != null ) {
      if ( !this.getGdziewyp().trim().equals("") ) {
        tmp += "<tr class = \"rap\">";  
        tmp += "<td class = \"rap\">" + 
          "<i>Wypisanie do</i></td>" + 
          "<td class = \"rap\">" + this.getGdziewyp() + "</td>";
        tmp += "</tr>\n";
      }      
    }        
    // Rozpoznanie wst�pne
    if ( this.getRozpwst() != null ) {
      if ( !this.getRozpwst().trim().equals("") ) {
        tmp += "<tr class = \"rap\">";  
        tmp += "<td class = \"rap\">" + 
          "<i>Rozpoznanie wst�pne</i></td>" + 
          "<td class = \"rap\">" + this.getRozpwst() + "</td>";
        tmp += "</tr>\n";
      }
    }
    // Rozpoznanie ostateczne
    if ( this.getRozpost() != null ) {
      if ( !this.getRozpost().trim().equals("") ) {
        tmp += "<tr class = \"rap\">"; 
        tmp += "<td class = \"rap\">" + 
          "<i>Rozpoznanie ostateczne</i></td>" + 
          "<td class = \"rap\">" + this.getRozpost() + "</td>";
        tmp += "</tr>\n";
      }      
    }
    tmp += "</table>\n";
    tmp += "<br /><i>Badania</i>\n";     
    Iterator iter = getBadanies().iterator(); 
    while ( iter.hasNext() ) {      
      Badanie badanie = (Badanie)iter.next();
      tmp += badanie.toStringHtml();                        
    }
    tmp += "<br /><i>Leczenie</i>\n"; 
    iter = getLeczenies().iterator(); 
    while ( iter.hasNext() ) {      
      Leczenie leczenie = (Leczenie)iter.next();
      tmp += leczenie.toStringHtml();                        
    }       
    // ... 
    return tmp + "<hr>"; 
  }
  
}