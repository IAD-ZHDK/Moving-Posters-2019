package Posters.Utilities;
import processing.core.*;
import geomerative.*;
import java.util.ArrayList;

public class Letter {
    protected PApplet p;
    private RFont font;
    private RPoint[] pnts;
    private ArrayList <RPoint> simplePnts;
    public float movement;
    public float animationStart;
    private float density = 8; //vorher war es 2.5
    public float startX;
    public float startY;
    public float x;
    public float y;
    private String letter;

   public Letter(PApplet p, RFont font, String letter, float x, float y, float animationStart) {
        this.font = font;
        this.p = p;
        this.animationStart = animationStart;
        this.letter = letter;
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;

        converText();
    }

    private void converText() {
        // get the points on font outline
        RGroup grp = font.toGroup(letter);
        grp = grp.toPolygonGroup();
        pnts = grp.getPoints();  //pnts[i].x , pnts[i].y array of points, each point with a x and y coordinate
    }

    //void morphLetter() {

    //}

    public void drawLetter(float pathSampleFactor, float ribbonWidth) {
        if (x > p.width || y>p.height) {
            return;
        }

        p.push();
        p.translate(x, y);
        int pointFactor = (int)(p.map(pathSampleFactor, 0, 1, 1, pnts.length*.1f))+1;
        pointFactor = p.max(1, pointFactor);
        simplePnts = new ArrayList <RPoint>();

        for (int i = 0; i < pnts.length; i++) {
            //println(""+pathSampleFactor+" pointFactor " +pointFactor +":"+i%pointFactor);
            if (i%pointFactor == 0) {
                simplePnts.add(pnts[i]);
            }
        }



        for (int d = 0; d < ribbonWidth; d += density) {
            p.beginShape();

            for (int i = 0; i < simplePnts.size(); i++) {
                RPoint pos = simplePnts.get(i);
                if (i + 1 < simplePnts.size()) {
                    RPoint nextPos = simplePnts.get(i + 1);

                    PVector p0 = new PVector(pos.x, pos.y);
                    PVector p1 =  new PVector(nextPos.x, nextPos.y);
                    PVector v = PVector.sub(p1, p0);
                    v.normalize();
                    v.rotate(p.HALF_PI);
                    v.mult(d);
                    PVector pneu = PVector.add(p0, v);
                    p.curveVertex(pneu.x, pneu.y);
                }
            }
            p.endShape(p.CLOSE);
        }
        p.pop();
    };
}
