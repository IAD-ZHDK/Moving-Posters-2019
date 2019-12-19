import processing.core.PApplet;
import java.util.Calendar;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    // create sketch
    //todo: add code to pic new poster each day
    Calendar cal = Calendar.getInstance();
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)+1;

    int totalPosters = 9;
    int nextPoster = dayOfMonth%(totalPosters);
  //  System.out.println(nextPoster);
    //Sketch sketch = new Sketch(nextPoster);
    Sketch sketch = new Sketch(nextPoster);
    // run sketch
     PApplet.runSketch(new String[] {"Posters"}, sketch);
    // crate log file
    try {
      PrintStream o = new PrintStream(new File("movingPostersLog"+Calendar.DAY_OF_MONTH+"_"+Calendar.HOUR+"_"+Calendar.MINUTE+".log"));
      //System.setOut(o);
      System.setErr(o);
    } catch (Exception e) {

    }

  }
}
