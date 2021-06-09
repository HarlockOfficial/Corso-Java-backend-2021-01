package ImageFilters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedImage in = ImageIO.read(new File("input.jpg"));
        BufferedImage out;
        out = applyBN(in);
        ImageIO.write(out, "jpg", new File("bn.jpg"));
        out = applymirror(in);
        ImageIO.write(out, "jpg", new File("mirror.jpg"));
        out = applyGBR(in);
        ImageIO.write(out, "jpg", new File("gbr.jpg"));
        out = applyNegative(in);
        ImageIO.write(out, "jpg", new File("negative.jpg"));
        out = applyPosterize(in, 3);
        ImageIO.write(out, "jpg", new File("poster.jpg"));
    }

    public static BufferedImage applyPosterize(BufferedImage in, int levels) throws IOException{
        int gap = 255/levels;
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<in.getWidth(); ++i){
            for(int j = 0; j<in.getHeight(); ++j){
                Color c = new Color(in.getRGB(i, j));
                boolean done_red = false, done_blue = false, done_green = false;
                for(int val = 255-gap; val>=0; val -= gap) {
                    if(c.getRed()>=val && !done_red){
                        c=new Color(val, c.getGreen(), c.getBlue());
                        done_red = true;
                    }
                    if(c.getGreen()>=val && !done_green){
                        c=new Color(c.getRed(), val, c.getBlue());
                        done_green = true;
                    }
                    if(c.getBlue()>=val && !done_blue){
                        c=new Color(c.getRed(), c.getGreen(), val);
                        done_blue = true;
                    }
                }
                out.setRGB(i, j, c.getRGB());
            }
        }
        return out;
    }

    public static BufferedImage applyNegative(BufferedImage in) throws IOException{
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<in.getWidth(); ++i){
            for(int j = 0; j<in.getHeight(); ++j){
                Color c = new Color(in.getRGB(i,j));
                c = new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
                out.setRGB(i, j, c.getRGB());
            }
        }
        return out;
    }

    public static BufferedImage applyGBR(BufferedImage in) throws IOException{
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<in.getWidth(); ++i){
            for(int j = 0; j<in.getHeight(); ++j){
                Color c = new Color(in.getRGB(i,j));
                c = new Color(c.getGreen(), c.getBlue(), c.getRed());
                out.setRGB(i, j, c.getRGB());
            }
        }
        return out;
    }

    public static BufferedImage applymirror(BufferedImage in) throws IOException{
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<in.getWidth(); ++i){
            for(int j = 0; j<in.getHeight(); ++j){
                out.setRGB(i, j, in.getRGB(in.getWidth()-i-1, j));
            }
        }
        return out;
    }

    public static BufferedImage applyBN(BufferedImage in) throws IOException{
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<in.getWidth(); ++i){
            for(int j = 0; j<in.getHeight(); ++j){
                Color c = new Color(in.getRGB(i, j));
                int lux = c.getRed()*77/255+c.getGreen()*150/255+c.getBlue()*28/255;
                out.setRGB(i, j, new Color(lux, lux, lux).getRGB());
            }
        }
        return out;
    }

}
