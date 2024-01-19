package software.ulpgc.imageviewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (bitmap != null){
            Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
            Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));
            int x = (this.getWidth() - resized.width) / 2;
            int y = (this.getHeight() - resized.height) / 2;
            g.drawImage(bitmap, x, y, resized.width, resized.height, null);
        }

        /*
        Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
        Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));
        int x = (this.getWidth() - bitmap.getWidth()) / 2;
        int y = (bitmap.getHeight() - bitmap.getHeight()) / 2;
        g.drawImage(bitmap, x, y, null);
         */
    }

    public static class Resizer {
        private final Dimension targetDimension;

        public Resizer(Dimension targetDimension) {
            this.targetDimension = targetDimension;
        }

        public Dimension resize(Dimension originalDimension) {
            int originalWidth = originalDimension.width;
            int originalHeight = originalDimension.height;

            int targetWidth = targetDimension.width;
            int targetHeight = targetDimension.height;

            double ratio = ratioCalculator(targetWidth, targetHeight, originalWidth, originalHeight);

            int newWidth = (int) (originalWidth * ratio);
            int newHeight = (int) (originalHeight * ratio);

            return new Dimension(newWidth, newHeight);
        }

        private double ratioCalculator(int targetWidth, int targetHeight, int originalWidth, int originalHeight) {
            double widthRatio = (double) targetWidth / originalWidth;
            double heightRatio = (double) targetHeight / originalHeight;
            return Math.min(widthRatio, heightRatio);
        }
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
