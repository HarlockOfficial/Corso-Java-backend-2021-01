package ImageFilters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedImage in = ImageIO.read(new File("input.jpg"));
        applyBN(in, "bn.jpg");
        /*applymirror(in, "mirror.jpg");
        applyGBR(in, "gbr.jpg");
        applyNegative(in, "negative.jpg");
        applyPosterize(in, "poster.jpg", 3);*/
    }

    private static void applyBN(BufferedImage in, String fileName) throws IOException{
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<in.getWidth(); ++i){
            for(int j = 0; j<in.getHeight(); ++j){
                Color c = new Color(in.getRGB(i, j));
                out.setRGB(i, j, c.getRed()*77/255+c.getGreen()*150/255+c.getBlue()*28/255);
            }
        }
        ImageIO.write(out, "jpg", new File(fileName));
    }

}
