package Posters;
import geomerative.*;
import processing.core.*;


public class Poster9 extends Poster{
    private RShape grp;
    private RShape grp2;
    private PShape circles;
    private int noOfChilds;

    public Poster9(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);

        RG.init(p);
        grp = RG.loadShape("Poster9/white.svg");

        grp2 = RG.loadShape("Poster9/black.svg");
    }

    public boolean draw(PVector Pos) {
        p.background(50);
        float posX = Pos.x;
        //posX = sin((posX*HALF_PI));
        //background(0, 0, 20);    // template

        for (int i = 0; i < grp.countChildren(); i++)
        {
            float paralax = p.sin(posX-.5f) * p.width/40;
            RShape ps =  grp.children[i];
            RPoint pos = ps.getCenter();
            //println(pos.x);
            p.fill(255);
            p.noStroke();
            float w  = ps.getOrigWidth();
            paralax = (paralax*(float)w);
            //RG.shape(ps, paralax, 0);
            p.circle(pos.x+paralax, pos.y, w);
        }
        for (int i = 0; i < grp2.countChildren(); i++)
        {
            float paralax =  p.sin(posX-.5f) *  p.width/40;
            RShape ps =  grp2.children[i];
            RPoint pos = ps.getCenter();
            //println(pos.x);
            p.fill(0);
            p.noStroke();
            float w  = ps.getOrigWidth();
            paralax = (paralax*(float)w);
            //RG.shape(ps, paralax, 0);
            p.circle(pos.x+paralax, pos.y, w);
        }
        return true;
    }

}