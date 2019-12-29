package Posters.Utilities;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Arm {

    protected PApplet p;
    private float newTriangleWidth = 0;
    private float currentTriangleWidth = 0;
    private float screenWidthFloat;

    public Arm(PApplet applet) {
        this.p = applet;
        screenWidthFloat = (float)p.width;
    }
    public void render(float triangleheight) {
        currentTriangleWidth = (screenWidthFloat/4)/ (p.height/triangleheight);
        newTriangleWidth = currentTriangleWidth;
        p.fill(0);
        p.triangle(-newTriangleWidth, 0, 0, -triangleheight, newTriangleWidth, 0);
    }

}