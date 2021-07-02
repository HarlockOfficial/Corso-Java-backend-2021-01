package ImageBlur;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlurImage implements Runnable{
    private final BufferedImage input, output;
    private final int blurRadius;
    public BlurImage(String filename, int blurRadius) throws IOException {
        input = ImageIO.read(new File(filename));
        output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        this.blurRadius = blurRadius;
    }
    @Override
    public void run() {
        Thread t1 = new Thread(()->calculateBlur(0, input.getHeight()/4));
        Thread t2 = new Thread(()->calculateBlur(input.getHeight()/4, input.getHeight()/2));
        Thread t3 = new Thread(()->calculateBlur(input.getHeight()/2, input.getHeight()/4*3));
        Thread t4 = new Thread(()->calculateBlur(input.getHeight()/4*3, input.getHeight()));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException ex){
            //ignored
        }
    }
    private void calculateBlur(int startHeight, int endHeight){
        for(int i = startHeight; i<endHeight; ++i){
            for(int j=0; j<input.getWidth(); ++j){
                Color avg = getAvgSurrounding(i,j);
                output.setRGB(avg.getRed(), avg.getGreen(), avg.getBlue());
            }
        }
    }

    private Color getAvgSurrounding(int x, int y) {
        int sum = 0;
        int surroundingRadius = 0;
        for(int i = 0; i<blurRadius; ++i){
            for(int j = 0; j<blurRadius; ++j){
                int tmpI = i+x-blurRadius/2;
                int tmpJ = j+y-blurRadius/2;
                if(tmpI>=0 && tmpJ>=0 && tmpI<input.getHeight() && tmpJ<input.getWidth()){
                    sum+=input.getRGB(tmpI, tmpJ);
                    ++surroundingRadius;
                }
            }
        }
        return new Color(sum/(surroundingRadius));
    }
}
