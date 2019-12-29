// Nemo Brigatti

package Posters;
import geomerative.*;
import processing.core.*;

public class Poster9 extends Poster{
    private RShape grp;
    private RShape grp2;

    public Poster9(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        RG.init(p);
        grp = RG.loadShape("Poster9/white.svg");
        grp2 = RG.loadShape("Poster9/black.svg");
    }

    public void draw(PVector Pos) {
        p.background(50);
        p.fill(255);
        paralax( grp, Pos.x);
        p.fill(0);
        paralax( grp2, Pos.x);
    }

    private void paralax(RShape grp, float posX) {
        for (int i = 0; i < grp.countChildren(); i++)
        {
            float paralax = p.sin(posX-.5f) * p.width/40;
            RShape ps =  grp.children[i];
            RPoint pos = ps.getCenter();
            p.noStroke();
            float w  = ps.getOrigWidth();
            paralax = paralax*w;
            p.circle(pos.x+paralax, pos.y, w);
        }
    }

}

