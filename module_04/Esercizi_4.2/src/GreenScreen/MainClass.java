package GreenScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedImage in_green = ImageIO.read(new File("green_screen_zeb.jpg"));
        BufferedImage aot = ImageIO.read(new File("aot.jpg"));
        BufferedImage out = new BufferedImage(in_green.getWidth(), in_green.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Color green = new Color(48, 255, 17);
        double soglia = 150;
        for(int i = 0; i<in_green.getWidth(); ++i){
            for(int j = 0; j<in_green.getHeight(); ++j){
                Color c = new Color(in_green.getRGB(i, j));
                if(distance(c, green)<soglia){
                    c = new Color(aot.getRGB(i, j));
                }
                out.setRGB(i, j, c.getRGB());
            }
        }
        ImageIO.write(out, "jpg", new File("out.jpg"));
    }

    private static double distance(Color c, Color green) {
        return Math.sqrt(
                Math.pow(c.getRed()-green.getRed(),2) +
                Math.pow(c.getGreen() - green.getGreen(), 2) +
                Math.pow(c.getBlue() - green.getBlue(), 2)
        );
    }
}
