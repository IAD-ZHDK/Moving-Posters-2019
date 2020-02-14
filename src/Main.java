import processing.core.PApplet;
import java.util.Calendar;
import java.io.*;

public class Main {
  private static boolean DEBUG = false;
  public static void main(String[] args) {
    // create sketch
    //Calendar cal = Calendar.getInstance();
    // int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)+1;
    //int totalPosters = 10;
    //int nextPoster = dayOfMonth%(totalPosters);
    //Sketch sketch = new Sketch(nextPoster);
    Sketch sketch = new Sketch(9, DEBUG);
    // run sketch
    PApplet.runSketch(new String[] {"Posters"}, sketch);
    // crate log file
    try {
      if (!DEBUG) {
        PrintStream o = new PrintStream(new File("movingPostersLog" + Calendar.DAY_OF_MONTH + "_" + Calendar.HOUR + "_" + Calendar.MINUTE + ".log"));
        System.setOut(o);
         System.setErr(o);
      }
    } catch (Exception e) {

    }

  }
}
