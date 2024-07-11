import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
    This invisible canvas sits on top of all the other canvases (layers) and absorbs all the MouseEvents. 
    Then it passses those mouse events to the intended canvas (the currently selected layer). 
    This allows you to draw on the intended canvas no matter if there are layers above it or not, 
    because this invisible layer is always on top of everything propagating the MouseEvent to where it needs to be.
*/
public class InputCanvas extends Canvas implements MouseListener, MouseMotionListener {
    public InputCanvas(Project project, int width, int height) {
        super(project, width, height);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void mousePressed(MouseEvent e) {
        this.getProject().getEditor().getCurrentLayer().mousePressed(e);
    }

    public void mouseClicked(MouseEvent e) {
        this.getProject().getEditor().getCurrentLayer().mouseClicked(e);
    }

    public void mouseExited(MouseEvent e) {
        this.getProject().getEditor().getCurrentLayer().mouseExited(e);
    }

    public void mouseEntered(MouseEvent e) {
        this.getProject().getEditor().getCurrentLayer().mouseEntered(e);
    }

    public void mouseReleased(MouseEvent e) {
        this.getProject().getEditor().getCurrentLayer().mouseReleased(e);
    }

    public void mouseDragged(MouseEvent e) {
        this.getProject().getEditor().getCurrentLayer().mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e) {
        this.getProject().getEditor().getCurrentLayer().mouseMoved(e);
    }
}