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
This is the JComponent that contains the BufferedImage that allows the user to actually draw an image
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public class Canvas extends JComponent {
    public int oldX = 0;
    public int oldY = 0;
    public int newX = 0;
    public int newY = 0;
    public int oldsX = 0; //For use in single drags: SquareTool, CircleTool
    public int oldsY = 0; //For use in single drags: SquareTool, CircleTool
    public int newsX = 0; //For use in single drags: SquareTool, CircleTool
    public int newsY = 0; //For use in single drags: SquareTool, CircleTool
    public int mode = 0;
    public int width;
    public int height;
    private boolean drawingEnabled = false;
    private boolean layerHidden = false;
    private BufferedImage image;
    private History history;
    private Graphics2D graph;
    private Graphics2D g2d;
    private Project project;
    //private ToolPicker toolpicker;

    /**
     retunrs a bool if the window is set to hidden
     * @return the boolean
     */
    public boolean isHidden()
    {
        return layerHidden;
    }

    /**
     the constructor fo this object.
     blanc canvas
     * @param project the project class that this object belongs to/is created in
     * @param width the windows width
     * @param height the windows heigh
     */
    public Canvas(Project project, int width, int height) {
        this.project = project;

        this.width = width;
        this.height = height;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        this.create();
    }

    /**
     the constructor fo this object
     contains an image opened from file
     * @param project the project class that this object belongs to/is created in
     * @param BufferedImage the image that has been opened from file
     */
    public Canvas(Project project, BufferedImage image){
        this.project = project;

        this.image = image;

        this.width = image.getWidth();
        this.height = image.getHeight();

        this.create();
    }

    /**
     sest up the window of this object
     */
    private void create() {
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(width, height);
        this.history = new History(image);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(false);
    }

    /**
     the built in paint component that draws the stuff on the BufferedImage using Graphics2D
     @param g the Graphics componenet of this object
     */
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        graph = (Graphics2D) this.getImage().createGraphics();

        //Let the tool handle the drawing
        if (drawingEnabled) {
            try {
                project.getToolPicker().getActiveTool().paint(this, graph);
            } catch (Throwable e) {
                System.out.println("Error: ");
                System.out.println(e);
            }
        }

        g2d.drawImage(this.getImage(), 0, 0, this);

        // data = image.getData();
    }

    /**
     returns the project that this object belongs to/is created in
     * @return project
     */
    public Project getProject() {
        return project;
    }

    /**
     enables drawing for this canvas, if the object is un-hidden
     */
    public void enableDrawing()
    {
        drawingEnabled = true;
    }

    /**
     disabled drawing for this canvas if the object is hidden
     */
    public void disableDrawing()
    {
        drawingEnabled = false;
    }

    /**
     hides this canvas. makes it invisible
     */
    public void hideLayer()
    {
        System.out.println("hide");
        layerHidden = true;
        disableDrawing();
        this.setVisible(false);
    }

    /**
     shows this canvas. makes it visible
     */
    public void showLayer()
    {
        System.out.println("show");
        layerHidden = false;
        enableDrawing();
        this.setVisible(true);
    }

    /**
     returns the x-coordinate of the mouse
     * @return newX
     */
    public int getXCoord()
    {
        return newX;
    }
    /**
     returns the y-coordinate of the mouse
     * @return newY
     */
    public int getYCoord()
    {
        return newY;
    }
    /**
     returns the width of the component
     @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     returns the height of the components
     @return heigh
     */
    public int getHeight() {
        return height;
    }

    /**
     undos the last action takes by user
     */
    public void undo() {
        boolean wasEnabled = this.drawingEnabled;
        this.disableDrawing();
        
        this.image = history.undo();
        graph = (Graphics2D) image.createGraphics();
        this.repaint();
        this.revalidate();

        if (wasEnabled) {
            this.enableDrawing();
        } else {
            this.disableDrawing();
        }
    }

    /**
     redos the last action undone by user
     */
    public void redo() {
        boolean wasEnabled = this.drawingEnabled;

        this.disableDrawing();
        BufferedImage change = history.redo();
        if (change != null) {
            this.image = change;
            graph = (Graphics2D) image.createGraphics();
            this.repaint();
            this.revalidate();
        }

        if (wasEnabled) {
            this.enableDrawing();
        } else {
            this.disableDrawing();
        }
    }

    /**
     * returns the bruch colour selected
     * @return project.getColorSelector().getColor();
     */
    public Color getBrushColor() {
        return project.getColorSelector().getColor();
    }

    /**
     returns brush size selected
     * @return project.getColorSelector().getBrushSize();
     */
    public int getBrushSize() {
        return project.getColorSelector().getBrushSize();
    }

    /**
     saves mouse coordinates when button clicked
     * @param e the MouseEvent manager
     */
    public void mousePressed(MouseEvent e) {
        newX = e.getX(); // gets x and y coordinate when mouse is clicked
        newY = e.getY();
        oldX = newX;
        oldY = newY;  
        repaint();
    }

    /**
     * not used
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * not used
     * @param e
     */
    public void mouseExited(MouseEvent e) {
    }
    /**
     * ot used
     * @param e
     */
    public void mouseEntered(MouseEvent e) {
    }


    /**
     saves the mouse coordinate when button released
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
        //Update history
        newsX = e.getX();// gets final x and y for singular drags
        newsY = e.getY();
        
        repaint(); //Repaint for all tools when mouse released
        history.append(image);
    }

    /**
     gets and saves the new mouse coordinates when the nouse button is clicked and the mouse is moved
     the previouse mouse coordinates are saved as old coordinates
     * @param e the MouseEvent manager
     */
    public void mouseDragged(MouseEvent e) {
        oldX = newX; // saves old x,y coordinates
        oldY = newY;
        newX = e.getX(); // gets current x,y coordinates
        newY = e.getY();

        if(project.getToolPicker().getActiveTool() instanceof PencilTool || project.getToolPicker().getActiveTool() instanceof BrushTool ||
        project.getToolPicker().getActiveTool() instanceof RubberTool)
        {
            repaint();
        }
    }

    /**
     not used
     * @param e
     */
    public void mouseMoved(MouseEvent e) {
    }

    /**
     returns the image that the user has drawn
     * @return
     */
    public BufferedImage getImage() {
        return image;
    }
}