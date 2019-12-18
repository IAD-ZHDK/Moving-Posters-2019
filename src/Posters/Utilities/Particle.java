package Posters.Utilities;
import processing.core.*;

public class Particle {
    private PApplet p;
    public PVector pos = new PVector(0, 0);
    private PVector vel = new PVector(0, 0);
    private PVector acc = new PVector(0, 0);
    public PVector target = new PVector(0, 0);

    public float maxSpeed = 5;
    public float maxForce = 3;
    public float particleSize = 50;
    public boolean isKilled = false;

    public Particle(PApplet p) {
        this.p =  p;
    }

    public void update() {

        this.vel.add(this.acc);

        this.vel.limit(maxSpeed);
        this.pos.add(this.vel);
        this.acc.mult(0);
    }


    public void applyForce(PVector force) {
        this.acc.add(force);
    }


    public void applyBehaviorsPassive() {

        PVector arriveForce = arrive();

        arriveForce.mult(1);

        applyForce(arriveForce);
    }


    public void applyBehaviorsActive(PVector d) {

        PVector arriveForce = arrive();
        PVector fleeForce = flee(d);


        fleeForce.mult(5);
        arriveForce.mult(1);


        applyForce(fleeForce);
        applyForce(arriveForce);
    }


    public PVector arrive () {
        PVector desired = PVector.sub(this.target, this.pos);  // A vector pointing from the position to the target
        float d = desired.mag();
        // Scale with arbitrary damping within 100 pixels
        if (d < 100) {
            float m = p.map(d, 0, 100, 0, maxSpeed);
            desired.setMag(m);
        } else {
            desired.setMag(maxSpeed);
        }
        // Steering = Desired minus Velocity
        PVector steer = PVector.sub(desired, this.vel);
        steer.limit(maxForce);  // Limit to maximum steering force
        return steer;
    }


    public PVector flee (PVector target) {
        PVector desired = PVector.sub(target, this.pos);
        float d = desired.mag();

        if (d < 200) {
            desired.setMag(maxSpeed);
            desired.mult(-1);
            PVector steer = PVector.sub(desired, this.vel);
            steer.limit(maxForce);
            return steer;
        } else {
            PVector steer= new PVector(0, 0);
            return steer ;
        }
    }


    public void draw(char letter) {
        // Draw particle

        p.stroke(51);
        p.fill(51);
        //p.text(letter, this.pos.x, this.pos.y);
         p.rect(this.pos.x, this.pos.y, 3, 3);
    }


    public void kill() {
        if (! this.isKilled) {
            // Set its target outside the scene
            PVector randomPos = generateRandomPos(p.width/2, p.height/2, (p.width+p.height)/2);
            this.target.x = randomPos.x;
            this.target.y = randomPos.y;
            this.isKilled = true;
        }
    }

    public PVector generateRandomPos(int x, int y, float mag) {
        PVector randomDir = new PVector(p.random(0, p.width), p.random(0, p.height));
        PVector pos = new PVector(x, y);
        pos.sub(randomDir);
        pos.normalize();
        pos.mult(mag);
        pos.add(x, y);
        return pos;
    }

}
