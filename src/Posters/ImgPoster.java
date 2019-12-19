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

    }

    public boolean draw(PVector Pos) {
        if (imgCount<totalImages) {
            p.println(imgCount);
            String seriesNo = p.nf(imgCount, 3);
            imageArray[imgCount] = p.loadImage(directory+""+prefix+seriesNo+".jpg");
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

    protected void drawimages(PVector Pos) {
        int index = p.constrain(p.floor(imageArray.length*Pos.x), 0, imageArray.length-1);
        index = p.abs(index);
        p.set(0, 0, imageArray[index]);
        p.set(p.width/2, 0, imageArray[index]);
    }





}