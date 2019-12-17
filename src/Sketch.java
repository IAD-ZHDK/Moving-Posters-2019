import processing.core.*;


public class Sketch extends PApplet{
    private static final boolean DEBUG = true;
    private int posterWidth;
    private int posterHeight;

    private RealSenseApplet RealSence;
    private PImage realsenseImg;
    // Object position
    private PVector average  = new PVector();
    private PVector target = new PVector();

    private float angle = 0;
    private float averageWeight = .97f; // ratio of old data vs new
    private int updateFlag = 0;
    //posters
    private int currentPoster = 0; // current vis
    private int nextPoster = 0; // next vis
    private int totalPosters = 7; // current vis
    private Posters.Poster Poster;

    public Sketch( int nextPoster) {
        this.nextPoster = nextPoster;
        currentPoster = nextPoster;
    }

    public void settings() {
        if (DEBUG) {
            size(1080,960, FX2D);
            // fullScreen(FX2D);
        } else {
            fullScreen(FX2D, SPAN);
        }
    }

    public void setup() {
        if (DEBUG) {
            //setUpScreen();
            width = 1080;
            height = 960;
        } else {
            noCursor();
            int d = day();
            nextPoster = d%(totalPosters+1);
        }
        realsenseImg = new PImage(640, 480);
        target = new PVector(.5f,.5f,.5f);
        surface.setAlwaysOnTop(true);
        RealSence = new RealSenseApplet(DEBUG);
        PApplet.runSketch(new String[] {"Sketch"}, RealSence);
        startPoster();
    }

    public void draw() {
        if (nextPoster != currentPoster) {
            currentPoster = nextPoster;
            Poster.distroyImgs();
            Poster = null;
            startPoster();
            RealSence.runCamera(false);
            background(0);
            println("nextPoster:" +nextPoster);

        } else {
            PVector pos = new PVector(.5f,.5f,.5f);
            if(Poster.loading) {
                RealSence.runCamera(false);
            } else {
                RealSence.runCamera(true);
            }
            pushStyle();
            pushMatrix();
            // draw and check poster for errors
            try {
                if (currentPoster == 6) {
                    // Poster 6 uses image feed from realsense camera
                    Poster.draw(realsenseImg);
                    getTargetImage();
                } else {
                    pos = getTarget();
                    Poster.draw(pos);
                    //getTargetImage();
                }
            } catch ( Exception e ) {
                // go to nextproject if poster draw failes
                nextProject();
            }
            popMatrix();
            popStyle();

        }

        if (DEBUG) {
            float memory = (usedMem()/totalMem());
            surface.setTitle("fps: " + floor(frameRate)+ " mem: "+ nf(memory, 2,5)+"%"); //Set the frame title to the frame rate
        }
    }


    private void startPoster() {
        if (currentPoster == 1) {
            Poster = new Posters.Poster1(this, DEBUG);
            println("Poster1");
        } else if (currentPoster == 2) {
            Poster = new Posters.Poster2(this, DEBUG);
            println("Poster2");
        } else if (currentPoster == 3) {
            Poster = new Posters.Poster3(this, DEBUG);
            println("Poster3");
        } else if (currentPoster == 4) {
            Poster = new Posters.Poster4(this, DEBUG);
            println("Poster4");
        } else if (currentPoster == 5) {
            // need to create new aplet to handle different renderer.
            Poster = new Posters.templateImg(this, DEBUG);
            println("Poster5");
        } else if (currentPoster == 6) {
            Poster = new Posters.Poster6(this, DEBUG);
            println("Poster6");
        } else if (currentPoster == 7) {
             Poster = new Posters.Poster7(this, DEBUG);
            println("Poster7");
        } else if (currentPoster == 9) {
            //Poster = new Posters.Poster5(this, DEBUG);
            println("Poster9");
        }  else if (currentPoster == 10) {
            //Poster = new Posters.Poster5(this, DEBUG);
            println("Poster10");
        } else {
            Poster = new Posters.Poster0(this, DEBUG);
            println("Poster0");
        }

    }

    private PVector getTarget() {
        // smooth readings out to avoid any shake
        try {
            PVector temp = RealSence.getPos();
            if (temp != null) {
                target = temp.copy();
                updateFlag = 0;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        if (updateFlag>100) {
            //animation if there are no input values
            angle += 0.005f;
            target = new PVector((float)(Math.sin(angle)+1f)*.5f, (float)(Math.cos(angle)+1f)*.1f+.5f);
        }
        PVector temp = target.copy();
        temp.mult(1-averageWeight);
        average.mult(averageWeight);
        average.add(temp);
        updateFlag ++;
        return average.copy();
    }

    private void getTargetImage() {
        try {
            PImage temp = RealSence.getImage();
            //  image(buffer.realsenseImg, 0, 0);
            if (temp != null) {
                realsenseImg = temp.copy();
            }
        } catch (Exception e) {
            e.printStackTrace();
           // realsenseImg = new PImage(640, 480);
        }

    }


    public void mousePressed() {
        nextProject();
        //exit();
    }
    public void keyPressed() {
        if (keyCode == UP) {
            RealSence.shutDown();
        }

        if (keyCode == '0' ||keyCode == '1'||keyCode == '2' ||keyCode == '3'||keyCode == '4' ||keyCode == '5'||keyCode == '6' ||keyCode == '7') {
            nextProject(Character.getNumericValue(keyCode));
        }

    }

    private void nextProject() {
        if (!Poster.loading) {
            nextPoster++;
            if (nextPoster > totalPosters) {
                nextPoster = 0;
            }
        }
    }
    private void nextProject(int no) {
        if (!Poster.loading) {
            nextPoster = no;
            if (nextPoster > totalPosters) {
                nextPoster = 0;
            }
        }
    }

    public void stop() {
        println("In stop");
        super.stop();
    }

    private void setUpScreen() {
        float pageWidth = 1080*2;
        float pageHeight = 1920;
        surface.setResizable(true);
        float aspectRatioH = pageWidth/pageHeight;
        float aspectRatioV = pageHeight/pageWidth;

        if (displayWidth< displayHeight) {
            // for portrait mode
            posterWidth= displayWidth;
            posterHeight = floor(displayWidth*aspectRatioV);
        } else {
            // for landscape mode
            posterWidth= floor(displayHeight*aspectRatioH);
            posterHeight = displayHeight;
        }
        surface.setSize(posterWidth, posterHeight);
        width = floor(posterWidth);
        height = floor(posterHeight);
        //reposition output in center of display
        int startPointX = (displayWidth/2) - (width/2);
        int startPointY = (displayHeight/2) - (height/2);
        surface.setLocation(startPointX, startPointY);
    }

    private float totalMem() {
        return Runtime.getRuntime().totalMemory();
    }
    private float usedMem() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

}
