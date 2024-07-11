import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.image.BufferedImage;

import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;
import java.util.ArrayList;

/**
This is the file that the user can draw on.
Is the imag that the user can save.
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public class Editor extends JPanel {
    // declares global variables
    // layers window
    private JLayeredPane layeredPane = new JLayeredPane();
    // misc
    
    private int width = 800;
    private int height = 600;
    
    private int selectedLayer = 0;
    private ArrayList<Canvas> layers = new ArrayList<Canvas>();

    private Border border = BorderFactory.createLineBorder(Color.black, 1);
    private Project project;

    /**
    The constructor for this object.
    Sets up the window and all componenets contained with in.
     * @param pro the Project class that this object blongs to/is created in
     */
    public Editor(Project pro) {
        project = pro; // saves project object

        this.setSize(width, height);
        this.setLayout(null);

        Canvas backgroundCanvas = new BackgroundCanvas(project, width, height);
        backgroundCanvas.setLocation(0,0);
        backgroundCanvas.repaint();
        layeredPane.add(backgroundCanvas, new Integer(-1000));

        // sets up pane
        layeredPane.setLocation(10, 10);
        layeredPane.setSize(width, height);
        layeredPane.setLayout(null);
        layeredPane.setBackground(Color.white);
        layeredPane.setBorder(border);
        this.add(layeredPane);

        //The inputCanvas sits on top of all other layers, takes their mouse events and passes them to the currently selected layer
        Canvas inputCanvas = new InputCanvas(project, width, height);
        inputCanvas.setBackground(new Color(0,0,0,0));
        inputCanvas.setLocation(0,0);
        layeredPane.add(inputCanvas, new Integer(1000));

        addLayer();
    }

    /**
     adds a new canvas/layer to the editor that the user can draw on
     * @return newCanvas, the created canvas or NULL
     */
    public Canvas addLayer() {
        if (layers.size() >= 10) {
            JOptionPane.showMessageDialog(null, "Cannot create more than 10 layers");
            return null;
        }
        // creates new editor
        Canvas newCanvas = new Canvas(project, width, height);

        // sets up editor
        newCanvas.setBackground(new Color(0, 0, 0, 0));
        newCanvas.setLocation(0, 0);

        // saves editor to array
        layers.add(newCanvas);
        layeredPane.add(newCanvas, new Integer(layers.size() + 1));

        project.getLayerSelector().addLayerButton();

        setCurrentLayer(layers.size() - 1);
        return newCanvas;
    }

    /**
     adds a new canvas/layer to the editor that the user can draw on.
     Opened from an image in file
     @param image a BufferedImage that contains the data for the image in file that the user has opened
     * @return newCanvas, the created canvas or NULL
     */
    public Canvas addLayer(BufferedImage image){
        if (layers.size() >= 10) {
            JOptionPane.showMessageDialog(null, "Cannot create more than 10 layers");
            return null;
        }
        Canvas newCanvas = new Canvas(project, image);

        // sets up editor
        newCanvas.setBackground(new Color(0, 0, 0, 0));
        newCanvas.setLocation(0, 0);

        // saves editor to array
        layers.add(newCanvas);
        layeredPane.add(newCanvas, new Integer(layers.size() + 1));

        project.getLayerSelector().addLayerButton();

        setCurrentLayer(layers.size() - 1);
        return newCanvas;
    }

    /**
     deletes the current layer that the user has selected/is on
     */
    public void deleteCurrentLayer() {
        if (layers.size() > 1) {
            Canvas toRemove = layers.get(selectedLayer);
            layers.remove(selectedLayer);
            layeredPane.remove(toRemove);
            project.getLayerSelector().deleteLayerButton(selectedLayer);
            for (int index = 0; index < layers.size(); index++) {
                Canvas layer = layers.get(index);

                layer.disableDrawing();
                layer.repaint();
            }
            layers.get(selectedLayer).enableDrawing();
        } else {
            System.out.println("no open tabs");
            JOptionPane.showMessageDialog(null, "Can't remove any more layers");
        }
    }
    
    /**
     returns the current layer the user has selected/is on
     * @return the current layer object
     */
    public Canvas getCurrentLayer() {
        if (selectedLayer != -1) {
            return layers.get(selectedLayer);
        }
        return null;
    }

    /**
     returns the index of the current layer
     * @return the selected layer's index
     */
    public int getCurrentLayerIndex() {
        return selectedLayer;
    }

    /**
      sets the layer that the user has selected to the current layer/active layer.
      So the user change/draw on different layers
     * @param index the index of the layer to be set active
     */
    public void setCurrentLayer(int index) {
        selectedLayer = index;
        for(int i=0;i<layers.size();i++)
        {
            layers.get(i).disableDrawing();
        }

        if(layers.get(index).isHidden() == false)
        {
            layers.get(index).enableDrawing();
        }
        project.getLayerSelector().setSelectedLayer(index);
    }

    /**
     returns a list of all the layer currently open in the applications
     * @return the list of layers
     */
    public ArrayList<Canvas> getLayers() {
        return layers;
    }

    /**
     resizes the window
     @param XResize the new width in pixels
     @param YResize the new height in pixels
     */
    public void Resize(int XResize,int YResize){
        layeredPane.setSize(XResize,YResize);
        layeredPane.revalidate();
    }

}
