package Posters;


import processing.core.*;
import java.util.ArrayList;
public class ImgPoster extends Poster{


    private int imageSize;
    private String directory;
    private String prefix;
    private String extension;
    public ImgPoster(PApplet parent, boolean DEBUG, String directory, String prefix,  String extension, int totalImages, int imageSize) {
        super(parent, DEBUG);
        this.directory = directory;
        this.prefix = prefix;
        loading = true;
        this.imageSize = imageSize;
        this.extension = extension;
        p.rectMode(p.CENTER);
        this.totalImages = totalImages;
        imageArray = new PImage[totalImages];
    }

    public void draw (PVector Pos) throws Exception {
        if (imgCount<totalImages) {

            String seriesNo = p.nf(imgCount, 3);

            imageArray[imgCount] = p.loadImage(directory + "" + prefix + seriesNo + "." + extension);
            if (imageArray[imgCount] == null) {
                p.println("failed to load image");
                loading = false;
                // p.println("img: "+imgCount+" loaded");
                throw new Exception("failed to load image");
            }
            if (imageArray[imgCount].width !=  p.width/imageSize) {
                imageArray[imgCount].resize(p.width / imageSize, p.height);
            }
            p.println("img: "+imgCount+" loaded");
            imgCount++;
            loadingAnimation();
        } else{
            loading = false;
            drawimages(Pos);
        }

    }

    protected void drawimages(PVector Pos) {
        int index = p.constrain(p.floor(imageArray.length*Pos.x), 0, imageArray.length-1);
        index = p.abs(index);
        p.set(0, 0, imageArray[index]);
        p.set(p.width/2, 0, imageArray[index]);
    }




}