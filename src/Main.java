import processing.core.PApplet;
import java.util.Calendar;

public class Main {
  public static void main(String[] args) {
    // create sketch
    //todo: add code to pic new poster each day
    Calendar cal = Calendar.getInstance();
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)+1;
    int totalPosters = 8;
    int nextPoster = dayOfMonth%(totalPosters);
    System.out.println(nextPoster);
    //Sketch sketch = new Sketch(nextPoster);
    Sketch sketch = new Sketch(3);
    // run sketch
     PApplet.runSketch(new String[] {"Posters"}, sketch);
  }
}
