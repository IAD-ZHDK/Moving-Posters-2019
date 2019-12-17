package Posters;

import processing.core.*;
import Posters.Utilities.Particle;
import java.util.ArrayList;

public class Poster3 extends Poster{
    private PFont f;
    private  PVector Pos;
    private ArrayList<Particle> particles = new ArrayList<Particle>();
    private ArrayList<String> words = new ArrayList<String>();
    private int bgColor;
    private  PVector historyPos;
    private String fontName1 = "Poster3/RobotoMono-Bold-8.vlw";
    private String fontName2 = "Poster3/RobotoMono-Bold-195.vlw";
    private int pixelSteps = 25; // Amount of pixels to skip
    private int wordIndex = 1;
    private int boredTimer = 50;
    private int changeTimer = 0;
    private boolean bored = true;
    private boolean change = false;
    private PImage[] imgs;

    public Poster3(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.rectMode(p.CENTER);
        bgColor = p.color(255, 100);
        p.rectMode(p.CENTER);
        p.fill(255);
        //p.printArray(PFont.list());
        f = p.loadFont(fontName1);
        p.textFont(f);
        p.textAlign(p.CENTER, p.CENTER);

        words.add(" THE LOVE  OF NOVELS");
        words.add("EVOLVE ONTO  SHELF  ");
        nextWord(wordIndex);
        historyPos = new PVector();
        imgs = new PImage[2];
        imgs[0] = p.loadImage("Poster3/img1.png");
        imgs[1] = p.loadImage("Poster3/img1.png");
        imgs[0].resize(p.width,p.height);
        imgs[1].resize(p.width,p.height);
    }

    public boolean draw(PVector Pos) {

        p.fill(bgColor);
        p.noStroke();
        p.rect(0, 0, p.width*2, p.height*2);

        int counter = 65;

        PVector TargetPos = Pos.copy();
        TargetPos.x = 1-TargetPos.x;
        TargetPos.x = TargetPos.x*p.width;

        PVector mouse = new PVector(TargetPos.x, p.height/2);


        p.push();
        //translate(10, 0);  // ????
        for (int x = particles.size ()-1; x > -1; x--) {
            // Simulate and draw pixels

            char letter = (char) counter;

            Particle particle = particles.get(x);

            if (bored) {
                particle.applyBehaviorsPassive();
            } else {
                particle.applyBehaviorsActive(mouse);
            }

            particle.update();

            particle.draw(letter);
            if ( counter < 85 ) {
                counter++;
            } else {
                counter= 65;
            }

            counter++;

            // Remove any dead pixels out of bounds
            if (particle.isKilled) {
                if (particle.pos.x < 0 || particle.pos.x > p.width || particle.pos.y < 0 || particle.pos.y > p.height) {
                    particles.remove(particle);
                }
            }
        }
        p.pop();


        int hx = p.round(historyPos.x);
        int hy  = p.round(historyPos.y);
        int px = p.round (Pos.x);
        int py = p.round(Pos.y);

        if (historyPos.x!= Pos.x || historyPos.y != Pos.y) {
            boredTimer = 0;
            bored = false;

            changeTimer ++;
            if ( changeTimer  > 800) {
                change();
                changeTimer = 0;
            }
        } else if (hx == px && hy == py) {
            boredTimer++;
            bored = boredTimer > 2;

            changeTimer = 0;
        }
        historyPos = Pos.copy();
        return true;
    }

    private void change() {

        wordIndex += 1;
        if (wordIndex > words.size()-1) {
            wordIndex = 0;
        }

        nextWord(wordIndex);
    }

    private void nextWord(int index) {
        // Draw word in memory
       //   PImage pg = imgs[index].copy();
       //  pg.loadPixels();
        PGraphics pg = p.createGraphics(p.width,  p.height);
        pg.beginDraw();
        pg.fill(0);
        pg.textSize(200);
        pg.textAlign(p.CORNER);
        //  PFont font = p.createFont(fontName, 195);
        f = p.loadFont(fontName2);
        pg.textFont(f);
        pg.text(words.get(index), 0, p.height/2);
        pg.endDraw();
        //pg.resize(p.width, p.height);
        // pg.save(word+".png");
        pg.loadPixels();


        int particleCount = particles.size();
        int particleIndex = 0;

        // Collect coordinates as indexes into an array
        // This is so we can randomly pick them to get a more fluid motion
        ArrayList<Integer> coordsIndexes = new ArrayList<Integer>();
        for (int i = 0; i < (pg.width*pg.height)-1; i+= pixelSteps) {
            coordsIndexes.add(i);
        }

        for (int i = 0; i < coordsIndexes.size (); i++) {
            // Pick a random coordinate
            int randomIndex = (int)p.random(0, coordsIndexes.size());
            int coordIndex = coordsIndexes.get(randomIndex);
            coordsIndexes.remove(randomIndex);

            // Only continue if the pixel is not blank

            int argb = pg.pixels[coordIndex];
            int a = (argb >> 24) & 0xFF;
            int  r = (argb >> 16) & 0xFF;
            int   g = (argb >> 8) & 0xFF;
            int   b = argb & 0xFF;
            float magnitude = (r+g+b)/3;

            if (pg.pixels[coordIndex] != 0) {
            //    if (magnitude <= .3) {
                // Convert index to its coordinates
                int x = coordIndex % p.width;
                int y = coordIndex / p.width;

                Particle newParticle;

                if (particleIndex < particleCount) {
                    // Use a particle that's already on the screen
                    newParticle = particles.get(particleIndex);
                    newParticle.isKilled = false;
                    particleIndex += 1;
                } else {
                    // Create a new particle
                    newParticle = new Particle(p);

                    PVector randomPos = newParticle.generateRandomPos(p.width/2, p.height/2, (p.width+p.height)/2);
                    newParticle.pos.x = randomPos.x;
                    newParticle.pos.y = randomPos.y;

                    newParticle.maxSpeed = p.random(2.0f, 5.0f);
                    newParticle.maxForce = newParticle.maxSpeed*0.025f;
                    newParticle.particleSize = 5;
                    // newParticle.colorBlendRate = random(0.0025, 0.03);

                    particles.add(newParticle);
                }


                // Assign the particle's new target to seek
                newParticle.target.x = x;
                newParticle.target.y = y;
            }
        }

        // Kill off any left over particles
        if (particleIndex < particleCount) {
            for (int i = particleIndex; i < particleCount; i++) {
                Particle particle = particles.get(i);
                particle.kill();
            }
        }
    }

}