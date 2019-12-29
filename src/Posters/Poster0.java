// template
package Posters;
import processing.core.*;

public class Poster0 extends Poster{
    public Poster0(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.rectMode(p.CENTER);
        p.fill(255);
    }

    public void draw(PVector Pos)  throws Exception {
        super.draw(Pos);
        p.background(240);
        p.strokeWeight(5);
        p.stroke(50);
        shape1(Pos);
        shape2(Pos);
        }

        private void shape1(PVector Pos) {
            PVector TargetPos = Pos.copy();
            TargetPos.x = 1-TargetPos.x; // invert the x axis
            TargetPos.x = p.constrain(TargetPos.x, .0f, .5f);
            TargetPos.x = TargetPos.x*p.width;
            TargetPos.y = TargetPos.y*p.height;
            PVector myPos = new PVector(p.width/4, p.height/2);
            TargetPos.sub(myPos);
            for (int i = 1; i <= p.width/100; i++) {
                p.pushMatrix();
                float scale = (p.width/2)*.65f-(i*15);
                float myX = myPos.x+(TargetPos.x*i*0.03f);
                float myY = myPos.y+(TargetPos.y*i*0.03f);
                p.translate(myX, myY);
                p.rotate(p.PI/4);
                //p.rotate(p.radians(Z*i));
                p.rect(0, 0, scale, scale);
                p.popMatrix();
            };
        }

        private void shape2(PVector Pos) {
            PVector TargetPos = Pos.copy();
            TargetPos.x = 1-TargetPos.x; // invert the x axis
            TargetPos.x = p.constrain(TargetPos.x, .5f, 1f);
            TargetPos.x = TargetPos.x*p.width;
            TargetPos.y = TargetPos.y*p.height;
            p.stroke(50);
            PVector myPos = new PVector(p.width/4*3, p.height/2);
            TargetPos.sub(myPos);
            for (int i = 1; i <= p.width/100; i++) {
                p.pushMatrix();
                float scale = (p.width/2.0f)*.65f-(i*15);
                float myX = myPos.x+(TargetPos.x*i*0.03f);
                float myY = myPos.y+(TargetPos.y*i*0.03f);
                p.translate(myX, myY);
                p.circle(0, 0, scale);
                p.popMatrix();
            };
        }
}