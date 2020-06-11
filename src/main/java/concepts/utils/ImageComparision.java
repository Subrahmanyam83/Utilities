package concepts.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageComparision {


    public static void main(String[] args) throws IOException {
        BufferedImage img1 = ImageIO.read(new File("/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/resources/images/img-1.png"));
        BufferedImage img2 = ImageIO.read(new File("/Users/n471306/Dropbox/Subrahmanyam-Projects/MY_GITHUB_PROJECTS/Maven/testmaven/src/main/resources/images/img-2.png"));

        double p = getDifferencePercent(img1, img2);
        System.out.println("diff percent: " + p);
    }

    private static double getDifferencePercent(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();

        if(width!=663 || height!=1024){
            img1=resize(img1,1024,663);
        }

        if(width2!=663 || height2!=1024){
            img2=resize(img2,1024,663);

        }

//        if (width != width2 || height != height2) {
//
//            //throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
//        }

        System.out.println(img1.getWidth()+"---"+img1.getHeight());
        System.out.println(img2.getWidth()+"---"+img1.getHeight());



        long diff = 0;
        for (int y = 0; y < 1024; y++) {
            for (int x = 0; x < 663; x++) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        long maxDiff = 3L * 255 * 663 * 1024;

        return 100.0 * diff / maxDiff;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private static int pixelDiff(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
    }
}
