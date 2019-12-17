package Posters;

import processing.core.*;
import java.util.ArrayList;


public class Poster6 extends Poster{
    // Size of each cell in the grid
   private int videoScale = 10;
    // Number of columns and rows in the system
    private int cols, rows;
    // Variable for capture device
    //Capture video;
    private float lengthMax = 10;
    private float counter = 0;

    //PShape fontShape;
    private PImage img;
    private PShape graphic;
    private int timer = 0;
    private int zoom = 45;


    public Poster6(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.rectMode(p.CENTER);
        //rectMode(CENTER);
        // Initialize columns and rows
        cols = p.width / videoScale;
        rows = p.height / videoScale;

        //fontShape = loadShape("b_weiss.svg");

        img = p.loadImage("Poster6/b_weiss_final.gif");
        if (DEBUG) {
            img.resize(p.width,p.height);
        }
        p.imageMode(p.CORNER);
        //graphic = p.loadShape("Poster6/b_weiss_final.svg");
        //graphic.resize(p.width, p.height);
        p.strokeCap(p.SQUARE);
        p.strokeWeight(2);
        //fill(255);
        p.noFill();
    }

    public boolean draw(PImage temp) {
        counter += 0.01; //speed oscilation
        // lengthMax += 0.8;
        //if(lengthMax>= 200){
        //  lengthMax = 10;
        //}
        lengthMax = (100+p.sin(counter)*100)+10; // max and min oscilation
        //println(lengthMax);
        p.background(255);
        PImage baseImage = temp.copy();
        baseImage.copy(baseImage, zoom, zoom, baseImage.width - zoom, baseImage.height - zoom, 0, 0, baseImage.width, baseImage.height);
        baseImage.resize(cols, rows);

        // baseImage.loadPixels();
        ArrayList<Integer> imageGreyScaleList = new ArrayList<Integer>();

        // Begin loop for columns
        p.translate(8, 8);

        for (int i = 0; i < cols; i++) {
            // Begin loop for rows
            for (int j = 0; j < rows; j++) {
                // Where are you, pixel-wise?
                int x = i*videoScale;
                int y = j*videoScale;

                // Reverse the column to mirro the image.
                //int loc = (baseImage.width - i - 1) + j * baseImage.width;
                int loc = i + j*baseImage.width;
                int c = baseImage.pixels[loc];

                // A rectangle's size is calculated as a function of the pixel’s brightness.
                // A bright pixel is a large rectangle, and a dark pixel is a small one.
                float sz =  p.map( p.brightness(c), 0, 180, 0, lengthMax);

                PVector line = new PVector(sz,0);
                // Every three seconds pick a new color and then display
                //if (millis()-timer > 3000) {
                //  timer = millis();
                //}
                if (sz>1) {
                    float angle =  p.map( p.brightness(c), 0, 180, 0,  p.TWO_PI);
                    //rectMode(CENTER);
                    line.rotate(angle);
                    //stroke(0);
                    p.stroke(0xff000000 | (int)( p.random(0xffffff)));
                    //stroke(color(int(random(255)),int(random(255)),int(random(255))));
                    //strokeJoin(ROUND);

                    //p.pushMatrix();
                    //p.translate(x, y);
                    //p.rotate(angle);

                    p.line(x, y, x+line.x, y+line.y);
                    //p.popMatrix();
                } else {
                    p.stroke(0xff000000 | (int)( p.random(0xffffff)));
                    p.rect(x, y, 1, 1);
                }
            }
           // p.shape(graphic, 0, 0, p.width,p.height);
            //p.image(img, 0, 0);
            //p.set(0, 0,img);
            //todo: image display is slowing down applet here. Maybe use a gif or an svg.
            //imageMode(CENTER);
        }

        return true;
    }

}