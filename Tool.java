import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
This is the interface for all the different tools. Different tools are made using polymorphism
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public interface Tool {

    /**
     the builting paint tool, that colours the buffered image using Graphics2D
     * @param canvas the canvas the tool will draw onto
     * @param graphics the passed grapics component
     */
    default public void paint(Canvas canvas, Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(canvas.getBrushSize()));

        graphics.setColor(canvas.getBrushColor());
        graphics.drawLine(canvas.newX, canvas.newY, canvas.oldX, canvas.oldY); // draws line from where mouse was to where it is
    };

    /**
     returns the name of the polymorphised object
     * @return the object name
     */
    public String getName();

    /**
     returns the icon of the polymorphised object
     * @return the icon image
     */
    public ImageIcon getIcon();
}