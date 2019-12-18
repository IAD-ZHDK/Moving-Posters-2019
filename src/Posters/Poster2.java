// Kimon
//
package Posters;

import processing.core.PApplet;
import processing.core.PVector;

public class Poster2 extends ImgPoster {

    public Poster2(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG, "Poster2/", "GDB_Anagramm_V01_", 225, 1);
    }

    protected void drawimages(PVector Pos) {
        int index = p.constrain(p.floor(imageArray.length*Pos.x), 0, imageArray.length-1);
        index = p.abs(index);
        //p.image(imageArray[index], 0, 0);
        p.set(0, 0, imageArray[index]);
    }
}