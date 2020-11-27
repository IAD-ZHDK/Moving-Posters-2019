import processing.core.*;

public class Sketch extends PApplet{

    private boolean DEBUG;
   // private Posters.PosterB P2Dapplet;
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
    private int nextPoster = 9; // next vis
    private int totalPosters = 12; // current vis
    private Posters.Poster Poster;
    private int AnimationStyle = 0;
    private boolean realsenseShutdown = false;
    private int changeCount = 0;

    public Sketch( int nextPoster, boolean DEBUG) {
        this.DEBUG = DEBUG;
        if (nextPoster<=totalPosters) {
            this.nextPoster = nextPoster;
            currentPoster = nextPoster;
        }
    }

    public void settings() {
        if (DEBUG) {
            //size(1944,1728, FX2D);
            size(972,864, FX2D);
        } else {
            fullScreen(FX2D, SPAN);
        }
    }

    public void setup() {
        if (DEBUG) {
           // width = 1944;
           // height = 1728;
            width = 972;
           height = 864;
        } else {
            noCursor();
            setUpScreen();
        }
        realsenseImg = new PImage(640, 480);
        target = new PVector(.5f,.5f,.5f);
        surface.setAlwaysOnTop(true);
        RealSence = new RealSenseApplet(DEBUG,this);
        PApplet.runSketch(new String[] {"Sketch"}, RealSence);
        startPoster();
        prepareExitHandler();
       // newWindow();
    }

    public void draw() {
        if (hour() == 1) {
            println("time to sleep");
            realsenseShutdown = true;
            RealSence.shutDown();
        }
        changeCount++;
        if (changeCount >= 50000) { // about 13 mins
            println("next");
            nextProject();
            changeCount = 0;
        }

        if (nextPoster != currentPoster) {
            currentPoster = nextPoster;
            colorMode(RGB,255,255,255);
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
            try {
                if (currentPoster == 6) {
                    // Poster 6 uses image feed from realsense camera
                    Poster.draw(realsenseImg);
                    getTargetImage();
                } else {
                    pos = getTarget();
                    Poster.draw(pos);
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
            // screen split line
            pushStyle();
            strokeWeight(1);
            stroke(255, 0, 0, 70);
            line( width/2, 0,  width/2,  height);
            popStyle();
        }
    }


    private void startPoster() {
        AnimationStyle = 0;
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
            AnimationStyle = 1;
            Poster = new Posters.Poster5(this, DEBUG);
            println("Poster5");
        } else if (currentPoster == 6) {
            Poster = new Posters.Poster6(this, DEBUG);
            println("Poster6");
        } else if (currentPoster == 7) {
            Poster = new Posters.Poster7(this, DEBUG);
            println("Poster7");
        }  else if (currentPoster == 8){
            Poster = new Posters.Poster8(this, DEBUG);
            println("Poster8");
        }   else if (currentPoster == 9){
            Poster = new Posters.Poster9(this, DEBUG);
            println("Poster9");
        } else if (currentPoster == 10){
            Poster = new Posters.Poster10(this, DEBUG);
            println("Poster10");
        }  else if (currentPoster == 11){
            Poster = new Posters.Poster11(this, DEBUG);
            println("Poster11");
        } else if (currentPoster == 12){
            Poster = new Posters.Poster12(this, DEBUG);
            println("Poster12");
        }  else {
            AnimationStyle = 1;
            Poster = new Posters.Poster13(this, DEBUG);
            println("Poster13");
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
            if (AnimationStyle == 0) {
                //animation if there are no input values
                angle += 0.005f;
                target = new PVector((float)(Math.sin(angle)+1f)*.5f, (float)(Math.cos(angle)+1f)*.1f+.5f);
            } else {
                target = new PVector(0.0f,0.0f,0.0f);
            }
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
            if (temp != null) {
                realsenseImg = temp.copy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mousePressed() {
        nextProject();
    }
    public void keyPressed() {
        if (keyCode == '1'||keyCode == '2' ||keyCode == '3'||keyCode == '4' ||keyCode == '5'||keyCode == '6' ||keyCode == '7'||keyCode == '8'||keyCode == '9') {
            nextProject(Character.getNumericValue(keyCode));
        } //else if (keyCode == '0') {
          //  surface.setAlwaysOnTop(false);
          //  P2Dapplet.active();
       // }
        else {
            realsenseShutdown = true;
            RealSence.shutDown();
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



    private void setUpScreen() {
        int pageWidth = 1080*2;
        int pageHeight = 1920;
        surface.setResizable(true);
         /*
        float aspectRatioH = pageWidth/pageHeight;
        float aspectRatioV = pageHeight/pageWidth;

        float posterWidth
        float posterHeight
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
          */
        surface.setSize(pageWidth, pageHeight);
        width = pageWidth;
        height = pageHeight;
        int startPointX = 0;
        int startPointY = 0;
        surface.setLocation(startPointX, startPointY);
    }


  /*  private void newWindow() {
        try {
            P2Dapplet = new Posters.PosterB(DEBUG, this);
            PApplet.runSketch(new String[]{"SketchB"}, P2Dapplet);
        } catch(Exception e) {
          println("failed to crate new applet");
        }
    }
*/


    private float totalMem() {
        return Runtime.getRuntime().totalMemory();
    }

    private float usedMem() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    private void prepareExitHandler () {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run () {
                System.out.println("SHUTDOWN HOOK");
                if (!realsenseShutdown) {
                    RealSence.shutDown();
                }
            }
        }));

    }




}
