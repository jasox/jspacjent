package okl.jspacjent.model;
// Generated 2006-12-05 16:02:43 by Hibernate Tools 3.2.0.beta8

import java.util.HashSet;
import java.util.Set;

/**
 * Rodzlecz generated by hbm2java
 */
public class Rodzlecz implements java.io.Serializable {

  // Fields    

  private long   idRodzl;
  private String opis;
  private String uwagi;
  private Set    leczenies = new HashSet(0);

  // Constructors

  /** default constructor */
  public Rodzlecz() {
  }

  /** minimal constructor */
  public Rodzlecz(long idRodzl) {
    this.idRodzl = idRodzl;
  }
  /** full constructor */
  public Rodzlecz(long idRodzl, String opis, String uwagi,
      Set leczenies) {
    this.idRodzl = idRodzl;
    this.opis = opis;
    this.uwagi = uwagi;
    this.leczenies = leczenies;
  }

  // Property accessors
  public long getIdRodzl() {
    return this.idRodzl;
  }

  public void setIdRodzl(long idRodzl) {
    this.idRodzl = idRodzl;
  }
  public String getOpis() {
    return this.opis;
  }

  public void setOpis(String opis) {
    this.opis = opis;
  }
  public String getUwagi() {
    return this.uwagi;
  }

  public void setUwagi(String uwagi) {
    this.uwagi = uwagi;
  }
  public Set getLeczenies() {
    return this.leczenies;
  }

  public void setLeczenies(Set leczenies) {
    this.leczenies = leczenies;
  }
  
  /** */
  public boolean equals(Object other) {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if ( !( other instanceof Rodzlecz ) )
      return false;        
    Rodzlecz castOther = (Rodzlecz) other;
    // Wystarczy por�wna� identyfikator
    return  ( ( this.getIdRodzl() == castOther.getIdRodzl() ) );
  }
  
  /** */
  public String toString() {
    String tmp = "";  
     //tmp += (new Long(this.getIdRodzl())).toString() + " , " ;
    tmp += this.getOpis()  + "   " ;    
    //tmp += this.getUwagi() + "" ;    
    return tmp; 
  }

}