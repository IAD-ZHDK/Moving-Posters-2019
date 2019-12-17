package Posters.Utilities;

import processing.core.*;

public class Picture{
/*
    PImage img;
    PImage bufferimg;
    int imageX = 0;
    int imageY = 0;
    float easeOut = 0;
    PGraphics pg;
    PApplet p;

    Picture(PApplet parent, String image, int x, int y) {
        this.p = parent;
        imageX = x;
        imageY = y;
        pg = p.createGraphics(p.width/2, p.height);
        img = p.loadImage(image+".png");
        img.resize(p.width/2, p.height);
        bufferimg = img.copy();
    }

    void display(float x, float zero) {

        p.push();
        float disparity = p.constrain(p.abs((zero * p.width) - x) / (0.25 * p.width), 0, 1);
        if (zero<=0.25){
            float easedDisp = easeOutQuintGlitch(disparity);
            glitch.amount = map(easedDisp, 0, 1, pg.width, 25);}
        else{
            float easedDisp = easeOutQuint(disparity);
            glitch.amount = map(easedDisp, 0, 1, pg.width, 25);}

        translate(imageX, 0);
        fx.render(pg)
                .custom(glitch)
                .compose();
        p.pop();


        if (disparity < 0.1) {
            p.image(img, imageX, 0);
        }
    }

    void preRender() {
        pg.beginDraw();
        pg.image(img, 0, 0);
        pg.endDraw();
    }
*/
}
