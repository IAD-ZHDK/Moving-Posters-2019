package Posters;

import processing.core.*;


public class Poster {
    public boolean loading = false;
    protected PApplet p;
    PVector Pos;
    protected int imgCount = 0;
    protected PImage[] imageArray;
    protected int totalImages;

    Poster(PApplet parent,boolean DEBUG) {
        this.p = parent;
    }

    public void draw(PVector Pos) throws Exception {
    this.Pos = Pos;
    }
    public boolean draw( PImage temp) throws Exception {
        this.Pos = Pos;
        return true;
    }

    protected void loadingAnimation() {
        p.background(0);
        p.pushStyle();
        p.fill(100);
        p.ellipseMode(p.RADIUS);
        float progress = p.map(imgCount, 0, totalImages, 0, 1)*p.TWO_PI;
        p.pushMatrix();
        p.translate(p.width/4,p.height/2);
        p.arc(0, 0, 30, 30, 0, progress);
        p.translate(p.width/2,0);
        p.arc(0, 0, 30, 30, 0, progress);
        p.popMatrix();
        p.popStyle();
    }

    public void distroyImgs() {
        imageArray = null;
    }

}
