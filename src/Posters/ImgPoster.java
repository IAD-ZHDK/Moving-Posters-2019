package Posters;


import processing.core.*;
import java.util.ArrayList;
public class ImgPoster extends Poster{


    private int imageSize;
    private String directory;
    private String prefix;
    public ImgPoster(PApplet parent, boolean DEBUG, String directory, String prefix, int totalImages, int imageSize) {
        super(parent, DEBUG);
        this.directory = directory;
        this.prefix = prefix;

        loading = true;
        this.imageSize = imageSize;
        p.rectMode(p.CENTER);
        this.totalImages = totalImages;
        imageArray = new PImage[totalImages];
        //loadState = new  ArrayList<Integer>();
       //for (int i = 0; i <totalImages; i++) {
        //    loadState.add(i);
            //String seriesNo = p.nf(i, 3);
           // imageArray[i] = p.requestImage(directory+""+prefix+seriesNo+".jpg");
        //}
    }

    public boolean draw(PVector Pos) {
        if (imgCount<totalImages) {
            p.println(imgCount);
            //p.println("loading images:");
           /* for (int j = 0; j <loadState.size(); j++) {
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
            */
           // int i = loadState.get(0);
            String seriesNo = p.nf(imgCount, 3);
            imageArray[imgCount] = p.loadImage(directory+""+prefix+seriesNo+".jpg");
            if (imageArray[imgCount].width !=  p.width/imageSize) {
                imageArray[imgCount].resize(p.width / imageSize, p.height);
            }
            //loadState.remove(0);
            imgCount++;
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
        p.set(0, 0, imageArray[index]);
        p.set(p.width/2, 0, imageArray[index]);
    }





}