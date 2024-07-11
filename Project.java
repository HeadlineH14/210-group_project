import java.awt.*;
import java.util.PrimitiveIterator;
//import java.util.concurrent.Flow;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.BoxLayout;

/**
This is the file that contains the editor window, he colour selector, etc.
this is where the draing of an image will tak place
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public class Project extends JPanel {
    // creates global avriables
    private ColorSelector colorSelector;
    private Editor editor;
    private ToolPicker toolPicker;
    private LayerSelector layerSelector;
    private Menu menu;

    // need to make it so that other files are returning what they are making
    // (panels)
    private JPanel projectFrame = this;
    private JPanel graphicsPanel = new JPanel();
    private JPanel otherPanel = new JPanel();

    private Resize Rs = new Resize(this);

    /**
     The constructor for this object
     Sets up the window and all componenets comtained within
     */
    public Project() {
        projectFrame.setVisible(true);
        projectFrame.setSize(1000, 1000);

        projectFrame.setLayout(new BorderLayout());

        graphicsPanel.setLayout(null);
        // otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.Y_AXIS));
        otherPanel.setLayout(new BorderLayout());
        // otherPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        try {
            layerSelector = new LayerSelector(this);
            Thread.sleep(30);
            editor = new Editor(this);
            Thread.sleep(30); // Sleep to ensure that the swing objects are instantiated in a consistent
            // order, otherwise some errors and missing elements can happen
            menu = new Menu(this);
            Thread.sleep(30);
            colorSelector = new ColorSelector(this);
            Thread.sleep(30);
            toolPicker = new ToolPicker(this);
            Thread.sleep(30);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        JPanel otherPanelWrap = new JPanel(new BorderLayout());
        otherPanelWrap.add(colorSelector, BorderLayout.NORTH);
        otherPanelWrap.add(layerSelector, BorderLayout.CENTER);

        otherPanel.add(otherPanelWrap, BorderLayout.CENTER);

        projectFrame.add(graphicsPanel, BorderLayout.CENTER);
        projectFrame.add(otherPanel, BorderLayout.EAST);
        projectFrame.add(menu.getMenuBar(), BorderLayout.NORTH);
        projectFrame.add(toolPicker, BorderLayout.WEST);
        graphicsPanel.add(editor);
        // otherPanel.add(colorSelector);
        // otherPanel.add(layerSelector);
        graphicsPanel.revalidate();
        otherPanel.revalidate();
        sleep(30);
        projectFrame.revalidate();
    }

    /**
    returns the JFrame window
     * @return the JFrame window
     */
    public JPanel getFrame() {
        return projectFrame;
    }

    /**
     *returns the ToolPicker object
     * @return the ToolPicker
     */
    public ToolPicker getToolPicker() {
        return toolPicker;
    }

    /**
     returns the ColorSelector object
     * @return the ColorSelector
     */
    public ColorSelector getColorSelector() {
        return colorSelector;
    }

    /**
     returns the Editor object
     * @return the Editor
     */
    public Editor getEditor() {
        return editor;
    }

    /**
     returns the LayerSelector object
     * @return the LayerSelector
     */
    public LayerSelector getLayerSelector() {
        return layerSelector;
    }

    /**
     this method pauses the program for t milli-seconds
     * @param t milli-seconds to wait
     */
    private void sleep(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

    }
}
