// template
package Posters;

import processing.core.PApplet;
import processing.core.PVector;

public class Poster9 extends imgPoster{

    public Poster9(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG, "templateImg/", "image", 119, 2);
    }
    protected void drawimages(PVector Pos) {
        int index = p.constrain(p.floor(imageArray.length*Pos.x), 0, imageArray.length-1);
        index = p.abs(index);
        int index2 = (imageArray.length-1)-index;
      //  p.image(imageArray[index], 0, 0);
       // p.image(imageArray[index2], p.width/2, 0);
        p.set(0, 0, imageArray[index]);
        p.set(p.width/2, 0, imageArray[index]);
    }

}