//Alessia Wiss
package Posters;

import processing.core.*;
import Posters.Utilities.Drop;
import java.util.ArrayList;

public class Poster8 extends Poster{

    private PImage backgroundSW;
    private PImage backgroundGR;
    private int displayBackgroundGRtill = 0;
    private PImage lime;
    private PImage mouth;
    private PImage tongue;

    private PImage limeSW;
    private PImage mouthSW;
    private PImage tongueSW;
    private ArrayList<Drop> drops = new ArrayList<Drop>();


    public Poster8(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.rectMode(p.CENTER);

        backgroundGR = p.loadImage("Poster8/background_GR.png");
        backgroundGR.resize(p.width, p.height);
        backgroundSW = p.loadImage("Poster8/background_SW.png");
        backgroundGR = p.loadImage("Poster8/background_GR.png");
        lime = p.loadImage("Poster8/Lime2GR.png");
        limeSW = p.loadImage("Poster8/Lime2SW.png");
        backgroundSW.resize(p.width, p.height);
        backgroundGR.resize(p.width, p.height);

        mouth = p.loadImage("Poster8/Mouth.png");
        tongue = p.loadImage("Poster8/Tongue2.png");
        mouthSW = p.loadImage("Poster8/Mouth_SW.png");
        tongueSW = p.loadImage("Poster8/Tongue2_SW.png");

        float scaleFactorLime = (p.width/2*.80f)/lime.width;
        lime.resize((int)(lime.width*scaleFactorLime), (int)(lime.height*scaleFactorLime));

        float scaleFactorMouth = (p.width/2*.80f)/mouth.width;
        mouth.resize((int)(mouth.width*scaleFactorMouth), (int)(mouth.height*scaleFactorMouth));

        float scaleFactorTongue = (p.width/2*.20f)/tongue.width;
        tongue.resize((int)(tongue.width*scaleFactorTongue), (int)(tongue.height*scaleFactorTongue));

        //SW
        float scaleFactorLimeSW = (p.width/2*.80f)/limeSW.width;
        limeSW.resize((int)(limeSW.width*scaleFactorLimeSW), (int)(limeSW.height*scaleFactorLimeSW));

        float scaleFactorMouthSW = (p.width/2*.80f)/mouthSW.width;
        mouthSW.resize((int)(mouthSW.width*scaleFactorMouthSW), (int)(mouthSW.height*scaleFactorMouthSW));

        float scaleFactorTongueSW = (p.width/2*.20f)/tongueSW.width;
        tongueSW.resize((int)(tongueSW.width*scaleFactorTongueSW), (int)(tongueSW.height*scaleFactorTongueSW));



        // myTracker = new Tracker();// important for getting the tracking information from an external application
        p.rectMode(p.CENTER);
        p.imageMode(p.CENTER);
        p.fill(255);

    }

    public void draw(PVector Pos) {
        if (p.frameCount % 250 == 1) {
            if (drops.size()<8) {
                drops.add(new Drop(p));
            }
        }

        p.background(255);
        PImage Background = backgroundSW;
        PImage Mouth = mouth;
        if (displayBackgroundGRtill > p.millis()) {
            Background = backgroundGR;
            Mouth = mouthSW;
        }
        p.image(Background, p.width/2, p.height/2);
        p.image(Mouth, p.width-(p.width/4), p.height-(p.height/6));

        Pos.x = 1-Pos.x;

        float tongueOffset = p.width*.1608f;
        float tongueX = p.width-(p.width/4);
        float tongueY = p.height*0.71f;
        float angle = p.map(Pos.x, 1, 0, p.radians(60), p.radians(-60));
        tongueX += p.sin(angle)*tongueOffset;
        tongueY += p.cos(angle)*tongueOffset;

        p.push();
        p.translate(tongueX, tongueY);
        p.rotate(p.map(Pos.x, 1, 0, p.radians(-60), p.radians(60)));//////////////////myTracker.getTarget().x für kamera tracking

        if (Pos.x>.5) {
            p.scale(-1,1);
        }

        if (displayBackgroundGRtill >  p.millis()) {
            p.image(tongueSW, 0, 0);
        } else {
            p.image(tongue, 0, 0);
        }
        p.pop();

        for (int i = 0; i< drops.size(); i++) {
            Drop drop = drops.get(i);
            if (drop.update(tongueX,tongueY)) {
                displayBackgroundGRtill = p.millis() + 700; // damit Grüner BG kurz bleibt
            };
            drop.paint();
        }

        if (displayBackgroundGRtill > p.millis()) {
            p.image(limeSW, p.width/4, p.height/6);
        } else {
            p.image(lime, p.width/4, p.height/6);
        }
    }

}