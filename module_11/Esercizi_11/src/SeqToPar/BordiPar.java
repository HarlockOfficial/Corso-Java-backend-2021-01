package SeqToPar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BordiPar extends Thread {
    private static final int black = new Color(0, 0, 0).getRGB(), white = new Color(255, 255, 255).getRGB();
    private final BufferedImage inputImage, outputImage;
    private final String outputFileName;
    private boolean completed;

    public BordiPar(String inputFileName, String outputFileName) throws IOException {
        inputImage = ImageIO.read(new File(inputFileName));
        outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        this.outputFileName = outputFileName;
        completed = false;
    }

    @Override
    public void run() {
        int halfWidth = inputImage.getWidth()/2;
        Thread a = new Thread(()->{
            for (int i = 0; i < halfWidth; i++) {
                for (int j = 0; j < inputImage.getHeight(); j++) {
                    generatePixel(i, j);
                }
            }
        }), b = new Thread(()->{
            for (int i = 0; i < halfWidth; i++) {
                for (int j = 0; j < inputImage.getHeight(); j++) {
                    generatePixel(halfWidth+i, j);
                }
            }
        });
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        completed = true;
    }

    private void generatePixel(int i, int j) {
        double sumNeighboors = 0;
        Color currentPixel = new Color(inputImage.getRGB(i, j));
        for (int internalI = -1; internalI < 2; internalI++) {
            for (int internalJ = -1; internalJ < 2; internalJ++) {
                //Controlli per vedere se il vicino è fuori matrice
                if (internalI == 0 && internalI == internalJ) continue;
                int neighboorI = i + internalI;
                if (neighboorI < 0 || neighboorI >= inputImage.getWidth()) continue;
                int neighboorJ = j + internalJ;
                if (neighboorJ < 0 || neighboorJ >= inputImage.getHeight()) continue;
                Color pendingPixel = new Color(inputImage.getRGB(neighboorI, neighboorJ));
                sumNeighboors += colorDistance(currentPixel, pendingPixel);
            }
            if (sumNeighboors > 255) outputImage.setRGB(i, j, black);
            else outputImage.setRGB(i, j, white);
        }
    }

    private double colorDistance(Color c1, Color c2) {
        int redDifference = c1.getRed() - c2.getRed();
        int greenDifference = c1.getGreen() - c2.getGreen();
        int blueDifference = c1.getBlue() - c2.getBlue();
        return Math.sqrt(Math.pow(redDifference, 2) + Math.pow(greenDifference, 2) + Math.pow(blueDifference, 2));
    }

    public boolean isCompleted() {
        return completed;
    }

    public void createImage() {
        if (!completed) throw new IllegalThreadStateException("Image creation not finished yet");
        try {
            ImageIO.write(outputImage, "png", new File(outputFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
