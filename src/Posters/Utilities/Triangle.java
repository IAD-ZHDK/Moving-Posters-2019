package Posters.Utilities;

import processing.core.*;

public class Triangle {
    protected PApplet p;
    private float newTriangleWidth = 0;
    private float motionMouthT1 = 0;
    private boolean triggerMouthClose = false;
    private float currentTriangleWidth = 0;
    private int counterMouthMotion = 0;
    private int speedMouth = 7;
    private boolean triggerMouthMotionStart = false;
    private float screenWidthFloat;

    public Triangle (PApplet applet) {
        this.p = applet;
        screenWidthFloat = (float)p.width;
    }


    public void render(float triangleheight, boolean triggerMotion, boolean triggerGhost,boolean triggerCoinMotion) {
        currentTriangleWidth = (screenWidthFloat/4)/(p.height/triangleheight);
        counterMouthMotion++;
        if (triggerGhost == true) {
            if (counterMouthMotion <= 200) {
                triggerMouthMotionStart = false;
            }
            if (counterMouthMotion >= 200 && counterMouthMotion < 400) {
                triggerMouthMotionStart = true;
            }
            if (counterMouthMotion >= 400) {
                counterMouthMotion = 0;
            }
        } else {
            if (counterMouthMotion <= 200) {
                triggerMouthMotionStart = true;
            }
            if (counterMouthMotion >= 200 && counterMouthMotion < 400) {
                triggerMouthMotionStart = false;
            }
            if (counterMouthMotion >= 400) {
                counterMouthMotion = 0;
            }
        }

        if (triggerMotion == true) {
            if (triggerCoinMotion == false) {
                if (triggerMouthMotionStart == true) {
                    if (currentTriangleWidth + motionMouthT1 >= ((screenWidthFloat/4)/(p.height/triangleheight))) {
                        triggerMouthClose = true;
                    }
                    if (currentTriangleWidth + motionMouthT1 <= 0) {
                        triggerMouthClose = false;
                    }
                    if (triggerMouthClose == true) {
                        motionMouthT1 -= speedMouth;
                    }
                    if (triggerMouthClose == false) {
                        motionMouthT1 += speedMouth;
                    }
                } else {
                    if (currentTriangleWidth + motionMouthT1 <= 0) {
                        triggerMouthClose = false;
                    } else {
                        triggerMouthClose = true;
                    }
                    if (triggerMouthClose == true) {
                        motionMouthT1 -= speedMouth;
                    } else {
                        motionMouthT1 = -currentTriangleWidth;
                    }
                }
            } else {
                if (currentTriangleWidth + motionMouthT1 <= 0) {
                    motionMouthT1 = -currentTriangleWidth;
                } else {
                    motionMouthT1 -= speedMouth;
                }
            }
        } else {
            motionMouthT1 = 0;
        }
        newTriangleWidth = currentTriangleWidth+motionMouthT1;
        p.triangle(-newTriangleWidth, triangleheight, 0, 0, newTriangleWidth, triangleheight);
    }
}