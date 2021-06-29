package ImageBlur;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "files/image.png";
        int blurRadius = 1;
        Thread t = new Thread(new BlurImage(fileName, blurRadius));
        t.start();
        t.join();
    }
}
