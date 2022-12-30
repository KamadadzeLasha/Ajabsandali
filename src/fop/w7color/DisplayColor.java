package fop.w7color;
import javax.swing.*;
import java.awt.*;

public class DisplayColor {

    public static void displayColor(RgbColor8Bit c) {
        JFrame jf = new JFrame("Color Test") {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue()));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jf.setLocation(1020, 100);
        jf.setSize(600, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) { // Here trying out own colors
        RgbColor rgbColor = new RgbColor(1, 1, 1, 1);
        displayColor(rgbColor.toRgbColor8Bit());
    }
}
