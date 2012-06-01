// -------------------------------------------------------------------
// :TextFile.java
// -------------------------------------------------------------------
// Static functions for reading and writing text files as
//  a single string, and treating a file as an ArrayList.
//   {Clean: test.txt test2.txt}
// From 'Thinking in Java, 3rd ed.' © Bruce Eckel 2002
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// -------------------------------------------------------------------
package okl.jspacjent.utility;
//
import java.io.*;
import java.util.*;

/**
  * Tools to read and write files as single strings,
  *  and treating a file as an ArrayList.
  */
public class TextFile extends ArrayList {
  /** */
  public static String read(String fileName) throws IOException {
    StringBuffer sb = new StringBuffer();
    BufferedReader in =
      new BufferedReader( new FileReader( fileName ) );
    String s;
    while( ( s = in.readLine() ) != null ) {      
      sb.append(s);
      sb.append("\n");
    }
    in.close();
    return sb.toString();
  }
  
  /** */
  public static String read(String fileName, String charsetName) 
  throws IOException, UnsupportedEncodingException {
    StringBuffer sb = new StringBuffer();
    BufferedReader in =
      new BufferedReader( new FileReader( fileName ) );
    String s;
    while( (s = in.readLine()) != null ) {
      s = new String( s.getBytes(), charsetName );
      sb.append(s);
      sb.append("\n");
    }
    in.close();
    return sb.toString();
  }
  
  /** */
  public static void write(String fileName, String text)
  throws IOException {
    PrintWriter out = new PrintWriter(
      new BufferedWriter( new FileWriter( fileName ) ) );
    out.print(text);
    out.close();
  }
  
  /** */
  public TextFile(String fileName) throws IOException {
    super( Arrays.asList( read(fileName).split("\n") ) );
  }
  
  /** */
  public void write(String fileName) throws IOException {
    PrintWriter out = new PrintWriter(
      new BufferedWriter( new FileWriter( fileName ) ) );
    for( int i = 0; i < size(); i++ ) {
      out.println( get(i) );
    }
    out.close();
  }
  
  // Simple test:
  public static void main(String[] args) throws Exception {
    String file = TextFile.read("TextFile.java");
    TextFile.write("test.txt", file);
    TextFile text = new TextFile("test.txt");
    text.write("test2.txt");
  }
} ///:~
