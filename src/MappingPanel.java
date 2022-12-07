import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.nio.Buffer;

public class MappingPanel extends JPanel {
    private final int BITMAP_WIDTH = 1024;
    private final int BITMAP_HEIGHT = 1024;
    double xMax = 1;
    double xMin = -1;
    double uMax = BITMAP_WIDTH;
    double uMin = 0;
    double yMax = 1;
    double yMin = -1;
    double vMax = BITMAP_HEIGHT;
    double vMin = 0;
    WritableRaster raster;
    BufferedImage image;
    Graphics2D g2d;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSettings(g);

        ComplexNumber z = new ComplexNumber(0, 0);
        ComplexNumber c = new ComplexNumber(xMax, xMax);

        for (int i = 0; i < 50; i++) {
            z = z.times(z).add(c);
            double argument = z.argument();

            if (argument > 2) {

            } else {

            }
        }

        for (int u = 0; u < BITMAP_WIDTH; u++) {
            for (int v = 0; v < BITMAP_HEIGHT; v++) {
                double x = xMin + (u - uMin) / (uMax - uMin) * (xMax - xMin);
                double y = yMin + (v - vMin) / (vMax - vMin) * (yMax - yMin);

                Color color = chooseColor(x, y);
                int[] colorArray =
                        {color.getRed(), color.getGreen(), color.getBlue()};

                raster.setPixel(u, v, colorArray);

                /*
                if (i < j) {
                    raster.setPixel(j, i, black);
                } // if
                else {
                    raster.setPixel(j, i, white);
                } // else
                */
            }

        }
        g2d.drawImage(image, scale, this);
    }

    Color chooseColor(double x, double y) {
        double radius =  Math.sqrt(x * x + y * y);
        Color color;
        if (radius > 1){
            color = Color.black;
        }else {
            double val = Math.sqrt( Math.cos(radius  * Math.PI));
            int r = (int) (158 * val);
            int g = (int) (0 * val);
            int b = (int) (255 * val);
            color = new Color(r, g, b);
            System.out.println("val " + val);

        }


        return color;
    }

    AffineTransform scale;

    private void paintSettings(Graphics g) {
        g2d = (Graphics2D) g;

        // Create an object to use in making
        // the picture fit the window.
        double w = this.getWidth();
        this.image = new BufferedImage(BITMAP_WIDTH, BITMAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
        raster = this.image.getRaster();
        double h = this.getHeight();
        scale = new AffineTransform();
        scale.setToScale(w / BITMAP_WIDTH, w / BITMAP_WIDTH);
    }
}
