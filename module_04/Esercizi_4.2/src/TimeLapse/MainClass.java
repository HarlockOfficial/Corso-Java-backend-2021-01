package TimeLapse;


import com.github.sarxos.webcam.Webcam;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
    /**
     * Questo metodo dato un array di immagini crea una gif e la salva su disco.
     *
     * @param captures       Le immagini da salvare
     * @param millisPerFrame durata di ogni frame della gif in millisecondi
     * @param filename       Nome del file su cui salvare la gif.
     * @return
     */
    public static boolean writeGif(BufferedImage[] captures, int millisPerFrame, String filename) {
        ImageOutputStream output;
        try {
            output = new FileImageOutputStream(new File(filename));
            GifSequenceWriter writer =
                    new GifSequenceWriter(output, 5, millisPerFrame, true);
            for (BufferedImage image : captures)
                writer.writeToSequence(image);
            writer.close();
            output.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Funct a = (BufferedImage[] array) -> {
            writeGif(array, 100, "out.gif");
            JOptionPane.showMessageDialog(null, "Gif Created");
        };
        create(a);
    }

    public interface Funct {
        void end(BufferedImage[] array) throws IOException;
    }
    public static void create(Funct aaa) {
        final boolean[] loop = {true};
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        JFrame f = new JFrame();
        f.setSize(640, 480);
        JLabel l = new JLabel();
        webcam.open();
        ImageIcon image = new ImageIcon(webcam.getImage());
        l.setIcon(image);
        f.add(l);
        f.pack();
        f.setVisible(true);
        //ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loop[0] = false;
                webcam.close();
            }
        });
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new Thread(() -> {
            ArrayList<BufferedImage> arr = new ArrayList<>();
            int n = 3;
            do {
                n = JOptionPane.showConfirmDialog(null, "Start capturing images");
                if (n == 0) {
                    arr.add(webcam.getImage());
                }
            } while (n == 0);
            if (arr.size() > 0) {
                BufferedImage[] array = new BufferedImage[arr.size()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = arr.get(i);
                }
                try {
                    aaa.end(array);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (loop[0]) {
            image = new ImageIcon(webcam.getImage());
            l.setIcon(image);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
