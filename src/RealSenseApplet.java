import processing.core.*;
import ch.bildspur.realsense.*;
import java.util.ArrayList;
import gab.opencv.*;
import java.awt.Rectangle;


public class RealSenseApplet extends PApplet {
    //  private SyphonClient client;
    public PImage realsenseImg;
    private OpenCV opencv;
    private boolean loop;
    private boolean DEBUG = false;
    private int trackingLow = 0;
    private int trackingHigh = 1000;
    private boolean cameraAvailable = false;
    private boolean videoOutput = false;
    private PVector Pos;
    private boolean updated  = false;
    private boolean cameraLoadSuccess = false;
    ArrayList<Contour> contours;
    RealSenseCamera camera = new RealSenseCamera(this);

    public RealSenseApplet(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    public void settings() {
        //size(640, 480, P2D);
        if (DEBUG) {
            size(640, 480, P2D);
        } else {
            fullScreen(P2D, 2);
        }
    }

    public void setup() {
       // setupCamera();
        realsenseImg = new PImage(width, height);
        Pos = new PVector(.5f, .5f, .5f);
        //frameRate(30);
        this.background(255,0,0);
        runCamera(false);
        frameRate(30 );
    }


    public void draw() {
        // todo: sometimes getting error, "java.lang.RuntimeException: Frame didn't arrived within 5000"
        try {
            if (cameraAvailable) {
                camera.readFrames();
                camera.createDepthImage(trackingLow, trackingHigh);
                realsenseImg = camera.getDepthImage();
                cameraLoadSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            cameraLoadSuccess = false;
        }

        if (cameraLoadSuccess) {
            if (!videoOutput) {
                updated = true;
                set(0,0,realsenseImg);
                blobTracking(realsenseImg);
                if (contours.size() > 0) {
                    Contour biggestContour = contours.get(0);
                    Rectangle r = biggestContour.getBoundingBox();
                    if (r.width >= 60) {
                        noFill();
                        strokeWeight(2);
                        stroke(30, 100, 100);
                        rect(r.x, r.y, r.width, r.height);
                        fill(30, 100, 100);
                        rect(r.x + r.width / 2, r.y + r.height / 2, 10, 10);
                        float x = r.x + r.width / 2;
                        float y = r.y + r.height / 2;
                        float z = width / r.width;
                        x = x / width;
                        y = y / height;
                        Pos.set(x, y, z);
                        updated = true;
                    }
                }
            } else {
                //  just video
                updated = true;
                set(0,0,realsenseImg);
            }
        } else {
            if (this.frameCount%1000 == 1) {
                setupCamera();
            }
        }
       if( DEBUG) {
           surface.setTitle("FPS: " + floor(frameRate)); //Set the frame title to the frame rate
       }
    }

    private void setupCamera() {
        println("setuping up Camera");
        try {
            camera.start(640, 480, 30, true, false);
            opencv = new OpenCV(this, width, height);
            cameraAvailable = true;
        } catch (Exception e) {
            //  e.printStackTrace();
            println("camera not available");
        }

    }

    public PVector getPos() {
        videoOutput = false;
        if (!updated) {
            return null;
        }
        updated = false;
        return Pos;
    }

    public PImage getImage() {
        videoOutput = true;
        if (!updated) {
            return null;
        }
        updated = false;
        return realsenseImg;
    }

    public void runCamera(boolean loop) {
        if (this.loop != loop) {
            this.loop = loop;
            if (loop) {
                this.loop();
                println("loop");
            } else {
                this.noLoop();
                println("noloop");
            }
        }

    }

    public void shutDown() {
        camera.stop();
        exit();
    }

    private void blobTracking(PImage depthImage) {
        PImage trackImage = depthImage;
        opencv.loadImage(trackImage);
        opencv.contrast(1.3f);
        opencv.dilate();
        opencv.blur(5);
        opencv.erode();
        opencv.erode();
        opencv.erode();
      //  set(0, 0,opencv.getSnapshot());
        contours = opencv.findContours(true, true);
    }
}