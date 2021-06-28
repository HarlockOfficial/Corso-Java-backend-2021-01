package SeqToPar;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        BordiPar bp = new BordiPar("gris.png", "outPar.png");
        BordiSeq bs = new BordiSeq("gris.png", "outSeq.png");
        long millisSeqStart = System.currentTimeMillis();
        bs.start();
        long millisSeqEnd = System.currentTimeMillis();
        long millisParStart = System.currentTimeMillis();
        bp.start();
        try {
            bp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long millisParEnd = System.currentTimeMillis();
        bs.createImage();
        bp.createImage();
        System.out.println((millisSeqEnd-millisSeqStart) + " " + (millisParEnd-millisParStart) + " " +
                ((double)(millisSeqEnd-millisSeqStart)/(double)(millisParEnd-millisParStart))*100.0);
    }
}
