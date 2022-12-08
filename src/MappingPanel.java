import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.nio.Buffer;

public class MappingPanel extends JPanel implements MouseListener {

    public final int BITMAP_WIDTH = 2048;
    private final int BITMAP_HEIGHT = 2048;
    private final int MAX_ITERATIONS = 50;


    double uMax = BITMAP_WIDTH;
    double uMin = 0;
    double vMax = BITMAP_HEIGHT;
    double vMin = 0;
    WritableRaster raster;
    BufferedImage image;
    Graphics2D g2d;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSettings(g);

        for (int u = 0; u < BITMAP_WIDTH; u++) {
            for (int v = 0; v < BITMAP_HEIGHT; v++) {
                double x = xMin + (u - uMin) / (uMax - uMin) * (xMax - xMin);
                double y = yMin + (v - vMin) / (vMax - vMin) * (yMax - yMin);


                ComplexNumber z = new ComplexNumber(0, 0);
                ComplexNumber c = new ComplexNumber(x, y);

                int counter = mandelbrot(z, c);
                Color color = chooseColor(counter);

                int[] colorArray = {color.getRed(), color.getGreen(), color.getBlue()};
                raster.setPixel(u, v, colorArray);
            }
        }
        g2d.drawImage(image, scale, this);
    }

    public int mandelbrot(ComplexNumber z, ComplexNumber c) {
        int counter = 0;
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            z = z.times(z).add(c);
            if (z.argument() < 2.0) {
                counter++;
            }
        }
        return counter;
    }

    private Color chooseColor(int counter) {
        Color color = Color.black;
        switch (counter % 8) {
            case 0 -> color = Color.green;
            case 1 -> color = Color.gray;
            case 2 -> color = Color.gray;
            case 3 -> color = Color.yellow;
            case 4 -> color = Color.magenta;
            case 5 -> color = Color.white;
            case 6 -> color = Color.cyan;
            case 7 -> color = Color.orange;
            default -> color = Color.pink;
        }
        return color;
    }

    Color chooseColor(double x, double y) {
        double radius = Math.sqrt(x * x + y * y);
        Color color;
        if (radius > 1) {
            color = Color.black;
        } else {
            double val = Math.sqrt(Math.cos(radius * Math.PI));
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

        double w = this.getWidth();
        this.image = new BufferedImage(BITMAP_WIDTH, BITMAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
        raster = this.image.getRaster();
        double h = this.getHeight();
        this.addMouseListener(this);
        scale = new AffineTransform();
        scale.setToScale(w / BITMAP_WIDTH, w / BITMAP_HEIGHT);
    }

    double xMax = 2;
    double xMin = -2;
    double yMax = 2;
    double yMin = -2;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            double x = xMin + e.getX() / uMax *  4 ;
            double y = yMin + e.getY() / vMax * 4;

            double dx = .2;
            xMin = x - dx;
            yMin = y - dx;
            System.out.println("x " + x);

            this.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
