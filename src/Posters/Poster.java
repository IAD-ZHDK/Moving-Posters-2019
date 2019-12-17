package Posters;

import processing.core.*;


public class Poster {
    public boolean loading = false;
    protected PApplet p;
    PVector Pos;

    Poster(PApplet parent,boolean DEBUG) {
        // set parent
        this.p = parent;
    }

    public boolean draw(PVector Pos) {
    this.Pos = Pos;
    return true;
    }
    public boolean draw( PImage temp) {
        this.Pos = Pos;
        return true;
    }
    public void distroyImgs() {
    }
}
