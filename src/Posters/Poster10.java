//Nicola Delany
package Posters;
import processing.core.*;

public class Poster10 extends Poster{

    private PShape nooption1;
    private int scaleX = 1; // direction
    private float letterW,letterH;


    public Poster10(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.noStroke();
        nooption1 =  p.loadShape("Poster10/nooption1.svg");
        float scale =  nooption1.height/nooption1.width;
        letterW = ((float)p.width/2)*.90f;
        letterH = letterW*scale;
        nooption1.disableStyle();
        p.shapeMode(p.CENTER);
        p.rectMode(p.CORNER);
    }

    public void draw(PVector Pos) {
        p.background(240);
        p.strokeWeight(5);
        p.fill(0);
        p.rect( p.width/2, p.map(Pos.x, 2.45f, 0, -p.height, p.height), p.width/2, p.height*.666f);
        float rotation = p.PI+(p.constrain(p.map(Pos.x, .1f, .8f, 0, 1), 0, 1)*p.PI);
        nooption1(rotation, p.width/2, p.height/2);
    }

    void nooption1(float angle, int x, int y) {

        if (angle>=p.TWO_PI) {
            int osc = (p.frameCount%15);
            if (osc>=7.5) {
                osc = 1;
                p.background(0);
                p.fill(255);
                p.rect(0, 0, p.width/2, p.height);
            } else {
                osc = 0;
                p.background(0);
                p.fill(255);
                p.rect(0, 0, p.width/2, p.height);
                p.fill(255, 0, 0);
                p.rect(p.width/2, p.height, p.width/2, p.height);
            }
            p.fill(255*osc);
        } else {
            p.fill(0);
            nooption1.setFill(p.color(0));
        }
        p.pushMatrix();
        p.translate(x, y);
        p.rotate(angle);
        //
        float gap = ((p.width/2)-letterW)/2;
        p.translate((p.width/4), -((float)p.height/2f)+(letterH/2)+gap);
        p.shape(nooption1, 0,0,letterW, letterH);
        p.popMatrix();
    }

}