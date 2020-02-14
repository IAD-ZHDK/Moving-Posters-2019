// Andreas Waldburger
package Posters;

import processing.core.*;

public class Poster11 extends ImgPoster {
    private int index = 1;
    private PImage e;

    public Poster11(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG, "Poster11/", "image","png", 76, 1);
        e = p.loadImage("Poster11/e.png");
        e.resize((int)(p.width*0.25f), (int)(p.height*0.9f));
    }
    protected void drawimages(PVector Pos) {
        p.background(255);
        int input = (int)(p.map(Pos.x, 0, 1, 0, 100));
        if (input > 10 && input <90) {
            index = (int)(p.map(input, 10, 90, 76, 0));
        }
        if (index>=74) {
            index= 75;
        }
        ////E
        p.imageMode(p.CENTER);
        p.image(e, p.width*0.77f, p.height*0.5f);
        p.image(e, p.width*0.23f, p.height*0.5f);

        p.strokeCap(p.ROUND);
        p.strokeWeight(p.width/42);
        p.stroke(0);
        float multi =15;
        p.line(p.width*0.1f, (p.height*0.8f)-index*multi, p.width*0.35f, (p.height*0.8f)-index*multi);
        p.line(p.width*0.65f, (p.height*0.2f)+index*multi, p.width*0.85f, (p.height*0.2f)+index*multi);
        p.strokeCap(p.ROUND);

        p.line( p.width*0.35f, ( p.height*0.8f)-index*multi,  p.width*0.5f, ( p.height*0.55f)-index*(multi*0.2f));
        p.line( p.width*0.65f, ( p.height*0.2f)+index*multi,  p.width*0.5f, ( p.height*0.45f)+index*(multi*0.15f));

        p.strokeCap( p.SQUARE);
        p.line( p.width*0.1f, ( p.height*0.8f)-index*multi,  p.width*0.13f, ( p.height*0.73f)-index*(multi*0.7f));
        p.line( p.width*0.85f, ( p.height*0.2f)+index*multi,  p.width*0.8f, ( p.height*0.3f)+index*(multi*0.7f));

        p.imageMode( p.CORNER);

        int index = p.constrain(p.floor(imageArray.length*Pos.x), 0, imageArray.length-1);
        index = p.abs(index);
        int index2 = (imageArray.length-1)-index;
        p.image(imageArray[index],0, 0);
        p.strokeCap(p.SQUARE);
        p.stroke(255);
        p.strokeWeight(p.width/40);
        p.line(p.width/2, 0, p.width/2, p.height);
    }
} 