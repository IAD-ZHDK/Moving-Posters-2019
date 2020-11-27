// Daniela Spühler
package Posters;

import processing.core.*;


public class Poster6 extends Poster{
    // Size of each cell in the grid
   private int videoScale = 10;
    // Number of columns and rows in the system
    private int cols, rows;
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
        // Initialize columns and rows
        cols = p.width / videoScale;
        rows = p.height / videoScale;
        p.fill(255);
        img = p.loadImage("Poster6/b_weiss_final.gif");
        if (DEBUG) {
            img.resize(p.width,p.height);
        }
        p.imageMode(p.CORNER);
        p.strokeCap(p.SQUARE);
        p.strokeWeight(2);
        p.noFill();
    }

    public boolean draw(PImage temp) {
        counter += 0.01; //speed oscilation
        lengthMax = (100+p.sin(counter)*100)+10; // max and min oscilation
        //println(lengthMax);
        p.background(255);
        PImage baseImage = temp.copy();
        baseImage.copy(baseImage, zoom, zoom, baseImage.width - zoom, baseImage.height - zoom, 0, 0, baseImage.width, baseImage.height);
        baseImage.resize(cols, rows);

        // Begin loop for columns
        p.translate(8, 8);

        for (int i = 0; i < cols; i++) {
            // Begin loop for rows
            for (int j = 0; j < rows; j++) {
                // Where are you, pixel-wise?
                int x = i*videoScale;
                int y = j*videoScale;

                // Reverse the column to mirror the image.
                int loc = i + j*baseImage.width;
                int c = baseImage.pixels[loc];

                // A rectangle's size is calculated as a function of the pixel’s brightness.
                // A bright pixel is a large rectangle, and a dark pixel is a small one.
                float sz =  p.map( p.brightness(c), 0, 180, 0, lengthMax);

                PVector line = new PVector(sz,0);

                if (sz>1) {
                    float angle =  p.map( p.brightness(c), 0, 180, 0,  p.TWO_PI);

                    line.rotate(angle);
                    p.stroke(0xff000000 | (int)( p.random(0xffffff)));
                    p.line(x, y, x+line.x, y+line.y);
                } else {
                    p.stroke(0xff000000 | (int)( p.random(0xffffff)));
                    p.strokeWeight(2);
                    p.line(x, y, x+1, y+1);
                }
            }


        }

        p.image(img, 0,0);

        return true;
    }

}