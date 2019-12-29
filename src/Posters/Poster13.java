//Aathmigan Jegatheeswaran
package Posters;

import processing.core.*;
import Posters.Utilities.Triangle;
import Posters.Utilities.Arm;


public class Poster13 extends Poster{
    private Triangle triangle;
    private Triangle triangleMouth2;
    private Triangle triangleMouth1;
    private Triangle triangleEyes2;
    private Arm arm;

     private float pixelSize = 0;
     private float widthonescreen = 0;
     private PShape ruthless;
     private PShape hustlers;
     private float screenWidthFloat = 0;
     private float screenHeightFloat = 0;
     private float angleArm = 180;
     private float coinMovement = 0;

     private boolean triggerMouthMotionStart = false;
     private boolean triggerCoinMotion = false;
     private boolean triggerArmsDown = false;
     private float counterCoinMotion = 0;
     private float counterArmsDown = 0;
     private float fadeinFont = 0;
     private float coinGravity = 15;

    public Poster13(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        triangle = new Triangle(p);
        triangleMouth2 = new Triangle(p);
        triangleMouth1 = new Triangle(p);
        triangleEyes2 = new Triangle(p);
        arm = new Arm(p);
        p.rectMode(p.CENTER);
        p.fill(255);
        widthonescreen = p.width/2;
        screenWidthFloat = p.width;
        screenHeightFloat = p.height;
        pixelSize = widthonescreen / 47;
        //todo: loadshape doesn't find files in jar file path, files need to be added to jar parent directory to work
        ruthless = p.loadShape("Poster13/ruthless.svg");
        hustlers = p.loadShape("Poster13/hustlers.svg");
    }

    public void draw(PVector Pos) {
        if(Pos.x* p.width <=  p.width/2){
            fadeinFont =  p.map(Pos.x* p.width,  p.width/16,  p.width*0.4f, 255, 0);
        }else{
            fadeinFont =  p.map(Pos.x* p.width,  p.width*0.6f,  p.width- p.width/16, 0, 255);
        }
        if (Pos.x* p.width >=  p.width/4.5 && Pos.x* p.width <=  p.width- p.width/4.5) {
            triggerCoinMotion = true;
            if (angleArm > 75) {
                angleArm -= 7;
            } else {
                angleArm = 75;
            }
        } else {
            triggerCoinMotion = false;
            if(triggerArmsDown == true){
                if (angleArm > 180) {
                    angleArm = 180;
                    triggerArmsDown = false;
                } else {
                    angleArm += 7;
                }
            }
        }
        if(triggerCoinMotion == true){
            counterCoinMotion++;
        }else{
            if(counterCoinMotion <= 90){
                counterCoinMotion = 0;
                triggerArmsDown = true;
            }
        }
        if(counterCoinMotion > 90){
            if((coinMovement <  p.height/3.4 && triggerCoinMotion == true) || coinMovement > 0){
                if(coinGravity >= 50){
                    coinGravity = 50;
                }else{
                    coinGravity++;
                }
                coinMovement += coinGravity;
                triggerArmsDown = false;
            }
            if(coinMovement >=  p.height/3.4f){
                if(counterArmsDown > 25){
                    triggerArmsDown = true;
                    counterArmsDown = 0;
                    coinMovement = 0;
                    counterCoinMotion = 0;
                    coinGravity = 0;
                }else{
                    triggerArmsDown = false;
                    counterArmsDown++;
                    coinMovement =  p.height/3.4f;
                }
            }
        }
        p.background(255);
        p.fill(0);
        p.noStroke();
        p.rectMode( p.CORNER);
        p.ellipseMode( p.CENTER);
        p.fill(128);
        p.circle( p.width/2- p.width/20,0- p.height/50+coinMovement,  p.width/50);
        p.circle( p.width/2+ p.width/20,0- p.height/50+coinMovement,  p.width/50);
        p.fill(0);
        ghost1();
        ghost2();
        p.shapeMode( p.CENTER);
        p.shape(ruthless,  p.width/4,  p.height-( p.height/16),  p.width/5,  p.width/5/7.48f);
        p.shape(hustlers,  p.width/4+ p.width/2,  p.height-( p.height/16),  p.width/5,  p.width/5/7.48f);
        p.rectMode( p.CENTER);
        p.fill(0,0,0,fadeinFont);
        p.rect( p.width/4,  p.height-( p.height/16), p.width/5,  p.width/5/7.48f);
        p.rect( p.width/2+ p.width/4,  p.height-( p.height/16), p.width/5,  p.width/5/7.48f);
        p.fill(255, 0, 0);
    }


    private void ghost1() {
        // Arms
        p.pushMatrix();
        p.translate( p.width/4,  p.height/3);
        p.rotate( p.radians(angleArm));
        arm.render( p.width/3.85f);
        p.popMatrix();
        // Body
        p.pushMatrix();
        p.translate(0.25f* p.width, 0);
        p.fill(0);
        triangle.render(p.height, false, false,triggerCoinMotion);
        p.popMatrix();
        // Mouth
        p.pushMatrix();
        p.translate(p.width/4.5f, p.height/6);
        p.rotate(p.radians(270));
        p.fill(255);
        triangleMouth1.render(p.width/9, true, false, triggerCoinMotion);
        p.popMatrix();
    }
    private void ghost2() {
        // Arms
        p.pushMatrix();
        p.translate(p.width-p.width/4, p.height/3);
        p.scale(-1,1);
        p.rotate(p.radians(angleArm));
        arm.render(p.width/3.85f);
        p.popMatrix();
        // Body
        p.pushMatrix();
        p.translate(0.75f*p.width, 0);
        p.fill(0);
        triangle.render(p.height, false, false, triggerCoinMotion);
        p.popMatrix();
        // Mouth
        p.pushMatrix();
        p.translate(p.width-p.width/4.5f, p.height/6);
        p.rotate(p.radians(90));
        p.fill(255);
        triangleMouth2.render(p.width/9, true, true,triggerCoinMotion);
        p.popMatrix();
    }

}