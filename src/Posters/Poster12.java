// Miriam Mai Watanabe
package Posters;

import processing.core.*;
import Posters.Utilities.Letter;
import geomerative.*;

public class Poster12 extends Poster{

    PVector gravity = new PVector();
    String [] letters = new String [1];

    int shapeColor=0;
    int pathSimplification = 0;
    float pathSampleFactor = 0.1f;

    RFont font;

    Letter f;
    Letter a;
    Letter l1;
    Letter l2;
    Letter i;
    Letter n;
    Letter g;

    Letter[] ls;

    Letter aa;
    Letter ll;
    Letter ii;
    Letter gg;
    Letter nn;


    public Poster12(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.noFill();
        p.strokeWeight(1.5f);
        p.textAlign( p.CENTER);
        gravity.set(0, 25);

        RG.init(p);
        font = new RFont("Poster12/NotoSans-Bold.ttf", (600* p.height)/1920, RFont.LEFT);
        f = new Letter(p, font, "f", ( p.width*60f)/1215f, ( p.height*290)/1080f, 6.0f/7.0f);
        a = new Letter(p, font,"a", ( p.width*200f)/1215f, ( p.height*350)/1080f, 5.0f/7.0f);
        l1 = new Letter(p, font,"l", ( p.width*430f)/1215f, ( p.height*480)/1080f, 4.0f/7.0f);
        l2 = new Letter(p, font,"l", ( p.width*255f)/1215f, ( p.height*680)/1080f, 3.0f/7.0f);
        i = new Letter(p, font,"i", ( p.width*69f)/1215f, ( p.height*680)/1080f, 2.0f/7.0f);
        n = new Letter(p, font,"n", ( p.width*69f)/1215f, ( p.height*960)/1080f, 1.0f/7.0f);
        g = new Letter(p, font,"g", ( p.width*370f)/1215f, ( p.height*860)/1080f, 0.7f/7.0f);

        aa = new Letter(p, font,"a", ( p.width*602f)/1215f,  (600* p.height)/1080f, 6.0f/7.0f);
        ll = new Letter(p, font,"l", ( p.width*752.5f)/1215f, (600* p.height)/1080f, 5.0f/7.0f);
        ii = new Letter(p, font,"i", ( p.width*800f)/1215f, (600* p.height)/1080f, 4.0f/7.0f);
        gg = new Letter(p, font,"g", ( p.width*855f)/1215f, (600* p.height)/1080f, 3.0f/7.0f);
        nn = new Letter(p, font,"n", ( p.width*1019f)/1215f, (600* p.height)/1080f, 2.0f/7.0f);

        p.rectMode(p.CORNER);
    }





    void animateLetter(Letter myLetter, float newX) {
        float ribbonWidth = p.map(myLetter.movement, 0, p.height, 1, 150);
        myLetter.drawLetter((float)((int)(myLetter.movement))/p.width, ribbonWidth);
        if (newX > myLetter.animationStart) {
            myLetter.y= p.min(p.height + 300, myLetter.y + 6);
        } else if (myLetter.y > myLetter.startY && newX < myLetter.animationStart) {
            myLetter.y= myLetter.y - 6;
        }
        myLetter.movement= myLetter.y - myLetter.startY;
    };

    void animateLetterH(Letter myLetter, float newX) {
        float ribbonWidth = p.map(myLetter.movement, 0, p.height, 1, 150);
        myLetter.drawLetter((float)((int)(myLetter.movement))/p.width, ribbonWidth);
        if (newX > myLetter.animationStart) {
            myLetter.x= p.min(p.width + 300, myLetter.x + 6);
        } else if (myLetter.x > myLetter.startX && newX < myLetter.animationStart) {
            myLetter.x = myLetter.x - 6;
        }
        myLetter.movement= myLetter.x - myLetter.startX;
    };

    public void draw(PVector Pos) {
        p.fill(206,41,41);
        p.rect(0, 0, p.width/2, p.height);

        p.fill(255);
        p.rect(p.width/2, 0, p.width/2, p.height);

        // screen split line
        p.line(p.width/2, 0, p.width/2, p.height);

        p.noFill();
        p.stroke(255);

        animateLetter(f, Pos.x);
        animateLetter(a, Pos.x);
        animateLetter(l1, Pos.x);
        animateLetter(l2, Pos.x);
        animateLetter(n, Pos.x);
        animateLetter(i, Pos.x);
        animateLetter(g, Pos.x);
        p.stroke(206,41,41);

        animateLetterH(aa, 1-Pos.x);
        animateLetterH(ll, 1-Pos.x);
        animateLetterH(ii, 1-Pos.x);
        animateLetterH(gg, 1-Pos.x);
        animateLetterH(nn, 1-Pos.x);

    }

}