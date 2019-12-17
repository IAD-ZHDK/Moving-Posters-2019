package Posters;


import processing.core.*;
import java.util.ArrayList;
public class imgPoster extends Poster{
    protected PImage[] imageArray;
    ArrayList<Integer> loadState;
    private int imageSize;
    private int totalImages;

    public imgPoster(PApplet parent, boolean DEBUG, String directory, String prefix, int totalImages, int imageSize) {
        super(parent, DEBUG);
        loading = true;
        this.imageSize = imageSize;
        p.rectMode(p.CENTER);
        this.totalImages = totalImages;
        imageArray = new PImage[totalImages];
        loadState = new  ArrayList<Integer>();
        for (int i = 0; i <totalImages; i++) {
            loadState.add(i);
            String seriesNo = p.nf(i, 3);
            imageArray[i] = p.requestImage(directory+""+prefix+seriesNo+".jpg");
            //  loadState[i] = false;
        }
    }

    public boolean draw(PVector Pos) {
        if (loadState.size()>0) {
            //p.println("loading images:");
            for (int j = 0; j <loadState.size(); j++) {
                int i = loadState.get(j);
                if (imageArray[i].width == 0) {
                    // p.println("image still loading");
                } else if (imageArray[i].width == -1){
                    p.println("image load fail");
                    return false; // failed to load an image
                }  else {
                    // resize
                    if (imageArray[i].width !=  p.width/imageSize) {
                        imageArray[i].resize(p.width / imageSize, p.height);
                    }
                    loadState.remove(j);
                    p.println(loadState.size());
                    break;
                }
            }
            loadingAnimation();
        } else{
            loading = false;
            drawimages(Pos);
        }
        return true;
    }

    protected void drawimages(PVector Pos) {
        int index = p.constrain(p.floor(imageArray.length*Pos.x), 0, imageArray.length-1);
        index = p.abs(index);
        //p.image(imageArray[index], 0, 0);
       // p.image(imageArray[index], p.width/2, 0);
        p.set(0, 0, imageArray[index]);
        p.set(p.width/2, 0, imageArray[index]);
    }

    protected void loadingAnimation() {
        p.background(0);
        // float x = (float)Math.sin(p.radians(p.frameCount))*10;
        // float y = (float)Math.cos(p.radians(p.frameCount))*10;
        p.pushStyle();
        p.fill(100);
        p.ellipseMode(p.RADIUS);
        float progress = p.map(loadState.size(), totalImages, 0, 0, 1)*p.TWO_PI;
        p.pushMatrix();
        p.translate(p.width/4,p.height/2);
        p.arc(0, 0, 30, 30, 0, progress);
        //p.arc(0, 0, 80, 80, p.PI, p.TWO_PI);
        // p.circle(0,0 ,10);
        p.translate(p.width/2,0);
        p.arc(0, 0, 30, 30, 0, progress);
        p.popMatrix();
        p.popStyle();
    }

    public void distroyImgs() {
        imageArray = null;
    }
   /* protected boolean checLoadStates(){
        for (int i = 0; i < imageArray.length; i++){
            if (imageArray[i].width == -1){
                return false;
            }
        }
        return true;
    }
    */

}