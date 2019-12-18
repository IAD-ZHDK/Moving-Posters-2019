package Posters;

import processing.core.*;
import geomerative.*;


public class Poster7 extends Poster{
   private RShape nieves;
   private RShape envies;

   private RPoint[] pointsNieves;
   private RPoint[] pointsEnvies;

   private PVector posNieves;
   private PVector posEnvies;

   //private PImage image;
   private float globalScale = 0.8f;
   private int amtBetween = 10;

   private float stepCounter = 0f;
   private int counter = 0;
   private PShape circle;

   private int startIndex = 0;
   private int pointIteratorStepSize = 1;

   private boolean disableLerp = false;

   private float magicValue;
   private float x;

   private boolean debugMode = false;


   public Poster7(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.rectMode(p.CENTER);
        RG.init(p);

       p.colorMode(p.HSB, 360, 100, 100, 1);

       //image = p.loadImage("Poster7/image2.png");

       nieves = RG.loadShape("Poster7/nieves.svg");
       nieves = RG.centerIn(nieves, p.g);
       pointsNieves = nieves.getPoints();
       posNieves = new PVector(p.width * 0.25f, p.height / 2 + 25);

       envies = RG.loadShape("Poster7/envies.svg");
       envies = RG.centerIn(envies, p.g);
       pointsEnvies = envies.getPoints();
       posEnvies = new PVector(p.width * 0.75f, p.height / 2 - 25);

       circle = p.createShape(p.ELLIPSE, 0, 0, 1, 1);
       circle.setFill(false);
       circle.setStrokeWeight(0.001f);
       //circle.setMode(p.CORNER);

    }

    float gaussian(float x, float mean, float variance) {
        return (1 / p.sqrt(p.TWO_PI * variance)) * p.exp(-p.sq(x - mean) / (2 * variance));
    }

    public boolean draw(PVector Pos) {
        p.background(p.color(0, 0, 100));

        magicValue = p.map(Pos.x, 0, 1, -1700, 1700);


        magicValue = p.sqrt(p.abs(magicValue))*p.pow(magicValue,2) * 0.00001f;

        float x = p.constrain(p.abs(magicValue), 1, p.width);
        //float t = p.abs(p.map(gaussian(p.abs(p.map(magicValue, 0, 560, -1, 1)) / 1.8f, 0, 0.2f), 0, 1, -1, 1) * 10); //map(x, 1, width, 1, 5);;
        float t = p.abs(p.map(gaussian(p.abs(p.map(magicValue, 0f, 560f, -1f, 1f)) / 1.8f, 0f, 0.2f), 0, 1, -1, 1) * 10); //map(x, 1, width, 1, 5);;

        if (x < 25) {
            t = 1;
        }

        if ( x > 25 && x < 600) {
            t = p.map(x, 0, 250, 1, 15);
        } else if (x >= 600 && x < 1300) {
            t = p.map(x, 600, 1400, 15, 8);
        } else {
            t = 1;
        }
        //p.println(x);

        pointIteratorStepSize = p.constrain((int) t, 1, 20);

        //println(abs(map(gaussian(abs(map(x, 0, 560, -1, 1)), 0, 0.2), 0, 1, -1, 1)) * 10);

        float stepSize = 1f / amtBetween;
        stepCounter += stepSize;
        float pieceScale = x * 0.01f;
        pieceScale = p.constrain(pieceScale, 0, 3);
        drawSnake(startIndex, posNieves, pointsNieves, (float) p.TWO_PI * 0.75f, globalScale, pieceScale, stepCounter);
        drawSnake(startIndex, posEnvies, pointsEnvies, (float) p.TWO_PI * 0.25f, globalScale, pieceScale, stepCounter);

        if (stepCounter > 1 ) {
            stepCounter = 0;
        }

        counter++;

        return true;
    }

    void drawSnake(int startPoint, PVector position, RPoint[] points, float rotation, float gScale, float scaleDiameter, float stepCounter) {

        float strokeWeight = (p.abs(magicValue)) * 0.003f + 1;

        disableLerp = scaleDiameter > 1.5;

        p.push();
        p.translate(position.x, position.y);
        p.rotate(rotation);
        p.scale(gScale);
        p.fill(0, 0, 0, 0);
        p.stroke(p.color(0, 0, 0, 1));
        p.strokeWeight(0.1f);

        drawSnakePiece(points[0].x, points[0].y, scaleDiameter, strokeWeight);
        for (int i = startPoint; i<points.length - pointIteratorStepSize; i = i + pointIteratorStepSize) {
            RPoint point1 = points[i];
            RPoint point2 = points[i+1];
            //filter(BLUR, 1);

            float pieceRotation = (float)i / pointsNieves.length * magicValue * 0.01f;

            p.push();

                if (disableLerp) {
                    p.translate(point1.x, point1.y);
                } else {
                    p.translate(p.lerp(point1.x, point2.x, stepCounter), p.lerp(point1.y, point2.y, stepCounter));
                }
                p.rotate(pieceRotation);

                // adaptive snake draw
                int skip = p.constrain(p.round(p.map(scaleDiameter, 1.0f, p.width * 0.01f, 1f, 20f)), 1, 10);
                //if (i % skip == 0)
                drawSnakePiece(0, 0, scaleDiameter, strokeWeight);
           //p.ellipse(0, 0, scaleDiameter, scaleDiameter);
            p.pop();

            //render last piece with rotation
            if (i >= points.length - 1) {
                p.push();
                p.translate(points[points.length-1].x, points[points.length-1].y);
                p.rotate(pieceRotation);
                drawSnakePiece(0, 0, scaleDiameter, strokeWeight);
                p.pop();
            }
        }
        p.pop();
    }

    void drawSnakePiece(float x, float y, float scaleDiameter, float strokeWeight) {
        float s = scaleDiameter;
        //min scaleDiameter
        if (scaleDiameter > 0 && scaleDiameter < 0.25) {
            s = 0.25f;
        } else if (scaleDiameter < 0 && scaleDiameter > -0.25) {
            s = -0.25f;
        }
        //image(image, x, y, 250* scaleDiameter, 325 * scaleDiameter);
        p.strokeWeight(strokeWeight);
        p.noFill();
        //stroke(color(280, 100, 100));
      //  p.pushMatrix();
       // p.scale(250 * s, 325 * s);
       // p.shape(circle);
       // p.popMatrix();
            p.ellipseMode(p.CORNER);
            p.ellipse(x, y, 250 * s, 325 * s);
    }

}