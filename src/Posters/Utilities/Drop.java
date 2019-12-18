package Posters.Utilities;
import processing.core.*;

public class Drop {
    protected PApplet p;

    PVector position;
    PVector velocity;

    private float speedLimit = 10;

    PImage drop;
    PImage dropSW;


    float w = 0;
    float h = 0;


    public Drop (PApplet applet) {


        this.p = applet;
        drop = p.loadImage("Poster8/Drop.png");
        position = new PVector ( p.width / 5,  p.height/2);
        int scaleX = (int)(((float)drop.width / 2160)*p.width);
        int scaleY = (int)(((float)drop.height / 1920)*p.height);
        drop.resize(scaleX,scaleY);
        velocity = new PVector();
        w = drop.width;
        h = drop.height;
        resetPosition();
    }


    /********* UPDATE ***************************************************************/

    // how to add drop on the right side, if the drop is left out of the screen?


    public boolean update(float tx, float ty) {

        float distance = p.dist(tx, ty, position.x, position.y);


        position.add(velocity);

        accelerate(0, .3f);

        if ((position.y-h)>p.height  && position.x<p.width/2) {
            position.x += p.width/2; //random (width*0.55, width*0.93); für rechte Hälfte
            position.y = -h;
        }

        if ((position.y-h)>p.height && position.x>p.width/2) {

            resetPosition();
        }

        if (distance<= w) { //Colision Detection

            resetPosition();
            //println("hit");

            return true;
        } else {
            return false;
        }
    }


    /********* reset pos ***************************************************************/

    public void resetPosition() {

        position.x = p.random (p.width/8, p.width*0.42f); //random (width*0.55, width*0.93); für rechte Hälfte
        position.y = p.height*0.20f;
        velocity.set(0,2);
    }


    /********* ACCELERATE ***************************************************************/

    private void accelerate(float accelerationX, float accelerationY) {
        // add acceleration
        velocity.add(accelerationX, accelerationY);
        velocity.limit(speedLimit);
    }


    /********* PAINT ***************************************************************/

    public void paint () {
        p.imageMode(p.CENTER);
        p.image(drop, position.x, position.y);
    }
}