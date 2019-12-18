// Kilian Ettlinger
package Posters;

import processing.core.*;

public class Poster1 extends ImgPoster {

    public Poster1(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG, "Poster1/", "left", 120, 2);
    }
    protected void drawimages(PVector Pos) {
        int index = p.constrain(p.floor(imageArray.length*Pos.x), 0, imageArray.length-1);
        index = p.abs(index);
        int index2 = (imageArray.length-1)-index;
       // p.image(imageArray[index], 0, 0);
        p.set(0, 0, imageArray[index]);
        p.set(p.width/2, 0, imageArray[index2]);
    }

}