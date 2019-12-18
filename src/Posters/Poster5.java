// Ramona Ruettimann

package Posters;

import processing.core.*;

import java.util.ArrayList;

public class Poster5 extends Poster {
    private int imageSize = 2;

    //state
    private String activeState = "videoStateOn";

    private int boredTimer = 0;
    private boolean bored = true;
    private PVector Pos, historyPos;

    private float hx = 0, hy = 0, px = 0, py = 0;

    private PImage[] imageArray;
    private ArrayList<String> loadState;
    private int womStrt = 0;
    private int womEnd = 7;
    private int manStrt = womEnd;
    private int manEnd = womEnd+7;
    private int noWomStrt = manEnd;
    private int noWomEnd = manEnd+11; //47
    private int noManStrt = noWomEnd;
    private int noManEnd = noWomEnd+11; //47

    private int passiveCounter = noWomStrt, i = 0;
    private int passiveCounterMen = noManStrt, j=0;


    public Poster5(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        String directory = "Poster5/";
        this.totalImages = noManEnd;
        loadState = new ArrayList<String>();
        imageArray = new PImage[totalImages];
        int j = 0;
        for (int i = 0; i <womEnd; i++) {
            String seriesNo = p.nf(j, 3);
            j++;
            loadState.add(directory+"image"+seriesNo+".png");
            //imageArray[i] = p.requestImage(directory+"image"+seriesNo+".png");
            p.println(i);
        }
        j = 0;

        for (int i = manStrt; i <manEnd; i++) {
            String seriesNo = p.nf(j, 3);
            j++;
            loadState.add(directory+"image_men_"+seriesNo+".png");
            //imageArray[i] = p.requestImage(directory+"image_men_"+seriesNo+".png");
        }
        j = 0;

        for (int i = noWomStrt; i <noWomEnd; i++) {
            String seriesNo = p.nf(j, 3);
            j++;
            //loadState.add(i);
            loadState.add(directory+"imagenothing"+seriesNo+".png");
            //imageArray[i] = p.requestImage(directory+"imagenothing"+seriesNo+".png");

        }
        j = 0;

        for (int i = noManStrt; i <noManEnd; i++) {
            p.println(j);
            String seriesNo = p.nf(j, 3);
            j++;
            //loadState.add(i);
            loadState.add(directory+"image_men_nothing"+seriesNo+".png");
            //imageArray[i] = p.requestImage(directory+"image_men_nothing"+seriesNo+".png");
        }

        p.imageMode(p.CORNER);
    }


    public boolean draw(PVector Pos) {
        if (imgCount<totalImages) {
            p.println(imgCount);
            String seriesNo = p.nf(imgCount, 3);
            imageArray[imgCount] = p.loadImage(loadState.get(imgCount));
            if (imageArray[imgCount].width !=  p.width/imageSize) {
                imageArray[imgCount].resize(p.width / imageSize, p.height);
            }
            imgCount++;
            loadingAnimation();
        } else{
            loading = false;
            drawimages(Pos);
        }
        return true;
    }

    private void drawimages(PVector Pos) {
        if (activeState.equals("videoStateOff")) {
            runpassiveState(Pos);
        } else if (activeState.equals("videoStateOn")) {
            runactiveState(Pos);
        } else {
            p.println("State error");
        }
    }

    private void runactiveState(PVector Pos) {

        int indexwomen =  p.constrain( p.floor((womEnd)*Pos.x), womStrt, womEnd);
        int indexmen =  p.constrain(manStrt+ p.floor((womEnd)*Pos.x), manStrt, manEnd);

        p.image(imageArray[indexwomen], 0, 0);
        p.image(imageArray[indexmen],  p.width/2, 0);
        px = (Pos.x);
        py = (Pos.y);

        if (hx == px && hy == py) {
            boredTimer++;
            if (boredTimer > 100) {
                activeState = "videoStateOff";
                boredTimer = 0;
            }
        }

        historyPos = Pos.copy();
        hx = (historyPos.x);
        hy = (historyPos.y);
    }

    private void runpassiveState(PVector Pos)  {

        p.image(imageArray[passiveCounter], 0, 0);
        p.image(imageArray[passiveCounterMen],  p.width/2, 0);
        //if (frameCount % 120 == 1  ) println("FPS"+frameRate); // for checking the performance of your code

        px = (Pos.x);
        py = (Pos.y);

        //println(px + "-" + hx);
        if (hx != px || hy != py) {
            activeState = "videoStateOn";
        }
        i++;
        if (i > 1000) i = 0;
        if (i<55 && i%5== 0) passiveCounter ++;
        if (i>550 && i<605 && i%5== 0) passiveCounterMen ++;
        // todo: fix array here
        if (passiveCounter > noWomEnd - 1) {
            passiveCounter = noWomStrt;
        }
        if (passiveCounterMen > noManEnd - 1) {
            passiveCounterMen = noManStrt;
        }
        historyPos = Pos.copy();
        hx = (historyPos.x);
        hy = (historyPos.y);
    }


}
