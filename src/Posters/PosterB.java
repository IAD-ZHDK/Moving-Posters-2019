package Posters;

import processing.core.*;
import ch.bildspur.postfx.builder.*;
import ch.bildspur.postfx.pass.*;
import ch.bildspur.postfx.*;
import processing.core.*;

public class PosterB extends PApplet{
    PostFX fx;
    private boolean DEBUG;
    private PApplet p;
    //GlitchPass glitch;
   // Picture cantRead;
    //Picture danceArt;

    public PosterB(boolean DEBUG, PApplet parent) {
        this.p = parent;
        this.DEBUG = DEBUG;
    }

    public void settings() {
        if (DEBUG) {
            size(972,864, P2D);
        } else {
            fullScreen(P2D, SPAN);
        }
    }


    public void setup() {
        if (DEBUG) {
            width = 972;
            height = 864;
        } else {
            noCursor();
            setUpScreen();
        }
    }


    public void draw(PVector Pos) {

    }
    private void setUpScreen() {
        int pageWidth = 1080*2;
        int pageHeight = 1920;
        surface.setResizable(true);
        surface.setSize(pageWidth, pageHeight);
        width = pageWidth;
        height = pageHeight;
        int startPointX = 0;
        int startPointY = 0;
        surface.setLocation(startPointX, startPointY);
    }
}