/*
import oscP5.*;
import netP5.*;
import processing.core.*;


public class Tracker {

        private PVector average  = new PVector();
        private PVector target = new PVector();
        private float angle = 0;
        private float averageWeight = .95f; // ratio of old data vs new
        int updateFlag = 0;


        Tracker(NetAddress myRemoteLocation, OscP5 oscP5) {
            oscP5 = new OscP5(this, 8338);
            myRemoteLocation = new NetAddress("localhost", 12000);
            oscP5.plug(this, "pose", "/pose/position");
            oscP5.plug(this, "scale", "/pose/scale");
            oscP5.plug(this, "stream", "/stream");
        }

        public void pose(float X, float Y) {
            target.x = X;
            target.y = Y;
            updateFlag = 0;
        }
        public void stream(float X, float Y) {
        target.x = X;
        target.y = Y;
        updateFlag = 0;
        }

        public void scale(float A) {
            target.z = A;
        }

        public PVector getTarget() {
            // smooth readings out to avoid any shake
            PVector temp;
            if (updateFlag <=30) {
                temp = target.copy();
            } else {
                // use the mouse if there is no communication
                //temp = new PVector(float(mouseX)/width, float(mouseY)/height, .1);
                angle += 0.005f;
                temp = new PVector((float)(Math.sin(angle)+1f)*.5f, (float)(Math.cos(angle)+1f)*.1f+.5f);
            }
            temp.mult(1-averageWeight);
            average.mult(averageWeight);
            average.add(temp);
            updateFlag ++;
            return average.copy();
        }
}
*/
