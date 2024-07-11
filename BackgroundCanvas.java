import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BackgroundCanvas extends Canvas {
    public BackgroundCanvas(Project project, int width, int height) {
        super(project, width, height);
    }

    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.createGraphics();
        
        //Transparent background texture
        int scale = 10;
        for (int x = 0; x < this.getWidth(); x+=scale) {
            for (int y = 0; y < this.getHeight(); y+=scale) {
                if ((x + y) % 4 == 0) { //Fill every other square
                    g2d.setColor(Color.white);
                    g2d.fillRect(x,y,scale,scale);
                } else {
                    g2d.setColor(new Color(0xDDDDDD));
                    g2d.fillRect(x,y,scale,scale);
                }
            }
        }
        return image;
    }
}