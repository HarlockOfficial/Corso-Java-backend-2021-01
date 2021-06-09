package ShadesOfGrey;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<img.getWidth(); ++i){
            img.setRGB(i, i, new Color(i,i,i).getRGB());
            for(int j = 0; j<i; ++j){
                img.setRGB(i, j, img.getRGB(i, i));
                img.setRGB(j, i, img.getRGB(i, i));
            }
        }
        try {
            ImageIO.write(img, "jpg", new File("tmp.jpg"));
        }catch (IOException ex){
            System.err.println("Error: "+ ex.getMessage());
        }
    }
}
