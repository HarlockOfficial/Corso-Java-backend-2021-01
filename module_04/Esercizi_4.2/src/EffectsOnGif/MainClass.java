package EffectsOnGif;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        TimeLapse.MainClass.create(aa);
    }
    private static final TimeLapse.MainClass.Funct aa = (BufferedImage[] array) -> {
        ArrayList<BufferedImage> arr = new ArrayList<>();
        for(BufferedImage img: array){
            arr.add(img);
            arr.add(ImageFilters.MainClass.applyNegative(img));
            arr.add(ImageFilters.MainClass.applyPosterize(img, 3));
            arr.add(ImageFilters.MainClass.applyBN(img));
            arr.add(ImageFilters.MainClass.applyGBR(img));
            arr.add(ImageFilters.MainClass.applymirror(img));
        }
        array = new BufferedImage[arr.size()];
        for(int i = 0 ;i<array.length; ++i){
            array[i] = arr.get(i);
        }
        TimeLapse.MainClass.writeGif(array, 300, "out.gif");
        System.out.println("Gif Created");
    };
}
